import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Boj12761 {
	
	public static int start;
	public static int end;
	public static int[] steps;
	public static boolean[] visited;
	
	static class Coord{
		
		int x;
		int depth;
		
		public Coord(int x, int depth) {
			this.x =x; this.depth = depth;
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		int A = Integer.parseInt(temp[0]);
		int B = Integer.parseInt(temp[1]);
		start = Integer.parseInt(temp[2]);
		end = Integer.parseInt(temp[3]);
		steps = new int[]{1, -1, -A, -B, A, B};
		Queue<Coord> q = new LinkedList<>();
		visited = new boolean[100000+1];
		visited[start] = true;
		q.offer(new Coord(start,0));
		
		if(start==end)
			System.out.println(0);
		else {
			while(!q.isEmpty()) {
				Coord c = q.poll();
				
				//add
				for(int i=0; i<steps.length; i++) {
					int nx = c.x + steps[i];
					
					if(nx>100000 || nx<1) continue;
					
					if(nx==end) {
						System.out.println(c.depth+1);
						q = new LinkedList<>();
						break;
					}
					
					if(nx<=100000 && !visited[nx]) {
						visited[nx] = true;
						q.offer(new Coord(nx, c.depth+1));
					}
				}
				
				//jump
				for(int i=4; i<steps.length; i++) {
					int nx = c.x * steps[i];
					
					if(nx>100000 || nx<1) continue;
					
					if(nx==end) {
						System.out.println(c.depth+1);
						q= new LinkedList<>();
						break;
					}
					
					if(nx<=100000 && !visited[nx]) {
						visited[nx] = true;
						q.offer(new Coord(nx, c.depth+1));
					}
				}
				
			}
			
		}
		

	}

}
