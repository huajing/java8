
/**
 * @Description TODO
 * @Author chenck
 * @Date 2022/4/17 15:43
 * @Version 1.0
 **/

public class HashMapTest {
    public static void main(String[] args) throws InterruptedException {
        /*HashMap s = null;
        s.put(11,22);*/

        /*int h;
        Object key = new Object();
        System.out.println(key.hashCode());
        System.out.println(key.hashCode()>>>16);//17748
        System.out.println((key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16));

        System.out.println(key.hashCode()&(15));*/

       /* for (int i = 0; i < 100; i++) {
            System.out.println(new Object().hashCode());
        }*/
        /*int initialCapacity = (int)(300*0.75) + 1;
        System.out.println(initialCapacity);
        int i = initialCapacity + (initialCapacity >>> 1) + 1;
        System.out.println(i);
        int n = i - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;

        System.out.println(n+1);
        int a = 35, start = 1;
        while ((start = start << 1) < a){}

        System.out.println(start);*/
        int n = 16;
        System.out.println(n>>>1);
        System.out.println(n>>>2);
        System.out.println(n - (n >>> 2));


    }
}
