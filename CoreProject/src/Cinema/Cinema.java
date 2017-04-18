package Cinema;

import java.util.Collection;
import java.util.Formatter;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class Cinema {
	private TreeMap<Days, Schedule> map;
	Schedule schedule = new Schedule();
	Formatter fmt = new Formatter();

	private static final Time open = new Time(6, 0);
	private static final Time closed = new Time(23, 0);

	public void addDay() {
		for (Days day : Days.values()) {
			map.put(day, new Schedule());
		}
	}

	public void addSchedule_day() {
		System.out.println("Enter day.");
		String name_of_day = Main.scanner.next().toUpperCase();
		Iterator<Entry<Days, Schedule>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Days, Schedule> entry = iterator.next();
			if (entry.getKey().name().equals(name_of_day)) {
				System.out.println("Please enter title of movie.");
				String title = Main.scanner.next();
				int hours_duration = 1 + (int) +(Math.random() * 3);
				int minutes_duration = (int) +(Math.random() * 60);
				System.out.println("Please enter time of begining (hour).");
				int hours_begin = Main.scanner.nextInt();
				System.out.println("Please enter time of begining (minutes).");
				int minutes_begin = Main.scanner.nextInt();
				if ((hours_begin * 60 + minutes_begin < open.getHours() * 60 + open.getMinutes())) {
					System.out.println("Cinema closed at this time");
				}

				else if (hours_begin * 60 + minutes_begin >= closed.getHours() * 60 + closed.getMinutes()
						| ((hours_duration * 60 + hours_begin * 60 + minutes_duration
								+ minutes_begin)) > (closed.getHours() * 60 + closed.getMinutes())) {
					System.out.println("Cinema closed at this time");
				}

				else if (entry.getValue().getSchedule().isEmpty()) {
					entry.getValue().getSchedule()
							.add(new Seance(new Movie(title, new Time(hours_duration, minutes_duration)),
									new Time(hours_begin, minutes_begin)));
					System.out.println("ADD");
				}

				else if ((hours_begin * 60
						+ minutes_begin) > (entry.getValue().getSchedule().iterator().next().getEndTime().getHours()
								* 60 + entry.getValue().getSchedule().iterator().next().getEndTime().getMinutes())

						|| ((hours_begin * 60 + minutes_begin
								+ entry.getValue().getSchedule().iterator().next().getMovie().getDuration().getHours()
										* 60
								+ entry.getValue().getSchedule().iterator().next().getMovie().getDuration()
										.getMinutes()) < (entry.getValue().getSchedule().iterator().next().getEndTime()
												.getHours() * 60
												+ entry.getValue().getSchedule().iterator().next().getEndTime()
														.getMinutes()))) {
					entry.getValue().getSchedule()
							.add(new Seance(new Movie(title, new Time(hours_duration, minutes_duration)),
									new Time(hours_begin, minutes_begin)));
					System.out.println("Added!");
				} else {
					System.out.println("Wrong Time!");
				}
			}
		}
	}

	public void addSchedule_all_days() {
		boolean work = true;
		Iterator<Entry<Days, Schedule>> iterator = map.entrySet().iterator();
		System.out.println("Please enter title of movie.");
		String title = Main.scanner.next();
		int hours_duration = 1 + (int) +(Math.random() * 3);
		int minutes_duration = (int) +(Math.random() * 60);
		System.out.println("Please enter time of begining (hour).");
		int hours_begin = Main.scanner.nextInt();
		System.out.println("Please enter time of begining (minutes).");
		int minutes_begin = Main.scanner.nextInt();
		while (iterator.hasNext()) {
			Entry<Days, Schedule> entry = iterator.next();
			if ((hours_begin * 60 + minutes_begin < open.getHours() * 60 + open.getMinutes()) & (work)) {
				System.out.println("Cinema closed at this time.");
				work = false;
			} else if (hours_begin * 60 + minutes_begin >= closed.getHours() * 60 + closed.getMinutes()
					| ((hours_duration * 60 + hours_begin * 60 + minutes_duration + minutes_begin)) > (closed.getHours()
							* 60 + closed.getMinutes()) & (work)) {
				System.out.println("Cinema closed at this time.");
				work = false;
			} else if (entry.getValue().getSchedule().isEmpty()) {
				entry.getValue().getSchedule()
						.add(new Seance(new Movie(title, new Time(hours_duration, minutes_duration)),
								new Time(hours_begin, minutes_begin)));
			} else if ((hours_begin * 60
					+ minutes_begin) > (entry.getValue().getSchedule().iterator().next().getEndTime().getHours() * 60
							+ entry.getValue().getSchedule().iterator().next().getEndTime().getMinutes())
					|| ((hours_begin * 60 + minutes_begin
							+ entry.getValue().getSchedule().iterator().next().getMovie().getDuration().getHours() * 60
							+ entry.getValue().getSchedule().iterator().next().getMovie().getDuration()
									.getMinutes()) < (entry.getValue().getSchedule().iterator().next().getEndTime()
											.getHours() * 60
											+ entry.getValue().getSchedule().iterator().next().getEndTime()
													.getMinutes()))) {
				System.out.println("Added!");
				entry.getValue().getSchedule()
						.add(new Seance(new Movie(title, new Time(hours_duration, minutes_duration)),
								new Time(hours_begin, minutes_begin)));
			} else if (work) {
				System.out.println("Time!" + entry.getKey());
			}
		}
	}

	public void deleteSeance_day() {
		System.out.println("Enter day.");
		String name_of_day = Main.scanner.next().toUpperCase();
		Iterator<Entry<Days, Schedule>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Days, Schedule> entry = iterator.next();
			if (entry.getKey().name().equals(name_of_day)) {
				System.out.println("Please enter title of movie.");
				String title = Main.scanner.next();
				Collection<Seance> seances = entry.getValue().getSchedule();
				Iterator<Seance> iterator2 = seances.iterator();
				while (iterator2.hasNext()) {
					Seance seance = iterator2.next();
					if (seance.getMovie().getTitle().equalsIgnoreCase(title)) {
						iterator2.remove();
					}
				}
			}
		}
	}

	public void deleteSeance_all() {
		Iterator<Entry<Days, Schedule>> iterator = map.entrySet().iterator();
		System.out.println("Please enter title of movie.");
		String title = Main.scanner.next();
		while (iterator.hasNext()) {
			Map.Entry<Days, Schedule> entry = iterator.next();
			Collection<Seance> seances = entry.getValue().getSchedule();
			Iterator<Seance> iterator2 = seances.iterator();
			while (iterator2.hasNext()) {
				Seance seance = iterator2.next();
				if (seance.getMovie().getTitle().equalsIgnoreCase(title)) {
					iterator2.remove();
				}
			}
		}
	}

	public void menu() {

		boolean work = true;

		while (work) {
			System.out.println("Enter 1 for adding day.");
			System.out.println("Enter 2 for adding  schedule for day.");
			System.out.println("Enter 3 for adding schedule for week.");
			System.out.println("Enter 4 for week schedule.");
			System.out.println("Enter 5 for deleting schedule day.");
			System.out.println("Enter 6 for deleting schedule for week.");
			System.out.println("Enter 7 for EXIT.");
			String choice = Main.scanner.next();

			switch (choice) {
			case "1":
				addDay();
				break;
			case "2":
				addSchedule_day();
				break;
			case "3":
				addSchedule_all_days();
				break;
			case "4":
				show_schedule();
				break;
			case "5":
				deleteSeance_day();
				break;
			case "6":
				deleteSeance_all();
				break;
			case "7":
				System.exit(0);
				break;
			}
		}

	}

	void show_schedule() {
		for (Map.Entry<Days, Schedule> entry : map.entrySet()) {
			System.out.println(entry.toString());
		}
	}

	public Cinema(Time open, Time closed) {
		super();
		this.map = new TreeMap<Days, Schedule>();
	}

	public Cinema(TreeMap<Days, Schedule> map, Set<Entry<Days, Schedule>> set) {
		super();
		this.map = map;
	}

	public TreeMap<Days, Schedule> getMap() {
		return map;
	}

	public void setMap(TreeMap<Days, Schedule> map) {
		this.map = map;
	}

	public Time getOpen() {
		return open;
	}

	public Time getClosed() {
		return closed;
	}

	@Override
	public String toString() {
		return "Cinema" + map + ", open=" + open + ", closed=" + closed;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

}