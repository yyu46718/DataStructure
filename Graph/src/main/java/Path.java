import java.sql.SQLOutput;
import java.util.Stack;
import java.util.Vector;

public class Path {
  private Graph graph;
  private int source; //starting point of path
  private boolean[] visited;
  private int[] from;

  private void dfs( int v ){
    visited[v] = true;
    for (int i : graph.adj(v)){ //go thru every vertices connected to v
      if( !visited[i]){
        from[i] = v; //vertex that originated i is v
        dfs(i);
      }
    }
  }

  public Path(Graph graph, int source){
    this.graph = graph;
    assert source >= 0 && source < graph.getVertex();
    this.source = source;
    visited = new boolean[graph.getVertex()];
    from = new int[graph.getVertex()];

    for(int i = 0; i < graph.getVertex(); i++){
      visited[i] = false;
      from[i] = -1;
    }
    dfs(source); //path algo
  }

  boolean hasPath (int w){
    assert w >= 0 && w < graph.getVertex();
    return visited[w];
  }

  Vector<Integer> path(int w){
    assert hasPath(w);

    Stack<Integer> stack = new Stack<Integer>();
    int p = w;
    while (p != -1){ //why is it not from[p] != -1
      stack.push(p);
      p = from[p];
    }

    Vector<Integer> res = new Vector<Integer>();
    while( !stack.isEmpty()){
      res.add( stack.pop());
    }
    return res;
  }

  void showPath(int w){
    assert hasPath(w);

    Vector<Integer> res = path(w);
    for(int i = 0; i < res.size(); i++){
      System.out.print(res.elementAt(i));
      if(i == res.size()-1){
        System.out.println();
      }
      else{
        System.out.print(" -> ");
      }
    }
  }
}
