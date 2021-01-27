package com.leetcode.hash;

/**
 * 构建自己的hashSet
 */
public class MyHashSet
{

    private static final int solt = 1 << 4;
    int[][] sets = new int[solt][solt];
    boolean[][] bl = new boolean[solt][solt];
    private TreeNode node;
    int size;

    /** Initialize your data structure here. */
    public MyHashSet()
    {

    }

    public void add(int key)
    {
        if (contains(key))
        {
            return;
        }
        if (key < solt * solt && null == node)
        {
            sets[key / solt][key % solt] = key;
            bl[key / solt][key % solt] = true;
        } else
        {
            TreeNode treeNode = getNode();
            setNodeValue(treeNode,key,key);
        }
        size++;
    }

    public void remove(int key)
    {
        if (contains(key))
        {
            if (key < solt * solt && null == node)
            {
                sets[key / solt][key % solt] = 0;
                bl[key / solt][key % solt] = false;
            } else
            {
                removeNodeValue(node,key);
            }
            size--;

        }
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key)
    {
        if (key < solt * solt && null == node)
        {
            return sets[key / solt][key % solt] == key && bl[key / solt][key % solt];
        } else
        {
            TreeNode treeNode = getNodeValue(node,key);
            if(null != treeNode && key == treeNode.val)
            {
                return true;
            }
        }
        return false;
    }

    public void setNodeValue(TreeNode node, int key, int hash)
    {
        if(null == node)
        {
            node = new TreeNode();
        }
        if (hash / solt == 0)
        {
            node.addNode(new TreeNode(key), hash);
        } else
        {
            hash =  hash / solt ;
            setNodeValue(node.getChildren()[hash % solt], key, hash);
        }
    }

    public void removeNodeValue(TreeNode node, int hash)
    {
        if(null == node)
        {
            node = new TreeNode();
        }
        if (hash / solt == 0)
        {
            node.getChildren()[hash%solt] = null;
        } else
        {
            hash =  hash / solt ;
            removeNodeValue(node.getChildren()[hash % solt], hash);
        }
    }

    public TreeNode getNodeValue(TreeNode node,  int hash)
    {
        if(null == node)
        {
            return null;
        }
        if (hash / solt == 0)
        {
            return node.getChildren()[hash % solt];
        } else
        {
            hash =  hash / solt ;
            return getNodeValue(node.getChildren()[hash % solt],  hash);
        }
    }

    public TreeNode getNode()
    {
        if (null == node)
        {
            node = new TreeNode();
            for (int i = 0; i < sets.length; i++)
            {
                TreeNode child = new TreeNode();
                node.addNode(child, i);
                for (int j = 0; j < sets[i].length; j++)
                {
                    child.addNode(new TreeNode(sets[i][j]), j);
                }
            }
        }
        return node;
    }

    class TreeNode
    {
        TreeNode[] children;
        TreeNode parent;
        int val;

        public TreeNode()
        {
        }

        public TreeNode(int val)
        {
            this.val = val;
        }

        public void addNode(TreeNode node, int i)
        {

            getChildren()[i] = node;
            getChildren()[i].parent = node;
        }

        public TreeNode[] getChildren()
        {
            if (null == children)
            {
                children = new TreeNode[solt];
            }
            return children;
        }
    }
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */
