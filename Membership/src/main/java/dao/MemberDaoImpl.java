package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import dto.Member;

// MemberDao 인터페이스 상속 받아 구현
public class MemberDaoImpl implements MemberDao {
	
	// implements Impl 멤버 서비스 구현체
	
	
	// 회원 데이터가 저장될 파일경로를 상수로 지정 (변경 못함!)
	// 상수는 전부 대문자 + _ 로 이뤄짐
	private final String FILE_PATH = "/io_test/membership.dat";
	
	// dto Member 로 타입 지정
	// 회원목록을 저장해둘 List 객체
	private List<Member> memberList = null;
	
	// 스트림 객체 참조 변수
	private ObjectInputStream ois = null;
	private ObjectOutputStream oos = null;
	
	
	// 기본 생성자
	// 기본 생성자를 왜 어렵게 만들었을까?
	// - 회원 다수를 관리할 회원목록인 List가 필요함!!
	// 이미 파일로 저장된 회원목록이 있으면 읽어오고,
	// 없으면 새로 만들기를 하겠다는 뜻!!
	public MemberDaoImpl() throws FileNotFoundException, IOException, ClassNotFoundException {
		// membership.dat 파일이 존재하는지 검사
		File file = new File(FILE_PATH);
		
		if(file.exists()) { // 존재하는 경우
			try {
				ois = new ObjectInputStream(new FileInputStream(FILE_PATH)); // 스트림 생성
				// 기본 스트림은 생성만 되면 되기 때문에, 보조 스트림 생성할 때 매개변수에 넣어버림
				
				// 저장된 객체를 파일에서 읽어와서 다운캐스팅하여 memberList가 참조하게 함
				memberList = (ArrayList<Member>)ois.readObject(); // Member로 다운캐스팅, 정확하게 ArrayList (import필요)
			} finally {
				// try에서 발생하는 예외를 throws 구문으로 처리하면
				// catch() 구문을 작성하지 않아도 된다 !!
				
				// 기본 생성자를 호출하는 곳으로 예외를 던지겠다 throws
				
				if (ois != null) ois.close();
			}
		} 
		// 파일이 존재하지 않는 경우
		else	memberList = new ArrayList<Member>(); // 새로운 ArrayList를 만들어서 참조하게 만들어라

	}
	
	
	// memberList 반환
	@Override
	public List<Member> getMemberList() {
		return memberList;
	}
	
	
	// 회원 추가
	@Override
	public boolean addMember(Member member) throws IOException {
		
		// 1) 매개 변수로 전달 받은 새 회원 정보를 memberList에 추가
		memberList.add(member);
		
		// 2) memberList를 지정된 파일로 출력(저장)
		// 앞으로 저장 기능을 계속 써야 함
		// -> 현재 메서드 말고 다른 메서드에서도 파일 출력(저장) 기능이 자주 사용될 예정
		// --> saveFile() 메서드 만들어 호출
		saveFile();
		
		return true; // 예외 발생하지 않고 성공적으로 파일 저장 (추가) 됨
	}
	
	
	// saveFile() 오버라이딩 (인터페이스에 있음)
	@Override
	public void saveFile() throws IOException {
		// memberList를 지정된 파일에 출력(저장) 
		// memberList라는 리스트타입의 객체 
		
		try {
		oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH));
		oos.writeObject(memberList);
		} finally {
			if (oos != null) oos.close(); // flush() + 메모리 반환
		}
	}

}
