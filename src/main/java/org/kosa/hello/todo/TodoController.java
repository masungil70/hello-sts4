package org.kosa.hello.todo;

import org.kosa.hello.page.PageRequestDTO;
import org.kosa.hello.page.PageResponseDTO;
import org.kosa.hello.todo.dto.TodoDTO;
import org.kosa.hello.todo.service.TodoService;
import org.kosa.hello.todo.vo.TodoVO;
import org.kosa.hello.util.MapperUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class TodoController {
	//private static ModelMapper mapper = MapperUtil.INSTANCE.getMapper();
	private final MapperUtil mapperUtil;
	private final TodoService todoService;

	@RequestMapping("/todo/list")
	public String list(@Valid PageRequestDTO pageRequestDTO, BindingResult bindingResult, Model model) {
		log.info("todo/list() ..... " + pageRequestDTO.toString());
		if (bindingResult.hasErrors()) {
			pageRequestDTO = PageRequestDTO.builder().build();
		}
		PageResponseDTO<TodoDTO> pageResponseDTO = todoService.getList(pageRequestDTO);
		log.info("pageResponseDTO -> " + pageResponseDTO.toString());
		model.addAttribute("pageResponseDTO", pageResponseDTO);
		model.addAttribute("pageRequestDTO", pageRequestDTO);
//		int total = todoService.getTotalCount();
//		model.addAttribute("list", todoService.getList(pageRequestDTO));
//		model.addAttribute("pageResponseDTO", new PageResponseDTO(pageRequestDTO, total));  
		
		return "/todo/list";
	}
	
	@RequestMapping(value="/todo/insert", method = RequestMethod.GET)
	public String insertGet() {
		log.info("todo/insert() ..... ");
		
		return "/todo/insert";
	}

	@RequestMapping(value="/todo/insert", method = RequestMethod.POST)
//	public String insert(String title, String dueDate) {
	public String insert(@Valid TodoDTO todo, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		log.info("todo/insert 처리할 것 () ..... ");
		log.info("title : " + todo.getTitle());
		log.info("dueDate : " + todo.getDueDate());
		
		//입력값 유효성 검증 
		todo.setUid("aaa");
		if (bindingResult.hasErrors()) {
			for (ObjectError err : bindingResult.getAllErrors()) {
				log.error("유효성 검증 오류 : " + String.join(", ", err.getCodes()) + " = " + err.getDefaultMessage());
			}
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			return "redirect:/todo/insert";
		}
		
		
		todoService.insert(mapperUtil.map(todo, TodoVO.class));
		
		return "redirect:/todo/list";
	}
	
	@RequestMapping(value="/todo/read", method = RequestMethod.GET)
	public String read(long id, PageRequestDTO pageRequestDTO, Model model) {
		
		model.addAttribute("todo", todoService.getRead(id));
		
		return "/todo/read";
	}
	
	@RequestMapping(value="/todo/remove", method = RequestMethod.GET)
	public String remove(long id, PageRequestDTO pageRequestDTO) {
		
		todoService.remove(id);
		
		return "redirect:/todo/list?" + pageRequestDTO.getLink();
	}
	
	@RequestMapping(value="/todo/modify", method = RequestMethod.GET)
	public String modifyForm(long id, PageRequestDTO pageRequestDTO, Model model) {
		
		model.addAttribute("todo", todoService.getRead(id));
		
		return "/todo/modify";
	}
	
	@RequestMapping(value="/todo/modify", method = RequestMethod.POST)
	public String modify(TodoDTO todo, PageRequestDTO pageRequestDTO) {
		
		todoService.modify(mapperUtil.map(todo, TodoVO.class));
		
		return "redirect:/todo/read?id=" + todo.getId() + "&" + pageRequestDTO.getLink();
	}
}

/*

Field error in object 'todoDTO' on field 'dueDate': rejected value [2024-09-10]; codes [Future.todoDTO.dueDate,Future.dueDate,Future.java.time.LocalDate,Future]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [todoDTO.dueDate,dueDate]; arguments []; default message [dueDate]]; default message [미래 날짜여야 합니다] - 미래 날짜여야 합니다

*/