package com.lyy.Algorithm_AcWing;

public class SingleNum {

    public static int solution(int[] cards) {
        // Edit your code here
        int result = 0;
        for (int i = 0;i < cards.length;i++){
            result ^= cards[i];
            System.out.println("Current result: " + result);
        }
        return result;
    }

    public static void main(String[] args) {
        // Add your test cases here

//        System.out.println(solution(new int[]{1, 1, 2, 2, 3, 3, 4, 5, 5}) == 4);
//        System.out.println(solution(new int[]{0, 1, 0, 1, 2}) == 2);
        System.out.println(solution(new int[]{7, 3, 3, 7, 10}) == 10);
    }
}
