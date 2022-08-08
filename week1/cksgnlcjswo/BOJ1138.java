import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.StringTokenizer;
/*observation: 첫번째 친구 이전에 두명이 크다면 첫번째놈은 반드시 두번째 인덱스
                두번째 친구는 ???
                세번째 친구는???
  */              
public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int N;
	static int[] arr;
	static int[] ans;
	
	static Set<Integer> s;
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		ans = new int[N];
		st = new StringTokenizer(br.readLine());
		
		for(int i=0;i<N;++i) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=1;i<=N;++i) {
			int cur =0;
			int bigCnt = 0; //내 뒤에 오는 숫자들이 들어갈 칸 수
			
			while(cur< N && bigCnt < arr[i-1]) {
				
				if(ans[cur] == 0) {
					cur++;
					bigCnt++;
				} else cur++;			
			}
			
			while(cur< N && ans[cur] != 0) {
				cur++; // i이전 숫자들보다는 i가 항상 크니까 뒤로 보내줌
			}
			
			//System.out.println("cur is "+cur);
			if(cur==N) cur--;
			ans[cur] = i;
		}
			
		for(int i=0;i<N;++i) {
			System.out.print(ans[i]+" ");
		}
	}
}

