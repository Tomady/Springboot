package com.travelmaker.board.controller;

import com.travelmaker.board.domain.BoardVO;
import com.travelmaker.board.domain.Criteria;
import com.travelmaker.board.service.BoardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {
    private BoardService service;

//    @GetMapping("/list")
//    public String list(Model model) {
//        log.info("/list");
//        model.addAttribute("list", service.getList());
//
//        return "board/list";
//    }

    @GetMapping("/list")
    public void list(Criteria cri, Model model) {
        log.info("/list: " + cri);

        model.addAttribute("list", service.getList(cri));
    }

    @GetMapping("/register")
    public void register() {
        log.info("/getRegister: ");
    }

    @PostMapping("/register")
    public String register(BoardVO board, RedirectAttributes rttr) {
        log.info("/register: " + board);

        service.register(board);
        rttr.addFlashAttribute("result", board.getBbs_id());

        return "redirect:/board/list";
    }

    @GetMapping({"/get", "/modify"})
    public void get(@RequestParam("bbs_id") int bbs_id, Model model) {
        log.info("/get or /modify");
        model.addAttribute("board", service.get(bbs_id));
    }

    @PostMapping("/modify")
    public String modify(BoardVO board, RedirectAttributes rttr) {
        log.info("modify: " + board);

        log.info("-------------------modify: " + String.valueOf(board));

        if(service.modify(board)) {
            rttr.addFlashAttribute("result", "success");
        }

        return "redirect:/board/list";
    }

    @PostMapping("/remove")
    public String remove(@RequestParam("bbs_id") int bbs_id, RedirectAttributes rttr) {
        log.info("/remove" + bbs_id);

        if(service.remove(bbs_id)) {
            rttr.addFlashAttribute("result", "success");
        }

        return "redirect:/board/list";
    }
}
