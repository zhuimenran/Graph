package com.txb.graph.utils;
/**
 * 图结构的接口
 * @author 13125
 *
 */
public interface Graph {

	public void BreadthFirstSearch();//广度优先遍历
	public void DepthFirstSearch();//深度优先遍历
	
	public int getNumOfNode();//得到顶点的数量
	
	public int getNumOfEdg();//得到边的数量
	
	//设置顶点
	public  void addNode(String name);
	
	//设置边
	public void addEdg(int start_index, int end_index);
	public void addEdg(String start_name, String end_name);
	
}
