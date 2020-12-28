import java.util.Objects;

public class LoopQueue<E> implements Queue<E> {

  private E[] data;
  private int front, tail;
  private int size;

  public LoopQueue(int capacity) { //c = how many space the user will occupy
    // capacity + 1 is bc loop queue intentionally waste one space
    data = (E[]) new Object[capacity + 1];
    //instantiate all member variables
    front = 0;
    tail = 0;
    size = 0;
  }

  // set ca = 10 as default by using this()
  public LoopQueue() {
    this(10);
  }

  public int getCapacity() {
    return data.length - 1;
  }

  @Override
  public boolean isEmpty() {
    return front == tail;
  }


  @Override
  public E getFront() {
    if (isEmpty()) {
      throw new IllegalArgumentException("queue is empty");
    }
    return data[front];
  }

  @Override
  public int getSize() {
    return size;
  }

  @Override
  public void enqueue(E value) {
    /*
    1. if (tail+1%c ==front the [] is full and need to resize
    2. add the value
    3. adjust tail size pointers; not % data.length (true number of spaces not user defined capacity)
    * */
    if ((1 + tail) % data.length == front) {
      resize(getCapacity() * 2);
    }
    data[tail] = value;
    tail = (tail + 1) % data.length;
    size++;
  }

  @Override
  public E dequeue() {
    /*
    1. determine if its empty, throw exception
    2.remove element
    3. adjust pointer size and front
    4. return the value that was dequeued;
    5. use lazy approach to readjust size of the queue; if the size = 1/4 capacity --> reduce capacity by half
    * */
    if (isEmpty()) {
      throw new IllegalArgumentException("queue is empty");
    }
    E ret = data[front];
    data[front] = null;
    size--;
    front = (front + 1) % data.length;
    if (size == getCapacity() / 4
        && getCapacity() / 2 != 0) { //ensure the capacity didn't get reset to 0
      resize(getCapacity() / 2);
    }
    return ret;
  }

  private void resize(int newCapacity) {
    /*
    1.create new object[] with new capacity
    2. iterate through old object[] and copy values to new object[]
    3. adjust pointers
    * */
    E[] newData = (E[]) new Object[newCapacity];
    for (int i = 0; i < size; i++) {
      newData[i] = data[(i + front) % data.length];
    }
    data = newData;
    front = 0;
    tail = size;
  }

  @Override
  public String toString() {
    /*
    i+front)%data.length same as (i+1)%data.length
    * */
    StringBuilder sb = new StringBuilder();
    sb.append(String.format("Queue: size =%d, capacity = %d\n[", size, getCapacity()));
    for (int i = front; i != tail; i = (i + 1) % data.length) {
      sb.append(data[i]);
      if ((i + 1) % data.length != tail) {
        sb.append(", ");
      }
    }
    sb.append("] tail");
    return sb.toString();
  }

  public static void main(String[] args) {
    LoopQueue<Integer> queue = new LoopQueue<>(10);
    for (int n = 0; n < 10; n++) {
      queue.enqueue(n);
    }
    System.out.println(queue);
    System.out.println();
    for (int n = 0; n < 10; n++) {
      if (n % 3 == 2) {
        queue.dequeue();
        System.out.println(queue);
      }
    }
  }
}
