import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int R, C;
	static int[][] grid;
	static int[][] _grid;
	static ArrayList<int[]> list;
	static int res;
	
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {-1, 0, 1, 0};
	
	static boolean[][] ch;
	static int size, cnt;
	
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream(new File(Main.class.getResource("").getPath() + "input.txt")));
		Scanner sc = new Scanner(System.in);

		R = sc.nextInt();
		C = sc.nextInt();
		grid = new int[R][C];
		_grid = new int[R][C];
		list = new ArrayList<>();
		res = 0;
		
		for(int r=0; r<R; r++) {
			for(int c=0; c<C; c++) {
				int v = sc.nextInt();
				grid[r][c] = v;
				
				if(grid[r][c] != 0) list.add(new int[] {r, c});
			}
		}
		
		bfs();
		System.out.println(res);
	}

	public static void bfs() throws Exception {
		Queue<int[]> q = new LinkedList<>();
		
		for(int i=list.size()-1; i>=0; i--) {
			q.offer(list.get(i));
		}
		
		if( check() ) return;
		
		while( !q.isEmpty() ) {
			int size = q.size();
			
			for(int s=0; s<size; s++) {
				int[] cur = q.poll();
				int r = cur[0];
				int c = cur[1];
				int temp = 0;
				
				for(int d=0; d<4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					if(grid[nr][nc] == 0) temp++;
				}

				_grid[r][c] = grid[r][c] - temp >= 0 ? grid[r][c] - temp : 0;
				
				if( _grid[r][c] > 0 ) {
					int[] pos = {r, c};
					q.offer(pos);
					list.add(pos);
				}
			}
			
			for(int r=0; r<R; r++) {
				grid[r] = _grid[r].clone();
			}
			res++;
			
			if( check() ) {
				break;
			}
		}
	}
	
	public static boolean check() {
		boolean flag = true;
		ch = new boolean[R][C];
		
		size = list.size();
		if (size == 0) {
			res = 0;
			return flag;
		};
		
		cnt = 0;
		int[] start = list.get(0);
		
		for(int i=list.size()-1; i>=0; i--) {
			int[] pos = list.get(i);
			ch[pos[0]][pos[1]] = true;
			list.remove(i);
		}
		
		ch[start[0]][start[1]] = false;
		dfs(start[0], start[1]);
		
		
		// 숫자가 같으면, 아직 빙산은 한 덩어리
		if(size == cnt) flag = false;
		
		return flag;
	}
	
	
	
	public static void dfs(int r, int c) {
		cnt++;
		
		for(int d=0; d<4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if( ch[nr][nc] ) {
				ch[nr][nc] = false;
				dfs(nr, nc);
			}
		}
	}
}
