package interviewbit.binarysearch;

import com.google.common.collect.ImmutableList;
import common.Interval;

import java.util.ArrayList;
import java.util.List;

import static interviewbit.binarysearch.Common.binarySearch;
import static interviewbit.binarysearch.Common.countBinarySearch;
import static interviewbit.binarysearch.Common.largerNeighbourBinarySearch;

/**
 * Created by shahbaaz on 12/27/16.
 */
public class Solution {

    /**
     * https://www.interviewbit.com/problems/matrix-median/
     * @param A
     * @return
     */
    public int findMedian(ArrayList<ArrayList<Integer>> A) {

        Integer start = 0, end = Integer.MAX_VALUE;
        Integer sum = A.size() * A.get(0).size();
        while (true) {
            if (start >= end) return start;
            Integer mid = start + (end - start)/2;
            System.out.println(mid);
            Integer count = 0;
            for(int i = 0; i < A.size(); i++) {
                count += countBinarySearch(A.get(i), mid);
            }
            if(count < sum/2) {
                start = mid + 1;
            } else if(count > sum/2) {
                end = mid - 1;
            }
            else return mid;
        }
    }

    private long mpow(long x, long n, long d) {
        if (n == 0) return (x == 0) ? 0 : 1;
        long p = mpow(x, n/2, d) % d;
        long ans = (p * p) % d;
        if (n % 2 == 1) {
            ans = (x * ans) % d;
        }
        return (ans >= 0) ? ans : ans + d;
    }

    /**
     * https://www.interviewbit.com/problems/implement-power-function/
     * @param x
     * @param n
     * @param d
     * @return
     */
    public int pow(int x, int n, int d) {
        return (int) mpow(x, n, d);
    }

    /**
     * https://www.interviewbit.com/problems/rotated-sorted-array-search/
     * @param a
     * @param b
     * @return
     */
    public int search(final List<Integer> a, int b) {
        Interval range = new Interval();
        if (a.get(0) < a.get(a.size() - 1)) {
            range = new Interval(0, a.size() - 1);
        } else {
            int start = 0, end = a.size() - 1, mid;
            while (start <= end) {
                mid = start + (end - start) / 2;
                if (a.get(mid) > a.get(mid + 1)) {
                    if (b > a.get(0)) range = new Interval(0, mid);
                    else range = new Interval(mid+1, a.size()-1);
                    break;
                } else if (a.get(mid) <= a.get(0)) {
                    end = mid - 1;
                } else if (a.get(mid) > a.get(0)) {
                    start = mid + 1;
                }
            }
        }
        Integer ans = binarySearch(a, range, b);
        return (ans == null) ? -1 : ans;
    }

    /**
     *
     * @param a
     * @param b
     * @return
     */
    public int searchMatrix(ArrayList<ArrayList<Integer>> a, int b) {
        int start = 0, end = a.size() - 1, row = -1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            ArrayList<Integer> ai = a.get(mid);
            if (ai.get(0) <= b && ai.get(ai.size()-1) >= b) {
                row = mid; break;
            } else if (b < ai.get(0)) {
                end = mid - 1;
            } else if (b > ai.get(ai.size()-1)) {
                start = mid + 1;
            }
        }
        if (row == -1) return 0;
        Integer ans = binarySearch(a.get(row), new Interval(0, a.get(row).size()-1), b);
        return (ans == null) ? 0 : 1;
    }

    /**
     * https://www.interviewbit.com/problems/median-of-array/
     * @param a
     * @param b
     * @return
     */
    public double findMedianSortedArrays(final List<Integer> a, final List<Integer> b) {
        int start = 0, end = a.size() - 1, n = a.size() + b.size();
        while(start <= end) {
            int mid = start + (end - start)/2;
            int count = mid + countBinarySearch(b, a.get(mid));
            if (count == n/2) {
                if (n % 2 == 1) return a.get(mid);
                else return a.get(mid) + Math.min(largerNeighbourBinarySearch(a, a.get(mid)), largerNeighbourBinarySearch(b, a.get(mid)));
            }
        }
        return findMedianSortedArrays(b, a);
    }

    /**
     * https://www.interviewbit.com/problems/square-root-of-integer/
     * @param a
     * @return
     */
    public int sqrt(int a) {
        long start = 0, end = 200000;
        while(start <= end) {
            long mid = start + (end - start) / 2;
            long square = mid*mid, square2 = (mid+1)*(mid+1);
            if (a == square || (a > square && a < square2)) {
                return (int) mid;
            } else if (a < square) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return (int) start;
    }

    public static void main(String[] args) {
        ArrayList<String> a = new ArrayList<String>(ImmutableList.of("lkgyyrqh", "qrdqusnh", "zyu", "w", "ukzxyykeh", "zmfqafqle", "e", "ajq", "kagjss", "mihiqla", "qekf", "ipxbpz", "opsndtyu", "x", "ec", "cbd", "zz", "yzgx", "qbzaffgf", "i", "atstkrdph", "jgx", "qiy", "jeythmm", "qgqvyz", "dfagnfpwat", "sigxajhjt", "zx", "hwmcgss"));
    }
}
