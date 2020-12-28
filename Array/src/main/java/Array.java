public class Array<E> {

  private E[] data;
  private int size;

  //first constructor to set the capacity of the array
  public Array(int capacity) {
    size = 0;
    data = (E[])new Object[capacity];
  }

  //constructors to set default capacity as 10
  public Array() {
    this(10);
  }

  //getters
  public int getSize() {
    return this.size;
  }

  public int getCapacity() {
    return data.length;
  }

  //isEmpty function
  public boolean isEmpty() {
    return this.size == 0;
  }

  //add new element at specific index
  public void add(int index, E value) {

    //check if index is legal, pay attention to size
    if (index > size || index < 0) {
      throw new IllegalArgumentException("index cannot be 0 or greater than size");
    }
    //check if array is full, size == index, if full, resize capacity
    if (size == data.length) {
      resize(2*data.length);
    }
    //move elements to appropriate places: starting with the last element (size-1), until index position

    for (int i = size; i > index; i--) {
      data[i] = data[i - 1];
    }
    data[index] = value;
    //adjust size pointer
    size++;
  }

  //add new element in the end, no need to add further check on index bc checks are done in add()
  public void addLast(E value) {
    add(this.getSize(), value);
  }

  //add new element in beginning
  public void addFirst(E value) {
    add(0, value);
  }


  @Override
  public String toString() {
    StringBuilder sd = new StringBuilder();
    sd.append(String.format("Array: size = %d capacity = %d\n", size, data.length));
    sd.append("[");
    for (int i = 0; i < size; i++) {
      if (i < size - 1) {
        sd.append(data[i] + ",");
      } else {
        sd.append(data[i]);
      }
    }
    sd.append("]");
    return sd.toString();
  }

  public E get(int index) {
    if (index < 0 || index >= size) {
      throw new IllegalArgumentException("index illegal");
    }
    return data[index];
  }
  public E getFirst(){
    return get(0);
  }

  public E getLast(){
    return get(size-1);
  }

  public void set(int index, E value) {
    if (index < 0 || index >= size)
      throw new IllegalArgumentException("index illegal");
    data[index] = value;
  }
//i<size not <data.length bc we dont need to iterate through the whole list
  public boolean contains(E value) {
    for (int i = 0; i < size; i++) {
      if (value.equals(data[i])) {
        return true;
      }
    }
    return false;
  }

  public int find(E value){
    for (int i = 0; i<size; i++){
      if(data[i].equals(value)){
        return i;
      }
    }
    return -1;
  }

  //remove with returning the value that got deleted;
  public E remove(int index){
    if(index <0 || index >=size){
      throw new IllegalArgumentException("index is not legal");
    }
    E deleted = data[index];
    for(int i = index; i<size-1; i++){
      data[i]=data[i+1];
    }
    size--;
    data[size] = null; //the purpose of this line is for loitering object to be recycled; not mandatory
    return deleted;
  }

  public E removeFirst(){
    return remove(0);
  }

  public E removeLast(){
    return remove(size-1);
  }

  public void removeElement(E value){
    if(find(value)!= -1){
      remove(find(value));
    }
    else{
      throw new IllegalArgumentException("the value doesn't exist in array");
    }
  }

  //resizing to dynamically change the capacity of the Array, similar to ArrayList
  private void resize(int newCapacity){
    E[] newData = (E[]) new Object[newCapacity];
    for (int i  = 0; i<size; i++){
      newData[i] = data[i];
    }
    data = newData;
  }
}

