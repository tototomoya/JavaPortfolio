package 自販機;

import java.io.*;
import java.util.stream.*;
import java.util.*;
import java.util.function.*;

class User {

	// お客様の投入金格納
	private Map<String, Integer> coin = 
		new HashMap<String, Integer>();
	{	
		coin.put("10", 0);
		coin.put("50", 0);
		coin.put("100", 0);
		coin.put("500", 0);
		coin.put("1000", 0);
	}

		protected void setCoin(String coin) {
			
			String[] inputCoin = coin.split(" ", -1); 
			
			for (String type: inputCoin){
				this.coin.put(type, this.coin.get(type) + 1);
			}

		}

	// 自販機インスタンス
	protected Location vending;

		protected void setVending(String location) {
			switch(location) {
				case "A":
					vending = A.getInstance();
					return;
				case "B":
					vending = B.getInstance();
					return;
				default:
					Message.setMessage(
						new Throwable(
							"自販機" + location + 
							"は存在しません。"
						)
					);
					return;
			}
		}

	// お客様が選択した商品のインスタンスを格納。
	protected Item item;

		protected boolean setItem(String name) {
			if (vending.setItem(name)) {
				this.item = vending.getItem(name);
				return true;
			}
			
			return false;
		}

// トランザクション完結まで行います。

	public void start(String location, String coin, String itemName) {
		
		setVending(location);
		if(!Message.check()) {
			err(location, coin, itemName);
			return;
		}

		if(!setItem(itemName)) {
			err(location, coin, itemName);
			return;
		}

		setCoin(coin);
		if(!Message.check()) {
			err(location, coin, itemName);
			return;
		}

		pay();
		if(!Message.check()) {
			err(location, coin, itemName);
			return;
		}

		vending.reduceItemNum(item.getName());
		if(!Message.check()) {
			err(location, coin, itemName);
			return;
		}

		success(location, coin, itemName);
	}

// 成功時の処理

	protected void success(String location, String coin, String itemName){
		System.out.println(
			"成功した処理\n" + "\t" + location + " : " + coin + itemName + "\n"
		);
		vending.saveMoney(vending);
		vending.printVendingStatus();

	}

// 失敗時の処理
	
	protected void err(String location, String coin, String itemName) {
		vending.coinReset();
		System.out.println(
			"失敗した処理\n" + "\t" + location + " : " + coin + itemName + "\n"
		);
		Message.getMessage();
		vending.printVendingStatus();
		
		System.out.println();
	}

// 会計処理

	// お釣り格納変数
	protected Map<String, Integer> change = new HashMap<String, Integer>();
	{	
		coin.put("10", 0);
		coin.put("50", 0);
		coin.put("100", 0);
		coin.put("500", 0);
		coin.put("1000", 0);
	}
		protected void getChange() {
			System.out.println(
				"お釣りは" + "\n" +
				"10: " + change.get("10") + "\n" +
				"50: " + change.get("50") + "\n" +
				"100: " + change.get("100") + "\n" +
				"500: " + change.get("500") + "\n" +
				"1000: " + change.get("1000") + "\n"
			);
		}

	protected void pay() {

		// お釣りなし
		if (inputCoinSum() == item.getValue()) {

			paid();

		// お釣りあり 
		} else if (inputCoinSum() > item.getValue()) {

			paid();
			
			change = vending.change(inputCoinSum() - item.getValue());	

		// 投入金額不足
		} else if (inputCoinSum() < item.getValue()) {
			
			Message.setMessage(
				new Throwable(
					"投入金が" + (item.getValue() - inputCoinSum() + "円") + 
					"不足してます。" 
				)
			);
		}
	}

		protected int inputCoinSum() {

			return
			coin.get("10").intValue() * 10 +
			coin.get("50").intValue() * 50 + 
			coin.get("100").intValue() * 100 +
			coin.get("500").intValue() * 500 + 
			coin.get("1000").intValue() * 1000 ;
		}

		// 自販機にお客様が入れたお金を反映
		protected void paid() {
			
			vending.ju.add(
				coinNum("10")
			);
			
			vending.go_ju.add(
				coinNum("50")
			);
			
			vending.hyaku.add(
				coinNum("100")
			);
			
			vending.go_hyaku.add(
				coinNum("500")
			);
			
			vending.sen.add(
				coinNum("1000")
			);
		
		}

			protected int coinNum(String type) {
				return coin.get(type).intValue();
			}
}

public class Vending {

    public static void main(String[] args) {

		// 引数
		String[][] s = {
			{"A", "10 10 10", "コーラ"},
			{"A", "10 10 100", "コーラ"},
			{"A", "1000", "ジュース"},
			{"A", "10 10 100 10", "コーラ"},
			{"A", "10 10 10", "コーラ"},
			{"A", "10 10 100", "コーラ"},
			{"A", "1000", "コーラ"},
			{"A", "10 10 100 10", "ジュース"},
		};

		for (String[] ss : s) {
			User user = new User();
			user.start(ss[0],ss[1],ss[2]);
		}
    }
}