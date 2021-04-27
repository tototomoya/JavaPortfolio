import 自販機.TestUser;

import org.junit.runner.*;

public class TestMain {
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(TestUser.class);
	}
}