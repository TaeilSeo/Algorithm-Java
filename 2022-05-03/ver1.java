import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
	static int N, M; // 과목 수, 선수 조건 수
	static List[] map;
	static int[] res;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream(new File(Main.class.getResource("").getPath() + "input.txt")));
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		
		map = new List[N+1];
		res = new int[N+1];
		
		for(int i=0; i<=N; i++) {
			map[i] = new ArrayList<Integer>();
		}
		
		for(int i=0; i<M; i++) {
			int pre = sc.nextInt();
			int post = sc.nextInt();
			
			map[post].add(pre);
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=1; i<=N; i++) {
			dfs(1, i, i);
			sb.append(res[i] + " ");
		}
		
		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb.toString());
	}
	
	private static void dfs(int L, int cur, int from) {
		int cnt = 0;
		
		for(int i=0; i<map[cur].size(); i++) {
			cnt++;
			dfs(L+1, (int)map[cur].get(i), from);
		}
		
		if(cnt == 0) {
			res[from] = Integer.max(res[from], L);
			return;
		}
	}
}
