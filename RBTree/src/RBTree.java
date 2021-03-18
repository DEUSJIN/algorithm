/**
 * @Author: DEUSJIN
 * @Date: 2021/2/6 8:52
 */
public class RBTree<T extends Comparable<T>> {
    private TreeNode<T> myRoot;
    private static final boolean RED = false;
    private static final boolean BLACK = true;

    public RBTree() {
        this.myRoot = new TreeNode<>(null, true, null, null, null);
    }

    /**
     * 添加节点
     * @param val
     */
    public void add(T val){
        TreeNode<T> node = new TreeNode<>(val, RED, null, null, null);
        if(myRoot.getRight() == null){
            myRoot.setRight(node);
            node.setParent(myRoot);
            node.setColor(BLACK);
            return;
        }
        TreeNode<T> curNode = myRoot.getRight();
        TreeNode<T> tmp = null;
        while (curNode != null){
            tmp = curNode;
            if(curNode.getVal().compareTo(node.getVal())<=0){
                curNode = curNode.getRight();
            }else{
                curNode = curNode.getLeft();
            }
        }
        node.setParent(tmp);
        if(node.getVal().compareTo(tmp.getVal())<0){
            tmp.setLeft(node);
        }else{
            tmp.setRight(node);
        }
        insertFixUp(node);
    }

    /**
     * 插入修复
     * @param node
     */
    public void insertFixUp(TreeNode<T> node){
        TreeNode<T> parent = node.getParent();
        //插入节点为根节点，变黑，返回
        if(parent == myRoot){
//            System.out.println("情况1");
            node.setColor(BLACK);
            return;
        }
        //插入节点父节点为黑色，返回
        if(parent.getColor() == BLACK){
//            System.out.println("情况2");
            return;
        }
        TreeNode<T> uncle = getUncle(node);
        TreeNode<T> grdFa = parent.getParent();
//        System.out.println("parent:" + parent + ",uncle:" + uncle + ",grdpa:" + grdFa);
        //插入节点父节点为红，叔叔节点存在也为红，则祖父节点变红，父节点和叔叔节点变黑
        if(parent.getColor() == RED && uncle!=null && uncle.getColor() == RED){
//            System.out.println("情况3");
            parent.setColor(BLACK);
            uncle.setColor(BLACK);
            grdFa.setColor(RED);
            insertFixUp(grdFa);
            return;
        }
        //若插入节点父节点为红色，叔叔节点不存在或为黑色,插入节点为父节点右孩子，父节点为祖父节点左孩子，则以父节点为轴左旋
        else if(parent.getColor() == RED &&(uncle == null||uncle.getColor()==BLACK)&&parent.getRight() == node&&grdFa.getLeft() == parent){
//            System.out.println("情况4.1");
            leftRotate(parent);
            insertFixUp(parent);
        }
        //若插入节点父节点为红色，叔叔节点不存在或为黑色,插入节点为父节点左孩子，父节点为祖父节点右孩子，则以父节点为轴右旋
        else if(parent.getColor() == RED &&(uncle == null||uncle.getColor()==BLACK)&&parent.getLeft() == node&&grdFa.getRight() == parent){
//            System.out.println("情况4.2");
            System.out.println(parent.getLeft());
            System.out.println(node);
            System.out.println(grdFa.getRight());
            System.out.println(parent);
            rightRotate(parent);
            insertFixUp(parent);
        }
        else if(parent.getColor() == RED && parent.getLeft() == node &&grdFa.getLeft() == parent && (uncle == null||uncle.getColor()==BLACK)){
//            System.out.println("情况5");
            rightRotate(grdFa);
            parent.setColor(BLACK);
            grdFa.setColor(RED);
        }
        else if(parent.getColor() == RED && parent.getRight() == node &&grdFa.getRight() == parent && (uncle == null||uncle.getColor()==BLACK)){
//            System.out.println("情况6");
            leftRotate(grdFa);
            parent.setColor(BLACK);
            grdFa.setColor(RED);
        }
    }

    /**
     * 得到node的叔叔节点
     * @param node
     * @return
     */
    public TreeNode<T> getUncle(TreeNode<T> node){
        TreeNode<T> parent = node.getParent();
        TreeNode<T> grdFa = parent.getParent();
        if(grdFa == null||grdFa == myRoot){
            return null;
        }
        TreeNode<T> uncle = null;
        if(grdFa.getLeft() == parent){
            uncle = grdFa.getRight();
        }else{
            uncle = grdFa.getLeft();
        }
        return uncle;
    }

