import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class Boj1022 {
	
	public static int R1;
	public static int C1;
	public static int R2;
	public static int C2;
	
	public static int[] dxs = {0,-1,0,1};
	public static int[] dys = {1,0,-1,0};
	
	public static boolean isRange(int x, int y, int size) {
		return 0<=x && x<size && 0<=y && y<size;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		R1 = Integer.parseInt(temp[0]);
		C1 = Integer.parseInt(temp[1]);
		R2 = Integer.parseInt(temp[2]);
		C2 = Integer.parseInt(temp[3]);
		
		//1. define array
		int[][] saved = new int[R2-R1+1][C2-C1+1];
		
		//2. make snail array 
		int cnt = 1;
		int x = 0; int y = 0;
		int dir = 0; int len = 1;
		int total = saved.length * saved[0].length;
		int max=0;
		//0,0 부터 시작하면서 값의 범위에 들어오는 경우 ary에 넣어준다.
		while(total>0) {
			//두번마다 방향이 바뀜
			for(int i=0; i<2; i++) {
				for(int j=0; j<len; j++) {
					// 범위에 들어온다면,
					if(R1<=x && x<=R2 && C1<=y && y<=C2) {
						//값을 넣어줌
						saved[x-R1][y-C1] = cnt;
						total-=1;
						max = cnt;
					}
					// 좌표 갱신
					x += dxs[dir];
					y += dys[dir];
					cnt+=1;
					
				}
				dir = (dir+1)%4;
			}
			len+=1;
		}
		
		int ws = 1;
		
		while(max>0) {
			max/=10;
			ws+=1;
		}

		// 3. print values
		for(int r=0; r<saved.length; r++) {
			for(int c=0; c<saved[0].length; c++) {
				String str = null;
				if(c==0)
					str = "%" + (ws-1) + "d";
				else
					str = "%" + ws + "d";
			System.out.printf(String.format(str, saved[r][c]));					
				
			}
			System.out.println();
		}
		
	}

}
