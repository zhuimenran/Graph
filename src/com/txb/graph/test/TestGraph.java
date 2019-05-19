package com.txb.graph.test;

import com.txb.graph.utils.MatrixGraph;
import com.txb.graph.utils.MatrixUdGraph;

public class TestGraph {

	public static void main(String[] args) {
		/**
		 MatrixUdGraph graphudg = new MatrixUdGraph()；
		System.out.println(graphudg.getNumOfNode());
		graphudg.addNode('A');
		graphudg.addNode('B');
		graphudg.addNode('C');
		graphudg.addNode('D');
		graphudg.addNode('E');
		graphudg.addNode('F');
		graphudg.addEdg(0, 1);
		graphudg.addEdg(1, 2);
		graphudg.addEdg(0, 2);
		graphudg.addEdg(2, 4);
		graphudg.addEdg(4, 3);
		System.out.println();
		System.out.println("邻接矩阵：");
		graphudg.printMatrix();
		graphudg.BreadthFirstSearch();
		*/
		MatrixGraph graphudg = new MatrixGraph();
		System.out.println(graphudg.getNumOfNode());
		graphudg.addNode('A');
		graphudg.addNode('B');
		graphudg.addNode('C');
		graphudg.addNode('D');
		graphudg.addNode('E');
		graphudg.addNode('F');
		graphudg.addEdg(0, 1);
		graphudg.addEdg(1, 2);
		graphudg.addEdg(0, 2);
		graphudg.addEdg(2, 4);
		graphudg.addEdg(4, 3);
		System.out.println();
		System.out.println("邻接矩阵有向图：");
		graphudg.printMatrix();
		System.out.println("邻接矩阵有向图dfs：");
		graphudg.DepthFirstSearch();
		
	}

}
