public interface Graph {
  public int getVertex();
  public int getEdge();
  public void addEdge( int x, int y);
  boolean hasEdge( int x, int y);
  void show();
  public Iterable<Integer> adj(int x);

}
