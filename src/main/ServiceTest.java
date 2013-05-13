package main;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import datasource.DataSource;
import datasource.QQPCChatLog;

public class ServiceTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		
		DataSource source = new QQPCChatLog();
		System.out.println(Paths.get("Test.txt"));
		source.setFilePath(Paths.get("U729512441.txt"));
//		source.print();
		List<ChatEntity> datas = source.getData();
		for (ChatEntity chatEntity : datas) {
			System.out.println(chatEntity);
		}
	}

}