    /**
     * 删除节点
     * @param val
     */
    public void delete(T val){
        TreeNode<T> node = get(val);
        if(node == null){
            return;
        }
        //待删除节点
        TreeNode<T> node1 = getSmallFromRight(node);
        //习惯性选取待删除节点右子树最小节点 node1 替换待删除节点（只是值替换，颜色不变），并将待删除节点变为 node1
        node.setVal(node1.getVal());
        //待删除节点父节点
        TreeNode<T> parent = node1.getParent();
        //待删除节点唯一的孩子
        TreeNode<T> child = getChild(node1);
        //child是否为null，即子节点是否存在,true为存在
        boolean flag = true;
        if(child == null){
            flag = false;
            child = new TreeNode<>(null, BLACK, null, null, parent);
        }
        //情况1. 自身是红色，直接删除。
        if(node1.getColor()==RED){
            System.out.println("情况1");
            if(parent.getRight() == node1){
                parent.setRight(child);
            }else{
                parent.setLeft(child);
            }
            child.setParent(parent);
        }else if(node1.getColor() == BLACK&&child.getColor()==RED){
            System.out.println("情况2");
            //情况2. 自身是黑色，唯一子节点是红色，直接删除并将子节点变为黑色。
            if(parent.getRight() == node1){
                parent.setRight(child);
            }else{
                parent.setLeft(child);
            }
            child.setColor(BLACK);
            child.setParent(parent);
        }else{
            //情况3. 自身是黑色，唯一子节点不存在或也是黑色，先删除自身，再分情况讨论
            if(parent.getRight() == node1){
                parent.setRight(child);
            }else{
                parent.setLeft(child);
            }
            child.setParent(parent);
            deleteHelper(child);

        }
        if(!flag){
            if(child.getParent().getRight()==child){
                child.getParent().setRight(null);
            }else{
                child.getParent().setLeft(null);
            }
        }
    }
    private void deleteHelper(TreeNode<T> node){
        TreeNode<T> parent = node.getParent();
        TreeNode<T> uncle = getUncle(node);
        TreeNode<T> brother = getBrother(node);
        TreeNode<T> leftNephew = brother==null?null:brother.getLeft();
        TreeNode<T> rightNephew = brother==null?null:brother.getRight();
        //N代指node
        if(myRoot == parent){
            System.out.println("情况3.1");
            //子情况1. N是根节点，则不需要调整。
            return;
        }else if(parent.getColor()==BLACK&&brother!=null&&brother.getColor()==BLACK&&(leftNephew==null||leftNephew.getColor()==BLACK)&&(rightNephew==null||rightNephew.getColor()==BLACK)){
            //子情况2. N的父亲、兄弟、侄子都是黑色，则将兄弟变为红色，父亲视作N，进行递归处理。
            System.out.println("情况3.2");
            brother.setColor(RED);
            deleteHelper(parent);
        }else if(brother!=null&&brother.getColor()==RED&&parent.getLeft()==node){
            System.out.println("情况3.3.1");
            //子情况3.1 N的兄弟节点是红色，且N为父亲节点左儿子，则以父亲节点为轴左旋，并将旋转后N的祖父节点(原兄弟)变为黑色，N的父节点(原父节点)变为红色
            leftRotate(parent);
            brother.setColor(BLACK);
            parent.setColor(RED);
            deleteHelper(node);
        }else if(brother!=null&&brother.getColor()==RED&&parent.getRight()==node){
            System.out.println("情况3.3.2");
            //子情况3.2 N的兄弟节点是红色，且N为父亲节点右儿子，则以父亲节点为轴右旋，并将旋转后N的祖父节点(原兄弟)变为黑色，N的父节点(原父节点)变为红色
            rightRotate(parent);
            brother.setColor(BLACK);
            parent.setColor(RED);
            deleteHelper(node);
        }else if(parent.getColor()==RED&&brother!=null&&brother.getColor()==BLACK&&(leftNephew==null||leftNephew.getColor()==BLACK)&&(rightNephew==null||rightNephew.getColor()==BLACK)){
            //子情况4. N的父亲节点是红色，兄弟和侄子节点是黑色，父亲节点变为黑色，兄弟节点变为红色。
            System.out.println("情况3.4");
            parent.setColor(BLACK);
            brother.setColor(RED);
        }else if(brother!=null&&parent.getRight()==brother&&leftNephew!=null&&leftNephew.getColor()==RED&&(rightNephew==null||rightNephew.getColor()==BLACK)){
            //子情况5.1 父节点颜色随意，兄弟节点为父节点黑色右孩子，左侄子节点为红色，右侄子节点为黑色，以兄弟节点为轴进行右旋
            // 将旋转后N的兄弟节点(原左侄子节点)变为黑色，N的右侄子节点（原兄弟节点）变为红色
            System.out.println("情况3.5.1");
            rightRotate(brother);
            leftNephew.setColor(BLACK);
            brother.setColor(RED);
            deleteHelper(node);
        }else if(brother!=null&&parent.getLeft()==brother&&rightNephew!=null&&rightNephew.getColor()==RED&&(leftNephew==null||leftNephew.getColor()==BLACK)){
            //子情况5.2 父节点颜色随意，兄弟节点为父节点黑色左孩子，右侄子节点为红色，左侄子节点为黑色，以兄弟节点为轴进行左旋
            // 将旋转后N的兄弟节点(原右侄子节点)变为黑色，N的左侄子节点（原兄弟节点）变为红色
            System.out.println("情况3.5.2");
            leftRotate(brother);
            rightNephew.setColor(BLACK);
            brother.setColor(RED);
            deleteHelper(node);
        }else if(parent.getRight()==brother&&brother!=null&&brother.getColor()==BLACK&&rightNephew!=null&&rightNephew.getColor()==RED){
            //子情况 6.1 N的父节点随意，兄弟节点为父节点的黑色右儿子，右侄子节点为红色，以N的父节点为轴进行左旋
            // 左旋后的N的祖父节点（原兄弟）变为父节点颜色，父节点（原父节点）变黑，叔叔节点（原右侄子）变黑。
            System.out.println("情况3.6.1");
            leftRotate(parent);
            brother.setColor(parent.getColor());
            parent.setColor(BLACK);
            rightNephew.setColor(BLACK);
        }else if(parent.getLeft()==brother&&brother!=null&&brother.getColor()==BLACK&&leftNephew!=null&&leftNephew.getColor()==RED){
            //子情况 6.2 N的父节点随意，兄弟节点为父节点的黑色左儿子，左侄子节点为红色，以N的父节点为轴进行右旋
            // 右旋后的N的祖父节点（原兄弟）变为父节点颜色，父节点（原父节点）变黑，叔叔节点（原左侄子）变黑。
            System.out.println("情况3.6.2");
            rightRotate(parent);
            brother.setColor(parent.getColor());
            parent.setColor(BLACK);
            leftNephew.setColor(BLACK);
        }
    }

