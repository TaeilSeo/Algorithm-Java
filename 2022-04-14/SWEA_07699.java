import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

public class Solution {
	static int R, C;
	static char[][] grid;
	static boolean[] ch;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static int answer;
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int t=1; t<=T; t++) {
			R = sc.nextInt();
			C = sc.nextInt();
			sc.nextLine();
			
			grid = new char[R][C];
			for(int r=0; r<R; r++) {
				grid[r] = sc.nextLine().toCharArray();
			}
			
			ch = new boolean[26];
			answer = 0;
			
			ch[grid[0][0] - 65] = true;
			dfs(1, 0, 0, 1);
			
			System.out.println(String.format("#%d %d", t, answer));
		}
	}
	
	private static void dfs(int L, int r, int c, int cnt) {
		if(L > R * C) return;
		
		for(int d=0; d<4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(nr >= 0 && nr < R && nc >= 0 && nc < C && !ch[grid[nr][nc] - 65]) {
				ch[grid[nr][nc] - 65] = true;
				dfs(L+1, nr, nc, cnt + 1);
				ch[grid[nr][nc] - 65] = false;
			}
		}
		
		answer = Integer.max(answer, cnt);
	}
}
