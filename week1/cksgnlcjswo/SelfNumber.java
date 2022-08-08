
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
//boj4673
//observation: 셀프넘버를 미리 만들어두고 생성안된 애들 check
public class Main {
	
	static StringBuilder sb = new StringBuilder();
	static boolean[] isNotSelfNumber;
	
	public static void main(String[] args) throws Exception {
		
		
		isNotSelfNumber = new boolean[10001];
		
		for(int i=1;i<=10000;++i) {
			isNotSelfNumber[generator(i)] = true;
		}
		
		for(int i=1;i<=10000;++i) {
			if(isNotSelfNumber[i] == false) {
				sb.append(i+"\n");
				
			}
		}
		
		
		System.out.println(sb.toString());
	}
	
	static int generator(int i) {
		int res = i;
		
		while(i != 0) {
			res += i%10;
			i /= 10;
		}
		
		if(res > 10000) return 0;
		return res;
	}
}
