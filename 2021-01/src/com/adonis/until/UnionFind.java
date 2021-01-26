package com.adonis.until;

/**
 * 并查集
 */
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
