package interviewbit.strings;

import java.util.ArrayList;

import static interviewbit.strings.Common.reverse;

/**
 * Created by shahbaaz on 12/28/16.
 */
public class Solution {

    /**
     * https://www.interviewbit.com/problems/palindrome-string/
     * @param a
     * @return
     */
    public int isPalindrome(String a) {
        int start = 0, end = a.length() - 1;
        a = a.toUpperCase();
        while (start <= end) {
            while(start < a.length() && (a.charAt(start) < 'A' || a.charAt(start) > 'Z') && (a.charAt(start) < '0' || a.charAt(start) > '9')) start++;
            while(end >= 0 && (a.charAt(end) < 'A' || a.charAt(end) > 'Z') && (a.charAt(end) < '0' || a.charAt(end) > '9')) end--;

            if(start >= a.length() || end < 0) return 1;

            if(a.charAt(start) != a.charAt(end)) return 0;
            start++; end--;
        }
        return 1;
    }

    /**
     * https://www.interviewbit.com/problems/implement-strstr/
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(final String haystack, final String needle) {
        for(int i = 0; i < haystack.length() - needle.length(); i++) {
            int j;
            for(j = 0; j < needle.length(); j++) {
                if (haystack.charAt(i+j) != needle.charAt(j)) break;
            }
            if (j == needle.length()) return j;
        }
        return -1;
    }

    /**
     * https://www.interviewbit.com/problems/compare-version-numbers/
     * @param a
     * @param b
     * @return
     */
    public int compareVersion(String a, String b) {
        int s1 = 0, s2 = 0;
        while(true) {
            int num1 = 0, num2 = 0;
            while (s1 < a.length() && a.charAt(s1) != '.') {
                num1 *= 10; num1 += (a.charAt(s1) - '0'); s1++;
            }
            while (s2 < b.length() && b.charAt(s2) != '.') {
                num2 *= 10; num2 += (b.charAt(s2) - '0'); s2++;
            }
            if (num1 > num2) return 1;
            else if (num2 > num1) return -1;

            if (s1 == a.length() && s2 == b.length()) return 0;
            else if (s1 == a.length()) return -1;
            else if (s2 == b.length()) return 1;

            s1++; s2++;
        }
    }

    private int getLongestPalindrome(String s, int center) {
        int s1 = center - 1, s2 = center + 1, count1 = 1, count2 = 0;
        while(s1 >= 0 && s2 < s.length() && s.charAt(s1) == s.charAt(s2)) { s1--; s2++; count1 += 2; }
        s1 = center; s2 = center + 1;
        while(s1 >= 0 && s2 < s.length() && s.charAt(s1) == s.charAt(s2)) { s1--; s2++; count2 += 2; }
        return Math.max(count1, count2);
    }

    /**
     * https://www.interviewbit.com/problems/longest-palindromic-substring/
     * @param a
     * @return
     */
    public String longestPalindrome(String a) {
        int max = 1, max_i = 0, begin = 0, end = 0;
        for(int i = 0; i < a.length(); i++) {
            int length = getLongestPalindrome(a, i);
            if (max < length) {
                max = length; max_i = i;
            }
        }
        begin = max_i;
        end = max_i + 1 - (max % 2);
        while (begin >= 0 && end < a.length() && a.charAt(begin) == a.charAt(end)) { begin--; end++; }
        return a.substring(begin + 1, end);
    }

    /**
     * https://www.interviewbit.com/problems/justified-text/
     * @param a
     * @param b
     * @return
     */
    public ArrayList<String> fullJustify(ArrayList<String> a, int b) {
        int x = 0, i = 0, j = 0;
        ArrayList<String> justifiedStrings = new ArrayList<String>();
        for(i = 0; i < a.size(); ) {
            StringBuilder sb = new StringBuilder();
            x = 0; j = i;
            while (x <= b && j < a.size()) {
                x += a.get(j).length() + 1;
                j++;
            }
            x--;
            if (x > b) { j--; x -= (a.get(j).length() + 1); }
            if (j == a.size()) break;
            int words = (j - i);
            int spaces = words - 1;
            int extras = b - x;
            System.out.println(a);
            System.out.println(x + "\t" + i + "\t" + j + "\t" + words + "\t" + spaces + "\t" + extras);
            for(int k = i; k < j-1; k++) {
                sb.append(a.get(k) + " ");
                int s = (extras + spaces - k + i - 1) / spaces;
                while (s-- > 0) sb.append(" ");
            }
            sb.append(a.get(j-1));
            while (sb.length() < b) sb.append(" ");
            justifiedStrings.add(sb.toString());
            i = j;
        }
        StringBuilder sb = new StringBuilder();
        for(j = i; j < a.size(); j++) sb.append(a.get(j)).append(" ");
        if (sb.length() > b) sb.deleteCharAt(b);
        if (sb.length() != 0) {
            while (sb.length() < b) sb.append(" ");
            justifiedStrings.add(sb.toString());
        }
        return justifiedStrings;
    }

