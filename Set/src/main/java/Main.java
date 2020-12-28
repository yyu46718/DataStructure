import java.util.ArrayList;

public class Main {

  private static double testSet(Set<String> set, String fileName){
    long startTime = System.nanoTime();
    System.out.println(fileName);
    ArrayList<String> words = new ArrayList<>();
    if(FileOperation.readFile(fileName, words)){
      System.out.println("Total words: "+ words.size());

      for(String word : words){
        set.add(word);
      }
      System.out.println("Total unique words: "+ set.getSize());
    }
    long endTime = System.nanoTime();
    return (endTime - startTime)/1000000000.0;
  }


  public  static void main(String[] args){
    String fileName = "/Users/yaoy/NEU/Data_Structures/Set/src/main/resources/pride-and-prejudice.txt";
    BSTSet<String> bstSet = new BSTSet<>();
    double time1 = testSet(bstSet, fileName);
    System.out.println("BST: " + time1);
    System.out.println();

    LinkedListSet<String> llset = new LinkedListSet<>();
    double time2 = testSet(llset, fileName);
    System.out.println("LinkedListSet "+time2);
  }

}
