import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	static int N;
	static int[][] grid;
	static boolean[][] ch;
	static int max;
	static int res;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int t=1; t<=T; t++) {
			N = sc.nextInt();
			grid = new int[N][N];
			max = 0;
			res = 0;
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					grid[i][j] = sc.nextInt();
					max = Math.max(max, grid[i][j]);
				}
			}
			
			for(int m=0; m<=max; m++) {
				ch = new boolean[N][N];
				int cnt = 0;
				
				for(int i=0; i<N; i++) {
					for(int j=0; j<N; j++) {
						if(!ch[i][j] && grid[i][j] > m) {
							ch[i][j] = true;
							cnt++;
							dfs(m, i, j);
						}
					}
				}
				
				res = Integer.max(res, cnt);
			}
			
			System.out.println(String.format("#%d %d", t, res));
		}
	}
	
	private static void dfs(int degree ,int r, int c) {
		for(int d=0; d<4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(nr >= 0 && nr < N && nc >= 0 && nc < N && !ch[nr][nc] && grid[nr][nc] > degree) {
				ch[nr][nc] = true;
				dfs(degree, nr, nc);
			}
		}
	}
}
