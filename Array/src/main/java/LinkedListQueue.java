public class LinkedListQueue<E> implements Queue<E> {

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
    @Override
    public String toString(){
      return value.toString();
    }
  }
  private Node head,tail;
  private int size;

  public LinkedListQueue(){
    head = null;
    tail = null;
    size = 0;
  }

  @Override
  public int getSize() {
    return size;
  }

  @Override
  public boolean isEmpty() {
    return size==0;
  }

  @Override
  //enqueue happen from the back
  public void enqueue(E value) {
    //determine if end is null and queue is empty
    if(tail == null){ //this means queue is empty
      tail = new Node(value); //add element to the end
      head = tail; //mistake: forgot
    }
    else {
      tail.next = new Node(value);
      tail = tail.next;
    }
    size++;
  }

  @Override
  public E dequeue() {
    if(isEmpty()){
      throw new IllegalArgumentException("queue is empty, cannot dequeue");
    }
    Node ret = head;
    head = head.next;
    ret.next = null;
    //
    if(head == null){
      tail = null;
    }
    size--;
    return ret.value; //needs to return ret.value not ret
  }

  @Override
  public E getFront() {
    if(isEmpty()){
      throw new IllegalArgumentException("queue is empty, cannot get front");
    }
    return head.value;
  }

  @Override
  public String toString(){
    StringBuilder sb = new StringBuilder();
    sb.append("Queue: front ");
    Node cur = head; //set a current pointer
    while(cur!=null){ //as long as current is not at the last element or empty queue
      sb.append(cur + "->");
      cur = cur.next;
    }
    sb.append("NULL back");
    return sb.toString();
  }
  public static void main(String[] args) {
    LinkedListQueue<Integer> queue = new LinkedListQueue<>();
    for (int i  = 0; i < 5; i++){
      queue.enqueue(i);
      System.out.println(queue);
    }
    queue.dequeue();
    System.out.println(queue);

  }

}
