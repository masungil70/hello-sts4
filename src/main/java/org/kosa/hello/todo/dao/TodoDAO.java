package org.kosa.hello.todo.dao;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.kosa.hello.page.PageRequestDTO;
import org.kosa.hello.todo.vo.TodoVO;
import org.springframework.stereotype.Repository;

@Mapper
public interface TodoDAO {
	
	List<TodoVO> getList(PageRequestDTO pageRequestDTO);
	int getTotalCount(PageRequestDTO pageRequestDTO);
	
	Optional<TodoVO> getRead(long id);
	int remove(long id);
	int modify(TodoVO todo);
	int insert(TodoVO todo);
}
