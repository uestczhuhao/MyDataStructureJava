import java.util.Arrays;

/**
 * Created by zhuhao on 17-4-20.
 */
public class Graph {
    /**
     * 最大顶点数
     */
    private final int MAX_VERTEX = 100;

//    /**
//     * 代表无穷大，即两个顶点之间没有边，在边有权值时用
//     */
//    private final int INFINITY = 65535;

    /**
     * 邻接矩阵
     */
    private int[][] adjcency;

    /**
     * 顶点数组，可以是String int 或其他数据类型
     */
    private Object[] vertex;
    /**
     * 当前的顶点数
     */
    private int numVertexs;
    /**
     * 当前的边数
     */
    private int numEdges;

    /**
     * 初始化构造函数
     */
    public Graph(){}
    public Graph(int n){
        checkRange ( n );

        adjcency = new int[n][n];
        vertex = new Object[n];
        this.numEdges =0;
        this.numVertexs =0;
    }

    /**
     * 初始化一个图，这个图只有点没有边
     * @param n 图的规模
     * @param ob 每个顶点的值
     */
    public void CreateMGraph(int n,Object[] ob){
        checkRange ( n );

        adjcency = new int[n][n];
        vertex = new Object[n];
        System.arraycopy ( ob,0,vertex,0,n );
        numEdges =0;
        numVertexs =n;
//        for(int i=0;i<n;i++)
//            System.out.print (vertex[i]+"  ");
    }

    /**
     * 增加图的边
     * @param start 开始结点
     * @param end  结束结点
     */
    public void CreateEdges(int start,int end){
        adjcency[start][end] =1;
        adjcency[end][start] =1;
    }

    /**
     * 打印邻接矩阵
     */
    public void print(){
        if (numVertexs == 0)
            return;
        for(int i=0;i<numVertexs;i++)
            System.out.println ( Arrays.toString (adjcency[i]));
    }

    /**
     * 判断n的范围是否合法
     * @param n
     */
    private void checkRange(int n){
        if(n<0 || n>MAX_VERTEX)
            throw new ArrayIndexOutOfBoundsException (  );
    }
}
