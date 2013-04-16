import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

/**
 * Handles input parsing to create WeightedGraphs from input
 * 
 * @author Ben Formosa, Student No. 11429074
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

	/**
	 * Apply labels to a WeightedGraph given an array of labels
	 * 
	 * If labels.length < w.size(), only the first vertices will be labeled,
	 * others will remain unlabeled. If labels.length > w.size(), extra labels
	 * will be ignored
	 * 
	 * @param w
	 *            WeightedGraph to label
	 * @param labels
	 *            an array of Objects, where labels[n] is the label for vertex n
	 *            in w
	 * @return the labeled WeightedGraph
	 */
	public static WeightedGraph label(WeightedGraph w, Object[] labels) {
		for (int i = 0; i < labels.length; i++) {
			try {
				w.setLabel(i, labels[i]);
			} catch (ArrayIndexOutOfBoundsException e) {
				// don't try to label vertex i, index input is probably too long
				// for this WeightedGraph
			}
		}
		return w;
	}

	/**
	 * Apply labels to a WeightedGraph given a file containing labels.
	 * 
	 * Each line in the file should follow the format as listed in
	 * WeightedGraphFactory.labelFromReader
	 * 
	 * @param w
	 *            WeightedGraph to label
	 * @param filename
	 *            the file containing the labels
	 * @return the labeled WeightedGraph
	 * @throws NumberFormatException
	 *             if the index is non-numeric
	 * @throws IOException
	 */
	public static WeightedGraph labelFromFile(WeightedGraph w, String filename)
			throws NumberFormatException, IOException {
		return labelFromReader(w, new FileReader(filename));
	}

	/**
	 * Apply labels to a WeightedGraph given a Reader
	 * 
	 * The input should be in the form of a line for each vertex of the graph,
	 * starting with the index, then a space, then a string to apply as the
	 * label. e.g. 0 label0 1 label1
	 * 
	 * @param w
	 *            WeightedGraph to label
	 * @param r
	 *            Reader to read
	 * @return the labeled WeightedGraph
	 * @throws NumberFormatException
	 *             if the index is non-numeric
	 * @throws IOException
	 */
	public static WeightedGraph labelFromReader(WeightedGraph w, Reader r)
			throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(r);
		String line;
		// an associative array where the key is the index and the value is the
		// label
		Map<Integer, String> map = new HashMap<Integer, String>();

		// read the input and add each label to the Map
		while ((line = br.readLine()) != null && !("".equals(line))) {
			map.put(Integer.parseInt(line.split(" ", 2)[0]),
					line.split(" ", 2)[1]);
		}

		// convert the Map into an array. I'm doing this here as we didn't know
		// the length of the input before
		Object[] labels = new String[map.size()];
		for (Object i : map.entrySet().toArray()) {
			@SuppressWarnings("unchecked")
			// if this isn't a Map.Entry<Integer, String>, we would have thrown
			// an exception when creating it
			Map.Entry<Integer, String> j = (Map.Entry<Integer, String>) i;
			labels[j.getKey()] = j.getValue();
		}
		return label(w, labels);
	}
}
