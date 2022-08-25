package com.ssafy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj10103 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Game g = new Game();
		for(int i=0; i<N; i++) {
			int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			g.game(arr[0], arr[1]);
		}
		g.print();
	}
	
	static class Game{
		
		public int S = 100;
		public int C = 100;
		
		public void game(int x, int y) {
			
			if(x>y) {
				this.C-=x;
			}
			else if (x<y) {
				this.S-=y;
			}
		}
		
		public void print() {
			System.out.println(S);
			System.out.println(C);
		}
	}

}
