package com.travelmaker.board.service;

import com.travelmaker.board.domain.BoardVO;
import com.travelmaker.board.domain.Criteria;
import com.travelmaker.board.mapper.BoardMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {
    private BoardMapper mapper;

    @Override
    public void register(BoardVO board) {
        log.info("register......." + board);

        mapper.insertSelectKey(board);
    }

    @Override
    public BoardVO get(int bbs_id) {
        log.info("get......." + bbs_id);

        return mapper.read(bbs_id);
    }

    @Override
    public boolean modify(BoardVO board) {
        log.info("modify......." + board);

        return mapper.update(board) == 1;
    }

    @Override
    public boolean remove(int bbs_id) {
        log.info("remove......." + bbs_id);

        return mapper.delete(bbs_id) == 1;
    }

//    @Override
//    public List<BoardVO> getList() {
//        log.info("getList.......");
//
//        return mapper.getList();
//    }

    @Override
    public List<BoardVO> getList(Criteria cri) {
        log.info("get List with criteria: " + cri);

        return mapper.getListWithPaging(cri);
    }
}
