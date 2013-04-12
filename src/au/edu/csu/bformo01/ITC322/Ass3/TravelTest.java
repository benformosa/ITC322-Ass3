package au.edu.csu.bformo01.ITC322.Ass3;

import java.io.IOException;
import java.util.ArrayList;

public class TravelTest {

	public static void main(String[] args) throws IOException {

		WeightedGraph w = WeightedGraphFactory.fromFile("distance.txt", false);
		w = WeightedGraphFactory.labelFromFile(w, "index.txt");

		ArrayList<Path> l = WeightedGraphSolver.findAllPaths(w);

		for (Path p : l) {
			for (int i = 0; i < w.size(); i++) {
				System.out.println(p.pathToToString(i));
			}
		}

		int source;
		int target;
		source = w.labelToIndex("Haxix");
		target = w.labelToIndex("Cera");

		// source = 0;
		// target = 2;

		Path p = WeightedGraphSolver.findPath(w, source);
		System.out.println(p);
		System.out.println(p.distanceTo(target));
		System.out.println("path from " + w.getStringLabel(source) + " to "
				+ w.getStringLabel(target));
		for (int i : p.pathTo(target)) {
			System.out.println(w.getStringLabel(i));
		}

	}
}