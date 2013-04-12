package au.edu.csu.bformo01.ITC322.Ass3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TravelPlanInteractive {

	/**
	 * @param args
	 * @throws IOException
	 * @throws NumberFormatException
	 */
	public static void main(String[] args) throws NumberFormatException,
			IOException {

		System.out.println("Ben Formosa (11429074) ITC322 Assignment 3");

		// prompt for data file names

		WeightedGraph w = WeightedGraphFactory.fromFile("distance.txt", false);
		w = WeightedGraphFactory.labelFromFile(w, "index.txt");

		System.out.println("Cities:");
		for (int i = 0; i < w.size(); i++) {
			System.out.print(w.getLabel(i) + " ");
		}
		System.out.println();

		// print cities
		// start looping
		// prompt
		// calculate path, display

		// get input
		Scanner sc = new Scanner(System.in).useDelimiter(", ");
		System.out.print("Source, Destination: ");
		String strSource = sc.next().trim();
		String strTarget = sc.next().trim();

		// convert city names to indexes
		int source = w.labelToIndex(strSource);
		int target = w.labelToIndex(strTarget);

		// find the shorted path
		Path path = WeightedGraphSolver.findPath(w, source);

		// get the visited cities
		ArrayList<Integer> p = path.pathTo(target);

		if (p.size() == 0) {
			System.out.println("There's no route from " + strSource + " to "
					+ strTarget);
		} else if (p.size() == 1) {
			System.out.println("The souce and destination are the same");
		} else {
			// print path and distance from source to target
			System.out.println(path.pathToToString(target));
			System.out.println("Distance: " + path.distanceTo(target));
		}
	}
}
