package 自販機;

import java.io.*;
import java.util.stream.*;
import java.util.*;
import java.util.function.*;

class User {

	private Map<String, Integer> coin = 
	new HashMap<String, Integer>();
	{	
		coin.put("10", 0);
		coin.put("50", 0);
		coin.put("100", 0);
		coin.put("500", 0);
		coin.put("1000", 0);
	}
	
	private Location l;
	Console console = System.console();

	// 実行時引数
	String inputCoin;
	String inputLocationName;
	String inputChoiseItemName;

	User(String l, String c, String n) {
		inputCoin = c; 
		inputLocationName = l;
		inputChoiseItemName = n;
		
		choiseVending();
	}
		
		private void choiseVending() {
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

	public void pay() {
		String[] inputCoin_ = inputCoin.split(" ", -1); 
		
		for (String type: inputCoin_){
			coin.put(type, coin.get(type) + 1);
		}
		choiseItem();
		paid();
	}
		public void choiseItem() {
			Object value = l.getValue(inputChoiseItemName);
			choiseItem_(value);
		}

			private void choiseItem_(Object value) {
				if (value.getClass().getName() 
				== "java.lang.String") {
					System.out.println(value);
					return;
				}
				Integer valueInteger = (Integer)value;
				System.out.println(
					"お客様が入れたお金: " 
					+ valueInteger.intValue()
				);
			}

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

			System.out.println(
				l.locationName + ":" + coinSum()
			);		
		}

			private int coinNum(String type) {
				return coin.get(type).intValue();
			}
			private int coinSum() {
				
				return 
				l.ju.sum() + l.go_ju.sum() + l.hyaku.sum() + l.go_hyaku.sum() + l.sen.sum();
			}
}

/*

Locationごとにスレッドを分ける

*/
abstract class UserLocation extends Thread {
	public String inputCoin;
	public String inputLocationName;
	public String inputItemName;

	@Override
	public void run() {
		User user = new User(
			inputLocationName,
			inputCoin,
			inputItemName
		);
		user.pay();
	}
}
	class LocationA extends UserLocation {
		public LocationA(String c, String n) {
			super.inputLocationName = "A";

			super.inputCoin = c;
			super.inputItemName = n;
		}
	}
	class LocationB extends UserLocation {
		public LocationB(String c, String n) {
			super.inputLocationName = "B";

			super.inputCoin = c;
			super.inputItemName = n;
		}
	}

public class Vending {
    public static void main(String[] args) {
		
		String[][] s = {
			{"10 10 100", "コーラ"},
			{"10 10 100", "コーラ"},
			{"10 10 100", "コーラ"},
			{"10 10 100", "コーラ"}
		};

		for (String[] ss : s) {
			try {
				LocationA a = 
				new LocationA(ss[0], ss[1]);
				LocationB b = 
				new LocationB(ss[0], ss[1]);
				a.start(); b.start();
				a.join(); b.join();
			} catch (InterruptedException e) {
				System.out.println("システムエラーです。");
			}
		}
    }
}
