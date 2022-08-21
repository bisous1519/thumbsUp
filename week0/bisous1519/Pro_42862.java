package com.eomji.prac.algo;

public class Pro_체육복 {
    public static int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        int[] check = new int[n+2];
        for(int r=0; r<lost.length; r++) {
            check[lost[r]]--;
        }
        for(int r=0; r<reserve.length; r++) {
            check[reserve[r]]++;
        }
        for(int r=1; r<=n; r++) {
            if(check[r] == 1) {
                if(check[r - 1] == -1) {
                    check[r - 1]++;
                    check[r]--;
                }else if(check[r + 1] == -1) {
                    check[r + 1]++;
                    check[r]--;
                }
            }
        }
        for(int r=1; r<=n; r++) {
            if(check[r] != -1) {
                answer++;
            }
        }
        return answer;
    }
    
    public static void main(String[] args) {
    	int n = 5;
    	int[] lost = {2, 4};
    	int[] reserve = {1, 3, 5};
    	
    	int answer = solution(n, lost, reserve);
    	System.out.println(answer);
    }
}



class Solution {
    public int changeLetter(char c){
        if('A' <= c && c <= 'M'){
            return c - 'A';   
        }else{
            // if('N' <= c && c <= 'Z')
            return ('Z' + 1) - c;
        }
    }
    public int solution(String name) {
        int answer = 0;
        char[] arr = name.toCharArray();
        for(int r=0; r<arr.length; r++){
            if(arr[r] != 'A'){
                answer += changeLetter(arr[r]);
            }
        }
        //
        int a = 0;
        int b = 0;
        int c = answer + (arr.length - 1);
        if(arr[1] == 'A'){
            int r = 1;
            while(arr[r] == 'A'){
                r++;
            }
            r--;
            a = answer + (arr.length - r - 1);
        }
        if(arr[arr.length - 1] == 'A'){
            int r = arr.length - 1;
            while(arr[r] == 'A'){
                r--;
            }
            b = answer + r;
        }
        else{
            
        }
        return (a < b ? a : b);
    }
}