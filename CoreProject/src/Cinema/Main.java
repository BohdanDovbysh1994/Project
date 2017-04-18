package Cinema;
import java.util.Scanner;

public class Main {

	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		Cinema cinema = new Cinema(new Time(06, 30), new Time(20, 00));
		cinema.menu();
}
}