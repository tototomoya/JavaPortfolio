package 自販機;

import java.util.*;
import java.io.*;

abstract class Location extends ManageMoney {

	public String locationName;

	public void printVendingStatus() {

		System.out.println(
			locationName + "の現在の状態: \n" + "\n" + 
			"残金: " + super.sumMoney() + "\n"
		);

		super.showItems();

	}

}

class ManageMoney extends ManageItem {

    public Ju ju = new Ju();
    public Go_ju go_ju = new Go_ju();
    public Hyaku hyaku = new Hyaku();
    public Go_hyaku go_hyaku = new Go_hyaku();
    public Sen sen = new Sen();

	public int sumMoney() {
		return 
		ju.sum() + go_ju.sum() + hyaku.sum() 
		+ go_hyaku.sum() + sen.sum(); 
	}

// お釣りの計算
	public Map<String, Integer> change (int change) {
		Map<String, Integer> coin = new HashMap<String, Integer>();

		coin.put("10", 0);
		coin.put("50", 0);
		coin.put("100", 0);
		coin.put("500", 0);
		coin.put("1000", 0);

		return change_(change, coin);
	}

		public Map<String, Integer> change_(int change, Map<String, Integer> coin) {

			if (change == 0) {
				return coin;
			}

			if (change >= 1000) {
				sen.subtract();
				coin.put("1000", coin.get("1000").intValue() + 1);
				change = change - 1000;
				return change_(change, coin);
			}

			if (change >= 500) {
				go_hyaku.subtract();
				coin.put("500", coin.get("500").intValue() + 1);
				change = change - 500;
				return change_(change, coin);
			}

			if (change >= 100) {
				hyaku.subtract();
				coin.put("100", coin.get("100").intValue() + 1);
				change = change - 100;
				return change_(change, coin);
			}

			if (change >= 50) {
				go_ju.subtract();
				coin.put("50", coin.get("50").intValue() + 1);
				change = change - 50;
				return change_(change, coin);
			}

			if (change >= 10) {
				ju.subtract();
				coin.put("10", coin.get("10").intValue() + 1);
				change = change - 10;
				return change_(change, coin);
			}

			//ここは実行されない
			return coin;
		}

    // public void coin_config(int[] List) {
    //     sen = List[0];
    //     go_hyaku = List[1];
    //     hyaku = List[2];
    //     go_ju = List[3];
    //     ju = List[4];
    // }
}

class ManageItem {

	private Hashtable<String, Item> vending = new Hashtable<String, Item>();

	public void readItemsList(String filename) {
		try {
			FileReader fr = 
			new FileReader(
				"./自販機/Code/Location/" + filename
			);
			BufferedReader br = new BufferedReader(fr);
			
			String line;
			while ((line = br.readLine()) != null) {
				String[] itemList = line.split(",", -1);
				Item item = new Item(
					itemList[0], 
					Integer.parseInt(itemList[1]), 
					Integer.parseInt(itemList[2]),
					Boolean.parseBoolean(itemList[3])
				);
				
				addItem(item);
			}

			br.close();

		} catch (IOException ex) {
			System.out.println("ファイルがないです");
		}
	}
	
	public void addItem(Item item) {
		vending.put(item.name, item);
	}

    public void showItems() {
		
		Set itemNameSet = vending.keySet();

		for (Object name : itemNameSet) {
            System.out.println(
				
				"商品名 :" +
				vending.get((String)name).name + "\n" +

				"在庫: " +
				vending.get((String)name).num + "\n"

			);
		}
    }

	public void drawItemNum(String name) {
		vending.get(name).drawItemNum();
	}

	public Object getItem(String name) {

		if (vending.get(name) == null) {
			return "商品が存在しません。";
		}

		if (vending.get(name).num == 0) {
			return "売り切れです。";
		}
		
		return vending.get(name);
	}
}