    /**
     * https://www.interviewbit.com/problems/multiply-strings/
     * @param a
     * @param b
     * @return
     */
    public String multiply(String a, String b) {
        String x = reverse(a), y = reverse(b);
        char product[] = new char[a.length() + b.length() + 1];
        for(int i = 0; i <= a.length() + b.length(); i++) product[i] = '0';
        for(int i = 0; i < x.length(); i++) {
            int carry = 0;
            for(int j = 0; j < y.length(); j++) {
                int p = (product[i+j] - '0') + ((x.charAt(i) - '0') * (y.charAt(j) - '0')) + carry;
                product[i+j] = (char) ('0' + (p % 10));
                carry = p / 10;
            }
            while (carry != 0) {
                int p = (product[i+y.length()] - '0') + carry;
                product[i+y.length()] = (char) ('0' + (p % 10));
                carry = p / 10;
            }
        }
        int start = a.length() + b.length();
        while(start > 0 && product[start] == '0') start --;

        StringBuilder sb = new StringBuilder();
        for(; start >= 0; start--) sb.append(product[start]);

        return sb.toString();
    }

    /**
     * https://www.interviewbit.com/problems/longest-common-prefix/
     * @param a
     * @return
     */
    public String longestCommonPrefix(ArrayList<String> a) {
        StringBuilder sb = new StringBuilder();
        int len = 0;
        for(int i = 0; i < a.size(); i++) {
            len = Math.max(len, a.get(i).length());
        }

        for(int i = 0; i < len; i++) {
            char x = ' '; int j = 0;
            for(; j < a.size(); j++) {
                if (a.get(j).length() <= i) return sb.toString();
                x = a.get(j).charAt(i); break;
            }
            for(; j < a.size(); j++) {
                if (a.get(j).length() <= i) return sb.toString();
                if (a.get(j).charAt(i) != x) return sb.toString();
            }
            sb.append(x);
        }
        return sb.toString();
    }

    /**
     * https://www.interviewbit.com/problems/roman-to-integer/
     * @param a
     * @return
     */
    public int romanToInt(String a) {
        int value = 0;
        for(int i = 0; i < a.length(); i++) {
            char c = a.charAt(i);
            char c2 = (i < a.length()-1) ? a.charAt(i+1) : ' ';
            if (c == 'I') {
                if (c2 == 'V' || c2 == 'X') value -= 1;
                else value += 1;
            } else if (c == 'V') {
                value += 5;
            } else if (c == 'X') {
                if (c2 == 'L' || c2 == 'C') value -= 10;
                else value += 10;
            } else if (c == 'L') {
                value += 50;
            } else if (c == 'C') {
                if (c2 == 'D' || c2 == 'M') value -= 50;
                else value += 50;
            } else if (c == 'D') {
                value += 500;
            } else if (c == 'M') {
                value += 1000;
            }
        }
        return value;
    }

    /**
     * https://www.interviewbit.com/problems/atoi/
     * @param a
     * @return
     */
    public int atoi(final String a) {
        int start = 0, m = 1;
        long value = 0;

        while(a.charAt(start) == ' ') start++;

        if ((a.charAt(start) < '0' || a.charAt(start) > '9') && a.charAt(start) != '-' && a.charAt(start) != '+') return 0;
        if (a.charAt(start) == '-' && a.length() > start + 1 && (a.charAt(start + 1) < '0' || a.charAt(start + 1) > '9')) return 0;
        if (a.charAt(start) == '+' && a.length() > start + 1 && (a.charAt(start + 1) < '0' || a.charAt(start + 1) > '9')) return 0;

        if (a.charAt(start) == '-') {
            start++;
            m = -1;
        } else if (a.charAt(start) == '+') {
            start++;
        }

        for(; start < a.length(); start++) {
            char c = a.charAt(start);
            if (c < '0' || c > '9') break;
            value *= 10l;
            value += (long) (c - '0');
            if (value >= (long) Integer.MAX_VALUE) {
                return (m == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
        }
        return m * (int) value;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().atoi("5121478262 8070067M75 X199R 547 8C0A11 93I"));
    }

}
