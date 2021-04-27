package 自販機;

class Item {

    private String name;

		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}

    private int value;
			
			public void checkValue(int value) {
				if(value < 0) {
					System.out.println
						("Item.value が不正な値です。\n" + value + "\n");
					
					return;
				}

				if (value >= 0) {
					this.value = value;
					return;
				}
			}
		
		public int getValue() {
			return value;
		}
		
		public void setValue(int value) {
			checkValue(value);
		}

    private int num;

			public void checkNum(int num) {
				if(num < 0) {
					System.out.println
						("Item.num が不正な値です。\n" + num + "\n");
					
					return;
				} 
				
				if (num >= 0) {
					this.num = num;
					return;
				}
			}

		public int getNum() {
			return num;
		}

		public void setNum(int num) {
			checkNum(num);
		}

    private boolean hot;

		public boolean getHot() {
			return hot;
		}

		public void setHot(boolean hot) {
			this.hot = hot;
		}
	
    public Item
		(String name, int value, int num, boolean hot) {
        this.name = name;
        this.value = value;
        this.num = num;
        this.hot = hot;
    }

	public void drawItemNum() {
		setNum(this.num - 1);
	}
}