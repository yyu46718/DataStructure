import java.util.ArrayList;
import java.util.LinkedList;

public class LinkedListMap<K,V> implements Map<K,V> {
  private class Node{
    public K key;
    public V value;
    public Node next;

    public Node(K key, V value, Node next){
      this.key = key;
      this.value = value;
      this.next = next;
    }

    public Node(K key, V value){
      this(key, value,null);
    }
    public Node(){
      this(null, null, null);
    }
    @Override
    public String toString(){
      return key.toString() +": " +value.toString();
    }
  }

  private Node dummyHead;
  private int size;

  public LinkedListMap(){
    dummyHead = new Node();
    size = 0;
  }

  public int getSize(){
    return size;
  }
  public boolean isEmpty(){
    return (size==0);
  }
  //helper function
  private Node getNode(K key){
    Node cur = dummyHead.next;
    while(cur!=null){
      if(cur.key.equals(key)){
        return cur;
      }
      cur = cur.next; //without it will cause infinite loop
    }
    return null;
  }

  @Override
  public boolean contains(K key){
    return (getNode(key)!=null);
  }

  @Override
  public V get(K key){
    Node node = getNode(key);
    return node == null ?null : node.value;
  }

  /**
   * if kv doesnt exist, add it; else override existing value
   * @param key
   * @param value
   */
  public void add(K key, V value){
    Node node = getNode(key);
    if(node == null){
      dummyHead.next = new Node(key, value, dummyHead.next);
      size++; //caused a bug bc didn't maintain size
    }
    else{
      node.value = value;
    }
  }

  public void set(K key, V newValue){
    Node node = getNode(key);
    if(node!= null){
      node.value = newValue;
    }
    else{
      throw new IllegalArgumentException(key+" doesn't exist");
    }
  }

  /**
   * search
   * @param key
   * @return the value that was just deleted
   */
  public V remove(K key){
    Node prev = dummyHead;
    while(prev.next!=null){
      if(prev.next.key.equals(key)){
        Node del = prev.next;
        prev.next = del.next;
        del.next = null;
        size--;
        return del.value;
      }
      else{
        prev = prev.next;
      }
    }
    return null;
  }
  public static void main(String[] args){
    System.out.println("Pride and Prejudice");

    ArrayList<String> pWords = new ArrayList<>();
    if(FileOperation.readFile("/Users/yaoy/NEU/Data_Structures/Set/src/main/resources/pride-and-prejudice.txt", pWords)){
      System.out.println("total words: "+pWords.size());

      LinkedListMap<String, Integer> map1 = new LinkedListMap<>();
      for(String word:pWords){
        if(map1.contains(word)){
          map1.set(word, map1.get(word)+1);
        }
        else{
          map1.add(word,1);
        }
      }
      System.out.println("Total unique words: "+map1.getSize());
      System.out.println("frequency of pride"+ map1.get("pride"));
    }

  }
}
