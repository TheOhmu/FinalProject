
public class GradiantDecent {
	private Tester tester;
	private String path;
	private int num;
	private Recommender weights; 
	private double min;
	
	
	public GradiantDecent(Tester t, Recommender r, String path, int num){
		tester=t;
		weights=r;
		this.path=path;
		this.num=num;
		
		
	}
	public double getMin(){
		return min;
	}
	public void setMin(double min){
		this.min=min;
	}
	public void findMin(){
		double error1 = 0, error2=0;
		double change=.05;
		double minRating=0;
		double temp=0;
		while (min!=temp){
			if (temp!=0){
				min=temp;
				System.out.println("new min "+min);
				System.out.println();
			}
			// the upper check
			double weight=weights.getRating();
			weights.setRating(weight-change);
			error1= tester.crossValidate("c:\\data\\recommender\\allratings.txt", 5);
			
		
			// the lower check
			weights.setRating(weight+change);
			error2= tester.crossValidate("c:\\data\\recommender\\allratings.txt", 5);
			
			
			minRating= weight;
			
			// changing the rating 
			if (Math.min(error1,error2)==error1) weights.setRating(weight-change);
			else weights.setRating(weight+change);
			
			
			temp=minThree(error1, error2, min);
			
			System.out.println(error1+" "+error2);
			
		}	
		System.out.println(minRating);
	}
	
	
	public double minThree(double a, double b, double c){
		if (Math.min(Math.min(a,b),c)==c) return c;
		if (Math.min(a, b)==a) return a;
		else return b;
	}
	

}
