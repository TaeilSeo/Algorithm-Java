import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static int V, E;
	static ArrayList<Integer>[] list;
	static boolean[] ch;
	static final int START = 1;
	
	static int answer;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream(new File(Main.class.getResource("").getPath() + "input.txt")));
		Scanner sc = new Scanner(System.in);
		
		V = sc.nextInt();
		E = sc.nextInt();
		list = new ArrayList[V+1];
		for(int i=0; i<=V; i++) {
			list[i] = new ArrayList<Integer>();
		}
		ch = new boolean [V+1];
		
		answer = 0;
		
		for(int e=0; e<E; e++) {
			int v1 = sc.nextInt();
			int v2 = sc.nextInt();
			
			list[v1].add(v2);
			list[v2].add(v1);
		}
		
		dfs(0, START);
		
		System.out.println(--answer);
	}

	private static void dfs(int L, int v) {
		ch[v] = true;
		answer++;
		for(int vector : list[v]) {
			if( !ch[vector] ) {
				dfs(L+1, vector);
			}
		}
	}
}
