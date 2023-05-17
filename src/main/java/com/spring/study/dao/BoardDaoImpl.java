package com.spring.study.dao;

import com.spring.study.domain.BoardDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/*
* DAO란? - Data Access Object(데이터에 접근하기 위한 객체)
* BoardDao를 통해서 DB에 있는 board 테이블로부터 CRUD를 한다.
* */
@Repository
public class BoardDaoImpl implements BoardDao {
    @Autowired
    private SqlSession session; // SQL 명령을 수행하는데 필요한 메서드 제공

    String namespace = "com.spring.dao.BoardMapper."; // 뒤에 점 하나 붙여야 하는 거 잊지말기

    /*
    * 게시글 하나를 가져온다.
    * 메서드명 : select
    * 매개변수 : int bno(게시글 번호)
    * 반환타입 : BoardDto
    * */
    @Override
    public BoardDto select(int bno) {
        // selectOne : 하나의 행을 반환하는 select에 사용 / parameter로 SQL에 binding될 값 제공
        return session.selectOne(namespace + "select", bno);
    }

    /*
     * 게시글을 등록한다.
     * 메서드명 : insert
     * 매개변수 : BoardDto
     * 반환타입 : int(insert된 row의 수)
     */
    @Override
    public int insert(BoardDto boardDto) {
        return session.insert(namespace + "insert", boardDto);
    }

    /*
    * 게시글을 수정한다.
    * 메서드명 : update
    * 매개변수 : BoardDto
    * 반환타입 : int(update된 row의 수)
    * */
    @Override
    public int update(BoardDto boardDto) {
        return session.update(namespace + "update", boardDto);
    }

    /*
    * 게시글을 삭제한다.
    * 메서드명 : delete
    * 매개변수 : BoardDto
    * 반환타입 : int(delete된 row의 수)
    * */
    @Override
    public int delete(BoardDto boardDto) {
        return session.delete(namespace + "delete", boardDto);
    }

    /*
    * 게시글의 개수를 가져온다.
    * 메서드명 : count
    * 매개변수 : 없음
    * 반환타입 : int
    * */
    @Override
    public int count() {
        return session.selectOne(namespace + "count");
    }

    /*
    * 모든 게시글을 삭제한다.
    * 메서드명 : deleteAll
    * 매개변수 : 없음
    * 반환타입 : int(삭제된 게시글의 수)
    * */
    @Override
    public int deleteAll() {
        return session.delete(namespace + "deleteAll");
    }

    /*
    * 모든 게시글을 가져온다.
    * 메서드명 : selectAll
    * 매개변수 : 없음
    * 반환타입 : List<BoardDto>
    * */
    @Override
    public List<BoardDto> selectAll() {
        return session.selectList(namespace + "selectAll");
    }

    /*
    * 페이지에 해당하는 게시물을 가져온다.
    * 메서드명 : selectPage
    * 매개변수 : Map map
    * 반환타입 : List<BoardDto>
    *  */
   public List<BoardDto> selectPage(Map<String, Integer> map) {
       return session.selectList(namespace + "selectPage", map);
   }







}
