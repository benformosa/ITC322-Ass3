package au.edu.csu.bformo01.ITC322.Ass3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class WeightedGraphSolver {
	private final static int INFINITY = Integer.MAX_VALUE;

	private static int closestVertex(Integer[] distance,
			HashSet<Integer> remainingVertices) {
		int closest = 0;
		int minDistance = INFINITY;
		for (int candidate : remainingVertices) {
			if (distance[candidate] < minDistance) {
				minDistance = distance[candidate];
				closest = candidate;
			}
		}
		return closest;
	}

	public static ArrayList<Path> findAllPaths(WeightedGraph w) {
		ArrayList<Path> l = new ArrayList<Path>();

		for (int i = 0; i < w.size(); i++) {
			l.add(WeightedGraphSolver.findPath(w, i));
		}
		return l;
	}

	/**
	 * Use Disktrja's Algorithm to calculate the shortest distance and path to every vertex in a graph from the given vertex.
	 * @param w WeightedGraph to work on
	 * @param source vertex to start from
	 * @return 
	 */
	public static Path findPath(WeightedGraph w, int source) {
		Integer[] distance = new Integer[w.size()];
		Integer[] previous = new Integer[w.size()];
		HashSet<Integer> remainingVertices = new HashSet<Integer>(w.size());
		HashSet<Integer> calculatedVertices = new HashSet<Integer>(w.size());

		for (int i = 0; i < distance.length; i++) {
			distance[i] = INFINITY;
			previous[i] = INFINITY;
		}
		distance[source] = 0;
		previous[source] = source;
		remainingVertices.add(source);

		while (!remainingVertices.isEmpty()) {
			int next = closestVertex(distance, remainingVertices);
			calculatedVertices.add(next);
			remainingVertices.remove(next);

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
	 * Returns an ArrayList of all the calculated paths within the WeightedGraph.
	 * 
	 * All possible results from Path.pathTo() are filtered out to leave the fewest which still describe every possible path in the graph. For example, if the path a, b exists; it will be removed if the path a, b, c also exists.
	 * 
	 * @param w WeightedGraph to map
	 * @return An ArrayList of ArrayLists representing all the calculated paths within the WeightedGraph
	 */
	public static ArrayList<ArrayList<Integer>> mapPaths(WeightedGraph w) {
		ArrayList<Path> l = WeightedGraphSolver.findAllPaths(w);
		ArrayList<ArrayList<Integer>> s =  new ArrayList<ArrayList<Integer>>();

		for (Path p : l) {
			for (int i = 0; i < w.size(); i++) {
				s.add(p.pathTo(i));
			}
		}

		ArrayList<ArrayList<Integer>> q = (ArrayList<ArrayList<Integer>>) s.clone();
		for(ArrayList<Integer> i : s) {
			if(i.size() == 0) {
				q.remove(i);
			}
			for(ArrayList<Integer> j : s) {
				if(i.containsAll(j) & i.size() > j.size()){
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
	 * @param w WeightedGraph to map
	 * @returns a String representation of all possible paths in the graph
	 */
	public static String mapPathsToString(WeightedGraph w) {
		ArrayList<ArrayList<Integer>> q = WeightedGraphSolver.mapPaths(w);
		String s = "";
		for(ArrayList<Integer> i : q) {
			s += Path.pathToToString(w, i) + System.getProperty("line.separator");
		}
		return s;
	}
}