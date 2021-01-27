import org.junit.Test;

public class Twenty6Leetcode
{
    @Test
    public void testMain()
    {
        Solution3 solution3 = new Solution3();
        System.out.println(solution3.convertBiNode(null));
    }

    /**
     * 每日一题 1128. 等价多米诺骨牌对的数量 执行时间4ms 内存消耗47.7MB 待优化
     */
    class Solution
    {
        public int numEquivDominoPairs(int[][] dominoes)
        {
            byte[][] isSearch = new byte[10][10];
            int result = 0;
            for (int i = 0; i < dominoes.length; i++)
            {
                if (dominoes[i][0] > dominoes[i][1])
                {
                    int temp = dominoes[i][0];
                    dominoes[i][0] = dominoes[i][1];
                    dominoes[i][1] = temp;
                }
                int count = isSearch[dominoes[i][0]][dominoes[i][1]];
                if (count > 0)
                {
                    result += count;
                }
                isSearch[dominoes[i][0]][dominoes[i][1]]++;

            }
            return result;
        }
    }

    /**
     * 每日一题 1128. 等价多米诺骨牌对的数量 解法二执行时间5ms 内存消耗47.2MB 待优化
     */
    class Solution1
    {
        public int numEquivDominoPairs(int[][] dominoes)
        {
            byte[][] isSearch = new byte[10][10];
            int result = 0;
            for (int i = 0; i < dominoes.length; i++)
            {
                int count = isSearch[dominoes[i][0]][dominoes[i][1]];
                if (dominoes[i][0] > dominoes[i][1])
                {
                    count = isSearch[dominoes[i][1]][dominoes[i][0]];
                }

                if (count > 0)
                {
                    result += count;
                }
                if (dominoes[i][0] != dominoes[i][1])
                {
                    isSearch[dominoes[i][1]][dominoes[i][0]]++;
                }
                isSearch[dominoes[i][0]][dominoes[i][1]]++;

            }
            return result;
        }
    }

    class Solution3
    {
        public TreeNode convertBiNode(TreeNode root)
        {
            root = new TreeNode(1);
            TreeNode left = new TreeNode(2);
            TreeNode right = new TreeNode(3);
            root.left = left;
            root.right = right;

            return cx(root);
        }

        public TreeNode cx(TreeNode root)
        {
            if (root != null)
            {
                if (null != root.left)
                {
                    root = cx(root.left);
                }

                root.right = null;

                if (null != root.right)
                {
                    root = cx(root.right);
                }
            }
            return root;
        }
    }

    public class TreeNode
    {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x)
        {
            val = x;
        }
    }
}
