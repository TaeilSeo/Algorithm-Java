import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int N;
	static int[][] grid;
	static int[][] dist;
	
	static int cnt; 	// 시복잡도 체크
	static int answer;	// 정답 
	
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in); // 표준 입력

		N = sc.nextInt();
		grid = new int[N][N];
		dist = new int[N][N];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				grid[i][j] = sc.nextInt();
			}
		}
		
		answer = Integer.MAX_VALUE;
		
		dfs(0, 0, 0, 1);

//		System.out.println("cnt: " + cnt);
		System.out.println(answer);
	}

	private static void dfs(int L, int D, int sum, int ch) {
//		cnt++;
//		System.out.println("L: " + L + " D: " + D + " sum: " + sum + " ch: " + ch);
		
		if(L == N) {
			answer = Integer.min(answer, sum);
			return;
		}
		
		if(L == N - 1) {
			// 0번째에 해당하는 방문체크를 해제. 2^N - 2와 and 연산
			ch = ch & ( (2 << N) - 2 );
		}
		
		for(int i=0; i<N; i++) {
			if( grid[D][i] != 0 && (ch & 1 << i) == 0 ) { // 방문하지 않은 곳이고, 갈 수 있다면
				dfs(L+1, i, sum + grid[D][i], ch | 1 << i);
			}
		}
	}
}
