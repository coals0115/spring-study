package com.spring.study.domain;

/*  */
public class PageHandler {
    private int pageSize; // 한 페이지당 게시물 개수
    private int curPage; // 현재 페이지
    private final int NAV_SIZE = 10;
    private int totalCnt; // 게시물의 총 개수
    private int totalPage; // 전체 페이지의 개수
    private int beginPage; // 화면에 보여줄 첫 페이지
    private int engPage; // 화면에 보여줄 마지막 페이지
    private boolean showNext; // 이후를 보여줄지의 여부. endPage == totalPage이면, showNext는 false
    private boolean showPrev; // 이전을 보여줄지의 여부. beginPage == 1이면, showPrev는 false

    public PageHandler(int totalCnt, int curPage) {
        this(totalCnt, 10, curPage);
    }

    // 게시물의 총 개수와 현재 페이지로 계산 가능?
    public PageHandler(int totalCnt, int pageSize, int curPage) {
        this.totalCnt = totalCnt;
        this.pageSize = pageSize;
        this.curPage = curPage;

        totalPage = (int) Math.ceil(totalCnt / (double) pageSize); // totalPage = 23, pageSize = 10일 때, 남는 페이지가 있을 수 있기 때문에 올림처리.
        beginPage = (curPage-1) / NAV_SIZE * NAV_SIZE + 1;
        engPage = Math.min(beginPage + NAV_SIZE - 1, totalPage); // totalPage가 endPage보다 작을 수 있기 때문
        showNext = engPage != totalPage;
        showPrev = beginPage != 1;
    }

    public void print() {
        if (showPrev)
            System.out.print("< ");

        for (int i = beginPage; i <= engPage; i++) {
            System.out.print(i + " ");
        }

        if (showNext)
            System.out.println(">");
        System.out.println();
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getNAV_SIZE() {
        return NAV_SIZE;
    }

    public int getTotalCnt() {
        return totalCnt;
    }

    public void setTotalCnt(int totalCnt) {
        this.totalCnt = totalCnt;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getBeginPage() {
        return beginPage;
    }

    public void setBeginPage(int beginPage) {
        this.beginPage = beginPage;
    }

    public int getEngPage() {
        return engPage;
    }

    public void setEngPage(int engPage) {
        this.engPage = engPage;
    }

    public boolean isShowNext() {
        return showNext;
    }

    public void setShowNext(boolean showNext) {
        this.showNext = showNext;
    }

    public boolean isShowPrev() {
        return showPrev;
    }

    public void setShowPrev(boolean showPrev) {
        this.showPrev = showPrev;
    }

    @Override
    public String toString() {
        return "PageHandler{" +
                "pageSize=" + pageSize +
                ", page=" + curPage +
                ", NAV_SIZE=" + NAV_SIZE +
                ", totalCnt=" + totalCnt +
                ", totalPage=" + totalPage +
                ", beginPage=" + beginPage +
                ", engPage=" + engPage +
                ", showNext=" + showNext +
                ", showPrev=" + showPrev +
                '}';
    }
}
