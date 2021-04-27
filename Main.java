import 自販機.*;
import java.lang.*;
import java.io.*;
import java.util.*;

// テスト動作の確認を別のプロセスで行います。
class Test {

	static final List<String> command = 
		Arrays.asList(
			"java", 
			"-classpath", ".:/run_dir/junit-4.12.jar:/run_dir/hamcrest-core-1.3.jar:/run_dir/json-simple-1.1.1.jar",
			"TestMain"
		);

	public static void startTestProcess() {
		try {
			process(command);
		} catch (Throwable e) {
			System.out.println("startTest()が実行できません。");
		} 
	}
	
	public static void process(List command) 
	throws Throwable {
		ProcessBuilder pb = new ProcessBuilder();
		pb = pb.command(command);
		pb.inheritIO();
		Process p = pb.start();
		p.waitFor();
	}
}

class Main {
	public static void main(String[] args) {
		Test.startTestProcess();
		Vending.main(args);
	}
}
