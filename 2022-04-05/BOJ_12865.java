import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int K = sc.nextInt();
		
		Data[] input = new Data[N];
		for(int i=0; i<N; i++) {
			input[i] = new Data(sc.nextInt(), sc.nextInt());
		}
		
		int[] map = new int[K+1];
		int[] history = new int[K+1];
		
		for(int i=0; i<N; i++) {
			Data cur = input[i];
			
			for(int j=cur.weight; j<=K; j++) {
				int remain = j - cur.weight;
				int newValue = cur.value + history[remain];
				map[j] = newValue > map[j] ? newValue : map[j];
			}
			
			history = map.clone();
		}
		
		System.out.println(map[K]);
	}

	private static class Data {
		int weight, value;

		public Data(int weight, int value) {
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
