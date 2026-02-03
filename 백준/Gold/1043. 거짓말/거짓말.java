import java.util.*;

public class Main {
    static class Person {
        int num;
        ArrayList<Person> adjacent_list;
        Person(int num) {
            this.num = num;
            adjacent_list = new ArrayList<>();
        }
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        int M = input.nextInt();

        Person[] persons = new Person[N + 1];
        for(int i = 1; i <= N; ++i) persons[i] = new Person(i);

        int knewPersonCount = input.nextInt();

        boolean[] visited = new boolean[N + 1];
        Queue<Person> q = new LinkedList<>();

        for(int i = 0; i < knewPersonCount; ++i) {
            int num = input.nextInt();
            if(!visited[num]) {
                visited[num] = true;
                q.offer(persons[num]);
            }
        }

        int[][] meetings = new int[M][];
        for(int i = 0; i < M; ++i) {
            int size = input.nextInt();
            meetings[i] = new int[size];
            for(int j = 0; j < size; ++j) {
                meetings[i][j] = input.nextInt();
            }
        }

        for(int[] arr : meetings) {
            for(int i = 0; i < arr.length; ++i) {
                int standard = arr[i];
                for(int j = 0; j < arr.length; ++j) {
                    int tmp = arr[j];
                    if(standard == tmp) continue;
                    persons[standard].adjacent_list.add(persons[tmp]);
                }
            }
        }

        while(!q.isEmpty()) {
            Person p = q.poll();
            for(Person person : p.adjacent_list) {
                if(visited[person.num]) continue;
                visited[person.num] = true;
                q.offer(person);
            }
        }

        int answer = 0;
        for(int[] arr : meetings) {
            boolean flag = true;
            for(int x : arr) {
                if(visited[x]) {
                    flag = false;
                    break;
                }
            }
            if(flag) ++answer;
        }
        System.out.println(answer);
    }
}