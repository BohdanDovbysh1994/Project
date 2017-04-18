package Cinema;

public class Movie {
	private String title;
	private Time Duration;
	public Movie(String title, Time Duration) {
		super();
		this.title = title;
		this.Duration = Duration; 
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Time getDuration() {
		return Duration;
	}
	public void setDuration(Time duration) {
		Duration = duration;
	}
	@Override
	public String toString() {
		return "title: " + title + ", Duration: " + Duration;
	}
	
	
	
	
}