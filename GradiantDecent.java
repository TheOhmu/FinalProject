public class GradiantDecent {
	private Tester tester;
	private String path;
	private int num;
	private Recommender recommender;
	private double min;

	public GradiantDecent(Tester t, Recommender r, String path, int num) {
		tester = t;
		recommender = r;
		this.path = path;
		this.num = num;

	}

	public double getMin() {
		return min;
	}

	public void setMin(double min) {
		this.min = min;
	}

	public void findMinRating() {
		double error1 = 0, error2 = 0;
		double change = .05;
		double minRating = 0;
		double temp = 0;
		while (min != temp) {
			if (temp != 0) {
				min = temp;
				System.out.println("new min " + min);
				System.out.println();
			}
			
			// the upper check
			double initialRating = recommender.getRating();
			recommender.setRating(initialRating - change);
			error1 = tester.crossValidate(
					"c:\\data\\recommender\\allratings.txt", 5);

			// the lower check
			recommender.setRating(initialRating + change);
			error2 = tester.crossValidate(
					"c:\\data\\recommender\\allratings.txt", 5);

			minRating = initialRating;

			// changing the rating
			if (Math.min(error1, error2) == error1) recommender.setRating(initialRating - change);
			else recommender.setRating(initialRating + change);

			temp = minThree(error1, error2, min);

			System.out.println("rating: "+ (initialRating-change)+" error: "+error1+ "DOWN"); 
			System.out.println(	"rating: "+ (initialRating+change)+" error: "+error2+"UP");

		}
		System.out.println(minRating);
	}
	public double findMinOfTwo(double change){
		int temp=0;
		double weight1, weight2;
		while (min>temp){
			if (temp!=0){
				min=temp;
				System.out.println("new min:"+min+"/n");
			}
			weight1=recommender.getWeight1();
			weight2=recommender.getWeight2();
			
			
			
			
			
			
		}
		
		
		
		
		
		
		
		
		
		return 0;
	}

	public double findMinOfThree(double change) {

		double temp = 0;
		double weight1, weight2, weight3;

		while (min > temp) {
			if (temp != 0) {
				min = temp;
				System.out.println("new min " + min);
				System.out.println();
			}

			weight1 = recommender.getWeight1();
			weight2 = recommender.getWeight2();
			weight3 = recommender.getWeight3();

			// adding to 1 subtract from 2
			double e1 = error(weight1 + 2 * change, weight2 - change, weight3 - change);
			double e2 = error(weight1 - change, weight2 + 2 * change, weight3 - change);
			double e3 = error(weight1 - change, weight2 - change, weight3 + 2 * change);
			System.out.println();
			
			// adding to 2 subtract from 1
			double e4 = error(weight1 - 2 * change, weight2 + change, weight3 + change);
			double e5 = error(weight1 + change, weight2 - 2 * change, weight3 + change);
			double e6 = error(weight1 + change, weight2 + change, weight3 - 2 * change);
			System.out.println();
			
			// adding to 1 subtract from 1
			double e7 = error(weight1 + change, weight2 - change, weight3);
			double e8 = error(weight1 + change, weight2, weight3 - change);
			double e9 = error(weight1 - change, weight2 + change, weight3);
			double e10 = error(weight1, weight2 + change, weight3 - change);
			double e11 = error(weight1 - change, weight2, weight3 + change);
			double e12 = error(weight1, weight2 - change, weight3 + change);
			System.out.println();
			
			temp = min12(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12);

		}
		System.out.println("a:" + recommender.getWeight1() + " b:" + recommender.getWeight2()
				+ " c:" + recommender.getWeight3());
		return min;
	}
	

	public void set(double e1, double e2, double e3, double e4, double e5, double e6,
			double e7, double e8, double e9, double e10, double e11, double e12,
			double min, double change, double weight1, double weight2, double weight3) {

		
		if(min == e1) 	recommender.setAll(weight1 + 2 * change, weight2 - change, weight3 - change);
		if(min == e2) 	recommender.setAll(weight1 - change, weight2 + 2 * change, weight3 - change);
		if(min == e3) 	recommender.setAll(weight1 - change, weight2 - change, weight3 + 2 * change);
		if(min == e4) 	recommender.setAll(weight1 - 2 * change, weight2 + change, weight3 + change);
		if(min == e5)	recommender.setAll(weight1 + change, weight2 - 2 * change, weight3 + change);
		if(min == e6) 	recommender.setAll(weight1 + change, weight2 + change, weight3 - 2 * change);
		if(min == e7) 	recommender.setAll(weight1 + change, weight2 - change, weight3);
		if(min == e8) 	recommender.setAll(weight1 + change, weight2, weight3 - change);
		if(min == e9) 	recommender.setAll(weight1 - change, weight2 + change, weight3);
		if(min == e10) 	recommender.setAll(weight1, weight2 + change, weight3 - change);
		if(min == e11) 	recommender.setAll(weight1 - change, weight2, weight3 + change);
		if(min == e12) 	recommender.setAll(weight1, weight2 - change, weight3 + change);
	}

	public double error(double a, double b, double c) {
		recommender.setAll(a, b, c);
		double error = tester.crossValidate(
				"c:\\data\\recommender\\allratings.txt", 5);
		System.out.print(error + " ");
		return error;

	}

	public void printABC(double a, double b, double c, String increased) {
		System.out.println("a =" + a + " b =" + b + " c =" + c + " "
				+ increased);

	}

	public double min12(double e1, double e2, double e3, double e4, double e5,
			double e6, double e7, double e8, double e9, double e10, double e11, double e12) {
		return minThree(minFour(e1, e2, e3, e4), minFour(e5, e6, e7, e8),
				minFour(e9, e10, e11, e12));

	}

	public double minFour(double e1, double e2, double e3, double e4) {
		if (Math.min(Math.min(e1, e2), Math.min(e3, e4)) == e1)	return e1;
		if (Math.min(Math.min(e1, e2), Math.min(e3, e4)) == e2)	return e2;
		else if (Math.min(e3, e4) == e3)						return e3;
		else													return e4;

	}

	public double maxFour(double e1, double e2, double e3, double e4) {
		if (Math.max(Math.max(e1, e2), Math.max(e3, e4)) == e1)	return e1;
		if (Math.max(Math.max(e1, e2), Math.max(e3, e4)) == e2)	return e2;
		else if (Math.max(e3, e4) == e3)						return e3;
		else													return e4;
	}

	public double minThree(double e1, double e2, double e3) {
		if (Math.min(Math.min(e1, e2), e3) == e3)				return e3;
		if (Math.min(e1, e2) == e1)								return e1;
		else													return e2;
	}

}
//gradient descent equation from all the data points?