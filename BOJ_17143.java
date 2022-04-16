import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

/*
 * 관건
 * 상어가 겹치는것을 어떻게 나타낼 것인가?
 * 상어 정보는 배열에 저장
 * 상어 이동 후, 상어 배열 돌면서 현재 확인하는 상어가 위치하는 2차원배열 조사.
 * 해당 칸에 아무도 없다면 자신의 번호를 바로 저장.
 * 저장된 정보가 있다면, 기존 친구랑 사이즈 비교해서 더 큰 친구 번호를 저장(약육강식)
 */

public class Main {

	static int R, C, M; // 2 ≤ R, C ≤ 100 | 0 ≤ M ≤ R×C
	static int[][] grid;
	static Data[] map;
	
	static int[] dr = {0, -1, 1, 0, 0};
	static int[] dc = {0, 0, 0, 1, -1};
	
	static int cnt;
	static int answer;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream(new File(Main.class.getResource("").getPath() + "input.txt")));
		Scanner sc = new Scanner(System.in);

		R = sc.nextInt();
		C = sc.nextInt();
		M = sc.nextInt();
		grid = new int[R][C];
		map = new Data[M+1];
		cnt = 0;
		answer = 0;
		
		// 처음부터 낚시터에 상어가 없다면 종료
		if(M == 0) {
			System.out.println(answer);
			return;
		}
		
		for(int i=1; i<=M; i++) {
			map[i] = new Data(i, sc.nextInt() - 1, sc.nextInt() - 1, sc.nextInt(), sc.nextInt(), sc.nextInt());
		}
		
		check();
//		print();
		
		for(int c=0; c<C; c++) {
//			System.out.println("now col: " + c);
			 // 상어 잡기
			for(int r=0; r<R; r++) {
				int curNum = grid[r][c];
				if(curNum == 0) continue;
				
//				System.out.println("fisher man at: r " + r + " c " + c);
//				System.out.println("catch " + (char)(map[curNum].n + 64));
				answer += map[curNum].z;
				cnt++;
//				System.out.println("total " + answer + "\n");
				map[curNum].n = -1; // 잡거나 죽은 상어는 n = -1로
				grid[r][c] = 0;
				
				// 남은 상어가 없으면 종료
				if(cnt == M) {
					System.out.println(answer);
					return;
				}
				
				break;
			}
			
			// 상어 이동
			move();
		}
		
		System.out.println(answer);
	}
	
	private static void move() {
		for(int i=1; i<=M; i++) {
			if(map[i].n <= 0) continue;
			
			Data data = map[i];
			int speed;
			
			// 원래 있던 칸 비우기
//			if(grid[data.r][data.c] == data.n) grid[data.r][data.c] = 0;
			grid[data.r][data.c] = 0;
			
			if(data.d >= 1 && data.d <= 2) {
				 // 종 이동 1 || 2
				speed = data.s % ( 2 * (R - 1) );
				for(int s=speed; s>0; s--) {
					if(data.r + dr[data.d] < 0 || data.r + dr[data.d] >= R) data.d = data.d == 1 ? 2 : 1;
					data.r += dr[data.d];
				}
			} else if (data.d >= 3 && data.d <= 4) {
				 // 횡 이동 3 || 4
				speed = data.s % ( 2 * (C - 1) );
				for(int s=speed; s>0; s--) {
					if(data.c + dc[data.d] < 0 || data.c + dc[data.d] >= C) data.d = data.d == 3 ? 4 : 3;
					data.c += dc[data.d];
				}
			}
		}
		
		// 이동을 마친 후, 약육강식
//		System.out.println("after moving");
//		print();
		check();
//		System.out.println("after checking");
//		print();
	}
	
	private static void check() {
		for(int i=1; i<=M; i++) {
			Data newData = map[i];
			if(map[i].n == -1) continue; // 잡히거나, 잡아먹힌 물고기는 pass
			
			int r = newData.r;
			int c = newData.c;
			int curNum = grid[r][c];
			Data originData = map[curNum];
			
			// 해당 칸에 어떠한 상어도 없는 경우, 해당 칸에 상어 번호 저장
			if(curNum == 0) {
				grid[r][c] = newData.n;
				continue;
			}
			
			// 먼저 선점한 상어가 있는 경우, 해당 상어와 약육강식.
			if(originData.z < newData.z) {// 뒤늦게 온 상어가 더 큼
				// 원래 있던 상어 kill
				originData.n = -1;
				
				// 해당 칸에 새로운 상어 번호 저장
				grid[r][c] = newData.n;
				
			} else { // 원래 있던 상어가 더 큼
				// 새로운 상어 kill
				newData.n = -1;
			}
		}
	}
	
	private static void print() {
		for(int i=1; i<=M; i++) {
			if(map[i].n != -1) System.out.println((char)(i + 64)+ " " + map[i]);
		}
		System.out.println();
	}
	
	public static class Data {
		int n;
		int r, c;
		int s; // 속력  0 ≤ s ≤ 1000
		int d; // 방향 1 위, 2  아래, 3  오른쪽, 4  왼쪽
		int z; // 크기
		
		public Data(int n, int r, int c, int s, int d, int z) {
			super();
			this.n = n;
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}
		
		@Override
		public String toString() {
			return "Data [n=" + n + ", r=" + r + ", c=" + c + ", s=" + s + ", d=" + d + ", z=" + z + "]";
		}
	}
}
