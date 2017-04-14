/**
 * Created by zhuhao on 17-4-12.
 */
public class Test {
    public static void main(String[] args) {
        SequenceList List = new SequenceList();
        for (int i=0;i<10;i++){
            List.add(i);

        }
        List.print (  );
//        List.removeByElement (16 );
//        System.out.println (List.get (6 ));
        System.out.println("The Current Size is: "+List.getCurrentSize ());
        System.out.println(List.getSize ());
    }
}
