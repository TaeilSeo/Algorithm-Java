import java.util.Scanner;

public class Solution {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream(new File(Solution.class.getResource("").getPath() + "input.txt")));
		Scanner sc = new Scanner(System.in);

		// 테스트 케이스
		int T = sc.nextInt();

		for(int t=1; t<=T; t++) {
			int N = sc.nextInt(); // 가방 수
			int MAX = sc.nextInt(); // 가방의 최대 무게(부피)
			int[] map = new int[MAX+1]; // N번째 무게별 최대 가치
			int[] history = new int[MAX+1]; // N-1번째 무게별 최대 가치(직전 기록)
			
			for(int n=0; n<N; n++) {
				int weight = sc.nextInt(); // 무게(부피)
				int value = sc.nextInt(); // 가치
				
				// 최소 자신의 무게(부피) 이상이어야 하므로, 자신의 무게를 시작으로 순회
				for(int j=weight; j<=MAX; j++) {
					int remain = j - weight; // 현재 무게 - 자신의 무게 = 남은 무게(더 담을 수 있는)
					int newValue = value + history[remain]; // 자신의 가치 + 남은 무게에 해당하는 가치
					map[j] = Integer.max(map[j], newValue); // 더 큰 값 저장
				}
				
				history = map.clone(); // 기록 업데이트
			}
			
			System.out.println(String.format("#%d %d", t, map[MAX]));
		}
	}
}
