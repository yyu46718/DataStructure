import java.util.ArrayList;

public class BSTMap<K extends Comparable<K>, V> implements Map<K,V>  {
//key must be able to compare
  private class Node{ //this class is private but member variable are public
    public Node left, right;
    public K key;
    public V value;

    public Node(K key, V value){
      this.key = key;
      this.value = value;
      left = null;
      right = null;
    }

}
  private Node root;
  private int size;

  public BSTMap(){
    root = null;
    size = 0;
  }

  @Override
  public int getSize(){
    return size;
  }
  @Override
  public boolean isEmpty(){
    return size==0;
  }

  @Override
  public void add(K key, V value){
    root = addHelper(root, key, value);
  }

  /**
   * root as node, adding this KV pair to BST using recursion
   * @param node the root of the new BST
   * @param key
   * @param value
   * @return
   */
  private Node addHelper(Node node, K key, V value){
    if(node==null){
      size++;
      return new Node(key, value); //straight return
    }
    if(key.compareTo(node.key)>0){ //the new key is bigger so insert to the right of BST
      node.right = addHelper(node.right, key, value);
    }
    else if(key.compareTo(node.key)<0){
      node.left = addHelper(node.left, key, value);
    }
    else //key.compareTo(node.key) ==0
      node.value = value;
    return node;
  }

  /**
   * helper function to get the node that is key
   * @param node root of the BST
   * @param key key value that we are looking for
   * @return the node that is key
   */
  private Node getNode(Node node, K key){
    if(node==null){
      return null;
    }

    if(key.compareTo(node.key)==0){
      return node;
    }
    else if(key.compareTo(node.key)>0){
      return getNode(node.right,key);
    }
    else //(key.compareTo(node.key)<0){
      return getNode(node.left,key);
  }

  @Override
  public boolean contains(K key){
    return getNode(root, key)!=null;
  }

  public V get(K key){ //need to consider when key doesn't exist
    Node node = getNode(root,key);
    return node.value==null ? null : node.value;
  }

  @Override
  public void set(K key, V newValue){
    Node node = getNode(root,key);
    if(node!=null){
      node.value = newValue;
    }
    else{
      throw new IllegalArgumentException(key+" does not exist;");
    }
  }

  /**
   *
   * @param node the root of the BST
   * @return the node that is the smallest
   */
  private Node min(Node node){
    if(node.left == null){
      return node;
    }
    return min(node.left);
  }

  /**
   * when note doesnt have left child, save right child and make right root as the new root
   * @param node node to be deleted
   * @return the new root
   */
  private Node removeMin(Node node){
    if(node.left == null){
      Node rightNode = node.right;
      node.right = null;
      size--;
      return rightNode;
    }
    node.left = removeMin(node.left);
    return node;
  }

  @Override
  /**find the node first using getNode() then use helper to remove
   * delete the node that is key and return its value
   */
  public V remove(K key){
    Node node = getNode(root, key);
    if(node == null){
      return null;
    }
    root = removeHelper(root, key);
    return node.value;
  }

  /**
   * helper to remove using recursion
   * @param node root of the current BST
   * @param key the key we want to delete
   * @return the root of the BST after deletion
   */
  private Node removeHelper(Node node, K key){
    //null
    if(node == null){
      return null;
    }
    //compare and recursion
    if(key.compareTo(node.key)>0){ //key searching for is in the right BST
      node.right = removeHelper(node.right,key);
      return node;
    }
    else if(key.compareTo(node.key)<0){
      node.left = removeHelper(node.left,key);
      return node;
    }

    //the current node is the one to remove
    else{ //if the current node is missing either left child or right child
      if(node.left==null){
        Node rightNode = node.right;
        node.right = null;
        size --;
        return rightNode;
      }
      if(node.right == null){
        Node leftNode = node.left;
        node.left = null;
        size--;
        return leftNode;
      }

      //is both left and right are present then need to use Hibbard method
      Node successor = min(node.right);
      successor.right = removeMin(node.right);
      successor.left = node.left;

      node.left = node.right = null;
      return successor;
    }
  }
  public static void main(String[] args){
    System.out.println("Pride and Prejudice");

    ArrayList<String> pWords = new ArrayList<>();
    if(FileOperation.readFile("/Users/yaoy/NEU/Data_Structures/Set/src/main/resources/pride-and-prejudice.txt", pWords)){
      System.out.println("total words: "+pWords.size());

      BSTMap<String, Integer> map2 = new BSTMap<>();
      for(String word:pWords){
        if(map2.contains(word)){
          map2.set(word, map2.get(word)+1);
        }
        else{
          map2.add(word,1);
        }
      }
      System.out.println("Total unique words: "+map2.getSize());
      System.out.println("frequency of pride: "+ map2.get("pride"));
    }

  }

}
