package com.spring.study.dao;

import com.spring.study.domain.BoardDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

// 하나의 ac를 만들어두고 재사용하기 때문에 성능에 이점이 있음
@RunWith(SpringJUnit4ClassRunner.class) // ac를 자동으로 만들어줌
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class BoardDaoImplTest {
    @Autowired
    BoardDao boardDao;

    @Before
    public void beforeEach(){
        boardDao.deleteAll();
        assertTrue(boardDao.selectAll().size() == 0);
    }

    @Test
    public void select() {
        // 1개 insert
        // BoardDto를 생성해서 insert하고 그 boardDto의 bno로 BoardDto 객체를 select 해와서 insert한 게시글과 select 해온 게시글이 같은지 검증
        BoardDto boardDto = new BoardDto("제목", "내용", "작가1");
        boardDao.insert(boardDto);
        int bno = boardDao.selectAll().get(0).getBno();
        boardDto.setBno(bno);

        BoardDto boardDto1 = boardDao.select(bno);

        System.out.println("boardDto = " + boardDto);
        System.out.println("boardDto1 = " + boardDto1);

        assertTrue(boardDto.equals(boardDto1));
    }

    @Test
    public void insert() {
        BoardDto boardDto1 = new BoardDto("제목", "내용", "작가1");
        assertTrue(boardDao.insert(boardDto1) == 1);
        assertTrue(boardDao.count() == 1);

        BoardDto boardDto2 = new BoardDto("제목", "내용", "작가1");
        assertTrue(boardDao.insert(boardDto2) == 1);
        assertTrue(boardDao.count() == 2);
    }

    @Test
    public void update() {
        // 1. 게시글 등록
        BoardDto boardDto1 = new BoardDto("제목", "내용", "작가1");
        boardDao.insert(boardDto1);
        int bno = boardDao.selectAll().get(0).getBno();

        // 2. 게시글 수정
        // 수정된 게시글이 하나인지 검증
        boardDto1.setBno(bno);
        boardDto1.setTitle("수정");
        boardDto1.setContent("수정");
        assertTrue(boardDao.update(boardDto1) == 1);

        // 3. 수정한 게시글의 id로 게시글 조회해와서 수정한 게시글의 boardDto와 같은지 검증
        BoardDto boardDto2 = boardDao.select(bno);

        System.out.println("boardDto1 = " + boardDto1);
        System.out.println("boardDto2 = " + boardDto2);

        assertTrue(boardDto1.equals(boardDto2));
    }

    @Test
    public void delete() {
        // 1. 게시글 하나 등록
        BoardDto boardDto1 = new BoardDto("제목", "내용", "작가1");
        assertTrue(boardDao.insert(boardDto1) == 1);

        // 2. 게시글 삭제
        // 2-1. 게시글 번호를 얻어와서 그걸로 삭제함
        int bno = boardDao.selectAll().get(0).getBno();
        boardDto1.setBno(bno);
        // 2-2. 삭제된 row가 1개인지 검증
        assertTrue(boardDao.delete(boardDto1) == 1);

        // 3. 게시글 번호로 select해왔을 때 등록한 boardDto와 같지 않은지 검증
        BoardDto boardDto2 = boardDao.select(bno);

        System.out.println("boardDto1 = " + boardDto1);
        System.out.println("boardDto2 = " + boardDto2);

        assertTrue(boardDto2 == null);
        assertTrue(!boardDto1.equals(boardDto2));
    }

    @Test
    public void count() {
        // 1. 34개의 게시글 등록
        int cnt = 30;

        for (int i = 0; i < cnt; i++) {
            BoardDto boardDto1 = new BoardDto("제목" + i, "내용" + i, "작가" + i);
            boardDao.insert(boardDto1);
        }

        // 2. boardDao.count()로 게시글 개수 얻어온다.

        // 3. 34개와 같은지 검증
        assertTrue(cnt == boardDao.count());
    }

    @Test
    public void deleteAll() {
        boardDao.deleteAll();
        assertTrue(boardDao.selectAll().size() == 0);
    }

    @Test
    public void selectAll() {
        // 0. 현재 존재하는 모든 게시글을 삭제한다.
        boardDao.deleteAll();
        assertTrue(boardDao.count() == 0);
        assertTrue(boardDao.selectAll().size() == 0);

        // 1. 34개의 게시글을 등록한다.
        List<BoardDto> boardDtoList = new ArrayList<>();
        int cnt = 34;
        for (int i = 0; i < cnt; i++) {
            BoardDto boardDto1 = new BoardDto("제목" + i, "내용" + i, "작가" + i);
            boardDao.insert(boardDto1);
            boardDtoList.add(boardDto1);
        }

        // 2. 등록된 게시글의 개수가 34개인지 검증한다.
        assertTrue(boardDao.selectAll().size() == cnt);
    }
}