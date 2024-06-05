package com.travelmaker.reply.mapper;

import com.travelmaker.board.domain.Criteria;
import com.travelmaker.reply.domain.ReplyVO;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
@Slf4j
public class ReplyMapperTests {
    @Setter(onMethod_ = @Autowired)
    private ReplyMapper mapper;

    private Long[] bnoArr = {3595L, 3594L, 3593L, 3592L, 3591L};

    @Test
    public void testMapper() {
        log.info(mapper.toString());
    }

    @Test
    public void testCreate() {
        IntStream.rangeClosed(1, 10).forEach(i -> {
            ReplyVO vo = new ReplyVO();

            vo.setBno(bnoArr[i % 5]);
            vo.setReply("댓글 테스트 " + i);
            vo.setReplyer("replyer " + i);

            mapper.insert(vo);
        });
    }

    @Test
    public void testRead() {
        Long targetRno = 5L;
        ReplyVO vo = mapper.read(targetRno);
        log.info(String.valueOf(vo));
    }

    @Test
    public void testDelete() {
        Long targetRno = 1L;

        mapper.delete(targetRno);
    }

    @Test
    public void testUpdate() {
        Long targetRno = 10L;
        ReplyVO vo = mapper.read(targetRno);

        vo.setReply("Update Reply ");

        int count = mapper.update(vo);

        log.info("UPDATE COUNT: " + count);
    }

    @Test
    public void testList() {
        Criteria cri = new Criteria();

        List<ReplyVO> replies = mapper.getListWithPaging(cri, bnoArr[0]);

        replies.forEach(reply -> log.info(String.valueOf(reply)));
    }

    @Test
    public void testList2() {
        Criteria cri = new Criteria(2, 10);
        List<ReplyVO> replies = mapper.getListWithPaging(cri, 3597L);

        replies.forEach(reply -> log.info(String.valueOf(reply)));
    }
}
