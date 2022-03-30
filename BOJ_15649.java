import java.util.Scanner;

public class Main {
	static int N, M;
	static int[] res;
	static boolean[] ch;
	static StringBuilder sb;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		res = new int[M];
		ch = new boolean[N+1];
		sb = new StringBuilder();

		perm(0);
		System.out.println(sb.toString());
	}

	static void perm(int L) {
		if(L == M) {
			for(int i=0; i<M; i++) {
				sb.append(res[i]);
				sb.append(" ");
			}
			sb.deleteCharAt(sb.length()-1);
			sb.append("\n");
			return;
		}
		
		for(int i=1; i<=N; i++) {
			if(!ch[i]) {
				ch[i] = true;
				res[L] = i;
				perm(L+1);
				ch[i] = false;
			}
		}
	}
}
