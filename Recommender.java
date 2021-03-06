import java.util.ArrayList;
import java.util.HashMap;

public class Recommender {

	Tester tester;
	private double system;
	private double weight1, weight2, weight3;
	private HashMap<String, Double> genres = new HashMap<String, Double>();
	private double rating;

	public Recommender(Tester t) {
		tester = t;
		weight1 = 1.0/3;
		weight2 = 1.0/3;
		weight3 = 1.0/3;
		rating = 2.5;
		system=1;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public double getRating() {
		return rating;
	}

	// This is automatically run before any tests. You may use this method to do
	// any calculations you need to support your predictRating method.

	// NOTE: to avoid cheating you must re-set any variables you use here
	// before you calculate their new values.
	// calculate stuff, with this data
	public void setAll(double a, double b, double c) {
		this.weight1 = a;
		this.weight2 = b;
		this.weight3 = c;

	}
	

	public void setSystem(double system) {
		this.system = system;
		if (system==2) setAll(.95,.05,0);
	}

	public double getWeight1() {
		return weight1;
	}

	public double getWeight2() {
		return weight2;
	}

	public double getWeight3() {
		return weight3;
	}

	public void init() {
		genres.put("Unknown", averageGenre(fillGenre("Unknown")));
		genres.put("Action", averageGenre(fillGenre("Action")));
		genres.put("Adventure", averageGenre(fillGenre("Adventure")));
		genres.put("Animation", averageGenre(fillGenre("Animation")));
		genres.put("Children's", averageGenre(fillGenre("Children's")));
		genres.put("Comedy", averageGenre(fillGenre("Comedy")));
		genres.put("Crime", averageGenre(fillGenre("Crime")));
		genres.put("Documentary", averageGenre(fillGenre("Documentary")));
		genres.put("drama", averageGenre(fillGenre("Drama")));
		genres.put("Fantasy", averageGenre(fillGenre("Fantasy")));
		genres.put("Film-Noir", averageGenre(fillGenre("Film-Noir")));
		genres.put("Horror", averageGenre(fillGenre("Horror")));
		genres.put("Musical", averageGenre(fillGenre("Musical")));
		genres.put("Mystery", averageGenre(fillGenre("Mystery")));
		genres.put("Romance", averageGenre(fillGenre("Romance")));
		genres.put("SciFi", averageGenre(fillGenre("Sci-Fi")));
		genres.put("Thriller", averageGenre(fillGenre("Thriller")));
		genres.put("War", averageGenre(fillGenre("War")));
		genres.put("Western", averageGenre(fillGenre("Western")));
	}

	public ArrayList<Movie> fillGenre(String s) {
		ArrayList<Movie> genre = new ArrayList<Movie>();
		for (Movie m : tester.getAllMovies())
			for (String other : m.getTags()) {
				if (s.equals(other))
					genre.add(m);
			}
		return genre;
	}

	public double averageGenre(ArrayList<Movie> all) {
		int sum = 0;
		double total = 0;
		for (Movie m : all) {
			sum++;
			total += getAverage(m.getRatings());
		}
		if (sum != 0)
			return total / sum;
		else
			return magicRating;

	}

	public double averageRatings() {
		double sum = 0.0;
		int total = 0;
		for (Rating r : tester.getTrainingRatings()) {
			if (r != null) {
				sum += r.getRating();
				total++;
			}
		}
		if (total != 0)
			return sum / total;
		return magicRating;
	}

	// edit the thing all the time
	public double predictRating(Person person, Movie movie) {
		if (system!=1)return weight1*averageP(person)+weight2*averageM(movie)+weight3*averageGenre(movie);
		else return rating;
	}

	public double getAverage(ArrayList<Rating> a) {
		int sum = 0;
		double total = 0;
		for (Rating r : a) {
			total += r.getRating();
			sum++;
		}
		if (sum != 0)
			return total / sum;
		else
			return magicRating;

	}

	public double averageP(Person p) {
		return getAverage(p.getRatings());
	}

	public double averageM(Movie m) {
		return getAverage(m.getRatings());
	}

	public double averageGenre(Movie m) {
		int sum = 0;
		double total = 0;
		for (String s : m.getTags()) {
			if (genres.containsKey(s)) {
				total += genres.get(s);
				sum++;
			}
		}
		if (sum != 0)
			return total / sum;
		else
			return magicRating;

	}

	public final double magicRating = 3.55;
	public final int child = -1;
	public final int teen = 0;
	public final int youngAdult = 1;
	public final int adult = 2;
	public final int senior = 3;
	public final double THRESHOLD = .50;

}
