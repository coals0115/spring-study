const NUMBER_OF_CARDS = 52; // 카드의 총 개수
const NUMBER_OF_HINTS = 2;  // 힌트로 보여줄 카드의 개수
const NUMBER_OF_RANKS = 13;

const btnShow = document.getElementById('btnShow');
const btnHide = document.getElementById('btnHide');
const btnShuffle = document.getElementById('btnShuffle');

const btnHint = document.getElementById('hint');
const pairsLeft = document.getElementById('pairsLeft');

let cardArr = new Array(NUMBER_OF_CARDS);

window.onload = function () { // main 메서드
    initGame(); // 게임 초기화
    startGame(); // 게임 시작
}

// show 버튼을 누르면 카드를 전부 보여준다.
btnShow.onclick = function() { flipAllCards(cardArr, false); };
// hide 버튼을 누르면 카드를 전부 숨긴다.
btnHide.onclick = function() { flipAllCards(cardArr, true); };

btnShuffle.onclick = function() {
    initGame(cardArr);
    startGame(cardArr);
};

btnHint.onclick = showHint;

const A = 1;
const J = 11
const Q = 12;
const K = 13;

const DIAMOND = 1;
const CLOVER = 2;
const HEART = 3;
const SPADE = 4;

function Card(number, suit) {
    //TODO suit랑 number 지역변수화하기
    this.number = number;
    this.suit = suit;
    this.isFront = true; // 현재 카드가 앞면인지를 확인하는 변수
    this.isPaired = false; // pair 여부를 확인하는 변수
}

Card.prototype.toString = function() {
    let suit = "";
    let number = "";

    switch (this.suit) {
        case 1: suit = "◆"; break;
        case 2: suit = "♧"; break;
        case 3: suit = "♡"; break;
        case 4: suit = "♠"; break;
        default:
    }

    switch (this.number) {
        case 1:  number = "A"; break;
        case 11: number = "J"; break;
        case 12: number = "Q"; break;
        case 13: number = "K"; break;
        default: number = this.number + "";
    }

    return suit + number;
}

// 카드의 아이디(number + "" + suit)를 가져온다.
Card.prototype.getId = function() {
    return this.number + "" + this.suit;
}

// 게임 초기화
function initGame() {
    // 1. card를 담을 배열을 만든다.

    // 2. 배열을 초기화한다.
    initCard(cardArr);

    // 3. 배열을 섞는다.
    shuffleCard(cardArr);

    // 4. 카드를 div#board에 보여준다.
    showCard(cardArr);

    // 5. 각 카드에 클릭 이벤트를 등록한다.
    let imgArr = document.getElementsByTagName("img");

    for (let i = 0; i < imgArr.length; i++) {
        imgArr[i].onclick = cardClick;
    }

    // 6. 남은 pair의 개수 초기값 보여주기
    pairsLeft.innerHTML = NUMBER_OF_CARDS / 2;
}

function startGame() {
    // 카드 전부를 뒷면 보여준다.
    flipAllCards(cardArr, true);
}

// 카드 배열을 초기화한다.
function initCard(cardArr) {
    let suitChk = 1;
    let numberChk = 1;

    for (let i = 0; i < NUMBER_OF_CARDS; i++) {
        cardArr[i] = new Card(numberChk++, suitChk++);

        if (suitChk > 4) suitChk = 1; // 다이아몬드(◆) = 4 그림 카드까지 만들었을 경우 다시 스페이드(♠) = 1 카드를 만들기 위해 1로 변경
        if (numberChk > 13) numberChk = 1; // K(13)까지 카드를 만들었을 경우 다시 A(1)를 넣어주기 위해 1로 변경
    }

    // 기본 정렬
    cardArr.sort(cardDefaultSortComparator);
}

// 두 카드의 짝이 일치하는지 체크한다.
function checkPair(twoCards) {
    return twoCards[0].number == twoCards[1].number && Math.abs(twoCards[0].suit - twoCards[1].suit);
}

