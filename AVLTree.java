
/*Starter code for AVL Tree*/

//package sa;
package bxa220020;

import java.util.Comparator;
import java.util.Stack;



public class AVLTree<T extends Comparable<? super T>> extends BinarySearchTree<T> {

	static class Entry<T> extends BinarySearchTree.Entry<T> {
        int height;
        Entry(T x, Entry<T> left, Entry<T> right) {
            super(x, left, right);
            height = 0;
        }
    }

	static class CheckNode<T>{

		boolean flag;
		T min;
		T max;
		int height;

		public CheckNode(){
			flag = false;
			min = null;
			max = null;
			height = 0;
		}

		public CheckNode(boolean flag, T min, T max, int height){
			this.flag = flag;
			this.min = min;
			this.max = max;
			this.height = height;
		}



	}


    AVLTree() {
		super();
    }

	// TO DO
    @Override
    public boolean add(T x) {
		// Intialize a Boolean and pass in new Entry of element x and two child node nulls
		boolean add_success = super.add(new Entry<T>(x, null, null));

		// Return back the success of new new node.
		return add_success;
    }
	
	//Optional. Complete for extra credit
	@Override
    public T remove(T x) {
	
		return super.remove(x);
    }
	


	// verify()
	public boolean verify(){
		boolean verify_sucess = false;
		CheckNode<T> result = null;

		if(super.size == 0){
			verify_sucess = true;
		}
		if(verify_sucess == false){
			//System.out.println("\n\n\n\n");
			Entry<T> root = (Entry<T>) super.root;
			result = verify(root);
			verify_sucess = result.flag;
		}

		return verify_sucess;
	}

	// verify(Entry<T>)
	private CheckNode<T> verify(Entry<T> node){
		//System.out.println("HEIGHT: " + node.height);
		//System.out.println();

		T element = node.element;

		T left_min = element;
		T right_max = element;

		int left_height = -1;
		int right_height = -1;

		////////////////////////////////////////////////
		CheckNode<T> left_child = null;
		if(node.left!= null){
			
			left_child = verify((Entry<T>) node.left);
			left_height = left_child.height;

			if(left_child.flag != true || (left_child.max).compareTo(element) >= 0){
				//System.out.println("ERROR");
				return (new CheckNode<>(false, left_min, left_child.max, left_height + 1));
			}

			left_min = left_child.min;
		}
		
		////////////////////////////////////////////////
		CheckNode<T> right_child = null;
		if(node.right!= null){

			right_child = verify((Entry<T>) node.right);
			right_height = right_child.height;
	
			if(right_child.flag != true || (element).compareTo(right_child.min) >= 0){
				//System.out.println("ERROR");
				return (new CheckNode<>(false, right_child.min, right_max, right_height + 1));
			}
			right_max = right_child.max;
		}
	
		////////////////////////////////////////////////
		/*
		System.out.println("BEFORE TEST: ");	
		System.out.println(node.height + " = " + (1 + get_max(left_height, right_height)));	
		System.out.println(get_absolute(left_height, right_height) + " > " + 1);	
		 */

		node.height = (get_max(left_height, right_height) + 1);	

		if(node.height != (1 + get_max(left_height, right_height)) || get_absolute(left_height, right_height) > 1 ){

			CheckNode<T> returning_data = new CheckNode<>(false, left_min, right_max, node.height);

			//System.out.println("BAD: RETURNING BACK");
			//printing_test(returning_data);
			return (returning_data);
		}else{

			CheckNode<T> returning_data = new CheckNode<>(true, left_min, right_max, node.height);

			//System.out.println("GOOD: RETURNING BACK");
			//printing_test(returning_data);
			return (returning_data);
		}
	}


	// Helper Functions
	//-------------------------------------------
	//-------------------------------------------
	private void printing_test(CheckNode<T> check_node){
		System.out.println("(" + check_node.flag + " : " 
								+ check_node.min + " : " 
								+ check_node.max + " : " 
								+ check_node.height + ")");
		System.out.println();
	}

	//-------------------------------------------
	private int get_max(int left_hand, int right_hand){
		int return_height = left_hand;

		
		if(right_hand >= left_hand){
			return_height = right_hand;
		}
		return return_height;
	}

	//-------------------------------------------
	private int get_absolute(int left_hand, int right_hand){
		int return_absolute_value = left_hand - right_hand;

		if(return_absolute_value < 0){
			return_absolute_value *= -1;
		}
		return return_absolute_value;
	}
	//-------------------------------------------
	//-------------------------------------------

}

