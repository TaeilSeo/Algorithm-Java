import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
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
	static boolean[][] ch;
	
	static int[] dr = {0, 0, 1, -1};
	static int[] dc = {1, -1, 0, 0};
	
	static ArrayList<Data> home;
	static ArrayList<Data> chicken;
	
	static int answer;
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		grid = new int[N+2][N+2];
		answer = Integer.MAX_VALUE;
		
		home = new ArrayList<>();
		chicken = new ArrayList<>();
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				grid[i][j] = sc.nextInt();
				if(grid[i][j] == 1) home.add(new Data(i, j));
				if(grid[i][j] == 2) chicken.add(new Data(i, j));
			}
		}
		
		// 치킨집을 최대 M개 선택할 때, 도시의 치킨거리 최솟값
		for(int i=1; i<=M; i++) {
			Data[] arr = new Data[i];
			comb(0, 1, chicken.size(), i, arr);
		}
		
		System.out.println(answer);
	}
	
	// 선택 가능한 치킨집 수 
	private static void comb(int L, int start, int n, int r, Data[] arr) {
		if(L == r) {
			getChickDist(arr);
			return;
		}
		
		for(int i=start; i<=n; i++) {
			arr[L] = chicken.get(i-1);
			comb(L+1, i+1, n, r, arr);
		}
	}

	private static void getChickDist(Data[] arr) {
		int sum = 0;
		
		for(int i=0; i<home.size(); i++) {
			int minDist = Integer.MAX_VALUE;
			
			for(int j=0; j<arr.length; j++) {
				minDist = Integer.min(minDist, getDist(home.get(i), arr[j]));
			}
			
			sum += minDist;
		}
		
		answer = Integer.min(answer, sum);
	}
	
	private static int getDist(Data pos1, Data pos2) {
		return Math.abs(pos1.r - pos2.r) + Math.abs(pos1.c - pos2.c);
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
