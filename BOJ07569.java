import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 토마토

public class Main {
	static int M, N, H;
	static int[][][] grid;
	static boolean[][][] ch;
	static ArrayList<Pos> list;
	static int cnt, day;
	
	static int[] dh = {0, 0, 0, 0, 1, -1};
	static int[] dr = {0, -1, 0, 1, 0, 0};
	static int[] dc = {-1, 0 , 1, 0, 0, 0};
	
	public static void main(String[] args) throws Exception {
		String path = Main.class.getResource("").getPath();
		File fileInSamePackage = new File(path + "input.txt");
		System.setIn(new FileInputStream(fileInSamePackage));

		Scanner sc = new Scanner(System.in);
		
		M = sc.nextInt();
		N = sc.nextInt();
		H = sc.nextInt();
		
		grid = new int[H][N][M];
		
		cnt = 0;
		day = 0;
		list = new ArrayList<>();
		
		for(int h=0; h<H; h++) {
			for(int n=0; n<N; n++) {
				for(int m=0; m<M; m++) {
					grid[h][n][m] = sc.nextInt();
					
					if(grid[h][n][m] == 0) cnt++;
					else if(grid[h][n][m] == 1) list.add(new Pos(h, n, m));
				}
			}
		}
		
		bfs();
		
		if(cnt == 0) System.out.println(day);
		else System.out.println(-1);
	}
	
	public static void bfs() {
		Queue<Pos> q = new LinkedList<>();
		
		for(int i=0; i<list.size(); i++) {
			q.offer(list.get(i));
		}
		
		while( !q.isEmpty() ) {
			if(cnt == 0) break;
			
			int size = q.size();
			
			for(int s=0; s<size; s++) {
				Pos cur = q.poll();
				
				for(int d=0; d<6; d++) {
					int nh = cur.h + dh[d];
					int nr = cur.r + dr[d];
					int nc = cur.c + dc[d];
					
					if(nh>=0 && nh<H && nr>=0 && nr<N && nc>=0 && nc<M && grid[nh][nr][nc] == 0) {
						grid[nh][nr][nc] = 1;
						cnt--;
						q.offer(new Pos(nh, nr, nc));
					}
				}
			}
			day++;
		}
	}
	
	static class Pos{
		int h, r, c;

		public Pos(int h, int r, int c) {
			super();
			this.h = h;
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Pos [h=" + h + ", r=" + r + ", c=" + c + "]";
		}
	}
}
