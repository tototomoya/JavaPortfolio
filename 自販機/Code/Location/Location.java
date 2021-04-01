package 自販機;

import java.util.*;
import java.io.*;

abstract class Location extends ManageMoney {}

class ManageMoney extends ManageItem {

    public Sen sen = new Sen();
    public Go_hyaku go_hyaku = new Go_hyaku();
    public Hyaku hyaku = new Hyaku();
    public Go_ju go_ju = new Go_ju();
    public Ju ju = new Ju();

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

    // public void show_items() {
    //     Enumeration e = vending.keys();
    //     while (e.hasMoreElements()) {
    //         System.out.println(e.nextElement());
    //     }      
    // }

    public void addItemNum(String name, int num) {
        vending.get(name).num += num;
        System.out.println(vending.get(name).num);
    }

}