package com.travelmaker.board.mapper;

import com.travelmaker.board.domain.BoardVO;
import com.travelmaker.board.domain.Criteria;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class BoardMapperTests {

    @Setter(onMethod_ = @Autowired)
    private BoardMapper mapper;

    @Test
    public void testGetList() {
        mapper.getList().forEach(board -> log.info(String.valueOf(board)));
    }

    @Test
    public void testInsert() {
        BoardVO board = new BoardVO();

        board.setManagement_id(333);
        board.setTitle("테스트 글");
        board.setCode_id("test_code_id");
        board.setNickname("test_nickname");
        board.setBcontent("test_bcontent");
        board.setStatus('N');
        board.setHit(333);
        board.setPlan_id(333);

        mapper.insert(board);

        log.info(String.valueOf(board));
    }

    @Test
    public void testRead() {
        BoardVO board = mapper.read(4);

        log.info(String.valueOf(board));
    }

    @Test
    public void testDelete() {
        int result = mapper.delete(4);

        log.info("-------Delete Count: " + String.valueOf(result));
    }

    @Test
    public void testUpdate() {
        BoardVO board = mapper.read(4);

        board.setTitle("타이틀 수정");
        board.setCode_id("F3333");
        board.setNickname("닉네임 수정");
        board.setBcontent("내용 수정");
        board.setStatus('T');

        int result = mapper.update(board);

        log.info("-------Update Count: " + String.valueOf(result));
    }

    @Test
    public void testPaging() {
        Criteria cri = new Criteria();
        cri.setPageNum(3);
        cri.setAmount(10);

        List<BoardVO> list = mapper.getListWithPaging(cri);

        list.forEach(board -> log.info(String.valueOf(board)));
    }

    @Test
    public void testSearch() {
        Criteria cri = new Criteria();
        cri.setKeyword("노랭");
        cri.setType("TC");

        List<BoardVO> list = mapper.getListWithPaging(cri);

        list.forEach(board -> log.info(String.valueOf(board)));
    }
}
