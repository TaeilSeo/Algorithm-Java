import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

/*
 * 원래 TSP 알고리즘이 있다는데,
 * 그것도 DFS + DP + BitMasking
 * 지금의 형태는 DFS로 순열임
 * 여기에 어떻게  DP를 적용시킬 수 있을까?
 * 
 * 메모이제이션
 * DP 배열 초기화 = new dp[탐색 가짓수][최댓값]
 * DFS순회
 * 1) 탐색 완료
 * 2) 이미 탐색
 * 3) 탐색 - 해당 부분에서 차이가 조금씩 나는 것으로 보임
 * 
 * 내릭막길 문제와 구슬의 무게 측정여부 판가름 하기 문제와 거의 흡사
 */

public class Main {
	static int N;
	static int[][] grid;
	static int[][] dp;
	
	static final int INF = 1_000_000 * 16; // 순회시 가능한 최대 값
	
	static int cnt; 	// 시복잡도 체크
	static int answer;	// 정답 
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in); // 표준 입력

		N = sc.nextInt();
		grid = new int[N][N];
		dp = new int[N][(1 << N) - 1];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				grid[i][j] = sc.nextInt();
			}
			Arrays.fill(dp[i], INF);
		}
		
		answer = dfs(0, 1);

		System.out.println(answer);
	}

	private static int dfs(int city, int ch) {
		// 모두 방문한 경우
		if(ch == (1 << N) -1) { // N = 4의 경우, 1111(2) = 2^4 - 1 
			if(grid[city][0] == 0) return INF; // 모든 도시 탐색후 시작점으로 돌아갈수 없다면
			return grid[city][0]; // 시작점으로 돌아갈 수 있다면
		}
		
		// 이미 방문한 경우
		if(dp[city][ch] != INF) {
			return dp[city][ch]; // 현재도시에서 원점까지 소요되는 비용 반환
		}
		
		// 탐색
		for(int i=0; i<N; i++) {
			if( grid[city][i] != 0 && (ch & 1 << i) == 0 ) { // 방문할 수 있고(can), 방문하지 않은 곳이라면(don't)
				// 이 라인이 아직 이해가 안됨.
				dp[city][ch] = Integer.min(dp[city][ch], dfs(i, ch | (1 << i)) + grid[city][i]);
			}
		}
		
		return dp[city][ch];
	}
}
