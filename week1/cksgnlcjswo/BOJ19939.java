import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

/*
    observation: K개의 박에다 공을 무조건 1개이상 넣을 때 최소 요구 개수는 1+2+3+...+K = (K+1)*K/2
                 위의 조건을 만족하는 경우 남는 공은 반드시 N개있는 공에다 넣어야함.
                 답의 최소는 모든 1부터 K까지 박에 넣고 남는 공이 없거나 남는 공이 K로 나누어떨어진 경우
                 나머지는 K가 답
 */
public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int N,K;
	
	public static void main(String[] args) throws Exception {
		
		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		
		int sum = K*(K+1)/2;
		if(N < sum) {
			System.out.print("-1");
		} else {
			N -= sum;
			//이제 가장 큰수부터 한개씩 던지면 됨
			
			if(N % K == 0) System.out.println(K-1);
			else System.out.println(K);
		}
	}
	
}


