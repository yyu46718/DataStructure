public class Components {
  Graph graph;
  private boolean[] visited; //in dfs, to check if the vertex has been visited
  private int ccount; //how many connected clusters in this graph
  private int[] id; //if two vertices are connected, the id would be the same

  //constructor to solve for connected cluster 联通分量
  public Components(Graph graph){
    this.graph = graph;
    visited = new boolean[graph.getVertex()];
    id = new int[graph.getVertex()];
    ccount = 0;

    for(int i =0; i < graph.getVertex(); i++){
      visited[i] = false; //initialize to false and -1
      id[i] = -1;
    }

    for(int i =0; i < graph.getVertex(); i++){
      if(!visited[i]){
        dfs(i);
        ccount++;
      }
    }
  }
  void dfs (int v ){
    visited[v] = true;
    id[v] = ccount;

    for(int i:graph.adj(v)){
      if( !visited[i] ){
        dfs(i);
      }
    }
  }

  int countCluster(){
    return ccount;
  }
}
