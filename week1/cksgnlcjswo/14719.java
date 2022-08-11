
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int W,H,ans;
	static int[] map;
	static boolean[] check;
	
	public static void main(String[] args) throws Exception {
		
		st = new StringTokenizer(br.readLine());
		
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		map = new int[W];
		check = new boolean[W];
		
		st = new StringTokenizer(br.readLine());
		
		for(int i=0;i<W;++i) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		
		//h만큼 잘라서 그 높이만큼 공간이 있으면 ok
		for(int h =0;h<=H;++h) {
			
			for(int w=0;w<W;++w) {
				if(map[w] < h) {
					int nxt = w+1;
					int prev = w-1;
					
					while(nxt < W && map[nxt] < h) nxt++;
					while(prev >=0 && map[prev] < h) prev--;
					if(nxt == W || prev < 0) continue;
					
					ans += nxt - prev - 1;
					w = nxt;
				}
			}
		}
		System.out.println(ans);
	}

}

