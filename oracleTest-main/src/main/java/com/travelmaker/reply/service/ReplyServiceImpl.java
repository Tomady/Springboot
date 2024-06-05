package com.travelmaker.reply.service;

import com.travelmaker.board.domain.Criteria;
import com.travelmaker.reply.domain.ReplyPageDTO;
import com.travelmaker.reply.domain.ReplyVO;
import com.travelmaker.reply.mapper.ReplyMapper;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ReplyServiceImpl implements ReplyService {
    @Setter(onMethod_ = @Autowired)
    private ReplyMapper mapper;

    @Override
    public int register(ReplyVO vo) {
        log.info("reply Register......." + vo);

        return mapper.insert(vo);
    }

    @Override
    public ReplyVO get(Long rno) {
        log.info("reply Get......." + rno);

        return mapper.read(rno);
    }

    @Override
    public int modify(ReplyVO vo) {
        log.info("reply Modify......." + vo);

        return mapper.update(vo);
    }

    @Override
    public int remove(Long rno) {
        log.info("reply Remove......." + rno);

        return mapper.delete(rno);
    }

    @Override
    public List<ReplyVO> getList(Criteria cri, Long bno) {
        log.info("get Reply List of a Board " + bno);

        return mapper.getListWithPaging(cri, bno);
    }

    @Override
    public ReplyPageDTO getListPage(Criteria cri, Long bno) {
        return new ReplyPageDTO(mapper.getCountByBno(bno), mapper.getListWithPaging(cri, bno));
    }
}
