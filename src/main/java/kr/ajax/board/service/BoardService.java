package kr.ajax.board.service;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.ajax.board.dao.BoardDAO;
import kr.ajax.board.dto.BoardDTO;
import kr.ajax.board.dto.PhotoDTO;

@Service
public class BoardService {
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired BoardDAO boardDAO;
	public String root_path="C:/upload/";

	public List<BoardDTO> list() {		
		return boardDAO.list();
	}
//	
//	public void delete(String idx) {
//		int row=boardDAO.delete(idx);
//		if(row>0) {
//			logger.info("삭제 idx = "+idx);
//		}
//	}
	public int del(List<String> delList) {		
		int cnt=0;
		for(String idx : delList) {
			// 1. 게시글에 연결된 파일명 확보
			List<String>files=boardDAO.getFiles(idx);
			// 2. bbs에서 해당 글 삭제
			cnt += boardDAO.del(idx); 			
			// 3. 파일 삭제
			logger.info("files : {}",files);
			delFile(files);
		}
		
		
		return cnt;
	}
	private void delFile(List<String> files) {
		for(String name:files) {
			File file = new File(root_path+name);
			if(file.exists()) {
				file.delete();
			}
		}
		
	}
	
}
