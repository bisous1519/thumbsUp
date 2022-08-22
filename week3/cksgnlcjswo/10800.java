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
	static StringBuilder sb = new StringBuilder();
	
	static int N;
	static List<Ball> arr = new ArrayList<>();
	static int[] pref; //현재 까지 누적합
	static int[] c; //특정 색에 대한 누적합
	static int[] s; //특정 크기에 대한 누적합
	static int[] ans;
	
 	public static void main(String[] args) throws Exception {
 		
 		N = Integer.parseInt(br.readLine());
 		pref = new int[N+1];
 		ans = new int[N+1];
 		
 		int max = 0;
 		
 		arr.add(new Ball(-100,-100,-100)); //인덱스 맞추기 위함
 		
 		for(int i=1;i<=N;++i) {
 			st = new StringTokenizer(br.readLine());
 			int color = Integer.parseInt(st.nextToken());
 			int size = Integer.parseInt(st.nextToken());
 			
 			arr.add(new Ball(i,color,size));
 			max = Math.max(size, max);
 		}
 		
 		c = new int[N+1];
 		s = new int[max+1];
 		
 		Collections.sort(arr);
 		
 		for(int i=1;i<=N;++i) {
 			int curColor = arr.get(i).color;
 			int curSize = arr.get(i).size;
 			int curId = arr.get(i).id;
 			
 			//현재 i번째에서 잡을 수 있는 공의 점수는 pref에서 이전에 나랑 같은 크기의 공, 나랑 같은 사이즈의 공 빼줘야함
 			if(arr.get(i-1).color == curColor && arr.get(i-1).size == curSize) {
 				ans[curId] = ans[arr.get(i-1).id];
 			}
 			else {
 				ans[curId] = Math.max(pref[i-1] - c[curColor] - s[curSize],0); 
 			}
 			
 			
 			s[curSize] += curSize;
 			c[curColor] += curSize;
 			
 			pref[i] = pref[i-1] + curSize;
 		}
 		
 		for(int i=1;i<=N;++i) {
 			sb.append(ans[i] + "\n");
 		}
 		
 		System.out.println(sb.toString());
 	}
 	
 	static class Ball implements Comparable<Ball>{
 		int id;
 		int color;
 		int size;
 	
 		public Ball(int id, int color, int size) {
 			this.id = id;
 			this.color = color;
 			this.size = size;
 		}
 		
 		@Override
 		public int compareTo(Ball b) {
 			//사이즈 같으면 색깔 오름차순
 			if(this.size == b.size) return Integer.compare(this.color, b.color);
 			
 			return Integer.compare(this.size, b.size); //크기 오름차순
 		}
 	}
}