import java.util.*;
import java.io.*;

/*
observation: 방에 넣어야 하는 최대값이 6과 9를 제외한 값이면 자명하게 그 값만큼 세트 필요
            6과 9가 최대값이면 6과 9의 쌍을 먼저 넣고 나머지는 최대값에서 최소값을 빼고 나머지 2함
            나누어떨어지지않으면 +1
*/
class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    
    static String N;
    static int[] cnt;

    public static void main(String[] args) throws Exception {
        
        N = br.readLine();
        cnt = new int[10];

        for(int i=0;i<N.length();++i) {
            cnt[N.charAt(i) - '0']++;
        }
        int cand1=0;
        int cand2=0;

        int max = Math.max(cnt[6],cnt[9]);
        int min = Math.min(cnt[6],cnt[9]);

        if(max == min) cand1 = min;
        else {
            cand1 = min;

            max -= min;

            cand1 += max % 2 == 0 ? max/2 : max/2+1;
        }


        for(int i=0;i<9;++i) {
            if(i == 6) continue;
            cand2 = Math.max(cand2, cnt[i]);
        }


        System.out.println(Math.max(cand1,cand2));
    }
}
