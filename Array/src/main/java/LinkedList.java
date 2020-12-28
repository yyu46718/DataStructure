public class LinkedList<E> {
  //user does not need to know there is a stucture called node in implementatin
  private class Node{
    //two member variable, value and next
    public E value;
    public Node next;

    //constructor
    public Node(E value, Node next){
      this.value = value;
      this.next = next;
    }
    public Node(E value){
      //mistake
      this(value, null);
    }
    public Node(){
      this(null, null);
    }

    @Override
    public String toString(){
      return value.toString();
    }
  }



  //LinkedList has head, size
  private Node dummyHead;
  private int size;

  public LinkedList(){
    dummyHead = new Node(null,null);
    size = 0;
  }
  public int getSize(){
    return size;
  }
  public boolean isEmpty(){
    return size==0;
  }

  public void add(int index, E value){
    //determine if index is legal
    if(index<0 || index > size){
      throw new IllegalArgumentException("index is illegal");
    }

    Node prev = dummyHead;
    for(int i = 0; i<index; i++){
      prev = prev.next;
    }
//      Node n = new Node(value);
//      n.next = prev.next;
//      prev.next = n;
      prev.next = new Node(value, prev.next);
      size++;
    }

  public void addLast(E value){
  add(size, value);
  }
  public void addFirst(E value){
//    Node n = new Node(value);
//    n.next = head;
//    head = n;
    //short hand
//    head = new Node(value,head);
    add(0,value);
    size++;
  }

  //get() given index, return value at the index
  public E get(int index){
    if(index <0 || index>size){
      throw new IllegalArgumentException("index illegal");
    }
    Node cur = dummyHead.next;
    for(int i= 0; i< index; i++){
      cur = cur.next;
    }
    return cur.value;
  }
  //getFirst()
  public E getFirst(){
    return get(0);
  }
  //getLast
  public E getLast(){
    return get(size);
  }

  //set() given a index, edit value at that index
  public void set(int index, E value){
    if(index <0 || index>size){
      throw new IllegalArgumentException("index illegal");
    }
    Node cur = dummyHead.next;
    for(int i = 0; i < index; i++){
      cur = cur.next;
    }
    cur.value = value;
  }

  //contains() given a value return bool if the linkedlist has that value
  public boolean contains(E value){
    Node cur = dummyHead.next;
    for (int i = 0; i<size; i++){
      if(cur.value.equals(value)){
        return true;
      }
    }
    return false;
  }

  //delete element at index x(starting at 0) and return the value that was deleted
  // when checking to see if index is legal. index>=size not >size
  //removeLast size -1
  // prev = dummyHead not dummyHead.next
  public E remove(int index){
    if(index<0 || index>= size){
      throw new IllegalArgumentException("index illegal");
    }
    Node prev = dummyHead;
    for(int i = 0; i<index; i++){
      prev = prev.next;
    }
    Node delNode = prev.next;
    prev.next = delNode.next;
    delNode.next = null;
    size--;
    return delNode.value;
  }

  public E removeFirst(){
    return remove(0);
  }
  public E removeLast(){
    return remove(size-1);
  }
  //toString a->b->null
  //mistake: different for loop; sb.append(cur) or sb.append(cur.value)
  public String toString(){
    StringBuilder sb = new StringBuilder();
    sb.append("LinkedList:");
    for (Node cur = dummyHead.next; cur!= null; cur = cur.next){
      sb.append(cur.value);
      sb.append("->");
    }
    sb.append("NULL");
    return sb.toString();
  }
}
