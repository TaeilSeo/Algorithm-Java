import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

public class Solution {
	static int N;
	static int[] map, dp;
	static int idx;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream(new File(LISTest.class.getResource("").getPath() + "input.txt")));
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int t=1; t<=T; t++) {
			N = sc.nextInt();
			map = new int[N];
			dp = new int[N]; // 이분탐색으로 풀 때는, dp에 길이 수가 아니라 실제 값이 들어감
			
			idx = 0;
			
			for(int i=0; i<N; i++) map[i] = sc.nextInt();
			
			dp[0] = map[0]; // 시작값 채워주기
			
			for(int i=1; i<N; i++) {
				if(dp[idx] < map[i]) { // 현재 idx번째의 dp[idx] 값 보다 큰 수라면
					dp[++idx] = map[i]; // idx+1 번째에 삽입
				} else { // idx번째 보다 작은 수라면
					// 작은 수가 어디에 삽입 될지 이분탐색
					int newIdx = lowerBound(map[i]);
					dp[newIdx] = map[i]; 
				}
			}
			System.out.println(String.format("#%d %d", t, idx + 1));
		}
	}
	
	private static int lowerBound(int n) {
		int start = 0;
		int end = idx;
		
		while(start < end) {
			int mid = (start + end) / 2;
			if(dp[mid] >= n) { // 
				end = mid;
			} else {
				start = mid + 1;
			}
		}
		
		return end;
	}
}
