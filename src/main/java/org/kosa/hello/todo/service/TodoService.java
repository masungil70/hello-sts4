package org.kosa.hello.todo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.kosa.hello.page.PageRequestDTO;
import org.kosa.hello.page.PageResponseDTO;
import org.kosa.hello.todo.dao.TodoDAO;
import org.kosa.hello.todo.dto.TodoDTO;
import org.kosa.hello.todo.vo.TodoVO;
import org.kosa.hello.util.MapperUtil;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TodoService {
	private final MapperUtil mapperUtil;
	private final TodoDAO todoDAO;

	public PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO) {
		List<TodoDTO> list = todoDAO.getList(pageRequestDTO).stream().map(todo -> mapperUtil.map(todo, TodoDTO.class)).collect(Collectors.toList());
		
		return new PageResponseDTO<TodoDTO>(pageRequestDTO, list, todoDAO.getTotalCount(pageRequestDTO));
	}
	
	public TodoDTO getRead(long id) {
		TodoVO todo = todoDAO.getRead(id).orElse(null);
		return todo != null ? mapperUtil.map(todo, TodoDTO.class) : null; 
	}

	public int remove(long id) {
		return todoDAO.remove(id);
	}
	
	public int modify(final TodoVO newTodo) {
		return todoDAO.modify(newTodo);  
	}
	
	public int insert(final TodoVO newTodo) {
		return todoDAO.insert(newTodo);
	}

}
