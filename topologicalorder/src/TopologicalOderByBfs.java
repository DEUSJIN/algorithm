import java.util.*;

/**
 * @Author: DEUSJIN
 * @Date: 2021/1/12 13:07
 */
public class TopologicalOderByBfs {
    private List<List<Integer>> edges = new ArrayList<>();
    private int[] indegrees;
    private int[] res;
    private Deque<Integer> deque;
    private int num;
    public int[] findOder(int n,int[][] prerequisites){
        num = n;
        res = new int[n];
        for (int i = 0; i < n; i++) {
            edges.add(new ArrayList<>());
        }
        indegrees = new int[n];
        deque = new LinkedList<>();
        for (int[] ints : prerequisites) {
            edges.get(ints[1]).add(ints[0]);
            indegrees[ints[0]]++;
        }
        for (int i = 0; i < indegrees.length; i++) {
            if(indegrees[i] == 0){
                deque.offer(i);
            }
        }
        System.out.println(Arrays.toString(indegrees));
        while (!deque.isEmpty()){
            Integer idx = deque.poll();
            res[n-num] = idx;
            num--;
            for (Integer integer : edges.get(idx)) {
                if(--indegrees[integer] == 0){
                    deque.offer(integer);
                }
            }
        }
        if(num == 0){
            return res;
        }
        return new int[0];
    }

    public static void main(String[] args) {
        TopologicalOderByBfs bfs = new TopologicalOderByBfs();
        int[][] p = {{1,0}} ;
        int[] oder = bfs.findOder(2, p);
        System.out.println(Arrays.toString(oder));
    }
}
