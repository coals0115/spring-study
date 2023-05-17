package com.spring.study.dao;

import com.spring.study.domain.BoardDto;

import java.util.List;
import java.util.Map;

public interface BoardDao {
    /*
     * 게시글 하나를 가져온다.
     * 메서드명 : select
     * 매개변수 : int bno(게시글 번호)
     * 반환타입 : BoardDto
     * */
    BoardDto select(int bno);

    /*
     * 게시글을 등록한다.
     * 메서드명 : insert
     * 매개변수 : BoardDto
     * 반환타입 : int(insert된 row의 수)
     */
    int insert(BoardDto boardDto);

    /*
     * 게시글을 수정한다.
     * 메서드명 : update
     * 매개변수 : BoardDto
     * 반환타입 : int(update된 row의 수)
     * */
    int update(BoardDto boardDto);

    /*
     * 게시글을 삭제한다.
     * 메서드명 : delete
     * 매개변수 : BoardDto
     * 반환타입 : int(delete된 row의 수)
     * */
    int delete(BoardDto boardDto);

    /*
     * 게시글의 개수를 가져온다.
     * 메서드명 : count
     * 매개변수 : 없음
     * 반환타입 : int
     * */
    int count();

    /*
     * 모든 게시글을 삭제한다.
     * 메서드명 : deleteAll
     * 매개변수 : 없음
     * 반환타입 : int(삭제된 게시글의 수)
     * */
    int deleteAll();

    /*
     * 모든 게시글을 가져온다.
     * 메서드명 : selectAll
     * 매개변수 : 없음
     * 반환타입 : List<BoardDto>
     * */
    List<BoardDto> selectAll();

    List<BoardDto> selectPage(Map<String, Integer> map);
}
