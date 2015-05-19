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

	public void findMin() {
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
			if (Math.min(error1, error2) == error1)
				recommender.setRating(initialRating - change);
			else
				recommender.setRating(initialRating + change);

			temp = minThree(error1, error2, min);

			System.out.println(error1 + " " + error2);

		}
		System.out.println(minRating);
	}

	public double findMin(double change) {

		double temp = 0;
		double a, b, c;
		double errorA, errorAB, errorB, errorBC, errorC, errorCA;
		while (min > temp) {
			if (temp != 0) {
				min = temp;
				System.out.println("new min " + min);
				System.out.println();
			}

			a = recommender.getA();
			b = recommender.getB();
			c = recommender.getC();

			// adding to 1 subtract from 2
			double e1 = error(a + 2 * change, b - change, c - change);
			double e2 = error(a - change, b + 2 * change, c - change);
			double e3 = error(a - change, b - change, c + 2 * change);

			// adding to 2 subtract from 1
			double e4 = error(a - 2 * change, b + change, c + change);
			double e5 = error(a + change, b - 2 * change, c + change);
			double e6 = error(a + change, b + change, c - 2 * change);
			// adding to 1 subtract from 1
			double e7 = error(a + change, b - change, c);
			double e8 = error(a + change, b, c - change);

			double e9 = error(a - change, b + change, c);
			double e10 = error(a, b + change, c - change);

			double e11 = error(a - change, b, c + change);
			double e12 = error(a, b - change, c + change);

			temp = min12(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12);

		}
		System.out.println("a:" + recommender.getA() + " b:" + recommender.getB()
				+ " c:" + recommender.getC());
		return min;
	}

	public void set(double x, double y, double z, double d, double e, double f,
			double g, double h, double i, double j, double k, double l,
			double min, double change, double a, double b, double c) {

		recommender.setAll(a, b, c);
		
		
		
		
		
		
		
		
		
		
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

	public double min12(double a, double b, double c, double d, double e,
			double f, double g, double h, double i, double j, double k, double l) {
		return minThree(minFour(a, b, c, d), minFour(e, f, g, h),
				minFour(i, j, k, l));

	}

	public double minFour(double a, double b, double c, double d) {
		if (Math.min(Math.min(a, b), Math.min(c, d)) == a)
			return a;
		if (Math.min(Math.min(a, b), Math.min(c, d)) == b)
			return b;
		else if (Math.min(c, d) == c)
			return c;
		else
			return d;

	}

	public double maxFour(double a, double b, double c, double d) {
		if (Math.max(Math.max(a, b), Math.max(c, d)) == a)
			return a;
		if (Math.max(Math.max(a, b), Math.max(c, d)) == b)
			return b;
		else if (Math.max(c, d) == c)
			return c;
		else
			return d;
	}

	public double minThree(double a, double b, double c) {
		if (Math.min(Math.min(a, b), c) == c)
			return c;
		if (Math.min(a, b) == a)
			return a;
		else
			return b;
	}

}
