package org.aig.datastructure.heap;

public class BinaryTreeNode {
	
	private int value;
	private BinaryTreeNode left;
	private BinaryTreeNode right;
	
	public BinaryTreeNode(int a) {
		value = a;
	}
	
	public BinaryTreeNode getLeft() {
		return left;
	}
	
	public BinaryTreeNode getRight() {
		return right;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setLeft(BinaryTreeNode left) {
		this.left = left;
	}
	
	public void setRight(BinaryTreeNode right) {
		this.right = right;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public String toString() {
		return ""+value;
	}
}
