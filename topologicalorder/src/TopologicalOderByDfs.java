import java.util.ArrayList;
import java.util.List;

/**
 * @Author: DEUSJIN
 * @Date: 2021/1/12 12:06
 */
public class TopologicalOderByDfs {
    private List<List<Integer>> edges;
    private int[] visited;
    private int[] result;
    private boolean valid=true;
    private int idx;
    public int[] getOder(int n,int[][] prerequisites){
        edges = new ArrayList<>();
        for(int i = 0;i<n;i++){
            edges.add(new ArrayList<>());
        }
        visited = new int[n];
        result = new int[n];
        idx = n-1;
        for (int[] ints : prerequisites) {
            edges.get(ints[1]).add(ints[0]);
        }
        for (int i = 0; i < n&&valid; i++) {
            if(visited[i] == 0){
                dfs(i);
            }
        }
        return valid?result:new int[0];
    }
    public void dfs(int i) {
        if(!valid){
            return;
        }
        visited[i] = 1;
        for (Integer integer : edges.get(i)) {
            if(visited[integer] == 0){
                dfs(integer);
            }
            if(visited[integer] != 2){
                valid = false;
                return;
            }
        }
        visited[i] = 2;
        result[idx--] = i;
    }
}
