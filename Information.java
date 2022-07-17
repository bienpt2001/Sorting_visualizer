

//	private String[] algInfo = {"Best Case: O(n^2)\nWorst Case: O(n^2)\nAverage: O(n^2)",
//								"Best Case: O(n)\nWorst Case: O(n^2)\nAverage: O(n^2)",
//								"Best Case: O(n)\nWorst Case: O(n^2)\nAverage: O(n^2)",
//								"Best Case: O(n)\nWorst Case: O(n^2)\nAverage: O(n^2)",
//								"Best Case: O(n)\nWorst Case: O(n^2)\nAverage: O(n^2)",
//								};
public class Information {
	private String[] BestTimeComplexity  = {"Ω(n)","Ω(n)","Ω(n log(n))","Ω(n log(n))","Ω(n log(n))"}; 
	private String[] AverageTimeComplexity = {"θ(n^2)","θ(n^2)","θ(n log(n))","θ(n log(n))","θ(n log(n))"} ;
	private String[] WorstTimeComplexity = {"O(n^2)","O(n^2)","O(n^2)","O(n log(n))","O(n log(n))"};
	private String[] SpaceComplexity = {"O(1)","O(1)","O(n)","O(log(n))","O(n)"};
	
	
	public String getBestTimeComplexity(int id) {
		return this.BestTimeComplexity[id];
	}
	public String getWorstTimeComplexity(int id) {
		return this.WorstTimeComplexity[id];
	}
	public String getAverageTimeComplexity(int id) {
		return this.AverageTimeComplexity[id];
	}
	public String getSpaceComplexity(int id) {
		return this.SpaceComplexity[id];
	}
	
	Information(){
		
	}
}
