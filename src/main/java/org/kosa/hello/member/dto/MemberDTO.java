package org.kosa.hello.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
	private String uid;
	private String pwd;
	private String name;
	private String uuid;
	private boolean auto_login; 
	
	public MemberDTO(String uid, String pwd, boolean auto_login) {
		this(uid, pwd, "", "", auto_login);
	}

	public MemberDTO(String uid, String pwd, String name) {
		this(uid, pwd, name, "", false);
	}
	
}
