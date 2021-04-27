package 自販機;

import org.junit.*;

import static org.junit.Assert.assertEquals;

/*
不正パラメータ検知を目的にJunitを用いたメッセージインスタンスの動作確認を行います。
*/

public class TestUser extends User {
	
	// 失敗ケース
	String testLocation = "C";
	String testItemName = "ジュース";
	String testCoin = "10";

	public void setting() {
		setVending("A");
		setItem("コーラ");
	}

    @Test
    public void testMessage() {
		System.out.println("Test: 自販機の不正選択、商品の不正選択、投入金不足に対するメッセージクラスの動作テスト\n");
		
		setVending(testLocation);
		
		setting();
		setItem(testItemName);
		
		setting();
		setCoin(testCoin);	
		pay();
		
		Message.getMessage();
    }
}
