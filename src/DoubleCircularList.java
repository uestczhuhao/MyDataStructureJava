import java.io.Serializable;

/**
 * Created by zhuhao on 17-4-18.
 * 双向循环列表的实现
 * 双向：即有前后两个指针
 * 循环：即尾指针指向头指针
 */
public class DoubleCircularList<E> implements Serializable {
    /**
     * 定义头节点
     */
    private Node head =new Node (  );

    /**
     * 定义尾节点
     */
    private Node tail = new Node (  );

    /**
     * 保存SingleLinkList的长度
     */
    private int size =-1;

    /**
     * 返回列表长度
     * @return int 当前列表长度
     */
    public int getSize(){
        return this.size;
    }

    /**
     * 判断当前列表是否为空
     */
    public boolean isEmpty(){
        return size==-1;
    }

    /**
     * 默认构造函数，构造一个空的双向循环列表
     */
    public DoubleCircularList(){
        head.prev = head;
        head.data = null;
        head.next = head;
        tail = head;
    }

    /**
     * 以可变参数初始化双向循环列表
     */
    public DoubleCircularList(E... e){
        for (int i=0;i<e.length;i++){

        }
    }

    /**
     * 在单链表的末尾增加元素
     * @param e 要增加的元素
     */
    public void addElement(E e){
        Node p =new Node (  );

        if (isEmpty ()){

        }
        else {

        }
    }

    /**
     * 内部节点，有前后两个指针指示前后节点
     * @ClassName Node
     */
    class Node{
        private Node prev; //前一个数据的指针
        private Object data; //数据
        private Node next; //后一个数据指针
        public Node(){

        }
        public Node(Node prev,Object data,Node next) {
            this.prev = prev;
            this.data = data;
            this.next = next;
        }
    }


    /**
     * 返回某元素在循环双列表的位置
     * @param e 搜索的元素
     * @return int 返回搜索元素的位置，若没有则返回-1
     */
    public int indexOf(E e) {
        if(e == null || e=="" ||e.equals ( null ) || e.equals ( "" ))
            return -1;
        else {
            Node p=head.next;
            for(int i=0;i<size+1;i++){
                if(p.data == e || p.data.equals ( e ))
                    return i;
                p=p.next;
            }
        }
        return -1;
    }
    /**
     * 判断索引的范围是否合法
     * @param index 需要判定的索引
     */
    private void checkRange(int index){
        if(index<0 || index>size){
            throw new ArrayIndexOutOfBoundsException (  );
        }
    }
}
