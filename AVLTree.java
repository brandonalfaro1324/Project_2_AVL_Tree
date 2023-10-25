
/*Starter code for AVL Tree*/

//package sa;
package bxa220020;

import java.util.Comparator;
import java.util.Stack;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

import bxa220020.BinarySearchTree.Entry;



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
		
		System.out.println("\n");
		
		// Intialize a Boolean and pass in new Entry of element x and two child node nulls
		Entry<T> new_node = new Entry<T>(x, null, null);
		boolean add_success = super.add(new_node);

		
		if(add_success == true){
			if(super.size == 1){
				Entry<T> tmp = (Entry<T>) super.root;
				tmp.height = 1;
			}
			else{
				Entry<T> newly_added_node = (Entry<T>) super.binary_stack.pop(); 
				checking_tree(newly_added_node);
			}
		}
		System.out.println("\n");
		// Return back the success of new new node.	
		return add_success;
    }
	


	
	private void checking_tree(Entry<T> node){
	
		Entry<T> current_node = node;

		int balance_checkter = 0;

		int iteration_ = 0;
		int left_balance = 0;
		int right_balance = 0;

		while(current_node != null){			


			if(current_node.left == null && current_node.right == null){
				current_node.height = 1;
			}
			else{

				int get_left_height = 0;
				if(current_node.left != null){
					Entry<T> left_node = (Entry<T>) current_node.left;
					get_left_height = left_node.height;
					left_balance = get_left_height;
				}

				int get_right_height = 0;
				if(current_node.right != null){
					Entry<T> right_node = (Entry<T>) current_node.right; 
					get_right_height = right_node.height;
					right_balance = get_right_height;
				}

				current_node.height = (get_max(get_left_height, get_right_height) + 1);
				System.out.println();
	
			}
			
			System.out.println(++iteration_ + " : " + current_node.element + " - " + current_node.height);
			balance_checkter = (left_balance - right_balance);



			/*
			if(balance_checkter > 1){

				Entry<T> balance_left_node = (Entry<T>) current_node.left;
				if(get_balance(balance_left_node) == 1){
					System.out.println("BEGIN LL ROTATION");
				}
				else{
					System.out.println("BEGIN LR ROTATION");
				}
			}
			else if(balance_checkter < -1){

				Entry<T> balance_right_node = (Entry<T>) current_node.right;
				if(get_balance(balance_right_node) == 1){
					System.out.println("BEGIN RR ROTATION");
				}
				else{
					System.out.println("BEGIN RL ROTATION");
				}
			}
 			*/
			
			
			System.out.println("Getting Balance: " + balance_checkter);
			current_node = (Entry<T>) super.binary_stack.pop();
		}		
	}


	private int get_balance(Entry<T> node){

		Entry<T> current_node = (Entry<T>) node;
		int balance_value = 0;

		int left_height = 0;
		if(current_node.left != null){
			Entry<T> left_node = (Entry<T>)current_node.left;
			left_height = left_node.height;
		}


		int right_height = 0;
		if(current_node.right != null){
			Entry<T> right_node = (Entry<T>)current_node.right;
			right_height = right_node.height;
		}

		balance_value = (left_height - right_height);

		return balance_value;
	}



	public void printing_nodes(){
		Entry<T> tmp = (Entry<T>) super.root;
		printing_node(tmp);
	}
	private void printing_node(Entry<T> node){
		if(node != null){
			System.out.println();
			System.out.println("PRINTING HEIGHT OF TREE: " + node.height);

			System.out.println("GOING_LEFT: ");
			printing_node((Entry<T>) node.left);
			System.out.println("GOING_RIGHT: ");
			printing_node((Entry<T>) node.right);
		}
		else{
			System.out.println("REACHED NULL, COMING BACK");
		}
	}
 	



	//Optional. Complete for extra credit
	@Override
    public T remove(T x) {
		return super.remove(x);
    }
	


	// THIS WORKS DO NOT TOUCH
	// verify()
	public boolean verify(){
		boolean verify_sucess = false;
		CheckNode<T> result = null;

		if(super.size == 1){
			verify_sucess = true;
		}
		if(verify_sucess == false){
			Entry<T> root = (Entry<T>) super.root;
			result = verify(root);

			verify_sucess = result.flag;
		}
		return verify_sucess;
	}

	// verify(Entry<T>)
	private CheckNode<T> verify(Entry<T> node){
		T element = node.element;

		T left_min = element;
		T right_max = element;

		int left_height = 0;
		int right_height = 0;

		////////////////////////////////////////////////
		CheckNode<T> left_child = null;
		if(node.left!= null){
			
			left_child = verify((Entry<T>) node.left);
			left_height = left_child.height;

			if(left_child.flag != true || (left_child.max).compareTo(element) >= 0){
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
				return (new CheckNode<>(false, null, null, right_height + 1));
			}
			right_max = right_child.max;
		}

		if(node.height != (1 + get_max(left_height, right_height)) || get_absolute(left_height, right_height) > 1 ){
			CheckNode<T> returning_data = new CheckNode<>(false, null, null, node.height);
			
			//printing_test(returning_data);
			return (returning_data);
		}
		else{
			CheckNode<T> returning_data = new CheckNode<>(true, left_min, right_max, node.height);
			//printing_test(returning_data);

			return (returning_data);
		}
	}
	//THIS WORKS DO NOT TOUCH


	// Helper Functions
	//-------------------------------------------
	//-------------------------------------------
	private void printing_test(CheckNode<T> check_node){
		System.out.println();
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