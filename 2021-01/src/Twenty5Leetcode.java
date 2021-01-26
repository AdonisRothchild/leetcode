import com.adonis.until.UnionFind;
import org.junit.Test;

/**
 * 1月25日刷题
 */
public class Twenty5Leetcode
{
    @Test
    public void testMain()
    {
        String[] grid = { " /", "/ " };
        Solution1 solution1 = new Solution1();
        System.out.println(solution1.regionsBySlashes(grid));
    }

    class Solution1
    {
        public int regionsBySlashes(String[] grid)
        {
            int n = grid.length;
            UnionFind unionFind = new UnionFind(n * n * 4);
            for (int i = 0; i < n; i++)
            {
                for (int j = 0; j < n; j++)
                {
                    int index = j * 4 + i * 4 * n;
                    if (32 == grid[i].charAt(j))
                    {
                        setUnionFind(unionFind, index, index + 1, index + 2, index + 3);

                    } else if (47 == grid[i].charAt(j))
                    {
                        setUnionFind(unionFind, index, index + 1);
                        setUnionFind(unionFind, index + 2, index + 3);

                    } else if (92 == grid[i].charAt(j))
                    {
                        setUnionFind(unionFind, index, index + 3);
                        setUnionFind(unionFind, index + 1, index + 2);

                    }

                    if (i != n - 1)
                    {
                        setUnionFind(unionFind, index + 3, index + 1 + 4 * n);
                    }

                    if (j != n - 1)
                    {
                        setUnionFind(unionFind, index + 2, index + 4);
                    }
                }
            }
            return unionFind.getSetCount();
        }

        public void setUnionFind(UnionFind unionFind, int... sets)
        {
            for (int i = 0; i < sets.length - 1; i++)
            {
                unionFind.union(sets[i], sets[i + 1]);
            }
        }
    }
}
