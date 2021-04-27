package 自販機;

abstract class Money {
	
	private int type;
			
		public int getType() {
			return type;
		}

		public boolean setType(int type) {
			return checkType(type);
		}

			public boolean checkType(int type) {

				if (
					type == 10 || 
					type == 50 || 
					type == 100 ||
					type == 500 || 
					type == 1000
				){
					this.type = type;
					return true;
				}

				Message.setMessage(
					new Throwable(Message.ERROR), 
					Integer.toString(this.num), 
					Integer.toString(this.type)
				);

				return false;

			}

	private int num = 0;

		public boolean add(int num) {
			return setNum(this.num + num);
		}

		public boolean add() {
			return setNum(num+1);
		}

		public boolean subtract(int num) {
			return setNum(this.num - num);
		}

		public boolean subtract() {
			return setNum(num-1);
		}

			public int getNum() {
				return num;
			}

			public boolean setNum(int num) {
				if (checkNum(num)) {
					this.num = num;
					return true;
				}

				return false;
			}

				public boolean checkNum(int num) {
					
					if (num >= 0) {
						return true;
					}

					new MoneyWarning(this.num, this.type);

					return false;
				}

    public int sum() {
        return type * num;
    }

}

class Ju extends Money {
    public Ju(){
        super.setType(10);
    }
}

class Go_ju extends Money {
    public Go_ju(){
        super.setType(50);
    }
}

class Hyaku extends Money {
    public Hyaku(){
        super.setType(100);
    }
}

class Go_hyaku extends Money {
    public Go_hyaku(){
        super.setType(500);
    }
}

class Sen extends Money {
    public Sen(){
        super.setType(1000);
    }
}

/*

一部の硬貨のお釣り切れによるエラーを回避します。
回避できなかった場合のみ、Messageインスタンスに通知します。

*/
class MoneyWarning extends Throwable {

	MoneyWarning(int num, int type) {
		if (checkNumUtil(num, type)) {
			return;
		}
		
		Message.setMessage(
			this, 
			Integer.toString(num), 
			Integer.toString(type)
		);
	}

	final String GREEN = "\u001B[32m";
	final String RESET = "\u001B[0m";

	boolean checkNumUtil(int num, int type) {
		
		if(num == 0) {

			switch (type) {
				case 10:
					System.out.println(
						GREEN + type + "円玉の補充をしてください。" 
					);
					System.out.println(RESET);
					return true;
				case 50:
					System.out.println(
						GREEN + type + "円玉の補充をしてください。" 
					);
					System.out.println(RESET);
					return true;
				case 100:
					System.out.println(
						GREEN + type + "円玉の補充をしてください。" 
					);
					System.out.println(RESET);
					return true;
				case 500:
					System.out.println(
						GREEN + type + "円玉の補充をしてください。" 
					);
					System.out.println(RESET);
					return true;

				default:
					return false;
			}
		}

		return false;
	}
}