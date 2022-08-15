
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int A,B,N,M;
	
	static int[] dp;
	static final int LIMIT = 100000;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		st = new StringTokenizer(br.readLine());
		
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int dx[] = {1,-1,A,B,-A,-B};
		
		dp = new int[LIMIT+1];
		
		Arrays.fill(dp, -1);
		
		dp[N] = 0;
		
		Queue<Integer> q = new LinkedList<>();
		
		q.add(N);
		
		while(!q.isEmpty()) {
			int qSize = q.size();
			
			while(qSize-- > 0) {
				int cur = q.peek();
				
				q.poll();
				
				for(int i=0;i<6;++i) {
					int nxt = cur + dx[i];
					
					if(nxt >= 0 && nxt <= LIMIT) {
						//처음 방문해야 최소성 
						if(dp[nxt] == -1) {
							dp[nxt] = dp[cur] + 1;
							q.offer(nxt);
						}
					}
				}
				
				if(A*cur <= LIMIT) {
					if(dp[A*cur] == -1) {
						dp[A*cur] = dp[cur] + 1;
						q.offer(A*cur);
					}
				}
				
				if(B*cur <= LIMIT) {
					if(dp[B*cur] == -1) {
						dp[B*cur] = dp[cur] + 1;
						q.offer(B*cur);
					}
				}
			}
		}
		
		System.out.println(dp[M]);
	}

}
