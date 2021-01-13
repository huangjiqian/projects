package com.myprojects.s02;

import java.util.ArrayList;
import java.util.List;

public class T01_ReverseLinkedList {

    public static class Node{
        public Node next;
        public int value;
        public Node(int value){
            this.value=value;
        }
    }

    public static class DoubleNode{
        public DoubleNode next;
        public DoubleNode prev;
        public int value;
        public DoubleNode(int value){
            this.value=value;
        }
    }

    //单链表反转
    public static Node reverseLinkedList(Node head){
        if(head==null) return null;
        Node prev=null;
        Node cur=null;
        while (head != null){
            cur=head;
            head=head.next;
            cur.next=prev;
            prev=cur;
        }
        return prev;
    }

    //双联表反转
    public static DoubleNode reverseDoubleLinkedList(DoubleNode head){
        if(head==null) return null;
        DoubleNode prev=null;
        DoubleNode cur=null;
        while (head != null){
            cur=head;
            head=head.next;
            cur.next=prev;
            cur.prev=head;
            //head.prev=cur;
            prev=cur;
        }
        return prev;
    }

    //用来测试单链表反转
    public static Node testReverseLinkedList(Node head){
        if(head==null) return null;
        List<Node> list=new ArrayList<>();
        while (head!=null){
            list.add(head);
            head=head.next;
        }

        int N=list.size();
        list.get(0).next=null;
        for (int i = N-1; i >0; i--) {
            list.get(i).next=list.get(i-1);
        }

        /*for (int i = 1; i < N; i++) {
            list.get(i).next=list.get(i-1);
        }*/
        return list.get(N-1);
    }

    //用来测试双链表反转
    public static DoubleNode testReverseDoubleLinkedList(DoubleNode head){
        if(head==null) return null;
        List<DoubleNode> list=new ArrayList<>();
        while (head !=null){
            list.add(head);
            head=head.next;
        }
        //第一个节点一定要指向null，不然测试就会有环
        list.get(0).next=null;
        int N=list.size();
        for (int i = N-1; i > 0; i--) {
            list.get(i).next=list.get(i-1);
            list.get(i-1).prev=list.get(i);
        }
        return list.get(N-1);
    }

    //生成单链表
    public static Node generateLinkedList(int maxSize,int maxValue){
        int size= (int) ((maxSize+1) * Math.random());
        if(size==0) return null;
        Node head=new Node((int) ((maxValue+1) * Math.random()));
        size--;
        Node prev=head;
        while (size-- > 0){
            Node cur=new Node((int) ((maxValue+1) * Math.random()));
            prev.next=cur;
            prev=cur;
        }
        return head;
    }

    //生成双链表
    public static DoubleNode generateDoubleLinkedList(int maxSize,int maxValue){
        int size= (int) ((maxSize+1) * Math.random());
        if(size==0) return null;
        DoubleNode head=new DoubleNode((int) ((maxValue+1) * Math.random()));
        size--;
        DoubleNode prev=head;
        while (size-- > 0){
            DoubleNode cur=new DoubleNode((int) ((maxValue+1) * Math.random()));
            prev.next=cur;
            cur.prev=prev;
            prev=cur;
        }
        return head;
    }

    public static void printLinkedList(Node head){
        System.out.print("Node [");
        while (head !=null){
            System.out.print(head.value+",");
            head=head.next;
        }
        System.out.println("]");
    }
    public static void printDoubleLinkedList(DoubleNode head){
        System.out.print("DoubleNode ->[");
        while (head !=null){
            System.out.print(head.value+",");
            head=head.next;
        }
        System.out.println("]");
    }

    //有环别用这个函数
    public static boolean checkLinkedListEquals(Node head1,Node head2){
        head2=reverseLinkedList(head2);
        while (head1 != null && head2 != null){
            if(head1.value != head2.value) return false;
            head1=head1.next;
            head2=head2.next;
        }
        return (head1 == null) && (head2 == null);
    }

    public static boolean checkDoubleLinkedListEquals(DoubleNode head1,DoubleNode head2){
        head2=reverseDoubleLinkedList(head2);
        while (head1 != null && head2 != null){
            if(head1.value != head2.value) return false;
            head1=head1.next;
            head2=head2.next;
        }
        return head1==null && head2==null;
    }
    public static void main(String[] args) {
        int testTimes=10;
        int maxSize=10;
        int maxValue=10;
        for (int i = 0; i < testTimes; i++) {
            Node head=generateLinkedList(maxSize,maxValue);
            //printLinkedList(head);
            Node reserve1=reverseLinkedList(head);
            //printLinkedList(reserve1);
            Node reserve2=testReverseLinkedList(reserve1);
            //printLinkedList(reserve2);
            //System.out.println("==================================");
            if(!checkLinkedListEquals(reserve1,reserve2)){
                System.out.println("fail");
                break;
            }
        }
        for (int i = 0; i < testTimes; i++) {
            DoubleNode head=generateDoubleLinkedList(maxSize,maxValue);
            //printDoubleLinkedList(head);
            DoubleNode reserve1=reverseDoubleLinkedList(head);
            //printDoubleLinkedList(reserve1);
            DoubleNode reserve2=testReverseDoubleLinkedList(reserve1);
            //printDoubleLinkedList(reserve2);
            //System.out.println("==================================");
            if(!checkDoubleLinkedListEquals(reserve1,reserve2)){
                System.out.println("fail");
                break;
            }
        }
        /*DoubleNode head=generateDoubleLinkedList(maxSize,maxValue);
        //DoubleNode head1=head;
        printDoubleLinkedList(head);
        DoubleNode head1=reverseDoubleLinkedList(head);
        printDoubleLinkedList(head1);*/
        System.out.println("fantastic baby");
    }
}
