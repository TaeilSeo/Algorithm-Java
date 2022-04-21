import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N;
	static int[][] grid;
	static int[][] temp;
	static boolean[][] ch;
	static int min, max;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static int open;
	
	static int answer;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream(new File(Main.class.getResource("").getPath() + "input.txt")));
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		min = sc.nextInt();
		max = sc.nextInt();
		grid = new int[N+2][N+2]; // padding 처리를 위해 +2
		temp = new int[N+2][N+2]; // padding 처리를 위해 +2
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				grid[i][j] = sc.nextInt(); // 입력값 저장
			}
		}
		
		answer = 0;
		
		while(true) {
			ch = new boolean[N+2][N+2]; // 각각의 날 마다 사용할 방문체크 배열
			open = 0; // 국경 연 횟수 초기화
			
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					if(ch[i][j]) continue; // 이미 순회한 곳이면 pass
					
					ch[i][j] = true; // 순회 체크
					bfs(i, j); // bfs 탐색
				}
			}
			
			if(open == 0) break; // 국경이 열린적이 한 번도 없으면 종료
			answer++; // 국경이 한번이라도 열렸다면 열린 일수 1 증가
			
			for(int i=1; i<=N; i++) {
				grid[i] = temp[i].clone(); // temp 배열값 deep copy
			}
		}
		
		System.out.println(answer);
	}

	private static void bfs(int r, int c) {
		Queue<Pos> q = new LinkedList<>();
		q.offer(new Pos(r, c)); // 넘어온 좌표값 큐에 삽입
		
		ArrayList<Pos> list = new ArrayList<>(); // 현재 넘어온 좌표를 기준으로 동맹국이될 나라들을 저장할 list
		int sum = 0; // 동맹국의 인구수 총합
		int cnt = 0; // 동맹국의 수
		
		while( !q.isEmpty() ) {
			Pos cur = q.poll();
			list.add(cur); // 동맹국에 추가
			sum += grid[cur.r][cur.c]; // 인구수 합산
			cnt++; // 수 증가
			
			for(int d=0; d<4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				int diff = Math.abs(grid[cur.r][cur.c] - grid[nr][nc]); // 인접국과의 인구수 차이
				
				 // 유효 인덱스 범위내이며, 최대 최소 범위 내에 있고, 방문한 적이 없다면
				if(grid[nr][nc] != 0 && diff >= min && diff <= max && !ch[nr][nc]) {
					open++; // 국경을 연 횟수 증가
					ch[nr][nc] = true; // 방문 처리
					q.offer(new Pos(nr, nc)); // 큐에 새롭게 동맹국이된 국가 추가
				}
			}
		}
		
		int avg = sum / cnt; // 평균 인구수 산출
		for(Pos cur : list) { // 동맹국을 순회하며 
			temp[cur.r][cur.c] = avg; // grid가 아닌 temp 2차원배열의 인구수 수정. grid를 직접 건들면 변경된 값이 영향을 미침
		}
	}
	
	public static class Pos {
		int r, c;

		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Pos [r=" + r + ", c=" + c + "]";
		}
	}
}
