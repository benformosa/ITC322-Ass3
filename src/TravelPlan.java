import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Console interactive interface to calculate paths and distances between
 * cities, where the distances between cities are a WeightedGraph
 * 
 * @author Ben Formosa, Student No. 11429074
 * 
 */
public class TravelPlan {

	/**
	 * Get city names in the form "source, target" from standard input.
	 * 
	 * If a blank line is entered, return -1 as source. If "map" is entered,
	 * return -1 as target.
	 * 
	 * @param w
	 *            WeightedGraph to work on
	 * @return int[] with two elements, 0 is source, 1 is target
	 * @throws IOException
	 */
	public static int[] getValidSourceTarget(WeightedGraph w)
			throws IOException {
		InputStreamReader in = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(in);

		String input = " ";
		String strSource = "";
		String strTarget = "";
		int source = -1;
		int target = -1;
		String regex = ",\\s";

		// loop until real city names are entered
		while (source < 0 || target < 0) {
			input = " ";
			// loop until valid input is entered
			while (!input.matches(".+" + regex + ".+")) {
				input = br.readLine();
				// return -1, -1 if a blank line is entered
				if ("".equals(input)) {
					int[] r = { -1, -2 };
					return r;
				} else if ("map".equals(input)) {
					int[] r = { -2, -1 };
					return r;
				}
			}
			// get cities from input
			String[] inputs = input.split(regex);
			strSource = inputs[0];
			strTarget = inputs[1];

			// convert city names to indexes
			source = w.labelToIndex(strSource);
			target = w.labelToIndex(strTarget);
		}
		int[] r = { source, target };
		return r;
	}

	/**
	 * Get WeightedGraph source files from standard input.
	 * 
	 * If blank lines are entered for either input or if the files don't exist,
	 * use a default value.
	 * 
	 * @return a WeightedGraph built from the input files given
	 */
	public static WeightedGraph getWeightedGraph() throws IOException {
		final String defaultDistance = "distance.txt";
		final String defaultIndex = "index.txt";

		InputStreamReader in = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(in);
		WeightedGraph w;

		System.out.print("Distance File [" + defaultDistance + "]: ");
		String distance = br.readLine();

		if ("".equals(distance)) {
			distance = defaultDistance;
		}

		// if a FileNotFoundException is thrown, just use the default
		try {
			w = WeightedGraphFactory.fromFile(distance, false);
		} catch (IOException e) {
			System.err.println("using default file " + defaultDistance);
			w = WeightedGraphFactory.fromFile(defaultDistance, false);
		}

		System.out.print("Index file [" + defaultIndex + "]: ");
		String index = br.readLine();

		if ("".equals(index)) {
			index = defaultIndex;
		}

		try {
			w = WeightedGraphFactory.labelFromFile(w, index);
		} catch (IOException e) {
			System.err.println("index file not found");
		}

		return w;
	}

	/**
	 * Main loop of the program
	 * 
	 * @param args
	 *            no arguments are processed
	 * @throws IOException
	 *             if invalid input is entered, or files specified don't exist,
	 *             handled internally
	 * @throws NumberFormatException
	 *             if input files are invalid, not handled
	 */
	public static void main(String[] args) throws NumberFormatException,
			IOException {

		// prompt for data file names
		WeightedGraph w = getWeightedGraph();

		// print cities
		System.out.println("Cities:");
		for (int i = 0; i < w.size(); i++) {
			System.out.print(w.getLabel(i) + ", ");
		}
		System.out.println();

		// keep prompting for input until a blank line is entered
		while (true) {
			System.out.print("Source, Destination: ");

			// loop until real city names are entered
			int[] sourcetarget = getValidSourceTarget(w);
			int source = sourcetarget[0];
			int target = sourcetarget[1];

			if (source == -1) {
				// if a blank line was entered for city names, end
				return;
			} else if (target == -1) {
				// if "map" was entered, print all possible paths
				System.out.println(WeightedGraphSolver.mapPathsToString(w));
			} else {

				// find the shortest path
				Path path = WeightedGraphSolver.findPath(w, source);

				// get the visited cities
				ArrayList<Integer> p = path.pathTo(target);

				if (p.size() == 0) {
					System.out.println("There's no route from "
							+ w.getStringLabel(source) + " to "
							+ w.getStringLabel(target));
				} else if (p.size() == 1) {
					System.out
							.println("The souce and destination are the same");
				} else {
					// print path and distance from source to target
					System.out.println(Path.pathToToString(w,
							path.pathTo(target)));
					System.out.println("Distance: " + path.distanceTo(target));
				}
			}
		}
	}
}
