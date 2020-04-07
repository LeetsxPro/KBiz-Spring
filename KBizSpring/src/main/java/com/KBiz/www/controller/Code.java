package com.KBiz.www.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.KBiz.www.dao.CodeDAO;
import com.KBiz.www.vo.CodeVO;
import com.google.gson.Gson;

@Controller
@RequestMapping("/code/")
public class Code {

	@Autowired
	CodeDAO cDAO;
	
	// 코드 리스트 출력
	@RequestMapping("codeList")
	public ModelAndView codeList(ModelAndView mv, CodeVO cVO) {
		
		List<CodeVO> list = cDAO.codeList(cVO);
		mv.addObject("LIST", list);
		
		mv.setViewName("code/codeList");
		return mv;
	}
	
	// 코드 내용 출력
	@RequestMapping("codeInfo")
	public @ResponseBody CodeVO codeInfo(CodeVO cVO) {
		
		cVO = cDAO.codeInfo(cVO);
	
		return cVO;
	}
	
	
	// 코드 추가 실행
	@RequestMapping("codeAdd")
	public ModelAndView codeAdd(ModelAndView mv, RedirectView rv, CodeVO cVO) {
	
		cDAO.codeAdd(cVO);
	
		mv.setViewName("code/codeRV"); return mv;
	}
	
	
	// 코드 수정 실행
	@RequestMapping("codeEdit")
	public ModelAndView codeEdit(ModelAndView mv, RedirectView rv, CodeVO cVO) {
	
		cDAO.codeEdit(cVO);
	
		mv.setViewName("code/codeRV"); return mv;
	}
	
	// 아이템 리스트 페이지 출력 , 1차 카테고리 리스트 출력
	@RequestMapping("itemList")
	public ModelAndView itemList(ModelAndView mv, CodeVO cVO) {
		
		List<CodeVO> list = cDAO.categoryList(cVO);
		mv.addObject("codeList", list);
		
		mv.setViewName("code/itemList");
		return mv;
	}
	
	// 1차 카테고리 선택시 자동으로 1차 분류 리스트 출력 실행
	@RequestMapping("itemListProc")
	public void itemListProc(CodeVO cVO, HttpServletResponse response) throws IOException {
		System.out.println(cVO.getCdno());
		
		List<CodeVO> list = cDAO.itemListProc(cVO);
		String gson = new Gson().toJson(list); 
		PrintWriter pw = response.getWriter();
		pw.write(gson);
		pw.flush();
		pw.close();
	}
	
	// 조회 클릭시 실행
	@RequestMapping("itemInfoList")
	public ModelAndView itemInfoList(ModelAndView mv, CodeVO cVO) {
		
		List<CodeVO> list = cDAO.itemInfoList(cVO);
		
		mv.addObject("itemList", list);
		mv.setViewName("code/itemList");
		return mv;
	}
}