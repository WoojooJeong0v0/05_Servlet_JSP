package dto;

import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter // 컴파일 시 getter 구문 자동 추가하는 어노테이션
@Setter // setter 추가
@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // 매개변수 생성자 (오버로딩) 전체필드 매개변수 생성자
@ToString // toString 오버라이딩
@EqualsAndHashCode // equals(), hashcode() 오버라이딩
public class Member implements Serializable { // 직렬화를 해야 함!!

	
	// DTO Data Transfer Object : 데이터 전달용 객체
	// - 여러 데이터를 한 번에 담아, [계층간 데이터]를 전달하는 목적
	// 계층 : view, service, dao 등 다른 클래스
	
	
	// 바뀌지 않는 (상수) 등급, 퍼블릭으로 사용 
	// Member.GOLD 등으로 사용
	public static final int COMMON = 0;
	public static final int GOLD = 1;
	public static final int DIAMOND = 2;
	
	// 회원 정보를 저장할 필드
	private String name;
	private String phone;
	private int amount;
	private int grade;
	
	
}
