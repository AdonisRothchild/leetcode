import com.adonis.until.UnionFind;
import org.junit.Test;

import java.util.concurrent.Semaphore;

public class Twenty5Leetcode
{
    @Test
    public void testMain()
    {

    }

    class H2O
    {

        Semaphore h = new Semaphore(2);
        Semaphore o = new Semaphore(0);

        public H2O()
        {

        }

        public void hydrogen(Runnable releaseHydrogen) throws InterruptedException
        {
            h.acquire();
            // releaseHydrogen.run() outputs "H". Do not change or remove this line.
            releaseHydrogen.run();
            o.release();
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
}
