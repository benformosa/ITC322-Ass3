import java.io.IOException;

public class TravelTest {

	public static void main(String[] args) throws IOException {

		WeightedGraph w = WeightedGraphFactory.fromFile("distance.txt", false);

		 w = WeightedGraphFactory.labelFromFile(w, "index.txt");

		System.out.println("find path 0 -> 18");
		Path p = TravelPlan.findPath(w, 0, 18);
		System.out.println(p.toStringSimple());
		System.out.println(p);

	}
}