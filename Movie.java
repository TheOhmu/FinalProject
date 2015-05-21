import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class Movie {
	private int id;
	private String name, release_date;
	private ArrayList<Rating> ratings;
	private ArrayList<String> tags;
	private HashMap<Movie, Double> similarMovies;
	
	public HashMap<Movie, Double> getSimilarMovies() {
		return similarMovies;
	}

	public void setSimilarMovies(Movie m, double similarity) {
		similarMovies.put(m, similarity);
	}

	public Movie(int id, String name, String release_date) {
		this(id, name, release_date, new ArrayList<String>());
	}
	
	public Movie(int id, String name, String release_date, ArrayList<String> tags) {
		this.id = id;
		this.name = name;
		this.release_date = release_date;
		this.tags = tags;
		ratings = new ArrayList<Rating>();
		similarMovies=new HashMap<Movie, Double>();
	}
	
	public ArrayList<Rating> getRatings() {
		return ratings;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getRelease_date() {
		return release_date;
	}
	
	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}
	
	public void addRating(Rating r) {
		ratings.add(r);
	}
	
	public void clearRatings() {
		ratings.clear();
	}
	
	public void removeRatings(ArrayList<Rating> ratingsToRemove) {
		ratings.removeAll(ratingsToRemove);
	}
	
	public ArrayList<String> getTags() {
		return tags;
	}
	
	
	public String toString() {
		String ret = "id:\t\t" + id;
		ret += "\nname:\t\t" + name;
		ret += "\nrelease_date:\t\t" + release_date;
		ret += "\ntags: " + tags;
		ret += "\nratings: " + ratings.size() + " total ratings\n";
		ret += ratings.toString();
		return ret;
	}

	public void addAllRatings(ArrayList<Rating> ratingsToAdd) {
		ratings.addAll(ratingsToAdd);
	}
}
