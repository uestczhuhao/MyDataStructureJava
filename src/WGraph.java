import java.lang.reflect.Array;
import java.nio.file.Path;
import java.util.Arrays;

/**
 * Created by zhuhao on 17-4-21.
 * 带权值的图的实现
 * 以邻接矩阵的形式实现图
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
     * 获取当前的边数
     * @return
     */
    public int getEdges(){
        return this.numEdges;
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
        numEdges++;
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
     * 利用Prim算法生成最小生成树
     */
    public void MiniSpanTree_Prim(){
        int min,i,j,k;
        /*
        * adjvex数组的更新时机：当邻接矩阵有值小于lowcost的某个值
        * 假设此时循环到j值，即adjvex[j] = k，其中k为上次最小值对应的下标，
        * 表达j处的最小值是k这个结点提供的
        * 等到下次再更新min时对应的k值是最新的最小值下标，
        * 因此（adjvex[k],k）就是最新的权值最小值所对应的边
        */
        int[] adjvex= new int[numVertexs]; //保存相关顶点下标
        int[] lowcost= new int[numVertexs]; //保存相关顶点之间边的权值
        lowcost[0] = 0; //初始化第一个权值为0,即V0加入生成树。lowcost[i]=0代表顶点i加入最小生成树
        adjvex[0] = 0; //初始化第一个顶点下标为0

        for(i=1;i<numVertexs;i++){
            lowcost[i] = adjacency[0][i]; //初始化lowcost为adjacency的第一行，即与v0相关的权值
            adjvex[i] = 0;  //初始化都为V0的下标
        }

        for(i=1;i<numVertexs;i++){
            min = INFINITY; //初始化最小权值为INFINITY
            j=1;k=0; //j是用来做顶点下标循环的变量，k是用来存储最小权值的顶点下标
            while (j<numVertexs){
                if(lowcost[j]!=0 && lowcost[j]<min){
                    //权值不为0且小于min时，更新min的值
                    min = lowcost[j];
                    k=j; //当前最小值的下标存入k
                }
                j++;
            }
            System.out.print ("("+adjvex[k]+","+k+")" + " ");
            lowcost[k] = 0; //将当前顶点的权值设置为0,表示此顶点已经完成任务
            for (j=1;j<numVertexs;j++){
                if(lowcost[j]!=0 && adjacency[k][j] < lowcost[j]){
                    //若下标为k顶点各边的权值小于此前这些顶点未被加入生成树的权值
                    lowcost[j] = adjacency[k][j]; //将较小的权值存入lowcost
                    adjvex[j] = k; //将下标为k的顶点存入adjvex数组
                }
            }
        }
    }

    /**
     * Kruskal算法生成最小生成树
     */
    public void MiniSpanTree_Kruskal(){
        int i,j,n,m;
        int k=0;
        Edge[] edges =new Edge[numEdges]; //定义边集数组
        int[] parent = new int[numVertexs]; //定义一维数组，判断边与边是否形成回路
        for (i=0;i<numVertexs;i++)
        {
            for(j=i+1;j<numVertexs;j++){
                if (adjacency[i][j]!= 0 && adjacency[i][j]!=INFINITY){
                    edges[k] = new Edge();
                    edges[k].start = i;
                    edges[k].end = j;
                    edges[k].weight = adjacency[i][j];
                    k++;
                }
            }
        }

        for(i=0;i<numEdges;i++){
            for(j=i+1;j<numEdges;j++){
                if(edges[i].weight > edges[j].weight){
                    Edge temp = new Edge ();
                    temp = edges[i];
                    edges[i] = edges[j];
                    edges[j] = temp;
                }
            }
        }

        for(i=0;i<numEdges;i++){
            n=Find(parent,edges[i].start);
            m = Find ( parent,edges[i].end );
            if(n!=m){
                //如果n和m不相等，则此边没有与现有生成树形成环路
                parent[n]=m; //此边的结尾顶点放在下标为起点的parent中，表示此顶点夜景在生成树集合中
                System.out.println (edges[i].start+" "+edges[i].end+" "+edges[i].weight);
            }
        }
//        for(i=0;i<numEdges;i++)
//            System.out.println (edges[i].weight);
    }

    private int Find(int[] parent,int f){
        while (parent[f]>0){
            f=parent[f];
        }
        return f;
    }

    /**
     * Dijkstra算法，求图的V0顶点到其余结点V最短路径P[v]及带全长度D[v]
     * P[v]的值为前驱顶点下标，D[v]表示V0到V的最短长度和
     */

    public void shortestPath_Dijkstra(int v0){
        int v,w,k=0,min;
        int[] Pathmatrix = new int[numVertexs];  //存储最短路径下标的数组
        int[] ShortPathTable = new int[numVertexs];  //存储到个点最短路径的权值和
        int[] _final = new int[numVertexs]; // _final[w]=1表示求得顶点V0到Vw的最短路径

        for( v=0; v<numVertexs;v++){
            _final[v] =0 ;  //全部顶点初始化为未知最短路径状态
            ShortPathTable[v] = adjacency[v0][v]; //将与V0点有连线的顶点加上权值
            Pathmatrix[v] = 0; //初始化路径数组Pathmatrix为0
        }
        ShortPathTable[v0] =0; //V0至V0路径为0
        _final[v0] =1; //v0至v0不需求路径
        //开始主循环，每次求得v0到某个v顶点的最短路径
        for(v=1;v<numVertexs;v++){
            min = INFINITY; //当前所知离v0顶点的最近距离
            for(w=0;w<numVertexs;w++){
                //寻找离v0最近的顶点
                if( _final[w]==0 && ShortPathTable[w]<min){

                    k =w;
                    min = ShortPathTable[w]; //w顶点离v0顶点更近
                }
            }
            _final[k] =1; //将目前找到的最近的顶点置为1
            for(w=0;w<numVertexs;w++) { //修正当前最短路径和距离
                //如果经过v顶点的路径比现在这条路径的长度短的话
                if(_final[w]==0 && ((min+adjacency[k][w])<ShortPathTable[w])){
                    //说明找到了更短路径，修改ShortPathTable[w]和Pathmatrix[w]
                    ShortPathTable[w] = min+adjacency[k][w];
                    Pathmatrix[w] = k;
                }
            }
        }

        System.out.println (Arrays.toString ( Pathmatrix ));
        System.out.println (Arrays.toString ( ShortPathTable ));

    }

    /**
     * Floyd算法，求图G中各顶点v到其余顶点w最短路径P[v][w]及带权长度D[v][w]
     */
    public void ShortestPath_Floyd(){
        int v,w,k = 0;
        int[][] Pathmatrix = new int[numVertexs][numVertexs];  //存储最短路径下标的数组
        int[][] ShortPathTable = new int[numVertexs][numVertexs];  //存储到个点最短路径的权值和

        for(v=0;v<numVertexs;++v){
            //初始化Pathmatrix和ShortPathTable
            for(w=0;w<numVertexs;w++){
                ShortPathTable[v][w] = adjacency[v][w]; //ShortPathTable[v][w]值即为对应点之间的权值
                Pathmatrix[v][w] = w; //初始化Pathmatrix
            }
        }

        for(k=0;k<numVertexs;k++){
            for(v=0;v<numVertexs;v++){
                for(w=0;w<numVertexs;w++){
                    if(ShortPathTable[v][w]>ShortPathTable[v][k]+ShortPathTable[k][w]){
                        //如果经过下标为k顶点路径比原两点间的路径更短
                        //将当前两点间权值设为更小的一个
                        ShortPathTable[v][w] = ShortPathTable[v][k] + ShortPathTable[k][w];
                        Pathmatrix[v][w] = Pathmatrix[v][k]; //路径设置经过下标为k的顶点
                    }
                }
            }
        }

        for(int i=0;i<numVertexs;i++){
            System.out.println (Arrays.toString ( Pathmatrix[i] ));
        }
        for(int i=0;i<numVertexs;i++){
            System.out.println (Arrays.toString ( ShortPathTable[i] ));
        }
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
     * 边集类，包括起点，终点和权重
     */

    class Edge{
        private int start;
        private int end;
        private int weight;
        public Edge(){
            start=0;
            end = 0;
            weight =0;
        }
    }
}
