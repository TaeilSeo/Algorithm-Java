import java.util.Scanner;

public class Main {
	static int N, weight, res;
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		res = 0;
		weight = N;
		
		if(isDividedBy5()) {
			print();
			return;
		} else {
			while(true) {
				weight-=3;
				res++;
				
				if(weight < 3 || isDividedBy5()) {
					print();
					break;
				}
			}
		}
	}
	
	static boolean isDividedBy5() {
		boolean flag = false;
		
		if(weight % 5 == 0) {
			res += (int)weight/5;
			weight = 0;
			flag = true;
		}
		
		return flag;
	}
	
	static void print() {
		if(weight != 0) res = -1;
		System.out.println(String.format("%d", res));
	}
}
