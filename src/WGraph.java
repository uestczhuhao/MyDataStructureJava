import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by zhuhao on 17-4-21.
 * 带权值的图的实现
 * 图的有向无向的通过调用createEdges或者createEdgesDir来区分
 */
public class WGraph {

    /**
     * 最大顶点数
     */
    private final int MAX_VERTEX = 100;

    /**
     * 代表无穷大，即两个顶点之间没有边，在边有权值时用
     */
    private final int INFINITY = 65535;

    /**
     * 邻接矩阵
     */
    private int[][] adjacency;

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
    public WGraph(){}
    public WGraph(int n){
        checkRange ( n );

        adjacency = new int[n][n];
        vertex = new Object[n];
        numEdges = 0;
        numVertexs = n;
    }

    /**
     * 初始化一个图，这个图只有点没有边
     * 因此默认边数为0,此时的邻接矩阵的所有值为INFINITY，代表没有边
     * 时间复杂度为O(n^2)
     * @param n 图的规模
     * @param ob 每个顶点的值
     */
    public void createMGraph(int n,Object[] ob){
        checkRange ( n );

        adjacency = new int[n][n];
        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++){
                if(j==i)
                    adjacency[i][j] = 0;
                else
                    adjacency[i][j] = INFINITY;
            }
        vertex = new Object[n];
        System.arraycopy ( ob,0,vertex,0,n );
        numEdges =0;
        numVertexs =n;
//        for(int i=0;i<n;i++)
//            System.out.print (vertex[i]+"  ");
    }

    /**
     * 打印邻接矩阵
     */
    public void print(){
        if(numVertexs == 0)
            return;
        for (int i=0;i<numVertexs;i++)
            System.out.println ( Arrays.toString (adjacency[i]));
    }

    /**
     * 增加无向图的边，无向图的邻接矩阵是对称矩阵
     * @param start 开始结点
     * @param end  结束结点
     * @param weight 此边的权值
     */
    public void createEdges(int start,int end,int weight){
        adjacency[start][end] =weight;
        adjacency[end][start] =weight;
        numEdges+=2;
    }

    /**
     * 增加有向图的边，无向图的邻接矩阵不对称
     * @param start 开始结点
     * @param end  结束结点
     * @param weight 此边的权值
     */
    public void createEdgesDir(int start,int end,int weight){
        adjacency[start][end] =weight;
        numEdges++;
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
