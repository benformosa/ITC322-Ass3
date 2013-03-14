import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Handles input parsing to create WeightedGraphs from input
 */
public class WeightedGraphFactory {
	/**
	 * Create a WeightedGraph from a file
	 * 
	 * @param filename
	 *            file to read from
	 * @param directed
	 *            is this a directed graph?
	 * @return the WeightedGraph created
	 * @throws NumberFormatException
	 *             if the input is non-numeric
	 * @throws IOException
	 */
	public static WeightedGraph fromFile(String filename, boolean directed)
			throws NumberFormatException, IOException {
		return fromReader(new FileReader(filename), directed);
	}

	/**
	 * Create a WeightedGraph from a Reader
	 * 
	 * @param r
	 *            Reader to read from
	 * @param directed
	 *            is this a directed graph?
	 * @return the WeightedGraph created
	 * @throws NumberFormatException
	 *             if the input is non-numeric
	 * @throws IOException
	 */
	public static WeightedGraph fromReader(Reader r, boolean directed)
			throws IOException, NumberFormatException {
		BufferedReader br = new BufferedReader(r);
		String line;

		// read the number of vertices from the first line
		line = br.readLine();

		WeightedGraph w = new WeightedGraph(Integer.parseInt(line));

		// loop until an empty line or EOF
		while ((line = br.readLine()) != null && !("".equals(line))) {
			int source = Integer.parseInt(line.split(" ")[0]);
			int target = Integer.parseInt(line.split(" ")[1]);
			int weight = Integer.parseInt(line.split(" ")[2]);

			// add each edge to the graph
			w.addEdge(source, target, weight);

			// if it is an undirected graph, add each edge again, in the other
			// direction
			if (!directed) {
				w.addEdge(target, source, weight);
			}
		}
		return w;
	}
}
