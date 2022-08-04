import java.io.*;
import java.util.*;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N,ans;
	static int[][] W;
	static boolean[] visited;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//System.out.println(1);
		N = Integer.parseInt(br.readLine());
		visited = new boolean[N];
		W=new int[N][N];
		ans = Integer.MAX_VALUE;
		
		for(int i=0;i<N;++i) {
			st = new StringTokenizer(br.readLine());
			
			for(int j=0;j<N;++j) {
				W[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
//		for(int[] row: W) {
//			System.out.println(Arrays.toString(row));
//		}
		
		List<Integer> arr = new ArrayList<>();
		Permutation(arr);
		System.out.println(ans);
	}
	
	static void Permutation(List<Integer> arr) {
		if(arr.size() == N) {
			int cand = 0;
			
			for(int i=1;i<N;++i ) {
				int nxtCost = W[arr.get(i-1)][arr.get(i)];
				if(nxtCost == 0) return; // 갈수 없는경우 
				cand += nxtCost;
			}
			int last = W[arr.get(arr.size()-1)][arr.get(0)];
			if(last == 0) return;
			
			cand += last;//처음장소로 돌아가는 비용
			ans = Math.min(ans,cand);
			return;
		}
		
		for(int i=0;i<N;++i) {
			if(visited[i] == false) {
				visited[i] = true;
				arr.add(i);
				Permutation(arr);
				arr.remove(arr.size()-1);
				visited[i] = false;
			}
		}
	
	}
}
