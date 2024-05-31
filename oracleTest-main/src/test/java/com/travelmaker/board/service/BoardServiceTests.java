package com.travelmaker.board.service;

import com.travelmaker.board.domain.BoardVO;
import com.travelmaker.board.domain.Criteria;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
public class BoardServiceTests {

    @Setter(onMethod_ = @Autowired)
    private BoardService service;

    @Test
    public void testExist() {
        log.info(service.toString());
        assertNotNull(service);
    }

    @Test
    public void testRegister() {
        BoardVO board = new BoardVO();

        board.setTitle("테스트 글");
        board.setWriter("test_writer");
        board.setContent("test_content");

        service.register(board);

        log.info("생성된 게시물의 번호: " + board.getBno());
    }

    @Test
    public void testGetList() {
//        service.getList().forEach(board -> log.info(String.valueOf(board)));
        service.getList(new Criteria(2, 10)).forEach(board -> log.info(String.valueOf(board)));
    }

    @Test
    public void testGet() {
        BoardVO board = service.get(4);
        log.info(String.valueOf(board));
    }

    @Test
    public void testDelete() {
        log.info("Remove result: " + service.remove(4));
    }

    @Test
    public void testUpdate() {
        BoardVO board = service.get(4);

        if(board == null) {
            return;
        }

        board.setTitle("제목 수정");
        log.info("Modify result: " + service.modify(board));
    }
}
