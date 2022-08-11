
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N,K,ans;
	static List<Integer> arr,diff;
	
	public static void main(String[] args) throws Exception {
		
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		arr = new ArrayList<>();
		diff = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		
		for(int i=0;i<N;++i) {
			arr.add(Integer.parseInt(st.nextToken()));
		}
		
		if(N<=K) {
			System.out.println(0);
			return;
		}
		
		Collections.sort(arr);
		
		for(int i=1;i<N;++i) {
			diff.add(arr.get(i)- arr.get(i-1));
		}
		
		
		Collections.sort(diff,Collections.reverseOrder());
		
		//K가 1개라면 가장 거리가 큰놈을 지울 수는 없음
		//K가 2개라면 가장 거리가 큰놈 1개 지움
		//K가 3개라면 가장 거리가 큰놈 2개지움 ....
		
		for(int i=0;i<K-1;++i) {
			diff.set(i, 0);
		}
		
		for(int i=0;i<diff.size();++i) {
			ans += diff.get(i);
		}
		System.out.println(ans);
	}
}
