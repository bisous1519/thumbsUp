package com.ssafy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Boj13335 {
	
	public static int N,W,L;
	
	public static int convert2Int(String s) {
		return Integer.parseInt(s);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] infos = br.readLine().split(" ");
		N = convert2Int(infos[0]);
		W = convert2Int(infos[1]);
		L = convert2Int(infos[2]);
		
		Queue<Truck> trucks = new LinkedList<>();
		for (String s : br.readLine().split(" "))
			trucks.add(new Truck(convert2Int(s), W));
		
		int time = 1;
		int onboardingWeight = 0;
		int index = 0;
		int cnt = 0;
		ArrayList<Truck> out = new ArrayList<Truck>();
		while(cnt<N) {
			if(!trucks.isEmpty() && onboardingWeight+trucks.peek().weight<=L) {
				onboardingWeight+= trucks.peek().weight;
				out.add(trucks.poll());
			}
			
			for(int i=index; i<out.size(); i++) {
				Truck temp = out.get(i);
				temp.tiktok();
				if(temp.time ==0) {
					cnt+=1;
					index+=1;
					onboardingWeight -= temp.weight;
				}
			}
			
			time+=1;
		}
		System.out.println(time);
		
	}
	
	static class Truck{
		int weight;
		int time;
		
		public Truck(int weight, int time) {
			this.weight = weight; this.time = time;
		}
		
		public void tiktok() {
			this.time-=1;
		}
	}

}
