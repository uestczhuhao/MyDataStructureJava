/**
 * Created by zhuhao on 17-4-12.
 */
public class Test {
    public static void main(String[] args) {
        SequenceList List = new SequenceList();
        for (int i=0;i<10;i++){
            List.add(i);

        }
        System.out.println(List.get (10));
    }
}
