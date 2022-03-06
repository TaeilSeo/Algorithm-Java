import java.io.File;
import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N, cnt1, cnt2;
	static char[][] grid;
	static boolean[][] ch;
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {-1, 0, 1, 0};
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream(new File(Main.class.getResource("").getPath() + "input.txt")));
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		grid = new char[N][N];
		ch = new boolean[N][N];
		cnt1 = 0;
		cnt2 = 0;
		
		for(int i=0; i<N; i++) {
			grid[i] = sc.next().toCharArray();
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if (!ch[i][j]) {
					bfs1(i, j, grid[i][j]);
					cnt1++;
				}
			}
		}
		
		ch = new boolean[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if (!ch[i][j]) {
					bfs2(i, j, grid[i][j]);
					cnt2++;
				}
			}
		}
		
		System.out.println(cnt1 + " " + cnt2);
	}

	public static void bfs1(int r, int c, char color) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] {r, c});
		
		while( !q.isEmpty() ) {
			int size = q.size();
			
			for(int s=0; s<size; s++) {
				int[] cur = q.poll();
				
				for(int d=0; d<4; d++) {
					int nr = cur[0] + dr[d];
					int nc = cur[1] + dc[d];
					
					if(nr >= 0 && nr < N && nc >= 0 && nc < N && grid[nr][nc] == color && !ch[nr][nc]) {
						ch[nr][nc] = true;
						q.offer(new int[] {nr, nc});
					}
				}
			}
		}
	}
	
	public static void bfs2(int r, int c, char color) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] {r, c});
		
		while( !q.isEmpty() ) {
			int size = q.size();
			
			for(int s=0; s<size; s++) {
				int[] cur = q.poll();
				
				for(int d=0; d<4; d++) {
					int nr = cur[0] + dr[d];
					int nc = cur[1] + dc[d];
					
					if(color == 'B') {
						if(nr >= 0 && nr < N && nc >= 0 && nc < N && grid[nr][nc] == color && !ch[nr][nc]) {
							ch[nr][nc] = true;
							q.offer(new int[] {nr, nc});
						}
					} else {
						if(nr >= 0 && nr < N && nc >= 0 && nc < N && grid[nr][nc] != 'B' && !ch[nr][nc]) {
							ch[nr][nc] = true;
							q.offer(new int[] {nr, nc});
						}
					}

				}
			}
		}
	}
}
