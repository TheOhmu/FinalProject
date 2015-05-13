import java.util.ArrayList;
import java.util.HashMap;

public class Person {
	private int id, age, gender;
	private String occupation, zip;
	private ArrayList<Rating> ratings;
	

	public Person(int id, int age, int gender, String occupation, String zip) {
		this.id = id;
		this.age = age;
		this.gender = gender;
		this.occupation = occupation;
		this.zip = zip;
		ratings = new ArrayList<Rating>();
		

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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public void addRating(Rating r) {
		ratings.add(r);
	}

	public void clearRatings() {
		ratings.clear();
	}

	public void removeRatings(ArrayList<Rating> ratingsToRemove) {
		// System.out.print("For: " + id + " before: " + ratings.size());
		ratings.removeAll(ratingsToRemove);
		// System.out.println(" after: " + ratings.size());
	}

	public String toString() {
		String ret = "id:\t\t" + id;
		ret += "\nage:\t\t" + age;
		ret += "\ngender:\t\t" + gender;
		ret += "\noccupation:\t\t" + occupation;
		ret += "\nzip_code:\t\t" + zip;
		ret += "\nratings: " + ratings.size() + " total ratings\n";
		ret += ratings.toString();

		return ret;
	}

	public void addAllRatings(ArrayList<Rating> ratingsToAdd) {
		ratings.addAll(ratingsToAdd);
	}
}
