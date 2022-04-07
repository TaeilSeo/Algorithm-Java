import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
	static int N;
	static int[][] grid;
	static int[][] dp;
	static boolean[][] ch;
	static int res;
	
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {-1, 0, 1, 0};
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream(new File(Solution.class.getResource("").getPath() + "input.txt")));
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		
		for(int t=1; t<=T; t++) {
			N = sc.nextInt();
			sc.nextLine();
			grid = new int[N][N];
			dp = new int[N][N];
			ch = new boolean[N][N];
			
			for(int i=0; i<N; i++) {
				String str = sc.nextLine();
				
				for(int j=0; j<N; j++) {
					grid[i][j] = str.charAt(j) - '0';
					dp[i][j] = -1;
				}
			}
			
			res = Integer.MAX_VALUE;
			
			ch[0][0] = true;
			
			bfs(0, 0);
			
			System.out.println(String.format("#%d %d", t, res));
		}
	}
	
	private static void bfs(int r, int c) {
		Queue<Pos> q = new LinkedList<>();
		q.offer(new Pos(r, c, 0));
		
		while( !q.isEmpty() ) {
			Pos cur = q.poll();
			if(cur.r == N-1 && cur.c == N-1) {
				res = Math.min(res, cur.sum);
			}
			
			if(dp[cur.r][cur.c] == -1) {
				dp[cur.r][cur.c] = cur.sum;
			}
			
			for(int d=0; d<4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				
				if(nr >= 0 && nr < N && nc >= 0 && nc < N) {
					int newSum = cur.sum + grid[nr][nc];
					
					// 간 적 없거나, 비용더 작을 때만
					if(dp[nr][nc] == -1 || dp[nr][nc] > newSum) {
						dp[nr][nc] = newSum;
						q.offer(new Pos(nr, nc, newSum));
					}
				}
			}
		}
	}
	
	static class Pos {
		int r, c, sum;

		public Pos(int r, int c, int sum) {
			super();
			this.r = r;
			this.c = c;
			this.sum = sum;
		}

		@Override
		public String toString() {
			return "Pos [r=" + r + ", c=" + c + ", sum=" + sum + "]";
		}
	}
	
	private static void printDP() {
		for(int i=0; i<N; i++) {
			System.out.println(Arrays.toString(dp[i]));
		}
	}
}
