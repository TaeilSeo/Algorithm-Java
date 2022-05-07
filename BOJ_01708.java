import java.io.*;
import java.util.*;
/*
 * 1. 기준점 구하기(좌측 최하단)
 * 2. 기준점을 제외한 좌표값 정렬(x축 기준으로 각도 정렬)
 * 3. stack에 기준점과 정렬된 좌표값 
 */

public class Main {
	
	static int N; // N(3 ≤ N ≤ 100,000)
	static Pos[] map;
	static Pos pivot;
	
	static final int MAX = 40_000;
	
	static Stack<Pos> stack; // Convex hull을 그릴 좌표들을 저장할 stack
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		map = new Pos[N];
		pivot = new Pos(MAX + 1, MAX + 1);
		
		// 좌표 입력, 기준점 찾기
		for(int i=0; i<N; i++) {
			map[i] = new Pos(sc.nextInt(), sc.nextInt());
			
			if(map[i].y == pivot.y) {
				if(map[i].x < pivot.x) {
					pivot = map[i];
				}
			} else {
				if(map[i].y < pivot.y) {
					pivot = map[i];
				}
			}
		}
		
		Arrays.sort(map, new Comparator<Pos>() {
			@Override
			public int compare(Pos secend, Pos third) {
				int ccwRes = ccw(pivot, secend, third);
				if(ccwRes > 0) return -1;
				else if(ccwRes < 0) return 1;
				else return getDist(pivot, secend) > getDist(pivot, third) ? 1 : -1;
			}
		});
		
		stack = new Stack<>();
		
		stack.add(pivot);
		
		for(int i=1; i<map.length; i++) {
			// 현재 점이 반시계를 만족할 때 까지 pop
			while(stack.size() > 1 && ccw( stack.get(stack.size() - 2), stack.get(stack.size() - 1), map[i]) <= 0 ) {
				stack.pop();
			}
			stack.add(map[i]);
		}
		
		System.out.println(stack.size());
	}
	
	// Counter Clockwise
	private static int ccw(Pos p1, Pos p2, Pos p3) {
		long xy = p1.x * p2.y + p2.x * p3.y + p3.x * p1.y;
		long yx = p1.y * p2.x + p2.y * p3.x + p3.y * p1.x;
		
		if(xy - yx > 0) return 1; // 반시계
		if(xy - yx < 0) return -1; // 시계
		return 0; // 일직선
	}
	
	private static long getDist(Pos p1, Pos p2) {
		return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
	}
	
	public static class Pos {
		long x, y;

		public Pos(long x, long y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Pos [x=" + x + ", y=" + y + "]";
		}
	}
}
