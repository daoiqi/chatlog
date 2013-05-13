package datasource;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import main.ChatEntity;
import main.Service;

/**
 * 数据源，各种形式，有xml、txt、手机QQ、短信
 * 
 * @author d
 * 
 */
public abstract class DataSource {
	private Path filePath;
	BufferedReader bufferedReader;
	long lineNumber;

	public void setFilePath(Path filepath) {
		this.filePath = filepath;
		try {
			bufferedReader = Files.newBufferedReader(filePath, Service.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 对数据转换，将数据源转成ChatEntity中间代码
	 * 
	 * @return ChatEntity
	 */
	public abstract List<ChatEntity> getData();

	/**
	 * 读取一行
	 * @return
	 */
	public String readLine() {
		try {
			lineNumber++;
			return bufferedReader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("error in line : " + lineNumber);
		}
		
		return null;
	}

	public void print() {
		try {
			List<String> readAllLines = Files.readAllLines(filePath,
					main.Service.UTF_8);
//			for (String string : readAllLines) {
//				System.out.println(string);
//			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
