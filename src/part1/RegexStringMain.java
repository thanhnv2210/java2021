package part1;

import java.util.regex.Pattern;

public class RegexStringMain {
    public static void main(String[] args) {
//        System.out.println("30000.20000".matches());
        System.out.println(Pattern.compile("^30000").matcher("30000.20000").find());
        System.out.println(Pattern.compile("^[1,2,9]0000").matcher("30000.20000").find());
        System.out.println(Pattern.compile("^30000.[1-4]").matcher("30000.50000").find());
    }


}
