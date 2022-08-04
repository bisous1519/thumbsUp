import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st; 
	
	static int h,m,L; // 현재 시간, 분, 명령 개수
	static int[] info; //영역별 수치
	static boolean[] seal; // 봉인 여부
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		st = new StringTokenizer(br.readLine(),":");
		
		h= Integer.parseInt(st.nextToken());
		m= Integer.parseInt(st.nextToken());
		
		info = new int[6];
		seal = new boolean[6];
		
		st = new StringTokenizer(br.readLine());
		
		for(int i=0;i<6;++i) {
			info[i] = Integer.parseInt(st.nextToken()); 
		}
		
		L = Integer.parseInt(br.readLine());
		
		for(int i=0;i<L;++i) {
			st = new StringTokenizer(br.readLine());
			float f = Float.parseFloat(st.nextToken());;
			String command = st.nextToken();
			
			//봉인
			if(command.equals("^")) {
				//경계선 제외
				if(h % 2 == 0 && m == 0) continue;
				
				seal[h/2] = true;
			} else {
				//분 더하기
				
				StringBuilder sb = new StringBuilder();
				
				for(int j=0;j<command.length();++j) {
					if(command.charAt(j) >= '0' && command.charAt(j) <= '9') {
						sb.append(command.charAt(j));
					} else break;
				}
				
				int tmp = Integer.parseInt(sb.toString());
				
				if(tmp >= 10) m += tmp; //분
				else h += tmp; //시간
				
				//시간 조정
				if(m >= 60) {
					h++;
					m-=60;
				} 
				
				if(h >= 12) h-=12;
			}
		}
		
		int ans = 0;
		
		//봉인 안된거 더하기
		for(int i=0;i<6;++i) {
			if(seal[i] == false) {
				ans += info[i];
			}
		}
		if(ans > 100) ans = 100;
		System.out.println(ans);
	}

}
