package com.myprojects.jvm.jmm;

import java.util.concurrent.TimeUnit;

/**
 * CPU为了高效，会让没有依赖的指令乱序执行
 */
public class T01_Disorder {

    static int x=0,y=0,a=0,b=0;
    static long count=0;

    public static void main(String[] args) {
        for(;;) {
            x=0;y=0;a=0;b=0;
            count++;
            Thread t1 = new Thread(() -> {
                try {
                    //由于线程t1先启动，下面这句话让它等一等线程t2
                    TimeUnit.NANOSECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                b = 1;
                x = a;
            });
            Thread t2 = new Thread(() -> {
                a = 1;
                y = b;
            });
            t1.start();
            t2.start();
            try {
                t1.join();
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (x == 0 && y == 0) {
                System.out.println("第"+count+"次,x==0 && y==0");
                break;
            }
        }
    }
}
