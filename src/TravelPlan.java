import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * TravelPlan is a subclass of WeightedGraph that stores a Graph of cities, and
 * solves paths between them
 */
class TravelPlan extends WeightedGraph {

	/**
	 * Read the given file and return the number of cities
	 */
	private static int readCities(String filename) throws IOException,
	FileNotFoundException {
		FileReader f = new FileReader(filename);
		BufferedReader br = new BufferedReader(f);
		// read the number of cities from the first line in the file
		return Integer.parseInt(br.readLine());
	}

	/**
	 * This int represents infinity. The textbook suggests use of -1 as
	 * infinity, however while this would be ideal, it is far simpler to use the
	 * highest possible value of an int to represent infinity. This should be
	 * fine for all but ludicrously huge Graphs.
	 */
	private final int INFINITY = 2147483647;

	/**
	 * The number of cities in this TravelPlan
	 */
	private int cities;

	public TravelPlan(String filename) throws IOException,
	FileNotFoundException, NumberFormatException {
		super(TravelPlan.readCities(filename));
		this.cities = TravelPlan.readCities(filename);

		System.out.println("cities: " + this.cities);

		FileReader f = new FileReader(filename);
		BufferedReader br = new BufferedReader(f);
		String line;

		// burn the first line
		line = br.readLine();

		// loop until an empty line or EOF
		while ((line = br.readLine()) != null && !("".equals(line))) {

			System.out.println(line);

			// add each path this TravelPlan as an edge, with the weight being
			// the distance between the cities
			this.addEdge(Integer.parseInt(line.split(" ")[0]),
					Integer.parseInt(line.split(" ")[1]),
					Integer.parseInt(line.split(" ")[2]));
		}
	}

	/**
	 * Find the shortest path between two cities.
	 * 
	 * @returns the calculated Path
	 */
	public Path findPath(int source, int target) {
		// array holding previous step in the shortest path
		int[] previous = new int[this.cities];
		// array holding distances from the source
		int[] distance = new int[this.cities];

		//set the initial values for distances
		for (int i = 1; i < this.cities; i++) {
			distance[i] = INFINITY;
		}
		distance[source] = 0;
		
		Set<Integer> solvedCities = new HashSet<Integer>(this.cities);
		for (int i = 1; i < this.cities; i++) {
			solvedCities.add(i);
		}
		solvedCities.remove(source);

/*		for(int city = 0; city < this.cities; city++) {
			// Set of cities with known distances from source
			Set<Integer> solvedCities = new HashSet<Integer>(this.cities);
			solvedCities.add(source);

			// set the next city to the city closest to source which does not have a
			// known distance
			int minDistance = INFINITY;
			int minIndex = 0;
			for (int i = 0; i < this.cities; i++) {
				System.out.println(this.isEdge(source, i));
				if (i != source && distance[i] < minDistance && this.isEdge(source, i) && (!solvedCities.contains(i))) {
					//if (distance[i] < minDistance && (!solvedCities.contains(i))) {
					minDistance = distance[i];
					minIndex = i;
				}
			}
			//System.out.println(next);
			int next = minIndex;
			solvedCities.add(next);

			// update the distances array if any unsolved cities have paths to
			// solved cities
			for (int i = 0; i < this.cities; i++) {
				// XXX this line assumes that paths are the same distance both
				// directions
				if ((!solvedCities.contains(i))
						&& (this.isEdge(next, i) || this.isEdge(i, next))) {
					int sum = distance[next] + this.getWeight(i, next);
					if (sum < distance[i]) {
						distance[i] = sum;
						previous[i] = next;
					}
				}
			}
		}*/
		return new Path(distance, previous);
	}

}
