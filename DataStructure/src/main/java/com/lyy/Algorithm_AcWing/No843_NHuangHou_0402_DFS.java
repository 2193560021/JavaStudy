package com.lyy.Algorithm_AcWing;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class No843_NHuangHou_0402_DFS {
	
	static int n;
//	static int[] res;
	static boolean[] col_used;
	static boolean[] dg_used;
	static boolean[] udg_used;
	static char[][] res;

	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
//		res = new int[n + 1];
		res = new char[n][n];
		col_used = new boolean[n + 1];
		dg_used = new boolean[n * 2];
		udg_used = new boolean[n * 2];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				res[i][j] = '.';
			}
		}
		
		dfs(0);
		
	}
	
	static void dfs(int p) {
		if(p == n) {
			for(int i = 0; i < n; i++){
				System.out.println(res[i]);
			}
			System.out.println("");
			return;
		}
		
		for(int i = 0; i < n; i++) {
			if(!col_used[i] && !dg_used[p + i] && !udg_used[n - p + i]) {
				res[i][p] = 'Q';
				col_used[i] = dg_used[p + i] = udg_used[n - p + i] = true;
				dfs(p + 1);
				col_used[i] = dg_used[p + i] = udg_used[n - p + i] = false;
				res[i][p] = '.';
			}
		}
	}

}
