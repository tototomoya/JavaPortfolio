import お金.*;
import 自販機.*;
import 商品.*;

import java.io.*;
import java.util.stream.*;
import java.util.*;
import java.util.function.*;


class Main {

    static private class User {

		private Map<String, Long> coin;

        private void pay() {
            Console console = System.console();
            String input = console.readLine("num: ");
            String[] inputArr = input.split(" ", -1); 
            List<String> inputList = Arrays.asList(inputArr); 
            
            this.coin = new HashMap<String, Long>();
            
            this.coin = inputList.stream().collect(
                Collectors.groupingBy(Function.identity(), Collectors.counting())
            );
            
            System.out.println(this.coin.get("10"));
        }

    }

    public static void main(String[] args) {
        A a = Location.get_location("A");
        A b = Location.get_location("A");
        a.hyaku.add(4);
        System.out.println(a.hyaku.sum());
        // User u = new User();
        // u.pay();
        // A b = Location.get_location("A");
        // a.add_hyaku(h);
        // b.sum_hyaku();
    }
}