package com.travelmaker.reply.mapper;

import com.travelmaker.reply.domain.ReplyVO;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
@Slf4j
public class ReplyMapperTests {
    @Setter(onMethod_ = @Autowired)
    private ReplyMapper mapper;

    private Long[] bbs_id_Arr = {1028L, 1027L, 1026L, 1025L, 1024L};

    @Test
    public void testMapper() {
        log.info(mapper.toString());
    }

    @Test
    public void testCreate() {
        IntStream.rangeClosed(1, 10).forEach(i -> {
            ReplyVO vo = new ReplyVO();

            vo.setBbs_id(bbs_id_Arr[i % 5]);
            vo.setManagement_id(1L);
            vo.setBcontent("댓글 테스트 " + i);
            vo.setNickname("replyer " + i);

            mapper.insert(vo);
        });
    }

    @Test
    public void testRead() {
        Long targetRbbs_id = 22L;
        ReplyVO vo = mapper.read(targetRbbs_id);
        log.info(String.valueOf(vo));
    }
}
