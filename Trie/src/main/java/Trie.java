import java.util.TreeMap;

public class Trie {

  private class Node{
    public boolean isWord;
    public TreeMap<Character, Node> next;

    public Node(boolean isWord){
      this.isWord = isWord;
      next = new TreeMap<>();//creating a new TreeMap
    }
    public Node(){
    this(false);
    }
  }

  private Node root;
  private int size; //how many words there are

  public Trie(){
    root = new Node();
    size = 0;
  }

  public int getSize(){
    return size;
  }

  public void add(String word){
    Node cur = root;
    for (int i=0; i<word.length(); i++){
      char c = word.charAt(i);
      if(cur.next.get(c)==null){
        cur.next.put(c, new Node());
      }
      cur = cur.next.get(c);
    }
    if(!cur.isWord){
      cur.isWord = true;
      size++;
    }
  }
  /* delete
  if the cur.next.get(c)== null, then we can delete
  and  isword = false
  * */
  public boolean contains(String word){
    Node cur = root;
    for(int i =0; i<word.length(); i++){
      char c  = word.charAt(i);
      if(cur.next.get(c)==null){
        return false;
      }
      cur = cur.next.get(c);
    }
    return cur.isWord;
  }



}
