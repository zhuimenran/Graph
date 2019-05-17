package com.txb.graph.utils;
/**
 * 图的节点
 * @author 13125
 *
 */
public class Node {
	private int index;//节点的序号
    private String name;//节点的名称
    private String example;//节点的介绍
    
    //构造函数
	public Node() {
		super();
	}
	
	public Node(String name) {
		super();
		this.name = name;
	}
	public Node(int index, String name) {
		super();
		this.index = index;
		this.name = name;
	}
	public Node(int index, String name, String example) {
		super();
		this.index = index;
		this.name = name;
		this.example = example;
	}
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getExample() {
		return example;
	}
	public void setExample(String example) {
		this.example = example;
	}
    
    
}
