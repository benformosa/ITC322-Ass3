import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Class with functions to work on a WeightedGraph, such as finding the shortest
 * distance and path between vertices
 * 
 * @author Ben Formosa, Student No. 11429074
 */
public class WeightedGraphSolver {
	/**
	 * the highest possible value of int. This is suitable to use as infinity
	 * for most WeightedGraphs
	 */
	private final static int INFINITY = Integer.MAX_VALUE;

	/**
	 * Given an array of distances from the source and a Set of vertices, return
	 * the vertex in the set with the lowest distance
	 * 
	 * @param distance
	 *            int[] where distance[n] is the distance from the source to
	 *            vertex n
	 * @param vertices
	 *            Set of vertexes to consider. vertices not in this set are
	 *            ignored
	 * @return index of the closest vertex
	 */
	private static int closestVertex(Integer[] distance, Set<Integer> vertices) {
		int closest = 0;
		int minDistance = INFINITY;
		for (int candidate : vertices) {
			// if the distance to the vertex is less the current lowest
			// distance, update the current lowest distance
			if (distance[candidate] < minDistance) {
				minDistance = distance[candidate];
				closest = candidate;
			}
		}
		return closest;
	}

	/**
	 * Calculate all possible paths in a WeightedGraph.
	 * 
	 * The path from every vertex to every other vertex is found using
	 * WeightedGraphSolver.findPath()
	 * 
	 * @param w
	 *            WeightedGraph to calculate
	 * @return ArrayList of all possible Paths
	 */
	public static ArrayList<Path> findAllPaths(WeightedGraph w) {
		ArrayList<Path> l = new ArrayList<Path>();

		for (int i = 0; i < w.size(); i++) {
			l.add(WeightedGraphSolver.findPath(w, i));
		}
		return l;
	}

	/**
	 * Use Disktrja's Algorithm to calculate the shortest distance and path to
	 * every vertex in a graph from the given vertex.
	 * 
	 * @param w
	 *            WeightedGraph to work on
	 * @param source
	 *            vertex to start from
	 * @return
	 */
	public static Path findPath(WeightedGraph w, int source) {
		// array to hold distance to each vertex, distance[n] is the distance
		// from source to n
		Integer[] distance = new Integer[w.size()];
		// array to hold the previous vertex for each vertex, previous[n] is the
		// last vertex on the path to n on the path from source
		Integer[] previous = new Integer[w.size()];
		// set of vertices we can try to reach
		HashSet<Integer> remainingVertices = new HashSet<Integer>(w.size());
		// set of vertices we know distances to
		HashSet<Integer> calculatedVertices = new HashSet<Integer>(w.size());

		// initialise the arrays to INFINITY, except for source
		for (int i = 0; i < distance.length; i++) {
			distance[i] = INFINITY;
			previous[i] = INFINITY;
		}
		distance[source] = 0;
		previous[source] = source;
		remainingVertices.add(source);

		while (!remainingVertices.isEmpty()) {
			// consider the next closest vertex to the source
			int next = closestVertex(distance, remainingVertices);
			calculatedVertices.add(next);
			remainingVertices.remove(next);

			// update distance and previous with the new hop
			for (int v = 0; v < w.size(); v++) {
				if ((!calculatedVertices.contains(v)) && w.isEdge(next, v)) {
					int sum = distance[next] + w.getWeight(next, v);
					if (sum < distance[v]) {
						distance[v] = sum;
						previous[v] = next;
						remainingVertices.add(v);
					}
				}
			}
		}
		return new Path(w, distance, previous, source);
	}

	/**
	 * Returns an ArrayList of all the calculated paths within the
	 * WeightedGraph.
	 * 
	 * All possible results from Path.pathTo() are filtered out to leave the
	 * fewest which still describe every possible path in the graph. For
	 * example, if the path a, b exists; it will be removed if the path a, b, c
	 * also exists.
	 * 
	 * @param w
	 *            WeightedGraph to map
	 * @return An ArrayList of ArrayLists representing all the calculated paths
	 *         within the WeightedGraph
	 */
	public static ArrayList<ArrayList<Integer>> mapPaths(WeightedGraph w) {
		ArrayList<Path> l = WeightedGraphSolver.findAllPaths(w);
		ArrayList<ArrayList<Integer>> s = new ArrayList<ArrayList<Integer>>();

		// populate s with the pathTo every vertex
		for (Path p : l) {
			for (int i = 0; i < w.size(); i++) {
				s.add(p.pathTo(i));
			}
		}

		// we know that s is the correct type, so suppress warnings. We need to
		// cast as clone() returns an Object
		@SuppressWarnings("unchecked")
		// copy s to a new variable. we want to remove elements in the loop
		// below, but can't operate on the iterable we are iterating over
		ArrayList<ArrayList<Integer>> q = (ArrayList<ArrayList<Integer>>) s
				.clone();
		// loop over all possible paths
		for (ArrayList<Integer> i : s) {
			// remove all paths without vertices, ie paths to targets that
			// aren't reached
			if (i.size() == 0) {
				q.remove(i);
			}
			// if any path is contained within the current path, remove that
			// path.
			for (ArrayList<Integer> j : s) {
				if (i.containsAll(j) & i.size() > j.size()) {
					q.remove(j);
				}
			}
		}
		return q;
	}

	/**
	 * Return a String representation of mapPaths.
	 * 
	 * Uses the String representation of paths from Path.PathToToString
	 * 
	 * @param w
	 *            WeightedGraph to map
	 * @returns a String representation of all possible paths in the graph
	 */
	public static String mapPathsToString(WeightedGraph w) {
		ArrayList<ArrayList<Integer>> q = WeightedGraphSolver.mapPaths(w);
		String s = "";
		// for each path, convert it to a String, append it to s, append a
		// newline
		for (ArrayList<Integer> i : q) {
			s += Path.pathToToString(w, i)
					+ System.getProperty("line.separator");
		}
		return s;
	}
}