package com.spring.study.domain;

import java.util.Date;
import java.util.Objects;

/*
* DTO란? - Data Transfer Object
* 계층간의 데이터를 주고 받기 위해 사용되는 객체
*
* 게시판에서 글을 읽을 때는 board 테이블에 있는 data를 BoardDto 객체에 담아서 가져와야 하고,
* 게시판에서 글을 쓸 때는 DTO라는 객체에 값을 채워서 DB의 board 테이블에 저장하게 되는 것이다.
* 그래서 BoardDto는 board 테이블에 맞게 정의해줘야 한다.
* */
public class BoardDto {
    int bno;
    String title;
    String content;
    String writer;
    int view_cnt;
    int comment_cnt;
    Date reg_date;
    Date up_date;

    public BoardDto() {}

    public BoardDto(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardDto boardDTO = (BoardDto) o;
        return Objects.equals(bno, boardDTO.bno) && Objects.equals(title, boardDTO.title) && Objects.equals(content, boardDTO.content) && Objects.equals(writer, boardDTO.writer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bno, title, content, writer);
    }

    public int getBno() {
        return bno;
    }

    public void setBno(int bno) {
        this.bno = bno;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public int getView_cnt() {
        return view_cnt;
    }

    public void setView_cnt(int view_cnt) {
        this.view_cnt = view_cnt;
    }

    public int getComment_cnt() {
        return comment_cnt;
    }

    public void setComment_cnt(int comment_cnt) {
        this.comment_cnt = comment_cnt;
    }

    public Date getReg_date() {
        return reg_date;
    }

    public void setReg_date(Date reg_date) {
        this.reg_date = reg_date;
    }

    public Date getUp_date() {
        return up_date;
    }

    public void setUp_date(Date up_date) {
        this.up_date = up_date;
    }

    @Override
    public String toString() {
        return "BoardDto{" +
                "bno=" + bno +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", writer='" + writer + '\'' +
                ", viewCnt=" + view_cnt +
                ", commentCnt=" + comment_cnt +
                ", regDate=" + reg_date +
                ", upDate=" + up_date +
                '}';
    }
}
