package baekjoon2573_0303;

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
	static ArrayList<int[]> list;
	static int res;
	
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {-1, 0, 1, 0};
	
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream(new File(Main.class.getResource("").getPath() + "input.txt")));
		Scanner sc = new Scanner(System.in);

		R = sc.nextInt();
		C = sc.nextInt();
		grid = new int[R][C];
		list = new ArrayList<>();
		res = 0;
		
		for(int r=0; r<R; r++) {
			for(int c=0; c<C; c++) {
				grid[r][c] = sc.nextInt();
				
				if( grid[r][c] == 0 ) {
					continue;
				}
				
				int temp = 0;
				for(int d=0; d<4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					if(grid[nr][nc] != 0) temp++;
				}
				
				if( temp != 4 ) {
					list.add(new int[] {r, c});
				}
			}
		}
		
		bfs();
		System.out.println(res);
	}

	public static void bfs() throws Exception {
		Queue<int[]> q = new LinkedList<>();
		
		for(int i=list.size()-1; i>=0; i--) {
			q.offer(list.get(i));
			list.remove(i);
		}
		
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

				grid[r][c] = grid[r][c] - temp >= 0 ? grid[r][c] - temp : 0;
				
				if(grid[r][c] > 0) {
					int[] pos = {r, c};
					q.offer(pos);
					list.add(pos);
				}
			}
			
			for(int r=0; r<R; r++) {
				System.out.println(Arrays.toString(grid[r]));
			}
			System.out.println();
			
			if( check() ) {
				break;
			}
			
			res++;
		}
	}
	
	public static boolean check() {
		boolean res = true;
		
		Queue<int[]> q = new LinkedList<>();
		boolean[][] ch = new boolean[R][C];
		
		for(int i=list.size()-1; i>=0; i--) {
			int[] pos = list.get(i);
			q.offer(pos);
			ch[pos[0]][pos[1]] = true;
			list.remove(i);
		}
		
		for(int r=0; r<R; r++) {
			System.out.println(Arrays.toString(ch[r]));
		}
		System.out.println();
		
		int size = q.size();
		
		int cnt = 0;
		
		while( !q.isEmpty() ) {
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];
			
			ch[r][c] = false;
			
			for(int d=0; d<4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if( ch[nr][nc] ) {
					cnt++;
					q.offer(new int[] {nr, nc});
				}
			}
		}
		
		for(int r=0; r<R; r++) {
			System.out.println(Arrays.toString(ch[r]));
		}
		
		System.out.println("size: " + size + ", cnt : " + cnt);
		
		// 숫자가 같으면, 아직 빙산은 한 덩어리
		if(size == cnt) res = false;
		
		return res;
	}
}
