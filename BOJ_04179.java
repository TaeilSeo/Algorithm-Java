import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int R, C;
	static char[][] grid;
	static boolean[][] ch;
	static Pos J;
	static ArrayList<Pos> F;
	static int cnt;
	
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {-1, 0, 1, 0};
	
	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream(new File(Main.class.getResource("").getPath() + "input.txt")));
		Scanner sc = new Scanner(System.in);

		R = sc.nextInt();
		C = sc.nextInt();
		sc.nextLine();
		grid = new char[R][C];
		ch = new boolean[R][C];
		F = new ArrayList<>();
		
		for(int r=0; r<R; r++) {
			grid[r] = sc.nextLine().toCharArray();
			for(int c=0; c<C; c++) {
				if(grid[r][c] == 'J') J = new Pos('J', r, c);
				if(grid[r][c] == 'F') F.add(new Pos('F', r, c));
			}
		}
		cnt = 0;
		
		// # 벽
		// . 통로
		// J 지훈이 초기 위치
		// F 발화 장소
		
		boolean res = bfs();
		
		if(res) {
			System.out.println(cnt);
		} else {
			System.out.println("IMPOSSIBLE");
		}
		// IMPOSSIBLE 탈출할 수 없는 경우 출력
	}
	
	private static boolean bfs() {
		Queue<Pos> q = new LinkedList<Pos>();
		
		for(int i=0; i<F.size(); i++) {
			q.offer(F.get(i)); // 불
		}
		q.offer(J); // 지훈이
		
		while( !q.isEmpty() ) {
			int size = q.size();
			
			cnt++;
			for(int s=0; s<size; s++) {
				Pos cur = q.poll();
				
				if(cur.type == 'J') {
					ch[cur.r][cur.c] = true;
					
					if(cur.r == 0 || cur.r == R-1 || cur.c == 0 || cur.c == C-1) {
						return true;
					}
				}
				
				for(int d=0; d<4; d++) {
					int nr = cur.r + dr[d];
					int nc = cur.c + dc[d];
					
					if(nr >= 0 && nr < R && nc >= 0 && nc < C) {
						if(cur.type == 'F' && grid[nr][nc] != '#' && grid[nr][nc] != 'F') { // 벽이랑 불 아니면 됨
							// 불이 번짐
//							System.out.println("fire!");
							grid[nr][nc] = 'F';
//							print();
							q.add(new Pos('F', nr, nc));
						} 
						
						if(cur.type == 'J' &&  grid[nr][nc] == '.' && !ch[nr][nc]) { // 오직 통로만
							// 지훈이 이동
//							System.out.println("jihoon moving! cnt: " + cnt);
							if(grid[cur.r][cur.c] == 'J') grid[cur.r][cur.c] = '.';
							grid[nr][nc] = 'J';
//							print();
							
							q.add(new Pos('J', nr, nc));
						}
					}
				}
			}
		}
		return false;
	}
	
	private static void print() {
		for(int i=0; i<R; i++) {
			System.out.println(Arrays.toString(grid[i]));
		}
		System.out.println();
	}

	static class Pos {
		char type;
		int r, c;
		
		
		public Pos(char type, int r, int c) {
			super();
			this.type = type;
			this.r = r;
			this.c = c;
		}
		
		@Override
		public String toString() {
			return "Pos [r=" + r + ", c=" + c + ", type=" + type + "]";
		}
	}
}
