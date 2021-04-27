package 自販機;

import java.util.*;

class Message {

// 固定のエラーメッセージ
	
	public static final String ERROR = "/ERROR/";

// ロギング

	// 出力文格納変数
	private static List<Object> message = 
		new ArrayList<Object>();

		public static void setMessage(Throwable e) {
			StackTraceElement[]	s = e.getStackTrace();
			
			Object saveMessage = 
				s[0].getClassName() + "\n\t" + 
				s[0].getMethodName() + "() : " + e.getMessage();

			message.add(saveMessage);
		}

		public static void setMessage(Throwable e, String... args) {	

			StackTraceElement[]	s = e.getStackTrace();
			
			Object saveMessage = 
				s[0].getClassName() + "\n\t" + 
				s[0].getMethodName() + "() : " + e.getMessage() + "\n\t" +
				String.join(",", args);

			message.add(saveMessage);
		}

		public static boolean check() {
			return message.isEmpty();
		}

		public static void getMessage() {
			for (Object m : message) {
				System.out.println(m + "\n");
			}
			message.clear();
		}
}