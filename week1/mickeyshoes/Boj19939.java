import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj19939 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		int N = Integer.parseInt(temp[0]);
		int K = Integer.parseInt(temp[1]);
		
		if(K*(K+1)/2 >N)
			System.out.println(-1);
		else {
			int prefixsum = K%2==0? K*(K+1)/2:0;
			
			if((N-prefixsum)%K==0)
				System.out.println(K-1);
			else
				System.out.println(K);
		}
	}
}
