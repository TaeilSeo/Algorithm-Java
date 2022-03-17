import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	// 정점 정보를 저장할 클래스
	public static class Vertex {
		int n, dist;

		public Vertex(int to, int dist) {
			super();
			this.n = to;
			this.dist = dist;
		}

		@Override
		public String toString() {
			return "Vertex [n=" + n + ", dist=" + dist + "]";
		}
	}
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream(new File(Main.class.getResource("").getPath() + "input.txt")));
		Scanner sc = new Scanner(System.in);
		
		int V = sc.nextInt();
		int E = sc.nextInt();
		
		// 인접리스트 생성
		ArrayList<int[]>[] list = new ArrayList[V+1];
		for(int i=1; i<=V; i++) {
			list[i] = new ArrayList<>();
		}
		
		// 입력값 인접리스트에 저장
		for(int i=0; i<E; i++) {
			int from  = sc.nextInt();
			int to  = sc.nextInt();
			int dist  = sc.nextInt();
			
			list[from].add(new int[] {to, dist});
		}
		
		// 시작점과 끝점
		int START = sc.nextInt();
		int END = sc.nextInt();
		
		// 정점별 최소거리, 방문 check 배열
		int[] distance = new int[V+1];
		boolean[] ch = new boolean[V+1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		
		// 시작 위치 지정
		distance[START] = 0;
		
		// 우선순위 큐 선언, 할당 후 comparator 설정. 정렬 기준은 정점의 최소 거리
		PriorityQueue<Vertex> pq = new PriorityQueue<>(new Comparator<Vertex>() {
			@Override
			public int compare(Vertex o1, Vertex o2) {
				return Integer.compare(o1.dist, o2.dist);
			}
		});
		
		// 시작점 pq에 삽입
		pq.offer(new Vertex(START, 0));
		
		Vertex cur;
		while( !pq.isEmpty() ) {
			// 현재 우선순위큐(최소힙)에서 최소 거리값을 가지는 루트값 가져오기
			// A도시라 명명
			cur = pq.poll();
			
			// 해당 정점이 이미 방문한 도시면 pass
			if( ch[cur.n] ) continue;
			
			// 방문 표시
			ch[cur.n] = true;
			
			// 해당 도시가 도착지점이라면 중지
			if(cur.n == END) break;
			
			// 현재 도시(정점)과 연결되어져 있는 도시 ( = 해당 정점 번호에 해당하는 인접리스트의 원소들) 순회
			for(int j=0; j<list[cur.n].size(); j++) {
				// 연결된 도시(정점) 정보를 저장할 변수 선언
				// B 도시라 명명
				int[] vertex = list[cur.n].get(j);
				
				// B 도시에 이미 방문, 즉 해당 도시의 최소거리가 이미 결정 되어졌다면 pass
				if( ch[vertex[0]] ) continue;
				
				// 현재까지 파악된 B 도시로 가는 최소 거리 > ( A 도시까지 가기위한 최소거리 + A 도시에서 B 도시까지의 거리 )
				if(distance[vertex[0]] > distance[cur.n] + vertex[1]) {
					// B도시로의 최소거리 업데이트
					distance[vertex[0]] = distance[cur.n] + vertex[1];
					
					// 업데이트된 B도시의 정보를 우선순위 큐에 삽입
					// 이미 우선순위큐에 B도시가 있어도, 현재 삽입되는 최소거리값이 더 작기때문에 기존에 들어가잇던 B도시 정보보다 먼저 꺼내짐
					// 방문된 도시의 최소 거리는 업데이트 될 수 없기 때문에, 값이 변경되지 않음.
					pq.offer(new Vertex(vertex[0], distance[vertex[0]]));
				}
			}
		}
		
		// 도착 도시까지의 최소거리 출력
		System.out.println(distance[END]);
	}

}
