import java.util.Arrays;
import java.util.Comparator;

class Solution {
	static int[] ch;
	static int N;
	static String[][] t;
	static String[] res;
	static boolean flag;

	public String[] solution(String[][] tickets) {
		t = tickets;
		
		Arrays.sort(t, new Comparator<String[]>() {

			@Override
			public int compare(String[] o1, String[] o2) {
				if(o1[0].toString().contentEquals(o2[0].toString()))
					return o1[1].toString().compareTo(o2[1].toString());
				else
					return o1[0].toString().compareTo(o2[0].toString());
			}			
		});
		
		N = tickets.length;
		ch = new int [N];
		flag = true;
		
		String[] ways = new String [N+1];


		for(int n=0; n<N; n++) {
			ch[n] = 1;
		}

		String from = "ICN";

		ways[0] = from;

		dfs(0, from, ways);
		
		return res;
	}

	public static void dfs(int L, String from, String[] ways) {
		if(L == N) {
			res = ways;
			flag = false;
			return;
		}

		for(int n=0; n<N; n++) {
			if( t[n][0].equals(from) && ch[n] == 1 && flag ) {
				ch[n] = 0;
				ways[L+1] = t[n][1];
				dfs(L+1, t[n][1], ways);
				ch[n] = 1;
			}
		}
	}
}
