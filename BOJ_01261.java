import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/*
 * 첫째 줄에 알고스팟 운영진이 (N, M)으로 이동하기 위해 벽을 최소 몇 개 부수어야 하는지 출력한다.
 * dfs?
 * 1. 빠르게 가는 것이 아니라, 벽을 최소한으로 부수는 것이 목표
 * 2. 좀 돌아가도 상관 없단 말
 * 3. 4방 탐색으로 이동가능한 곳 돌면서, 한칸 부쉈을때 이어질 수 있는 스팟을 찾기?
 * 4. 굳이 맵을 부쉈다고 표시할 필요 없이, 대각선으로 이동 가능하면, 그게 1칸 부순거랑 같으니까 대각선 이동?
 * 
 * bfs였다.
 * PQ를 사용해서 정렬 기준을 벽을 뚫은 횟수로 두고, 오름차순으로 두면 해결!
 */

public class Main {
	static int N, M; // 가로, 세로. 1 ≤ N, M ≤ 100
	static int[][] grid;
	static boolean[][] ch;
	static Pos START, END;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static int answer;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream(new File(Main.class.getResource("").getPath() + "input.txt")));
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		sc.nextLine();
		grid = new int[M][N];
		ch = new boolean[M][N];
		START = new Pos(0, 0, 0);
		END = new Pos(M - 1, N - 1, 0);
		
		for(int m=0; m<M; m++) {
			String str = sc.nextLine();
			
			for(int n=0; n<N; n++) {
				grid[m][n] = str.charAt(n) - '0';
			}
		}
		
		answer = Integer.MAX_VALUE;
		
		ch[START.r][START.c] = true;
		bfs();
		
		System.out.println(answer);
	}
	
	private static void bfs() {
		PriorityQueue<Pos> q = new PriorityQueue<>(new Comparator<Pos>() {
			@Override
			public int compare(Pos o1, Pos o2) {
				return Integer.compare(o1.cnt, o2.cnt);
			}
		});
		
		q.offer(new Pos(START.r, START.c, 0));
		
		while( !q.isEmpty() ) {
			Pos cur= q.poll();
			
			if(cur.r == END.r && cur.c == END.c) {
				answer = cur.cnt;
			}
			
			for(int d=0; d<4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				
				if(nr >=0 && nr < M && nc >= 0 && nc < N && !ch[nr][nc]) {
					if(grid[nr][nc] == 0) {
						ch[nr][nc] = true;
						q.offer(new Pos(nr, nc, cur.cnt));
					} else if(grid[nr][nc] == 1) {
						ch[nr][nc] = true;
						q.offer(new Pos(nr, nc, cur.cnt + 1));
					}
				}
			}
		}
	}
	
	static class Pos {
		int r, c;
		int cnt;
		
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
