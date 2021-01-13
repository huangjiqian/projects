package com.myprojects.s02;

import java.util.Random;

//删除所有指定的值
public class T02_DeleteGivenValue {

    public static class Node{
        public int value;
        public Node next;
        public Node(int value){
            this.value=value;
        }
    }

    public static Node removeValue(Node head,int value){
        while (head!=null && head.value == value){
            head=head.next;
        }
        Node prev=head;
        Node cur=head;
        while (cur != null){
            if(cur.value == value){
                prev.next=cur.next;
            }else{
                prev=cur;
            }
            cur=cur.next;
            //pre.next=cur;
        }
        return head;
    }

    //生成单链表
    public static Node generateLinkedList(int maxSize, int maxValue){
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
    public static void printLinkedList(Node head){
        System.out.print("Node [");
        while (head !=null){
            System.out.print(head.value+",");
            head=head.next;
        }
        System.out.println("]");
    }
    public static void main(String[] args) {
        int maxSize=10;
        int maxValue=10;
        Node head=generateLinkedList(maxSize,maxValue);
        printLinkedList(head);
        //int value=new Random().nextInt(maxValue);
        head=removeValue(head,head.value);
        printLinkedList(head);
    }
}
