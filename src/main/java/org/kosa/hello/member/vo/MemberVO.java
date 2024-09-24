package org.kosa.hello.member.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberVO {
	private String uid;
	private String pwd;
	private String name;
	private String uuid; 
	
	public boolean isEqualPwd(String pwd) {
		return this.pwd.equals(pwd);
	}

	public MemberVO(String uid, String pwd) {
		this(uid, pwd, "");
	}
	
	public MemberVO(String uid, String pwd, String name) {
		this(uid, pwd, name, "");
	}
	
//	public MemberVO(MemberDTO member) {
//		super();
//		this.uid = member.getUid();
//		this.pwd = member.getPwd();
//		this.name = member.getName();
//	}
	
//	public MemberDTO createMemberDTO() {
//		return new MemberDTO(uid, pwd, name);  
//	}
	
}