// 남은 pair의 개수 update
function updatePairsLeft() {
    // 남은 pair의 개수 구하는 법 = getBackCardIndexArr 해서 가져온 배열의 length / 2하면 됨

    const leftCardIndexArr = getLeftCardIndexArr();
    pairsLeft.innerHTML = leftCardIndexArr.length / 2;

    // 남은 카드의 개수가 1개 이하면 다시하겠냐고 물어보고 Y면 shuffle N이면기끝내기
    if (leftCardIndexArr.length < 1) {
        // 걍 물어보지 말고 1개 이하면 카드 다 지우고 다시하기 버튼이랑 시도 횟수 보여주기
        if (confirm("성공. 게임을 종료하시겠습니까? | 게임 종료 = 확인 | 재시도 = 취소")) {
            alert("게임 종료");
        } else {
            btnShuffle.onclick();
        }
    }
}

// 매치 여부를 변경한다.
function updateIsPaired(cards, isPaired) {
    let card;
    for (let i = 0; i < cards.length; i++) {
        card = cards[i];

        document.getElementById(card.getId()).classList.add("matched");
        card.isPaired = isPaired;
    }
}

// 카드 클릭 시 발생되는 이벤트
function cardClick() {
    // 카드를 찾는다.
    const card = findCard(this);

    // 1. 카드가 앞면인지 확인한다.
    if (isFront(card))
        // 1-1. 앞면이면 return
        return;

    // 1-2. 뒷면이면 뒤집는다.
    flip(card);

    // 2. 뒤집은 카드가 2장인가?
    const chkTwoCards = checkFrontCardCountTwo();
    if (chkTwoCards) {
        // 2-1. 두 카드의 짝이 일치하는지 체크한다.
        if (checkPair(chkTwoCards)) {
            // 2-2. 일치하면 계속 진행한다.
            // pair인 카드들의 isMatched = true로 변경
            updateIsPaired(chkTwoCards, true);
            // 남은 pair의 개수 변경해주기
            updatePairsLeft();

        } else {
            // 2-3. 일치하지 않으면 클릭했던 카드들을 다시 뒤집는다.
            setTimeout(function() {
                flipAllCards(chkTwoCards, true);
            }, 600);
        }
    }
}

// 기본 정렬 - 1순위 suit 2순위 숫자
function cardDefaultSortComparator(c, c2) {
    if (c.suit == c2.suit) {
        return c.number - c2.number;
    } else {
        return c.suit - c2.suit;
    }
}

// 카드를 섞는다.
function shuffleCard(cardArr) {
    cardArr = cardArr.sort(() => Math.random() - 0.5);
}

// 카드를 div#board에 보여준다.
function showCard(cardArr) {
    // 이미지 파일명 구해오는 공식 = (number - 1) + (suit - 1) * 13

    let c;
    let result = "";
    // 1. 카드의 개수만큼 반복문 돌린다.
    for (let i = 0; i < NUMBER_OF_CARDS; i++) {
        // 2. 카드 객체를 하나 가져와서 <img src="${(c.number - 1) + (c.suit - 1) *13}.png">
        c = cardArr[i];
        // 3. 위의 결과를 result 문자열에 집어넣은 뒤
        // 처음에 카드 10초간 보여주고 gameStart()에서 hide하자
        result += `<img src="${(c.number - 1) + (c.suit - 1) * NUMBER_OF_RANKS}.png" id=${c.getId()} data-number="${c.number}" data-suit=${c.suit} data-isFront=true>`;
        // 12개가 한줄 = 만약 i가 12라면 br태그 넣어주기
        if ((i + 1) % 13 == 0) {
            result+= `<br>`;
        }
    }
    // 4. div#board에 innerHTML로 넣어준다.
    document.getElementById('cardGameBoard').innerHTML = result;

}

// card가 앞면이면 true 아니면 false를 반환
// card는 Card 객체가 들어와야 한다.
function isFront(card) {
    return card.isFront;
}

// 카드를 뒤집는다.
function flip(card, isFront) {
    // 1. 카드가 앞면인지 확인한다. +) hide시에 pair된 카드들은 여젼히 보여져야 한다.
    if (isFront && !card.isPaired) {
        // 1-1. 앞면(그림)이면 뒷면(농담곰)을 보여준다.
        showBack(card);
    } else {
        // 1-2. 뒷면(농담곰)이면 앞면(그림을 보여준다.)
        showFront(card);
    }
}

// 카드의 앞면을 보여준다.
function showFront(card) {
    let cardImg = document.getElementById(card.getId());

    cardImg.src = `/static/img/cardGame/${(card.number - 1) + (card.suit - 1) *13}.png`;
    card.isFront = true;
}

