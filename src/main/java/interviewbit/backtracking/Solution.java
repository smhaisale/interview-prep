package interviewbit.backtracking;

import com.google.common.collect.ImmutableList;
import sun.awt.image.ImageWatched;

import java.lang.reflect.Array;
import java.util.*;
import java.util.jar.Pack200;

/**
 * Created by shahbaaz on 12/29/16.
 */
public class Solution {

    private Map<Character, char[]> characterMap = new HashMap<Character, char[]>();

    private ArrayList<String> generateLetterCombinations(String a, int pos) {
        ArrayList<String> sb = new ArrayList<String>(), sb2 = new ArrayList<String>();
        if (pos == a.length()) {
            sb.add("");
            return sb;
        }

        sb = generateLetterCombinations(a, pos+1);
        char x = a.charAt(pos), r[] = characterMap.get(x);

        for(int j = 0; j < r.length; j++) {
            for(int i = 0; i < sb.size(); i++) {
                sb2.add(r[j] + sb.get(i));
            }
        }

        return sb2;
    }

    /**
     * https://www.interviewbit.com/problems/letter-phone/
     * @param a
     * @return
     */
    public ArrayList<String> letterCombinations(String a) {
        characterMap.put('0', new char[] {'0'});
        characterMap.put('1', new char[] {'1'});
        characterMap.put('2', new char[] {'a', 'b', 'c'});
        characterMap.put('3', new char[] {'d', 'e', 'f'});
        characterMap.put('4', new char[] {'g', 'h', 'i'});
        characterMap.put('5', new char[] {'j', 'k', 'l'});
        characterMap.put('6', new char[] {'m', 'n', 'o'});
        characterMap.put('7', new char[] {'p', 'q', 'r', 's'});
        characterMap.put('8', new char[] {'t', 'u', 'v'});
        characterMap.put('9', new char[] {'w', 'x', 'y', 'z'});

        return generateLetterCombinations(a, 0);
    }

    private Set<ArrayList<Integer>> subset = new TreeSet<ArrayList<Integer>>(new Comparator<ArrayList<Integer>>() {
        public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
            int i = 0;
            for (i = 0; i < o1.size(); i++) {
                if (i >= o2.size()) return 1;
                if (o1.get(i) > o2.get(i)) return 1;
                if (o1.get(i) < o2.get(i)) return -1;
            }
            if (i == o2.size()) return 0;
            return -1;
        }
    });

    private Set<ArrayList<String>> palindromeSet = new TreeSet<ArrayList<String>>(new Comparator<ArrayList<String>>() {
        public int compare(ArrayList<String> o1, ArrayList<String> o2) {
            int i = 0;
            for (i = 0; i < o1.size(); i++) {
                if (i >= o2.size()) return 1;
                int cmp = o1.get(i).compareTo(o2.get(i));
                if (cmp != 0) return cmp;
            }
            if (i == o2.size()) return 0;
            return -1;
        }
    });

    /**
     * https://www.interviewbit.com/problems/subset/
     * @param a
     * @return
     */
    public ArrayList<ArrayList<Integer>> subsets(ArrayList<Integer> a) {
        Collections.sort(a);
        subset.add(new ArrayList<Integer>());

        for (Integer x : a) {
            ArrayList<ArrayList<Integer>> array = new ArrayList<ArrayList<Integer>>();
            array.addAll(subset);
            for(int j = 0; j < array.size(); j++) {
                ArrayList<Integer> arr = new ArrayList<Integer>(array.get(j));
                arr.add(x);
                subset.add(arr);
            }
        }

        return new ArrayList<ArrayList<Integer>>(subset);
    }

    /**
     * https://www.interviewbit.com/problems/palindrome-partitioning/
     * @param a
     * @return
     */
    public ArrayList<ArrayList<String>> partition(String a) {
        return null;
    }

    private ArrayList<ArrayList<String>> nQueens = new ArrayList<ArrayList<String>>();

    private void fillNQueens(ArrayList<ArrayList<Integer>> board, int k) {

        if (k == board.size() + 1) {
            ArrayList<String> result = new ArrayList<String>();
            for(int i = 0; i < board.size(); i++) {
                ArrayList<Integer> row = board.get(i);
                StringBuilder sb = new StringBuilder();
                for(int j = 0; j < row.size(); j++) {
                    if (row.get(j) == -1) sb.append('Q');
                    else sb.append('.');
                }
                result.add(sb.toString());
            }
            nQueens.add(result);
            return;
        }

        ArrayList<Integer> row = board.get(k-1);
        for(int i = 0; i < row.size(); i++) {
            if (row.get(i) == 0) {
                row.set(i, -1);
                //Mark the column and diagonal elements on rows after this as taken.
                for(int j = 0; k+j < board.size(); j++) {
                    ArrayList<Integer> nextRow = board.get(k+j);
                    nextRow.set(i, nextRow.get(i)+k);
                    if (i-j > 0) nextRow.set(i-j-1, nextRow.get(i-j-1) + k);
                    if (i+j+1 < nextRow.size()) nextRow.set(i+j+1, nextRow.get(i+j+1) + k);
                }
                fillNQueens(board, k+1);
                for(int j = 0; k+j < board.size(); j++) {
                    ArrayList<Integer> nextRow = board.get(k+j);
                    nextRow.set(i, nextRow.get(i)-k);
                    if (i-j > 0) nextRow.set(i-j-1, nextRow.get(i-j-1) - k);
                    if (i+j+1 < nextRow.size()) nextRow.set(i+j+1, nextRow.get(i+j+1) - k);
                }
                row.set(i, 0);
            }
        }
    }

    /**
     * https://www.interviewbit.com/problems/nqueens/
     * @param a
     * @return
     */
    public ArrayList<ArrayList<String>> solveNQueens(int a) {

        ArrayList<ArrayList<Integer>> board = new ArrayList<ArrayList<Integer>>();

        ArrayList<Integer> array = new ArrayList<Integer>();
        for(int i = 0; i < a; i++) array.add(0);
        for(int i = 0; i < a; i++) board.add(new ArrayList<Integer>(array));

        fillNQueens(board, 1);
        return nQueens;
    }

    ArrayList<ArrayList<Integer>> permutations = new ArrayList<ArrayList<Integer>>();

    private void generatePermutations(ArrayList<Integer> array, int k) {
        if (k == array.size()) {
            ArrayList<Integer> result = new ArrayList<Integer>(array);
            permutations.add(result);
            return;
        }

        for(int i = k; i < array.size(); i++) {
            int temp = array.get(i);
            array.set(i, array.get(k));
            array.set(k, temp);
            generatePermutations(array, k+1);
            temp = array.get(i);
            array.set(i, array.get(k));
            array.set(k, temp);
        }
    }

    /**
     * https://www.interviewbit.com/problems/permutations/
     * @param a
     * @return
     */
    public ArrayList<ArrayList<Integer>> permute(ArrayList<Integer> a) {
        permutations.clear();
        generatePermutations(a, 0);
        return permutations;
    }

    /**
     * https://www.interviewbit.com/problems/gray-code/
     * @param a
     * @return
     */
    public ArrayList<Integer> grayCode(int a) {
        ArrayList<Integer> result = new ArrayList<Integer>();

        if (a == 1) {
            result.add(0);
            result.add(1);
            return result;
        }

        result = grayCode(a-1);
        for(int i = result.size()-1; i >= 0; i--) {
            result.add(result.get(i) + (1 << (a-1)));
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().grayCode(3));
    }
}
