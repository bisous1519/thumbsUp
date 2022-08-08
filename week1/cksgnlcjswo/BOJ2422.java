
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;
/*
    최대 200개 중에 3개를 뽑기 때문에 200C3의 조합을 써도 되고 3중for문으로 해도 괜찮
    다면 금지되는 조합을 찾을 때 10000개를 다 탐색하는 것은 시간이 오래걸리니 자료구조 생각!
 */
public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int M,N,ans;
	static int[][] notAllow;
	static int SIZE = 3;
	static int[] res;
	static Set<Data> s;
	
	static class Data implements Comparable<Data>{
		int f;
		int s;
		
		public Data(int f,int s) {
			this.f = f;
			this.s = s;
		}
		
		@Override
		public int compareTo(Data o) {
			// TODO Auto-generated method stub
			if(this.f == o.f) return this.s-o.s;
			return this.f - o.f;
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		res = new int[3];
		notAllow = new int[M][2];
		
		s = new TreeSet<>();
		
		for(int i=0;i<M;++i) {
			st = new StringTokenizer(br.readLine());
			int first,second;
			
			first  = Integer.parseInt(st.nextToken());
			second = Integer.parseInt(st.nextToken());
			s.add(new Data(first,second));
		}
	
		for(int i=1;i<=N;++i) {
			for(int j=i+1;j<=N;++j) {
				
				Data check = new Data(i,j);
				if(s.contains(check)) continue;
				
				int temp = check.f;
				check.f = check.s;
				check.s = temp;
				
				if(s.contains(check)) continue;
				
				for(int k=j+1;k<=N;++k) {
					Data check2 = new Data(j,k);
					Data check3 = new Data(i,k);
					
					if(s.contains(check2) || s.contains(check3)) continue;
					
					temp = check2.f;
					check2.f = check2.s;
					check2.s = temp;
					
					temp = check3.f;
					check3.f = check3.s;
					check3.s = temp;
					
					
					if(s.contains(check2) || s.contains(check3)) continue;
					
					ans++;
				}
			}
		}
		System.out.println(ans);
	}
	
}


