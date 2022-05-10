import java.util.Scanner;

public class Main {
	
	static int N; // N×N(N=2k, k는 1 이상 7 이하의 자연수)
	static int[][] grid;
	
	static final int WHITE = 0;
	static final int BLUE = 1;
	
	static int white;
	static int blue;

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		grid = new int[N][N];
		
		white = 0;
		blue = 0;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				grid[i][j] = sc.nextInt();
			}
		}
		
		dfs(0, 0, N);
		
		
		System.out.println(white);
		System.out.println(blue);
	}

	static private void dfs(int r, int c, int len) {
		// 범위 안의 값이 모두 0이거나 1이어야 한다.
		int color = grid[r][c]; // 기준점 색깔. 범위안의 다른 좌표여도 상관없다.
		boolean flag = false; // len/2 범위로 다시 탐색할지 여부를 판단하는 flag. 범위 안의 색이 모두 같으면 false.
		
		for(int i=r; i<r+len; i++) {
			for(int j=c; j<c+len; j++) {
				if(color != grid[i][j]) { // 기준 색과 현재의 색이 같지 않다면 종료. 전체를 탐색할 필요가 없다.
					flag = true; // len/2 범위 다시 탐색
					break; // 종료
				}
			}
		}
		
		// 조건을 만족하지 못한다면 나눠진다
		if(flag) { // 4범위로 나누어 탐색
			dfs(r, c, len/2);
			dfs(r, c + len/2, len/2);
			dfs(r + len/2, c, len/2);
			dfs(r + len/2, c + len/2, len/2);
		} else { // 모두 색이 같다면, 해당 색의 수 증가
			if(color == WHITE) white++;
			else blue++;
		}
	}
}
