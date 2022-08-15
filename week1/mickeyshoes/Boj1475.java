import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj1475 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] ary = new int[10];
		
		for(char c : br.readLine().toCharArray()) {
			
			int idx = Character.getNumericValue(c);
			
			if(idx ==6)
				ary[9] +=1;
			else
				ary[idx] +=1;
		}
		
		int answer = 0;
		for(int i=0; i<9; i++) {
			answer = Math.max(ary[i], answer);
		}
		System.out.println(Math.max(answer, (int)Math.ceil(1.0*ary[9]/2)));

	}

}
