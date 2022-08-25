import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int N,sang=100,chang=100;
	
	
 	public static void main(String[] args) throws Exception {
 		N= Integer.parseInt(br.readLine());
 		
 		for(int i=0;i<N;++i) {
 			st = new StringTokenizer(br.readLine());
 			int a = Integer.parseInt(st.nextToken());
 			int b = Integer.parseInt(st.nextToken());
 			
 			if(a > b) sang -= a;
 			else if(a < b) chang -= b;
 		}
 		System.out.println(chang + "\n" + sang);
 	}
}