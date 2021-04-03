package 自販機;

class B extends Location {
	
	/*
	
	single tone(thread safe)

	webアプリのようなステートレスなプログラムではないので
	使用しました
	
	*/
    private static B instance = new B();
    private void B(){}
	static {
		// 自販機の商品データ読み込み
		instance.readItemsList("B_itemsList.csv");
		instance.locationName = "B";
	}

    public static B getInstance(){
        return instance;
    }
}