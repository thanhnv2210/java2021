package part1;

import org.junit.jupiter.api.Test;

public class StringMain {
    public static void main(String[] args) {
        String input = "First Value";
        System.out.printf("%s:%s%n", input, input.hashCode());
        input = "Second Value";
        System.out.printf("%s:%s%n", input, input.hashCode());
    }

    @Test
    public void testAnnotation(){
        assert getCompanyName().equals("moneytap");
    }

    private static String getCompanyName(){
        return "moneytap";
    }
}
