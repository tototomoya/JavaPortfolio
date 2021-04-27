package 自販機;

import java.util.*;
import java.io.*;

abstract class Location extends ManageMoney {

	protected String locationName;

	public void printVendingStatus() {
		System.out.println(
			locationName + "の現在の状態: \n" + "\n" + 
			"残金: " + super.sumMoney() + "\n"
		);
		super.showItems();
	}
}

class ManageMoney extends ManageItem implements Cloneable {

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

// トランザクション失敗時の復元処理

	private ManageMoney preMoney;

		public void saveMoney(ManageMoney m) {
			preMoney = new ManageMoney();
			preMoney.ju.setNum(
				m.ju.getNum()
			);
			
			preMoney.go_ju.setNum(
				m.go_ju.getNum()
			);
			
			preMoney.hyaku.setNum(
				m.hyaku.getNum()
			);

			preMoney.go_hyaku.setNum(
				m.go_hyaku.getNum()
			);
			
			preMoney.sen.setNum(
				m.sen.getNum()
			);
		}

		public void coinReset() {
			if (preMoney == null) {
				preMoney = new ManageMoney();
			}

			ju.setNum(
				preMoney.ju.getNum()
			);
			
			go_ju.setNum(
				preMoney.go_ju.getNum()
			);
			
			hyaku.setNum(
				preMoney.hyaku.getNum()
			);

			go_hyaku.setNum(
				preMoney.go_hyaku.getNum()
			);
			
			sen.setNum(
				preMoney.sen.getNum()
			);
		}

// お釣りの計算

	private Map<String, Integer> coin = new HashMap<String, Integer>();
	{
		coin.put("10", 0);
		coin.put("50", 0);
		coin.put("100", 0);
		coin.put("500", 0);
		coin.put("1000", 0);
	}

	private int next = -1;

	public Map<String, Integer> change(int change) {

		if (change == 0) {
			next = -1;
			return coin;
		}

		if (next == -1 && change >= 1000) {
			if (sen.subtract()) {
				coin.put("1000", coin.get("1000").intValue() + 1);
				change = change - 1000;

				return change(change);
			}
			
			next = 500;
			
			return change(change);
		}

		if (next == -1 && change >= 500 || next == 500) {
			if (go_hyaku.subtract()) {
				coin.put("500", coin.get("500").intValue() + 1);
				change = change - 500;

				return change(change);
			}

			next = 100;

			return change(change);
		}

		if (next == -1 && change >= 100 || next == 100) {
			if(hyaku.subtract()){
				coin.put("100", coin.get("100").intValue() + 1);
				change = change - 100;
				
				return change(change);
			}

			next = 50;
			
			return change(change);
		}

		if (next == -1 && change >= 50 || next == 50) {
			if (go_ju.subtract()){
				coin.put("50", coin.get("50").intValue() + 1);
				change = change - 50;

				return change(change);
			}

			next = 10;

			return change(change);
		}

		if (change >= 10) {
			
			if (ju.subtract()) {
			
				coin.put("10", coin.get("10").intValue() + 1);
				change = change - 10;

				return change(change);
			}
			
			next = -1;

			Message.setMessage(
				new ManageMoneyException("お釣りが不足してます。")
			);
		}

		//ここは実行されない
		return coin;
	}

}

	class ManageMoneyException extends Throwable {

		ManageMoneyException(String msg){
			super(msg);
		}

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
        vending.put(item.getName(), item);
    }


    public void showItems() {
		
		Set itemNameSet = vending.keySet();

		for (Object name : itemNameSet) {
            System.out.println(
				
				"商品名 :" +
				vending.get((String)name).getName() + "\n" +

				"在庫: " +
				vending.get((String)name).getNum() + "\n"

			);
		}
    }

    public void reduceItemNum(String name) {
		vending.get(name).drawItemNum();
    }

// お客様が選択した商品インスタンスを保持

	Item selectItem;

		public Item getItem(String name) {
			return selectItem;
		}

		public boolean setItem(String name) {
			if (checkItem(name)) {
				selectItem = vending.get(name);
				return true;
			}

			return false;
		}

			public boolean checkItem(String name) {
				Item item = vending.get(name);

				if (item == null) {
					Message.setMessage(
						new ManageItemException(
							"商品" + name + "が存在しません。"
						)
					);
					return false;
				}

				if (item.getNum() == 0) {
					Message.setMessage(
						new ManageItemException( name + "は売り切れです。")
					);
					return false;
				}

				return true;
			}
}

