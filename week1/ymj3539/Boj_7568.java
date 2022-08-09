package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 덩치
 * https://www.acmicpc.net/problem/7568
 */

public class Boj_7568 {
	
	static int N;	// 전체 사람 수
	static int[][] arr;	// 몸무게, 키
	static int[] answer;	// 덩치 등수
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws NumberFormatException, IOException{
		
		N = Integer.parseInt(input.readLine());
		
		arr = new int[N][2];
		answer = new int[N];
		
		for(int i=0; i<N; i++) {
			tokens = new StringTokenizer(input.readLine());
			arr[i][0] = Integer.parseInt(tokens.nextToken());
			arr[i][1] = Integer.parseInt(tokens.nextToken());
		}
		
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(i == j) continue;
				// 몸무게와 키가 모두 내가 더 작은 경우의 횟수
				if(arr[i][0] < arr[j][0] && arr[i][1] < arr[j][1]) {
					++answer[i];
				}
			}
			// 덩치 등수(자신 보다 더 큰 덩치의 사람 수 +1) 
			++answer[i];
		}
		
		for(int i=0; i<N-1; i++) {
			sb.append(answer[i]+" ");
		}
		sb.append(answer[N-1]);
		
		System.out.println(sb);
	}
	
}
