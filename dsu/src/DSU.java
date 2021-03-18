import org.junit.Test;

import java.util.*;

/**
 * 并查集
 * @Author: DEUSJIN
 * @Date: 2021/1/11 10:49
 */
public class DSU {
    private int[] fa;
    private int[] rank;
    @Test
    public void sort(){
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(10, (o1, o2) -> o1.compareTo(o2));
    }
    public DSU(int n){
        fa = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            fa[i] = i;
            rank[i] = 1;
        }
    }
    public int find(int x){
        return fa[x] == x?x:(fa[x] = find(fa[x]));
    }
    public void union(int a,int b){
        int x = find(a);
        int y = find(b);
        if(x == y){
            return;
        }
        if(rank[x]<=rank[y]){
            fa[x] = y;
        }else {
            fa[y] = x;
        }
        if(rank[x] == rank[y]){
            rank[y]++;
        }

    }
}
