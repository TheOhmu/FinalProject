
public class GradiantDecent {
	private Tester tester;
	private String path;
	private int num;
	private Recommender recomender; 
	private double min;
	
	
	public GradiantDecent(Tester t, Recommender r, String path, int num){
		tester=t;
		recomender=r;
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
			double initialRating=recomender.getRating();
			recomender.setRating(initialRating-change);
			error1= tester.crossValidate("c:\\data\\recommender\\allratings.txt", 5);
			
		
			// the lower check
			recomender.setRating(initialRating+change);
			error2= tester.crossValidate("c:\\data\\recommender\\allratings.txt", 5);
			
			
			minRating= initialRating;
			
			// changing the rating 
			if (Math.min(error1,error2)==error1) recomender.setRating(initialRating-change);
			else recomender.setRating(initialRating+change);
			
			
			temp=minThree(error1, error2, min);
			
			System.out.println(error1+" "+error2);
			
		}	
		System.out.println(minRating);
	}
	public double findMin(double change){
		
		double temp=0;
		double a, b,c;
		double errorA, errorAB, errorB, errorBC, errorC, errorCA;
		while (min!=temp){
			if (temp!=0){
				min=temp;
				System.out.println("new min "+min);
				System.out.println();
			}
			
			a=recomender.getA();
			b=recomender.getB();
			c=recomender.getC();
		
			
			
			
			
			
			
			
			
			
			// change a
			recomender.setAll(a+2*change, b-change, c-change);
			errorA=tester.crossValidate("c:\\data\\recommender\\allratings.txt", 5);
			System.out.print("error:"+ errorA);
			
			// change ab combination
			recomender.setAll(a+change, b+change, c-2*change);
			errorAB=tester.crossValidate("c:\\data\\recommender\\allratings.txt", 5);
			System.out.print(" "+ errorAB);
			
			//change b
			recomender.setAll(a-change, b+2*change, c-change);
			errorB=tester.crossValidate("c:\\data\\recommender\\allratings.txt", 5);
			System.out.println(" "+errorB);
			
			//change bc combination
			
			recomender.setAll(a-2*change, b+change, c+change);
			errorBC=tester.crossValidate("c:\\data\\recommender\\allratings.txt", 5);
			System.out.print(" "+ errorBC);
			
			// change c
			recomender.setAll(a-change,b-change, c+2*change);
			errorC=tester.crossValidate("c:\\data\\recommender\\allratings.txt", 5);
			System.out.print(" "+errorC);
		
			//change ca
			recomender.setAll(a+change, b-2*change, c+change);
			errorCA=tester.crossValidate("c:\\data\\recommender\\allratings.txt", 5);
			System.out.println(" "+ errorCA);
			
			
			double maxDiff=maxFour(min-errorA, min-errorB,min-errorC, 0);// the last one is minError-minError
			if (maxDiff==0) return min;
			if (maxDiff==(min-errorA))temp=errorA;
			else if (maxDiff==(min-errorB)) temp=errorB;
			else temp=errorC;
			
		}
		System.out.println("a:"+recomender.getA()+" b:"+recomender.getB()+" c:"+recomender.getC());
		return min;
	}
	public double error(double a, double b, double c, double change){
		recomender.setAll(a,b,c);
		double error=tester.crossValidate("c:\\data\\recommender\\allratings.txt", 5); 
		System.out.print(error+" ");
		return error;
				
	}
	public void printABC(double a, double b, double c, String increased){
		System.out.println("a ="+ a+ " b ="+ b+" c ="+ c+ " "+ increased);
		
	}

	public double minFour(double a, double b,  double c, double d){
		if (Math.min(Math.min(a,b), Math.min(c,d))==a) return a;
		if (Math.min(Math.min(a,b), Math.min(c,d))==b) return b;
		else if (Math.min(c,d)== c) return c;
		else return d;
		
		
	}
	public double maxFour(double a, double b,  double c, double d){
		if (Math.max(Math.max(a,b), Math.max(c,d))==a) return a;
		if (Math.max(Math.max(a,b), Math.max(c,d))==b) return b;
		else if (Math.max(c,d)== c) return c;
		else return d;
	}
	
	public double minThree(double a, double b, double c){
		if (Math.min(Math.min(a,b),c)==c) return c;
		if (Math.min(a, b)==a) return a;
		else return b;
	}
	

}
