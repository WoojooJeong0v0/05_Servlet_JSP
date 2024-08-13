package dao;

import java.io.IOException;
import java.util.List;

import dto.Member;

// DAO Data Access Object  : 데이터가 저장된 곳(파일/DB)에 접근하는 역할의 객체/클래스
// 서비스가 요청한 데이터를 가지고 오거나 집어넣거나 하는 역할
// - 데이터 저장 / 수정 / 삭제 / 조회 가능
public interface MemberDao {

	// 인터페이스 메서드는 무조건 public abstract
	// 묵시적(암무적)으로 public abstract 이다
	/**
	 * DAO 객체가 가지고 있는 
	 * @return memberList 반환
	 */
	List<Member> getMemberList();

	/**
	 * 회원 추가
	 * @param member
	 * @return 성공 시 true, 실패 시 false
	 * @throws IOException
	 */
	boolean addMember(Member member) throws IOException;
	
	
	/**
	 * 파일 저장하는 메서드
	 * @throws IOException
	 */
	void saveFile() throws IOException;
	
}