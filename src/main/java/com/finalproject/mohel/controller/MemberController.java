package com.finalproject.mohel.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.finalproject.mohel.MohelApplication;
import com.finalproject.mohel.service.Certified;
import com.finalproject.mohel.service.KakaoAPI;
import com.finalproject.mohel.service.MemberService;
import com.finalproject.mohel.vo.MemberVO;

@Controller
@RequestMapping("/member/")
public class MemberController {
	
	@Inject
	MemberService service;
	@Inject
	Certified certified;
	@Inject
	KakaoAPI kakao;
	@Inject
	JavaMailSender javaMailSender;

	// 회원가입
	@GetMapping("signup")
	public String signup(Model model, HttpSession session) {
		Object obj = model.getAttribute("kakaoVO");
		if(obj!=null) {
			session.setAttribute("kakaoVO", obj);
		}else {
			session.invalidate();
		}
		
		return "/member/signup";
	}
	
	@PostMapping("signupOk")
	public String signupOk(MemberVO vo, HttpServletRequest request) {
		MohelApplication.profileImgUpload(vo, request);
		service.insertMember(vo);
		
		return "redirect:login";
	}
	
	@PostMapping("searchEmail")
	@ResponseBody
	public int searchEmail(String email) {
		return service.searchEmail(email);
	}
	
	@PostMapping("checkMail")
	@ResponseBody
	public String checkMail(String email) {
		return certified.checkMail(email, javaMailSender);
	}
	
	@GetMapping("searchNickname")
	@ResponseBody
	public int searchNickname(String nickname) {
		return service.searchNickname(nickname);
	}
	
	@PostMapping("sendSMS")
	@ResponseBody
	public String sendSMS(String tel) {
		return certified.sendSMS(tel);
	}
	
	
	// 로그인
	@GetMapping("login")
	public String login() {
		return "/member/login";
	}
	
	@PostMapping("loginOk")
	public String loginOk(HttpSession session, MemberVO vo, RedirectAttributes redirect) {
		MemberVO userInfo = service.selectMember(vo);
		if(userInfo!=null) {
			session.setAttribute("userInfo", userInfo);
			session.setAttribute("verify", userInfo.getVerify());
			return "redirect:/";
		}else {
			redirect.addFlashAttribute("email", vo.getEmail());
			redirect.addFlashAttribute("nologin", true);
			return "redirect:login";
		}
	}
	
	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("kakaologin")
	public String kakaologin(String code, HttpSession session, RedirectAttributes redirect) {
		MemberVO kakaoVO = new MemberVO(kakao.getUserInfo(kakao.getAccessToken(kakao.getRefreshToken(code))));
		MemberVO userInfo = service.selectMember(kakaoVO);
		
		if(userInfo!=null) {
			session.setAttribute("userInfo", userInfo);
			session.setAttribute("kakao", true);
			return "redirect:/";
		}else {
			redirect.addFlashAttribute("kakaoVO", kakaoVO);
			return "redirect:signup";
		}
	}
	
	// 비밀번호 찾기
	@GetMapping("findPwd")
	public String findPwd() {
		return "/member/findPwd";
	}
	
	// 비밀번호 재설정
	@GetMapping("resetPwd")
	public String resetPwd() {
		return "/member/resetPwd";
	}
}