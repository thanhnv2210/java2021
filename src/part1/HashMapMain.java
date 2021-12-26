package part1;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.val;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class HashMapMain {
    public static final MySimpleDateFormat df = new MySimpleDateFormat("yyyy-MM-dd");
    public static void main(String[] args) {
        Map<CustomerSideC, Integer> data = getData();
        Set<CustomerSideC> setData = getSetData();
        // System.out.println(setData);
        final Integer[] index1 = {0};
        // setData.forEach(c -> { index1[0]++; System.out.println(index1[0] + " | " + c);});
        int reduce = setData.stream()
                .filter(c -> c != null && c.getCustomerId() != null)
                .flatMapToInt(o -> IntStream.of(o.getCustomerId()))
//                .flatMap(c -> Stream.of(Optional.of(c.getCustomerId())))
                .reduce(0, Integer::sum);

//                        .mapToInt(CustomerSideC::getCustomerId).sum();
        System.out.println("asjkfhsjkfh: " + reduce);

        List<CustomerSideC> reduce2 = setData.stream()
                .filter(Objects::nonNull)
                .filter(c -> //Optional.ofNullable(c.getName()).isPresent() &&
                        Optional.ofNullable(c.getName()).filter(o -> !o.startsWith("cus1")).isPresent())
                        //c.getName().startsWith("cus1") ) //Optional.ofNullable(c).isPresent()) //
//                .filter(c -> c != null && Optional.ofNullable(c.getName()).get().startsWith("cus1"))
                .collect(Collectors.toList());
        System.out.println("reduce2: " + reduce2);
        List<String> collect2 = reduce2.stream().map(CustomerSideC::getName).collect(Collectors.toList());
        System.out.println("collect2: " + collect2);
//        setData.stream().filter(c -> c != null && c.getCustomerId() != null)
//                .sorted()
//                //.sorted(
//                //(CustomerSideC x, CustomerSideC y) -> x.getFollowerId().compareTo(y.getCustomerId())
//                //)
//                .forEachOrdered(System.out::println);//.forEach(c -> { index1[0]++; System.out.println(index1[0] + " | " + c);});
//        System.out.println("<------> PEEK");
//        setData.stream().filter(c -> c != null && c.getCustomerId() != null)
//                .peek(System.out::println)
//                .forEachOrdered(System.out::println);
//                .sorted().forEach(System.out::println);
//        System.out.println("<------> PEEK END");
        System.out.println("<------>");
        data.forEach(
                (CustomerSideC key, Integer value) -> System.out.println(key + " | "+ value)
        );
        val cusChecking = new CustomerSideC("cusChecking",103,200,df.parse("2021-11-15"));
        System.out.println("cusChecking Id: " + data.get(cusChecking));
    }

    private static Map<CustomerSideC, Integer> getData() {
        val result = new HashMap<CustomerSideC, Integer>();
        val cus1 = new CustomerSideC("cus1",100,200,df.parse("2021-11-12"));
        val cus2 = new CustomerSideC("cus2",101,200,df.parse("2021-11-12"));
        val cus3 = new CustomerSideC("cus3",102,200,df.parse("2021-11-12"));
        val cus4 = new CustomerSideC("cus4",103,200,df.parse("2021-11-12"));
        val cus5 = new CustomerSideC("cus5",100,200,df.parse("2021-11-13"));
        val cus6 = new CustomerSideC("cus6",103,201,df.parse("2021-11-13"));
        val cus7 = new CustomerSideC("cus7",102,201,df.parse("2021-11-13"));
        val cus8 = new CustomerSideC(null,104,204,df.parse("2021-11-13"));
        result.put(null, -1);// This data work as mess data and we should filter it for every action.
        result.put(cus1, 1);
        result.put(cus2, 2);
        result.put(cus3, 3);
        result.put(cus4, 4);
        result.put(cus5, 5); // have same hashCode and equal cus1, so it will keep old key and replace old value to new value because have value for hashCode and equal
//        result.put(cus1, 1);
        result.put(cus6, 6);// have same hashCode with cus4 but not equal cus4, so it will store in the same bucket with cus4
        result.put(cus7, 7);
        result.put(cus8, 8);
        return result;
    }

    private static Set<CustomerSideC> getSetData() {
        val result = new HashSet<CustomerSideC>();
        val cus1 = new CustomerSideC("cus1",100,200,df.parse("2021-11-12"));
        val cus2 = new CustomerSideC("cus2",101,200,df.parse("2021-11-12"));
        val cus3 = new CustomerSideC("cus3",102,200,df.parse("2021-11-12"));
        val cus4 = new CustomerSideC("cus4",103,200,df.parse("2021-11-12"));
        val cus5 = new CustomerSideC("cus5",100,200,df.parse("2021-11-13"));
        val cus6 = new CustomerSideC("cus6",103,201,df.parse("2021-11-13"));
        val cus7 = new CustomerSideC("cus7",102,201,df.parse("2021-11-13"));
        val cus8 = new CustomerSideC(null,104,202,df.parse("2021-11-13"));
        result.add(null);
        result.add(cus1);
        result.add(cus2);
        result.add(cus3);
        result.add(cus4);
        result.add(cus5); // replace old value because have value for hashCode and equal
        result.add(cus6);
        result.add(cus7);
        result.add(cus8);
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
