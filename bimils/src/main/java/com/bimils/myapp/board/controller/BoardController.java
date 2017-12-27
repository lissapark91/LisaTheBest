package com.bimils.myapp.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/board")
public class BoardController {
	
	@RequestMapping("/boardList")
	public String getBoardList() {
		return "board/boardList";
	}

}
