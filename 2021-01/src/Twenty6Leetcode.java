import org.junit.Test;

public class Twenty6Leetcode
{

    @Test
    public void testMain()
    {
        Solution solution = new Solution();
        TreeNode root  = new TreeNode(1);
        TreeNode left  = new TreeNode(2);
        root.left = left;
        System.out.println(solution.hasPathSum(root,22));
    }
    class Solution {
        public boolean hasPathSum(TreeNode root, int targetSum) {
            boolean result = false;
            if(null == root)
            {
                return 0 == targetSum;
            }
            if(null != root.left)
            {
                result =  result || hasPathSum(root.left,targetSum - root.val);
            }

            if(null != root.right)
            {
                result = result || hasPathSum(root.right,targetSum - root.val);
            }

            return result || (null == root.left&&null == root.right &&root.val == targetSum);
        }
    }

    class TreeNode
    {
        TreeNode left;
        TreeNode right;
        int val;

        public TreeNode(int val)
        {
            this.val = val;
        }
    }
}
