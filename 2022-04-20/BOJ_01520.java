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
	static int[][] dp;
	static long res;
	
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {-1, 0, 1, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		grid = new int[R+2][C+2]; // 산의 높이
		dp = new int[R+2][C+2]; // 경우의 수를 저장할 배열
		res = 0;
		
		
		for(int r=1; r<=R; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int c=1; c<=C; c++) {
				grid[r][c] = Integer.parseInt(st.nextToken());
				dp[r][c] = -1; // -1로 초기화. 지나간 유무 판단을 위해서
			}
		}
		
		int startR = 1, startC = 1;
		res = dfs(startR, startC);
		
		System.out.println(res);
	}

	public static long dfs(int r, int c) {
		if( r == R && c == C ) { // 도착점에 도착했다면 1을 반환
			return 1;
		}
		
		if(dp[r][c] != -1) { // 해당 좌표를 이미 거쳐갔었다면, 또 갈 필요가 없다.
			return dp[r][c];
		}
		
		dp[r][c] = 0; // 처음 순회하는 곳이므로, 0으로 초기화
		int nr, nc;
		for(int d=0; d<4; d++) {
			nr = r + dr[d];
			nc = c + dc[d];
			
			if(grid[nr][nc] != 0 && grid[r][c] > grid[nr][nc]) { // 다음 좌표의 높이가 0이 아니고, 현재보다 높이가 낮다면
				dp[r][c] += dfs(nr, nc);
			}
		}
		
		return dp[r][c];
	}
}
