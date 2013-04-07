import java.io.IOException;

public class TravelTest {

	public static void main(String[] args) throws IOException {

		WeightedGraph w = WeightedGraphFactory.fromFile("distance1.txt", false);

//		w = WeightedGraphFactory.labelFromFile(w, "index1.txt");
		
		int source;
		int target;
//		source = w.labelToIndex("Selim");
//		target = w.labelToIndex("Cup");
		
		source = 0;
		target = 2;

		Path p = TravelPlan.findPath(w, source, target);
		System.out.println(p);
		System.out.println(p.distanceTo(target));
		System.out.println();
		System.out.println(p.pathTo(target));

	}
}