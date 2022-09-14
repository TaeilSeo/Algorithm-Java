import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N, M;
	static int[][] grid;
	static boolean[][] ch;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	
	static int result;
	
	public static void main(String[] args) throws FileNotFoundException{
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		sc.nextLine();
		
		grid = new int[N][M];
		ch = new boolean[N][M];
		
		for(int n=0; n<N; n++) {
			String input = sc.nextLine();
			
			for(int m=0; m<M; m++) {
				grid[n][m] = input.charAt(m) - '0';
			}
		}
		
		bfs();
		
		System.out.println(result);
	}
	
	static void bfs() {
		Queue<Pos> q = new LinkedList<>();
		q.offer(new Pos(0, 0, 0));
		ch[0][0] = true;
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			
			if(cur.r == N-1 && cur.c == M-1) {
				cur.cnt++;
				result = cur.cnt;
				break;
			}				
			
			for(int d=0; d<4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				
				if(nr >= 0 && nr < N && nc >= 0 && nc < M && !ch[nr][nc] && grid[nr][nc] == 1) {
					q.offer(new Pos(nr, nc, cur.cnt+1));
					ch[nr][nc] = true;
				}
			}	
		}
	}
	
	static class Pos {
		int r, c, cnt;

		public Pos(int r, int c, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}

		@Override
		public String toString() {
			return "Pos [r=" + r + ", c=" + c + ", cnt=" + cnt + "]";
		}
	}
}
