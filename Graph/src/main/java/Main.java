public class Main {

  public static void main(String[] args) {

//    // 使用两种图的存储方式读取testG1.txt文件
//    String filename = "testG1.txt";
//    SparseGraph g1 = new SparseGraph(13, false);
//    ReadGraph readGraph1 = new ReadGraph(g1, filename);
//    System.out.println("test G1 in Sparse Graph:");
//    g1.show();
//
//    System.out.println();
//
//    DenseGraph g2 = new DenseGraph(13, false);
//    ReadGraph readGraph2 = new ReadGraph(g2 , filename );
//    System.out.println("test G1 in Dense Graph:");
//    g2.show();
//
//    System.out.println();
//
//    // 使用两种图的存储方式读取testG2.txt文件
//    filename = "testG2.txt";
//    SparseGraph g3 = new SparseGraph(6, false);
//    ReadGraph readGraph3 = new ReadGraph(g3, filename);
//    System.out.println("test G2 in Sparse Graph:");
//    g3.show();
//
//    System.out.println();
////
//    DenseGraph g4 = new DenseGraph(6, false);
//    ReadGraph readGraph4 = new ReadGraph(g4, filename);
//    System.out.println("test G2 in Dense Graph:");
//    g4.show();

//test for count cluster
//    String filename2 = "testG1.txt";
//    SparseGraph g5 = new SparseGraph(14, false);
//    ReadGraph readGraph5 = new ReadGraph(g5, filename2);
//    Components component2 = new Components(g5);
//    System.out.println("TestG2.txt, Component Count: " + component2.countCluster());

//    String filename = "testG.txt";
//    SparseGraph g = new SparseGraph(7, false);
//    ReadGraph readGraph = new ReadGraph(g, filename);
//    g.show();
//    System.out.println();
//
//    Path path = new Path(g, 0);
//    System.out.println("Path from 0 to 6 : ");
//    path.showPath(6);

    //test for bfs and dfs comparison
    String filename = "testG.txt";
    SparseGraph g = new SparseGraph(7, false);
    ReadGraph readGraph = new ReadGraph(g, filename);
    g.show();

    // 比较使用深度优先遍历和广度优先遍历获得路径的不同
    // 广度优先遍历获得的是无权图的最短路径
    Path dfs = new Path(g,0);
    System.out.print("DFS : ");
    dfs.showPath(6);

    ShortestPath bfs = new ShortestPath(g,0);
    System.out.print("BFS : ");
    bfs.showPath(6);

    System.out.println();

    filename = "testG1.txt";
    SparseGraph g2 = new SparseGraph(13, false);
    ReadGraph readGraph2 = new ReadGraph(g2, filename);
    g2.show();

    // 比较使用深度优先遍历和广度优先遍历获得路径的不同
    // 广度优先遍历获得的是无权图的最短路径
    Path dfs2 = new Path(g2,0);
    System.out.print("DFS : ");
    dfs2.showPath(3);

    ShortestPath bfs2 = new ShortestPath(g,0);
    System.out.print("BFS : ");
    bfs.showPath(3);
  }
}