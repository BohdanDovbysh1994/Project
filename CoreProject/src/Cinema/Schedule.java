package Cinema;

import java.util.Set;
import java.util.TreeSet;

public class Schedule {
	private Set<Seance> schedule;

	public Schedule() {
		super();
		this.schedule = new TreeSet<Seance>();
	}

	public Set<Seance> getSchedule() {
		return schedule;
	}

	public void setSchedule(Set<Seance> schedule) {
		this.schedule = schedule;
	}

	@Override
	public String toString() {
		return schedule.toString();
	}

	public Schedule(Set<Seance> schedule) {
		super();
		this.schedule = schedule;
	}

}
