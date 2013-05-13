package main;

import java.nio.charset.Charset;
import java.util.TreeSet;

import datasource.DataSource;

public class Service {
	public static final Charset UTF_8 = Charset.forName("UTF-8");
	private DataSource source;
	private TreeSet<ChatEntity> list;
	
	public void setDataSource(DataSource source) {
		this.source= source;
	}
	
	public void importData(){
		list.addAll(source.getData());
	}

}
