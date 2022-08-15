import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Boj11723 {

	public static Set<String> set;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		set = new HashSet<>();
		
		for(int i=0; i<N; i++) {
			String[] command = br.readLine().split(" ");
			switch(command[0]) {
			case "add":
				set.add(command[1]);
				break;
			case "remove":
				set.remove(command[1]);
				break;
			case "check":
				sb.append(set.contains(command[1])? 1:0).append("\n");
				break;
			case "toggle":
				if(set.contains(command[1]))
					set.remove(command[1]);
				else
					set.add(command[1]);
				break;
			case "all":
				set = new HashSet<>(Arrays.asList(
						"1", "2", "3", "4", "5", 
						"6", "7", "8", "9", "10",
						"11", "12", "13", "14", "15",
						"16", "17", "18", "19", "20"));
				break;
			case "empty":
				set = new HashSet<>();
				break;
			}
			
		}
		
		System.out.println(sb.toString());
		

	}

}
