import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;

public class ShortestPath {

  private Graph graph;
  private int source;
  private boolean[] visited;
  private int[] from;
  private int[] order; //order[i] is i's path order;

  public ShortestPath(Graph graph, int source){
    assert source >= 0 && source < graph.getVertex();
    this.graph = graph;
    this.source = source;

    visited = new boolean[graph.getVertex()];
    from = new int[graph.getVertex()];
    order = new int[graph.getVertex()];

    for(int i = 0; i < graph.getVertex(); i++){
      visited[i] = false;
      from[i] = -1;
      order[i] = -1;
    }
    Queue<Integer> q = new LinkedList<>();
    q.add(source);
    visited[source] = true;
    order[source] = 0;

    while( !q.isEmpty() ){
      int v = q.remove();
      for(int i : graph.adj(v)){
        if(!visited[i]){
          visited[i] = true;
          q.add(i);
          from[i] = v;
          order[i] = order[v] + 1; //represent the layer from the root
        }
      }
    }
    for(int i = 0; i < from.length; i++){
      System.out.println(from[i]);
    }
  }
  public boolean hasPath(int w){
    assert w >= 0 && w < graph.getVertex();
    return visited[w];
  }

  public Vector<Integer> path(int w){
    assert hasPath(w);
    Stack<Integer> stack = new Stack<>();
    int p = w;
    while (p != -1) { //-1 represent the root, p is the from[w]
      stack.push(p);
      p = from[p];
    }
    Vector<Integer> res = new Vector<>();
    while( !stack.empty() )
      res.add( stack.pop() );
    return res;
  }

  public void showPath(int w){
    assert hasPath(w);
    Vector<Integer> vec = path(w);
    for(int i = 0; i < vec.size(); i++){
      System.out.print(vec.elementAt(i));
      if(i == vec.size()-1){ //last element
        System.out.println();
      }
      else{
        System.out.print("->");
      }
    }
  }

  public int length(int w){
    assert w >= 0 && w < graph.getVertex();
    return order[w];
  }


}
