import java.util.Vector;

public class DenseGraph {
  private int n; //num of vertices
  private int m; //num of edges
  private boolean isDirected;
  private boolean [][] g; //graph using a 2d array to represent

  public DenseGraph(int n, boolean isDirected){
    this.n = n;
    m = 0;
    this.isDirected = isDirected;
    g =new boolean[n][n];
    // g初始化为n*n的布尔矩阵, 每一个g[i][j]均为false, 表示没有任和边
    // false为boolean型变量的默认值
  }

  public int getVertex(){
    return n;
  }
  public int getEdge(){
    return m;
  }
  //add an edge between x and y vertex
  public void addEdge(int x, int y){
    assert x >= 0 && x < n;
    assert y >= 0 && y < n;

    if(hasEdge(x, y)){
      return;
    }
    g[x][y] = true;
      if(!isDirected){
        g[y][x] = true;
      }
      m++;
  }

  public boolean hasEdge(int x, int y){
    assert x >= 0 && x < n;
    assert y >= 0 && y < n;
    return g[x][y];
  }

  public Iterable<Integer> adj(int v){
    assert v >= 0 && v < n;
    Vector<Integer> adjV = new Vector<>();
    for(int i = 0; i < n; i++){
      if(g[v][i]){
        adjV.add(i);
      }
    }
    return adjV;
  }

}
