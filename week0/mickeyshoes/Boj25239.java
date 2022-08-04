package com.ssafy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj25239 {
	
	public static int time;
	public static int min;
	public static int[] patterns;
	
	public static void changeTimes(int t, int m) {
		
		min +=m;
		if(min>60) {
			min-=60;
			time+=1;
		}
		time = (time+t)%12;
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] timeInfo = Arrays.stream(br.readLine().split(":")).mapToInt(Integer::parseInt).toArray();
		time = timeInfo[0]; 
		min = timeInfo[1];
		patterns = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int N = Integer.parseInt(br.readLine());
		
		for(int i=0; i<N; i++) {
			String[] commands = br.readLine().split(" ");
			String[] commandTime = commands[0].split("\\.");
			if(Integer.parseInt(commandTime[0])>60)
				break;
			
			if(commands[1].equals("^")) {
				int dir = time/2;
				if (dir==6) dir=0;
				patterns[dir] = 0;
			}else if (commands[1].contains("MIN")) {
				changeTimes(0, Integer.parseInt(commands[1].replace("MIN", "")));
				
			}else if (commands[1].contains("HOUR")) {
				changeTimes(Integer.parseInt(commands[1].replace("HOUR", "")),0);
			}
		}
		int answer = 0;
		for(int i: patterns)
			answer+= i;
		System.out.println(answer>100? 100:answer);
	}

}
