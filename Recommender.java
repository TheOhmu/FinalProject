import java.util.ArrayList;
import java.util.HashMap;

public class Recommender {

	Tester tester;
	private double weightOfPerson;
	private double otherWeight;
	private double allMovieAverage;
	private double allPersonAverage;
	private double rating;
	
	

	public Recommender(Tester t) {
		tester = t;
		weightOfPerson=.50;
		otherWeight=1-weightOfPerson;
		rating=2.5;
	}
	public void setWeights(double weightOfPerson){
		this.weightOfPerson=weightOfPerson;
		otherWeight=1-weightOfPerson;
	}
	public void setRating(double rating){
		this.rating= rating;
	}
	public double getRating(){
		return rating;
	}
	// This is automatically run before any tests. You may use this method to do
	// any calculations you need to support your predictRating method.

	// NOTE: to avoid cheating you must re-set any variables you use here
	// before you calculate their new values.
	// calculate stuff, with this data

	public void init() {
	
			
	}
		
		
	public double averageRatings(){
		double sum=0.0;
		int total=0;
		for (Rating r:tester.getTrainingRatings()){
			if (r!=null){
				sum+=r.getRating();
				total++;
			}
		}
		if (total!=0) return sum/total;
		return magicRating;
	}

	// edit the thing all the time
	public double predictRating(Person person, Movie movie) {
		return rating;
	}

public final double magicRating = 3.5;	
public final int child = -1;
public final int teen = 0;
public final int youngAdult = 1;
public final int adult = 2;
public final int senior = 3;
public final double THRESHOLD = .50;
	
}	
	
	