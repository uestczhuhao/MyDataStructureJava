import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by zhuhao on 17-4-20.
 * 简单的无权图实现
 * 图的有向无向的通过调用createEdges或者createEdgesDir来区分
 */
public class Graph {
    /**
     * 最大顶点数
     */
    private final int MAX_VERTEX = 100;


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
     * 记录顶点是否已经被访问过的数组
     */
    private boolean[] visited;

    /**
     * 初始化构造函数
     */
    public Graph(){}
    public Graph(int n){
        checkRange ( n );

        adjacency = new int[n][n];
        vertex = new Object[n];
        this.numEdges =0;
        this.numVertexs =n;
    }

    /**
     * 初始化一个图，这个图只有点没有边
     * @param n 图的规模
     * @param ob 每个顶点的值
     */
    public void createMGraph(int n,Object[] ob){
        checkRange ( n );

        adjacency = new int[n][n];
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
    public void createEdges(int start,int end){
        adjacency[start][end] =1;
        adjacency[end][start] =1;
        numEdges++;
    }

    /**
     * 增加有向图的边，无向图的邻接矩阵不对称
     * @param start 开始结点
     * @param end  结束结点
     * @param weight 此边的权值
     */
    public void createEdgesDir(int start,int end,int weight){
        adjacency[start][end] = 1;
        numEdges++;

    }

    /**
     * 打印邻接矩阵
     */
    public void print(){
        if (numVertexs == 0)
            return;
        for(int i=0;i<numVertexs;i++)
            System.out.println ( Arrays.toString (adjacency[i]));
    }

    /**
     * 判断n的范围是否合法
     * @param n
     */
    private void checkRange(int n){
        if(n<0 || n>MAX_VERTEX)
            throw new ArrayIndexOutOfBoundsException (  );
    }


    /**
     * dfs方法遍历图
     * @param vert 遍历起点
     */
    int count = 0;//起计数的作用，最后一个"->"不输出
    public void dfsTraverse(int vert){
        if(visited == null){
            //visited==null代表图刚刚被初始化，因此需要初始化visited数组
            visited = new boolean[numVertexs];
            count=0;
        }
        if (count == 0){
            // count==0 代表第一次进入此函数，此时要初始化visited数组，所有的点都没有遍历过
            visited = new boolean[numVertexs];
        }
//        System.out.println (Arrays.toString ( visited ));
       visited[vert]=true;
        System.out.print (vertex[vert]);
        if ( count != numVertexs-1)
            System.out.print ("->");
        else {
            /*当所有的结点遍历完成后，需要把数据还原
            * 包括把count置为0,输出换行符，
            * 而且马上返回，否则就会执行count++，与设计不符 */
            System.out.println ();
            count = 0;
            return;
        }
        count++;
        for(int j=0;j<numVertexs;j++){
            if (adjacency[vert][j] == 1 && !visited[j]){
               dfsTraverse (j);
            }
        }
    }


    /**
     * bfs方法遍历图
     * @param vert 遍历起点
     */
    public void bfsTranverse(int vert){
        checkRange ( vert );

        Queue queue = new Queue ();
        int i,j;
        int count =0;
        visited = new boolean[numVertexs];
        for(i=vert;i<numVertexs;i++){
            if(!visited[i]){
                visited[i] =true;
                System.out.print (vertex[i]);
                if (count != numVertexs-1)
                    System.out.print ("->");
                else System.out.println ();
                count++;

                queue.enQueue ( i );
                while (!queue.isEmpty ()){
                    i = (Integer)queue.deQueue ();

                    for(j=0;j<numVertexs;j++){
                        if(adjacency[i][j] == 1 && !visited[j]){
                            visited[j]=true;
                            System.out.print (vertex[j]);
                            if (count!=numVertexs-1)
                                System.out.print ("->");
                            else System.out.println ();
                            count++;
                            queue.enQueue ( j );
                        }
                    }
                }
            }
        }
        //遍历完成后，恢复visited数组的初始化，即全是false
        for(i=0;i<numVertexs;i++){
            visited[i] = false;
        }

    }
}
