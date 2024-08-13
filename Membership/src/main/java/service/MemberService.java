package service;

import java.io.IOException;
import java.util.List;

import dto.Member;

// Service : 기능(비즈니스 로직) 제공 클래스/ 객체
// - 프로그램 핵심 기능 작성
public interface MemberService {

	// 인터페이스에 작성하는 모든 메서드는 추상 메서드
	//인터페이스의 메서드
	// - public abstract
	// - default method
	
	// 회원을 추가 했을 때 (추가/실패 라는 두 가지 결과가 있음) 반환형을 생각했을 때 가장 적합한 boolean
	/**
	 * 전달받은 이름, 휴대폰 번호를 이용해서 회원 추가
	 * 단, 목록에 있는 회원 중 같은 번호의 회원이 존재하면 false 반환
	 * 없으면 가입 후 true 반환
	 * @param name
	 * @param phone
	 * @return true / false(==중복된 번호)
	 */
	public abstract boolean addMember(String name, String phone) throws IOException;
	
	
	/**
	 * 전체 회원 목록 조회
	 * @return memberList
	 */
	public abstract List<Member> getMemberList(); 
	
	
	/**
	 * searchName 같은 이름 지닌 회원 조회
	 * - 동명이인 존재하면 모두 조회
	 * @param searchName
	 * @return searchList (저장된 요소 0개 이상)
	 */
	List<Member> selectName(String searchName);
	
	
	/**
	 * 전달 받은 회원의 금액 누적하기
	 * @param target
	 * @param acc
	 * @return 결과 문자열
	 * @throws IOException
	 */
	String updateAmount(Member target, int acc) throws IOException;


	/**
	 * 전달 받은 회원의 전화번호 변경하기
	 * @param target
	 * @param editPhon
	 * @return 결과 문자열
	 * @throws IOException
	 */
	
	public abstract String updateMember(Member target, String editPhon) throws IOException;


	/**
	 * 회원 탈퇴
	 * @param target
	 * @return 결과 문자열
	 * @throws IOException
	 */
	public abstract String deleteMember(Member target) throws IOException;
	
	

	
}