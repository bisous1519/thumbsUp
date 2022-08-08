
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
//observation: 등수를 매긴다는 거는 나보다 큰 놈을 들을 찾아야함.

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N;
	
	static boolean[] isNotSelfNumber;
	static Person[] persons;
	static int[] rank;
	public static void main(String[] args) throws Exception {
		
		N = Integer.parseInt(br.readLine());
		persons = new Person[N];
		rank = new int[N];
		
		for(int i=0;i<N;++i) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			rank[i] = 1;
			persons[i] = new Person(w,h);
		}
		
		
		for(int i=0;i<N;++i) {
			for(int j=i+1;j<N;++j) {
				int res = greaterThan(persons[i],persons[j]);
				if( res== 1) {
					rank[j]++;
				} else if(res == -1) {
					rank[i]++;
				}
			}
		}
		
		for(int i=0;i<N;++i) {
			sb.append(rank[i]+" ");
		}
		
		System.out.println(sb.toString());
	}
	
	static int greaterThan(Person p1, Person p2) {
		if(p1.weight > p2.weight && p1.height > p2.height) {
			return 1;
		} else if(p1.weight < p2.weight && p1.height < p2.height) {
			return -1;
		}
		
		return 0;
	}
}

class Person {
	int weight;
	int height;
	
	public Person(int weight, int height) {
		this.weight = weight;
		this.height = height;
	}
}
