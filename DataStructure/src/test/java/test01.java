import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

public class test01 {
    @Test
    public void testArray(){
        int[] a = new int[10];

        int[] nums = {1,3,2,5,4};

        int randomIndex = ThreadLocalRandom.current().nextInt(0, nums.length);

        int random = nums[randomIndex];

        System.out.println("random = " + random);

    }
}
