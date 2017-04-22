import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by zhuhao on 17-4-21.
 * 邻接表（Adjacency List）表示图
 * 有权值+无向+有向
 */
public class ALGraph {
    /**
     * 最大顶点数
     */
    private final int MAX_VERTEX = 100;

    /**
     * 代表无穷大，即两个顶点之间没有边，在边有权值时用
     */
    private final int INFINITY = 65535;

    /**
     * 顶点数组，数据类型为VertexNode，包括数据域和指针域
     */
    private VertexNode[] vertex;

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
    public ALGraph(){}
    public ALGraph(int n){
        checkRange ( n );

        vertex = new VertexNode[n];
        this.numEdges =0;
        this.numVertexs =n;
    }

    /**
     * 创建顶点表，每一个顶点由数据域和指针构成
     * @param n  初始化顶点的数量
     * @param ob 顶点的值，如：V0,V1,..Vn或A,B,..
     */
    public void createVertexs(int n,Object[] ob){
        checkRange ( n );

        vertex = new VertexNode[n];
        for(int i=0;i<n;i++){
            vertex[i] = new VertexNode (  );
            vertex[i].data = ob[i];
            vertex[i].firstEdge=null;

        }
        this.numEdges =0;
        this.numVertexs =n;
    }

    /**
     * 根据输入无向图边的起点和终点以及权值，建立边表
     * @param start 边的起点
     * @param end  边的终点
     */
    public void createEdges(int start, int end, int weight){
        if ((start<0 || start>=numVertexs) ||(end<0 || end>=numVertexs))
            throw new ArrayIndexOutOfBoundsException (  );

        EdgeNode edge = new EdgeNode ();
        edge.vexindex = start;
        edge.weight = weight;
        edge.next = vertex[end].firstEdge;
        vertex[end].firstEdge = edge;

        EdgeNode edge1 = new EdgeNode ();
        edge1.vexindex = end;
        edge1.weight = weight;
        edge1.next = vertex[start].firstEdge;
        vertex[start].firstEdge = edge1;
        numEdges++;
    }

    /**
     * 根据输入无向图边的起点和终点以及权值，建立边表
     * @param start 边的起点
     * @param end  边的终点
     */
    public void createEdgesDir(int start, int end, int weight){
        if ((start<0 || start>=numVertexs) ||(end<0 || end>=numVertexs))
            throw new ArrayIndexOutOfBoundsException (  );

        EdgeNode edge = new EdgeNode ();
        edge.vexindex = end;
        edge.weight = weight;
        edge.next = vertex[start].firstEdge;
        vertex[start].firstEdge = edge;
        numEdges ++;

    }

    /**
     * 打印输出邻接表的内容
     */
    public void print(){
        for(int i=0;i<numVertexs;i++){
            VertexNode verTemp = vertex[i];
            System.out.print (verTemp.data);
            if (verTemp.firstEdge != null){
                System.out.print ("->");
                EdgeNode edgeTemp = verTemp.firstEdge;
                while (edgeTemp != null){
                    System.out.print (vertex[edgeTemp.vexindex].data);
                    edgeTemp = edgeTemp.next;
                    if(edgeTemp!=null)
                        System.out.print ("->");
                }
                System.out.println ();
            }
        }
    }

    /**
     * dfs算法遍历
     * @param vert 开始遍历的起点
     */
    int count = 0;
    public void dfs(int vert){
        checkRange ( vert );

        if(visited == null){
            //visited==null代表图刚刚被初始化，因此需要初始化visited数组
            visited = new boolean[numVertexs];
            count=0;
        }
        if (count == 0){
            // count==0 代表第一次进入此函数，此时要初始化visited数组，所有的点都没有遍历过
            visited = new boolean[numVertexs];
        }

        visited[vert] = true;
        System.out.print (vertex[vert].data);
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

        EdgeNode p = vertex[vert].firstEdge;
        while (p != null){
            if(!visited[p.vexindex])
                dfs ( p.vexindex );
            p = p.next;
        }
    }

    /**
     * bfs 算法遍历
     * @param vert 开始遍历的起点
     */
    public void bfs(int vert){
        checkRange ( vert );
//        for(int k=0;k<numVertexs;k++)
//            System.out.println (vertex[k].firstEdge);
        Queue queue =new Queue ();
        int i;
        int count=0;
        visited = new boolean[numVertexs];
        for (i=vert;i<numVertexs;i++){
            if(!visited[i]){
                visited[i]=true;
                System.out.print (vertex[i].data + " ");
                //输出格式控制
                if (count != numVertexs-1)
                    System.out.print ("->");
                else System.out.println ();
                count++;

                queue.enQueue ( i );
                while (!queue.isEmpty ()){
                    i = (Integer)queue.deQueue ();
                    EdgeNode p = vertex[i].firstEdge;
                    while (p!=null){


//                        System.out.println ("Comming Here!");
                        if( !visited[p.vexindex]){
                            visited[p.vexindex] = true;
                            System.out.print (vertex[p.vexindex].data);
                            if (count!=numVertexs-1)
                                System.out.print ("->");
                            else System.out.println ();
                            count++;

                            queue.enQueue ( p.vexindex );
                        }
                        p = p.next;
                    }
                }
            }
        }

        //遍历完成后，恢复visited数组的初始化，即全是false
        for(i=0;i<numVertexs;i++){
            visited[i] = false;
        }

    }
    /**
     * 边表结点
     */
    class EdgeNode{
        private int vexindex; //邻接点域，存储该顶点对应的下标
        private int weight; //用于存储权值
        private EdgeNode next;  //指向下一个邻接点
        public EdgeNode(){}
    }
    /**
     * 顶点表结点，包括数据域和指针域
     */
    class VertexNode{
        private Object data;
        private EdgeNode firstEdge;
        public VertexNode(){

        }
        public VertexNode(Object data){
            this.data = data;
            this.firstEdge = null;
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

}
