Ben Formosa
Student No. 11429074

ICT322 Assessment 2

Manifest:
README.txt	- this file
src/edu/colorado/graphs/Graph.java - Michael Main's Graph class. I have not modified this class.
src/WeightedGraph.java - subclass of edu.colorado.graphs.Graph, allowing edges to have weights.
src/WeightedGraphFactory.java - a class which reads input to create instances of WeightedGraph.
src/WeightedGraphSolver.java - a class which provides methods to find the shortest distance and path through a WeightedGraph

Building and Running:
*Build javadoc:
		javadoc -d doc src/*
*Compile
		mkdir bin
		javac -classpath bin -sourcepath src -d bin src/*.java
*Run
		java -classpath bin TravelPlanInteractive
	
Explanation:

The first step in this project was creating a sub class of Graph, WeightedGraph. This just adds a 2D array of ints to store each edge's weight. I figured adding another array would be simpler than replacing the boolean array in Graph.
I added some methods for functionality that wasn't present in Graph.
The method labelToIndex() allows finding the index of a vertex with the given label. While implementing this class, I changed the access level of Graph.labels to protected to allow the subclass WeightedGraph to use it.

To handle reading input files and construct instances of WeightedGraph, I used another class than WeightedGraph in order to keep reading input and the model of a WeigtedGraph separate.
The fromReader() method takes a boolean argument, if true each input path is added again in reverse, to make the graph undirected. It also contains a method to add Strings as labels to a graph from a file.

WeightedGraphSolver implements Dijkstra's algorithm in order to find the shortest distance path and distance between points in a WeightedGraph. The assignment specifies creating two methods one each to find the distance and path. In order to avoid rerunning the algorithm to find the path and distance, both are calculated at the same time and the results are stored in a Path object.
I used Integer instead of int for the arrays holding the distance to each city. This was to make using ArrayList simpler, as it doesn't play too nicely with primitives. An ArrayList was chosen to take advantage of the Collections.reverse() method.
WeightedGraphSolver also has a few extra methods like mapPaths, which is handy to see every possible path through the graph in as few lines of output as possible.

Path stores the results from WeightedGraphSolver.findPath(), ie the distance to every other vertex from the source, and the previous hop on that path for each vertex.
The toString() method gives a slightly wordy summary of the calculated paths in human readable format.
