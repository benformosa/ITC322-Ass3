import java.util.HashSet;
import java.util.Set;

class TravelPlan {
	/**
	 * This int represents infinity. The textbook suggests use of -1 as
	 * infinity, however while this would be ideal, it is far simpler to use the
	 * highest possible value of an int to represent infinity. This should be
	 * fine for all but ludicrously huge Graphs.
	 */
	private final static int INFINITY = Integer.MAX_VALUE;

	/**
	 * Find the shortest path between two cities, given a WeightedGraph.
	 * @param w WeightedGraph containing the cities as vertices, and the edges as paths between them. The weight of an edge represents the distance.
	 * @param source index of the source city
	 * @param target index of the destination city
	 * @return a Path holding the shortest path between source and target
	 */
	public static Path findPath(WeightedGraph w, int source, int target) {
		// array holding previous step in the shortest path
		int[] previous = new int[w.size()];
		// array holding distances from the source
		int[] distance = new int[w.size()];

		//set the initial values for distances
		for (int i = 1; i < w.size(); i++) {
			distance[i] = INFINITY;
		}
		distance[source] = 0;

		Set<Integer> allowedCities = new HashSet<Integer>(w.size());

		for (int allowedSize = 1; allowedSize < w.size(); allowedSize++) {
			
			//set next to the closest city to the source
			int minDistance = INFINITY;
			int next = source;
			for (int candidate = 0; candidate < w.size(); candidate++) {

				System.err.println(" " + candidate + ": is source = " + (candidate == source) + ", weight = " + w.getWeight(source, candidate) + ", edge = " + (w.isEdge(source, candidate) || w.isEdge(candidate, source)) + " allowed = "  + (allowedCities.contains(candidate)));

				if (candidate != source && distance[candidate] < minDistance && (w.isEdge(source, candidate) || w.isEdge(candidate, source)) && (!allowedCities.contains(candidate))) {
					minDistance = distance[candidate];
					next = candidate;
				}
			}
			allowedCities.add(next);

			System.err.println(allowedSize + ": closest city is: " + next);
			System.err.println(allowedCities);
			
			if (distance[next] == INFINITY || target == next) {
				System.err.println("reached end");
				return new Path(distance, previous);
			}

			//update the array of distances
			for (int j = 0; j < w.size(); j++) {
				if((!allowedCities.contains(j)) && (w.isEdge(next, j) || w.isEdge(j, next))) {
					int sum = distance[next] + w.getWeight(j, next) + w.getWeight(next, j);
					if (sum < distance[j]) {
						distance[j] = sum;
						previous[j] = next;

						System.err.println("distance[" + j + "] is now " + distance[j]);
						System.err.println("previous[" + j + "] is now " + previous[j]);
					}
				}
			}
		}
		
		for( int i : distance) {
			System.err.print(i + ", ");
		}
		System.err.println("\n");
		
		for( int i : previous) {
			System.err.print(i + ", ");
		}
		System.err.println("\n");
		
		return new Path(distance, previous);
	}
}