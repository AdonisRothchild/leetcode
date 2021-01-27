import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Twenty7Leetcode
{
    @Test
    public void testMain()
    {
        //        Solution solution3 = new Solution();
        //        int[] inorder = {};
        //        int[] postorder = {};
        //        System.out.println(solution3.buildTree(null));
        int[][] edge = new int[][] { { 1, 1, 2 }, { 2, 1, 3 }, { 3, 2, 4 }, { 3, 2, 5 }, { 1, 2, 6 }, { 3, 6, 7 },
                { 3, 7, 8 }, { 3, 6, 9 }, { 3, 4, 10 }, { 2, 3, 11 }, { 1, 5, 12 }, { 3, 3, 13 }, { 2, 1, 10 },
                { 2, 6, 11 }, { 3, 5, 13 }, { 1, 9, 12 }, { 1, 6, 8 }, { 3, 6, 13 }, { 2, 1, 4 }, { 1, 1, 13 },
                { 2, 9, 10 }, { 2, 1, 6 }, { 2, 10, 13 }, { 2, 2, 9 }, { 3, 4, 12 }, { 2, 4, 7 }, { 1, 1, 10 },
                { 1, 3, 7 }, { 1, 7, 11 }, { 3, 3, 12 }, { 2, 4, 8 }, { 3, 8, 9 }, { 1, 9, 13 }, { 2, 4, 10 },
                { 1, 6, 9 }, { 3, 10, 13 }, { 1, 7, 10 }, { 1, 1, 11 }, { 2, 4, 9 }, { 3, 5, 11 }, { 3, 2, 6 },
                { 2, 1, 5 }, { 2, 5, 11 }, { 2, 1, 7 }, { 2, 3, 8 }, { 2, 8, 9 }, { 3, 4, 13 }, { 3, 3, 8 },
                { 3, 3, 11 }, { 2, 9, 11 }, { 3, 1, 8 }, { 2, 1, 8 }, { 3, 8, 13 }, { 2, 10, 11 }, { 3, 1, 5 },
                { 1, 10, 11 }, { 1, 7, 12 }, { 2, 3, 5 }, { 3, 1, 13 }, { 2, 4, 11 }, { 2, 3, 9 }, { 2, 6, 9 },
                { 2, 1, 13 }, { 3, 1, 12 }, { 2, 7, 8 }, { 2, 5, 6 }, { 3, 1, 9 }, { 1, 5, 10 }, { 3, 2, 13 },
                { 2, 3, 6 }, { 2, 2, 10 }, { 3, 4, 11 }, { 1, 4, 13 }, { 3, 5, 10 }, { 1, 4, 10 }, { 1, 1, 8 },
                { 3, 3, 4 }, { 2, 4, 6 }, { 2, 7, 11 }, { 2, 7, 10 }, { 2, 3, 12 }, { 3, 7, 11 }, { 3, 9, 10 },
                { 2, 11, 13 }, { 1, 1, 12 }, { 2, 10, 12 }, { 1, 7, 13 }, { 1, 4, 11 }, { 2, 4, 5 }, { 1, 3, 10 },
                { 2, 12, 13 }, { 3, 3, 10 }, { 1, 6, 12 }, { 3, 6, 10 }, { 1, 3, 4 }, { 2, 7, 9 }, { 1, 3, 11 },
                { 2, 2, 8 }, { 1, 2, 8 }, { 1, 11, 13 }, { 1, 2, 13 }, { 2, 2, 6 }, { 1, 4, 6 }, { 1, 6, 11 },
                { 3, 1, 2 }, { 1, 1, 3 }, { 2, 11, 12 }, { 3, 2, 11 }, { 1, 9, 10 }, { 2, 6, 12 }, { 3, 1, 7 },
                { 1, 4, 9 }, { 1, 10, 12 }, { 2, 6, 13 }, { 2, 2, 12 }, { 2, 1, 11 }, { 2, 5, 9 }, { 1, 3, 8 },
                { 1, 7, 8 }, { 1, 2, 12 }, { 1, 5, 11 }, { 2, 7, 12 }, { 3, 1, 11 }, { 3, 9, 12 }, { 3, 2, 9 },
                { 3, 10, 11 } };
        int n = 4;
        Solution1 solution1 = new Solution1();
        System.out.println(solution1.maxNumEdgesToRemove(n, edge));
    }

    public class TreeNode
    {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode()
        {
        }

        TreeNode(int val)
        {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right)
        {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    class Solution
    {
        Map<Integer, TreeNode> map = new HashMap<>();

        public TreeNode buildTree(int[] inorder, int[] postorder)
        {

            for (int i = postorder.length - 1; i >= 0; i--)
            {
                int root = postorder[i];
                int index = Arrays.binarySearch(inorder, root);
                TreeNode treeNode = map.get(index);
            }
            return buildTree(inorder, postorder);
        }

    }

    class Solution1
    {
        public int maxNumEdgesToRemove(int n, int[][] edges)
        {
            int delete = 0;
            UnionFind alice = new UnionFind(n);
            UnionFind bob = new UnionFind(n);
            for (int i = 0; i < edges.length; i++)
            {
                edges[i][1]--;
                edges[i][2]--;
                if (edges[i][0] == 3)
                {
                    if (!alice.union(edges[i][1], edges[i][2]))
                    {
                        delete++;
                    } else
                    {
                        bob.union(edges[i][1], edges[i][2]);
                    }

                }
            }
            for (int i = 0; i < edges.length; i++)
            {
                if (edges[i][0] == 1)
                {
                    if (!alice.union(edges[i][1], edges[i][2]))
                    {
                        delete++;
                    }
                } else if (edges[i][0] == 2)
                {
                    if (!bob.union(edges[i][1], edges[i][2]))
                    {
                        delete++;
                    }
                }
            }
            if (alice.getSetCount() < n - 1 || bob.getSetCount() < n - 1)
            {
                return -1;
            }
            return delete;
        }
    }

    public class UnionFind
    {
        private final int[] parents;
        private final int[] ranks;
        private int setCount;

        public int getSetCount()
        {
            return setCount;
        }

        public void setSetCount(int setCount)
        {
            this.setCount = setCount;
        }

        public UnionFind(int n)
        {
            this.parents = new int[n];
            ranks = new int[n];
            setCount = n;
            for (int i = 0; i < n; i++)
            {
                parents[i] = i;
            }
        }

        public int findSet(int n)
        {
            return parents[n] == n ? n : (parents[n] = findSet(parents[n]));
        }

        public boolean union(int x, int y)
        {
            x = findSet(x);
            y = findSet(y);
            if (x == y)
            {
                return false;
            }
            if (ranks[x] > ranks[y])
            {
                int temp = x;
                x = y;
                y = temp;
            }

            parents[y] = x;
            ranks[x] += ranks[y];
            setCount--;
            return true;
        }

        public boolean connect(int x, int y)
        {
            x = findSet(x);
            y = findSet(y);
            return x == y;

        }
    }

}
