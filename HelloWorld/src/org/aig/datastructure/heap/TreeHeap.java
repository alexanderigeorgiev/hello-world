package org.aig.datastructure.heap;

import java.util.ArrayDeque;
import java.util.Hashtable;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Stack;

public class TreeHeap {
	
	private BinaryTreeNode _root = null;
	
	public TreeHeap(int value) {
		_root = new BinaryTreeNode(value);
	}
	
	public void add(int value) {
		if (_root == null) {
			_root = new BinaryTreeNode(value);
		} else {
			if (value < _root.getValue()) {
				int rootValue = _root.getValue(); 
				_root.setValue(value);
				value = rootValue;
			}
			if (_root.getLeft() == null) {
				_root.setLeft(new BinaryTreeNode(value));
			} else if (_root.getRight() == null) {
				_root.setRight(new BinaryTreeNode(value));
			} else {
				//add from left to right, level by level
				Queue<BinaryTreeNode> que = new ArrayDeque<>(); 
				que.add(_root.getLeft());
				que.add(_root.getRight());
				while(!que.isEmpty()) {
					BinaryTreeNode node = que.poll();
					if (node.getLeft() == null) {
						node.setLeft(new BinaryTreeNode(value));
						adjust(node);
						break;
					} else if (node.getRight() == null) {
						node.setRight(new BinaryTreeNode(value));
						adjust(node);
						break;
					} else {
						que.add(node.getLeft());
						que.add(node.getRight());
					}
				}
			}
		}
	}
	
	public boolean isEmpty() {
		return _root == null;
	}
	
	public int pop() {
		if (_root == null) {
			throw new NoSuchElementException();
		}
		int result = _root.getValue();
		//get the deepest rightmost leaf
		Stack<BinaryTreeNode> stek = new Stack<>(); 
		stek.add(_root);
		Hashtable<BinaryTreeNode,BinaryTreeNode> map = new Hashtable<>();
		BinaryTreeNode node = null;
		while(!stek.isEmpty()) {
			node = stek.pop();
			if (node.getLeft() != null) {
				stek.add(node.getLeft());
				map.put(node.getLeft(), node);
			} else if (node.getRight() != null) {
				stek.add(node.getRight());
				map.put(node.getRight(), node);
			}
		}
		if (node != null) {//found it
			BinaryTreeNode parent = map.get(node);
			_root.setValue(node.getValue());
			if (parent != null) {
				if (node.equals(parent.getRight())) {
					parent.setRight(null);
				} else {
					parent.setLeft(null);
				}
			}
		}
		adjust(_root);
		return result;
	}
	
	private void adjust(BinaryTreeNode node) {
		int value = node.getValue();
		int leftValue = Integer.MAX_VALUE;
		int rightValue = Integer.MAX_VALUE;
		if (node.getLeft() != null) {
			leftValue = node.getLeft().getValue();
		}
		if (node.getRight() != null) {
			rightValue = node.getRight().getValue();
		}
		if (leftValue < rightValue) {
			if (leftValue < value) {
				node.setValue(leftValue);
				node.getLeft().setValue(value);
				adjust(node.getLeft());
			}
		} else {
			if (rightValue < value) {
				node.setValue(rightValue);
				node.getRight().setValue(value);
				adjust(node.getRight());
			}
		}
	}
	
	public static void main(String[] args) {
		TreeHeap heap = new TreeHeap(5);
		heap.add(6);
		heap.add(7);
		heap.add(4);
		heap.add(3);
		heap.add(2);
		heap.add(1);
		heap.add(8);
		
		for(int i = 1 ; i <= 8 ; i++) {
			System.out.println("["+i+"] heap.pop(): "+heap.pop());
		}
	}

}
