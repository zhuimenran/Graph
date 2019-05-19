package com.txb.graph.utils;
/**
 * 用邻接矩阵实现无向图的存储
 * @author 13125
 *
 */
public class MatrixUdGraph implements Graph{

	private char[] nodes;//顶点数组
	private int[][]  matrix;//邻接矩阵
	private int node_size;//顶点的数量
	private int edg_size;//边的数量
	private static int NUMOFNODE = 10;//顶点的容量
	private int captity;//容量
	
	//构造函数
	public MatrixUdGraph(char []nodes) {
		this.node_size = nodes.length;
		this.captity = NUMOFNODE;
	   this.nodes = new char[NUMOFNODE];
	   
	   if(nodes.length>this.captity) {
		   resize(this.captity*2);
	   }
	   
	   //设置顶点
	   for(int i = 0; i < node_size; i++) {
		   this.nodes[i] = nodes[i];
	   }
	   //设置边
	   for(int i = 0; i < this.node_size; i++) {
			for(int j = 0; j < this.node_size; j++) {
				matrix[i][j] = 0;
			}
		}
	}
	
	public  MatrixUdGraph() {
		//System.out.println("dfg");
		this.edg_size = 0;
		this.captity = NUMOFNODE;
		this.matrix = new int[NUMOFNODE][NUMOFNODE];
		this.nodes = new char[NUMOFNODE];
		this.node_size = 0;
		 //设置顶点
		
		   //设置边
		   for(int i = 0; i < NUMOFNODE; i++) {
				for(int j = 0; j < NUMOFNODE; j++) {
					matrix[i][j] = 0;
				}
			}
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
		char []newNode = new char[newCaptity];
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
	public void addNode(char name) {
		if(contains(name)) {
			throw new IllegalArgumentException("已经有这个节点了");
		}else {
			
			//重构顶点数组
			//重构邻接矩阵
			
			if(this.node_size+1>this.captity) {
				resize(2*this.captity);
			}
			
			if(node_size<this.captity) {
				nodes[this.node_size] = name;
				
				this.node_size++;
			}
		}
		
	}

	//是否已包含顶点
	private boolean contains(char name) {
		for(int i = 0; i < node_size; i++) {
			if(nodes[i] == name) {
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
	private int getIndexByName(char name) {
		for(int i = 0; i<node_size; i++) {
			if(nodes[i]==name) {
				return i;
			}
		}
		return -1;//代表失败
	}
	
	//添加边
	@Override
	public void addEdg(char start_name, char end_name) {
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
	/**
	 * 广度优先搜索不适应用递归方法
	 */
	@Override
	public void BreadthFirstSearch() {
		
		//辅助队列
		int []queue = new int[node_size];
		int rear = 0;
		int head = 0;
		//顶点标记
		boolean []visited = new boolean[node_size];
		for(int i = 0; i<node_size; i++) {
			visited[i] = false;
		}
		
		
		//找到开始节点和再开始节点
	  for(int i = 0; i < node_size; i++) {
			if(!visited[i]) {
					visited[i] = true;//改变访问状态
					System.out.printf("%c",nodes[i]);
					queue[rear++] = i;
				}
			 while (head != rear) {
	                int j = queue[head++];  // 出队列
	                int []fac = new int[node_size];
	                fac = findFacingEdg(j);
	                for (int k = 0; k <fac.length; k++) { //fac[k]是为访问的邻接顶点
	                    if (!visited[fac[k]]) {
	                        visited[fac[k]] = true;
	                        System.out.printf("%c ",nodes[fac[k]] );
	                        queue[rear++] = fac[k];
	                    }
	                }
	            }
	  }
		
	  System.out.printf("\n");

	}

	public void printMatrix() {
		for(int i = 0; i < node_size; i++) {
			for(int j = 0; j < node_size; j++) {
				 System.out.print(matrix[i][j]);
			}
			 System.out.printf("\n");
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
		System.out.println(nodes[index]);
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
