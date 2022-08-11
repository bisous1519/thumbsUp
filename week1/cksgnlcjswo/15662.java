
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int T,K;
	static char[][] gear;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		T = Integer.parseInt(br.readLine());
		gear = new char[T][8];
		
		for(int i=0;i<T;++i) {
			String g = br.readLine();
			
			for(int j=0;j<8;++j) {
				gear[i][j] = g.charAt(j);
			}
		}
		K = Integer.parseInt(br.readLine());
		
		for(int i=0;i<K;++i) {
			st = new StringTokenizer(br.readLine());
			
			int which = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			
			which--;
			
			check(which,dir);
		}
		int ans = 0;
		for(int i=0;i<T;++i) {
			if(gear[i][0] == '1') ans++;
		}
		
		System.out.println(ans);
	}

	
	static void check(int idx, int d) {
		
		int[] dir = new int[T]; //현재 방향을 바꿀건지 아닌지 결정
		
		dir[idx] = d;
		
		int nxt = idx+1;
		
		while(nxt < T && gear[nxt-1][2] != gear[nxt][6]) {
			dir[nxt] = dir[nxt-1] * -1;
			nxt++;
		}
		
		int prev = idx-1;
		
		while(prev >= 0 && gear[prev][2] != gear[prev+1][6]) {
			dir[prev] = dir[prev+1] * -1;
			prev--;
		}
		
		for(int i=0;i<T;++i) {
			if(dir[i] != 0) {
				
				rotate(i,dir[i]);
			}
		}
		
	}
	
	static void rotate(int idx, int dir) {
		
		//시계방향
		if(dir == 1) {
			char last = gear[idx][7];
			
			for(int i = 6;i>=0;--i) {
				gear[idx][i+1] = gear[idx][i];
			}
			gear[idx][0] = last;
		}
		//반시계 방향
		else {
			char first = gear[idx][0];
			
			for(int i=0;i<7;++i) {
				gear[idx][i] = gear[idx][i+1]; 
			}
			gear[idx][7] = first;
		}
	}
}
