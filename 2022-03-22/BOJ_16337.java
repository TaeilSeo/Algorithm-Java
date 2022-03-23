import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

public class Main {
	static ArrayList<Character> opers; // 연산자 저장할 arraylist
	static ArrayList<Integer> nums; // 숫자 저장하라 arraylist
	static int answer;
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[] map = br.readLine().toCharArray();
        
        opers = new ArrayList<>();
        nums = new ArrayList<>();
        answer = Integer.MIN_VALUE;
        
        for(int i=0; i<N; i++) {
        	char cur = map[i];
        	
		// 연산자라면
        	if( cur == '*' || cur == '+' || cur == '-' ) {
        		opers.add(cur);
        		continue;
        	}
		// 연산자가 아니라면
        	nums.add(Character.getNumericValue(cur));
        }
        
	// 첫번째 숫자와 처음 수행되어야 할 연산자의 인덱스번호
        dfs(nums.get(0), 0);
        System.out.println(answer);
    }
    
	
    static int calc(char oper, int v1, int v2) {
		switch(oper) {
		case '*':
			return v1 * v2;
		case '+': 
			return v1 + v2;
		case '-':
			return v1 - v2;
		default:
			return -1;
		}
    }
    
    static void dfs(int result, int idx) {
    	if(idx == opers.size()) { // 모든 연산자가 수행되었다면, 최댓값 비교하고 종료
    		if(result > answer) answer = result;
    		return;
    	}
    	
    	// 경우의 수는 두가지로 나뉨
    	// 1. 현재 계산되어야할 연산자가 수행 됨
    	// 2. 현자 계산되어야할 연산자의 수행 안됨 -> 다음 번 연산자 수행
    	// 의 두 가지 경우이다.
    	
    	// 1. 연산 1번 수행으로 idx+=1
    	dfs(calc(opers.get(idx), result, nums.get(idx+1)) ,idx+1);
    	
    	// 2.  연산 1번 수행으로 idx+=2
    	if (idx + 2 <= opers.size() ) {
    		int temp = calc(opers.get(idx+1), nums.get(idx+1), nums.get(idx+2));
    		dfs(calc(opers.get(idx), result, temp) ,idx+2);
    	}
    	
    }
}
