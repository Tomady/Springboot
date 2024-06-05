package com.travelmaker.reply.controller;

import com.travelmaker.board.domain.Criteria;
import com.travelmaker.reply.domain.ReplyPageDTO;
import com.travelmaker.reply.domain.ReplyVO;
import com.travelmaker.reply.service.ReplyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/replies/")
@RestController
@Slf4j
@AllArgsConstructor
public class ReplyController {
    private ReplyService service;

    @PostMapping(value = "/new", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> create(@RequestBody ReplyVO vo) {
        log.info("reply: /new.......");
        log.info("ReplyVO: " + vo);

        int insertCount = service.register(vo);

        log.info("Reply INSERT COUNT: " + insertCount);

        return insertCount == 1 ?
            new ResponseEntity<>("success", HttpStatus.OK) :
            new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @GetMapping(value = "/pages/{bno}/{page}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @GetMapping(value = "/pages/{bno}/{page}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ReplyPageDTO> getList(@PathVariable("page") String page, @PathVariable("bno") Long bno) {
        log.info("reply: /pages/bno/page: " + "bno: " + bno + ", page: " + page);
        log.info("getList.......");

        // 문자열 .json으로 요청이 들어왔을 경우.
        if(page.indexOf('.') >= 0) {
            page = page.split("\\.")[0];
        }

        Criteria cri = new Criteria(Integer.parseInt(page), 10);

        log.info("get Reply List bno: " + bno);
        log.info(String.valueOf(cri));

        return new ResponseEntity<>(service.getListPage(cri, bno), HttpStatus.OK);
    }

//    @GetMapping(value = "/{rno}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @GetMapping(value = "/{rno}")
    public ResponseEntity<ReplyVO> get(@PathVariable("rno") String rno) {
        log.info("reply get: " + rno);

        if(rno.indexOf('.') >= 0) {
            rno = rno.split("\\.")[0];
        }

        return new ResponseEntity<>(service.get(Long.parseLong(rno)), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{rno}", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> remove(@PathVariable("rno") Long rno) {
        log.info("reply remove: " + rno);

        return service.remove(rno) == 1 ?
            new ResponseEntity<>("success", HttpStatus.OK) :
            new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH},
        value = "/{rno}",
        consumes = "application/json",
        produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> modify(@RequestBody ReplyVO vo, @PathVariable("rno") Long rno) {
        log.info("reply modify: " + vo);
        log.info("rno: " + rno);
        vo.setRno(rno);

        return service.modify(vo) == 1 ?
            new ResponseEntity<>("success", HttpStatus.OK) :
            new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
