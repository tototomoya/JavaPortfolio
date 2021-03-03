package 自販機;

import お金.*;
import 商品.*;
import java.util.*;

public class Location extends ManageMoney{

    //single tone
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

    public void readNameList(String[] List) {
        for (String name : List) {
            Item item = new Item(name, 100, 1, false);
            add_item(item);
        }
    }

}