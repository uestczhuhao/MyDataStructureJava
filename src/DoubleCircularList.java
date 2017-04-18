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
     * 在双向循环列表的末尾增加元素
     * @param e 要增加的元素
     */
    public void addElement(E e){
        Node p =new Node (  );

        if (isEmpty ()){
            p.prev = head;
            p.next = head;
            head.prev = p;
            head.next = p;
            p.data = e;
            head.data = null;
            tail = p;
            size++;
        }
        else {
            p.data = e;
            p.prev = tail;
            p.next = head;
            tail.next = p;
            tail = p;
            size++;
        }
    }

    /**
     * 在指定位置插入元素
     * 注意：不能在队尾插入元素，如果要在队尾插入，请使用addElement()函数
     * @param e  插入的元素
     * @param index  要插入的位置
     */
    public void addElementAtIndex(E e,int index){
        checkRange ( index );

        Node p =head;
        for (int i=0;i<index;i++){
            p=p.next;
        }
        //新节点插入在找到的p节点之后
        Node s = new Node (  );
        Node p_next = p.next;
        s.data = e;
        s.next = p_next;
        s.prev = p;
        p.next = s;
        p_next.prev = s;
        size++;
    }

    /**
     * 删除指定的元素值，若此元素不存在则报错ArrayIndexOutOfBoundsException
     * @param e
     */
    public void removeElement(E e){
        int index = indexOf ( e );
        checkRange ( index );

        removeElementAtIndex ( index );
    }

    /**
     * 删除指定位置的元素
     * @param index 要删除元素的位置
     */
    public void removeElementAtIndex(int index){
        checkRange ( index );


        if(index == size){
            Node p = tail.prev;
            p.next = head;
            head.prev = p;
            tail = p;
            size--;
        }
        else if(size == 0){
            head.next = head.next.next;
            head.next.prev = head;
            size--;
        }
        else {
            Node p =getNode(index);
            Node p_pre = p.prev;
            Node p_nex = p.next;
            p_pre.next = p_nex;
            p_nex.prev = p_pre;
            size--;
        }
    }

    /**
     * 获取在index位置的节点
     * @param index 要搜索的节点位置
     * @return 找到的节点
     */
    public Node getNode(int index){
        checkRange ( index );

        int j=0;
        Node p = head.next;
        for (;j<index;j++)
            p = p.next;
        return p;
    }

    /**
     * 打印输出当前列表的所有值
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
            System.out.println ("This is a empty DoubleCircularList!");
        }
    }

    /**
     * 清空整表
     */
    public void clear(){
        if(isEmpty ())
            return;
        else{
            Node p=head;
            for (int i=0;i<=size;i++){
                p.next.data=null;
                p.prev = null;
                p.next=p.next.next;
            }
            size=-1;
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
