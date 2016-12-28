package interviewbit.arrays;

import common.Interval;

import java.util.*;

/**
 * Created by shahbaaz on 12/26/16.
 */
public class Solution {

    /**
     * https://www.interviewbit.com/problems/add-one-to-number/
     * @param a
     * @return
     */
    public ArrayList<Integer> plusOne(ArrayList<Integer> a) {
        int carry = 1;
        LinkedList<Integer> sum = new LinkedList<Integer>();
        for(int i = a.size() - 1; i >= 0; i--) {
            int res = a.get(i) + carry;
            if(res == 10) {
                sum.addFirst(0);
                carry = 1;
            } else {
                sum.addFirst(res);
                carry = 0;
            }
        }

        if (carry == 1) sum.addFirst(1);

        while (sum.size() > 0 && sum.get(0) == 0) sum.remove(0);

        return new ArrayList<Integer>(sum);
    }

    /**
     * https://www.interviewbit.com/problems/max-sum-contiguous-subarray/
     * @param a
     * @return
     */
    public int maxSubArray(final List<Integer> a) {

        Integer temp = a.get(0), max = a.get(0);

        for(int i = 1; i < a.size(); i++) {
            Integer x = a.get(i);
            temp = Math.max(x, x + temp);
            max = Math.max(max, temp);
        }

        return max;
    }

    /**
     * https://www.interviewbit.com/problems/maximum-absolute-difference/
     * @param A
     * @return
     */
    public int maxArr(ArrayList<Integer> A) {
        int len = A.size();
        int max = Integer.MIN_VALUE, max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE,
                max3 = Integer.MIN_VALUE, max4 = Integer.MIN_VALUE;

        for(int i = 0; i < len; i++) {
            max1 = Math.max(max1, i + A.get(i));
            max2 = Math.max(max2, i - A.get(i));
            max3 = Math.max(max3, -i + A.get(i));
            max4 = Math.max(max4, -i - A.get(i));
        }

        for(int i = 0; i < len; i++) {
            max = Math.max(max, max1 - i - A.get(i));
            max = Math.max(max, max2 - i + A.get(i));
            max = Math.max(max, max3 + i - A.get(i));
            max = Math.max(max, max4 + i + A.get(i));
        }

        return max;
    }

    /**
     * https://www.interviewbit.com/problems/kth-row-of-pascals-triangle/
     * @param a
     * @return
     */
    public ArrayList<Integer> getRow(int a) {
        ArrayList<Integer> x = new ArrayList<Integer>();
        ArrayList<Integer> y = new ArrayList<Integer>();
        x.add(0);
        x.add(1);
        x.add(0);
        for(int i = 0; i < a; i++) {
            if(i%2 == 0) {
                y.add(0);
                for(int j = 1; j < x.size(); j++) {
                    y.add(x.get(j-1) + x.get(j));
                }
                y.add(0);
                x.clear();
            } else {
                x.add(0);
                for(int j = 1; j < y.size(); j++) {
                    x.add(y.get(j-1) + y.get(j));
                }
                x.add(0);
                y.clear();
            }
        }

        if(a%2 == 0) {
            x.remove(0);
            x.remove(x.size() - 1);
        } else {
            y.remove(0);
            y.remove(y.size() - 1);
        }

        return (a%2 == 0) ? x : y;
    }

    /**
     * https://www.interviewbit.com/problems/merge-intervals
     * @param intervals
     * @param newInterval
     * @return
     */
    public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
        ArrayList<Interval> result = new ArrayList<Interval>();

        if(newInterval.end < newInterval.start) {
            int temp = newInterval.start;
            newInterval.start = newInterval.end;
            newInterval.end = temp;
        }

        boolean added = false;
        for(Interval interval : intervals) {
            if(interval.end < newInterval.start) {
                result.add(interval);
            } else if (interval.start > newInterval.end) {
                if (!added) {
                    result.add(newInterval);
                    added = true;
                }
                result.add(interval);
            } else if (interval.start > newInterval.start && interval.end < newInterval.end) {
                continue;
            } else if (interval.start < newInterval.start && interval.end > newInterval.end) {
                result.add(interval);
                added = true;
            } else if (interval.start > newInterval.start) {
                newInterval.end = interval.end;
            } else {
                newInterval.start = interval.start;
            }
        }

        if (!added) {
            result.add(newInterval);
        }

        return result;
    }

    /**
     * https://www.interviewbit.com/problems/largest-number/
     * @param a
     */
    public String largestNumber(final List<Integer> a) {
        Collections.sort(a, new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return (o2.toString() + o1).compareTo(o1.toString() + o2);
            }
        });

        StringBuilder result = new StringBuilder();
        for(Integer i : a) {
            result.append(i);
        }

        while (result.length() > 0 && result.charAt(0) == '0') {
            result.deleteCharAt(0);
        }

        return result.toString();
    }

    /**
     * https://www.interviewbit.com/problems/set-matrix-zeros/
     * @param a
     */
    public void setZeroes(ArrayList<ArrayList<Integer>> a) {
        for (int i = 0; i < a.size(); i++) {
            for (int j = 0; j < a.get(0).size(); j++) {
                int x = a.get(i).get(j);
                if (x == 0) {
                    a.get(i).set(j, 4);
                }
            }
        }

        for (int i = 0; i < a.size(); i++) {
            for (int j = 0; j < a.get(0).size(); j++) {
                int x = a.get(i).get(j);

                if (x == 2 || x == 4) {
                    for(int k = 0; k < a.size(); k++) {
                        if(a.get(k).get(j) == 1) a.get(k).set(j, 0);
                        if(a.get(k).get(j) == 2) a.get(k).set(j, 0);
                        if(a.get(k).get(j) == 4) a.get(k).set(j, 3);
                    }
                }
                if (x == 3 || x == 4) {
                    for(int k = 0; k < a.get(0).size(); k++) {
                        if(a.get(i).get(k) == 1) a.get(i).set(k, 0);
                        if(a.get(i).get(k) == 3) a.get(i).set(k, 0);
                        if(a.get(i).get(k) == 4) a.get(i).set(k, 2);
                    }
                }

                if (x == 2 || x == 3 || x == 4) {
                    a.get(i).set(j, 0);
                }
            }
        }
    }

    /**
     * https://www.interviewbit.com/problems/first-missing-integer/
     * @param a
     * @return
     */
    public int firstMissingPositive(ArrayList<Integer> a) {
        int pos = 0;
        for(int i = 0; i < a.size(); i++) {
            if(a.get(i) <= 0) {
                int temp = a.get(i);
                a.set(i, a.get(pos));
                a.set(pos, temp);
                pos++;
            }
        }

        for(int i = pos; i < a.size(); i++) {
            int j = Math.abs(a.get(i)) + pos - 1;
            if (j >= a.size()) continue;
            a.set(j, -a.get(j));
        }

        int ans;
        for(ans = pos; ans < a.size(); ans++) {
            if(a.get(ans) > 0) break;
        }
        return ans - pos + 1;
    }

    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<Integer>();
        a.add(-3);
        a.add(4);
        a.add(1);
        a.add(-1);
        a.add(2);

        System.out.println(a);

        System.out.println(new Solution().firstMissingPositive(a));

        System.out.println(a);
    }
}
