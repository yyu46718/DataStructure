import java.util.Random;
public class MaxHeap<E extends Comparable<E>> {
  private Array<E> data;

  public MaxHeap(int capacity){
    data = new Array<>(capacity);
  }

  public MaxHeap(){
    data = new Array<>();
  }
  //heapify constructor
  public MaxHeap(E[] arr){
    data = new Array<>(arr);
    for(int i = parent(arr.length-1); i>=0; i--){
      siftDown(i);
    }
  }

  public int size(){
  return data.getSize();
  }

  public boolean isEmpty(){
  return data.isEmpty();
  }

  /**
   * in a complete binary tree (left side on every layer is full)
   * @param index
   * @return this index's parent index
   */
  public int parent(int index){
    if(index==0){
      throw new IllegalArgumentException("this index is parent and has no further parent");
    }
    return (index-1)/2;
  }

  public int leftChild(int index){
    return index*2 +1;
  }
  public int rightChild(int index){
    return index*2+2;
  }

  /**
   * adding element to the end of the array and use helpers siftup and swap to accomplish that
   * @param e value to add
   */
  public void add(E e){
    data.addLast(e);
    siftUp(data.getSize()-1);
  }

  private void siftUp(int k){
    while(k>0 && data.get(parent(k)).compareTo(data.get(k))<0){
      data.swap(k,parent(k));
      k = parent(k);
    }
  }

  public E findMax(){
    if(data.getSize()==0){
      throw new IllegalArgumentException("array is empty");
    }
    return data.get(0);
  }

  public E extractMax(){
    E ret = findMax();
    data.swap(0,data.getSize()-1);
    data.removeLast();
    siftDown(0);
    return ret;
  }

  /**
   * loop while there is a left child;
   * save the bigger between left and right child in max
   * use max value to compare to root k value
   * if max value > k value then swap these two
   * reassign k = max
   * @param k the headnode but could be small
   */
  private void siftDown(int k) {
    while (data.getSize() > leftChild(
        k)) { //when leftchild > than total size, that means left child doesnt exist so look can break
      //compare
      int max = leftChild(k);
      if (rightChild(k) < data.getSize() &&
          data.get(rightChild(k)).compareTo(data.get(leftChild(k)))
              > 0) { //the right child is bigger
        max = rightChild(k);
      }
      if (data.get(k).compareTo(data.get(max)) >= 0) {
        break;
      } else {
        data.swap(max, k);
      }
      k = max;
    }
  }

  /**
   * replace the biggest element with a new element; run time O(logn)
   * @param e new element
   * @return the old biggest element that got replaced
   */
  public E replace(E e){
    E ret = findMax();
    data.set(0,e);
    siftDown(0);
    return ret;

  }
  public static void main(String[] args){
    int n= 1000000;
    MaxHeap<Integer> maxHeap = new MaxHeap<>();
    Random random = new Random();
    for(int i = 0; i<n; i++){
      maxHeap.add(random.nextInt(Integer.MAX_VALUE));
    }
    int[] arr = new int[n];
    for(int i = 0; i<n; i++) {
      arr[i] = maxHeap.extractMax();
    }
    for(int i = 1; i<n; i++) {
      if (arr[i - 1] < arr[i]) {
        throw new IllegalArgumentException("error");
      }
    }
    System.out.println("test completed");

  }
}
