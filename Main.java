
public class Main {
	public static void main(String[] args) {
		Tester tester = new Tester();
		Recommender r = new Recommender(tester);
		tester.setRecommender(r);
		GradiantDecent method= new GradiantDecent(tester, r,"c:\\data\\recommender\\allratings.txt", 5);
		// ------------====== (1) LOAD DATA ======---------------------------------
		// Method #1:  for this to work your files must be in c:\\data
		tester.loadDataFromFile();
		
		// Method #2:  uncomment the line below and specify your own file paths.
		// tester.loadDataFromFile("c:\\your_user_file_path",
		// 						   "c:\\your_movie_file_path");
		// ------------------------------------------------------------------------
		// ------------====== (2) RUN TESTS ======---------------------------------
		// Note:  do not change the 5.  This controls how many test groups the validator 
		// separates the data into.
		// tester.DISPLAY = true;			// Display predictions you got wrong
		
		tester.threshold = 0;					// How far off does your predictor have to be to display
		method.setMin(tester.crossValidate("c:\\data\\recommender\\allratings.txt", 5));															// a prediction as incorrect?
		
		
		double error= findMin(1, method);
		// -------------------------------------------------------------------------
		
		System.out.println("Your error is: " + error);
		System.out.println("(Since it's error, smaller is better.)");
	}
	public static double findMin(int type, GradiantDecent method){
		
		if (type==rating){// only adjusting the rating 
			method.findMinRating();
			
		}
		else if (type==weight){// adjusting 2 weights
			method.findMinOfTwo(.025);
			
		}
		else{// adjusting 3 weights
			method.findMinOfThree(.025);
		}
		
		
		return method.getMin();
	}
	public static int rating=1;
	public static int weight=2;
	
}
