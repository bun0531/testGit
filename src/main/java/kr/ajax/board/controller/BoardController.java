package kr.ajax.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ajax.board.dto.BoardDTO;
import kr.ajax.board.service.BoardService;

@Controller
public class BoardController {
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired BoardService boardService;
	
	// 동기 방식
	@RequestMapping(value="/") // 요청이 오면
	public String list(Model model) {
		logger.info("list요청");
		List<BoardDTO> list= boardService.list();
		
		// model.addAttribute("list",list); // 모델에 데이터를 받아서
		return "list";					 // 특정한 페이지(list.jsp로 보낸다.)
	}
	
	// 비동기 방식 : 일단 페이지에 도착한 다음
	@RequestMapping(value="/list.ajax")
	@ResponseBody // response 객체로 반환하는데. . .
	public Map<String, Object>listCall(String id){
		Map<String, Object> map = new HashMap<String, Object>();
		List<BoardDTO> list = boardService.list();
		
		map.put("list", list);
		
		return map; // json과 가장 닮은 map으로 
	}
	// 배열 형태로 들어올 경우, 따로 명시를 해줘야 한다.
	@RequestMapping(value="/del", method = RequestMethod.POST)
	public Map<String, Object>del(@RequestParam(value="delList[]") List<String> delList,String idx){
		logger.info("del list : {}",delList);
		
		int cnt= boardService.del(delList);
		logger.info("del count : "+cnt);		
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("cnt", cnt);
		
		return map;
	}
	
	
	
}
