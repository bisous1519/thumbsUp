package baekjoon;

/*
 * 셀프 넘버
 * https://www.acmicpc.net/problem/4673
 */

public class Boj_4673 {
	
	public static void main(String[] args) {
		
		boolean answer[] = new boolean[10001];
		
		// 생성자 true로 체크
		for(int i=1; i< 10001; i++) {
			if(isSelf(i)<10001) answer[isSelf(i)] = true;
		}
		
		StringBuilder sb = new StringBuilder();
		
		// 생성자가 아닌 값만 출력
		for(int i=1; i<10001; i++) {
			if(answer[i] == false) sb.append(i+"\n");
		}
		
		System.out.print(sb);
	}
	
	// 매개변수 n가 만드는 생성자를 구하는 로직
	static int isSelf(int n) {
		int sum = n;
		while(n > 0) {
			sum += n%10;
			n /= 10;
		}
		return sum;
	}
}
