package お金;

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
