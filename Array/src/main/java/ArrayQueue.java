public class ArrayQueue<E> implements Queue<E> {
  //member variable
  private Array<E> array;
  //constructor
  public ArrayQueue(int capacity){
    array = new Array<>(capacity);
  }
  public ArrayQueue(){
    array = new Array<>();
  }

  @Override
  public int getSize() {
    return array.getSize();
  }

  @Override
  public boolean isEmpty() {
    return array.isEmpty();
  }

  public int getCapacity(){
    return array.getCapacity();
  }
  @Override
  public void enqueue(E value) {
    array.addLast(value); // because this is no return bc it's void. don't need to write return
  }

  @Override
  public E dequeue() {
    return array.removeFirst(); //time complexity is O(n) bc after removing the first element, all
    //subsequent element will need to move up by 1 so this operation is costly and needs optimizing
  }

  @Override
  public E getFront() {
    return array.getFirst();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("ArrayQueue front[");
    for (int i  = 0; i< array.getSize(); i++){
      sb.append(array.get(i));
      if(i != array.getSize()-1){
        sb.append(", ");
      }
    }
    sb.append("] tail");
    return sb.toString();
  }

  public static void main(String[] args){
    ArrayQueue<Integer> queue = new ArrayQueue<>(10);
    for(int n = 0; n< 10; n++) {
      queue.enqueue(n);
    }
    System.out.println(queue);
    System.out.println();
    for(int n = 0; n< 10; n++) {
      if (n%3 ==2){
        queue.dequeue();
        System.out.println(queue);
      }
    }

    System.out.println(2%8);
  }
}
