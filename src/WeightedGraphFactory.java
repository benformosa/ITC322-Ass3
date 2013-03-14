import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Handles input parsing to create WeightedGraphs from file input 
 */
public class WeightedGraphFactory {
	/**
	 * Create a WeightedGraph from a file
	 * @param filename file to read from
	 * @return the WeightedGraph created
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws NumberFormatException
	 */
	public static WeightedGraph fromFile(String filename) throws IOException,
	FileNotFoundException, NumberFormatException {

		FileReader f = new FileReader(filename);
		BufferedReader br = new BufferedReader(f);
		String line;

		//read the number of vertices from the first line
		line = br.readLine();
		
		WeightedGraph w = new WeightedGraph(Integer.parseInt(line));

		// loop until an empty line or EOF
		while ((line = br.readLine()) != null && !("".equals(line))) {
			// add each edge to the graph
			w.addEdge(Integer.parseInt(line.split(" ")[0]),
					Integer.parseInt(line.split(" ")[1]),
					Integer.parseInt(line.split(" ")[2]));
		}
		return w;
	}
}
