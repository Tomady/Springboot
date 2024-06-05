package com.travelmaker.reply.service;

import com.travelmaker.board.domain.Criteria;
import com.travelmaker.reply.domain.ReplyPageDTO;
import com.travelmaker.reply.domain.ReplyVO;

import java.util.List;

public interface ReplyService {
    public int register(ReplyVO vo);
    public ReplyVO get(Long rno);
    public int modify(ReplyVO vo);
    public int remove(Long rno);
    public List<ReplyVO> getList(Criteria cri, Long bno);
    public ReplyPageDTO getListPage(Criteria cri, Long bno);
}
