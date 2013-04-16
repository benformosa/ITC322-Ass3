Ben Formosa
Student No. 11429074

ICT322 Assessment 2

Manifest:
README.txt	- this file
src/edu/colorado/graphs/Graph.java - Michael Main's Graph class. Modified by me.
src/WeightedGraph.java - subclass of edu.colorado.graphs.Graph, allowing edges to have weights.
src/WeightedGraphFactory.java - a class which reads input to create instances of WeightedGraph.
src/WeightedGraphSolver.java - a class which provides methods to find the shortest distance and path through a WeightedGraph
src/Path.java - class to store calculated paths through a WeightedGraph
src/TravelPlan.java - main class, interactively find paths and distances between cities.
bin/		- directory for compiled classes
.classpath	- eclipse classpath file
.project	- eclipse project file
distance.txt, index.txt - input files

Building and Running:
*Build javadoc:
		javadoc -d doc src/*
*Compile
		mkdir bin
		javac -classpath bin -sourcepath src -d bin src/*.java
*Run
		java -classpath bin Travel
	
Explanation:

The first step in this project was creating a sub class of Graph, WeightedGraph. This just adds a 2D array of ints to store each edge's weight. I figured adding another array would be simpler than replacing the boolean array in Graph.
I added some methods for functionality that wasn't present in Graph.
I overrode getLabel() to return the index of the vertex instead of null, if no label has been set.
The method labelToIndex() allows finding the index of a vertex with the given label. While implementing this class, I changed the access level of Graph.labels to protected to allow the subclass WeightedGraph to use it. In order to allow text input in TravelPlan for unlabled WeightedGraphs, this method will try to convert String input into ints, as getLabel() will give the vertex if there is no label.

To handle reading input files and construct instances of WeightedGraph, I used another class than WeightedGraph in order to keep reading input and the model of a WeigtedGraph separate.
The fromReader() method takes a boolean argument, if true each input path is added again in reverse, to make the graph undirected. It also contains a method to add Strings as labels to a graph from a file.

WeightedGraphSolver implements Dijkstra's algorithm in order to find the shortest distance path and distance between points in a WeightedGraph. The assignment specifies creating two methods one each to find the distance and path. In order to avoid rerunning the algorithm to find the path and distance, both are calculated at the same time and the results are stored in a Path object.
I used Integer instead of int for the arrays holding the distance to each city. This was to make using ArrayList simpler, as it doesn't play too nicely with primitives. An ArrayList was chosen to take advantage of the Collections.reverse() method.
WeightedGraphSolver also has a few extra methods like mapPaths, which is handy to see every possible path through the graph in as few lines of output as possible.

Path stores the results from WeightedGraphSolver.findPath(), ie the distance to every other vertex from the source, and the previous hop on that path for each vertex.
The toString() method gives a slightly wordy summary of the calculated paths in human readable format.

TravelPlan prompts the user for the filenames of the distance and index files. If the distance file input is blank, or a file with the specified name cannot be found, the default filename distance.txt is used. If the index file input is blank, the default filename index.txt is used. If the index file cannot be found, no index file is used.
Once the input is collected, the WeightedGraph is built, then the cities' names are printed and the user is prompted to enter two cities to find a route between.
Input is in the form "Source, Destination", ie. separated by ", " (comma whitespace). If the input doesn't match that form, or the city name is not recognised, the user is prompted again. If nothing is entered, the program ends.
Once valid input is entered, the path is printed by showing the source and each hop to the destination separated by "->". The distance is printed on another line. I haven't attempted to show units for distance. This could possibly be added as part of the first line of distance.txt and the program modified to read it. If there's no path between the cities, or the same city is entered twice, a message reflecting that is displayed instead.
The user is then prompted for another source and destination.
