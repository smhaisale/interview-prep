package interviewbit.binarysearch;

import com.google.common.collect.ImmutableList;
import common.Interval;

import java.util.ArrayList;
import java.util.List;

import static interviewbit.binarysearch.Common.countBinarySearch;

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

    /**
     * https://www.interviewbit.com/problems/implement-power-function/
     * @param x
     * @param n
     * @param d
     * @return
     */
    public long mpow(long x, long n, long d) {
        if (n == 0) return (x == 0) ? 0 : 1;
        long p = mpow(x, n/2, d) % d;
        long ans = (p * p) % d;
        if (n % 2 == 1) {
            ans = (x * ans) % d;
        }
        return (ans >= 0) ? ans : ans + d;
    }

    public int pow(int x, int n, int d) {
        return (int) mpow(x, n, d);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().pow(71045970, 41535484, 64735492));
    }
}
