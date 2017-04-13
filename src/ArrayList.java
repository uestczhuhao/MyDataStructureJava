import java.util.Arrays;


/**
 * 这是一个线性表的顺序存储结构，即顺序表。 这个类是一个顺序表数据结构的一般实现。
 *
 * @version 1.0
 * @start_time 2013-06-07
 * @end_time 2013-06-09
 * @author whw
 * @param <E>
 *
 */
public class ArrayList<E> implements java.io.Serializable{
    public static void main(String[] args) {
        System.out.println("Everything Normal!");
    }

    private static final long serialVersionUID = 1L;

    /**
     * 定义一个顺序结构的允许最大容量，这里参考了jdk源码的方法，官方文档解释说
     * Object[]的最大容量一般收到VMs的限制，推荐设置为Integer.MAX_VALUE-8
     */
    private static final int MAX_SIZE = Integer.MAX_VALUE-1;

    /**
     * 这个变量是保存着ArrayList的大小
     */
    private int size;

    /**
     * 这个变量保存ArrayList的实际存储长度，这个长度不一定等于初始化大小。
     * 而是实际存储了数据的长度。
     */
    private int currentSize = -1;

    /**
     * 这是一个存放ArrayList的数据的对象数组
     */
    private Object[] elementData;

    /**
     * 默认初始化方法，在jdk源码里 默认初始化方法时 默认设置Object的初始大小为10
     * 这里的实现也参考了jdk源码的初始化设置
     */

    public ArrayList(){
        this(10);
    }

    /**
     * ArrayList初始化构造器，这个初始化方法要求使用者指定构造该ArrayList的初始化大小initCapacity.
     * @param initCapacity 初始化容量
     */
    public ArrayList(int initCapacity){
        if(initCapacity < 0)
            throw new ExceptionInInitializerError();
        if(initCapacity > MAX_SIZE)
            throw new ArrayIndexOutOfBoundsException();

        this.elementData = new Object[initCapacity]; // 初始化数组
        this.size = initCapacity; // 同时初始化ArrayList的大小
    }

    /**
     * 确保所需的ArrayList容量没有超过初始化的容量
     * @param requireCapacity 所需的容量
     */
    private void ensureCapacity(int requireCapacity){
        if(requireCapacity < 0)
            throw new ExceptionInInitializerError();
        if(requireCapacity >= this.size){
            int newCapacity = requireCapacity+10;  // 每次扩展容量只扩展10个单位的容量。
            if(newCapacity > MAX_SIZE){
                newCapacity = requireCapacity + (10 - (newCapacity - MAX_SIZE)); // 重新计算新的容量
            }
            this.size = newCapacity;
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }

    /**
     * 获得顺序表ArrayList的容量
     * @return int
     */
    public int getSize(){
        return this.size;
    }

    /**
     * 获得顺序表ArrayList的实际存储大小
     * @return int
     */
    public int getCurrentSize(){
        return this.currentSize+1;
    }

    /**
     * 判断顺序表ArrayList是否为空
     * @return boolean
     */
    public boolean isEmply(){
        return (this.currentSize==-1);
    }

    /**
     * 增加泛型的支持，使每个放入ArrayList容器的元素不会丢失其数据类型
     * @param index
     * @return E
     */
    @SuppressWarnings("unchecked")
    private E elementData(int index) {
        checkRange(index);

        return (E) elementData[index];
    }

    /**
     * 往顺序表ArrayList尾部添加元素的方法。
     * @param e 要添加的元素
     */
    public boolean add(E e){
        ensureCapacity(currentSize+1);
        elementData[currentSize+1] = e;
        currentSize++; // 更新顺序表实际存放数据的大小
        return true;
    }

    /**
     * 往顺序表ArrayList指定位置添加元素的方法。
     * @param e 要添加的元素
     * @param index 要添加到顺序表里的位置索引
     */
    public boolean addAtIndex(E e, int index){
        checkRange(index);

        ensureCapacity(currentSize+1);

        for(int i=currentSize; i>=index; i--){
            elementData[i+1] = elementData[i];
        }
        elementData[index] = e;
        currentSize++; // 更新顺序表实际存放数据的大小
        return true;
    }

    /**
     * 删除指定索引位置的ArraList里的元素
     * @param index 要删除的元素的索引
     * @return boolean
     */
    public boolean removeByIndex(int index){
        checkRange(index);

        elementData[index] = null;
        currentSize--; // 顺序表长度减一
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
        currentSize--; // 顺序表长度减一
        return true;
    }

    /**
     * 删除所有ArraList里的元素
     * @return boolean
     */
    public boolean removeAll(){
        for(int i=0; i<currentSize; i++){
            elementData[i] = null;
        }
        currentSize = -1;
        return true;
    }

    /**
     * 获取指定索引位置的ArrayList里的元素
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
    public boolean replaceByIndex(E e, int index){
        checkRange(index);
        elementData[index] = e;
        return true;
    }

    /**
     * 替换指定的元素
     * @param e1 替换前的元素
     * @param e2  替换后的元素
     * @return boolean 返回true表示替换成功，返回false则相反
     */
    public boolean replaceByElement(E e1, E e2){
        if(e1.getClass() != e2.getClass())
            throw new ClassCastException();
        int index = indexOf(e1);
        elementData[index] = e2;
        return true;
    }

    /**
     * 搜索指定元素是否包含在ArrayList里
     * @param e 要搜索元素
     * @return boolean  返回true表示ArrayList中包含指定元素，返回false则相反。
     */
    public boolean contains(Object e){
        return indexOf(e) != -1;
    }

    /**
     * 搜索指定元素在ArrayList里的索引位置
     * @param e 要搜索元素
     * @return int 返回该元素所在的索引，没有返回-1
     */
    public int indexOf(Object e){
        if(e==null || e=="" || e.equals(null) || e.equals(""))
            return -1;
        else {
            for(int i=0; i<=currentSize; i++)
                if(e.equals(elementData[i]) || e.hashCode()==elementData[i].hashCode())
                    return i;
        }
        return -1;
    }

    /**
     * 判断索引的范围
     * @param index 需要判断的索引
     */
    private void checkRange(int index){
        if(index<0 || index>currentSize)
            throw new ArrayIndexOutOfBoundsException();
    }

}