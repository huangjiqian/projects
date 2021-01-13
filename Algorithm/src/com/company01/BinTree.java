package com.company01;

public class BinTree {
    private int value;
    private BinTree left;
    private BinTree right;

    //存储二叉树
    public void store(int value) {
        if (value < this.value) {//在该结点的左边
            if (left == null) {//该结点的左结点为空
                left = new BinTree();
                left.value = value;//新建它的左结点
            } else {//如果不为空则将左结点当作当前节点继续判断
                left.store(value);
            }
        } else if (value > this.value) {
            if (right == null) {
                right = new BinTree();
                right.value = value;
            } else {
                right.store(value);
            }
        }
    }

    //查找二叉树
    public boolean find(int value) {
        System.out.println("happen: " + this.value);//显示当前结点的值
        if (this.value == value) return true;
        else if (value < this.value) {//搜索的值小于当前值
            if (left == null) return false;
            return left.find(value);
        } else {
            if (right == null) return false;
            return right.find(value);
        }
    }

    //前序历遍
    public void preList() {
        System.out.print(this.value + ",");
        if (left != null) left.preList();
        if (right != null) right.preList();
    }

    //中序历遍
    public void midList() {
        if (left != null) left.midList();
        System.out.print(this.value + ",");
        if (right != null) right.midList();
    }

    //后序历遍
    public void afterList() {
        if (left != null) left.afterList();
        if (right != null) right.afterList();
        System.out.print(this.value + ",");
    }
}