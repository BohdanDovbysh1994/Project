package BlackJack;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Casino {

	static final byte max_number_of_players = 4;

	static final short starting_price = 100;

	Dealer dealer = new Dealer();

	{
		dealer.cards();

	}

	Map<Player, ArrayList<Cards>> map_casino = new HashMap<>();
	Formatter formatter = new Formatter();

	public void add_player() {
		System.out.println("Please enter name.");
		String name = Main.scanner.next();
		int money = 500 + (short) (Math.random() * 10000);

		if (map_casino.size() <= max_number_of_players) {
			map_casino.put(new Player(name, money), new ArrayList<Cards>());
		} else {
			System.out.println("Sorry, but max number of players at the one table is 5.");
		}

	}

	public void add_cards() {
		System.out.println("Please enter name of player.");
		String name_of_player = Main.scanner.next();
		Iterator<Entry<Player, ArrayList<Cards>>> iterator = map_casino.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Player, ArrayList<Cards>> entry = iterator.next();
			if (entry.getKey().getName().equalsIgnoreCase(name_of_player)) {
				if (entry.getValue().isEmpty()) {
					int i = 0;
					while (i < 2) {
						short choice_01 = (short) (Math.random() * dealer.dealer_cards.size());
						entry.getValue().add(dealer.getCards().get(choice_01));
						dealer.dealer_cards.remove(choice_01);
						i++;
					}
					short sum = 0;
					for (byte j = 0; j < entry.getValue().size(); j++) {
						sum += entry.getValue().get(j).getValue();
					}

					System.out.println(entry.getKey().getName().substring(0, 1).toUpperCase()
							+ entry.getKey().getName().substring(1).toLowerCase() + "'s score is: " + sum);

				}
				boolean game = true;
				while (game) {
					System.out.println("Do you want to take another card?");
					String answer = Main.scanner.next();

					if (answer.equalsIgnoreCase("YES")) {
						byte choice_02 = (byte) (Math.random() * dealer.dealer_cards.size());
						entry.getValue().add(dealer.getCards().get(choice_02));
						dealer.dealer_cards.remove(choice_02);

						byte sum = 0;
						for (byte j = 0; j < entry.getValue().size(); j++) {
							sum += entry.getValue().get(j).getValue();
						}

						if (sum > 21) {
							System.out.println("You lose.");
							playSound();
							game = false;
						}

						if (sum == 21) {
							System.out.println("YOU ARE WINNER!!!!!!!!");
							playSound_1();
							entry.getKey().setScore(sum);
						}

						System.out.println(entry.getKey().getName().substring(0, 1).toUpperCase()
								+ entry.getKey().getName().substring(1).toLowerCase() + "'s score is: " + sum);

					} else if (answer.equalsIgnoreCase("NO")) {
						byte sum = 0;
						for (byte j = 0; j < entry.getValue().size(); j++) {
							sum += entry.getValue().get(j).getValue();
						}
						entry.getKey().setScore(sum);
						System.out.println(entry.getKey().getName() + "'s score is: " + sum);

						game = false;

					}
					List<Integer> ints = Stream.of(entry.getKey().getScore()).collect(Collectors.toList());
					Integer maxIntP = ints.stream().max(Comparator.comparing(i -> i)).get();

					System.out.println("Maximum number in the set is " + maxIntP);

				}

			}
		}
	}

	public void play_with_dealer() {
		System.out.println("play_with_dealer");
		dealer.map_dealer.put(new Dealer(), new ArrayList<>());
		Iterator<Entry<Dealer, ArrayList<Cards>>> iterator = dealer.map_dealer.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Dealer, ArrayList<Cards>> entry = iterator.next();
			if (entry.getValue().isEmpty()) {
				int x = 0;
				while (x < 2) {
					System.out.println("sdfgh");
					int choice_03 = (int) (Math.random() * dealer.dealer_cards.size());
					entry.getValue().add(dealer.getCards().get(choice_03));
					x++;
				}
				int sum = 0;
				for (int j = 0; j < entry.getValue().size(); j++) {
					sum += entry.getValue().get(j).getValue();
					entry.getKey().setScore(sum);
				}
				System.out.println(entry.getKey().getName().substring(0, 1).toUpperCase()
						+ entry.getKey().getName().substring(1).toLowerCase() + "'s score is: " + sum);
			}

		}

	}

	public void result() {
		int max = 0;
		String player = " ";
		Iterator<Entry<Player, ArrayList<Cards>>> iterator = map_casino.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Player, ArrayList<Cards>> entry = iterator.next();

			if (max < entry.getKey().getScore() & entry.getKey().getScore() < 21) {
				max = entry.getKey().getScore();
				player = entry.getKey().getName();
			}
			

			// List<Integer> ints =
			// Stream.of(entry.getKey().getScore()).collect(Collectors.toList());
			// Integer maxIntP = ints.stream().max(Comparator.comparing(i ->
			// i)).get();
			//
			// System.out.println("Maximum number in the set is " + maxIntP);

		}
		if (max < dealer.getScore()) {
			System.out.println("Dealer won!");
		} else {
			System.out.println(player + " is winner " + max);
		}
	}

	public void show_table() {
		for (Map.Entry<Player, ArrayList<Cards>> entry : map_casino.entrySet()) {
			System.out.println(entry);
			System.out.println(entry.getKey().getName() + " " + entry.getKey().getScore());
		}
	}

	public void playSound() {
		try {
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(new File("D:/MusicPlayer/KissesinParadise.wav").getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}

	public void playSound_1() {
		try {
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(new File("D:/MusicPlayer/UpbeatFunk.wav").getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}

	public void playSound_2() {
		try {
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(new File("D:/MusicPlayer/UpbeatFunk.wav").getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}

}