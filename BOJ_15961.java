import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream(new File(Main.class.getResource("").getPath() + "input.txt")));
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt(); // 벨트 위에 놓인 초밥 접시 수
		int D = sc.nextInt(); // 초밥의 가짓수
		int K = sc.nextInt(); // 연속해서 먹는 접시 수
		int C = sc.nextInt(); // 쿠폰 번호
		
		int[] map = new int[N]; // 회전 초밥 벨트 저장
		int[] count = new int[D+1]; // 연속해서 먹을 접시의 종류 
		int type = 1;
		
		count[C] += 1;
		
		for(int i=0; i<N; i++) {
			map[i] = sc.nextInt();
			if(i < K) {
				if(count[map[i]]++ == 0) type++;
			}
		}

		// 모든 초밥을 다 먹어야 하는 경우 종료
		if(N == K) {
			System.out.println(type);
			return;
		}
		
		int res = type;
		
		for(int i=0; i<N; i++) {
			// 해당 초밥 종류의 접시 수가 딱 1개였다면, 가짓수 -1
			if(count[map[i]]-- == 1) type--;
			// 새롭게 추가되는 접시가 기존에 포함되지 않았던 종류라면, 가짓수 +1
			if(count[map[(i+K)%N]]++ == 0) type++;
			
			res = Integer.max(res, type);
		}
		
		System.out.println(res);
	}
}
