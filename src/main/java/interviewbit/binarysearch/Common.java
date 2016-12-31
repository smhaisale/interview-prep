package interviewbit.binarysearch;

import common.Interval;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Pack200;

/**
 * Created by shahbaaz on 12/27/16.
 */
public class Common {

    /**
     * Recursive binary search in an interval of an array list for an integer x
     * @param A
     * @param interval
     * @param x
     * @return
     */
    public static final Integer binarySearch(List<Integer> A, Interval interval, Integer x) {
        if (interval.start > interval.end) return null;
        if (interval.start == interval.end) {
            if (A.get(interval.start) == x) return interval.start;
            return null;
        }

        Integer mid = interval.start + (interval.end - interval.start)/2;
        if (x < A.get(mid)) {
            return binarySearch(A, new Interval(interval.start, mid-1), x);
        } else if (x > A.get(mid)) {
            return binarySearch(A, new Interval(mid+1, interval.end), x);
        }
        return mid;
    }

    /**
     * Count of elements smaller than or equal to integer x using iterative binary search
     * @param A
     * @param x
     * @return
     */
    public static final Integer countBinarySearch(List<Integer> A, Integer x) {
        int start = 0, end = A.size() - 1;

        while(true) {
            if(start == end) {
                if (A.get(end) <= x) return end + 1;
                return end;
            }
            int mid = start + (end - start)/2;
            if (A.get(mid) > x) {
                end = mid;
            } else if (A.get(mid) < x) {
                start = mid + 1;
            } else {
                return mid;
            }
        }
    }

    /**
     * Greater neighbour of integer x using iterative binary search
     * @param A
     * @param x
     * @return
     */
    public static final Integer largerNeighbourBinarySearch(List<Integer> A, Integer x) {
        int start = 0, end = A.size() - 1;

        while(start <= end) {
            int mid = start + (end - start)/2;
            if (A.get(mid+1) > x && A.get(mid) > x) {
                end = mid - 1;
            } else if (A.get(mid) < x && A.get(mid+1) < x) {
                start = mid + 2;
            } else {
                return mid;
            }
        }

        return null;
    }

}
