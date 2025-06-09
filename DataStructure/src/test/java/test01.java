import com.lyy.DP.Money;
import com.lyy.LeetCode.no739;
import org.junit.Test;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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

    @Test
    public void testMoney(){
        Date day = new Date();
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
        System.out.println(day);

        System.out.println(localDateTime.plusYears(2L));
    }

    @Test
    public void testMoney1(){


        String acctmanXmlCode = "<entity name=\"AM_BillingAccounts\" csvc=\"wade_acctmanm_ISyncDataSVCCSV_syncTableDataBillingAccountsSVCCSV\" path=\"com.ailk.acctmanm.base.service.common.SyncDataSVC@syncTableDataBillingAccounts\"/>";
        String acctmanXmlCode_SRV_Path = "com.ailk.acctmanm.shxi.service.common.csv.interfaces";



        String acctmanXmlCode_name = acctmanXmlCode.split("\"")[1];	//AM_BillingAccounts
        String acctmanXmlCode_csvc = acctmanXmlCode.split("\"")[3];	//wade_acctmanm_ISyncDataSVCCSV_syncTableDataBillingAccountsSVCCSV
        String acctmanXmlCode_path = acctmanXmlCode.split("\"")[5];	//com.ailk.acctmanm.base.service.common.SyncDataSVC@syncTableDataBillingAccounts

        String acctmanXmlCode_SRV_Name = acctmanXmlCode_csvc.split("_")[2];	//ISyncDataSVCCSV
        String acctmanXmlCode_SRV_Method = acctmanXmlCode_csvc.split("_")[3];	//syncTableDataBillingAccountsSVCCSV
        String acctmanXmlCode_SRV_INTERFACE = acctmanXmlCode_SRV_Path + "." + acctmanXmlCode_SRV_Name;

        LocalDateTime dateNow = LocalDateTime.now();


        Map<String, Object> params = new HashMap<>();
        params.put("SERVICE_CODE", acctmanXmlCode_csvc);		//1服务编码
        params.put("CENTER_CODE", "wade-acctmanm");		//1服务提供方 关联服务所属中心
        params.put("SERVICE_NAME", acctmanXmlCode_SRV_Method);		//1服务名称 服务的唯一标识，用于加载到编排客户端资源管理器
        params.put("DESCRIPTION", "");		//0服务提供方 关联服务所属中心
        params.put("SERVICE_TYPE", "1");		//1服务类型 1-中心服务类、3-封装编排类
        params.put("SERVICE_EXTEND_TYPE", "2");		//服务提供方 关联服务所属中心
        params.put("RELATED_TEMPLATE_TAG", "");		//服务编码
        params.put("SRV_INTERFACE", acctmanXmlCode_SRV_INTERFACE);		//1服务接口类
        params.put("SRV_IMPL_CLASS", "");		//服务实现类 null
        params.put("SRV_METHOD", acctmanXmlCode_SRV_Method);		//服务实现类
        params.put("SRV_RETURN", "com.ailk.common.data.IDataOutput");		//返回类型 java的类型，如果是Map类型，具体的key对应到“服务参数表”的出参对应
        params.put("PROTOCOL", "remote");		//调用协议 注明服务调用者使用哪种协议 http+xml、http+hession、http+json、ws、socket、FTP等
        params.put("VERSION", "1.0");		//服务版本 "N-当前版本（新版本）O-旧版本"
        params.put("STATUS", "U");		//状态 "C--创建M--修改W--待上线U--正常，已上线X--已下线D--已废弃E--审批不通过"
        params.put("OP_ID", "1");		//服务创建者
        params.put("CREATE_DATE", dateNow);		//创建时间 发布时间，注册时间
        params.put("VALID_DATE", dateNow);		//生效时间
        params.put("EXPIRE_DATE", dateNow.plusYears(2L));		//失效时间
        params.put("REMARKS", " ");		//备注
        params.put("SRV_OP_TYPE", "Q");		//服务操作类型
        params.put("IS_REVERSE", "F");		//服务关系
        params.put("TENANT_CODE", "V0");		//租户
        params.put("TENANT_NAME", "V0");		//租户名称
        params.put("EXT_A", "");		//扩展字段A
        params.put("EXT_B", "");		//扩展字段B
        params.put("EXT_C", "");		//扩展字段C

        System.out.println(params);
    }

}
