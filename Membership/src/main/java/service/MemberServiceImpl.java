package service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dao.MemberDao;
import dao.MemberDaoImpl;
import dto.Member;

/*
 * 왜 Service, Dao 인터페이스를 만들어서 구현했을까?
 * 
 * - 인터페이스를 상속 받아 구현하면 모든 자식 클래스가 똑같은 기능을 갖게 됨
 * 비슷한 모양으로 생김!!
 * 
 * -> 비슷하게 생기면 대체가 가능해짐!!
 * --> 언제든지 서로 다른 자식 클래스로 대체 가능 => 유지 보수성 증가!
 */


// MemberService를 상속받아 구현한 객체/클래스
// 왜? (위에 설명)
public class MemberServiceImpl implements MemberService {

	// implements Impl 멤버 서비스 구현체
	
	// dao 객체 부모 참조변수 선언
	private MemberDao dao = null;
	
	private String[] gradeArr = {"일반", "골드", "다이아"};
	
	// 기본 생성자
	// - MemberServiceImpl 객체 생성 시
	// MemberDaoImpl 객체도 생성하겠다는 뜻
	// MemberDaoImpl 이 생성되면 파일을 불러오거나 만든다!
	public MemberServiceImpl() throws FileNotFoundException, ClassNotFoundException, IOException {
		dao = new MemberDaoImpl(); // MemberDaoImpl 객체를 생성
		// 호출한 곳에서 예외처리를 해야 하지만 여기서 안 함!!
	}
	
	// 회원추가
	@Override
	public boolean addMember(String name, String phone) throws IOException { // 처음엔 부모와 다르게 생긴 오버라이딩 문구라서 부모와 맞춰야 함
		
		// 1) 회원 목록을 얻어와 휴대폰 번호 중복 검사
		List<Member> memberList =  dao.getMemberList();
		
		for (Member member : memberList) {
			if(phone.equals(member.getPhone())) // member.getPhone 지금 입력 받은 번호 {
			// 1-1) 휴대폰 번호가 같은 경우 == 중복인 경우
			return false;
		}
		
		// 2) 중복이 아닌 경우
		// 입력 받은 정보를 이용해 Member 객체를 생성하고 DAO에 전달하여 파일 저장
		Member member = new Member(name, phone, 0, Member.COMMON); // 처음에 만든 등급 상수
		
		// DAO 메서드 호출 후 결과 반환 받기
		boolean result =  dao.addMember(member); // 문제 없으면 true로 옴
		
		return result;
	}

	
		// DAO에서 조회한 memberList를 그대로 반환
		// 해당 서비스 메서드는 따로 처리할 조건/기능이 없음!
		// 중간에서 전달만 해주는 역할
		@Override
		public List<Member> getMemberList() {
			return dao.getMemberList();
		}
		
	
	
		// 이름 검색
		
		@Override
		public List<Member> selectName(String searchName) { // 서비스에서 미리 선언해야 함 (그다음 임플로)
			
			// DAO를 이용해 회원 전체 목록 조회
			List<Member> memberList = dao.getMemberList();	// Dao에 있는 멤버리스트 필드를 그대로 줌	
			
			// memberList에 저장된 요소(회원) 중
			// 이름이 같은 회원 찾아서 검색결과를 저장할 별도 List에 추가
			// 별도리스트부터 만들어야 함
			List<Member> searchList = new ArrayList<Member>();
			//                   전체 회원 목록에서 한 명씩 꺼내기
			for(Member member : memberList) {
				if(member.getName().equals(searchName)) { // 메서드 체이닝 : 멤버 한 명 이름 꺼내서 받아온 서치네임과 비교해봄
					searchList.add(member); // 비교한 멤버를 별도로 만든 리스트에 저장해라
				}
			}
			
			return searchList; // 검색 결과 반환
		}

		
		// 금액누적
		@Override
		public String updateAmount(Member target, int acc) throws IOException {
			
			// 이전 금액 백업 -> 출력할 문자열 만들 때 사용
			int before = target.getAmount(); 
			
			// 대상 회원의 금액 누적하기
			target.setAmount(before + acc);
			
			// 등급 판별
//			  일반   : 0 ~ 100,000 미만
//			  골드   : 100,000 이상 ~ 1,000,000 미만
//			  다이아 : 1,000,000 이상
			
				// curren에 저장	// 현재 대상이 가지고 있는 금액을
			int currentAmount = target.getAmount();
			int grade = 0; // 판별된 등급을 저장
			
			if(currentAmount < 100000) 		 grade = Member.COMMON;
			else if(currentAmount < 1000000) grade = Member.GOLD;
			else 							 grade = Member.DIAMOND;
 			
			StringBuilder sb = new StringBuilder(); // 문자열을 계속 조립해나갈 예정
			sb.append(target.getName());
			sb.append(" 회원님의 누적 금액\n");
			sb.append(before + " -> " + currentAmount);
			// 이전 회원의 등급과 새로 판별된 등급이 다른 경우
			if (target.getGrade() != grade) {
				String str = String.format("\n * %s * 등급으로 변경 되셨습니다\n", gradeArr[grade]);
				sb.append(str);
				
				// 대상 회원 데이터에 등급을 판별된 등급(grade)으로 변경
				target.setGrade(grade);
			}
			
			// 변경된 데이터를 저장하는 DAO 메서드 호출이 필요함!!
			dao.saveFile();
			
			return sb.toString();
		}

	
		@Override
		public String updateMember(Member target, String editPhon) throws IOException {
			
			// 이전 번호 저장
			String before = target.getPhone();
			
			// 대상의 전화번호를 입력받은 새 번호로 변경
			target.setPhone(editPhon);
			
			// 출력 문자열 만들기
			StringBuilder sb = new StringBuilder();
			
			sb.append(target.getName());
			sb.append("님의 전화번호가 변경 되었습니다\n");
			sb.append(before + "->" + editPhon);
			
			dao.saveFile();
			
			return sb.toString(); // 결과 문자열 반환
		}
	
	
		@Override
		public String deleteMember(Member target) throws IOException {
			
			// 회원 목록을 얻어오기
			List<Member> memberList = dao.getMemberList();
			
			// 회원 목록에서 target 제거하기
			// List.remove(Object obj) 결과 boolean 반환
			// -> List에 저장된 요소 중 obj와 같은 요소 제거
			// --> ***조건 : 요소 객체가 equals()오버라이딩 되어야 있어야 함
			boolean result = memberList.remove(target);
			
//			StringBuilder sb = new StringBuilder();
			
			dao.saveFile(); // 탈퇴 데이터 세이브
			
			return target.getName() + "회원이 탈퇴처리 되었습니다";
		}
	
}
