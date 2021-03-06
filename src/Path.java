import java.util.ArrayList;
import java.util.Collections;

/**
 * Holds the path taken and distances along a path through a WeightedGraph
 * 
 * As a path is calculated from a source vertex to each other vertex, we only
 * need to store the source vertex.
 * 
 * @author Ben Formosa, Student No. 11429074
 */
public class Path {
	/**
	 * pathToToString without attempting to use labels
	 * 
	 * @param p
	 *            an ArrayList containing the vertices on the path, Usually the
	 *            output from Path.pathTo()
	 * @return a String with each vertex on the path's index, separated with ->
	 */
	public static String pathToToString(ArrayList<Integer> p) {
		return Path.pathToToString(null, p);
	}

	/**
	 * A string representation of the path from the source to a given vertex.
	 * 
	 * @param w
	 *            the WeightedGraph used in calculating the path
	 * @param p
	 *            an ArrayList containing the vertices on the path, Usually the
	 *            output from Path.pathTo()
	 * @return a String with each vertex on the path's label using
	 *         WeightedGraph.getStringLabel(), separated with ->
	 */
	public static String pathToToString(WeightedGraph w, ArrayList<Integer> p) {
		String s = "";
		String sep = " -> ";
		for (int i : p) {
			// don't get the label if w is null, ie if we are running
			// pathToToString(ArrayList<Integer> p)
			if (w != null) {
				s += w.getStringLabel(i);
			} else {
				s += Integer.toString(i);
			}
			s += sep;
		}
		// get rid of that last separator
		s = s.replaceAll(sep + "$", "");
		return s;
	}

	public final Integer[] distance;
	public final Integer[] previous;

	public final int source;

	public final WeightedGraph w;

	/**
	 * Create a path along a WeightedGraph
	 * 
	 * A Path is the result of determining the distance from one vertex (the
	 * source) in a WeightedGraph to all other vertices.
	 * 
	 * @param w
	 *            the WeightedGraph this Path is in
	 * @param distances
	 *            an array where the distance from the start of the path to
	 *            vertex n is distances[n]
	 * @param previous
	 *            an array where the previous step along the path from the start
	 *            to n is previous[n]
	 * @param source
	 *            the vertex from which distances are measured
	 */
	public Path(WeightedGraph w, Integer[] distances, Integer[] previous,
			int source) {
		this.w = w;
		this.distance = distances;
		this.previous = previous;
		this.source = source;
	}

	/**
	 * The distance from the source to a given vertex
	 * 
	 * @param target
	 *            vertex to find distance to
	 * @return distance to target
	 */
	public int distanceTo(int target) {
		return this.distance[target];
	}

	/**
	 * Returns an ArrayList with the traversed vertices on the path from the
	 * source to a given vertex
	 * 
	 * If l is an ArrayList returned from this method, l.get(0) will give the
	 * source, l.get(l.size()) will give target If the target is not in the
	 * path, the returned ArrayList will be empty
	 * 
	 * @param target
	 *            vertex to find the path to
	 * @return ArrayList with vertices on the path to target
	 */
	public ArrayList<Integer> pathTo(int target) {
		ArrayList<Integer> l = new ArrayList<Integer>();

		// starting at the target, walk through the previous array until we
		// reach the source
		int v = target;
		l.add(v);
		while (v != source) {
			try {
				v = previous[v];
				l.add(v);
			} catch (ArrayIndexOutOfBoundsException e) {
				// if previous[v] doesn't exist, return an empty ArrayList
				// immediately
				return new ArrayList<Integer>();
			}
		}
		Collections.reverse(l);
		return l;
	}

	/**
	 * String representation of the path.
	 * 
	 * First prints the distance from the source to each vertex, then each city
	 * and the previous city to it on the path from the source
	 */
	@Override
	public String toString() {
		String s = "";

		for (int i = 0; i < distance.length; i++) {
			System.out.println(w.getStringLabel(source) + " to "
					+ w.getStringLabel(i) + " = " + distance[i]);
		}

		for (int i = 0; i < previous.length; i++) {
			try {
				System.out.println(w.getStringLabel(i) + ": last hop was "
						+ w.getStringLabel(previous[i]));
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println(w.getStringLabel(i) + " was never visited");
			}
		}
		return s;
	}
}