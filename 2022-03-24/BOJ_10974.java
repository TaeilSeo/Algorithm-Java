import java.util.Scanner;

public class Main {
	static int N;
	static boolean[] ch;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		ch = new boolean[N+1];
		int[] arr = new int[N];
		
		perm(0, arr);
	}

	static void perm(int L, int[] arr) {
		if(L == N) {
			StringBuilder sb = new StringBuilder();
			for(int i=0; i<N; i++) {
				sb.append(arr[i]);
				sb.append(" ");
			}
			sb.deleteCharAt(sb.length()-1);
			
			System.out.println(sb.toString());
		}
		
		for(int i=1; i<=N; i++) {
			if( !ch[i] ) {
				ch[i] = true;
				arr[L] = i;
				perm(L+1, arr);
				ch[i] = false;
			}
		}
	}
}
