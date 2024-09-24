package org.kosa.hello.member.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.kosa.hello.member.dao.MemberDAO;
import org.kosa.hello.member.dto.MemberDTO;
import org.kosa.hello.member.vo.MemberVO;
import org.kosa.hello.page.PageRequestDTO;
import org.kosa.hello.page.PageResponseDTO;
import org.kosa.hello.util.MapperUtil;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	//private static ModelMapper modelMapper = MapperUtil.INSTANCE.getMapper();
	
	private final MapperUtil mapperUtil;
	private final MemberDAO memberDAO;

	//승범이 이부분을 추가함 
	//승범이 이부분을 새로 추가함 
	
	public PageResponseDTO<MemberDTO> getList(PageRequestDTO pageRequestDTO) {
		List<MemberDTO> list = memberDAO.getList(pageRequestDTO).stream().map(member -> mapperUtil.map(member, MemberDTO.class)).collect(Collectors.toList());
		
		return new PageResponseDTO<MemberDTO>(pageRequestDTO, list, memberDAO.getTotalCount(pageRequestDTO));
	}
	
	
	public MemberDTO getRead(String uid) {
		MemberVO member = memberDAO.getRead(uid).orElse(null);
		return member != null ? mapperUtil.map(member, MemberDTO.class) : null; 
	}
	
	public MemberDTO getRead_uuid(String uuid) {
		MemberVO member = memberDAO.getRead_uuid(uuid).orElse(null);
		return member != null ? mapperUtil.map(member, MemberDTO.class) : null; 
	}
	
	public int remove(String uid) {
		return memberDAO.remove(uid);
	}
	
	public int modify(final MemberVO newMember) {
		return memberDAO.modify(newMember);  
	}
	
	public int modify_uuid(final MemberVO newMember) {
		return memberDAO.modify_uuid(newMember);  
	}
	
	public int insert(final MemberVO newMember) {
		return memberDAO.insert(newMember);
	}

	public MemberDTO login(MemberDTO inMember) {
		MemberVO member = memberDAO.getRead(inMember.getUid()).orElse(null);
		if (member != null && member.isEqualPwd(inMember.getPwd())) {
			if (inMember.isAuto_login()) {
				//1. uuid를 생성한다 
				String uuid = UUID.randomUUID().toString();
				
				//2. 회원 테이블에 uuid를 변경한다
				member.setUuid(uuid);
				memberDAO.modify_uuid(member);
				
				//3. 쿠키 값을 추가한다 -> contoller에서 처리한다 
			} else {
				//1. 회원 테이블에 uuid를 제거한다
				member.setUuid("");
				memberDAO.modify_uuid(member);
			}
			
			return mapperUtil.map(member, MemberDTO.class);
		}
		return null;
	}
	
}
