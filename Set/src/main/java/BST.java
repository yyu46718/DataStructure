import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
public class BST<E extends Comparable<E>> {
  //not allow repeated nodes;
  private class Node{
    public E e;
    public Node right, left;

    Node(E e){
      this.e = e;
      right = null;
      left = null;
    }
  }

  private Node root;
  private int size; //how many nodes there are in the tree

  BST(){
    root = null;
    size = 0;
  }

  public int getSize(){
    return size;
  }

  public boolean isEmpty(){
    return size==0;
  }

  public void add(E e) { //para can be anything so it's E
    if (root == null){
      root = new Node(e);
      size++;
    }
    else addHelper(root, e);
  }

  //private function using node as root to insert new element e;
  private void addHelper(Node node, E e){
    /*
    base case to check
    node.val == e
    e < node.val  insert to left
    e > node.val insert to right
    * */
    if(e.equals(node.e)){
      return; //return can be nothing if the function is void function
    }
    else if (e.compareTo(node.e)<0 && node.left ==null){
      node.left = new Node(e); //root's left is set a new node with val of e;
      size++; //adjust size is must
      return; //to end the function here
    }
    else if (e.compareTo(node.e)>0 && node.right ==null){
      node.right = new Node(e);
      size++;
      return;
    }
    //getting to base case
    if(e.compareTo(node.e)<0){
      addHelper(node.left, e);
    }
    else{
      addHelper(node.right, e);
    }
  }

  public boolean contains(E e){
    return containsHelper(root, e);
  }

  private boolean containsHelper(Node node, E e){
    /*
     * four possible outcomes
     * 1. found value
     * 2. value doesn't exist
     * 3. value is in left tree
     * 4. value is in right tree
     * */
    if(node ==null)
      return false;

    if(node.e.equals(e)){
      return true;
    }
    else if(e.compareTo(node.e)<0){
      return containsHelper(node.left, e);
    }
    else //(e.compareTo(node.e)>0)
      return containsHelper(node.right,e);
  }

  /**preOrder Traversing, does not take para bc user can just call and
   * it will traverse through
   *
   */

  public void preOrder(){
    preOrderHelper(root);
  }

  private void preOrderHelper(Node node){
    if(node == null)
      return;
    System.out.println(node.e);
    preOrderHelper(node.left);
    preOrderHelper(node.right);
  }


  /** preOrder traverse non_recursively using stack
   * push root
   * pop root
   * push right child
   * push left child
   * pop left child
   */
  public void preOrderNR(){
    Stack<Node> stack = new Stack<>(); //<NODE> not <E>
    if(root==null)
      return;
    stack.push(root);
    while(!stack.isEmpty()){
      Node node = stack.pop();
      System.out.println(node.e);
      if(node.right!=null)
        stack.push(node.right);
      if(node.left!=null)
        stack.push(node.left);
    }
  }

  /**
   * level order - width first traversing using a queue
   * queue root to start the queue
   * queue left child
   * queue right child
   * dequeue left child
   * queue left child's left child
   * queue left child's right child
   *
   * node java.util.queue is a interface not a class, so need to point
   * an actual data structure to implement, here we use linkedlist
   */
  public void levelOrder(){
    Queue<Node> queue = new LinkedList<>();
    queue.add(root);
    while(!queue.isEmpty()){
      Node node = queue.remove();
      System.out.println(node.e);
      if(node.left !=null){
        queue.add(node.left);
      }
      if(node.right!=null){
        queue.add(node.right);
      }
    }
  }

  /**
   *find the min and max val
   * min = most left side of leaf
   * max = most right side of leaf
   */
  public E min(){
    if(size==0){
      throw new IllegalArgumentException("bst is empty");
    }
    return minHelper(root).e;
  }
  /**
   * using node to find the min val under that node
   *
   */
  private Node minHelper(Node node){
    if(node.left==null)
    {return node;}
    return minHelper(node.left);
  }

  /**
   * find max value
   */
  public E max(){
    if(size==0){
      throw new IllegalArgumentException("bst is empty");
    }
    return maxHelper(root).e;
  }
  private Node maxHelper(Node node){
    if(node.right == null)
      return node;
    return maxHelper(node.right);
  }

  /**
   * deletion min, and return min val
   * if min is a leaf value, deletion is pointing prev == null
   * else
   * delete the min/max, and connect the rest of the BST to the deleted parent
   */
  public E removeMin(){
    E ret = min();
    root = removeMinHelper(root); //WHY set all of return val to root?
    return ret;
  }

  /**
   * delete min under node
   * return new root of the sub bst after deletion
   */
  private Node removeMinHelper(Node node){
    if(node.left==null){ //no left child, maybe there's right child, but we know node can be deleted
      Node rightNode = node.right; //save the right node before deleting node
      node.right = null; //disconnect the node and the right child
      size--;
      return rightNode; //return value is the root of the remaining new BST
    }
    //pay attention to reassignment
    node.left = removeMinHelper(node.left); //assigning value
    return node;
  }

  /**
   * remove max
   * @return the value that was removed
   */
  public E removeMax(){
    E ret = max();
    root = removeMaxHelper(root);
    return ret;
  }
  private Node removeMaxHelper(Node node){
    if (node.right == null){
      Node leftNode = node.left;
      node.left = null;
      size--;
      return leftNode;
    }
    node.right = removeMaxHelper(node.right);//删除当前节点右子树中的最大值， 将结果返回给当前节点的右孩子
    return node;
  }

  /**remove element specified by user to remove from bst
   * @return void
   */
  public void remove(E e){
    root = removeHelper(root, e);
  }

  /**delete node (root)'s e's value in bst using recursion
   * @return after deletion, the new root's node
   * 1.base case if node is null, searched through but found nothing
   * 2.compare node.e and e to recursively call removeHelper to close in on base case
   * 3. when node is the one we want to delete
   *  a. determine if right or left node is null, store the not null part
   *  b.
   */
  private Node removeHelper(Node node, E e){
    if(node == null){
      return null;
    }
    if (e.compareTo(node.e)<0){
      node.left = removeHelper(node.left, e); //WHY assignment???
      return node;
    }
    else if(e.compareTo(node.e)>0){
      node.right = removeHelper(node.right,e);
      return node;
    }
    else{ //e==node.e
      if(node.left==null){
        Node rightNode = node.right;
        node.right = null;//disconnect node by pointing right side to null
        size--;
        return rightNode; //this becomes the new root after deletion
      }
      if(node.right == null){
        Node leftNode = node.left;
        node.left = null;
        size--;
        return leftNode;
      }
      //left right child both are not null
      // find the right branch min value to replace the deleted node
      else{
        Node successor = minHelper(node.right);//finding the min node
        successor.left = node.left;
        successor.right = removeMinHelper(node.right);
        node.left = node.right = null;
        return successor;
      }
    }
  }

  @Override
  public String toString() {
    StringBuilder res = new StringBuilder();
    generateBST(root, 0, res);
    return res.toString();
  }
  //recursive call to generate line by line each node
  private void generateBST(Node node, int depth, StringBuilder res){
    if(node == null){
      res.append(generateDepth(depth)+"null\n");
      return;
    }
    res.append(generateDepth(depth)+node.e +"\n");
    generateBST(node.left, depth+1, res);
    generateBST(node.right, depth+1, res);
  }

  //build -- to signify depth
  private String generateDepth(int depth){
    StringBuilder res = new StringBuilder();
    for (int i = 0; i < depth; i++){
      res.append("--");
    }
    return res.toString();
  }
}
