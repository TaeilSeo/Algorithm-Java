import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	static int N, K;	// (1 ≤ N, K ≤ 300,000)
	static Data[] jewels;	// (0 ≤ Mi, Vi ≤ 1,000,000)
	static int[] bags;	// (1 ≤ Ci ≤ 100,000,000)

	static long answer;

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in); // 표준 입력

		N = sc.nextInt();
		K = sc.nextInt();
		jewels = new Data[N];
		bags = new int[K];

		for(int i=0; i<N; i++) {
			jewels[i] = new Data(sc.nextInt(), sc.nextInt(), true);
		}

		for(int i=0; i<K; i++) {
			bags[i] = sc.nextInt();
		}

		answer = 0;

		Arrays.sort(jewels, new Comparator<Data>() {
			@Override
			public int compare(Data o1, Data o2) {
				if(o1.weight == o2.weight) return Integer.compare(o1.value, o2.value) * -1;
				else return Integer.compare(o1.weight, o2.weight);
			}
		});
		
		Arrays.sort(bags);
		
		PriorityQueue<Data> q = new PriorityQueue<>(new Comparator<Data>() {
			@Override
			public int compare(Data o1, Data o2) {
				return Integer.compare(o1.value, o2.value) * -1;
			}
		});
		
		int start = 0;
		
		for(int k=0; k<K; k++) { // 가방 순회
			for(int n=start; n<N; n++) { // 보석 순회
				// 현재 가방이 담을 수 있는 한계치까지, 보석을 순회하면서 pq에 넣기
				// -> 아직 뭐가 제일 값어치 있는지 모른다!
				// => 즉, 현재 가방에 어떤 보석을 담아야 할 지 결정하지 못했다.
				if(bags[k] >= jewels[n].weight) {
					q.offer(jewels[n]);
					start++;
					continue;
				}
				
				break;
			}
			
			if(!q.isEmpty()) {
				Data jewel = q.poll();
				answer += jewel.value;
			}
		}
		
		System.out.println(answer);
	}


	public static class Data {
		int weight, value;
		
		public Data(int weight, int value, boolean isValid) {
			super();
			this.weight = weight;
			this.value = value;
		}

		@Override
		public String toString() {
			return "Data [weight=" + weight + ", value=" + value + "]";
		}
	}
}
