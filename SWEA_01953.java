import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
	static int R, C, Y, X, L;
	static int[][] grid;
	static boolean[][] ch;
	static int res;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream(new File(Solution.class.getResource("").getPath() + "input.txt")));
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		
		for(int t=1; t<=T; t++) {
			R = sc.nextInt();
			C = sc.nextInt();
			Y = sc.nextInt(); // 맨홀의 Y 좌표
			X = sc.nextInt(); // 맨홀의 X 좌표
			L = sc.nextInt(); // 탈출 후 소요된 시간
			grid = new int[R][C];
			ch = new boolean[R][C];
			res = 0;
			
			for(int r=0; r<R; r++) {
				for(int c=0; c<C; c++) {
					grid[r][c] = sc.nextInt();
				}
			}
			
			ch[Y][X] = true;
			bfs(Y, X);
			
//			printCH();
			
			System.out.println(String.format("#%d %d", t, res));
		}
	}
	
	private static void bfs(int r, int c) {
		Queue<Pos> q = new LinkedList<>();
		q.add(new Pos(r, c));
		int cnt = 0;
		
		while( !q.isEmpty() ) {
			if(cnt == L) break;
			
			
			int size = q.size();
			
			for(int s=0; s<size; s++) {
				Pos cur = q.poll();
//				System.out.println("r: " + cur.r + ", c: " + cur.c);
				res++;
				ArrayList<Integer> list = getValidPath(grid[cur.r][cur.c]);
				
				for(int i=0; i<list.size(); i++) {
					int d = list.get(i);
					
					int nr = cur.r + dr[d];
					int nc = cur.c + dc[d];

					// 해당 칸이 파이프라고 해서 모두 이동할 수 있는 것은 아님
					// 이어지는 모양이어야지 가능
					if(nr >= 0 && nr < R && nc >= 0 && nc < C && !ch[nr][nc] && grid[nr][nc] != 0) {
						int pair = d % 2 == 0 ? d+1 : d-1; // 현재 진행하려는 방향과 상응되는 짝이 있어야만 이동가능
						if(!getValidPath(grid[nr][nc]).contains(pair)) continue;
						
						ch[nr][nc] = true;
						q.offer(new Pos(nr, nc));
					}
				}
			}
//			System.out.println();
			cnt++;
		}
	}

	private static ArrayList<Integer> getValidPath(int N) {
		ArrayList<Integer> list = new ArrayList<>(); 
		switch (N) {
		case 1:
			list.add(0);
			list.add(1);
			list.add(2);
			list.add(3);
			break;
		case 2:
			list.add(0);
			list.add(1);
			break;
		case 3:
			list.add(2);
			list.add(3);
			break;
		case 4:
			list.add(0);
			list.add(3);
			break;
		case 5:
			list.add(1);
			list.add(3);
			break;
		case 6:
			list.add(1);
			list.add(2);
			break;
		case 7:
			list.add(0);
			list.add(2);
			break;			
		}
		return list;
	}
	
	private static void printCH() {
		for(int i=0; i<R; i++) {
			System.out.println(Arrays.toString(ch[i]));
		}
		System.out.println();
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
