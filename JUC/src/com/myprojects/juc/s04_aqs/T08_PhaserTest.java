package com.myprojects.juc.s04_aqs;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * 类似于有多个CyclicBarrier
 */
public class T08_PhaserTest {

    private static MarriagePhaser phaser=new MarriagePhaser();

    static class MarriagePhaser extends Phaser {
        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            switch (phase) {
                case 0:
                    System.out.println("所有人到齐了"+registeredParties);
                    return false;
                case 1:
                    System.out.println("所有人吃饭"+registeredParties);
                    return false;
                case 2:
                    System.out.println("所有人离开"+registeredParties);
                    return false;
                case 3:
                    System.out.println("新郎新娘洞房"+registeredParties);
                    return true;
                default:
                    return true;
            }
        }
    }

    static class Person implements Runnable {
        String name;
        public Person(String name){
            this.name=name;
        }
        public void arrive(){
            millionSleep();
            System.out.printf("%s 到达现场！\n",name);
            //等待其他线程
            phaser.arriveAndAwaitAdvance();
        }
        public void eat(){
            millionSleep();
            System.out.printf("%s 吃饭！\n",name);
            phaser.arriveAndAwaitAdvance();
        }
        public void leave(){
            millionSleep();
            System.out.printf("%s 离开！\n",name);
            phaser.arriveAndAwaitAdvance();
        }
        public void room(){
            if(name.equals("新郎") || name.equals("新娘")) {
                millionSleep();
                System.out.printf("%s 洞房！\n", name);
                phaser.arriveAndAwaitAdvance();
            }else{
                //拒绝线程进入
                phaser.arriveAndDeregister();
            }
        }
        @Override
        public void run() {
            arrive();
            eat();
            leave();
            room();
        }
    }

    public static void millionSleep(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //设置线程数
        phaser.bulkRegister(7);
        for (int i = 0; i < 5; i++) {
            new Thread(new Person("person"+1)).start();
        }
        new Thread(new Person("新郎")).start();
        new Thread(new Person("新娘")).start();
    }
}
