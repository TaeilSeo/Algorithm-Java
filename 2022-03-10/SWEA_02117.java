import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	static int N, M;
	static int[][] grid;
	static ArrayList<int[]> list;
	static int res;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream(new File(Solution.class.getResource("").getPath() + "input.txt")));
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		
		for(int t=1; t<=T; t++) {
			N = sc.nextInt();
			M = sc.nextInt();
			grid = new int[N][N];
			res = 0;
			
			list = new ArrayList<>();
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					int v = sc.nextInt();
					grid[i][j] = v;
					
				}
			}
			
//			for(int n=1; n<=(int)N/2+1; n++) {
			for(int n=1; n<=N*2+1; n++) {
				double min = (double)((int)Math.pow(n, 2) + (int)Math.pow(n-1, 2))/M;
				int max = 0;
				
				for(int r=0; r<N; r++) {
					for(int c=0; c<N; c++) {
						int cnt = 0;
						
						int temp = 0;
						for(int i=r-n+1; i<=r; i++) {
							for(int j=c-temp; j<=c+temp; j++) {
								if(i>=0 && i<N && j>=0 && j<N && grid[i][j] == 1) {
									cnt++;
								}
							}
							temp++;
						}
						
						temp--;
						
						for(int i=r+1; i<r+n; i++) {
							temp--;
							for(int j=c-temp; j<=c+temp; j++) {
								if(i>=0 && i<N && j>=0 && j<N && grid[i][j] == 1) {
									cnt++;
								}
							}
							
						}
						
						if(cnt > max) max = cnt;
					}
				}
				
//				System.out.println("n: " + n +  ", max: " + max + ", min" + min);
				if(max > min && max > res) res = max;
			}
			
			System.out.println(String.format("#%d %d", t, res));
		}
	}

}
