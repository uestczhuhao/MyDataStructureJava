import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zhuhao on 17-4-12.
 */
public class Test {
    public static void main(String[] args) {
//        testSingleLinkList();
//        testDoubleCircularList ();
//        testLinkStack ();
//        testBinaryTree ();
//        testGraph ();
//        testWGraph ();
        testALGraph ();
//        testQueue ();
    }
    public static void testSingleLinkList(){
        SingleLinkList ListTest = new SingleLinkList ("ccc"  );

        for (int i=0;i<3;i++)
            ListTest.addElement ( i );
        ListTest.addElementAtIndex ( 444,0 );
//        SingleLinkList.Node p =ListTest.getPreNode ( 5 );
//        System.out.println (ListTest.getDataOfNode(p));
        ListTest.print ();
        ListTest.clear ();
        ListTest.print ();
//        System.out.println (ListTest.getSize ());
//        ListTest.removeElement ( 2 );
//        ListTest.print ();
//        System.out.println (ListTest.getSize ());
    }
    public static void testDoubleCircularList(){
        DoubleCircularList ListTest = new DoubleCircularList (  );
        ListTest.print ();
        ListTest.addElement ( 11 );
        ListTest.addElement ( 111 );
        ListTest.addElement ( 1111 );
        ListTest.addElementAtIndex ( 2,2 );
//        ListTest.removeElement ( 2 );
        ListTest.print ();
        ListTest.clear ();
        ListTest.print ();
    }
    public static void testQueue(){
        Queue queue = new Queue ();
        queue.enQueue ( "aaa" );
        queue.enQueue ( 123 );
        queue.enQueue ( 'q' );
        queue.enQueue ( 'b' );
        queue.enQueue ( 1222312 );
        System.out.println (queue.deQueue ());
        System.out.println (queue.deQueue ());
        System.out.println (queue.deQueue ());
        queue.print ();
    }
    public static void testLinkStack(){
        LinkStack stack = new LinkStack ();
        stack.print ();
        stack.push ( 1 );
        stack.push ( 2 );
        stack.push ( 3 );
        stack.pop();
        System.out.println (stack.getStackLength ());
        stack.pop();
        System.out.println (stack.getStackLength ());
        stack.pop();
        stack.print ();
    }
    public static void testBinaryTree(){
        BinaryTree tree = new BinaryTree ();
        String str = "ABCDEFG";
        char[] strTest = str.toCharArray ();
        for(int i=0;i<strTest.length;i++){
            tree.CreateBiTree ( tree.getRoot (),strTest[i] );
        }
        System.out.println (tree.getSize ());
        tree.PreOrderTraverse ( tree.getRoot () );
        System.out.println ();
        tree.InOrderTraverse ( tree.getRoot () );
        System.out.println ();
        tree.PostOrderTraverse ( tree.getRoot () );
    }
    public static void testGraph(){
        Graph graph = new Graph (  );
        createGraph ( graph );
        graph.print ();
//        graph.dfsTraverse ( 3 );
        graph.bfsTranverse ( 0 );
        graph.bfsTranverse ( 2 );
        graph.bfsTranverse ( 3 );
    }
    public static void testWGraph(){
        WGraph wgraph = new WGraph (  );
        createWGraph ( wgraph );
        wgraph.print ();

    }

    private static void createGraph(Graph graph){
        String[] str = {"aaa","bbb","ccc","ddd" };
        graph.createMGraph ( 4,str);
        //增加无向图的边
        graph.createEdges ( 1,0 );
        graph.createEdges ( 1,2 );
        graph.createEdges ( 2,0 );
        graph.createEdges ( 0,3 );
    }
    private static void createWGraph(WGraph wgraph){

        String[] str = {"V0","V1","V2","V3","V4"};
        wgraph.createMGraph ( 5,str );
        //增加无向图的边
        wgraph.createEdges ( 1,0,9 );
        wgraph.createEdges ( 1,2,3 );
        wgraph.createEdges ( 2,0,2 );
        wgraph.createEdges ( 2,3,5 );
        wgraph.createEdges ( 3,4,1 );
        wgraph.createEdges ( 0,4,6 );
    }

    public static void testALGraph(){
        ALGraph graph = new ALGraph (  );
        createALGraph ( graph );
//        graph.print ();
        graph.bfs ( 0 );
//        graph.bfs ( 1 );
//        graph.bfs ( 2 );
//        graph.bfs ( 3 );

//        createALGraphDir ( graph );
//        graph.print ();
    }

    private static void createALGraph(ALGraph graph){
        String[] str = {"V0","V1","V2","V3"};
        graph.createVertexs ( 4,str );
        //无向图增加边
        graph.createEdges ( 0,1,1 );
        graph.createEdges ( 0,2,1 );
        graph.createEdges ( 0,3,1 );
        graph.createEdges ( 1,2,1 );
        graph.createEdges ( 2,3,1 );
    }

    private static void createALGraphDir(ALGraph graph){
        String[] str = {"V0","V1","V2","V3"};
        graph.createVertexs ( 4,str );
        //有向图增加边
        graph.createEdgesDir ( 1,0,1 );
        graph.createEdgesDir ( 1,2,1 );
        graph.createEdgesDir ( 2,1,1 );
        graph.createEdgesDir ( 2,0,1 );
        graph.createEdgesDir ( 0,3,1 );
    }

}
