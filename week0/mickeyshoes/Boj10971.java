package com.ssafy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class Boj10971 {
	
	public static int N;
	public static int answer;
	public static int[][] cityCosts;
	public static boolean[] visited;
	public static Stack<Integer> stack;
	
	public static void calAnswer(Integer[] permutations) {
		int sum = 0;
		
		for(int i=0, j=1; i<permutations.length; i++, j=(j+1)%N) {
			if(cityCosts[permutations[i]-1][permutations[j]-1] ==0) return;
			sum+= cityCosts[permutations[i]-1][permutations[j]-1];
		}
		answer = Math.min(answer, sum);
	}
	
	public static void makePermutation() {
		
		if(stack.size() == N) {
			calAnswer(stack.toArray(new Integer[stack.size()]));
			return;
		}
		
		for(int i=1; i<=N; i++) {
			if(!visited[i] && cityCosts[stack.peek()-1][i-1] != 0) {
				stack.add(i);
				visited[i] = true;
				makePermutation();
				visited[i] = false;
				stack.pop();
				
			}
		}
		
		
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		cityCosts = new int[N][];
		visited = new boolean[N+1];
		stack = new Stack<Integer>();
		answer = 10_000_001;
		
		for(int i=0; i<N; i++) {
			cityCosts[i] = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
		}
		
		for(int i=1; i<=N; i++) {
			stack.add(i);
			visited[i]= true;
			makePermutation();
			stack.pop();
			visited[i] = false;
		}
		
		System.out.println(answer);
		
	}

}
