package au.edu.csu.bformo01.ITC322.Ass3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class WeightedGraphSolver {
	private final static int INFINITY = Integer.MAX_VALUE;

	private static int closestVertex(Integer[] distance,
			Set<Integer> remainingVertices) {
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

	public static Path findPath(WeightedGraph w, int source) {
		Integer[] distance = new Integer[w.size()];
		Integer[] previous = new Integer[w.size()];
		Set<Integer> remainingVertices = new HashSet<Integer>(w.size());
		Set<Integer> calculatedVertices = new HashSet<Integer>(w.size());

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
	
	public static ArrayList<Path> findAllPaths(WeightedGraph w) {
		ArrayList<Path> l = new ArrayList<Path>();
		
		for(int i = 0; i < w.size(); i++){
			l.add(WeightedGraphSolver.findPath(w, i));
		}
		return l;		
	}
}
