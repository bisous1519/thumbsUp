
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

/*
    observation: 그냥 배열에 쓰면 삭제하는데 많은 시간 소요
                 검색 알고리즘과 삭제 알고리즘을 어떻게 빠르게 할 수 있을까?
                 이진트리로 구성된 treeset사용
                 비트마스킹도 good
 */

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int M;
	static Set<Integer> s;
	
	public static void main(String[] args) throws Exception {
		
		M = Integer.parseInt(br.readLine());
		s = new TreeSet<>();
		for(int i=0;i<M;++i) {
			st = new StringTokenizer(br.readLine());
			
			String command = st.nextToken();
			
			if(command.equals("add")) {
				int num = Integer.parseInt(st.nextToken());
				
				s.add(num);
			} else if(command.equals("check")) {
				
				int num = Integer.parseInt(st.nextToken());
				
				if(s.contains(num) == true) {
					sb.append("1\n");
				} else {
					sb.append("0\n");
				}
				
			} else if(command.equals("remove")) {
				int num = Integer.parseInt(st.nextToken());
				s.remove(num);
				
			} else if(command.equals("toggle")) {
				int num = Integer.parseInt(st.nextToken());
				
				if(s.contains(num) == true) {
					s.remove(num);
				} else {
					s.add(num);
				}
				
			} else if(command.equals("all")) {
				for(int j=1;j<=20;++j) s.add(j);
				
			} else if(command.equals("empty")) {
				s.clear();
			}
		}
	System.out.println(sb.toString());
	}
}

