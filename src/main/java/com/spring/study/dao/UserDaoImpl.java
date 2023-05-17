package com.spring.study.dao;

import com.spring.study.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// DAO란? - 데이터에 접근하기 위한 객체
// UserDao를 통해서 DB에 있는 user_info TABLE로부터 CRUD를 한다.
@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    DataSource dataSource;
    /*
    * 유저 가져오기
    * 메서드명 : selectUser
    * 매개변수 : String id
    * 반환타입 : User
    * */
    @Override
    public User selectUser(String id) throws SQLException {
        // 1. DB 커넥션 얻어오기
        Connection conn = dataSource.getConnection();

        // 2. sql 생성
        String sql = "select * from user_info where id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, id);

        // 3. sql 실행
        ResultSet rs = pstmt.executeQuery(); // select

        // 4. 값 가져오기
        User user = new User();

        while (rs.next()) {
            user.setId(rs.getString(1));
            user.setPwd(rs.getString(2));
            user.setName(rs.getString(3));
            user.setEmail(rs.getString(4));
            user.setBirth(new Date(rs.getDate(5).getTime()));
            user.setSns(rs.getString(6));
            user.setRegDate(rs.getDate(7));
        }

        return user;
    }

    /*
     * 유저 삭제하기
     * 메서드명 : deleteUser
     * 매개변수 : String id
     * 반환타입 : int(삭제한 row의 개수)
     * */
    @Override
    public int deleteUser(String id) throws Exception {
        // 1. DB 커넥션을 얻어온다.
        Connection conn = dataSource.getConnection();

        // 2. sql문을 작성한다.
        String sql = "delete from user_info where id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, id);

        // 3. sql문을 실행한다.
        int result = pstmt.executeUpdate(); // insert, update, delete

        // 4. 결과를 반환한다.(영향을 받은 row수)
        return result;
    }

    /*
     * 유저 추가하기
     * 메서드명 : insertUser
     * 매개변수 : User user
     * 반환타입 : int(db에 영향을 준 row의수 - insert된 row의 수 반환)
     * */
    @Override
    public int insertUser(User user) throws SQLException {
        // 1. DB 커넥션을 얻어온다.
        Connection conn = dataSource.getConnection();

        // 2. sql문을 작성한다.
        String sql = "insert into user_info (id, pwd, name, email, birth, sns, reg_date)" +
                "values (?, ?, ?, ?, ?, ?, now());";

        // 3. sql문을 실행한다.
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, user.getId());
        pstmt.setString(2, user.getPwd());
        pstmt.setString(3, user.getName());
        pstmt.setString(4, user.getEmail());
        pstmt.setDate(5, new java.sql.Date(user.getBirth().getTime()));
        pstmt.setString(6, user.getSns());

        // 4. 결과를 반환한다.
        int result = pstmt.executeUpdate();

        return result;
    }

    /*
     * 유저 정보 수정
     * 메서드명 : updateUser
     * 매개변수 : User user
     * 반환타입 : int(DB에 영향을 준 row의 수 - update된 row의 수 반환)
     * */
    @Override
    public int updateUser(User user) throws SQLException {
        // 1. DB 커넥션을 얻어온다.
        Connection conn = dataSource.getConnection();

        // 2. sql문을 작성한다.
        String sql = "update user_info set pwd = ?, name = ?, email = ?, birth =?, sns = ?" +
                     "where id = ?";

        // 3. sql문을 실행한다.
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, user.getPwd());
        pstmt.setString(2, user.getName());
        pstmt.setString(3, user.getEmail());
        pstmt.setDate(4, new java.sql.Date(user.getBirth().getTime()));
        pstmt.setString(5, user.getSns());
        pstmt.setString(6, user.getId());

        int result = pstmt.executeUpdate();

        // 4. 결과를 반환한다.
        return result;
    }

    @Override
    public int count() throws SQLException {
        // 1. DB 커넥션을 얻어온다.
         Connection conn = dataSource.getConnection();

        // 2. sql문을 작성한다.
        String sql = "select count(*) from user_info;";

        // 3. sql문을 실행한다.
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet result = pstmt.executeQuery();

        // 4. 결과를 반환한다.
        int cnt = 0;
        while (result.next()) {
            cnt = result.getInt(1);
        }
        return cnt;
    }

    /*
     * user 테이블의 모든 행을 삭제한다.
     * 메서드명 : deleteAll
     * 매개변수 : 없음
     * 반환타입 : int(table에 영향을 준 row의 수 - delete된 row의 수 반환)
     * */
    @Override
    public int deleteAll() throws SQLException {
        // 1. DB 커넥션을 얻어온다.
        Connection conn = dataSource.getConnection();

        // 2. sql문을 작성한다.
        String sql = "delete from user_info";

        // 3. sql문을 실행한다.
        PreparedStatement pstmt = conn.prepareStatement(sql);
        int result = pstmt.executeUpdate();

        // 4. 결과를 반환한다.
        return result;
    }

    /*
     * user 테이블의 모든 행을 읽어온다.
     * 메서드명 : selectAll
     * 매개변수 : 없음
     * 반환타입 : List(순서 O, 중복 O)
     * */
    @Override
    public List selectAll() throws SQLException {
        // 1. DB 커넥션을 얻어온다.
        Connection conn = dataSource.getConnection();

        // 2. sql문을 작성한다.
        String sql = "select * from user_info";

        // 3. sql문을 실행한다.
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

        List<User> userList = new ArrayList<>();
        User user = new User();

        // 4. 결과를 반환한다.
        while (rs.next()) {
            user.setId(rs.getString(1));
            user.setPwd(rs.getString(2));
            user.setName(rs.getString(3));
            user.setEmail(rs.getString(4));
            user.setBirth(new Date(rs.getDate(5).getTime()));
            user.setSns(rs.getString(6));
            user.setRegDate(rs.getDate(7));

            userList.add(user);
        }

        return userList;
    }
}
