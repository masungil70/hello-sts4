package org.kosa.hello.member.dao;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.kosa.hello.member.vo.MemberVO;
import org.kosa.hello.page.PageRequestDTO;

@Mapper
public interface MemberDAO {
	public List<MemberVO> getList(PageRequestDTO pageRequestDTO);
	public int getTotalCount(PageRequestDTO pageRequestDTO);
	public Optional<MemberVO> getRead(String uid);
	public Optional<MemberVO> getRead_uuid(String uuid);
	public int remove(String uid);
	public int  modify(MemberVO member);
	public int  modify_uuid(MemberVO member);
	public int  insert(MemberVO member);
}
