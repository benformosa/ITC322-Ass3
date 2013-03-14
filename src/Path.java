/**
 * Holds the path taken and distances along a path through a WeightedGraph
 * 
 * 
 */
public class Path {
	public final WeightedGraph w;
	public final int[] distance;
	public final int[] previous;

	/**
	 * Create a path along a WeightedGraph
	 * @param w the WeightedGraph this Path is in
	 * @param distances an array where the distance from the start of the path to vertex n is distances[n]
	 * @param previous an array where the previous step along the path from the start to n is previous[n]
	 */
	public Path(WeightedGraph w, int[] distances, int[] previous) {
		this.w = w;
		this.distance = distances;
		this.previous = previous;
	}

/*	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < distance.length; i++) {
			s += previous[i];
			s += "(";
			s += distance[i];
			s += ")";
			s += ",";
		}
		return s;
	}*/
	
	@Override
	public String toString() {
		String s = "";
		String c = "";
		
		for (int i = 0; i < distance.length; i++) {
			
			if(w.getLabel(i) != null) {
				c = (String) w.getLabel(i);
			} else {
				c = Integer.toString(i);
			}
			
			System.out.println("source to " + c + " = " + distance[i]); 
		}
		
		for (int i = 0; i < previous.length; i++) {
			System.out.println(c + ": last hop was " + previous[i]);
		}
		
		return s;
	}
	
}