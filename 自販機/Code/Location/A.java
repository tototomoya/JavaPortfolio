package 自販機;

public class A extends Location {
	
	//single tone(thread safe)
	//webアプリのようなステートレスなプログラムではないので使用しました
    private static A instance = new A();
    private A(){}
    public static A get_instance(){
        return instance;
    }
}