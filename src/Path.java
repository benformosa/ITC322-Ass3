/**
 * Holds the path taken and distances along a path through a WeightedGraph
 * 
 * 
 */
public class Path {
	public final WeightedGraph w;
	public final int[] distance;
	public final int[] previous;
	public final int source;

	/**
	 * Create a path along a WeightedGraph
	 * 
	 * A Path is the result of determining the distance from one vertex (the source) in a WeightedGraph to all other vertices. 
	 * 
	 * @param w
	 *            the WeightedGraph this Path is in
	 * @param distances
	 *            an array where the distance from the start of the path to
	 *            vertex n is distances[n]
	 * @param previous
	 *            an array where the previous step along the path from the start
	 *            to n is previous[n]
	 * @param source the vertex from which distances are measured
	 */
	public Path(WeightedGraph w, int[] distances, int[] previous, int source) {
		this.w = w;
		this.distance = distances;
		this.previous = previous;
		this.source = source;
	}
	
	public int distanceTo(int target) {
		return this.distance[target];
	}
	
	public int[] pathTo(int target) {
		int[] p = {0};
		
		int v = target;
		while(v != source) {
			System.out.println(v);
			v = previous[v];
		}
		return p;
	}

	private String tidyLabel(Object o) {
		String c = "";

		try {
			c = o.toString();
		} catch (ClassCastException e) {
		}

		if (o.getClass().isAssignableFrom(int.class)) {
			c = Integer.toString((int) o);
		}
		return c;
	}

	@Override
	public String toString() {
		String s = "";

		for (int i = 0; i < distance.length; i++) {
			System.out.println("source to " + tidyLabel(w.getLabel(i)) + " = "
					+ distance[i]);
		}

		for (int i = 0; i < previous.length; i++) {
			try {
				System.out.println(tidyLabel(w.getLabel(i)) + ": last hop was "
					+ tidyLabel(w.getLabel(previous[i])));
			} catch(ArrayIndexOutOfBoundsException e) {
				System.out.println(tidyLabel(w.getLabel(i)) + " was never visited");
			}
		}

		return s;
	}

	public String toStringSimple() {
		String s = "";
		for (int i = 0; i < distance.length; i++) {
			s += i;
			s += ":";
			s += previous[i];
			s += "(";
			s += distance[i];
			s += ")";
			s += ",";
		}
		return s;
	}
}