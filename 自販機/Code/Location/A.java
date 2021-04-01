package 自販機;

class A extends Location {
	
	/*
	
	single tone(thread safe)

	webアプリのようなステートレスなプログラムではないので
	使用しました
	
	*/
    private static A instance = new A();
    private A(){}
    public static A get_instance(){
		// 自販機の商品データ読み込み
		instance.readItemsList("itemsList.csv");
        return instance;
    }
}