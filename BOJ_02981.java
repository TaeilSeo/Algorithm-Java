import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

/*
 * 시간 초과가 뜨면, dp를 적용해 보려고 했는데,
 * 아예 틀렸다고 나오니까..
 * 어디가 틀렸지?
*/
public class Main {

	static int N; // (2 ≤ N ≤ 100)
	static int[] map;
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		map = new int[N];
		
		for(int i=0; i<N; i++) {
			map[i] = sc.nextInt();
		}
		
		Arrays.sort(map);
		
		StringBuilder sb = new StringBuilder();
		for(int i=2; i <= map[0]; i++) {
			int remain = map[0] % i;
			boolean flag = true;
			
//			System.out.println("i: " + i + " reamin: " + remain);
			
			for(int j=1; j<N; j++) {
				if(map[j] % i != remain) {
					flag = false;
					break;
				}
			}
			
			if(flag) sb.append(i + " ");
		}
		
		sb.deleteCharAt(sb.length() - 1);
		
		System.out.println(sb.toString());
	}

}
