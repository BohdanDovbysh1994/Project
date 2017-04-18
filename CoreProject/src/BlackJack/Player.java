package BlackJack;


	import java.util.Currency;
	import java.util.Locale;

	public class Player {
		
		private String name;
		private int money;
		private int score;
		


		public Player(String name, int money) {
			super();
			this.name = name;
			this.money = money;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getMoney() {
			return money;
		}

		public void setMoney(int money) {
			this.money = money;
		}
		public int getScore() {
			return score;
		}
		
		public void setScore(int score) {
			this.score = score;
		}

		@Override
		public String toString() {
				Currency c;
				c = Currency.getInstance(Locale.US);
		
			return "Name=" + name.substring(0, 1).toUpperCase() + name.substring(1) + ", money=" + money + " " + c.getSymbol();
		}



}
