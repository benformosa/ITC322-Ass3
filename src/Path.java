/**
 * Holds the path taken and distances along a path through a WeightedGraph
 */
public class Path {
	public final int[] distances;
	public final int[] previous;

	public Path(int[] distances, int[] previous) {
		this.distances = distances;
		this.previous = previous;
	}

	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < distances.length; i++) {
			s += previous[i];
			s += "(";
			s += distances[i];
			s += ")";
			s += ",";
		}
		return s;
	}
}