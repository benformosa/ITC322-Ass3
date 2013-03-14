import java.io.IOException;

public class TravelTest {

	public static void main(String[] args) throws IOException {
		
		WeightedGraph w = WeightedGraphFactory.fromFile("distance1.txt");
	
		System.out.println("find path 4 -> 0");
		Path p = TravelPlan.findPath(w, 4, 1);
		System.out.println(p);
		
	}
}