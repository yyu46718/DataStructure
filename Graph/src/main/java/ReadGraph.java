import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ReadGraph {
  private Scanner scanner;

  public ReadGraph(Graph graph, String fileName){
    readFile(fileName);

    try{
      int vertex = scanner.nextInt();
      if (vertex < 0 ){
        throw new IllegalArgumentException("vertices must be non-negatives");
      }
      int edge = scanner.nextInt();
      if(edge <0){
        throw new IllegalArgumentException("edge must be non-negative");
      }

      for(int i = 0; i < edge; i++){
        int v = scanner.nextInt();
        int w = scanner.nextInt();
        assert v >= 0 && v < vertex;
        assert w >= 0 && w < vertex;
        graph.addEdge(v, w);
      }
    }
    catch (InputMismatchException e){
      String token  = scanner.next();
      throw new InputMismatchException("attemps to read an int value  from input but the next token is \""+ token +"\"");
    }
    catch (NoSuchElementException e){
      throw new NoSuchElementException("no more token available");
    }
  }




  private void readFile(String fileName){
    assert fileName != null;
    File file = new File(fileName);
    if(file.exists()){
      FileInputStream fis = null;
      try {
        fis = new FileInputStream(file);
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
      scanner = new Scanner(new BufferedInputStream(fis),"UTF-8");
      scanner.useLocale(Locale.ENGLISH);
    }
    else{
      throw new IllegalArgumentException(fileName + "does not exist");
    }


  }

}
