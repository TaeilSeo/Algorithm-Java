import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	static int R, C;
	static int[][] grid;
	static int res;
	
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {-1, 0, 1, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		grid = new int[R+2][C+2];
		res = 0;
		
		for(int r=1; r<=R; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int c=1; c<=C; c++) {
				grid[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		int startR = 1, startC = 1;
		dfs(grid[startR][startC], startR, startC);
		
		System.out.println(res);
	}

	public static void dfs(int V, int r, int c) {
		if( r == R && c == C ) {
			res++;
			return;
		}
		
		int nr, nc;
		for(int d=0; d<4; d++) {
			nr = r + dr[d];
			nc = c + dc[d];
			
			if( grid[nr][nc] > 0 && grid[nr][nc] < V ) {
				dfs(grid[nr][nc], nr, nc);
			}
		}
	}
}
