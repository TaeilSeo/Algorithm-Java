import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	
	static int N;
	static int[][] grid;
	static boolean[] ch;
	
	static double answer;

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int t=1; t<=T; t++) {
			N = sc.nextInt();
			grid = new int[N][N];
			ch = new boolean[N];
			answer = Integer.MIN_VALUE;
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					grid[i][j] = sc.nextInt();
				}
			}
			
			perm(0, 1.000000);
			
			System.out.println(String.format("#%d %6f", t, (double)answer * 100));
		}
	}

	private static void perm(int L, double mul) {
		if(L == N) {
//			System.out.println("completed!!");
			answer = Math.max(answer, mul);
			return;
		}
		
		if((double)answer / mul >= 1.000000) return;
		
		for(int i=0; i<N; i++) {
			if(ch[i] || grid[L][i] == 0) continue;
			
			ch[i] = true;
			
			double next = ((double)grid[L][i] / 100) * mul;
			perm(L+1, next);
			
			ch[i] = false;
		}
	}
}
