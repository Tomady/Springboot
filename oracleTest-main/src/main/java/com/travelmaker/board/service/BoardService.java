package com.travelmaker.board.service;

import com.travelmaker.board.domain.BoardVO;
import com.travelmaker.board.domain.Criteria;

import java.util.List;

public interface BoardService {
    public void register(BoardVO board);
    public BoardVO get(int bbs_id);
    public boolean modify(BoardVO board);
    public boolean remove(int bbs_id);
//    public List<BoardVO> getList();
    public List<BoardVO> getList(Criteria cri);
    public int getTotal(Criteria cri);
}
