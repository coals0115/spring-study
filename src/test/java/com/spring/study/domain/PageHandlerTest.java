package com.spring.study.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class PageHandlerTest {
    /*
     * 맨 앞, 맨 끝 테스트 * */
    @Test
    public void firstPageTest() {
        PageHandler p = new PageHandler(252, 1);
        p.print();
        System.out.println("p = " + p);

        assertEquals(1, p.getBeginPage());
        assertEquals(10, p.getEngPage());
        assertEquals(26, p.getTotalPage());
        assertFalse(p.isShowPrev());
        assertTrue(p.isShowNext());
    }

    @Test
    public void lastPageTest() {
        PageHandler p = new PageHandler(252, 24);
        p.print();
        System.out.println("p = " + p);

        assertEquals(21, p.getBeginPage());
        assertEquals(26, p.getEngPage());
        assertEquals(26, p.getTotalPage());
        assertTrue(p.isShowPrev());
        assertFalse(p.isShowNext());
    }

    @Test
    public void test() {
        PageHandler ph = new PageHandler(251, 10, 13);
        System.out.println(ph);

        assertTrue(ph.getTotalPage() == 26);
        assertTrue(ph.getBeginPage() == 11);
        assertTrue(ph.getEngPage() == 20);
        assertTrue(ph.isShowPrev());
        assertTrue(ph.isShowNext());
    }

    @Test
    public void test2() {
        PageHandler ph = new PageHandler(41, 10, 3);
        System.out.println(ph);

        assertTrue(ph.getTotalPage() == 5);
        assertTrue(ph.getBeginPage() == 1);
        assertTrue(ph.getEngPage() == 5);
        assertFalse(ph.isShowPrev());
        assertFalse(ph.isShowNext());
    }

    @Test
    public void test3() {
        PageHandler ph = new PageHandler(121, 10, 13);

        ph.print();
        System.out.println(ph);

        assertTrue(ph.getTotalPage() == 13);
        assertTrue(ph.getBeginPage() == 11);
        assertTrue(ph.getEngPage() == 13);
        assertTrue(ph.isShowPrev());
        assertFalse(ph.isShowNext());
    }

}