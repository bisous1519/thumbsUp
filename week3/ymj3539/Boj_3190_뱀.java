package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Boj_3190_뱀 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	static int N, K, L;
	static int[][] map;
	static String[] dirInfo = new String[101];
	static int[] dx = {0, 1, 0, -1}; // 상(0) 우(1) 하(2) 좌(3)
	static int[] dy = {-1, 0, 1, 0};
	static int curDir;
	static int answer;
	static Point head;
	static Queue<Point> queue = new LinkedList<>();	// 뱀
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(input.readLine());
		K = Integer.parseInt(input.readLine());
		
		map = new int[N+1][N+1];
		
		for(int i=0; i<K; i++) {
			tokens = new StringTokenizer(input.readLine());
			int r = Integer.parseInt(tokens.nextToken());
			int c = Integer.parseInt(tokens.nextToken());
			
			map[r][c] = 1;
		}
		
		L = Integer.parseInt(input.readLine());
		for(int i=0; i<L; i++) {
			String[] str = input.readLine().split(" ");
			dirInfo[Integer.parseInt(str[0])] = str[1];
		}
		
//		System.out.println(Arrays.toString(dirInfo));
		/////////////입력끝//////////////
		
		func();
		System.out.println(answer);
	}
	
	static void func() {
		Point head = new Point(1,1);
		queue.offer(head);
		
		curDir = 1;
		
		// 시간에 따른 처리
		for(int i=0; i<=dirInfo.length; i++) {
			answer =i;
//			if(answer == 12) {
//				for(int[] arr: map) {
//					System.out.println(Arrays.toString(arr));
//				}
//			}
			
			head.r += dy[curDir];
			head.c += dx[curDir];
			
//			System.out.println(i+"초: "+"방향-"+curDir+" "+head.toString());
			
			
			//// 진행하려는 곳에 사과의 유무에 따른 로직
			if(!isIn(head.r,head.c) || map[head.r][head.c] == 2) {
				return;
			}
			
			queue.offer(new Point(head.r,head.c));
			
			if(map[head.r][head.c] == 0) {
				Point tail = queue.poll();
				map[tail.r][tail.c] = 0;
			}
			

			map[head.r][head.c] = 2;
			
			
			// 방향 전환
			if(dirInfo[i] != null) {
				if(dirInfo[i].equals("D")) {
					curDir += 1;
					
				}else if(dirInfo[i].equals("L")) {
					curDir += 3;
				}
				curDir %= 4;
			}
			
		}
	}
	
//	static void isApple(int r, int c) {
//		// 사과가 없으면
//		if(map[head.r][head.c] == 0) {
//			Point tail = queue.poll();
//			map[tail.r][tail.c] = 0;
//		}
//		
//		if(!isIn(head.r,head.c) || map[head.r][head.c] == 2) {
//			return;
//		}
//
//		map[head.r][head.c] = 2;
//		queue.offer(new Point(head.r,head.c));
//	}
	
	static class Point{
		int r,c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "[(" + r + ", " + c + ")]";
		}
		
		
	}
	
	static boolean isIn(int r, int c) {
		return (r>=1 && c>=1 && r<=N && c<=N);
	}
}
