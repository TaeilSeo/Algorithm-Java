import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Solution {
	static int N;
	static int[] map;
	static int[] ch;
	static int max;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream(new File(Solution.class.getResource("").getPath() + "input.txt")));
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int t=1; t<=T; t++) {
			N = sc.nextInt();
			map = new int[N];
			max = 0;
			
			for(int n=0; n<N; n++) {
				map[n] = sc.nextInt();
				max += map[n];
			}
			
			ch = new int[max+1];
			
			HashSet<Integer> set = new HashSet<>();
			ArrayList<Integer> list = new ArrayList<>();
			set.add(0);
			
			for(int i=0; i<N; i++) {
				for(int num : set) {
					list.add(num + map[i]);
				}
				set.addAll(list);
				list.clear();
				
				System.out.println(set.toString());
			}
			
			System.out.println(String.format("#%d %d", t, set.size()));
		}
	}
}


/*

import java.util.Scanner;
import java.util.stream.IntStream;

public class Solution {
	static int N;
	static int[] map;
	static int[] ch;
	static int _sum, cnt, max;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream(new File(Solution.class.getResource("").getPath() + "input.txt")));
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int t=1; t<=T; t++) {
			N = sc.nextInt();
			map = new int[N];
			
			for(int n=0; n<N; n++) {
				map[n] = sc.nextInt();
			}
			
			_sum = IntStream.of(map).sum();
			ch = new int[_sum+1];
			cnt = 0;
			max = (int)Math.pow(2, N);
			
			dfs(0, 0);
						
			System.out.println(String.format("#%d %d", t, IntStream.of(ch).sum()));
		}
	}
	
	public static void dfs(int L, int sum) {
		if( cnt == max || IntStream.of(ch).sum() == _sum+1)
			return;
		
		if ( ch[sum] == 0) {
			ch[sum] = 1;
			cnt++;
		}
		
		if( L == N ) {
			return;
		}
		
		dfs(L+1, sum + map[L]);
		dfs(L+1, sum);
	}
}
*/
