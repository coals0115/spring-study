package com.spring.study.service;

import com.spring.study.dao.BoardDao;
import com.spring.study.domain.BoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    BoardDao boardDao;

    @Override
    public int getCount() {
        return boardDao.count();
    }

    @Override
    public int remove(BoardDto boardDto) {
        return boardDao.delete(boardDto);
    }

    @Override
    public int write(BoardDto boardDto) {
        return boardDao.insert(boardDto);
    }

    @Override
    public List<BoardDto> getList() {
        return boardDao.selectAll();
    }

    @Override
    public BoardDto read(Integer bno) throws Exception {
        return boardDao.select(bno);
    }

    @Override
    // 여기서 Map이 뭘 받아오는 거임? 페이지 핸들러에 넣어줄 값들? totalCnt, pageSize, curPage
    public List<BoardDto> getPage(Map<String, Integer> map) throws Exception {
        return boardDao.selectPage(map);
    }

    @Override
    public int modify(BoardDto boardDto) {
        return boardDao.update(boardDto);
    }

    // getSearchResultCnt

    // getSearchResultPage
}
