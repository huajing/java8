/**
 * @Description TODO
 * @Author chenck
 * @Date 2022/4/17 15:43
 * @Version 1.0
 **/

public class TestOp {
    public static void main(String[] args) {
        int i = 16;
        printBinary(i);
        System.out.println();
        i = i>>1;
        printBinary(i);
        System.out.println();
        System.out.println(i);
    }

    public static void printBinary(int a){
        for (int i = 31; i >= 0; i--){
            System.out.print(((a >> i) & 1));
        }
    }
}
