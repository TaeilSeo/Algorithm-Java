import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] dp = new int [N+2];
		dp[2] = 3;
		for(int i=4; i<=N; i++) {
			if(N%2 == 0) {
				dp[i] += dp[i-2] * 3;
				for(int j=i-4; j>=0; j-=2) {
					dp[i] += dp[j] * 2;
				}
				dp[i] += 2;
			}
		}
		
		System.out.println(dp[N]);
	}
}
