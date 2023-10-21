/**
 * @author rbk, sa
 * Binary search tree (starter code)
 **/

// replace package name with your netid
package dsa;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;

// Adding my own imports
import java.util.Stack;

import javax.swing.RowFilter.Entry;


// Understanding this Syntax
// "BinarySearchTree<T extends Comparable<? super T>> implements Iterable<T>"

// 1) Class name "BinarySearchTree"
// 2) (<T extends Comparable<? super T>>) T class now has "Comparable"
//    -attribute to compare "?" Parent super class to child "T" class
// 3) (Comparable<? super T>) Comparing Super class of "?" to its child "T"
// 4) (<? super T>) "?" Superclass for "T" class 
// 5) (implements Iterable<T>)  BinarySearchTree objects can now Iterate

public class BinarySearchTree<T extends Comparable<? super T>> implements Iterable<T> {
    static class Entry<T> {
        T element;
        Entry<T> left, right;

        public Entry(T x, Entry<T> left, Entry<T> right) {
            this.element = x;
            this.left = left;
            this.right = right;
        }
    }

    Entry<T> root;
    int size;
    Stack<Entry<T>> binary_stack;

    ////////////////////////////////////////////////////////////////////////////////
    public BinarySearchTree() {
        root = null;
        size = 0;
        binary_stack = null;
    }

    /** TO DO: Is x contained in tree?
     */
    public boolean contains(T x) {
        return false;
    }

    // "add()" function adds elements
    public boolean add(T x) {

        // Set boolean var to return success of adding new node
        boolean add_success = false;

        // If root is null go here and assign the new node to root.
        if (root == null){

            // Allocate new node and insert it to root
            Entry<T> new_root = new Entry<>(x, null, null);
            root = new_root;

            // Increase size by 1 and return true
            size++;
            add_success = true;
        }
        // If root is not null then find where to place new node
        else {

            // Find if x exist in tree or find the lowest point where
            //  we can place new node either left or right of parent. 
            Entry<T> element_extracted = set_find(x);

            // If x does not exist in tree, place 
            // either left or right of parent node
            if (x.compareTo(element_extracted.element) != 0){
                
                // Place left of parent
                if (x.compareTo(element_extracted.element) < 0){
                    element_extracted.left = new Entry<>(x, null, null);
                }
                // Place right of parent
                else {
                    element_extracted.right = new Entry<>(x, null, null);
                }
                // Increase size + 1
                size++;
                add_success = true;
            }
        }
        // Reset Stack and return success of adding node, true or false.
        binary_stack = null;
        return add_success;
    }

    ////////////////////////////////

    
    // remove() function that removes elements
    public T remove(T x){
        return null;
    }


    // Helper function for remove and add
        

    private Entry<T> set_find(T x){

        binary_stack = new Stack<>();
        binary_stack.add(null);
        Entry<T> return_node = find(root, x);

        return return_node;
    }

    // find() Function finds element if it exist or not.
    private Entry<T> find(Entry<T> root, T x){

        // Create a null Entry, return back Entry in tree if found

        Entry<T> current_entry = root;
        boolean found_element = false;


        // If we root is null or root equals
        // to new element, return back the node.
        if (current_entry == null || current_entry.element == x){
            found_element = true;
        }

        // Create boolean loop to stop while()
        boolean stop_loop = false;    
        
        // Loop until we find leaf of a perfect parent or
        // if there already exists a x in the tree.
        while(found_element == false && stop_loop == false){

            // Go here if x < current.element
            if(x.compareTo(current_entry.element) < 0){

                // If left is null, stop while() and 
                // return current parent node
                if (current_entry.left == null){
                    stop_loop = true;
                }
                // If left is not null, add "current_entry"
                // to stack and assign it to its own left child 
                else{
                    binary_stack.add(current_entry);
                    current_entry = current_entry.left;
                }
            }
            // If x exist, break loop and return "current_entry"
            else if(x.compareTo(current_entry.element) == 0 ){
                stop_loop = true;
            }
            // if "current_entry" is null, stop while()
            // and return current parent node
            else if(current_entry.right == null){
                stop_loop = true;
            }
            // If not null assign "current_entry"
            //  to its own right child
            else{
                binary_stack.add(current_entry);
                current_entry = current_entry.right;
            }
        }
        // Return Node 
        return current_entry;
    }
    // splice()

    ////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////

 

    ////////////////////////////////////////////////////////////////////////////////
    /*
    // Start of Optional problems
     // Optional problem : Iterate elements in sorted order of keys
     //Solve this problem without creating an array using in-order traversal (toArray()).
     
    */
    public Iterator<T> iterator() {
        return null;
    }
    /*
    // Optional problem
    public T min() {
        return null;
    }

    public T max() {
        return null;
    }

    // Optional problem.  Find largest key that is no bigger than x.  Return null if there is no such key.
    public T floor(T x) {
        return null;
    }

    // Optional problem.  Find smallest key that is no smaller than x.  Return null if there is no such key.
    public T ceiling(T x) {
        return null;
    }

    // Optional problem.  Find predecessor of x.  If x is not in the tree, return floor(x).  Return null if there is no such key.
    public T predecessor(T x) {
        return null;
    }

    // Optional problem.  Find successor of x.  If x is not in the tree, return ceiling(x).  Return null if there is no such key.
    public T successor(T x) {
        return null;
    }

   // Optional: Create an array with the elements using in-order traversal of tree
    public Comparable[] toArray() {
        Comparable[] arr = new Comparable[size];
        // write code to place elements in array here
        return arr;
    }
    // End of Optional problems
     */
    ////////////////////////////////////////////////////////////////////////////////


    public static void main(String[] args) throws FileNotFoundException {

        // We create "BinarySearchTree" object
        BinarySearchTree<Long> bst = new BinarySearchTree<>();
        
        // Create "Scanner" varible
        Scanner sc;

        // Check if user inputed a file
        if (args.length > 0) {
            
            // Assigning file to "file" object and passing it to sc
            File file = new File(args[0]);
            sc = new Scanner(file);

            // If file does not exist, get user input
        } else {
            sc = new Scanner(System.in);
        }

        String operation = "";
        long operand = 0;
        int modValue = 999983;
        long result = 0;


        // Initialize the timer
        
        //Timer timer = new Timer();

        // Accoring to the file were using, we 
        while (!((operation = sc.next()).equals("End"))) {
            switch (operation) {
                case "Add": {
                    operand = sc.nextInt();

                    System.out.println("ADDING");
                    if (bst.add(operand)) {
                        result = (result + 1) % modValue;
                    }
                    
                    break;
                }
                case "Remove": {

                    System.out.println("REMOVE");
                    operand = sc.nextInt();
                    
                    if (bst.remove(operand) != null) {
                        result = (result + 1) % modValue;
                    }
                    
                    break;
                }
                case "Contains": {
                    operand = sc.nextInt();
                    /*  
                    if (bst.contains(operand)) {
                        result = (result + 1) % modValue;
                    }
                   */
                    break;
                }
            }
        }

    
        // End Time
        //timer.end();

        System.out.println(result);
        //System.out.println(timer);
    }


    public void printTree() {
        System.out.print("[" + size + "]");
        printTree(root);
        System.out.println();
    }

    // Inorder traversal of tree
    void printTree(Entry<T> node) {
        if (node != null) {
            printTree(node.left);
            System.out.print(" " + node.element);
            printTree(node.right);
        }
    }
}




