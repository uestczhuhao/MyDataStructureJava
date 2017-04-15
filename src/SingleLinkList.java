import java.io.Serializable;

/**
 * Created by zhuhao on 17-4-14.
 * 单链表的实现
 * 单链表的结构： head->a0->a1->...->an(tail)
 * 即：表头head的数据域为空，表尾tail的
 * 空链表： head->tail
 */
public class SingleLinkList<E> implements Serializable{

    /**
     * 保存SingleLinkList的长度
     */
    private int size=-1;
    /**
     * 定义头节点
     */
    private Node head = new Node (  );
    /**
     * 定义尾节点
     */
    private Node tail = new Node (  );

    /**
     *默认初始化方法，设置首尾节点都为空
     */
    public SingleLinkList(){
        head = new Node (  );
        tail = head;
    }

    /**
     * 以某个元素初始化单链表
     * @param e
     */
    public SingleLinkList(E e){
        tail.data = e;
        head.next = tail;
        size++;
    }

    /**
     * 返回单链表的长度
     */
    public int getSize(){
        return this.size+1;
    }


    /**
     * 在单链表的末尾增加元素
     *@param e 增加的元素
     */
    public void addElement(E e){
        Node p = new Node ();
        if (head==null){
            head= new Node ( e,null );
        }
        else {
            p.data=e;
            tail.next = p;
            tail = p;
        }
        size++;
    }

    /**
     * 在index处插入元素e
     * @param e
     * @param index
     */
    public void addElementAtIndex(E e,int index){
        checkRange ( index );

        Node p=head.next;
        for(int i=0;i<index;i++)
            p = p.next;
        Node s = null;
        s.data=e;
        s.next=p.next;
        p.next=s;
    }

    /**
     * 读取单链表的元素
     * @param index
     */
    public Node getElement(int index){
        int j=0;
        Node p;
        p = head.next;
        while (p.next!=null && j<index){
            p = p.next;
            j++;
        }
        if(p.next==null || j>index){
            throw new ArrayIndexOutOfBoundsException();
        }
        return p;
    }

    /**
     * 打印输出当前单链表的所有值
     */
    public void print(){
        if(this.size != -1){
            Node p=head;
            for(int i=0;i<size+1;i++){
                System.out.print (p.next.data+" ");
                if (i==size)
                    System.out.println ();
                p=p.next;
            }
        }
        else {
            System.out.println ("This is a empty SingleLinkList!");
        }
    }




    /**
     * 节点类为内部类，是单链表的基本组成单位，在内部完成增删该查等功能
     * @ClassName Node 节点
     */
    class Node{
        private Object data; //数据
        private Node next;  //下一个节点的指针
        public Node(){

        }
        public Node(E data,Node next){
            this.data=data;
            this.next=next;
        }
    }

    /**
     * 返回某元素在SingleLinkList的位置
     * @param e 搜索的元素
     * @return int 返回搜索元素的位置，若没有则返回-1
     */
    public int indexOf(E e) {
        if(e == null || e=="" ||e.equals ( null ) || e.equals ( "" ))
            return -1;
        else {
            Node p=head;
            for(int i=0;i<size+1;i++){
                if(p.data == e)
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
