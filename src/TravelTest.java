import java.io.IOException;

public class TravelTest {

	public static void main(String[] args) throws IOException {
		
		WeightedGraph w = WeightedGraphFactory.fromFile("distance1.txt", false);
		
		w = WeightedGraphFactory.labelFromFile(w, "index1.txt");
	
		System.out.println("find path 0 -> 4");
		Path p = TravelPlan.findPath(w, 0, 1);
		System.out.println(p);
		
	}
}