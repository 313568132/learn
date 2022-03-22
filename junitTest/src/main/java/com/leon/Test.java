package com.leon;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Test {

    public static void main(String[] args) {
        // lastPeople(43,3);
        // lastPeople2(43,3);
       // lastPeople3(43,3);
        int i = 1000/10;
        System.out.println(i);
    }
    private static void lastPeople3(int total, int k) {
        //初始化人数，人数排号从1开始；
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 1; i <= total; i++) {
            list.add(i);
        }
        //从第target（数组下标）个开始重新数数；
        int target = 0;
        while (!list.isEmpty()) {
            target = (target + k)%list.size();
            //当target=0时，target-1就是-1了，数组越界，其意思就是返回倒数第一个元素，即list.size()-1；
            if (target != 0) {
                System.out.print(list.get(target-1)+" ");
                list.remove(target-1);
                target--;
            }else {
                System.out.print(list.get(list.size()-1)+" ");
                list.remove(list.size()-1);
            }
        }

    }
    private static void lastPeople2(int n, int k) {
        int res = 0;
        for (int i = 2; i <= n; i++) {
            res = (res + k)%i;
        }
        System.out.print((res+1)+" ");
    }

    private static void lastPeople(int n, int k) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i<= n; i++){
            list.add(i);
        }
        List<Integer> list2 = new ArrayList<>();
        int r = 0;
        int i = 0;
        int d = 0;
        while (true){
            d = 0;
            i = 0;
            list2.clear();
            list2.addAll(list);
            r = r%k;
            for (Integer integer : list) {
                r++;
                i++;
                if(r%k == 0){
                    System.out.println(list2.get(i-1-d));
                    list2.remove(i-1-d);
                    d++;
                }
            }
            if(list2.size() == 1){
                System.out.println("最后一个位置: " + list2.get(0));
                return;
            }
            list.clear();
            list.addAll(list2);
        }
    }
}
