import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
	static String[] map = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"}; // 변환할 숫자에 대응되는 string  배열
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in); // 표준 입력
		
		int START = sc.nextInt(); // M, N(1 ≤ M, N ≤ 99). 시작
		int END = sc.nextInt();   // M, N(1 ≤ M, N ≤ 99). 끝
		Data[] results = new Data[END - START + 1]; // 변환된 string과 기존 숫자를 저장하기 위한 class
		
		for(int i=START; i<=END; i++) { // 시작부터 끝가지. 다만 끝도 포함이므로 <=
			if(i >= 10) { // 두 자리수라면
				int first = i / 10;		// 십의 자리 수
				int second = i % 10;	// 일의 자리 수
				results[i - START] = new Data(i, map[first] + " " + map[second]); // 변환 값과 기존 숫자 값 저장. i 값에 START를 빼줘 인덱스 값 맞춰주기
			} else { // 한 자리수라면
				results[i - START] = new Data(i, map[i]); // 변환 값과 기존 숫자 값 저장
			}
		}
		
		Arrays.sort(results, new Comparator<Data>() { // 변환된 string을 기준으로 오름차순하기 위한 sort
			@Override
			public int compare(Data o1, Data o2) {
				return o1.str.compareTo(o2.str); // string 비교를 위하여 compareTo 사용
			}
		});
		
		// M 이상 N 이하의 정수를 문제 조건에 맞게 정렬하여 한 줄에 10개씩 출력한다.
		StringBuilder sb = new StringBuilder(); // StringBuilder사용
		int cnt = 0; // 10개마다 자르기 위하여 cnt 변수 사용
		for(int i=START; i<=END; i++) { // 처음부터 끝 까지
			sb.append(results[i - START].num); // 현재 인덱스 기존 숫자 sb에 append
			cnt++; // cnt 1 증가
			
			if(cnt % 10 == 0) {		// cnt가 10의 배수여서 줄바꿈을 해야한다면
				sb.append("\n");	// 줄바꿈 문자 추가
			} else {				// cnt가 10의 배수가 아니라면
				sb.append(" ");		// 공백 문자 추가
			}
		}
		
		sb.deleteCharAt(sb.length() - 1); // 제일 마지막에 추가된 것이 줄바꿈 문자든 공백 문자든 지워주기
		
		System.out.println(sb.toString()); // 정답 출력
	}
	
	public static class Data {
		int num; 	// 기존 숫자
		String str;	// 변환된 string
		
		public Data(int num, String str) { // 생성자
			super();
			this.num = num;
			this.str = str;
		}
		
		@Override
		public String toString() { // debuging을 위한 toString
			return "Data [num=" + num + ", str=" + str + "]";
		}
	}
}
