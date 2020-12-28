public class IndexMinHeap<Item extends Comparable> {

  protected Item[] data;
  protected int[] indexes;
  protected int[] reverse; //reverse index
  protected int count;
  protected int capacity;

  public IndexMinHeap(int capacity){
    data = (Item[])new Comparable[capacity+1];
    indexes = new int[capacity+1];
    reverse = new int[capacity+1];
    count = 0;
    this.capacity = capacity;
  }

  public int size(){
    return count;
  }

  public boolean isEmpty(){
    return count ==0;
  }

  public boolean contain (int i){
    return reverse[i+1]!= 0;
  }

  /**
   * inserting an element to index min heap
   * index of the element is i
   * value of the element is item
   */
  public void insert(int i, Item item){
    assert count + 1 <= capacity;
    assert i >= 0 && i +1 <= capacity;
    assert !contain(i);
  }



}
