package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_10103 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;

	static int n, A, B;
	public static void main(String[] args) throws NumberFormatException, IOException {
		n = Integer.parseInt(input.readLine());
		A = 100;
		B = 100;
		
		for(int i=0; i<n; i++) {
			tokens = new StringTokenizer(input.readLine());
			int a = Integer.parseInt(tokens.nextToken());
			int b = Integer.parseInt(tokens.nextToken());
			
			if(a<b) {
				A -= b;
			}else if(b<a) {
				B -= a;
			}
		}
		
		System.out.println(A);
		System.out.println(B);
	}
}