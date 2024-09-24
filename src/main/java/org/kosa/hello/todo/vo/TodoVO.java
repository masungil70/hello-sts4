package org.kosa.hello.todo.vo;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoVO {
	private long id;
	private String uid;
	private String title;
	private LocalDate dueDate;
	private boolean finished;
	
	public TodoVO(String title) {
		super();
		this.id = 0;
		this.title = title;
		this.dueDate = LocalDate.now();///new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		this.finished = false;
	}
	
	public String getFinishedStr() {
		return this.finished ? "완료" : "진행중";
	}
	
	
}
