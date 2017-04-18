import java.io.Serializable;

/**
 * Created by zhuhao on 17-4-18.
 * 栈的链式存储结构
 * LinkStack(*S) 初始化空栈
 * clearStack(*S):将栈清空
 * isEmpty(*S):若栈为空，返回true，否则返回false
 * getTop(*S):若栈存在且非空，用e返回S的栈顶元素
 * push(*S,e):若栈S存在，插入新元素e到栈S中并称为栈顶元素
 * pop(*S,*e):删除栈S中栈顶元素，并用e返回其值
 * getStackLength(S):返回S中的元素个数
 */
public class LinkStack<E> implements Serializable {

    public LinkStack(){
        this.top = new Node (  );
        this.size = -1;
    }

    /**
     * 保存栈的长度
     */
    private int size=-1;

    /**
     * 栈顶元素
     */
    private Node top=new Node (  );

    /**
     * 返回当前栈的大小
     */
    public int getStackLength(){
        return this.size+1;
    }

    /**
     * 判断栈是否为空
     */
    public boolean isEmpty(){
        return size==-1;
    }

    /**
     * 清空栈
     */
    public void clearStack(){
        if(size==-1)
            return;
        else{
            Node p = top;
            for (int i=0;i<=size;i++){
                p.data=null;
                p=p.next;
            }
            size=-1;
        }
    }

    /**
     * 打印输出当前栈的所有值
     */
    public void print(){
        if(this.size != -1){
            Node p=top;
            for(int i=0;i<size+1;i++){
                System.out.print (p.data+" ");
                if (i==size)
                    System.out.println ();
                p=p.next;
            }
        }
        else {
            System.out.println ("This is a empty LinkStack!");
        }
    }

    /**
     * 获取栈顶元素
     * @return 返回Object类型，为栈顶元素的数据值，但不弹出，栈顶元素依然在栈中
     */
    public Node getTop() {
        return this.top;
    }

    /**
     * 压入元素到栈中
     */
    public void push(Object e){
        Node p =new Node ();
        if (this.size == -1){
            top.data = e;
            top.next = null;
            size++;
        }
        else {
            p.data=e;
            p.next=top;
            top=p;
            size++;
        }

    }


    /**
     * 弹出栈顶元素
     * @return 返回Object类型，为栈顶元素的数据值，栈顶元素不再在栈中
     */
    public Object pop(){
        if(size == -1)
            throw new ArrayIndexOutOfBoundsException();
        Object ob = top.data;
        top.data = null;
        top=top.next;
        size--;
        return ob;
    }

    /**
     * 栈的链式存储结构，需要内部类Node来表示每一个节点
     */
    class Node {
        private Object data; //栈中的数据
        private Node next;  //下一个节点的指针
        public Node(){

        }
        public Node(Object data,Node next){
            this.data=data;
            this.next=next;
        }
    }
}
