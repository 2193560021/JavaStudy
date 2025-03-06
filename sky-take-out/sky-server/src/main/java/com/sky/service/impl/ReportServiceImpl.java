package com.sky.service.impl;

import com.sky.dto.GoodsSalesDTO;
import com.sky.entity.Orders;
import com.sky.mapper.OrderMapper;
import com.sky.mapper.UserMapper;
import com.sky.service.ReportService;
import com.sky.service.WorkspaceService;
import com.sky.vo.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private OrderMapper orderMapper;


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private WorkspaceService workspaceService;

    @Override
    public TurnoverReportVO getTurnoverStatistics(LocalDate begin, LocalDate end) {
        List<LocalDate> dateList = new ArrayList<>();
        dateList.add(begin);
        while(!begin.equals(end)){
            begin = begin.plusDays(1);
            dateList.add(begin);
        }
        List<Double> turnoverList = new ArrayList<>();
        for (LocalDate date : dateList) {
            LocalDateTime beginTime = LocalDateTime.of(date, LocalTime.MIN);
            LocalDateTime endTime = LocalDateTime.of(date, LocalTime.MAX);
            // select sum(amount) from orders where order_time > date and order time < date + 1 and status = 5
            Map map = new HashMap<>();
            map.put("begin", beginTime);
            map.put("end", endTime);
            map.put("status", Orders.COMPLETED);
            Double turnOver = orderMapper.sumByMap(map);
            turnOver = turnOver == null ? 0.0 : turnOver;
            turnoverList.add(turnOver);
        }


        String join = StringUtils.join(dateList, ",");
        return TurnoverReportVO.builder()
                .dateList(join)
                .turnoverList(StringUtils.join(turnoverList, ","))
                .build();
    }

    @Override
    public UserReportVO getUserStatistics(LocalDate begin, LocalDate end) {
        List<LocalDate> dateList = new ArrayList<>();
        dateList.add(begin);
        while(!begin.equals(end)){
            begin = begin.plusDays(1);
            dateList.add(begin);
        }
        List<Integer> totalUserList = new ArrayList<>();
        List<Integer> newUserList = new ArrayList<>();
        for (LocalDate date : dateList) {
            LocalDateTime beginTime = LocalDateTime.of(date, LocalTime.MIN);
            LocalDateTime endTime = LocalDateTime.of(date, LocalTime.MAX);
            Map map = new HashMap<>();
            map.put("begin", beginTime);
            map.put("end", endTime);
            Integer AllUser = userMapper.getAllByMap(map);
            Integer NewUser = userMapper.getNewRegisterByMap(map);
            totalUserList.add(AllUser);
            newUserList.add(NewUser);
        }
        return UserReportVO.builder()
                .dateList(StringUtils.join(dateList, ","))
                .totalUserList(StringUtils.join(totalUserList, ","))
                .newUserList(StringUtils.join(newUserList, ","))
                .build();

    }

    @Override
    public OrderReportVO getOrderStatistics(LocalDate begin, LocalDate end) {
        List<LocalDate> dateList = new ArrayList<>();
        dateList.add(begin);
        while(!begin.equals(end)){
            begin = begin.plusDays(1);
            dateList.add(begin);
        }

        List<Integer> orderCountListList = new ArrayList<>();
        List<Integer> validOrderCountListList = new ArrayList<>();
        List<Integer> totalOrderCountList = new ArrayList<>();
        List<Integer> validOrderCountList = new ArrayList<>();
        List<Double> orderCompletionRateList = new ArrayList<>();
        for (LocalDate date : dateList) {
            LocalDateTime beginTime = LocalDateTime.of(date, LocalTime.MIN);
            LocalDateTime endTime = LocalDateTime.of(date, LocalTime.MAX);
            Map map = new HashMap<>();
            map.put("end", endTime);
            Integer OrderCount = orderMapper.countByMap(map);
            map.put("begin", beginTime);
            map.put("status", Orders.COMPLETED);
            Integer validOrderCount = orderMapper.countByMap(map);
            orderCountListList.add(OrderCount);
            validOrderCountListList.add(validOrderCount);
        }
        Map map2 = new HashMap<>();


        Integer totalOrderCount = orderCountListList.stream().reduce(Integer::sum).get();
        Integer validOrderCount = validOrderCountListList.stream().reduce(Integer::sum).get();

        Double orderCompletionRate = 0.0;
        orderCompletionRate = validOrderCount * 1.0 / (totalOrderCount == 0 ? 1 : totalOrderCount);

        return OrderReportVO.builder()
                .dateList(StringUtils.join(dateList, ","))
                .orderCountList(StringUtils.join(orderCountListList, ","))
                .validOrderCountList(StringUtils.join(validOrderCountListList, ","))
                .totalOrderCount(totalOrderCount)
                .validOrderCount(validOrderCount)
                .orderCompletionRate(orderCompletionRate)
                .build();
    }

    @Override
    public SalesTop10ReportVO getTop10(LocalDate begin, LocalDate end) {
        LocalDateTime beginTime = LocalDateTime.of(begin, LocalTime.MIN);
        LocalDateTime endTime = LocalDateTime.of(end, LocalTime.MAX);


        List<String> name = new ArrayList<>();
        List<String> number = new ArrayList<>();
        List<GoodsSalesDTO> top10List = orderMapper.getTop10(beginTime, endTime);
        for (GoodsSalesDTO top10 : top10List) {
            name.add(top10.getName());
            number.add(top10.getNumber().toString());
        }


        return SalesTop10ReportVO.builder()
               .nameList(StringUtils.join(name, ","))
               .numberList(StringUtils.join(number, ","))
               .build();
    }

    @Override
    public void exportExcel(HttpServletResponse response) {
        //查询数据库
        LocalDate begin = LocalDate.now().minusDays(30);
        LocalDateTime beginTime = LocalDateTime.of(begin, LocalTime.MIN);
        LocalDate end = LocalDate.now().minusDays(1);
        LocalDateTime endTime = LocalDateTime.of(end, LocalTime.MAX);

        BusinessDataVO businessDataVO = workspaceService.getBusinessData(beginTime, endTime);
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("template/运营数据报表模板.xlsx");
        //通过POI写入s
        try {
            XSSFWorkbook excel = new XSSFWorkbook(in);
            //填充数据
            //获取第一个sheet
            XSSFSheet sheet = excel.getSheetAt(0);
            sheet.getRow(1).getCell(1).setCellValue("时间：" + begin + "至" + end);
            XSSFRow row = sheet.getRow(3);
            row.getCell(2).setCellValue(businessDataVO.getTurnover());
            row.getCell(4).setCellValue(businessDataVO.getOrderCompletionRate());
            row.getCell(6).setCellValue(businessDataVO.getNewUsers());

            row = sheet.getRow(4);
            row.getCell(2).setCellValue(businessDataVO.getValidOrderCount());
            row.getCell(4).setCellValue(businessDataVO.getUnitPrice());


            //明细数据
            for (int i = 0; i < 30; i++) {
                LocalDate date = begin.plusDays(i);
                BusinessDataVO businessData = workspaceService.getBusinessData(LocalDateTime.of(date, LocalTime.MIN), LocalDateTime.of(date, LocalTime.MAX));
                row = sheet.getRow(7 + i);
                row.getCell(1).setCellValue(date.toString());
                row.getCell(2).setCellValue(businessData.getTurnover());
                row.getCell(3).setCellValue(businessData.getValidOrderCount());
                row.getCell(4).setCellValue(businessData.getOrderCompletionRate());
                row.getCell(5).setCellValue(businessData.getUnitPrice());
                row.getCell(6).setCellValue(businessData.getNewUsers());
            }

            //下载文件
            ServletOutputStream out = response.getOutputStream();
            excel.write(out);

            out.close();
            excel.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
