import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	static int N, M; // 과목 수, 선수 조건 수
	static List[] map; // 인접리스트 저장할 List 배열
	static int[] dp; // 선수 과목 수를 저장할 dp 배열
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		
		map = new List[N+1];
		dp = new int[N+1];
		
		for(int i=0; i<=N; i++) {
			map[i] = new ArrayList<Integer>();
			dp[i] = 1; // 선수 과목이 없는 과목 이수에도 최소 1학기가 필요함
		}
		
		for(int i=0; i<M; i++) {
			int pre = sc.nextInt();
			int post = sc.nextInt();
			
			map[post].add(pre); // 선수 과목 수를 알고싶은 과목번호를 시작으로 탐색하기 위하여 post를 index로 설정
		}
		
		StringBuilder sb = new StringBuilder(); // 출력 값 저장할 StringBuilder
		
		for(int i=1; i<=N; i++) {
			dp[i] = dfs(i); // 현재 과목의 선수 과목이 몇개인지 dfs 탐색
			sb.append(dp[i] + " "); // 출력 값에  append
		}
		
		sb.deleteCharAt(sb.length() - 1); // 마지막 공백 삭제
		System.out.println(sb.toString()); // 정답 출력
	}
	
	private static int dfs(int cur) {
		// 현재 과목이 이미 선수 과목 조사를 마쳤다면 return
		if(dp[cur] != 1) {
			return dp[cur];
		}
		
		// 선수 과목 탐색
		for(int i=0; i<map[cur].size(); i++) {
			// 현재 과목의 선수 과목 수는, dfs 탐색으로 얻은 값들 중 가장 큰 값
			// 따라서 현재 저장된 선수 과목 수와, 탐색을 끝마친 새로운 선수과목 수를 비교하여 더 큰 값 + 1 을 dp 배열에 저장
			// 예] 문제에서 주어진 보기의 경우 5번 과목의 선수과목 탐색은
			// 4 <- 5		총 2학기 
			// 1 <- 2 <- 5	총 3학기
			// 두갈래로 나뉘므로 더 큰 값인 3(2 + 1)학기가 5번 과목을 이수하기 위해 필요한 총 학기이다.   
			dp[cur] =  Integer.max(dp[cur], dfs((int)map[cur].get(i)) + 1);
		}
		
		return dp[cur]; // 현재 과목의 선수과목 수 반환
	}
}
