public class LinkedListPractice<E extends Comparable<E>> {
  private class Node{
    public E value;
    public Node next;

    public Node(E value, Node next){
      this.value = value;
      this.next = next;
    }
    public Node(E value){
      this(value, null);
    }
    public Node(){
      this(null, null);
    }
  }
  private Node head;
  private int size;
  public LinkedListPractice(){
    head = null; //set head to null
    size = 0;
  }

  public void add(E value){
    if(head==null){
      head = new Node(value,null);
    }
    else {
      Node cur = head;
      while (cur.next != null) {//bug
        cur = cur.next;
      }
      cur.next = new Node(value);//bug
    }
    size++;
  }
  @Override
  public String toString(){
    StringBuilder sb = new StringBuilder();
    sb.append("LinkedList: ");
    Node cur = head;
    while(cur!=null){
      sb.append(cur.value+"->");
      cur = cur.next;
    }
    sb.append("NULL");
    return sb.toString();
  }
  public E getNth(int search){
    int count = 0;
    Node cur = head;
    while(cur!=null){
      if(count==search){
        return cur.value;
      }
      count++;
      cur = cur.next;
    }
    return null;
  }

  public void deleteList(){
    Node cur = head;
    while(cur!=null){
      Node next = cur.next;
      cur.next = null;
      cur = next;
    }
    head = null;
    size=0;
  }

  public E pop(){
    if(head==null){
      throw new IllegalArgumentException("empty list");
    }
    E ret = head.value;
    head = head.next;
    size--;
    return ret;
  }
  public void insertNth(int index, E value){
    if(index<0 || index>size){
      throw new IllegalArgumentException("index illegal");
    }
    if(index==0){
      Node prevHead = head;
      head = new Node(value, prevHead);
    }
    else{
      int count = 0;
      Node cur = head;
      while(index-1>count){
        cur = cur.next;
        count++;
      }
      Node toInsert = new Node(value, cur.next);
      cur.next = toInsert;
    }
    size++;
  }

  public void sortedInsert(E value){
    if(size==0){//order matters in this case bc if head is null head.val would throw error
      Node head = new Node(value, null);
      size++;
      return;
    }
    if(head.value.compareTo(value)>=0){
      Node temp = head;
      Node newHead = new Node(value, temp);
      newHead.next = head;
      size++;
      return;
    }
    if(head.value.compareTo(value)<0 && (size==1||head.next.value.compareTo(value)>=0)) {
      Node temp = head.next;
      head.next = new Node(value,temp);
      size++;
      return;
    }
    else{
      Node cur = head;
      Node forward = cur.next;
      while(forward.value.compareTo(value)>=0){
        cur = forward;
        forward = forward.next;
      }
      Node toInsert = new Node(value, cur.next);
      cur.next = toInsert;
      size++;
    }
  }

  public static void main(String[] args) {
    LinkedListPractice p = new LinkedListPractice();
    System.out.println(p);
    p.add(1);
    p.add(3);
    p.add(5);
    p.add(10);
    System.out.println(p);
    p.sortedInsert(12);
//    System.out.println(p.getNth(0));
//    p.insertNth(0,100);
    System.out.println(p);
    System.out.println(p.size);
  }
}
