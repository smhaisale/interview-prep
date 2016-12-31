package interviewbit.strings;

/**
 * Created by shahbaaz on 12/28/16.
 */
public class Common {

    public static final String reverse(String s) {
        StringBuilder sb = new StringBuilder();
        for(int i = s.length() - 1; i >= 0; i--) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }
}
