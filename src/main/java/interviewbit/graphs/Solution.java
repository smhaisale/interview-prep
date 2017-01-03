package interviewbit.graphs;

import common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by shahbaaz on 1/2/17.
 */
public class Solution {

    /**
     * https://www.interviewbit.com/problems/level-order/
     * @param a
     * @return
     */
    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode a) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(a);
        queue.add(null);
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> array = new ArrayList<Integer>();
        while(queue.size() > 1) {
            TreeNode node = queue.remove();
            if (node != null) {
                array.add(node.val);
                if (a.left != null) queue.add(a.left);
                if (a.right != null) queue.add(a.right);
            }
            else {
                result.add(array);
                array = new ArrayList<Integer>();
                queue.add(null);
            }
        }
        return result;
    }

    private void markVisited(ArrayList<char []> board, int i, int j) {
        if (board.get(i)[j] == 'O') return;
        board.get(i)[j] = 'O';
        if (i > 0) markVisited(board, i-1, j);
        if (i < board.size()-1) markVisited(board, i+1, j);
        if (j > 0) markVisited(board, i, j-1);
        if (j < board.get(i).length-1) markVisited(board, i, j+1);
    }

    /**
     * https://www.interviewbit.com/problems/black-shapes/
     * @param a
     * @return
     */
    public int black(ArrayList<String> a) {
        ArrayList<char []> board = new ArrayList<char[]>();
        for(int i = 0; i < a.size(); i++)
            board.add(a.get(i).toCharArray());

        int count = 0;
        for(int i = 0; i < board.size(); i++) {
            for(int j = 0; j < board.get(i).length; j++) {
                if (board.get(i)[j] == 'X') {
                    count++;
                    markVisited(board, i, j);
                }
            }
        }
        return count;
    }
}
