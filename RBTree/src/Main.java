import org.junit.Test;

import java.util.Random;

/**
 * @Author: DEUSJIN
 * @Date: 2021/2/6 9:56
 */
public class Main {

    public static void main(String[] args) {
        RBTree<Integer> rbTree = new RBTree<>();
        int[] test = {53,68,54,67,23,54,16,745,32,623,128,123};
        for (int i = 0; i < test.length; i++) {
            rbTree.add(test[i]);
        }
        rbTree.displayAll();

        System.out.println("=======");
        System.out.println("After delete 53");
        rbTree.delete(53);
        rbTree.displayAll();
        System.out.println("=======");
        System.out.println("After delete 23");
        rbTree.delete(23);
        rbTree.displayAll();
        System.out.println("=======");
        System.out.println("After delete 54");
        rbTree.delete(54);
        rbTree.displayAll();
        System.out.println("=======");
        System.out.println("After delete 67");
        rbTree.delete(67);
        rbTree.displayAll();
        System.out.println("=======");
        System.out.println("After delete 745");
        rbTree.delete(745);
        rbTree.displayAll();
        System.out.println("=======");
        System.out.println("After delete 123");
        rbTree.delete(123);
        rbTree.displayAll();
        System.out.println("=======");
        System.out.println("After delete 32");
        rbTree.delete(32);
        rbTree.displayAll();
        System.out.println("=======");
        System.out.println("After delete 54");
        rbTree.delete(54);
        rbTree.displayAll();
    }
}
