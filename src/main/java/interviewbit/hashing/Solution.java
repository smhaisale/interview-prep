package interviewbit.hashing;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableTable;
import common.RandomListNode;
import jdk.nashorn.internal.ir.annotations.Immutable;
import jdk.nashorn.internal.parser.Parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shahbaaz on 12/30/16.
 */
public class Solution {

    /**
     * https://www.interviewbit.com/problems/2-sum/
     * @param a
     * @param b
     * @return
     */
    public ArrayList<Integer> twoSum(final List<Integer> a, int b) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        for(int i = 0; i < a.size(); i++) {
            int n = a.get(i);
            if(map.get(n) == null) map.put(n, i);
        }

        ArrayList<Integer> result = new ArrayList<Integer>();

        for(int i = 1; i < a.size(); i++) {
            Integer j = map.get(b - a.get(i));
            if (j != null && j < i) {
                result.add(j+1); result.add(i+1);
                return result;
            }
        }

        return result;
    }

    /**
     * https://www.interviewbit.com/problems/copy-list/
     * @param head
     * @return
     */
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) return head;

        RandomListNode it = head.next, copy = new RandomListNode(head.label), copyHead = copy;
        Map<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
        map.put(head, copyHead);

        while(it != null) {
            copy.next = new RandomListNode(it.label);
            map.put(it, copy.next);
            it = it.next;
            copy = copy.next;
        }

        it = head;
        copy = copyHead;

        while (it != null && copy != null) {
            if (it.random == null) {
                copy.random = null;
            } else {
                copy.random = map.get(it.random);
            }
            it = it.next;
            copy = copy.next;
        }
        return copy;
    }

    private boolean hasAll(Map<Character, Integer> minCount, Map<Character, Integer> count) {
        for(Character c : minCount.keySet()) {
            Integer n1 = minCount.get(c);
            Integer n2 = count.get(c);
            if (n2 == null || n2 < n1) return false;
        }
        return true;
    }

    /**
     * https://www.interviewbit.com/problems/window-string/
     * @param S
     * @param T
     * @return
     */
    public String minWindow(String S, String T) {

        if (S.length() < T.length() || T.length() == 0) return "";

        Map<Character, Integer> minCount = new HashMap<Character, Integer>();
        Map<Character, Integer> count = new HashMap<Character, Integer>();

        for(int i = 0; i < T.length(); i++) {
            Character c = T.charAt(i);
            Integer n = minCount.get(c);
            if (n == null) {
                minCount.put(c, 1);
            } else {
                minCount.put(c, n+1);
            }

            if (count.get(c) == null) count.put(c, 0);

            c = S.charAt(i);
            n = count.get(c);
            if (n == null) {
                count.put(c, 1);
            } else {
                count.put(c, n+1);
            }
        }

        int start = 0, end = T.length() - 1, rs = 0, re = S.length();
        while(end >= start + T.length() - 1) {
            while (hasAll(minCount, count)) {
                if (end-start < re-rs) {
                    re = end; rs = start;
                }

                Character c = S.charAt(start);
                if (minCount.containsKey(c)) count.put(c, count.get(c) - 1);
                start++;
            }
            end++;
            if (end == S.length()) break;
            Character c = S.charAt(end);
            if (minCount.containsKey(c)) count.put(c, count.get(c) + 1);
        }

        if (re - rs >= S.length()) return "";
        return S.substring(rs, re+1);
    }

    private String fraction(long numerator, long denominator) {
        Map<Long, Integer> map = new HashMap<Long, Integer>();
        StringBuilder fraction = new StringBuilder();
        while (numerator != 0) {
            if(map.containsKey(numerator)) {
                StringBuilder result = new StringBuilder();
                for(int j = 0; j < fraction.length(); j++) {
                    if (map.get(numerator) == j) {
                        result.append("(");
                    }
                    result.append(fraction.charAt(j));
                }
                result.append(")");
                return result.toString();
            }
            map.put(numerator, fraction.length());
            numerator *= 10;
            fraction.append(numerator/denominator);
            numerator %= denominator;
            if (numerator < 0) numerator += denominator;
        }
        return fraction.toString();
    }
    /**
     * https://www.interviewbit.com/problems/fraction/
     * @param numerator
     * @param denominator
     * @return
     */
    public String fractionToDecimal(int numerator, int denominator) {
        StringBuilder result = new StringBuilder();
        long num = numerator, denom = denominator;

        num = Math.abs(num);
        denom = Math.abs(denom);
        result.append(num/denom);
        num %= denom;

        if (num == 0) return result.toString();

        result.append('.').append(fraction(num, denom));
        if ((numerator < 0 && denominator > 0) || (numerator > 0 && denominator < 0)) result.insert(0, "-");
        return result.toString();
    }

    private boolean equals(String S, Map<String, Integer> map) {
        Map<String, Integer> copy = new HashMap<String, Integer>(map);
        int end = 0;
        StringBuilder sb = new StringBuilder();
        while (end < S.length()) {
            sb.append(S.charAt(end));
            String s = sb.toString();
            if(copy.get(s) != null && copy.get(s) != 0) {
                copy.put(s, copy.get(s) - 1);
                sb = new StringBuilder();
            }
            end++;
        }
        if (sb.toString().equals("")) return true;
        return false;
    }

    /**
     * https://www.interviewbit.com/problems/substring-concatenation/
     * @param a
     * @param b
     * @return
     */
    public ArrayList<Integer> findSubstring(String a, final List<String> b) {
        int k = 0;
        Map<String, Integer> map = new HashMap<String, Integer>();
        ArrayList<Integer> result = new ArrayList<Integer>();
        for(String s : b) {
            k += s.length();
            if (map.get(s) == null) map.put(s, 0);
            map.put(s, map.get(s)+1);
        }
        for(int i = k; i <= a.length(); i++) {
            if (equals(a.substring(i-k, i), map)) {
                result.add(i-k);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("foo", 1);
        map.put("bar", 1);
        System.out.println(new Solution().findSubstring("barfoothefoobarman", new ArrayList<String>(ImmutableList.of("foo", "bar"))));
    }
}
