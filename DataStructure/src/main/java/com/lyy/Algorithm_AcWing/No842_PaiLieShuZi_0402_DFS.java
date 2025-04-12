package com.lyy.Algorithm_AcWing;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;


public class No842_PaiLieShuZi_0402_DFS {
	
	static int n;
	static int[] res;
	static boolean[] used;
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		res = new int[n + 1];
		used = new boolean[n + 1];
		System.out.println();
		Scanner scanner = new Scanner(System.in);
		scanner.next();
		
		dfs(0);
		
	}
	
	static void dfs(int p) {
		if(p == n) {
			for(int i = 0; i < n; i++){
				System.out.print(res[i]);
				if(i != n - 1) {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
		
		for(int i = 1; i <= n; i++) {
			if(!used[i]) {
				res[p] = i;
				used[i] = true;
				dfs(p + 1);
				used[i] = false;
			}
		}
	}

}
