package 自販機;

public class A extends Location {
    private static A instance = new A();
    private A(){}
    public static A get_instance(){
        return instance;
    }
}
