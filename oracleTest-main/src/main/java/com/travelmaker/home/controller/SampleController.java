package com.travelmaker.home.controller;

import com.travelmaker.home.domain.SampleVO;
import com.travelmaker.home.domain.Ticket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/sample")
@Slf4j
public class SampleController {
    @GetMapping(value = "/getText", produces = "text/plain; charset=UTF-8")
    public String getText() {
        log.info("/getText.......");
        log.info("MIME TYPE: " + MediaType.TEXT_PLAIN_VALUE);

        return "안녕하세요";
    }

    @GetMapping(value = "/getSample",
            produces = {MediaType.APPLICATION_JSON_VALUE,
                        MediaType.APPLICATION_XML_VALUE})
    public SampleVO getSample() {
        log.info("/getSample.......");

        return new SampleVO(112, "스타", "로드");
    }

    @GetMapping(value = "/getSample2")
    public SampleVO getSample2() {
        log.info("/getSample2.......");

        return new SampleVO(113, "로켓", "라쿤");
    }

    @GetMapping(value = "/getList")
    public List<SampleVO> getList() {
        log.info("/getList.......");

        return IntStream.range(1, 10).mapToObj(i -> new SampleVO(i, i + "first", i + " Last"))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/getMap")
    public Map<String, SampleVO> getMap() {
        log.info("/getMap.......");

        Map<String, SampleVO> map = new HashMap<>();
        map.put("First", new SampleVO(111, "그루트", "주니어"));

        return map;
    }

    @GetMapping(value = "/check", params = {"height", "weight"})
    public ResponseEntity<SampleVO> check(Double height, Double weight) {
        SampleVO vo = new SampleVO(0, "" + height, "" + weight);

        ResponseEntity<SampleVO> result = null;

        if(height < 150) {
            result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);
        } else {
            result = ResponseEntity.status(HttpStatus.OK).body(vo);
        }

        return result;
    }

    @GetMapping("/product/{cat}/{pid}")
    public String[] getPath(@PathVariable("cat") String cat, @PathVariable("pid") Integer pid) {
        log.info("/product/cat/pid.......");

        return new String[] {"category: " + cat, "productid: " + pid};
    }

    @PostMapping("/ticket")
    public Ticket convert(@RequestBody Ticket ticket) {
        log.info("convert.......ticket" + ticket);

        return ticket;
    }
}
