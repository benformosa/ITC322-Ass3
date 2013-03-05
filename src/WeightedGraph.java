import edu.colorado.graphs.Graph;

/**
 * A WeightedGraph is a subclass of Graph which records a weight for each edge.
 * 
 * @author Ben Formosa, Student No. 11429074
 */
public class WeightedGraph extends Graph { // implements Cloneable {
	private int[][] weight; // edge weights, such that edge[a][b] has a weight
							// of weights[a][b]

	/**
	 * Creates an empty WeightedGraph with n vertices
	 * 
	 * @param n
	 *            number of vertices in Graph
	 */
	public WeightedGraph(int n) {
		super(n);
		this.weight = new int[n][n];
	}

	public void addEdge(int source, int target, int weight) {
		super.addEdge(source, target);
		this.setWeight(source, target, weight);
	}

	/**
	 * Get the weight of a given edge
	 * 
	 * @param source
	 * @param target
	 * @return
	 */
	public int getWeight(int source, int target) {
		return weight[source][target];
	}

	public void setWeight(int source, int target, int w) {
		this.weight[source][target] = w;
	}

	// /**
	// *
	// */
	// public Object clone( ) {
	// WeightedGraph answer;
	//
	// answer = (WeightedGraph) super.clone();
	// answer.weight = (int [][]) weight.clone();
	//
	// return answer;
	// }
}
