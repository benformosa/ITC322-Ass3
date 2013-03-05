import java.io.IOException;

public class TravelTest {

	public static void main(String[] args) throws IOException {
		TravelPlan t = new TravelPlan("distance1.txt");

		//System.out.println(t.isEdge(0, 4));
		//System.out.println(t.isEdge(4, 0));
	
		System.out.println("find path 4 -> 0");
		Path p = t.findPath(4, 0);
		System.out.println(p);
	}

}