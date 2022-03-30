import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Solution {
	static class Data {
		int from, to, weight;

		public Data(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "Data [from=" + from + ", to=" + to + ", weight=" + weight + "]";
		}
	}
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int t=1; t<=T; t++) {
			int V = sc.nextInt();
			int E = sc.nextInt();
			
			List[] list = new ArrayList[V+1];
			for(int i=1; i<=V; i++) {
				list[i] = new ArrayList<Data>();
			}
			
			for(int i=0; i<E; i++) {
				int from = sc.nextInt();
				int to = sc.nextInt();
				int weight = sc.nextInt();
				
				// 무방향이니 양방향 모두 등록
				list[from].add(new Data(from, to, weight));
				list[to].add(new Data(to, from, weight));
			}
			
			PriorityQueue<Data> pq = new PriorityQueue<>(new Comparator<Data>() {
				public int compare(Data o1, Data o2) {
					return Integer.compare(o1.weight, o2.weight);
				}
			});
			
			boolean[] ch = new boolean[V+1];
			long res = 0;
			
			int start = 1;
			int cnt = 0;

			pq.offer(new Data(start, start, 0));
			Data cur = null;
			while( !pq.isEmpty() ) {
				cur = pq.poll();
				
				if(ch[cur.to]) continue;
				res += cur.weight;
				cnt++;
				ch[cur.to] = true;
				
				if(cnt == V) break;
				
				for(int i=0; i<list[cur.to].size(); i++) {
					Data node = (Data) list[cur.to].get(i);
					
					if(ch[node.to]) continue;
					pq.offer(node);
				}
			}
			
			System.out.println(String.format("#%d %d", t, res));
		}
	}
}
