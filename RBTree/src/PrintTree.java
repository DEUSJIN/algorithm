/**
 * @Author: DEUSJIN
 * @Date: 2021/2/14 19:03
 */
public class PrintTree {
    private static class Trunk {
        Trunk prev;
        String str;

        private Trunk(Trunk prev, String str) {
            this.prev = prev;
            this.str = str;
        }
    }

    // Helper function to print branches of the binary tree
    private static void showTrunks(Trunk p) {
        if (p == null) {
            return;
        }

        showTrunks(p.prev);

        System.out.print(p.str);
    }

    // 使用中序遍历方式打印二叉树
    private static void traversalPrint(TreeNode root, Trunk prev, boolean isLeft) {
        if (root == null) {
            return;
        }

        String ROOT_PREV                    = "   ";
        String CHILD_PREV                   = "    ";

        String LEFT_CHILD_CURVED_EDGE       = ".---";
        String LEFT_CHILD_STRAIGHT_EDGE     = "   |";

        String RIGHT_CHILD_CURVED_EDGE      = "`---";
        String RIGHT_CHILD_STRAIGHT_EDGE    = "   |";


        String prev_str = CHILD_PREV;
        Trunk trunk = new Trunk(prev, prev_str);

        // 遍历左子树
        traversalPrint(root.getLeft(), trunk, true);

        if (prev == null) {
            trunk.str = ROOT_PREV;
        } else if (isLeft) {
            trunk.str = LEFT_CHILD_CURVED_EDGE;
            prev_str = LEFT_CHILD_STRAIGHT_EDGE;
        } else {
            trunk.str = RIGHT_CHILD_CURVED_EDGE;
            prev.str = prev_str;
        }

        showTrunks(trunk);

        // 打印当前节点
        System.out.println(root.getVal()+"("+(root.getColor()==true?"B":"R")+")");

        if (prev != null) {
            prev.str = prev_str;
        }

        trunk.str = RIGHT_CHILD_STRAIGHT_EDGE;

        // 遍历右子树
        traversalPrint(root.getRight(), trunk, false);
    }

    public static void print(TreeNode root) {
        traversalPrint(root, null, false);
    }
}
