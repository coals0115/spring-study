package com.spring.study.dao;

import com.spring.study.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

// 하나의 ac를 만들어두고 재사용하기 때문에 성능에 이점이 있음
@RunWith(SpringJUnit4ClassRunner.class) // ac를 자동으로 만들어줌
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
// xml 설정 파일 위치를 지정해줌. root-context.xml에 존재하는 bean으로 테스트함
public class UserDaoImplTest {
    @Autowired
    UserDao userDao;

    @Test
    public void selectUser() throws SQLException {
        userDao.deleteAll();
        int cnt = userDao.count();
        System.out.println("cnt = " + cnt);
        assertTrue(cnt == 0);

        User user1 = new User("asdf", "1234", "하이", "aaa@aaa.com", new Date(2000, 0, 1), "", new Date());
        userDao.insertUser(user1);
        User user2 = userDao.selectUser(user1.getId());
        User user3 = new User("asdf1", "1234", "하이", "aaa@aaa.com", new Date(2000, 0, 1), "", new Date());

        System.out.println("user1 = " + user1);
        System.out.println("user2 = " + user2);
        System.out.println("user3 = " + user3);

        assertTrue(user1.getId().equals(user2.getId()));
        assertTrue(user1.equals(user2));
        assertTrue(!user1.equals(user3 ));

    }

    @Test
    public void deleteUser() throws Exception {
        userDao.deleteAll();
        int cnt = userDao.count();
        System.out.println("cnt = " + cnt);
        assertTrue(cnt == 0);

        // 1. user insert
        User user1 = new User("asdf", "1234", "하이", "aaa@aaa.com", new Date(2000, 0, 1), "", new Date());
        userDao.insertUser(user1);

        // 2. user delete
        userDao.deleteUser(user1.getId());

        // 3. selectUser로 삭제 잘 됐는지 확인
        User user2 = userDao.selectUser(user1.getId());

        System.out.println("user1 = " + user1);
        System.out.println("user2 = " + user2);

        assertTrue(!user1.equals(user2));
    }

    @Test
    public void insertUser() throws Exception {
        userDao.deleteAll();
        int cnt = userDao.count();
        System.out.println("cnt = " + cnt);
        assertTrue(cnt == 0);

        // 1. user insert
        User user1 = new User("asdf", "1234", "하이", "aaa@aaa.com", new Date(2000, 0, 1), "", new Date());
        userDao.insertUser(user1);

        // 2. insert한 User 객체의 id로 user select
        User user2 = userDao.selectUser(user1.getId());

        System.out.println("user1 = " + user1);
        System.out.println("user2 = " + user2);

        // 3. insert한 User 객체와 select 해온 User 객체가 같은지 검증
        assertTrue(user1.equals(user2));
    }

    @Test
    public void updateUser() throws SQLException {
        userDao.deleteAll();
        int cnt = userDao.count();
        System.out.println("cnt = " + cnt);
        assertTrue(cnt == 0);

        // 1. insert user
        User user1 = new User("asdf", "1234", "하이", "aaa@aaa.com", new Date(2000, 0, 1), "", new Date());
        userDao.insertUser(user1);

        // 2. update user
        User user2 = new User("asdf", "4321", "헬로", "zzz@zzz.com", new Date(1000, 4, 20), "", new Date());
        userDao.updateUser(user2);

        // 3. update한 user객체의 id로 user를 가져와서 user1과 같은지 검증
        User user3 = userDao.selectUser(user2.getId());

        assertTrue(user2.equals(user3));
    }

    @Test
    public void count() throws SQLException {
        userDao.deleteAll();
        int cnt = userDao.count();
        System.out.println("cnt = " + cnt);
        assertTrue(cnt == 0);

        // delete하고 0인지 확인 모든 작업이 맞는지 확인

        int userCnt = 43;

        // 1. 43개 user insert
        for (int i = 0; i < userCnt; i++) {
            User user = new User("asdf" + i, "1234", "하이", "aaa@aaa.com", new Date(2000, 0, 1), "", new Date());
            userDao.insertUser(user);
        }

        // 2. 내가 insert한 유저의 개수와 user_info table에 들어가있는 row의 수가 같은지 검증
        cnt = userDao.count();
        System.out.println("cnt = " + cnt);
        assertTrue(userCnt == cnt);
    }

    @Test
    public void deleteAll() throws SQLException {
        userDao.deleteAll();
        int cnt = userDao.count();
        System.out.println("cnt = " + cnt);
        assertTrue(cnt == 0);
    }

    @Test
    public void selectAll() throws SQLException {
        userDao.deleteAll();
        int cnt = userDao.count();
        System.out.println("cnt = " + cnt);
        assertTrue(cnt == 0);

        List<User> userList1 = new ArrayList<>();

        int userCnt = 43;

        // 1. 43개 user insert
        for (int i = 0; i < userCnt; i++) {
            User user = new User("asdf" + i, "1234", "하이", "aaa@aaa.com", new Date(2000, 0, 1), "", new Date());
            userDao.insertUser(user);
            userList1.add(user);
        }

        List<User> userList2 = userDao.selectAll();

        int cnt2 = (int) userList1.stream().count();
        int cnt3 = (int) userList2.stream().count();

        assertTrue(cnt2 == cnt3);
//        for (int i = 0; i < userCnt; i++) {
//            User user1 = userList1.get(i);
//            User user2 = userList2.get(i);
//
//            System.out.println("user1 = " + user1);
//            System.out.println("user2 = " + user2);
//
//            assertTrue(user1.equals(user2));
//        }
    }
}