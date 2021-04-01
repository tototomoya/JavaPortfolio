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

	User(Location l) {
		this.l = l; 
	}

	public void pay() {
		Console console = System.console();
		String input = console.readLine("お金を入れてください: ");
		String[] inputList = input.split(" ", -1); 
		
		for (String type: inputList){
			coin.put(type, coin.get(type) + 1);
		}
		
		paid();
	}

	private void paid() {
		l.hyaku.add(
			coin.get("100").intValue()
		);

		System.out.println(l.hyaku.sum());		
	}

}

public class Vending {

    public static void main(String[] args) {
        A a = A.get_instance();
        A b = A.get_instance();
        a.hyaku.add(4);

        User u = new User(a);
        u.pay();
    }
}