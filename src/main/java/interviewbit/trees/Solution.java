package interviewbit.trees;

import common.TreeLinkNode;
import common.TreeNode;
import common.TrieNode;

import java.util.*;

/**
 * Created by shahbaaz on 12/31/16.
 */
public class Solution {

    private int balanced(TreeNode a) {

        if (a == null) return 0;

        int left = balanced(a.left);
        int right = balanced(a.right);

        if (left == -1 || right == -1) return -1;

        if (Math.abs(left - right) > 1) return -1;

        return Math.max(left, right) + 1;
    }

    /**
     * https://www.interviewbit.com/problems/balanced-binary-tree/
     * @param a
     * @return
     */
    public int isBalanced(TreeNode a) {

        return (balanced(a) == -1) ? 0 : 1;
    }

    /**
     * https://www.interviewbit.com/problems/identical-binary-trees/
     * @param a
     * @param b
     * @return
     */
    public int isSameTree(TreeNode a, TreeNode b) {
        if (a == null && b == null) return 1;
        if (a == null || b == null) return 0;

        return isSameTree(a.left, b.left) * isSameTree(a.right, b.right);
    }

    private TreeNode sortedArrayToBST(final ArrayList<Integer> a, int begin, int end) {
        if (begin > end) return null;
        if (begin == end) return new TreeNode(a.get(begin));

        int mid = begin + (end-begin)/2;

        TreeNode node = new TreeNode(a.get(mid));
        node.left = sortedArrayToBST(a, begin, mid-1);
        node.right = sortedArrayToBST(a, mid+1, end);

        return node;
    }

    /**
     * https://www.interviewbit.com/problems/sorted-array-to-balanced-bst/
     * @param a
     * @return
     */
    public TreeNode sortedArrayToBST(final List<Integer> a) {
        ArrayList<Integer> array = new ArrayList<Integer>(a);
        return sortedArrayToBST(array, 0, a.size()-1);
    }

    private int size(TreeNode a) {
        if (a == null) return 0;
        return size(a.left) + size(a.right) + 1;
    }

    private ArrayList<Integer> getKthSmallest(TreeNode root, int k) {

        ArrayList<Integer> result = new ArrayList<Integer>();
        result.add(0);
        result.add(-1);
        if (root == null) {
            return result;
        }

        result = getKthSmallest(root.left, k);
        if (result.get(0) == -1) return result;
        if (result.get(0) == k) {
            result.set(0, -1);
            result.set(1, root.val);
            return result;
        }

        int left = result.get(0);

        result = getKthSmallest(root.right, k - left - 1);
        if (result.get(0) == -1) return result;
        result.set(0, result.get(0) + left + 1);

        return result;
    }
    /**
     * https://www.interviewbit.com/problems/kth-smallest-element-in-tree/
     * @param root
     * @param k
     * @return
     */
    public int kthsmallest(TreeNode root, int k) {
        return getKthSmallest(root, k-1).get(1);
    }

    /**
     * https://www.interviewbit.com/problems/invert-the-binary-tree/
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;

        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);

        root.left = right;
        root.right = left;

        return root;
    }

    /**
     * https://www.interviewbit.com/problems/populate-next-right-pointers-tree/
     * @param root
     */
    public void connect(TreeLinkNode root) {
        Queue<TreeLinkNode> queue = new LinkedList<TreeLinkNode>();

        queue.add(root);
        queue.add(null);

        while (queue.size() > 1) {
            TreeLinkNode node = queue.remove();
            if (node != null) {
                TreeLinkNode next = queue.peek();
                node.next = next;
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            } else {
                queue.add(null);
            }
        }
    }

    /**
     * https://www.interviewbit.com/problems/inorder-traversal/
     * @param a
     * @return
     */
    public ArrayList<Integer> inorderTraversal(TreeNode a) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        ArrayList<Integer> result = new ArrayList<Integer>();

        TreeNode temp = a;
        while (temp != null) {
            stack.push(temp);
            temp = temp.left;
        }

        while(!stack.empty()) {
            TreeNode node = stack.pop();
            result.add(node.val);
            if (node.right != null) {
                temp = node.right;
                while (temp != null) {
                    stack.push(temp);
                    temp = temp.left;
                }
            }
        }

        return result;
    }

    /**
     * https://www.interviewbit.com/problems/path-sum/
     * @param a
     * @param b
     * @return
     */
    public int hasPathSum(TreeNode a, int b) {
        if (a == null) return 0;
        if (a.left == null && a.right == null && b == a.val) return 1;

        return Math.min(1, hasPathSum(a.left, b - a.val) + hasPathSum(a.right, b - a.val));
    }

    /**
     * https://www.interviewbit.com/problems/shortest-unique-prefix/
     * @param a
     * @return
     */
    public ArrayList<String> prefix(ArrayList<String> a) {
        TrieNode start = new TrieNode(null);

        for(int i = 0; i < a.size(); i++) {
            String s = a.get(i);
            if(start.map[s.charAt(0) - 'a'] == null) {
                start.map[s.charAt(0) - 'a'] = new TrieNode(s);
                continue;
            }
            TrieNode node = start.map[s.charAt(0)-'a'];
            for(int j = 1; j < a.get(i).length(); j++) {
                if (node.map[s.charAt(j) - 'a'] == null) {
                    String word = node.word;
                    node.word = null;
                    while (word != null && word.charAt(j) == s.charAt(j)) {
                        node.map[s.charAt(j)-'a'] = new TrieNode();
                        node = node.map[s.charAt(j)-'a'];
                        j++;
                    }
                    node.map[s.charAt(j) - 'a'] = new TrieNode(s);
                    if (word != null) node.map[word.charAt(j)-'a'] = new TrieNode(word);
                    break;
                }
                node = node.map[s.charAt(j) - 'a'];
            }
        }

        ArrayList<String> result = new ArrayList<String>();
        for(int i = 0; i < a.size(); i++) {
            TrieNode node = start;
            String s = a.get(i);
            for(int j = 0; j < s.length(); j++) {
                node = node.map[s.charAt(j)-'a'];
                if (node.word != null && node.word.equals(s)) {
                    result.add(s.substring(0, j+1));
                    break;
                }
            }
        }

        return result;
    }

    /**
     * https://www.interviewbit.com/problems/least-common-ancestor/
     * @param a
     * @param val1
     * @param val2
     * @return
     */
    public int lca(TreeNode a, int val1, int val2) {
        if (a == null) return -1;

        int left = lca(a.left, val1, val2);
        int right = lca(a.right, val1, val2);

        int sum = val1 + val2;
        if (left+right == sum || left + a.val == sum || right + a.val == sum) return - a.val;
        if (a.val == val1 || a.val == val2) return - a.val;
        if (left == val1 || left == val2) return left;
        if (right == val1 || right == val2) return right;

        return -1;
    }

    private TreeNode flatten(TreeNode a, TreeNode tail) {
        if (a == null) return tail;
        TreeNode right = flatten(a.right, tail);
        a.right = flatten(a.left, right);
        a.left = null;
        return a;
    }

    /**
     * https://www.interviewbit.com/problems/flatten-binary-tree-to-linked-list/
     * @param a
     * @return
     */
    public TreeNode flatten(TreeNode a) {
        return flatten(a, null);
    }

    public static void main(String[] args) {
        TreeNode a = new TreeNode(4);
        a = new TreeNode(3, null, a);
        a = new TreeNode(5, a, null);

        System.out.println(new Solution().flatten(a));
    }
}