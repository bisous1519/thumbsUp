import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Boj1461 {
	
	public static int N;
	public static int M;
	public static PriorityQueue<Integer> plusPQ;
	public static PriorityQueue<Integer> minusPQ;
	
	public static PriorityQueue<Integer> padding(PriorityQueue<Integer> pq) {
		if(pq.size()%M !=0) {
			int limit = M - (pq.size()%M);
			for(int i=0; i<limit; i++) {
				pq.add(0);
			}
		}
		
		return pq;
	}
	
	public static int calDistance(PriorityQueue<Integer> pq) {
		int result = 0;
		
		while(!pq.isEmpty()) {
			int prev = 0;
			for(int i=0; i<M; i++) {
				int compare = pq.poll();
				prev = Math.max(prev, compare);
			}
			result += prev*2;
		}
		
		return result;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		N = Integer.parseInt(temp[0]);
		M = Integer.parseInt(temp[1]);
		int answer = 0;
		int maxValue = 0;
		plusPQ = new PriorityQueue<>();
		minusPQ = new PriorityQueue<>();
		
		for(String s : br.readLine().split(" ")) {
			int num = Integer.parseInt(s);
			if(num<0) { 
				num *=-1;
				minusPQ.add(num);
			}
			else
				plusPQ.add(num);
			maxValue = Math.max(num, maxValue);
		}
		
		plusPQ = padding(plusPQ);
		minusPQ = padding(minusPQ);
		
		answer += calDistance(plusPQ) + calDistance(minusPQ);
		System.out.println(answer-maxValue);
		
	}

}
