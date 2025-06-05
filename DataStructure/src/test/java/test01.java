import com.lyy.DP.Money;
import com.lyy.LeetCode.no739;
import org.junit.Test;

import java.io.*;
import java.util.concurrent.ThreadLocalRandom;

public class test01 implements Serializable {
    private transient final int in = 10;
    @Test
    public void testArray(){
         int[] a = new int[10];

        int[] nums = {1,3,2,5,4};

        int randomIndex = ThreadLocalRandom.current().nextInt(0, nums.length);

        int random = nums[randomIndex];

        System.out.println("random = " + random);
    }

    @Test
    public void testArray1(){
        String acctmanXmlCode = "<entity name=\"AM_BillingAccounts\" csvc=\"wade_acctmanm_ISyncDataSVCCSV_syncTableDataBillingAccountsSVCCSV\" path=\"com.ailk.acctmanm.base.service.common.SyncDataSVC@syncTableDataBillingAccounts\"/>";


        String acctmanXmlCode_name = acctmanXmlCode.split("\"")[1];
        String acctmanXmlCode_csvc = acctmanXmlCode.split("\"")[3];
        String acctmanXmlCode_path = acctmanXmlCode.split("\"")[5];


        System.out.println("acctmanXmlCode_name = " + acctmanXmlCode_name);
        System.out.println("acctmanXmlCode_csvc = " + acctmanXmlCode_csvc);
        System.out.println("acctmanXmlCode_path = " + acctmanXmlCode_path);

        String classPath = no739.class.getPackage().getName();
        System.out.println("Class Path: " + classPath);


    }

}
