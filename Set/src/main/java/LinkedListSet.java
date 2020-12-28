import java.util.ArrayList;

public class LinkedListSet<E> implements Set<E> {
  //linkedlist element does not need to extend comparable
  private LinkedList<E> list;
  public LinkedListSet(){
    list = new LinkedList<>();
  }
  @Override
  public void add(E e) {
    //must sepcify no repeat
    if(!list.contains(e)){
      list.addFirst(e);
    }
  }

  @Override
  public void remove(E e) {
    list.removeElemtn(e);

  }

  @Override
  public boolean contains(E e) {
    return list.contains(e);
  }

  @Override
  public int getSize() {
    return list.getSize();
  }

  @Override
  public boolean isEmpty() {
  return list.isEmpty();
  }

    public  static void main(String[] args){
      System.out.println("Pride and Prejudice");

      ArrayList<String> pWords = new ArrayList<>();
      if(FileOperation.readFile("/Users/yaoy/NEU/Data_Structures/Set/src/main/resources/pride-and-prejudice.txt", pWords)){
        System.out.println("total words: "+pWords.size());

        LinkedListSet<String> set1 = new LinkedListSet<>();
        for(String word:pWords){
          set1.add(word);
        }
        System.out.println("Total unique words: "+set1.getSize());
      };

    }


}
