package com.company01;

public class MyLinkListTest {
    public static void main(String[] args) {
        MyLinkList linkList = new MyLinkList();
        linkList.add(3);
        linkList.add(2);
        linkList.add(4);
        linkList.add(5);
        linkList.add(9);
        linkList.add(8);
        linkList.add(1);
        linkList.add(6);
        linkList.add(4);
        linkList.add(3);
        linkList.add(6);

        linkList.show();

        //System.out.println(linkList.add(3, 6));
        //linkList.show();
        //System.out.println(linkList.remove(4));
        //linkList.show();
        //linkList.quickSort();
        linkList.show();
    }
}
