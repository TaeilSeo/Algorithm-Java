import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.stream.IntStream;

/*
2	추의 개수						30 이하
1 4	추의 무게들(가벼운 것부터. 중복가능)	500g이하
2	구슬들의 개수					7이하
3 2	구슬들의 무게					40,000보다 작거나 같은 자연수

[제약사항]
시간제한 1초
메모리 제한 128MB
*/

public class Main {
	static int N, M; // 추 / 구슬의 개수
	static int[] mapN, mapM; // 추 / 구슬 의 무게 저장
	static int MAX;
	
	static boolean[][] dp; // dp 저장할 배열
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in); // 표준 입력
		
		N = sc.nextInt(); 			// 추 개수 읽어오기
		mapN = new int[N+1];			// 추의 개수 만큼 배열 길이 할당
		for(int i=1; i<=N; i++) {	// 추의 개수 만큼 반복
			mapN[i] = sc.nextInt();	// 추 무게 저장
		}
		
		MAX = IntStream.of(mapN).sum(); // 측정할 수 있는 무게의 최대치
		
		M = sc.nextInt();			// 구슬 개수 읽어오기
		mapM = new int[M];			// 구슬의 개수 만큼 배열 길이 할당
		for(int i=0; i<M; i++) {	// 구슬의 개수 만큼 반복
			mapM[i] = sc.nextInt();	// 구슬 무게 저장
		}
		
		dp = new boolean[31][MAX+1];
		
		dfs(0, 0);
		
		StringBuilder sb = new StringBuilder(); // 결과값을 저장할 StringBuilder 선언 및 할당
		
		for(int i=0; i<M; i++) { // 측정 가능 여부를 검사할 구슬의 수 만큼 반복
			int W = mapM[i]; // 현재 측정할 구슬의 무게
			if(W > MAX) sb.append("N "); // 현재 측정할 구슬의 무게가 최대치보다 크면 측정불가
			else sb.append(dp[N][W] ? "Y " : "N "); // 현재 구슬의 무게가 측정된 적이 있으면 Y, 아니면 N
		}
		
		sb.deleteCharAt(sb.length() - 1); // 마지막 공백문자 지워주기
		
		System.out.println(sb.toString()); // 정답과 출력
	}
	
	private static void dfs(int L, int weight) {
		if(dp[L][weight]) return; // 현재 레벨에서 해당 무게가 이미 측정됐다면 return 
		dp[L][weight] = true; // 첨 측정하는 것이라면 측정 체크
		if(L == N) return; // 모든 추를 사용했다면
		
		dfs(L+1, weight + mapN[L+1]); // 구슬이 올려지지 않은 곳에 추를 얹음
		dfs(L+1, Math.abs(weight - mapN[L+1])); // 구슬이 올려진 곳에 추를 얹음
		dfs(L+1, weight); // 해당 추를 올리지 않음
	}
}