// 카드의 뒷면(농담곰)을 보여준다.
function showBack(card) {
    let cardImg = document.getElementById(card.getId());

    cardImg.src = '/static/img/cardGame/bear.png';
    card.isFront = false;
}

// 카드를 모두 뒤집는다.
function flipAllCards(cardArr, isFront) {
    // 카드의 총 개수만큼 반복문 돌면서 flip(card)를 호출
    for (const card of cardArr) {
        flip(card, isFront);
    }
}

// 뒤집혀있는 카드의 개수가 2장인지 체크한다.
function checkFrontCardCountTwo() {
    // 뒤집혀있는 카드의 조건 : isFront = true && isMatched = false
    let returnCards = [];

    for (let i = 0; i < NUMBER_OF_CARDS; i++) {
        let card = cardArr[i];
        if (card.isFront && !card.isPaired) {
            returnCards.push(card);
        }
    }

    if (returnCards.length == 2) { // 뒤집혀있는 카드의 개수가 2개면 2장의 카드를 담은 배열반환
        return returnCards;
    } else { // 아니라면 false반환
        return false;
    }
}

// carImg 엘리먼트에서 cardArr중에 일치하는 카드를 찾는다.
function findCard(cardImg) {
    const number = parseInt(cardImg.getAttribute('data-number'));
    const suit = parseInt(cardImg.getAttribute('data-suit'));

    for (let i = 0; i < NUMBER_OF_CARDS; i++) {
        let card = cardArr[i];
        if (card.number == number && card.suit == suit)
            return card;
    }
}

// 뒷면인 카드들의 index를 가져온다. (이미 앞면인 카드들은 제외)
function getLeftCardIndexArr() {
    let notFrontCardIndexArr = [];
    for (let i = 0; i < NUMBER_OF_CARDS; i++) {
        if (!cardArr[i].isPaired)
            notFrontCardIndexArr.push(i);
    }

    return notFrontCardIndexArr;
}

function getRandom(max, count) {
    // 이걸 Set으로 바꾸고 frontIndexArr의 length가 2가 될 때까지 반복문 돌기
    let randomNumArr = new Set();

    while (randomNumArr.size < count) {
        randomNumArr.add(parseInt(Math.random() * max));
    }
    return randomNumArr;
}

function showHintRandom(cards) {
    // notFrontCardIndexArr에서 랜덤한 카드의 index를 가져와서 해당하는 index의 카드의 앞면을 보여준다.
    const frontIndexArr = []; // 힌트로 보여준 card의 index를 저장하기 위한 변수
    const randomNumArr = getRandom(cards.length, NUMBER_OF_HINTS);

    for (const random of randomNumArr) {
        flip(cardArr[cards[random]], false); // 랜덤한 카드의 앞면을 보여준다.
        frontIndexArr.push(cards[random]);
        //  [조건2] 보여지는 2개의 카드가 같은 카드면 안 된다.
        cards.splice(random, 1);

    }

    return frontIndexArr;
}

// 랜덤한 2개의 카드의 앞면을 2초동안 보여준다.
function showHint() {
    //  [조건1] 이미 짝이 맞춰진 카드는 제외해야 한다. - 뒷면인 카드들의 index를 가져온다. (이미 앞면인 카드들은 제외)
    let backCardIndexArr = getLeftCardIndexArr();
    // 뒷면인 카드들 중에서 랜덤으로 앞면을 보여준다.
    let frontIndexArr = showHintRandom(backCardIndexArr); // 지금 얘가 이상한 거 같음  {6, 27}

    // 1초 후에 보여주었던 2개의 카드의 뒷면을 다시 보여준다.
    btnHint.disabled = true; // 기다리는 1초동안 또 힌트 버튼을 누를 수 없도록 disabled 처리
    setTimeout(function() {
        for (let i = 0; i < NUMBER_OF_HINTS; i++) {
            flip(cardArr[frontIndexArr[i]], true);
        }
        btnHint.disabled = false;
    }, 1000);
}

// from과 to 범위의 정수(int)값을 반환한다. from과 to 모두 범위에 포함된다.
function getRand(from, to) {
    return parseInt(((Math.random() * Math.abs(to-from) + 1))) + Math.min(from, to);
}