    /**
     * 返回node的兄弟节点，如果node为null或父节点为null或父节点为根节点，则返回null
     * @param node
     * @return
     */
    private TreeNode<T> getBrother(TreeNode<T> node){
        if(node == null){
            return null;
        }
        TreeNode<T> parent = node.getParent();
        if(parent == null||parent==myRoot){
            return null;
        }
        return parent.getRight()==node?parent.getLeft():parent.getRight();
    }
    public TreeNode<T> get(T val){
        TreeNode<T> node = myRoot.getRight();
        while (node!=null&&node.getVal().compareTo(val)!=0){
            if(val.compareTo(node.getVal())>0){
                node = node.getRight();
            }else{
                node = node.getLeft();
            }
        }
        return node;
    }

    /**
     * 返回node加点右子树最小的节点，如果node为空返回null，node没有右子树，返回node
     * @param node
     * @return
     */
    private TreeNode<T> getSmallFromRight(TreeNode<T> node){
        if(node == null){
            return null;
        }
        if(node.getRight() == null){
            return node;
        }
        TreeNode<T> res = node.getRight();
        TreeNode<T> tmpNode = res.getLeft();
        while (tmpNode!=null){
            res = tmpNode;
            tmpNode = tmpNode.getLeft();
        }
        return res;
    }
    public void display(TreeNode<T> node){
        if(node == null){
            return;
        }
        PrintTree.print(node);
//        System.out.println("val:"+node.getVal()+" ,color:"+(node.getColor()?"black":"red"));
//        display(node.getLeft());
//        display(node.getRight());
    }
    public void displayAll(){
        display(myRoot.getRight());
    }

    /**
     * 返回node节点的孩子，如果node节点为null，或没有子节点则返回null
     * @param node
     * @return
     */
    public TreeNode<T> getChild(TreeNode<T> node){
        if(node == null){
            return null;
        }
        return node.getRight()==null?node.getLeft():node.getRight();
    }
    /**
     * 左旋
     * @param node 以node为轴左旋
     */
    public void leftRotate(TreeNode<T> node){
        TreeNode<T> right = node.getRight();
        TreeNode<T> parent = node.getParent();
        if(right == null){
            return;
        }
        TreeNode<T> grdSon = right.getLeft();
        right.setParent(parent);
        if(parent.getRight() == node){
            parent.setRight(right);
        }else{
            parent.setLeft(right);
        }
        node.setRight(grdSon);
        if(grdSon != null){
            grdSon.setParent(node);
        }
        node.setParent(right);
        right.setLeft(node);
    }

    /**
     * 右旋
     * @param node 以node为轴右旋
     */
    public void rightRotate(TreeNode<T> node){
        TreeNode<T> left = node.getLeft();
        if(left == null){
            return;
        }
        TreeNode<T> parent = node.getParent();
        TreeNode<T> rightNephew = left.getRight();
        left.setParent(parent);
        if(parent.getLeft() == node){
            parent.setLeft(left);
        }else{
            parent.setRight(left);
        }
        node.setLeft(rightNephew);
        if(rightNephew != null){
            rightNephew.setParent(node);
        }
        node.setParent(left);
        left.setRight(node);
    }
}
