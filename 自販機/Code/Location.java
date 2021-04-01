package 自販機;

import お金.*;
import 商品.*;
import java.util.*;

public class Location extends ManageMoney{

    //single tone(thread safe)
	//webアプリのようなステートレスなプログラムではないので使用しました
    public static A get_location(String location){
        return A.get_instance();
    }
    
    private Hashtable<String, Item> vending = new Hashtable<String, Item>();

    public void add_item(Item item) {
        vending.put(item.name, item);
    }

    public void show_items() {
        Enumeration e = vending.keys();
        while (e.hasMoreElements()) {
            System.out.println(e.nextElement());
        }      
    }

    public void add_itemNum(String name, int num) {
        vending.get(name).num += num;
        System.out.println(vending.get(name).num);
    }

    public void readItemsList(String[] List) {
        for (String name : List) {
            Item item = new Item(name, 100, 1, false);
            add_item(item);
        }
    }
}

class ManageMoney {

    private Sen sen = new Sen();
    private int go_hyaku;
    public Hyaku hyaku = new Hyaku();
    private int go_ju;
    private int ju;

    // public void add_hyaku(Hyaku h) {
    //     hyaku += h.get_num();
    // }

    // public void sum_hyaku() {
    //     System.out.println(hyaku * 100);
    // }

    // public void show_coins() {
    //     System.out.println(sen);
    //     System.out.println(go_hyaku);
    //     System.out.println(hyaku);
    //     System.out.println(go_ju);
    //     System.out.println(ju);
    // }
    
    // public void coin_config(int[] List) {
    //     sen = List[0];
    //     go_hyaku = List[1];
    //     hyaku = List[2];
    //     go_ju = List[3];
    //     ju = List[4];
    // }
}

// class A extends Location {
	
// 	//single tone(thread safe)
// 	//webアプリのようなステートレスなプログラムではないので使用しました
//     private static A instance = new A();
//     private A(){}
//     public static A get_instance(){
//         return instance;
//     }
// }