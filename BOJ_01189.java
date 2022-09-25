package baekjoon01189_0920;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int N, M, K;
	static char[][] grid;
	static boolean[][] ch;
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	
	static int result;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream(new File(Main.class.getResource("").getPath() + "input.txt")));
		Scanner sc = new Scanner(System.in);		

		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		sc.nextLine();
		
		grid = new char[N][M];
		ch = new boolean[N][M];
		
		for(int n=0; n<N; n++) {
			grid[n] = sc.nextLine().toCharArray();
		}
		
		result = 0;
		
		ch[N-1][0] = true;
		dfs(N-1, 0, 1);
		
		System.out.println(result);
	}
	
	static void dfs(int r, int c, int dist) {
		
		if(r == 0 && c == M - 1) {
			if(dist == K)
				result++;
			return;
		}
		
		for(int d=0; d<4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(nr < 0 || nr >= N || nc < 0 || nc >= M) {
				continue;
			}
			
			if(grid[nr][nc] != '.') {
				continue;
			}
			
			if(ch[nr][nc] != false) {
				continue;
			}
			
			ch[nr][nc] = true;
			dfs(nr, nc, dist + 1);
			ch[nr][nc] = false;
		}
	}
}
