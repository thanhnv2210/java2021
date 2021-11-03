package part1;

import lombok.*;

public class HashingObjectMain {
    public static void main(String[] args) {
        val cus1 = new CustomerSideA(100, "Thanh 1");
        //... <=> final CustomerSideA cus1 = new CustomerSideA(100, "Thanh 1");

        val cus2 = new CustomerSideA(100, "Thanh 2");
        System.out.printf("Check cus1.hashCode: %d -- cus2.hashCode: %d \n",cus1.hashCode(),cus2.hashCode());
        System.out.printf("Check cus1=cus2: %s \n",cus1.equals(cus2));

        val cus3 = new CustomerSideB(100, "Thanh 3");
        System.out.printf("Check cus1.hashCode: %d -- cus3.hashCode: %d \n",cus1.hashCode(),cus3.hashCode());
        //System.out.printf("Check cus1=cus3: %s \n",cus1.equals(cus3));
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class CustomerSideA{
    private Integer customerId;
    private String FistName;

    @Override
    public int hashCode() {
        return Integer.hashCode(customerId);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CustomerSideA) {
            return this.hashCode() == obj.hashCode();
        } else if (obj instanceof CustomerSideB) {
            return this.hashCode() == obj.hashCode() -1;
        }
        System.out.println(".....Unexpected Type");
        return false;
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class CustomerSideB{
    private Integer customerId;
    private String FistName;

    @Override
    public int hashCode() {
        return Integer.hashCode(customerId + 1);
    }
}

