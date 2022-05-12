import java.util.Scanner;

public class Main {

	static int R, C; // (1 ≤ R,C ≤ 1,000)
	static int[][] grid;
	static boolean[][] ch;
	
	static int answer;
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);

		while(true) {
			R = sc.nextInt();
			C = sc.nextInt();
			
			if(R == 0 && C == 0) break;
			
			grid = new int[R][C];
			ch = new boolean[R][C];
			
			answer = Integer.MIN_VALUE;
			
			for(int r=0; r<R; r++) {
				for(int c=0; c<C; c++) {
					grid[r][c] = sc.nextInt();
				}
//				System.out.println(Arrays.toString(grid[r]));
			}
			
			for(int i=0; i<R; i++) {
				for(int j=0; j<C; j++) {
					
					if(ch[i][j]) continue;
					
					if(grid[i][j] == 0) {
						ch[i][j] = true;
						continue;
					}
					
//					System.out.println("i: " + i + ", j: " + j);
					
					int size = 1;
					int limit = Integer.min(R - i - 1, C - j - 1);
					
					for(int s=1; s<=limit; s++) {
						
						// 제일 바깥라인을 3부분으로 나누어 순회
						int colCnt = 0;
						for(int r=i; r<i+s; r++) {
							if(grid[r][j+s] == 0) break;
							colCnt++;
						}
						if(colCnt != s) break;
						
						int rowCnt = 0;
						for(int c=j; c<j+s; c++) {
							if(grid[i+s][j] == 0) break;
							rowCnt++;
						}
						if(rowCnt != s) break;

						
						if(grid[i + s][j + s] == 0) break;
						
						size++;
					}
					
					for(int r=i; r<i+size; r++) {
						for(int c=j; c<j+size; c++) {
							ch[r][c] = true;
						}
					}
					
					answer = Integer.max(answer, size);
					
//					for(int r=0; r<R; r++) {
//						System.out.println(Arrays.toString(ch[r]));
//					}
				}
			}
			
			if(answer == Integer.MIN_VALUE) answer = 0;
			
			System.out.println(answer);
		}
	}

}
