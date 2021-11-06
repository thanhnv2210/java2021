package part1;

import lombok.*;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public class HashingObjectMain {
    public static void main(String[] args) {
        val cus1 = new CustomerSideA(100, "Thanh 1");
        //... <=> final CustomerSideA cus1 = new CustomerSideA(100, "Thanh 1");

        val cus2 = new CustomerSideA(100, "Thanh 2");
        System.out.printf("Check cus1.hashCode: %d -- cus2.hashCode: %d \n",cus1.hashCode(),cus2.hashCode());
        System.out.printf("Check cus1=cus2: %s \n",cus1.equals(cus2));

        val cus3 = new CustomerSideB(100, "Thanh 3");
        System.out.printf("Check cus1.hashCode: %d -- cus3.hashCode: %d \n",cus1.hashCode(),cus3.hashCode());
        // System.out.printf("Check cus1=cus3: %s \n",cus1.equals(cus3));
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
        //return Integer.hashCode(customerId);
        return Arrays.hashCode(new Object[]{customerId});
        // ??? org.apache.commons.lang3.builder.HashCodeBuilder
        //return new HashCodeBuilder().append(customerId).toHashCode();
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


@Data
@NoArgsConstructor
@AllArgsConstructor
class CustomerSideC{
    private Integer customerId;
    private Integer followerId;
    private Date startTime;

    @Override
    public int hashCode() {
        return Integer.hashCode(customerId );
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CustomerSideC) {
            return Objects.equals(this.customerId, ((CustomerSideC) obj).customerId)
                    && Objects.equals(this.followerId, ((CustomerSideC) obj).followerId);
        }
        System.out.println(".....Unexpected Type");
        return false;
    }
}
