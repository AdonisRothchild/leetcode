import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class Twenty2Leetcode
{
    @Test
    public void testMain()
    {

    }

    class Solution1 {
        @Test
        public int makeConnected(int n, int[][] connections) {
            Set<Integer> sets = new HashSet<Integer>();
            if(connections.length < n - 1)
            {
                return -1;
            }

            for(int[] lines : connections)
            {
                sets.add(lines[0]);
                sets.add(lines[1]);
            }
            return n - sets.size();
        }
    }

    class Solution2 {
        @Test
        public int makeConnected(int n, int[][] connections) {
            Set<Integer> sets = new HashSet<Integer>();
            if(connections.length < n - 1)
            {
                return -1;
            }

            for(int[] lines : connections)
            {
                sets.add(lines[0]);
                sets.add(lines[1]);
            }
            return n - sets.size();
        }
    }
}
