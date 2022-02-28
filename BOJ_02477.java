import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int[] dy = {};
	static int[] dx = {};
	public static void main(String[] args) throws Exception {
		String path = Main.class.getResource("").getPath();
		File file = new File(path + "input.txt");
		System.setIn(new FileInputStream(file));

		Scanner sc = new Scanner(System.in);
		
		int S = sc.nextInt();
		int N = 6;
		int[][] input = new int[N][];
		int[] ch = new int[5];
		ArrayList<Integer> list = new ArrayList<>();
		int res = 0;
		
		
		for(int n=0; n<N; n++) {
			// 1 동
			// 2 서
			// 3 남
			// 4 북
			int d = sc.nextInt();
			int v = sc.nextInt();
			input[n] = new int[] {d, v};
			
			ch[d]++;
		}
		
		
		for(int i=1; i<=4; i++) {
			if(ch[i] == 1) {
				for(int j=0; j<N; j++) {
					if(input[j][0] == i) list.add(j);
				}
			}
		}
		
		int bIdx1 = list.get(0);
		int bIdx2 = list.get(1);
		
		int sIdx1 = bIdx1 + 3 < N ? bIdx1 + 3 : (bIdx1 + 3) % N;
		int sIdx2 = bIdx2 + 3 < N ? bIdx2 + 3 : (bIdx2 + 3) % N;
		
		res = (input[bIdx1][1]*input[bIdx2][1] - input[sIdx1][1]*input[sIdx2][1]) * S;
		
		System.out.println(res);
	}
}
