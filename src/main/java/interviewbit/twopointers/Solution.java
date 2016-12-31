package interviewbit.twopointers;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by shahbaaz on 12/29/16.
 */
public class Solution {

    /**
     * https://www.interviewbit.com/problems/3-sum/
     * @param a
     * @param b
     * @return
     */
    public int threeSumClosest(ArrayList<Integer> a, int b) {

        Collections.sort(a);

        int closest = a.get(0) + a.get(1) + a.get(2);

        for(int i = 0; i < a.size(); i++) {
            int sum = b - a.get(i);
            int start = i + 1, end = a.size() - 1;
            while(start < end) {
                if (start == i) { start++; continue; }
                if (end == i) { end--; continue; }

                Integer tempSum = a.get(start) + a.get(end);

                if (tempSum == sum) return b;

                if (Math.abs(sum - tempSum) < Math.abs(closest - b)) {
                    closest = tempSum + a.get(i);
                } else if (tempSum < sum) {
                    start ++;
                } else if (tempSum > sum) {
                    end --;
                }
            }
        }
        return closest;
    }

    /**
     * https://www.interviewbit.com/problems/intersection-of-sorted-arrays/
     * @param a
     * @param b
     * @return
     */
    public ArrayList<Integer> intersect(final List<Integer> a, final List<Integer> b) {
        int start1 = 0, start2 = 0;
        ArrayList<Integer> intersection = new ArrayList<Integer>();
        while(start1 < a.size() && start2 < b.size()) {
            int x = a.get(start1), y = b.get(start2);
            if(x == y) {
                start1 ++; start2 ++;
                intersection.add(x);
            } else if (x < y) {
                start1 ++;
            } else {
                start2++;
            }
        }
        return intersection;
    }

    /**
     * https://www.interviewbit.com/problems/remove-duplicates-from-sorted-array/
     * @param a
     * @return
     */
    public int removeDuplicates(ArrayList<Integer> a) {

        // Mark duplicates as null
        int d = 0;
        for (int i = 0; i < a.size(); i++) {
            int value = a.get(i);
            while (i < a.size() - 1 && a.get(i+1) == value) {
                a.set(i+1, null);
                d++;
                i++;
            }
        }
        d = a.size() - d;

        //System.out.println(a);

        int start = 0, end = 0;

        while(true) {
            while (start < a.size() && a.get(start) != null) start++;
            end = start;
            while (end < a.size() && a.get(end) == null) end++;

            if(start == a.size() || end == a.size()) break;

            a.set(start, a.get(end));
            a.set(end, null);
        }

        //System.out.println(a);

        while (a.get(a.size()-1) == null) a.remove(a.size()-1);

        //System.out.println(a);

        return d;
    }

    /**
     * https://www.interviewbit.com/problems/max-continuous-series-of-1s/
     * @param a
     * @param b
     * @return
     */
    public ArrayList<Integer> maxone(ArrayList<Integer> a, int b) {
        int start = 0, end = 0, w = 0, m = 0, wmax = 0, imax = 0;
        while(start < a.size() && end < a.size()) {
            if (start > end) {
                end = start;
                w = 0;
                m = 0;
            }
            if (a.get(end) == 1 || m < b) {
                end++;
                w++;
                if (w > wmax) { wmax = w; imax = start;}
                if (a.get(end-1) == 0) m++;
            } else {
                while (m == b) {
                    if (a.get(start) == 0) m--;
                    start++;
                    w--;
                }
            }
        }
        ArrayList<Integer> seq = new ArrayList<Integer>();
        for(int i = 0; i < wmax; i++) {
            seq.add(i+imax);
        }
        return seq;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().maxone(new ArrayList<Integer>(ImmutableList.of(0, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 0, 0)), 0));
    }
}
