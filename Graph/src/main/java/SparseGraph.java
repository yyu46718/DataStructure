import java.util.Vector;

public class SparseGraph {
  private int n; //nodes or vertex
  private int m; //edge
  private boolean isDirected;
  private Vector<Integer>[] g;

  public SparseGraph(int n, boolean isDirected){
    this.n = n;
    this.isDirected = isDirected;
    m = 0;
    g = new Vector[n];
    for(int i = 0; i < n; i++ ){
      g[i] = new Vector<Integer>();
    }
  }

  public int getVertex(){
    return n;
  }

  public int getEdge(){
    return m;
  }

  public boolean hasEdge(int x, int y){
    assert x>=0 && x < n;
    assert y>=0 && y < n;

    for(int i = 0; i < g[x].size(); i++){
      if(g[x].elementAt(i) == y){
        return true;
      }
    }
    return false;
  }

  public void addEdge(int x, int y) {
    assert x >= 0 && x < n;
    assert y >= 0 && y < n;

    g[x].add(y);
    if (x != y && !isDirected) {
      g[y].add(x);
    }

    m++;
  }

    public Iterable<Integer> adj(int v){
      assert v >= 0 && v < n;
      return g[v];
  }

}
