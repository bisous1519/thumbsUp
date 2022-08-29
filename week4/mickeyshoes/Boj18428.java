import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Boj18428 {
	
	public static int N;
	public static boolean endFlag = false;
	
	public static ArrayList<Point> teachers;
	
	public static int[] dxs = {1,0,0,-1};
	public static int[] dys = {0,1,-1,0};
	
	public static char[][] map;
		
	public static int[] toCoordinate(int x){
		int[] coords = new int[2];
		
		coords[0] = x/N;
		coords[1] = x%N;
		
		return coords;
	}
	
	public static char[][] copyMap(char[][] map){
		char[][] newChar = new char[N][N];
		
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				newChar[r][c] = map[r][c];
			}
		}
		
		return newChar;
	}
	
	public static boolean isRange(int x, int y) {
		return 0<=x && x<N && 0<=y && y<N;
	}
	
	public static boolean isPossible(int[][] coords, char[][] map) {
		
		for(int r=0; r<coords.length; r++) {
			map[coords[r][0]][coords[r][1]] = 'O';
		}

		for(int t=0; t<teachers.size(); t++) {
			Point teacher = teachers.get(t);
			//선생님 좌표를 가지고 사방탐색 진행해서 학생 걸리면 빠
			for(int d=0; d<dxs.length; d++) {
				int nx = teacher.x + dxs[d];
				int ny = teacher.y + dys[d];
				while(isRange(nx, ny)) {
					if(map[nx][ny] == 'S') return false;
					
					if(map[nx][ny] == 'O') break;

					nx += dxs[d];
					ny += dys[d];
				}
			}
		}
		
		return true;
	}
	
	public static void combinations(int depth, int start, int[][] ary) {
		
		
		if(depth == 3) {
			endFlag = isPossible(ary, copyMap(map));
			return;
		}
		
		for(int i=start; i<N*N; i++) {

			if(endFlag) return;
			
			int[] coords = toCoordinate(i);
			if(map[coords[0]][coords[1]] == 'X') {
				ary[depth] = coords;
				combinations(depth+1, i+1, ary);
			}
		}
		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		teachers = new ArrayList<Point>();
		map = new char[N][N];
		
		for(int r=0; r<N; r++) {
			String[] temp = br.readLine().split(" ");
			for(int c=0; c<N; c++) {
				map[r][c] = temp[c].charAt(0);
				if(map[r][c] == 'T') {
					teachers.add(new Point(r,c));
				}
			}
		}
		
		combinations(0,0, new int[3][]);
		System.out.println(endFlag? "YES":"NO");

	}
	
	static class Point{
		int x;
		int y;
		
		public Point(int x, int y) {
			this.x=x; this.y=y;
		}
	}

}
