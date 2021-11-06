package part1;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.val;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class HashMapMain {
    public static final MySimpleDateFormat df = new MySimpleDateFormat("yyyy-MM-dd");
    public static void main(String[] args) {
        Map<CustomerSideC, Integer> data = getData();
        data.forEach(
                (CustomerSideC key, Integer value) -> System.out.println(key + " | "+ value)
        );
        val cusChecking = new CustomerSideC(103,200,df.parse("2021-11-15"));
        System.out.println("cusChecking Id: " + data.get(cusChecking));
    }

    private static Map<CustomerSideC, Integer> getData() {
        val result = new HashMap<CustomerSideC, Integer>();
        val cus1 = new CustomerSideC(100,200,df.parse("2021-11-12"));
        val cus2 = new CustomerSideC(101,200,df.parse("2021-11-12"));
        val cus3 = new CustomerSideC(102,200,df.parse("2021-11-12"));
        val cus4 = new CustomerSideC(103,200,df.parse("2021-11-12"));
        val cus5 = new CustomerSideC(100,200,df.parse("2021-11-13"));
        val cus6 = new CustomerSideC(103,201,df.parse("2021-11-13"));
        val cus7 = new CustomerSideC(102,201,df.parse("2021-11-13"));
        result.put(cus1, 1);
        result.put(cus2, 2);
        result.put(cus3, 3);
        result.put(cus4, 4);
        result.put(cus5, 5); // replace old value because have value for hashCode and equal
        result.put(cus6, 6);
        result.put(cus7, 7);
        return result;
    }
}

@Data
@EqualsAndHashCode(callSuper = true)
class MySimpleDateFormat extends SimpleDateFormat {
    public MySimpleDateFormat(String pattern)
    {
        super(pattern);
    }
    @Override
    public Date parse(String source)
    {
        try {
            return super.parse(source);
        } catch (ParseException ex){
            return null;
        }
    }

}
