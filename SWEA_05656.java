import java.io.File;
import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

class Pos {
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

public class Solution {
	static int N, W, H;
	static int[][] input;
	static int[][] grid;
	static int res;
	static int remain;
	
	static int[] dr = {0, -1, 0 , 1};
	static int[] dc = {-1, 0, 1 , 0};
	
	static int[] map;
	
	static boolean valid;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream(new File(Solution.class.getResource("").getPath() + "input.txt")));
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int t=1; t<=T; t++) {
			N = sc.nextInt();
			W = sc.nextInt();
			H = sc.nextInt();
			input = new int[H][W];
			grid = new int[H][W];
			res = Integer.MAX_VALUE;
			remain = 0;
			
			map = new int[N];
			
			valid = true;
			
			// 입력
			for(int h=0; h<H; h++) {
				for(int w=0; w<W; w++) {
					int v = sc.nextInt();
					input[h][w] = v;
					if(v != 0) remain++;
				}
			}
			
			// 중복순열로 시뮬레이션
			perm(0);
			System.out.println(String.format("#%d %d", t, res));
		}
	}
	
	private static void perm(int L) {
		if(!valid) return;
		
		if(L == N) {
			
			// 배열 복사
			for(int i=0; i<H; i++) {
				grid[i] = input[i].clone();
			}
			// 벽돌 깨기
			for(int i=0; i<N; i++) {
				breakBlock(map[i]);
				if(remain == 0) {
					valid = false;
				}
			}
			// 결과값 비교
			res = Integer.min(res, remain);
			
			return;
		}
		
		for(int i=0; i<W; i++) {
			map[L] = i;
			perm(L+1);
		}
	}
	
	private static int findBlock(int c, int n) {
		int result = -1;
		
		for(int r=0; r<H; r++) {
			if(grid[r][c] != 0) {
				result = r + n - 1; // 꼭대기로부터 n번째 블록의 r값
				break;
			}
		}
		
		return result;
	}

	private static void breakBlock(int x) {
		Queue<Pos> pq = new LinkedList<>();
		
		int y = findBlock(x, 1);
		if(y == -1) return;
		
		pq.add(new Pos(y, x));
		
		while( !pq.isEmpty() ) {
			Pos cur = pq.poll();
			int r = cur.r;
			int c = cur.c;
			
			int range =  grid[r][c] - 1; // 깨지는 범위
			grid[r][c] = 0; // 벽돌깨기
			
			if(range == 0) continue; // 0 이면 pass
			
			for(int d=0; d<4; d++) {
				int nr = r;
				int nc = c;
				
				// 깨지는 범위만큼 반복
				for(int i=0; i<range; i++) {
					nr += dr[d];
					nc += dc[d];
					if(nr >= 0 && nr < H && nc >= 0 && nc < W && grid[nr][nc] != 0) {
						Pos newPos = new Pos(nr, nc);
						pq.add(newPos);
					}
				}
			}
		}
		
		adjustBlocks();
	}
	
	private static void adjustBlocks() {
		int cnt = 0;
		for(int c=0; c<W; c++) {
			Stack<Integer> stack = new Stack<>();
			for(int r=0; r<H; r++) {
				if(grid[r][c] != 0) stack.push(grid[r][c]);
			}
			
			
			int size = stack.size();
			cnt += size;
			
			for(int r=H-1; r>H-1-size; r--) {
				grid[r][c] = stack.pop();
			}
			
			for(int r=H-1-size; r>=0; r--) {
				grid[r][c] = 0;
			}
		}
		
		remain = cnt;
	}
}
