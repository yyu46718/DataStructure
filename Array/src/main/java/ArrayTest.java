
public class ArrayTest {
  public static void main(String[] args) {
    /**
     * testing for intro to array
     */
//    int[] arr = new int[10];
//    for (int i=0; i<arr.length;i++){
//      arr[i] = i;
//      System.out.println(arr[i]);
//      System.out.println("---------");
//      System.out.println(i);
//    }

////assign value directly in {}
//    int[] score = new int[]{20,30,40};
//    for (int i:score){
//      System.out.println(i);
//    }

    /**
     * testing for adding, removing value to array and override toString()
     */
    Array<Integer> arr = new Array<Integer>(10);
    for (int i =0; i<10; i++){
      arr.addLast(i);
    }
    System.out.println(arr);

    arr.addFirst(100);
    System.out.println(arr);

    arr.add(5,55);
    System.out.println(arr);

//    System.out.println(arr.get(3));
//
//    arr.set(3,66);
//    System.out.println(arr);
//    //removing
//    arr.remove(1);
//    System.out.println(arr);
//
//    arr.removeElement(100);
//    System.out.println(arr);
//
//    System.out.println(arr.find(12));

//    arr.removeElement(12);
//    System.out.println(arr);

  }
}
