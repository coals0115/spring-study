package com.spring.study.service;

import com.spring.study.domain.BoardDto;

import java.util.List;
import java.util.Map;

public interface BoardService {
    int getCount();
    int remove(BoardDto boardDto);
    int write(BoardDto boardDto);
    List<BoardDto> getList();
    BoardDto read(Integer bno) throws Exception;
    List<BoardDto> getPage(Map<String, Integer> map) throws Exception;
    int modify(BoardDto boardDto);
//    int getSearchResultCnt(SearchCondition sc) throws Exception;
//    List<BoardDto> getSearchResultPage(SearchCondition sc) throws Exception;
}
