package 自販機;

import お金.*;

// Inner class of singleton instance.
class ManageMoney {
    private int sen;
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