import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 *  23号的leetcode练习
 */
public class Twenty3Leetcode
{
    @Test
    public void testMain()
    {
        // int[][] isConnected = {{1,1,0},{1,1,0},{0,0,1}};

    }

    class Solution1
    {
        public int makeConnect(int[][] isConnected)
        {
            UnionFind unionFind = new UnionFind(isConnected.length);
            for (int i = 0; i < isConnected.length; i++)
            {
                for (int j = 0; j < isConnected[i].length; j++)
                {
                    if (isConnected[i][j] == 1)
                    {
                        unionFind.union(i, j);
                    }
                }
            }
            return unionFind.setCount;
        }
    }

    class Solution2
    {
        //        public List<List<String>> accountsMerge(List<List<String>> accounts) {
        //            UnionField UnionField = new UnionField();
        //        }
    }

    class Solution3
    {
        public String longestPalindrome(String s)
        {
            if (s.length() <= 1)
            {
                return s;
            }
            int length = 0;
            int from = 0;
            for (int i = 0; i < s.length(); i++)
            {
                for (int j = s.length(); j > i; j--)
                {
                    String sub = s.substring(i, j);
                    if (isPalind(sub))
                    {
                        if (sub.length() > length)
                        {
                            length = sub.length();
                            from = i;
                        }
                    }
                }
            }
            return s.substring(from, from + length);
        }

        public boolean isPalind(String s)
        {
            if (s.length() <= 1)
            {
                return true;
            }
            for (int i = 0; i < (s.length() / 2 + s.length() % 2); i++)
            {
                if (s.charAt(i) != (s.charAt(s.length() - i)))
                {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * 罗马数字转换问题 目前空间及时间效率均不理想
     */
    static class Solution4
    {
        private static final Map<String, Integer> hash = new HashMap<String, Integer>();

        static
        {
            hash.put("I", 1);
            hash.put("V", 5);
            hash.put("X", 10);
            hash.put("L", 50);
            hash.put("C", 100);
            hash.put("D", 500);
            hash.put("M", 1000);

        }

        public int romanToInt(String s)
        {
            String[] splits = s.split("");
            Integer result = 0;
            Integer last = 0;
            for (String ss : splits)
            {
                Integer i = hash.get(ss);

                result += i;
                if (null != last)
                {
                    if (last < i)
                    {
                        result -= (last * 2);
                    }
                    last = i;
                }
            }
            return result;
        }
    }

    class UnionFind
    {
        private final int[] parents;
        private final int[] ranks;
        private int setCount;

        public UnionFind(int n)
        {
            this.parents = new int[n];
            setCount = n;
            ranks = new int[n];
            for (int i = 0; i < n; i++)
            {
                parents[i] = i;
            }
        }

        public int findSet(int n)
        {
            return parents[n] == n ? n : (parents[n] = findSet(parents[n]));
        }

        public void union(int x, int y)
        {
            x = findSet(x);
            y = findSet(y);
            if (x == y)
            {
                return;
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
        }

        public boolean connect(int x, int y)
        {
            x = findSet(x);
            y = findSet(y);
            return x == y;

        }
    }

}
