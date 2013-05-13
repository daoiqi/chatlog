package datasource;

import java.sql.Timestamp; 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import main.ChatEntity;

/**
 * 
 * nickname content breakline nickname...
 * 
 * @author d
 * 
 */
public class QQPCChatLog extends DataSource {
	/**
	 * 昵称格式：\d{4}/\d{1,2}/\d{1,2} \d{2}:\d{2}:\d{2}
	 */
	private static String compileTimeAndNickName = "(\\d{4}/\\d{1,2}/\\d{1,2} \\d{2}:\\d{2}:\\d{2}) (.*)?";
	private List<ChatEntity> list = new LinkedList<>();
//	public int[] statuses = { DontKnow, TimeAndNickname, Content, BreakLine };
//	public static final int DontKnow = 0;
//	public static final int TimeAndNickname = 1;
//	public static final int Content = 2;
//	public static final int BreakLine = 3;

	private String nowString;//当前
	private String oldString;//老的字符串
	protected Status nowstatus = Status.DontKnow;//单前
	protected Status oldStatus;//老的状态
	
	//--------------------
	/***
	 * content[0] = Timestamp time<br>
	 * content[1] = String sender<br>
	 * content[2] = String content<br>
	 */
	private Object[] contents = new Object[3];{
		contents[2]="";
	}
	//--------------------
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	private static Pattern patternTime = Pattern
			.compile(compileTimeAndNickName);
	private static Pattern patternContent = null;

	@Override
	public List<ChatEntity> getData() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 8; i++) {
			readLine();
		}
		parseContent();
		return list;
	}

	protected void parseContent() {
		
		nowString = readLine();
		ChatEntity chatEntity=null;
		while (nowString != null) {
			parse(nowString);
			
			switch(this.oldStatus){
				case TimeAndNickname://昵称
					chatEntity.setDate((Timestamp)contents[0]);
					chatEntity.setSender((String)contents[1]);
					break;
				case Content://内容
					contents[2] = contents[2]+oldString;
					chatEntity.setContent((String)contents[2]);
					break;
				case BreakLine:
					chatEntity = new ChatEntity();
					list.add(chatEntity);
					contents[2]="";
					break;
			default:
				break;
			}
//			System.out.println(this.oldStatus+" : "+oldString);
			oldString = nowString;
			nowString = readLine();
		}
		contents[2] = contents[2]+oldString;
		chatEntity.setContent((String)contents[2]);
//		System.out.println(this.nowstatus+" : "+oldString);
	}

	protected boolean isContent(String s) {

		return false;
	}

	/**
	 * 判断是否是开头，并会解析内容
	 * @param s
	 * @return
	 */
	protected boolean isTimeAndNickName(String s) {
		Pattern string = Pattern.compile(compileTimeAndNickName);
		Matcher matcher = string.matcher(s);

		if (matcher.matches()){
			matcher.group(1);
			matcher.group(2);
			try {
				Date date;
				date = sdf.parse(matcher.group(1));
				contents[0]=new Timestamp(date.getTime());
				contents[1]=matcher.group(2);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}else{return false;}

	}


	
	/**
	 * 解析当前行，明确上一行（老一行的状态）的状态
	 * @param newString
	 * @return
	 */
	private Status parse(String newString){
		Status old = nowstatus;

		if (isTimeAndNickName(newString)) {//如果当前是时间，那么old=BreakLine
			nowstatus = Status.TimeAndNickname;
			oldStatus = Status.BreakLine;//为了明确以前的状态时什么
		} else if (old == Status.TimeAndNickname) {//如果前一条是时间，那么此行必是内容
			nowstatus = Status.Content;
			oldStatus = Status.TimeAndNickname;
		} else {//如果当前行既不是明确的时间和内容，那么当前行可能是BreakLine也可能是Content
			nowstatus = Status.DontKnow;
			oldStatus = Status.Content;//但是前一行必定是Content
		}
		return old;
	}

	private static enum Status {
		DontKnow, TimeAndNickname, Content, BreakLine
	}

}
