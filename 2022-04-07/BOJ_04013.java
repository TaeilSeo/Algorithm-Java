import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	static int K;
	static int[][] grid;
	static Rotation[] map;
	static final int MAGNET_NUM = 4; // 자석 수
	static final int BLADE_NUM = 8; // 자석의 톱니 날 수
	
	static final int RED = 0;
	static final int L = 6;
	static final int R = 2;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream(new File(Solution.class.getResource("").getPath() + "input.txt")));
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		
		for(int t=1; t<=T; t++) {
			// 입력 시작
			K = sc.nextInt();
			
			grid = new int[MAGNET_NUM][BLADE_NUM];
			for(int i=0; i<MAGNET_NUM; i++) {
				for(int j=0; j<BLADE_NUM; j++) {
					grid[i][j] = sc.nextInt();
				}
			}
			
			map = new Rotation[K];
			for(int i=0; i<K; i++) {
				map[i] = new Rotation(sc.nextInt()-1, sc.nextInt());
			}
			
			// 입력 끝
			
			// 0: N극, 1: S극
			// 1: 시계방향, -1: 반시계
			// 빨간화살표 부터 시계방향으로
			
			// print();
			
			for(int i=0; i<K; i++) {
				doRotation(map[i]); // 회전 작업 수행
				// print();
			}
			
			int res = getResult(); // 모든 회전 작업 후, 빨간 화살표의 값을 통해 점수를 정산 후 리턴
			System.out.println(String.format("#%d %d", t, res));
		}
	}
	
	// 회전 작업을 수행하기위한 함수. 실제로 돌리는건 rotate 함수가 수행
	private static void doRotation(Rotation rotation) {
		int num = rotation.num; // 현재 회전 작업을 수행할 자석 번호
		int direct = rotation.direct; // 회전 방향. 가변
		
		boolean[] isRotate = new boolean[MAGNET_NUM]; // 해당 번호의 자석이 실제로 회전하게 되는지 여부
		boolean[] diff = getDiff(); // 자석 번호 n 의 R(index: 2) 과 자석 번호 n+1 의 R(index: 6) 의 일치여부. 일치하면 true
		isRotate[num] = true; // 작업을 수행할 최초 자석은 무조건 돌아가므로 true
		
		if(num % (MAGNET_NUM - 1) == 0) { // 양 끝쪽바퀴(0, 3)가 최초 돌려지는 바퀴로 지정됐을 때
			int idx = num; // 다음 순번으로 회전 여부를 확인할 자석들의 index 번호
			
			// 자석번호가 0이라면 to는 1, 3이라면 -1. 하나의 for문안에서 자석번호  0, 3인 경우를 모두 처리하기 위함
			// 다음 순번으로의 진행방향
			// 0일 경우 1-> 2 -> 3. 3일경우 2 -> 1 -> 0
			int to = num % 2 == 0 ? 1 : -1; 
			
			rotate(rotation, true); // 첫번째로 돌릴바퀴를 실제로 돌리기
			
			for(int i=1; i<MAGNET_NUM; i++) {
				idx += to; // 다음 자석번호로 변경
				int diffIdx = to == 1 ? idx-1 : idx; // isRotate 배열 길이가 3이기때문에 임시방편으로 index 값 조정
				
				// 끝쪽에서 시작됐으므로, 1)이전의 바퀴가 돌았고  2)접해 있는 쌍이 서로 다른 극일 때 회전
				isRotate[idx] = isRotate[idx - to] && diff[diffIdx];
				direct *= -1; // 번호가 변경될때마다 회전 방향이 변경됨
				rotate(new Rotation(idx, direct), isRotate[idx]); // 현재 회전을 수행할 자석 번호, 방향, 실제로 회전할지(false면 돌지 않음)
			}
		} else { // 중앙의 2개 일 때
			int to = num % 2 == 0 ? -1 : 1; // 위와 같은 이유이나, 값은 반대
			int diffIdx = to == 1 ? num : num-1; // isRotate 배열 길이가 3이기때문에 임시방편으로 index 값 조정.위의 경우와 반대
			
			rotate(rotation, true); // 처음 돌리질 자석 돌리고
			
			// for문으로 깔끔하게 해보려다가 포기.
			
			// (num + to) 번호의 자석 회전. num이 2일때 1이 됨
			rotate(new Rotation(num + to, direct*-1), diff[diffIdx]);
			
			// (num + to * 2) 번호의 자석 회전. num이 2일때 0이 됨. 이의 경우 접한 부분의 극이 서로 다르고, 전의 자석이 회전했어야 됨
			rotate(new Rotation(num + to * 2, direct), diff[diffIdx] && diff[diffIdx + to]);
			
			// (num - to) 번호의 자석 회전. num이 2일때 3이 됨
			rotate(new Rotation(num - to, direct*-1), diff[diffIdx - to]);
		}
	}
	
	// 실제 회전을 수행
	private static void rotate(Rotation rotation, boolean flag) {
		if(flag) {
			int[] temp = new int[BLADE_NUM];
			
			for(int i=0; i<BLADE_NUM; i++) {
				int idx = (i + rotation.direct) % BLADE_NUM >= 0 ? (i + rotation.direct) % BLADE_NUM : BLADE_NUM - 1;
				temp[idx] = grid[rotation.num][i];
			}
			
			grid[rotation.num] = temp.clone();
		}
	}
	
	// 접한 부분이 서로 다른지에 대한 여부를 return
	private static boolean[] getDiff() {
		boolean[] diff = new boolean[MAGNET_NUM-1];
		
		for(int i=0; i<MAGNET_NUM-1; i++) {
			if( grid[i][R] != grid[i+1][L]) diff[i] = true;
		}
		
		return diff;
	}
	
	// 결과값 산출
	private static int getResult() {
		int res = 0;
		
		for(int i=0; i<MAGNET_NUM; i++) {
			res += grid[i][RED] == 1 ? (int)Math.pow(2, i) : 0;
		}
		
		return res;
	}
	
	private static void print() {
		for(int i=0; i<MAGNET_NUM; i++) {
			System.out.println(Arrays.toString(grid[i]));
		}
		System.out.println();
	}

	
	static class Rotation {
		int num, direct;

		public Rotation(int num, int direct) {
			super();
			this.num = num;
			this.direct = direct;
		}

		@Override
		public String toString() {
			return "Rotation [num=" + num + ", direct=" + direct + "]";
		}
	}
}
