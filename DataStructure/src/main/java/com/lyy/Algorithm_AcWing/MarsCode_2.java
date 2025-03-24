package com.lyy.Algorithm_AcWing;

public class MarsCode_2 {
    public static int solution(int n, int k, int[] data) {

        // 初始化总花费为0
        int totalCost = 0;
        // 初始化当前携带的食物数量为0
        int currentFood = 0;

        // 遍历每一天
        for (int i = 0; i < n; i++) {
            // 如果当前携带的食物数量不足1份，需要购买
            if (currentFood < 1) {
                // 计算需要购买的食物数量
                int foodToBuy = Math.min(k, n - i);
                // 计算购买这些食物的花费
                totalCost += foodToBuy * data[i];
                // 更新当前携带的食物数量
                currentFood += foodToBuy;
            }
            // 每天消耗1份食物
            currentFood--;
        }

        return totalCost;



//        // Edit your code here
//        int money = 0;
////        int[] have = new int[n];
//        int have = 0;
//        for (int i = 0;i < n;i++){
////            have[i] = 0;
////            if(have != 1){
//            if(i == 0){
//                    money += data[0];
//                    System.out.println("money[1] = " + money);
//                    have ++;
//            }
//            System.out.println("第" + (i ) + "+ 1天开始了，持有"+ have + "份");
//            for(int j = 1;j <= k ;j++){
////                if(i + 1 == n)
////                    break;
//                int temp = i + j;
//                if(temp >= n){
//                    temp = n - 1;
//                }
//                if (data[i] <= data[temp]){
//                    if(have < k){
//                        have ++;
//                        money += data[i];
//                        System.out.println("money["+ (temp ) +"] = " + money);
//                        System.out.println("第" + temp + "天买了"+ 1 + "份，现有"+ have + "份");
//                    }
//                    //                    System.out.println("temp["+ (temp ) +"+ 1] = " + temp);
////                        System.out.println("第" + i + "+ 1天把第"+ (temp ) + "+ 1天的买了");
//                }
//
//
////                System.out.println("第" + i + "+ 1天持有"+ have + "份，吃了" + 1 + "份");
//
//                System.out.println("第" + (temp ) + "天不用买了");
//            }
//            System.out.println("第" + (i ) + "+ 1天度过了，持有"+ have + "份");
//            if(have > 0)
//                have --;
//            if(n - i - 1 < have || have == 0){
//                System.out.println("进入倒数一元素");
//                if(have == 0){
//                    money += data[i + 1];
//                    System.out.println("money["+ (i ) +"] = " + money);
//                    return money;
//                }
//                System.out.println("money["+ (i ) +"] = " + money);
//                return money;
//            }
//            System.out.println("第" + (i ) + "+ 1天度过了，吃了1份,还有"+ have + "份");
////            }
//        }
//
//        //打印have数组
//        System.out.println("have = " + have);
//
//
//        return money;
    }

    public static void main(String[] args) {
        // Add your test cases here

        System.out.println(solution(5, 2, new int[]{1, 2, 3, 3, 2}) == 9);
//        System.out.println(solution(6, 3, new int[]{4, 1, 5, 2, 1, 3}) == 9);
//        System.out.println(solution(4, 1, new int[]{3, 2, 4, 1}) == 10);
    }
}
