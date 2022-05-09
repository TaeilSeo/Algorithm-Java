import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

/*
 * 1 초 (추가 시간 없음)	128 MB
 * 
*/

public class Main {
	
	static int N; // 2 이상 100,000 이하
	static int[] map; // -1,000,000,000 이상 1,000,000,000 이하
	
	static int left, right;
	static int leftIdx, rightIdx;
	static int min;

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		map = new int[N];
		
		for(int i=0; i<N; i++) {
			map[i] = sc.nextInt();
		}
		
		left = -1_000_000_001;
		right = 1_000_000_001;
		leftIdx = 0;
		rightIdx = N - 1;
		
		Arrays.sort(map);
		
		System.out.println(left + " " + right);
	}
}
