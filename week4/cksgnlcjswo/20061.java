
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int N,score,b;
	static int[][] map;
	
 	public static void main(String[] args) throws Exception {
 		N = Integer.parseInt(br.readLine());
 		map = new int[10][10];
 		
 		for(int i=0;i<N;++i) {
 			st = new StringTokenizer(br.readLine());
 			
 			int t = Integer.parseInt(st.nextToken());
 			int x = Integer.parseInt(st.nextToken());
 			int y = Integer.parseInt(st.nextToken());
 		
 			moveBlue(t,x,y);
 			moveGreen(t,x,y); 			
 			rowColCheck(); // 초록, 파랑맵안에서 열,행 체크			
 			clearCheck(); //연한 초록, 연한 파랑 체크
 			
// 			for(int[] row : map) {
// 				System.out.println(Arrays.toString(row));
// 			}
// 			System.out.println("--------"+(i+1)+"------------");
 		}
 		
 		sb.append(score + "\n");
 		
 		for(int i =0;i<4;++i) {
 			for(int j=6;j<=9;++j) {
 				if(map[i][j] == 1) b++;
 			}
 		}

 		for(int i =6;i<=9;++i) {
 			for(int j=0;j<4;++j) {
 				if(map[i][j] == 1) b++;
 			}
 		}
 		
 		sb.append(b);
 		System.out.println(sb.toString());
 	}
 	
 	static void moveBlue(int type,int sx,int sy) {
 		//1x1타일
 		if(type == 1) {
 			
 			int nxtx = sx;
 			int nxty = sy+1;
 			
 			while(isIn(nxtx,nxty) && map[nxtx][nxty] == 0) {
 				nxty++;
 			}
 			nxty--;
 			
 			map[nxtx][nxty] = 1; //타일 놓기
 		} 
 		//ㅁㅁ 모양 타일
 		
 		else if(type == 2) {
 			
 			int nxtx = sx;
 			int nxty = sy+2;
 			
 			while(isIn(nxtx,nxty) && map[nxtx][nxty] == 0) {
 				nxty++;
 			}
 			nxty--;
 			
 			map[nxtx][nxty-1] = map[nxtx][nxty] = 1; //타일 놓기
 		}
 		/*
 		 * ㅁ
 		 * ㅁ
 		 * 타일 놓기
 		 * */
 		else {
 			int nxtx = sx;
 			int nxtx2 = sx+1;
 			int nxty = sy+1;
 			
 			while(isIn(nxtx,nxty) && map[nxtx][nxty] == 0 && map[nxtx2][nxty] == 0) {
 				nxty++;
 			}
 			nxty--;
 			
 			map[nxtx][nxty] = map[nxtx2][nxty] = 1; //타일 놓기
 		}
 	}
 	
 	static void moveGreen(int type,int sx,int sy) {
 		//1x1타일
 		if(type == 1) {
 			
 			int nxtx = sx+1;
 			int nxty = sy;
 			
 			while(isIn(nxtx,nxty) && map[nxtx][nxty] == 0) {
 				nxtx++;
 			}
 			nxtx--;
 			
 			map[nxtx][nxty] = 1; //타일 놓기
 		} 
 		
 		//ㅁㅁ 모양 타일		
 		else if(type == 2) {
 			int nxtx = sx+1;
 			
 			int nxty = sy;
 			int nxty2 = sy+1;
 			
 			while(isIn(nxtx,nxty) && map[nxtx][nxty] == 0 && map[nxtx][nxty2] == 0) {
 				nxtx++;
 			}
 			nxtx--;
 			
 			map[nxtx][nxty2] = map[nxtx][nxty] = 1; //타일 놓기
 		}
 		/*
 		 * ㅁ
 		 * ㅁ
 		 * 타일 놓기
 		 * */
 		else {
 			int nxtx = sx+2;
 			int nxty = sy;
 			
 			while(isIn(nxtx,nxty) && map[nxtx][nxty] == 0) {
 				nxtx++;
 			}
 			nxtx--;
 			
 			map[nxtx-1][nxty] = map[nxtx][nxty] = 1; //타일 놓기
 		}
 	}
 	//행,열이 모두 1로 찼는지 찾기
 	static void rowColCheck() {
 		//파란색 체크
 		for(int j =9;j>=6;--j) {
 			boolean complete = true; // 모든 열이 블록으로 찼는지 야부
 			for(int i=0;i<4;++i) {
 				if(map[i][j] == 0) {
 					complete = false;
 					break;
 				}
 			}
 			
 			if(complete == true) {
 				score++;
 				
 				//왼쪽에 있는 애들 하나씩 옮기기(연판파랑 포함)
 				for(int c = j; c >=4; --c) {
 					
 					for(int i=0;i<4;++i) {
 						map[i][c] = map[i][c-1];
 					}
 				}
 				++j;
 			}
 			
 		}
 		
 		//초록색 체크
 		for(int i =9;i>=6;--i) {
 			boolean complete = true; // 모든 열이 블록으로 찼는지 야부
 			for(int j=0;j<4;++j) {
 				if(map[i][j] == 0) {
 					complete = false;
 					break;
 				}
 			}
 			
 			if(complete == true) {
 				score++;
 				
 				//위에 있는 애들 하나씩 옮기기(연판파랑 포함)
 				for(int r = i; r >=4; --r) {
 					
 					for(int j=0;j<4;++j) {
 						map[r][j] = map[r-1][j];
 					}
 				}
 				++i;
 			}			
 		}						
 	}
 	
 	static void clearCheck() {
 		//연한 파랑부터 체크
 		int bCnt = 0;
 		int gCnt = 0;
 		
 		for(int j=4;j<=5;++j) {
 			for(int i=0;i<4;++i) {
 	 			if(map[i][j] == 1) {
 	 					
 	 				bCnt++;
 	 				break;
 	 			}
 	 		}
 		}
 		
 		for(int i=4;i<=5;++i) {
 			
 			for(int j=0;j<4;++j) {
 	 			if(map[i][j] == 1) {
 	 				gCnt++;
 	 				break;
 	 			}
 	 		}
 		}
 		
 		if(bCnt > 0) moveRight(bCnt);
 		if(gCnt > 0) moveDown(gCnt);		
 	}
 	
 	static void moveRight(int cnt) {
 		
 		for(int i=0;i<4;++i) {
 			for(int j=9;j>=4;--j) {
 				map[i][j] = map[i][j-cnt];
 			}
 		}
 	}
 	
 	static void moveDown(int cnt) {
 		for(int i=9;i>=4;--i) {
 			for(int j=0;j<4;++j) {
 				map[i][j] = map[i-cnt][j];
 			}
 		}
 	}
 	
 	static boolean isIn(int r,int c) {
 		return r < 10 && c < 10;
 	}
}