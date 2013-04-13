Ben Formosa
Student No. 11429074

ICT322 Assessment 2

Manifest:
README.txt	- this file
src/edu/colorado/graphs/Graph.java - Michael Main's Graph class. I have not modified this class.
src/WeightedGraph.java - subclass of edu.colorado.graphs.Graph, allowing edges to have weights.

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

In order to implement labelToIndex(), I changed the access level of Graph.labels to protected to allow the subclass WeightedGraph to use it.

I used Integer instead of int for the arrays holding the distance to each city. This was to make using ArrayList simpler, as it doesn't play too nicely with primitives. An ArrayList was chosen to take advantage of the Collections.reverse() method.