package com.spring.study.dao;

import com.spring.study.domain.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    /*
     * 유저 가져오기
     * 메서드명 : selectUser
     * 매개변수 : String id
     * 반환타입 : User
     * */
    User selectUser(String id) throws SQLException;

    /*
    * 유저 삭제하기
    * 메서드명 : deleteUser
    * 매개변수 : String id
    * 반환타입 : int(삭제한 row의 개수)
    * */
    int deleteUser(String id) throws Exception;

    /*
    * 유저 추가하기
    * 메서드명 : insertUser
    * 매개변수 : User user
    * 반환타입 : int(db에 영향을 준 row의수 - insert된 row의 수 반환)
    * */
    int insertUser(User user) throws SQLException;

    /*
    * 유저 정보 수정
    * 메서드명 : updateUser
    * 매개변수 : User user
    * 반환타입 : int(DB에 영향을 준 row의 수 - update된 row의 수 반환)
    * */
    int updateUser(User user) throws SQLException;

    /*
    * user 테이블의 행 갯수를 반환한다.
    * 메서드명 : count
    * 매개변수 : 없음
    * 반환타입 : int(행 갯수)
    * */
    int count() throws SQLException;

    /*
    * user 테이블의 모든 행을 삭제한다.
    * 메서드명 : deleteAll
    * 매개변수 : 없음
    * 반환타입 : int(table에 영향을 준 row의 수 - delete된 row의 수 반환)
    * */
    int deleteAll() throws SQLException;

    /*
    * user 테이블의 모든 행을 읽어온다.
    * 메서드명 : selectAll
    * 매개변수 : 없음
    * 반환타입 : List(순서 O, 중복 O)
    * */
    List selectAll() throws SQLException;
}
