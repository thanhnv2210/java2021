package leetcode.part1;


import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class FirstAlgorithm {

    public int search(int[] nums, int target) {
        int result = Arrays.binarySearch(nums,target);
        return result >= 0 ? result : -1;
    }

    public int firstBadVersion(int n) {
        return findFirstBadVersion(1,n);
    }
     public int findFirstBadVersion(int low, int high){
        if(isBadVersion(low))
            return low;
        int middle = low + (high - low) /2;// ***** prevent total int + int > Max_Int
        System.out.printf("%s - %s -> Middle:%s%n ",low,high,middle);
        if(low == middle){
            if(isBadVersion(high))
                return high;
            else
                return -1;
        }

        if(isBadVersion(middle)){
            return findFirstBadVersion(low, middle);
        } else
            return findFirstBadVersion(middle, high);
     }
    public static int firstBadData = 0;
    boolean isBadVersion(int n){
        return n >= firstBadData;
    }

    public int[] searchRange(int[] nums, int target) {
        int index = Arrays.binarySearch(nums,target);
        if(index <0)
            return new int[]{-1,-1};
        int left = index, right = index;
        final int limit_right = nums.length;
        while (left > 0 && nums[left-1] == target){
            left--;
        }
        while (right < limit_right && nums[right+1] == target){
            right++;
        }
        return new int[]{left, right};
    }

    // a is sorted array
    private static int binarySearch0(int[] a, int fromIndex, int toIndex,
                                     int key) {
        int low = fromIndex;
        int high = toIndex - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            int midVal = a[mid];

            if (midVal < key)
                low = mid + 1;
            else if (midVal > key)
                high = mid - 1;
            else
                return mid; // key found
        }
        return -(low + 1);  // key not found.
    }

    public static int[] sortedSquares(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        int[] result = new int[nums.length];
        int target = 0;
        int pointer = 0;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            int midVal = nums[mid];

            if (midVal < target)
                low = mid + 1;
            else if (midVal > target)
                high = mid - 1;
            else {
                result[pointer++] = 0; // target found and the first element is target*target=0
                low = mid - 1;
                high = mid + 1;
                break;
            }

        }
        if(pointer == 0){
            high = low;// current low is the position if we add the target value in this array
            low = high -1;
        }
        System.out.println("low:" + low + " high:" + high + " pointer:" + pointer);
        boolean reachLow = low < 0,reachHigh = high > nums.length -1;
        while (!(reachLow && reachHigh)){
            if(reachLow) {
                System.out.println("reachLow high:" + high + " pointer:" + pointer);
                result[pointer++] = nums[high] * nums[high++];
            } else if(reachHigh) {
                System.out.println("reachHigh low:" + low + " pointer:" + pointer);
                result[pointer++] = nums[low] * nums[low--];
            } else {
                int squareLow = nums[low] * nums[low];
                int squareHigh = nums[high] * nums[high];
                if(squareHigh > squareLow){
                    System.out.println("squareHigh > squareLow :: squareHigh:" + squareHigh + " squareLow:" + squareLow + " pointer:" + pointer);
                    result[pointer++] = squareLow;
                    low--;
                } else if (squareHigh < squareLow) {
                    System.out.println("squareHigh < squareLow :: squareHigh:" + squareHigh + " squareLow:" + squareLow + " pointer:" + pointer);
                    result[pointer++] = squareHigh;
                    high++;
                } else {
                    System.out.println("squareHigh = squareLow :: squareHigh:" + squareHigh + " squareLow:" + squareLow + " pointer:" + pointer);
                    result[pointer++] = squareLow;
                    result[pointer++] = squareHigh;
                    low--;
                    high++;
                }
            }
            if(!reachHigh && high > nums.length -1)
                reachHigh = true;
            if(!reachLow && low < 0)
                reachLow = true;
            System.out.println("Finish a round:" + Arrays.toString(result));
            System.out.println(" Next Round :: high:" + high + "(" + reachHigh + ") low:" + low + "(" + reachLow + ") pointer:" + pointer);
        }
        return result;
    }

    public static void rotate(int[] nums, int k) {
        k = k % nums.length;
        if (k > 0){
            int targetValue;
            int startIndex;
            int targetIndex;
            int cachedValue;
            if (nums.length % k == 0){
                for (int i = 0; i < k; i++){
                    cachedValue = nums[i];
                    startIndex = i;
                    targetIndex = k + i;
                    while (targetIndex != i){
                        targetIndex = (startIndex + k)% nums.length;
                        targetValue = nums[targetIndex];
                        nums[targetIndex] = cachedValue;
                        startIndex = targetIndex;
                        cachedValue = targetValue;
                    }
                }
            } else {
                startIndex = 0;
                cachedValue = nums[startIndex];
                targetIndex = k;
                while (targetIndex != 0){
                    targetIndex = (startIndex + k)% nums.length;
                    targetValue = nums[targetIndex];
                    nums[targetIndex] = cachedValue;
                    startIndex = targetIndex;
                    cachedValue = targetValue;
                }
            }
        }
        System.out.println("rotate " + k + " steps to the right:" + Arrays.toString(nums));
    }

    public static void moveZeroes(int[] nums) {
        int pointer = 0;
        for(int i = 0;i< nums.length ; i++){
            if(nums[i] != 0)
                nums[pointer++] = nums[i];
        }
        for(;pointer < nums.length ; pointer++){
            nums[pointer] = 0;
        }
        System.out.println(Arrays.toString(nums));
    }

    public static int[] twoSum(int[] numbers, int target) {
        int start,matched;
        for(int i = 0;i < numbers.length; i ++){
            start = numbers[i];
            matched = target - start;
            int j = Arrays.binarySearch (numbers, i + 1, numbers.length,matched );
            if (j > 0)
                return new int[]{i+1,j+1};
//            for (int j =i+1;j < numbers.length;j++){
//                if(numbers[j] == matched)
//                    return new int[]{i+1,j+1};
//                if(numbers[j] > matched)
//                    break;
//            }
        }
        return null;
    }

    public static int[] twoSumV2(int[] numbers, int target) {
        System.out.println(Arrays.toString(numbers));
        int half = target/2;
        int targetSum = target%2;
        for(int i = 0;i < numbers.length; i ++){
            numbers[i] = numbers[i] - half;//get a half
        }
        int high = Arrays.binarySearch (numbers, 0 );
        System.out.println("pointer:" + high);
        if(high < 0) high = -high-1; else if(high == 0) high = 1;
        int low = high -1;
        System.out.println("low:" + low + " high:" + high);
        int direct;
        while (true){
            direct = numbers[low] + numbers[high] - targetSum;
            if (direct == 0)
                return new int[]{low+1,high+1};
            if(direct > 0){
                if(low > 0) low--;
            }else {
                if(high < numbers.length-1) high++;
            }

        }
    }
    public void reverseString(char[] s) {
        char cacheValue;
        for(int i = 0;i < s.length /2 ; i ++){
            cacheValue = s[i];
            s[i] = s[s.length-1-i];
            s[s.length-1-i] = cacheValue;
        }
    }

    public String reverseWords(String s) {
        int start = 0,end = 0;
        StringBuilder sb = new StringBuilder();
        while(end < s.length()){
            if(end == s.length() - 1){
                for (int i = end; i >= start;i--) {
                    sb.append(s.charAt(i));
                }
                break;
            }
            if(s.charAt(end) == ' '){
                for (int i = end - 1; i >= start;i--) {
                    sb.append(s.charAt(i));
                }
                sb.append(' ');
                start = end + 1;
            }
            end++;
        }
        return sb.toString();
    }

    public boolean containsDuplicate(int[] nums) {
//        List<Integer> collect = Arrays.stream(nums).boxed().collect(Collectors.toList());
//        return Arrays.stream(nums).anyMatch(i -> Collections.frequency(collect,i) > 1);
//        HashSet<Integer> abc = Arrays.stream(nums).boxed().collect(Collectors.toCollection(HashSet::new));
        Set<Integer> abc = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        //HashSet<Integer> abc = new HashSet(Collections.singletonList(nums));
        System.out.println(Arrays.toString(abc.toArray()));
//        Arrays.sort(nums);
//        for(int i =0; i < nums.length-1; i ++){
//            if(nums[i]==nums[i+1])
//                return true;
//        }
//        return false;
        //return abc.size() != nums.length;
        Set<Integer> tmp = new HashSet<>();
        for (int num : nums) {
            if (!tmp.add(num))
                return true;
        }
        return false;
    }

    public static int maxSubArray(int[] nums) {
        List<Integer> tmp = new ArrayList<>();
        boolean isChange = false;
        int value = nums[0];
        boolean havePositive = false;
        int valueNegative = nums[0];

        for(int i = 1; i < nums.length; i++){
            if(!havePositive){
                if(nums[i] >=0)
                    havePositive = true;
                else
                    valueNegative = Math.max(valueNegative,nums[i]);
            }
            if((value >= 0) == (nums[i] >=0)){
                value += nums[i];
            } else {
                if(tmp.size() == 0){
                    if(value > 0)
                        tmp.add(value);
                } else
                    tmp.add(value);
                value = nums[i];
            }
        }
        if(!havePositive)
            return valueNegative;
        if(value > 0) tmp.add(value);

        int oldSum=nums[0],newSum=nums[0];
        boolean isNewMax = false;
//        for(int i = 0; i < tmp.size()/2; i++){
//            if(nums[2*i + 1] + nums[2*(i + 1)] < 0){
//                // Am nhieu hon duong
//                // oldSum giu nguyen
//                // new sum la gia tri moi
//                if(oldSum > nums[2*(i + 1)]){
//                    isNewMax = false;
//                } else {
//
//                }
//            }
//            if(nums[i] > 0){
//                oldSum = Math.max(oldSum,nums[i]);
//                newSum += nums[i]
//            } else {
//
//            }
//        }
//        return Math.max(oldSum,newSum);
        System.out.println(tmp);
        return 1;
    }

    public ListNode middleNode(ListNode head) {
        ListNode result = head,current = head;
        int index = 1;
        while (current.next != null){
            index++;
            current = current.next;
            if(index%2 ==0)
                result = result.next;
        }
        return result;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode before = null,current = head;
        int index = 1;
        while (current.next != null){
            index++;
            if(index > n +1) {
                before = before.next;
            } else if(index == n + 1) {
                before = head;
            }
            current = current.next;
        }
        if(index == n){
            return head.next;
        }
        if( before != null && before.next != null)
            before.next = before.next.next;
        else
            return null;
        return head;
    }

    public int fib(int n) {
        if(n <= 1)
            return n;
        return fib(n-1) + fib(n-2);
    }
    public int fib3(int n) {
        if(n <= 1)
            return n;
        int result = 1, cached = 0, tmp;
        for(int i = 2;i <= n; i++){
            tmp = result;
            result += cached;
            cached = tmp;
        }
        return result;
    }
    public int fib4(int n) {
        if(n <= 2)
            return Math.min(n,1);
        int tn1 = 1, tn2=1,tn3 = 2, tmp;
        for(int i = 4;i <= n; i++){
            tmp = tn3;
            tn3 += tn1 + tn2;
            tn1 = tn2;
            tn2 = tmp;
        }
        return tn3;
    }



    public int fib2(int n) {
        return (int) ((Math.pow(1+Math.sqrt(5)/2,n) - Math.pow(1-Math.sqrt(5)/2,n))/Math.sqrt(5));
    }

    public static int maxSubArrayV2(int[] nums) {
        List<Integer> tmp = new ArrayList<>();
        boolean isChange = false;
        int value = nums[0];
        boolean havePositive = false;
        int valueNegative = nums[0];

        for(int i = 1; i < nums.length; i++){
            if(!havePositive){
                if(nums[i] >=0)
                    havePositive = true;
                else
                    valueNegative = Math.max(valueNegative,nums[i]);
            }
            if((value >= 0) == (nums[i] >=0)){
                value += nums[i];
            } else {
                if(tmp.size() == 0){
                    if(value >= 0)
                        tmp.add(value);
                } else
                    tmp.add(value);
                value = nums[i];
            }
        }
        if(!havePositive)
            return valueNegative;
        if(value >= 0) tmp.add(value);
        System.out.println(tmp);

        int sum = tmp.get(0);
        int maxGlobal = tmp.get(0);
        int maxLocal = tmp.get(0);
        for(int i = 1;i < tmp.size();i++){
            if(sum + tmp.get(i) < 0){
                maxGlobal = Math.max(maxGlobal, maxLocal);
                System.out.println("maxGlobal: " + maxGlobal);
                sum = tmp.get(++i);
                maxLocal = sum;
            } else {
                sum += tmp.get(i);
                maxLocal = Math.max(maxLocal,sum);
            }

        }
        System.out.println("maxGlobal: " + maxGlobal + " maxLocal: " + maxLocal + " sum: " + sum);
        return Math.max(maxGlobal, maxLocal);
    }
    public int minCostClimbingStairs(int[] cost) {
        if(cost.length == 1)
            return cost[0];
        else if (cost.length == 2)
            return Math.min(cost[0],cost[1]);
        int result0 = cost[0],result1 = cost[1];
        for (int i = 2;i < cost.length;i++){
            result0 += cost[i];
            result1 += cost[i];
        }
        return 1;
    }


    public static void main(String[] args) {
        System.out.println("Result: " + maxSubArrayV2(new int[]{-2,1,-1,-3,4,-1,2,1,-5,4, -7}));

        System.out.println("Result: " + maxSubArrayV2(new int[]{-12,-5,-7,-9}));

//        System.out.println(Arrays.toString(twoSumV2(new int[]{2,7,11,15},9)));
//        System.out.println(Arrays.toString(twoSumV2(new int[]{4,4},8)));
//        System.out.println(Arrays.toString(twoSumV2(new int[]{4,5},9)));
//        System.out.println(Arrays.binarySearch(new int[]{4,4},4));
//        System.out.println(Arrays.binarySearch(new int[]{4,4},5));
//        System.out.println(Arrays.binarySearch(new int[]{4,4},3));
//        System.out.println(Arrays.binarySearch(new int[]{-2,0,2},0));
//        System.out.println(Arrays.binarySearch(new int[]{-2,0,0,2},0));
//        System.out.println(Arrays.binarySearch(new int[]{-2,0,0,0,2},0));
//        System.out.println(Arrays.binarySearch(new int[]{-1,0,0,0},0));
//
//        System.out.println(Arrays.binarySearch(new int[]{-1,1,2,3},0));
//        System.out.println(Arrays.toString(twoSumV2(new int[]{2,7,11,15},9)));
//        moveZeroes(new int[]{0,1,0,3,12});
//        rotate(new int[]{7,1,2,3,4,5,6}, 3);
//        System.out.println(Arrays.toString(sortedSquares(new int[]{-7,-3,2,3,11})));
    }

    @Test
    public void checkFirstBadVersion(){
//        firstBadData = 1702766719;
//        //badData = Arrays.asList(false,false,false,true,true,true,true,true,true);
//        FirstAlgorithm firstAlgorithm = new FirstAlgorithm();
//        assert firstAlgorithm.firstBadVersion(2126753390) == firstBadData;
        int low = 4, high = 5;
//        assert (low + high) >>> 1 == 4;
    }
}
