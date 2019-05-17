package com.txb.graph.utils;
/**
 * 用邻接矩阵实现无向图的存储
 * @author 13125
 *
 */
public class MatrixUdGraph implements Graph{

	private Node[] nodes;//顶点数组
	private int[][]  matrix;//邻接矩阵
	private int node_size;//顶点的数量
	private int edg_size;//边的数量
	private static int NUMOFNODE = 10;//顶点的容量
	private int captity;//容量
	
	//构造函数
	public MatrixUdGraph(String []nodes) {
		this.node_size = nodes.length;
		this.captity = NUMOFNODE;
	   this.nodes = new Node[NUMOFNODE];
	   
	   //设置顶点
	   for(int i = 0; i < node_size; i++) {
		   this.nodes[i].setIndex(i);
		   this.nodes[i].setName(nodes[i]);
	   }
	   //设置边
	   for(int i = 0; i < NUMOFNODE; i++) {
			for(int j = 0; j < NUMOFNODE; j++) {
				matrix[i][j] = 0;
			}
		}
	}
	
	public  MatrixUdGraph() {
		this.edg_size = 0;
		this.captity = NUMOFNODE;
		this.matrix = new int[NUMOFNODE][NUMOFNODE];
		this.nodes = new Node[NUMOFNODE];
		this.node_size = 0;
	}
	

	@Override
	public int getNumOfNode() {
		
		return node_size;
	}

	@Override
	public int getNumOfEdg() {
		
		return edg_size;
	}

	//扩容缩容
	private void resize(int newCaptity) {
		//更新数组
		Node []newNode = new Node[newCaptity];
		for(int i = 0; i < node_size; i++) {
			newNode[i] = nodes[i];
		}
		nodes = newNode;
		//更新矩阵
		int [][]newMatrix = new int[newCaptity][newCaptity];
		for(int i = 0; i < node_size; i++) {
			for(int j = 0; j < node_size; j++) {
				newMatrix[i][j] = matrix[i][j];
			}
		}
		matrix = newMatrix;
	}
	
	//添加顶点
	@Override
	public void addNode(String name) {
		if(this.contains(name)) {
			throw new IllegalArgumentException("已经有这个节点了");
		}else {
			
			//重构顶点数组
			//重构邻接矩阵
			this.node_size ++;
			if(this.node_size>this.captity) {
				resize(2*this.captity);
			}
			
			nodes[node_size-1].setName(name);
			nodes[node_size-1].setIndex(node_size);
			
		}
		
	}

	//是否已包含顶点
	private boolean contains(String name) {
		for(int i = 0; i < node_size; i++) {
			if(nodes[i].getName().compareTo(name)==0) {
				return true;
			}
		}
		return false;
	}
	
	//添加边
	@Override
	public void addEdg(int start_index, int end_index) {
		matrix[start_index][end_index] = 1;
		matrix[end_index][start_index] = 1;
	}

	//根据name查找边顶点的索引
	private int getIndexByName(String name) {
		for(int i = 0; i<node_size; i++) {
			if(nodes[i].getName().compareTo(name)==0) {
				return i;
			}
		}
		return -1;//代表失败
	}
	
	//添加边
	@Override
	public void addEdg(String start_name, String end_name) {
		int start_index = getIndexByName(start_name);
		int end_index = getIndexByName(end_name);
		
		//判断传入参数的合理性
		if(start_index==-1||end_index==-1) {
			throw new IllegalArgumentException("参数错误");
		}
		
		addEdg(start_index, end_index);
	}
	
	//查找与index相连的顶点
	private int [] findFacingEdg(int index) {
		int []fac = new int[node_size-1];
		int size  =  0;
		for(int i = 0; i<node_size; i++) {
			if(matrix[index][i]==1) {
				fac[size] = i;
				size++;
			}
		}
		
		return fac;
	}
	
	//广度优先搜索
	@Override
	public void BreadthFirstSearch() {
		
		//顶点标记
		boolean []visited = new boolean[node_size];
		for(int i = 0; i<node_size; i++) {
			visited[i] = false;
		}
		int unvis = node_size;
		BFS(0,visited);
		while(unvis!=0) {
			for(int i = 0; i < node_size; i++) {
				if(visited[i] == false) {
					BFS(i,visited);
				}
			}
		}
		
		
	}

	private void BFS(int index, boolean []visited) {
		System.out.println(nodes[index].getName());
		visited[index] = true;
		int []fac = new int[node_size-1];
		fac = findFacingEdg(index);
		for(int i = 0; i<node_size; i++) {
			if(!visited[fac[i]]) {
				//没有访问过且为index的连接点
				BFS(i,visited);
			}
		}
	}
	
	@Override
	public void DepthFirstSearch() {
		//顶点标记
		boolean []visited = new boolean[node_size];
		for(int i = 0; i<node_size; i++) {
			visited[i] = false;
		}
		DFS(0,visited);
		
	}
	private void DFS(int index, boolean []visited) {
		System.out.println(nodes[index].getName());
		visited[index] = true;
		int []fac = new int[node_size-1];
		fac = findFacingEdg(index);
		for(int i = 0; i<node_size; i++) {
			if(!visited[fac[i]]) {
				//没有访问过且为index的连接点
				DFS(i,visited);
				break;
			}
		}
	}

}
