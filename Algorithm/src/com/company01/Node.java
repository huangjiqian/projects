package com.company01;

/**
 * 双向链表
 */
public class Node {
    public Object o;
    public Node prev;
    public Node next;
    public Node(){

    }
    public Node(Object o){
        this.o=o;
        prev=null;
        next=null;
    }

}
