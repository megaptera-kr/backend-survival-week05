package kr.megaptera.assignment;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

// 원인은 테스트 파일이 demo 패키지 하위에 존재하지 않아서 @SpringBootApplication 을 찾지 못하는 것이다.
// @SpringBootTest -> @SpringBootTest(classes = AssignmentApplication.class)
@SpringBootTest(classes = AssignmentApplication.class)
class AssignmentApplicationTests {

	@Test
	void contextLoads() {
	}

}
