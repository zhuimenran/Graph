package com.txb.graph.test;

import com.txb.graph.utils.MatrixUdGraph;

public class TestGraph {

	public static void main(String[] args) {
		MatrixUdGraph graphudg = new MatrixUdGraph();
		System.out.println(graphudg.getNumOfNode());
		graphudg.addNode("A");
		graphudg.addNode("B");
		graphudg.addNode("C");
		graphudg.addEdg(0, 1);
		graphudg.addEdg(1, 2);
		graphudg.BreadthFirstSearch();
	}

}
