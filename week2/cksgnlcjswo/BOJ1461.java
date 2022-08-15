
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    
	static int N,M,ans;

	static List<Integer> neg,pos;
	
	public static void main(String[] args) throws Exception {
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		neg = new ArrayList<>();
		pos = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		
		for(int i=0;i<N;++i) {
			
			int num = Integer.parseInt(st.nextToken());
			if(num > 0) pos.add(num);
			else neg.add(-num);
		}
		
		Collections.sort(pos);
		Collections.sort(neg);
		
		List<Integer> tmp;
		
		//편의상 pos가 절대값이 더 큰게 오도록
		if(pos.size() > 0 && neg.size() > 0 && pos.get(pos.size()-1) < neg.get(neg.size()-1)) {
			tmp = pos;
			pos = neg;
			neg = tmp;
		}
		
		if(pos.size() == 0) {
			pos = new ArrayList<>(neg);
			neg.clear();
		}
		
		if(pos.size() > 0) {
			ans += pos.get(pos.size()-1);
			
			for(int i=pos.size()-1-M;i>=0;i-=M) {
				ans += pos.get(i) *2;
			}
		}
		
		if(neg.size() > 0) {
			for(int i=neg.size()-1;i>=0;i-=M) {
				ans += neg.get(i)*2;
			}
		}
		
		System.out.println(ans);
	}
}

