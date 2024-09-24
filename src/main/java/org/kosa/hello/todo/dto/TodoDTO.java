package org.kosa.hello.todo.dto;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoDTO {
	private long id;
	
	@NotBlank
	private String uid;
	
	@NotBlank
	@Size(min = 2)
	private String title;
	
	@Future
	private LocalDate dueDate;
	
	private boolean finished;
	
	public TodoDTO(String title) {
		super();
		this.id = 0;
		this.title = title;
		this.dueDate = LocalDate.now();//new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		this.finished = false;
	}
	
	public String getFinishedStr() {
		return this.finished ? "완료" : "진행중";
	}
	
}
