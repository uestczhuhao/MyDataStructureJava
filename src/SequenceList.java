import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by zhuhao on 17-4-12.
 * 顺序列表的实现
 * @version 1.0
 */
public class SequenceList<E> implements Serializable{

    /**
     * 定义一个顺序结构的允许的最大容量，这里参考了jdk源码的方法，官方文档解释说
     * Object[]的最大容量一般收到VMs的限制，推荐设置为Integer.MAX_VALUE-8
     */
    private static final int MAX_SIZE =Integer.MAX_VALUE-8;
    /**
     * 保存这SequenceList的大小
     */
    private static int size;
    /**
     * 保存SequenceList的实际存储长度，其长度不一定等于初始化大小，而是实际存储了数据的长度。
     */
    private int currentSize = -1;
    /**
     * 存放SequenceList的数据的对象数组
     */
    private Object[] elementData;

    /**
     * 默认初始化方法，在jdk源码里 默认初始化方法时 默认设置Object的初始大小为10
     * 这里的实现也参考了jdk源码的初始化设置
     */
    public SequenceList(){
        this (10);
    }

    /**
     * SequenceList初始化构造器，这个初始化方法要求使用者指定构造该SequenceList的初始化大小initCapacity.
     * @param initCapacity 初始化容量
     */
    public SequenceList(int initCapacity) {
        if(initCapacity<0)
            throw new ExceptionInInitializerError ();
        if(initCapacity>MAX_SIZE)
            throw new ArrayIndexOutOfBoundsException ();

        this.elementData = new Object[initCapacity];//初始化数组
        this.size = initCapacity; //同时初始化SequenceList的大小
    }

    /**
     * 确保所需的SequenceList容量没有超过初始化的容量
     * @param requireCapacity 所需的容量
     */
    private void ensureCapacity(int requireCapacity){
        if(requireCapacity<0)
            throw new ExceptionInInitializerError();
        if(requireCapacity >= this.size){
            int newCapacity = requireCapacity+10; //每次扩展容量只扩展10个单位的容量
            if(newCapacity > MAX_SIZE){
                newCapacity = requireCapacity + (10 - (newCapacity - MAX_SIZE)); // 重新计算新的容量
            }
            this.size = newCapacity;
            elementData = Arrays.copyOf (elementData,newCapacity);
        }
    }

    /**
     * 获得顺序表SequenceList的容量
     * @return int
     */
    public int getSize(){
        return this.size;
    }

    /**
     * 获取顺序表SequenceList的实际存储大小
     * @return int
     */
    public int getCurrentSize(){
        return this.currentSize+1;
    }

    /**
     * 输出顺序表的所有节点
     */
    public void print() {
        if(currentSize != -1){
            for(int i=0;i<currentSize+1;i++){
                System.out.print (elementData[i] + " ");
                if(i == currentSize)
                    System.out.println ();
            }
        }else {
            throw new ArrayIndexOutOfBoundsException (  );
        }
    }
    /**
     * 判断顺序表SequenceList是否为空
     * @return boolean
     */
    public boolean isEmply(){
        return (this.currentSize==-1);
    }

    /**
     * 增加泛型的支持，使每个放入SequenceList容器的元素不会丢失其数据类型
     * @param index
     * @return E
     */
    @SuppressWarnings("unchecked")
    private E elementData(int index){
        checkRange(index);

        return (E) elementData[index];
    }

    /**
     * 往顺序表SequenceList尾部添加元素的方法。
     * @param e 要添加的元素
     */
    public boolean add(E e){
        ensureCapacity ( currentSize+1 );
        elementData[currentSize+1] = e;
        currentSize++;
        return true;
    }

    /**
     * 往顺序表SequenceList指定位置添加元素的方法。
     * @param e 要添加的元素
     * @param index 要添加到顺序表里的位置索引
     */
    public boolean addAtIndex(E e,int index){
        checkRange(index);

        ensureCapacity ( currentSize+1 );

        for(int i=currentSize;i>=index;i--){
            elementData[i+1] = elementData[i];
        }
        elementData[index] = e;
        currentSize++; // 更新顺序表实际存放数据的大小
        return true;
    }

    /**
     * 删除指定索引位置的列表中的元素
     * @param index 要删除的元素的索引
     * @return boolean
     */
    public boolean deleteByIndex(int index){
        checkRange ( index );

        for(int k=index;k<=currentSize;k++){
            elementData[k-1] = elementData[k];
        }
        currentSize--;
        return true;
    }

    /**
     * 删除指定索引位置的列表中的元素
     * @param e 要删除的元素
     * @return boolean
     */
    public boolean deleteByElement(E e){
        int index = indexOf ( e );

        for(int k=index;k<=currentSize;k++){
            elementData[k-1] = elementData[k];
        }
        currentSize--;
        return true;
    }

    /**
     * 删除指定索引位置的ArraList里的元素
     * @param index 要删除的元素的索引
     * @return boolean
     */
    public boolean removeByIndex(int index){
        checkRange(index);

        elementData[index]=null;
        currentSize--; //顺序表长度减一
        return true;
    }

    /**
     * 删除指定ArraList里的元素
     * @param e 要删除的元素
     * @return boolean
     */
    public boolean removeByElement(E e){
        int index = indexOf(e);
        elementData[index] = null;
        currentSize--;
        return true;
    }

    /**
     * 删除所有ArraList里的元素
     * @return boolean
     */
    public boolean removeAll(){
        for(int i=0;i<currentSize;i++){
            elementData[i] = null;

        }
        currentSize = -1;
        return true;
    }

    /**
     * 获取指定索引位置的SequenceList里的元素
     * @param index 要获取的元素的索引
     * @return E
     */
    public E get(int index){
        return (elementData(index));
    }

    /**
     * 替换指定位置的元素
     * @param e 要替换的元素
     * @param index  要替换的元素的索引
     * @return boolean 返回true表示替换成功，返回false则相反
     */
    public boolean replaceByIndex(E e,int index){
        checkRange(index);
        elementData[index]=e;
        return true;
    }

/**
     * 替换指定的元素
     * @param e1 替换前的元素
     * @param e2  替换后的元素
     * @return boolean 返回true表示替换成功，返回false则相反
 */
    public boolean replacaByElement(E e1,E e2){
        if(e1.getClass () != e2.getClass ())
            throw new ClassCastException ();
        int index = indexOf(e1);
        elementData[index]=e2;
        return true;
    }

    /**
     * 搜索指定元素是否包含在SequenceList里
     * @param e 要搜索元素
     * @return boolean  返回true表示SequenceList中包含指定元素，返回false则相反。
     */
    public boolean contains(Object e){
        return indexOf(e)!=-1;
    }

    /**
     * 搜索指定元素在SequenceList里的索引位置
     * @param e 要搜索元素
     * @return int 返回该元素所在的索引，没有返回-1
     */
    public int indexOf(Object e){
        if(e==null || e=="" || e.equals (null) || e.equals ("")){
            return -1;
        }
        else {
            for(int i=0;i<currentSize;i++){
                if(e.equals ( elementData[i] ) || e.hashCode ()==elementData[i].hashCode ())
                    return i;
            }
        }
        return -1;
    }

    /**
     * 判断索引的范围
     * @param index 需要判断的索引
     */
    private void checkRange(int index){
        if(index<0 || index>currentSize)
            throw new ArrayIndexOutOfBoundsException (  );
    }

}