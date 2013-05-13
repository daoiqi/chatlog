package datasource;

import static org.junit.Assert.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class QQPCChatLogTest {
	QQPCChatLog test =new QQPCChatLog();

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		//fail("Not yet implemented");
	}
	
	//@Test
	public void testParse(){
		String s = "2012/12/12 12:24:12 米图";
		String s1 = "你好";
		String s2= "";
		String ss[] = {s,s1,s2,s2,s};
		assertTrue(test.isTimeAndNickName(s));
		
//		assertEquals(QQPCChatLog.DontKnow, test.parser(ss[0]));
//		assertEquals(QQPCChatLog.TimeAndNickname, test.nowstatus);
//		assertEquals(QQPCChatLog.TimeAndNickname,test.parser(ss[1]));
//		assertEquals(QQPCChatLog.Content, test.nowstatus);
//		assertEquals(QQPCChatLog.Content,test.parser(ss[2]));
//		assertEquals(QQPCChatLog.DontKnow, test.nowstatus);
//		assertEquals(QQPCChatLog.DontKnow,test.parser(ss[3]));
//		assertEquals(QQPCChatLog.DontKnow,test.nowstatus);
//		assertEquals(QQPCChatLog.DontKnow,test.parser(ss[4]));
//		assertEquals(QQPCChatLog.TimeAndNickname,test.nowstatus);
//		assertEquals(QQPCChatLog.TimeAndNickname,test.parser(ss[4]));	
		}
	
	@Test
	public void testReg(){
		String reg = "\\d{4}/\\d{1,2}/\\d{1,2}";
		String s = "2012/12/12";
		Pattern compile = Pattern.compile(reg);
		Matcher matcher = compile.matcher(s);
		System.out.println(matcher.matches());
	}

}
