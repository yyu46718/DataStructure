public class BSTTest {
  public static void main(String[] arg){
    BST<Integer> bst = new BST<>();
    int[] nums = {5,3,6,8,4,2};
    for(int i = 0; i< nums.length; i++){
      bst.add(nums[i]);
    }
//    bst.preOrder();
//    System.out.println("______________");
//    bst.preOrderNR();

    bst.levelOrder();
  }

}
