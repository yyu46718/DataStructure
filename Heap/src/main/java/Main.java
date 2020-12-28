import java.util.Random;
import javax.print.attribute.standard.PrinterMakeAndModel;
import sun.jvm.hotspot.types.JDoubleField;

public class Main {
    private static double testHeap(Integer[] testData, boolean isHeapify){
      long startTime = System.nanoTime();
      MaxHeap<Integer> maxHeap;
      if(isHeapify){
        maxHeap = new MaxHeap<>(testData);
      }
      else{
        maxHeap = new MaxHeap<>();
        for(int num:testData){
          maxHeap.add(num);
        }
      }

      long endTime  = System.nanoTime();
      return (endTime-startTime)/1000000000.0;
    }

  public static void main(String[] args){
      int n = 1000000;
      Random random = new Random();
      Integer[] testData = new Integer[n];
      for(int i = 0; i<n; i++) {
        testData[i] = random.nextInt(Integer.MAX_VALUE);
      }
      double time1 = testHeap(testData, false);
    System.out.println("without heapify: " + time1);
    double time2 = testHeap(testData, true);
    System.out.println("with heapify: "+time2);
  }
}
