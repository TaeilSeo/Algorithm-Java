import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

public class Main {
	static ArrayList<Character> opers;
	static ArrayList<Integer> nums;
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
        	
        	if( cur == '*' || cur == '+' || cur == '-' ) {
        		opers.add(cur);
        		continue;
        	}
        	nums.add(Character.getNumericValue(cur));
        }
        
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
    	if(idx == opers.size()) {
    		if(result > answer) answer = result;
    		return;
    	}
    	
    	// 괄호를 두르게 되면, 결국 기존의 순서가 역전되는 것이므로
    	// 1. 순서가 역전되지 않음
    	// 2. 순서가 역전됨
    	// 의 두 가지 경우이다.
    	
    	// 1
    	dfs(calc(opers.get(idx), result, nums.get(idx+1)) ,idx+1);
    	
    	// 2
    	if (idx + 2 <= opers.size() ) {
    		int temp = calc(opers.get(idx+1), nums.get(idx+1), nums.get(idx+2));
    		dfs(calc(opers.get(idx), result, temp) ,idx+2);
    	}
    	
    }
}
