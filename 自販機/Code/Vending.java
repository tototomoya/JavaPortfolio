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

// 自販機インスタンス
	private Location l;

// 引数を格納する変数
	String inputCoin;
	String inputLocationName;
	String inputChoiseItemName;

// 選択した商品のインスタンス
	Item item;

	User(String location, String coin, String itemName) {
		inputCoin = coin; 
		inputLocationName = location;
		inputChoiseItemName = itemName;
		
		createVending();
		
		int existItem = choiseItem();
/*
	1 商品の在庫がある

	0 売り切れ

	-1 商品が存在しない
*/
		if (existItem == 1) {

			String[] inputCoin_ = inputCoin.split(" ", -1); 
			
			for (String type: inputCoin_){
				this.coin.put(type, this.coin.get(type) + 1);
			}

			pay();

		} else if (existItem == 0) {

			System.out.println("売り切れです。\n");

		} else if (existItem == -1) {

			System.out.println("商品が存在しません。\n");

		}

	}
		
		private void createVending() {
			try {

				switch(inputLocationName) {
					case "A":
						l = A.getInstance();
						break;
					case "B":
						l = B.getInstance();
						break;
				}
				
			} catch (ExceptionInInitializerError e) {
				System.out.println("原因: " + e.getCause());
			}
		}

// 選択商品の存在確認
		private int choiseItem() {

			Object itemObject =
				l.getItem(inputChoiseItemName);
			
			if (itemObject.getClass().getName() 
								    == "java.lang.String") {

				if (itemObject == "商品が存在しません。") {
					return -1;
				}
				
				if (itemObject == "売り切れです。") {
					return 0;
				}

			}

			this.item = (Item)itemObject;

			System.out.println(
				"選んだ商品: " + item.name + "\n" +
				"商品の値段: " + item.value + "円" + "\n"
			);

			return 1;
		}

	public void pay() {

// お釣りなし
		if (inputCoinSum() == item.value) {
			paid();

			l.printVendingStatus();

// お釣りあり 
		} else if (inputCoinSum() > item.value) {

			paid();
			
			l.drawItemNum(inputChoiseItemName);
			
			Map<String, Integer> change = 
				new HashMap<String, Integer>();
			change = l.change(inputCoinSum() - item.value);	
		
			System.out.println(
				"お釣りは" + "\n" +
				"10: " + change.get("10") + "\n" +
				"50: " + change.get("50") + "\n" +
				"100: " + change.get("100") + "\n" +
				"500: " + change.get("500") + "\n" +
				"1000: " + change.get("1000") + "\n"
			);

			l.printVendingStatus();

// 投入金額不足
		} else if (inputCoinSum() < item.value) {
			System.out.println(inputCoinSum());
			System.out.println("お金が不足してます。");
		}
	}

		private int inputCoinSum() {
			return
			coin.get("10").intValue() * 10 +
			coin.get("50").intValue() * 50 + 
			coin.get("100").intValue() * 100 +
			coin.get("500").intValue() * 500 + 
			coin.get("1000").intValue() * 1000 ;
		}



/*

	自販機にお客様が入れたお金を反映

*/

		private void paid() {
			l.ju.add(
				coinNum("10")
			);
			l.go_ju.add(
				coinNum("50")
			);
			l.hyaku.add(
				coinNum("100")
			);
			l.go_hyaku.add(
				coinNum("500")
			);
			l.sen.add(
				coinNum("1000")
			);

		}

			private int coinNum(String type) {
				return coin.get(type).intValue();
			}
}

public class Vending {
    public static void main(String[] args) {
		
		String[][] s = {
			{"10 10 10 100", "サイダー"},
			{"10 10 100 100", "ジュース"},
			{"1000", "コーラ"},
			{"10 10 100", "コーラ"}
		};

		for (String[] ss : s) {
			User user = new User(
				"A",
				ss[0],
				ss[1]
			);
		}
    }
}