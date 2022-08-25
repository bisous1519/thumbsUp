import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int N, W,L,ans;
	static int[] arr;
	
 	public static void main(String[] args) throws Exception {
 		st = new StringTokenizer(br.readLine());
 		
 		N = Integer.parseInt(st.nextToken());
 		W = Integer.parseInt(st.nextToken());
 		L = Integer.parseInt(st.nextToken());
 		
 		st = new StringTokenizer(br.readLine());
 		arr = new int[N+1];
 		
 		for(int i=1;i<=N;++i) {
 			arr[i] = Integer.parseInt(st.nextToken());
 		}
 		
 		Queue<Integer> bridge = new ArrayDeque<>();
 		
 		int sum = 0;
 		int idx = 1;
 		
 		for(int t=1;;t++) {
 			//맨 앞트럭이 도착한 경우
 			
 			
 			if(bridge.size() == W) {
 				sum -= bridge.poll();
 			}
 			
 			if(idx == N+1 && sum==0) {
 				ans = t;
 				break;
 			}
 			
 			//i초에서 다리에 올릴수 있는지
 			if(idx <= N && sum + arr[idx] <= L) {
 				bridge.add(arr[idx]);
 				sum += arr[idx];
 				idx++;
 			} 
 			//다리위의 위치정보를 0으로 맞춰줌
 			else {
 				bridge.add(0);
 			}
 		}
 		System.out.println(ans);
 	}
}