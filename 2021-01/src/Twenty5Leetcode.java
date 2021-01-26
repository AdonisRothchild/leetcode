import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.Semaphore;

public class Twenty5Leetcode
{
    @Test
    public void testMain()
    {
        Solution solution = new Solution();
        int[][] envelopes = {{4,5},{4,6},{6,7},{2,3},{1,1}};
        System.out.println(solution.maxEnvelopes(envelopes));

    }
    class H2O {

        Semaphore h = new Semaphore(2);
        Semaphore o = new Semaphore(0);
        public H2O() {

        }

        public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
            h.acquire();
            // releaseHydrogen.run() outputs "H". Do not change or remove this line.
            releaseHydrogen.run();
            o.release();
        }

        public void oxygen(Runnable releaseOxygen) throws InterruptedException {
            o.acquire(2);
            // releaseOxygen.run() outputs "O". Do not change or remove this line.
            releaseOxygen.run();
            h.release(2);
        }
    }

    /**
     * 依然是并查集的问题 用并查集做不出来 使用动态规划得出 执行时间549ms 内存使用39.9MB 待优化
     */
    class Solution {
        public int maxEnvelopes(int[][] envelopes) {

            if(envelopes.length == 0 || envelopes.length == 1)
            {
                return envelopes.length;
            }
            Integer[] size = new Integer[envelopes.length];
            for(int i = 0;i < envelopes.length;i++)
            {
                size[i] = i;
            }
            Arrays.sort(size,(o1,o2) ->{
                int ov1 = envelopes[ o1][0] + envelopes[o1][1];
                int ov2 = envelopes[ o2][0] + envelopes[o2][1];
                return Integer.compare(ov2,ov1);
            });
            int[] maxDp = new int[envelopes.length];
            Arrays.fill(maxDp,1);
            boolean[] haveDp = new boolean[envelopes.length];
            int max = 1;
            for(int i = 0;i < envelopes.length;i++)
            {
                if(!haveDp[i])
                {
                    max = Math.max(getMaxChild(envelopes, maxDp, haveDp, size, i), max);
                }else
                {
                    max = Math.max(maxDp[i],max);
                }
            }
            return max;
//            UnionFind unionFind = new UnionFind(envelopes.length);
           /* for(int i = 0; i < size.length;i++)
            {
                for(int j = i + 1 ; j < size.length;j++)
                {
                    if (i == j)
                    {
                        continue;
                    }
                    if(envelopes[size[i]][0] > envelopes[size[j]][0] && envelopes[size[i]][1] > envelopes[size[j]][1])
                    {
                        unionFind.union(i, j);
                        break;
                    }
                }
            }
            return unionFind.size == 0? 1 : unionFind.size;*/
        }

        public int getMaxChild(int[][] envelopes,int[] maxDp,boolean[] haveDp,Integer[] size,int index)
        {
              int width = envelopes[size[index]][0];
              int height = envelopes[size[index]][1];
              for(int i = index + 1; i < size.length;i++)
              {
                      if(width > envelopes[size[i]][0] && height > envelopes[size[i]][1])
                      {
                          int max  = 1;
                          if(haveDp[i])
                          {
                              max += maxDp[i];
                          }else
                          {
                               max += getMaxChild(envelopes,maxDp,haveDp,size,i);
                          }
                          maxDp[index] = Math.max(max,maxDp[index]);
                      }
              }
              haveDp[index] = true;
              return maxDp[index];
        }
        class UnionFind
        {
            private int[] parents;
            private int[] ranks;
            private int size;

            public UnionFind(int n)
            {
                parents = new int[n];
                ranks = new int[n];
                Arrays.fill(ranks,1);
                for(int i=0;i<n;i++)
                {
                    parents[i] = i;
                }
            }

            public int findSet(int n)
            {
                return parents[n] == n? n:(parents[n] = findSet(parents[n]));
            }

            public void union(int x,int y)
            {
                x = findSet(x);
                y = findSet(y);
                if(x == y)
                {
                    return;
                }
                parents[y] = x;
                ranks[x] += ranks[y];
                size = Math.max(size,ranks[x]);

            }
        }
    }
}
