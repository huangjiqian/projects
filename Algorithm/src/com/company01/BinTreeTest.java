package com.company01;

public class BinTreeTest {
    public static void main(String[] args) {
        BinTree node = new BinTree();
        node.store(3);
        node.store(2);
        node.store(1);
        node.store(4);
        node.store(5);
        node.store(6);
        node.preList();
        //node.midList();
        //node.afterList();
    }
}
