package com.lyy.HONOR;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main002 {
    public static void main(String[] args) throws Exception  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String no = br.readLine();
        String tmp1 = no.split("\\[")[1];
        String tmp2 = tmp1.split("\\]")[0];

        String[] line = tmp2.split(" ");

        double p_large = Double.parseDouble(line[0]);
        int n_means = Integer.parseInt(line[1]);
        int n_over = Integer.parseInt(line[2]);

        double res = 0.0;
        double[] arr_res = new double[n_means + 1];
        for(int k = 1; k <= n_means; k++){


        }

        System.out.printf("%1.2f", arr_res[n_over - 1]);

    }

    static double judge(int n_means, int k, double p_large){
        double res = com(n_means, k) * Math.pow(p_large, k) * Math.pow(1 - p_large, n_means - k);
        return res;
    }

    static double com(int n_means, int k){
        if(k > n_means - k){
            k = n_means - k;
        }
        double res = 1.0;
        for (int i = 1; i < k; i++) {
            res = res * (n_means - k + i) / i;
        }

        return res;
    }
}
