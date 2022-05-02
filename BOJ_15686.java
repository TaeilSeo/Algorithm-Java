package baekjoon15686_0502;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

/*
사이의 거리는 |r1-r2| + |c1-c2|
치킨 거리는 집과 가장 가까운 치킨집 사이의 거리
도시의 치킨 거리는 모든 집의 치킨 거리의 합

0 빈칸
1 집
2 치킨집

폐업시키지 않을 치킨집을 최대 M개를 골랐을 때, 도시의 치킨 거리의 최솟값을 출력
*/

public class Main {
	static int N, M;
	static int[][] grid;
	
	static int[] dr = {};
	static int[] dc = {};
	
	static int answer;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream(new File(Main.class.getResource("").getPath() + "input.txt")));
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		grid = new int[N+2][N+2];
		answer = 0;
		
		ArrayList<Data> list = new ArrayList<>(); 
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				grid[i][j] = sc.nextInt();
				if(grid[i][j] == 1) list.add(new Data(i, j));
			}
		}
		
		
		
	}

	private static int bfs(int r, int c) {
		int dist = 0;
		
		
		
		return dist;
	}
	
	public static class Data {
		int r, c;

		public Data(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Data [r=" + r + ", c=" + c + "]";
		}
	}
}
