package swea01949_0412;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * 1. 제일 높은 봉우리가 몇갠지 구해야됨. 있는 만큼 다 -> Arraylist
 * 2. 최대니까 DFS 가 좋아보임.
 */



public class Solution {
	static int N, K;
	static int[][] grid;
	static boolean[][] ch;
	static ArrayList<Pos> list;
	static int max;
	static int answer;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
//	static ArrayList<Pos> tracking; // 디버깅을 위함
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream(new File(Solution.class.getResource("").getPath() + "input.txt")));
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		
		for(int t=1; t<=T; t++) {
			N = sc.nextInt();
			K = sc.nextInt();
			grid = new int[N][N];
			ch = new boolean[N][N];
			list = new ArrayList<>();
			max = 0;
			answer = 0;
			
//			tracking = new ArrayList<>();
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					grid[i][j] = sc.nextInt();
					
					if(max > grid[i][j]) {
						continue;
					}
					
					if(max == grid[i][j]) {
						list.add(new Pos(i, j));
						continue;
					}
					
					if(grid[i][j] > max) {
						max = grid[i][j];
						list.clear();
						list.add(new Pos(i, j));
					}
				}
			}
			
			for(int i=0; i<list.size(); i++) {
				Pos cur = list.get(i);
				
				ch[cur.r][cur.c] = true;
				dfs(1, cur.r, cur.c, true);
				ch[cur.r][cur.c] = false;
			}
			
			System.out.println(String.format("#%d %d", t, answer));
		}
	}
	
	private static void dfs(int L, int r, int c, boolean posDig) {
//		tracking.add(new Pos(r, c));
		
		for(int d=0; d<4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(nr >= 0 && nr < N && nc >= 0 && nc < N && !ch[nr][nc]) {
				if(grid[nr][nc] >= grid[r][c]) {
					if(!posDig || grid[nr][nc] - grid[r][c] >= K) continue;
					
					int temp = grid[nr][nc];
					grid[nr][nc] = grid[r][c] - 1;
					ch[nr][nc] = true;
					dfs(L+1, nr, nc, false);
					ch[nr][nc] = false;
					grid[nr][nc] = temp;
					
					continue;
				}
				
				ch[nr][nc] = true;
				dfs(L+1, nr, nc, posDig);
				ch[nr][nc] = false;
			}
		}
		
		if(L > answer) {
//			System.out.println(tracking);
			answer = L;
		}
		
//		tracking.remove(L-1);
	}

	static class Pos {
		int r, c;

		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Pos [r=" + r + ", c=" + c + "]";
		}
	}
}
