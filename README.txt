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
*Compile:
	mkdir bin
	cd src
	javac -d ../bin *.java
*Run:
	cd ../bin
	java -classpath bin MazeTest
	
Explanation:

The first step in this project was creating a sub class of Graph, WeightedGraph. This just adds a 2D array of ints to store each edge's weight. I figured adding another array would be simpler than replacing the boolean array in Graph.

I grabbed a chart of cities and the distances between them from http://www.mapsofworld.com/utilities/world-airdistance-locator.htm
