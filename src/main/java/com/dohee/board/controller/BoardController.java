package com.dohee.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dohee.board.dto.Board;
import com.dohee.board.service.BoardService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;


@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;
    
    @GetMapping("/list")
    public String list(Model model) throws Exception {
        List<Board> boardList = boardService.list();

        model.addAttribute("boardList", boardList);

        return "/board/list";
    }

    // 게시글 조회
    @GetMapping("/read")
    public String read(@RequestParam("no") int no, Model model) throws Exception {
        Board board = boardService.select(no);

        model.addAttribute("board", board);

        return "/board/read";
    }
    
    // 게시글 등록 화면
    @GetMapping("/insert")
    public String insert() {
        return "/board/insert";
    }
    
    // 게시글 등록 처리
    @PostMapping("/insert")
    public String insertPro(Board board) throws Exception {

        int result = boardService.insert(board);
        
        if (result == 0) {
            return "/board/insert";
        }

        return "redirect:/board/list";
    }
    
    // 게시글 수정 화면
    @GetMapping("/update")
    public String update(@RequestParam("no")int no, Model model) throws Exception {
        Board board = boardService.select(no);

        model.addAttribute("board", board);

        return "/board/update";
    }

    // 게시글 수정 처리
    @PostMapping("/update")
    public String updatePro(Board board) throws Exception {

        int result = boardService.update(board);
        
        if (result == 0) {
            return "/board/update";
        }

        return "redirect:/board/list";
    }

    // 게시글 삭제 처리
    @PostMapping("/delete")
    public String delete(@RequestParam("no")int no) throws Exception {

        int result = boardService.delete(no);
        
        if (result == 0) {
            return "/board/update";
        }

        return "redirect:/board/list";
    }
    
    


    
    
    
    
}
