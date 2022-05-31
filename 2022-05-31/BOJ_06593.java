import java.io.File;
import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	
	static int L;
	static int R, C;
	
	static char[][][] grid;
	static boolean[][][] ch;
	
	static int[] dz = {0, 0, 0, 0, 1, -1};
	static int[] dy = {0, 0, -1, 1, 0, 0};
	static int[] dx = {-1, 1, 0, 0, 0, 0};
	
	static Pos S, E;
	
	
	static int answer;

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			L = sc.nextInt();
			R = sc.nextInt();
			C = sc.nextInt();
			
			if(L == 0 && R == 0 && C == 0) break;
			
			grid = new char[L][R][C];  // 건물 전체 지형
			ch = new boolean[L][R][C]; // 방문 여부 
			answer = 0; // 걸린 시간
			
			sc.nextLine();
			
			for(int l=0; l<L; l++) {
				
				for(int r=0; r<R; r++) {
					String str = sc.nextLine();
					
					for(int c=0; c<C; c++) {
						grid[l][r][c] = str.charAt(c);
						
						if(grid[l][r][c] == 'S') S = new Pos(l, r, c, 0); // 시작좌표 저장
						if(grid[l][r][c] == 'E') E = new Pos(l, r, c, 0); // 도착좌표 저장
					}
				}
				
				sc.nextLine();
			}
			
			
			// 탐색 시작
			answer = bfs();
			
			if(answer != 0) System.out.println(String.format("Escaped in %d minute(s).", answer));
			else System.out.println("Trapped!");
		}
	}
	
	private static int bfs() {
		int res = 0;
		
		Queue<Pos> q = new LinkedList<Pos>();
		q.offer(S); // 시작좌표 enqueue
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			
			if(cur.x == E.x && cur.y == E.y && cur.z == E.z) { // 현재 좌표가 종료지점이면
				res = cur.t; // 걸린 시간 반환하고 종료
				break;
			}
			
			for(int d=0; d<6; d++) {
				int nz = cur.z + dz[d]; // 후보 좌표의 z 값
				int ny = cur.y + dy[d]; // 후보 좌표의 y 값
				int nx = cur.x + dx[d]; // 후보 좌표의 x 값
				
				if(nz < 0 || nz >= L) continue; // 범위 벗어나면 pass
				if(ny < 0 || ny >= R) continue; // 범위 벗어나면 pass
				if(nx < 0 || nx >= C) continue; // 범위 벗어나면 pass
				
				if(ch[nz][ny][nx]) continue; // 방문한 곳이면 pass
				
				if(grid[nz][ny][nx] == '#') continue; // 갈 수 없는 곳이면 pass
				
				ch[nz][ny][nx] = true; // 방문체크
				
				q.offer(new Pos(nz, ny, nx, cur.t + 1)); // 다음 좌표 enqueue
			}
		}
		
		return res;
	}

	public static class Pos {
		int z, y, x;
		int t;
		public Pos(int z, int y, int x, int dist) {
			super();
			this.z = z;
			this.y = y;
			this.x = x;
			this.t = dist;
		}
		@Override
		public String toString() {
			return "Pos [z=" + z + ", y=" + y + ", x=" + x + ", t=" + t + "]";
		}
	}
}
