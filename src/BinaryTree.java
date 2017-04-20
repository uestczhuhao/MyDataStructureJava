/**
 * Created by zhuhao on 17-4-20.
 */
public class BinaryTree {

    /**
     * 初始化的空根结点
     */
    private BiTNode root;

    /**
     * 二叉树树的结点个数
     */
    private int size = -1;

    public int getSize(){
        return this.size+1;
    }

    /**
     * 返回二叉树的根结点
     */
    public BiTNode getRoot(){
        return this.root;
    }

    /**
     * 前序遍历算法
     */
    public void PreOrderTraverse(BiTNode node) {
        if (node == null){
            return;
        }

        System.out.print (node.data+" ");
        PreOrderTraverse ( node.lChild );
        PreOrderTraverse ( node.rChild );
    }

    /**
     * 中序遍历算法
     */
    public void InOrderTraverse(BiTNode node) {
        if (node == null){
            return;
        }

        InOrderTraverse ( node.lChild );
        System.out.print (node.data+" ");
        InOrderTraverse ( node.rChild );
    }

    /**
     * 后序遍历算法
     */
    public void PostOrderTraverse(BiTNode node) {
        if (node == null){
            return;
        }

        PostOrderTraverse ( node.lChild );
        PostOrderTraverse ( node.rChild );
        System.out.print (node.data+" ");
    }

    /**
     * 以前序遍历算法创建一个二叉树
     * @param ob 输入前序遍历序列
     */
    public void CreateBiTree(BiTNode node, Object ob) {
        if(root == null){
            root = new BiTNode ( ob );
            size=0;
        }
        else {
            if(Math.random ()<0.5){
                //随机生成左子树或右指数
                if(node.lChild==null){
                    node.lChild = new BiTNode ( ob );
                    size++;
                }
                else
                    CreateBiTree ( node.lChild,ob );
            } else {
                if (node.rChild == null){
                    node.rChild = new BiTNode ( ob );
                    size++;
                }
                else
                    CreateBiTree ( node.rChild, ob );
            }
        }
    }

    /**
     * 返回二叉树是否为空树
     * @return 为空则返回true，否则返回false
     */
    public boolean isEmpty(){
        return size==-1;
    }
    /**
     * 内置类，表示树的结点
     * @ClassName BiTNode
     */
    class BiTNode{
        private Object data;
        private BiTNode lChild;
        private BiTNode rChild;
        public BiTNode(){

        }
        public BiTNode(Object data){
            this.data = data;
            this.lChild = null;
            this.rChild = null;
        }
    }
}
