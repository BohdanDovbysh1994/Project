package BlackJack;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dealer {
	static final private String name = "Pablo";
	private int score;
	List<Cards> dealer_cards = new ArrayList<>();
	Map<Dealer, ArrayList<Cards>> map_dealer = new HashMap<>();

	public void cards() {
		System.out.println("Dealer's cards.");
		while (dealer_cards.size() < 52) {
			for (Cards card : Cards.values())
				dealer_cards.add(card);
		}

		Main.formatter = new Formatter();
		System.out.println(Main.formatter.format(" %1$s\n %2$s\n %3$s\n %4$s\n", dealer_cards.subList(0, 13),
				dealer_cards.subList(13, 26), dealer_cards.subList(26, 39), dealer_cards.subList(39, 52)));
	}

	public Dealer() {
		super();

	}

	public String getName() {
		return name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public List<Cards> getCards() {
		return dealer_cards;
	}

	public void setCards(List<Cards> cards) {
		this.dealer_cards = cards;
	}

	@Override
	public String toString() {
		return "Dealer [name=" + name + ", score=" + score + ", cards=" + dealer_cards + "]" + "\n";
	}

}
