package janken;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JankenJUnit {
	
	static int testCount;
	
	@BeforeEach
	void beforeEach(){
		testCount++;
		System.out.println("テスト開始：" + testCount + "回目");
	}
	
	@AfterEach
	void afterEach() {
		System.out.println("テスト終了");
	}

	@Test
	void test() {
		JankenGroup jankenGroup = new JankenGroup();
		assertEquals("グー",JankenGroup.choice(1));
		assertEquals("チョキ",JankenGroup.choice(2));
		assertEquals("パー",JankenGroup.choice(3));
		
	}

}
