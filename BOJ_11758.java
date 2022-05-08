import java.lang.*;
import java.util.*;

public class Main {
	
	static int N;
	static Data[] map;
	
	static int answer;

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		N = 3;
		map = new Data[N];
		answer = 0;
		
		for(int i=0; i<N; i++)
		{
			map[i] = new Data(sc.nextInt(), sc.nextInt());
		}
		
		int ccwRes = ccw(map[0], map[1], map[2]);
		
		if(ccwRes > 0) {
			answer = 1;
		} else if (ccwRes < 0) {
			answer = -1;
		}
		
		System.out.println(answer);
	}

	private static int ccw(Data p1, Data p2, Data p3) {
		int xy = p1.x * p2.y + p2.x * p3.y + p3.x * p1.y;
		int yx = p1.y * p2.x + p2.y * p3.x + p3.y * p1.x;
		
		return xy - yx;
	}
	
	public static class Data {
		int x, y;

		public Data(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Data [x=" + x + ", y=" + y + "]";
		}
	}
}
