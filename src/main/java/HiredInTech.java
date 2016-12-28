import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HiredInTech {

    public static int count_the_paths(List<String> grid) {
        int N = grid.size(), M = grid.get(0).length(); //Empty list?
        char[][] input = new char[N][M];
        int[][] count = new int[N][M];

        for(int i = 0; i < N; i++) {
            input[i] = grid.get(i).toCharArray();
        }

        count[N-1][0] = 1;

        for(int j = 1; j < M; j++) {
            if(input[N-1][j-1] == '0') {
                count[N-1][j] = count[N-1][j-1];
            } else {
                count[N-1][j] = 0;
            }
        }

        for(int i = N-2; i >= 0; i--) {
            if(input[i+1][0] == '0') {
                count[i][0] = count[i+1][0];
            } else {
                count[i][0] = 0;
            }
        }

        for(int i = N-2; i >= 0; i--) {
            for(int j = 1; j < M; j++) {
                count[i][j] = 0;
                if(input[i+1][j] == '0') count[i][j] += count[i+1][j];
                if(input[i][j-1] == '0') count[i][j] += count[i][j-1];
                count[i][j] %= 1000003;
            }
        }

        return count[0][M-1];
    }

    public static void sort_the_files(int n, List<String> result) {
        for(int i = 1; i <= n && i <= 1001; i++) result.add("IMG" + i + ".jpg");
        for(int i = 1002; i <= n && i <= 1999; i++) result.add("IMG" + i + ".jpg");
        for(int i = 10000; i <= n && i <= 11001; i++) result.add("IMG" + i + ".jpg");
        for(int i = 100000; i <= n && i <= 101001; i++) result.add("IMG" + i + ".jpg");
        for(int i = 1000000; i <= n && i <= 1001001; i++) result.add("IMG" + i + ".jpg");

        Collections.sort(result);

        while (result.size() > 1000) {
            result.remove(1000);
        }
    }

    public static int cover_the_border(int l, List< List<Integer> > radars) {
        Collections.sort(radars, new Comparator<List<Integer>>() {
            public int compare(List<Integer> o1, List<Integer> o2) {
                return o1.get(0) - o2.get(0);
            }
        });

        int uncovered = radars.get(0).get(0);
        int end = radars.get(0).get(1);

        for(int i = 1; i < radars.size(); i++) {
            if (radars.get(i).get(0) > end) {
                uncovered += radars.get(i).get(0) - end;
                end = radars.get(i).get(1);
            } else if (radars.get(i).get(1) > end) {
                end = radars.get(i).get(1);
            }
        }

        uncovered += (l - end);
        return l - uncovered;
    }

    public static void main(String[] args) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        list.add(ImmutableList.of(5, 10));
        list.add(ImmutableList.of(3, 25));
        list.add(ImmutableList.of(46, 99));
        list.add(ImmutableList.of(39, 40));
        list.add(ImmutableList.of(45, 50));
        System.out.println(cover_the_border(100, list));
    }
}
