package 自販機;

abstract class Money {
    protected int type;
    protected int num = 0;

    public int get_num() {
        return num;
    }

    public int sum() {
        return type * num;
    }

    public void add(int num) {
        this.num += num;
    }

    public void add() {
        num++;
    }

    public void subtract() {
        num--;
    }

    public void subtract(int num) {
        this.num -= num;
    }
}

class Ju extends Money {
    public Ju(){
        super.type = 10;
    }
}
class Go_ju extends Money {
    public Go_ju(){
        super.type = 50;
    }
}
class Hyaku extends Money {
    public Hyaku(){
        super.type = 100;
    }
}
class Go_hyaku extends Money {
    public Go_hyaku(){
        super.type = 500;
    }
}
class Sen extends Money {
    public Sen(){
        super.type = 1000;
    }
}

