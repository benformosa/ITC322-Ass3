import edu.colorado.graphs.Graph;

/**
 * A WeightedGraph is a subclass of Graph which records a weight for each edge.
 * 
 * @author Ben Formosa, Student No. 11429074
 */
public class WeightedGraph extends Graph {
	/**
	 * edge weights, such that edge[a][b] has a weight of weights[a][b]
	 */
	private int[][] weight;

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

	/**
	 * Add an edge to this WeightedGraph with a specified weight
	 * 
	 * @param source
	 *            source vertex of the edge
	 * @param target
	 *            target vertex of the edge
	 * @param weight
	 *            weight of the edge
	 */
	public void addEdge(int source, int target, int weight) {
		super.addEdge(source, target);
		this.setWeight(source, target, weight);
	}

	/**
	 * Returns the label of the given vertex
	 * 
	 * Modified from Graph's getLabel to use the index of the vertex as the
	 * label if none other exists.
	 * 
	 * @param vertex
	 *            vertex to get the label of
	 * @return the label of the vertex, or if it is null, the int index of the
	 *         vertex
	 */
	@Override
	public Object getLabel(int vertex) {
		Object l = super.getLabel(vertex);

		if (l == null) {
			l = vertex;
		}
		return l;
	}

	/**
	 * Returns a Label for a vertex that will always be a String. Equivalent to
	 * getLabel(vertex).toString()
	 * 
	 * @param vertex
	 *            vertex to get the label of
	 * @return String label of the vertex
	 */
	public String getStringLabel(int vertex) {
		return getLabel(vertex).toString();
	}

	/**
	 * Get the weight of a given edge
	 * 
	 * @param source
	 *            source vertex of the edge to find the weight of
	 * @param target
	 *            target vertex
	 * @return weight of the edge source, target
	 */
	public int getWeight(int source, int target) {
		return weight[source][target];
	}

	/**
	 * Return the index of the first vertex with the given label
	 * 
	 * If the parameter label is a String, and can be parsed into an int equal
	 * to an int label of a vertex, that vertex's index will be returned
	 * 
	 * @param label
	 *            Object to search for
	 * @return index of the vertex with the given label, or -1 if the label is
	 *         not found
	 */
	public int labelToIndex(Object label) {
		for (int i = 0; i < labels.length; i++) {
			// the overridden getLabel won't return null, so we can safely try
			// .equals()
			if (this.getLabel(i).equals(label)) {
				return i;
				// if label is a String containing an int, which is i's label
			} else if (label instanceof String) {
				try {
					if (this.getLabel(i).equals(
							Integer.parseInt((String) label))) {
						return i;
					}

				} catch (NumberFormatException e) {
					// can't convert String to int
				}
			}
		}
		return -1;
	}

	/**
	 * Set the weight of an edge
	 * 
	 * Does nothing if the edge source, target doesn't exist
	 * 
	 * @param source
	 *            source vertex of the edge to set a weight on
	 * @param target
	 *            target vertex
	 * @param w
	 *            weight to set
	 */
	public void setWeight(int source, int target, int w) {
		if (this.isEdge(source, target)) {
			this.weight[source][target] = w;
		}
	}
}
