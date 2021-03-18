import java.util.Random;

/**
 * @Author: DEUSJIN
 * @Date: 2021/2/21 9:53
 */
public class Main {

    public static int st(int[] nums,int l,int r){
        int n = nums.length;
        int[][] f = new int[n+1][21];
        int[] log2 = new int[n+1];
        for(int i = 1;i<=n;i++){
            f[i][0] = nums[i-1];
        }
        for(int i = 1;i<=n;i++){
            for(int j = 1;j+(1<<i)-1<=n;j++){
                f[j][i] = Math.max(f[j][i-1],f[j+(1<<(i-1))][i-1]);
            }
        }
        for(int i = 2;i<=n;i++){
            log2[i] = log2[i/2]+1;
        }
        int s = log2[r-l+1];
        return Math.max(f[l+1][s],f[r-(1<<s)+2][s]);
    }

    public static void main(String[] args) {
        int[] nums = {10,9,2,1,3,5,10,9,1};
        for (int i = 0; i < nums.length; i++) {
            System.out.println(i+":" + nums[i]);
        }
        int k = 0;
        while(k++<10){
            int x = new Random().nextInt(9);
            int y = new Random().nextInt(9);
            System.out.println(Math.min(x, y)+","+Math.max(x, y)+":"+st(nums, Math.min(x, y), Math.max(x, y)));
        }
    }
}
