import java.util.ArrayList;

public class BSTSet<E extends Comparable<E>> implements Set<E>  {
  private BST<E> bst;

  //constructor
  public BSTSet(){
    bst = new BST<>();
  }

  @Override
  public void add(E e) {
    bst.add(e);
  }

  @Override
  public void remove(E e) {
    bst.remove(e);
  }

  @Override
  public boolean contains(E e) {
    return bst.contains(e);
  }

  @Override
  public int getSize() {
    return bst.getSize();
  }

  @Override
  public boolean isEmpty() {
    return bst.isEmpty();
  }


    public  static void main(String[] args){
      System.out.println("Pride and Prejudice");

      ArrayList<String> pWords = new ArrayList<>();
      if(FileOperation.readFile("/Users/yaoy/NEU/Data_Structures/Set/src/main/resources/pride-and-prejudice.txt", pWords)){
        System.out.println("total words: "+pWords.size());

        BSTSet<String> set1 = new BSTSet<>();
        for(String word:pWords){
          set1.add(word);
        }
        System.out.println("Total unique words: "+set1.getSize());
      };

    }
}
