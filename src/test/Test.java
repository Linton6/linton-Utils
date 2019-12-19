package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Date 2019/12/18 14:44
 * @
 */

public class Test {
    public static void main(String[] args) {


        Integer[] aa = {2,34,5,6};
        List<Integer> list = Arrays.asList(aa);
        System.out.println(list.get(3));
        list.remove(3);



    }
}

