/**
 * Created by zhuhao on 17-4-22.
 * Queue() 初始化队列
 * clear() 清空队列
 * isEmpty()  判断队列是否为空
 * getHead()  若队列不为空，则返回队首元素
 * enQueue(Object e)插入新元素到队列并成为队尾元素
 * deQueue() 删除Q中队头元素，并返回其值e
 * getLength()  返回队列的长度
 * print()  打印输出整个队列的内容
  */
public class Queue {

    /**
     * 队列的长度
     */
    private int size;

    /**
     * 头结点，其数据域为空，指针域指向队头
     */
    private Node front;
    /**
     * 队尾，队列为空时其数据域为空，否则它就是an
     */
    private Node rear;

    /**
     * 初始化构造函数
     */
    public Queue(){
        size =-1;
        front = new Node (  );
        rear = front;
    }

    /**
     * 返回队列的长度
     */
    public int getSize(){
        return this.size+1;
    }

    /**
     * 判断队列是否为空
     */
    public boolean isEmpty(){
        return this.size == -1;
    }

    /**
     * 若队列不为空，返回头队头
     * 注意不是返回头结点
     */
    public Node getHead(){
        if(!isEmpty ())
            return this.front.next;
        return null;
    }

    /**
     * 清空队列
     */
    public void clear(){

    }

    /**
     * 插入新元素到队列并成为队尾元素
     */
    public void enQueue(Object ob){
        if(isEmpty ()){
            rear = new Node (  );
            rear.data = ob;
            rear.next = null;
            front.next = rear;
            this.size++;
        } else {
            Node p = new Node (  );
            p.data = ob;
            p.next = null;
            rear.next = p;
            rear = p;
            this.size++;
        }
    }

    /**
     * 删除Q中队头元素，并返回其值e
     */
    public Object deQueue(){
        if(isEmpty ())
            return null;
        else if(this.size == 0){
            Object ob = rear.data;
            rear.data = null;
            rear = front;
            this.size--;
            return ob;
        } else {
            Node p = front.next;
            Object ob = p.data;
            Node p_next = p.next;
            p.data = null;
            p.next = null;
            front.next = p_next;
            this.size--;
            return ob;
        }
    }

    /**
     * 打印输出整个队列
     */
    public void print(){
        if(isEmpty ())
            System.out.println ("");
        Node p = front.next;
        for (int i=0;i<=this.size;i++){
            System.out.print (p.data);
            p = p.next;
            if(i == this.size)
                System.out.println ();
            else System.out.print ("->");
        }
    }

    /**
     * 队列的链式存储结构，需要内部类Node来表示每一个节点
     */
    class Node {
        private Object data; //队列中的数据
        private Node next;  //下一个节点的指针
        public Node(){

        }
        public Node(Object data,Node next){
            this.data=data;
            this.next=next;
        }
    }
}
