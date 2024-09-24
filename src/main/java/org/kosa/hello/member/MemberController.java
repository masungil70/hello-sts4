package org.kosa.hello.member;

import org.kosa.hello.member.dto.MemberDTO;
import org.kosa.hello.member.service.MemberService;
import org.kosa.hello.member.vo.MemberVO;
import org.kosa.hello.page.PageRequestDTO;
import org.kosa.hello.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
	@Autowired
	private MapperUtil mapperUtil;

	@Autowired
	private MemberService memberService;
	
	//@RequestMapping(value = "list", method = RequestMethod.GET)
	@GetMapping("list")
	public String list(@Valid PageRequestDTO pageRequestDTO, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			pageRequestDTO = PageRequestDTO.builder().build();
		}
		model.addAttribute("pageResponseDTO", memberService.getList(pageRequestDTO));
		
		return "/member/list";
	}
	
	@RequestMapping(value="insert", method = RequestMethod.GET)
	public String insertGet() {
		log.info("member/insert() ..... ");
		
		return "/member/insert";
	}

	@RequestMapping(value="insert", method = RequestMethod.POST)
	public String insert(MemberDTO member) {
		memberService.insert(mapperUtil.map(member, MemberVO.class));
		
		return "redirect:list";
	}
	
	@RequestMapping(value="read", method = RequestMethod.GET)
	public String read(String uid, Model model) {
		
		model.addAttribute("member", memberService.getRead(uid));
		
		return "/member/read";
	}
	
	@RequestMapping(value="remove", method = RequestMethod.GET)
	public String remove(String uid) {
		
		memberService.remove(uid);
		
		return "redirect:list";
	}
	
	@RequestMapping(value="modify", method = RequestMethod.GET)
	public String modifyForm(String uid, Model model) {
		
		model.addAttribute("member", memberService.getRead(uid));
		
		return "/member/modify";
	}
	
	@RequestMapping(value="modify", method = RequestMethod.POST)
	public String modify(MemberDTO member) {
		
		memberService.modify(mapperUtil.map(member, MemberVO.class));
		
		return "redirect:read?uid=" + member.getUid();
	}
	
	@RequestMapping(value="login", method = RequestMethod.GET)
	public String loginForm() {
		HttpServletRequest request = ((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest();
		HttpServletResponse response = ((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getResponse();
		
		System.out.println("로그인 화면");
		for (Cookie cookie : request.getCookies()) {
			if (cookie.getName().equals("remember_me")) {
				MemberDTO member = memberService.getRead_uuid(cookie.getValue());
				if (member != null) {
					HttpSession session = request.getSession();
					
					session.setAttribute("loginInfo", member);
					cookie.setMaxAge(60 * 10);
					response.addCookie(cookie);
					
					return "redirect:/todo/list";
				}
			}
		}
		return "/member/login";
	}
	
	@RequestMapping(value="login", method = RequestMethod.POST)
	public String login(MemberDTO inMember, HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		MemberDTO member = memberService.login(inMember);
		if (member != null) { 
			if (inMember.isAuto_login()) {
				//3. 쿠키 값을 추가한다
				Cookie cookie = new Cookie("remember_me", member.getUuid());
				cookie.setMaxAge(60 * 10);//10분만 사용됨
				cookie.setPath("/");
				
				response.addCookie(cookie);
			}
			session.setAttribute("loginInfo", member);
			return "redirect:/todo/list";
		} else {
			return "redirect:/member/login?error=error";
		}
	}
	
	@RequestMapping(value="logout", method = RequestMethod.GET)
	//public String logout(HttpServletRequest request, HttpServletResponse response) {
	public String logout(HttpSession session) {
		
		MemberDTO member = (MemberDTO) session.getAttribute("loginInfo");
		member.setUuid("");
		memberService.modify_uuid(mapperUtil.map(member, MemberVO.class));

		//세션에 저장된 모든 정보를 무효하 한다 
		session.invalidate();
		return "redirect:/member/login";
	}
}
