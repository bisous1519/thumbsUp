import java.util.*;

//observation1 : 보트에 탈 수 있다면 최대한 간당간당하게 타야지!
class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        
        Arrays.sort(people);
        int n = people.length;
        int l = 0;
        int r = n-1;
        
        while(l<=r) {
            //현재 가장 작은놈(l)이 간당간당하게 태울 수 있는 친구(r)
            if(people[l] + people[r] <= limit) {
                l++; 
            } 
            answer++; r--;
        }
       
        return answer;
    }
}