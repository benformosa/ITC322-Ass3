import java.util.HashSet;
import java.util.Set;

public class TravelPlan {
	private final static int INFINITY = Integer.MAX_VALUE;

	private static int closestCity(int[] distance, Set<Integer> remainingCities) {
		int closest = 0;
		int minDistance = INFINITY;
		for (int candidate : remainingCities) {
			if (distance[candidate] <= minDistance) {
				minDistance = distance[candidate];
				closest = candidate;
			}
		}
		return closest;
	}

	public static Path findPath(WeightedGraph w, int source, int target) {

		int[] distance = new int[w.size()];
		int[] previous = new int[w.size()];
		Set<Integer> remainingCities = new HashSet<Integer>(w.size());
		Set<Integer> calculatedCities = new HashSet<Integer>(w.size());

		for (int i = 0; i < distance.length; i++) {
			distance[i] = INFINITY;
			previous[i] = INFINITY;
		}
		distance[source] = 0;
		previous[source] = source;
		remainingCities.add(source);

		while (!remainingCities.isEmpty()) {
			int next = closestCity(distance, remainingCities);
			calculatedCities.add(next);
			remainingCities.remove(next);

			for (int v = 0; v < w.size(); v++) {
				if ((!calculatedCities.contains(v)) && w.isEdge(next, v)) {
					int sum = distance[next] + w.getWeight(next, v);
					if (sum < distance[v]) {
						distance[v] = sum;
						previous[v] = next;
					}
				}
			}
		}

		return new Path(w, distance, previous, source);
	}
}
