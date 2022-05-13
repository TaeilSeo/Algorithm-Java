package baekjoon02589_0512;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
1 초	512 MB
두 지점이 좌표로 주어지지 않는다
최단거리로 왕래할 수 있는 육지 중, 가장 거리가 먼 두 좌표의 거리를 출력해야 한다.

모든 육지 좌표에서 bfs 탐색
 => 각 좌표의 bfs 탐색으로 나올 수 있는 최대 거리들을 차례로 비교
 => 가장 큰 값을 정답으로 출력
*/
public class Main {

	static int R, C;
	static char[][] grid;
	static boolean[][] ch;

	static ArrayList<Data> list;

	static int[] dr = { 0, 0, -1, 1 };
	static int[] dc = { -1, 1, 0, 0 };

	static int answer;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream(new File(Main.class.getResource("").getPath() + "input.txt")));
		Scanner sc = new Scanner(System.in);

		R = sc.nextInt();
		C = sc.nextInt();
		sc.nextLine();

		grid = new char[R][C];
		list = new ArrayList<>();

		for (int r = 0; r < R; r++) {
			String str = sc.nextLine();
			for (int c = 0; c < C; c++) {
				grid[r][c] = str.charAt(c);
				
				if(grid[r][c] == 'L') list.add(new Data(r, c, 0)); // 육지인 좌표를 list에 저장
			}
		}

		for(int i=0; i<list.size(); i++) { // 전체 list 순회
			answer = Integer.max(answer, bfs(list.get(i))); // 각 bfs 탐색에서 반환되는 최댓값과 현재의 값을 비교
		}
		
		System.out.println(answer); // 최댓값 출력
	}
	
	private static int bfs(Data pos) {
		int max = 0; // 해당 좌표의 탐색에서 가장 먼거리를 저장할 변수
		
		ch = new boolean[R][C]; // 방문 체크 배열 초기화
		
		Queue<Data> q = new LinkedList<>();
		q.offer(pos);  // 현재 좌표 삽입
		ch[pos.r][pos.c] = true; // 방문 체크
		
		Data cur = null; // 마지막 cur 값을 쓰기위해, 선언을 while문 밖에서.
		
		while( !q.isEmpty() ) {
			cur = q.poll();
			
			for(int d=0; d<4; d++) { // 4방 탐색
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				
				 // 2차원 배열의 범위를 벗어나지 않을 때,진행할 수 있는 좌표가 있다면
				if(nr >=0 && nr < R && nc >= 0 && nc < C && !ch[nr][nc] && grid[nr][nc] == 'L') {
					ch[nr][nc] = true; // 방문체크
					q.offer(new Data(nr, nc, cur.dist + 1)); // 큐에 삽입
				}
			}
		}
		
		return cur.dist; // pos 좌표 기준 bfs 탐색에서의 최대 거리 값은 큐에서 꺼내지는 마지막 값이 된다.
	}

	public static class Data {
		int r, c;
		int dist;

		public Data(int r, int c, int dist) {
			super();
			this.r = r;
			this.c = c;
			this.dist = dist;
		}

		@Override
		public String toString() {
			return "Data [r=" + r + ", c=" + c + ", dist=" + dist + "]";
		}

	}
}
