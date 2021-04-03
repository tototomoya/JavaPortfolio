package 自販機;

class Item {

    public String name;
    public int value;
    public int num;
    public boolean hot;
    
    public Item(String name, int value, int num, boolean hot) {
        this.name = name;
        this.value = value;
        this.num = num;
        this.hot = hot;
    }

	public int getValue() {
		return value;
	}
}