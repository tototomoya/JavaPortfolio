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

	User(Location l) {
		this.l = l; 
	}

	public void pay() {
		String input = console.readLine("お金を入れてください: ");
		String[] inputList = input.split(" ", -1); 
		
		for (String type: inputList){
			coin.put(type, coin.get(type) + 1);
		}
		choiseItem();
		// paid();
	}

		public void choiseItem() {
			String input = 
			console.readLine("商品を選択してください: ");
			Object value = l.getValue(input);
			choiseItem_(value);
		}

			private void choiseItem_(Object value) {
				if (value.getClass().getName() 
				== "java.lang.String") {
					System.out.println(value);
					choiseItem();
					return;
				}
				Integer valueInteger = (Integer)value;
				System.out.println(valueInteger.intValue());
			}

		private void paid() {
			l.hyaku.add(
				coin.get("100").intValue()
			);
			l.sen.add(
				coin.get("1000").intValue()
			);

			System.out.println(l.locationName + ":" + l.hyaku.sum() + l.sen.sum());		
		}

}

public class Vending {

    public static void main(String[] args) {
        A a = A.get_instance();
        A b = A.get_instance();

        User u = new User(a);
        u.pay();

		User uu = new User(a);
        uu.pay();

    }
}