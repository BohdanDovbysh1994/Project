package Cinema;

public class Seance implements Comparable<Seance> {
	private Movie movie;
	private Time startTime;
	private Time endTime;
	
public Seance(Movie movie, Time startTime) {
		super();
		this.movie = movie;
		this.startTime = startTime;
		endTime = new Time(startTime.getHours() + movie.getDuration().getHours(), 
				startTime.getMinutes() + movie.getDuration().getMinutes());
	}

	
	@Override
public String toString() {
		if(getEndTime().getMinutes() > 59)
		   {
		    int hours = getEndTime().getHours();
		    int mins = getEndTime().getMinutes();
		    hours = hours + 1;
		    mins = mins - 60;
		    setEndTime(new Time(hours, mins));
		   }
	return  movie + ", startTime: " + startTime + ", endTime: " + endTime + "\n";
}


	public Movie getMovie() {
	return movie;
}


public void setMovie(Movie movie) {
	this.movie = movie;
}


public Time getStartTime() {
	return startTime;
}


public void setStartTime(Time startTime) {
	this.startTime = startTime;
}


public Time getEndTime() {
	return endTime;
}


public void setEndTime(Time endTime) {
	this.endTime = endTime;
}

@Override
public int compareTo(Seance o) {
    if (!(o instanceof Seance))
        return -1;
    Seance temp = (Seance) o;
    int index = this.startTime.compareTo(temp.getStartTime());
    if (index == 0)
        index = this.endTime.compareTo(temp.endTime);
    if (index == 0)
        index = this.movie.toString().compareToIgnoreCase(temp.getMovie().toString());
    return index;
    }
  
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + ((movie == null) ? 0 : movie.hashCode());
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Seance other = (Seance) obj;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (movie == null) {
			if (other.movie != null)
				return false;
		} else if (!movie.equals(other.movie))
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		return true;
	}


}