package com.company01;

public class MyLinkList {
    private Node head;
    private Node tail;
    private int size=0;

    public  MyLinkList(){
        head=new Node();
        tail=new Node();
        head.next=null;
        tail.prev=null;
    }

    public boolean isEmpty(){
        return size==0;
    }
    //添加的第一个结点
    public boolean addFirst(Node node){
        if(this.isEmpty()){
            head=node;
            tail=node;
            size++;
            return true;
        }
        return false;
    }
    //往链表末尾添加结点
    public void add(Object o){
        Node node = new Node(o);
        //判断是否为第一个结点
        if(!addFirst(node)) {
            Node rNode = head;
            while (rNode.next != null) {
                rNode = rNode.next;
            }

            tail.next=node;
            node.prev=tail;
            tail=tail.next;
            rNode.next=tail;
            size++;
        }
    }
    //往指定的索引添加结点
    public boolean add(int index,Object o){
        if(index < 0||index > this.size-1)
            return false;
        Node node=new Node(o);
        Node indexNode=find(index);
        /**
         * A<->B<->C
         * 在B后添加D,组成A<->B<->D<->C
         * 1.B的next指针指向C，赋给D，然后D的next指针指向C，完成A<->B D->C
         * 2.B的next指针指向C，C的prev指针赋给D，完成A<->B D<->C
         * 3.B的next指针指向改成D，完成A<->B->D<->C
         * 4.D的prev指针指向改成B，完成A<->B<->D<->C
         */
        node.next=indexNode.next;
        indexNode.next.prev=node;
        node.prev=indexNode;
        indexNode.next=node;
        size++;
        return true;
    }

    //根据索引寻找指定的结点
    public Node find(int index) {
        int temp;
        //若索引值小于总长度一半，选择从head往后开始寻找
        if(index<=this.size/2){
            Node rNode=head;
            temp=0;
            while(true){
                if(temp==index){
                    return rNode;
                }
                rNode=rNode.next;
                temp++;
            }
            //若索引值大于总长度一半，选择从tail往前开始寻找
        }else{
            Node lNode=tail;
            temp=size-1;
            while(true){
                if(temp==index){
                    return lNode;
                }
                lNode=lNode.prev;
                temp--;
            }
        }
    }

    public Object remove(int index){
        Node indexNode=this.find(index);
        Object ob=indexNode.o;
        if(index<0||index>size-1)
            return null;
        //若需要移除第一个结点，将head指向下一个结点，并将head的next和head.next.prev指针赋值为null
        if(index==0){
            head.next.prev=null;
            head=head.next;
            head.next=null;
            size--;
            return ob;
        }
        //若需要移除最后一个结点，将tail指向前一个结点，并将tail的prev和tail.prev.next指针赋值为null
        if(index==size-1){
            tail.prev.next=null;
            tail=tail.prev;
            tail.prev=null;
            size--;
            return ob;
        }
        //若结点不在head和tail，需要将次结点的前后结点互连,并将当前结点的next和prev指针赋值为null
        indexNode.prev.next=indexNode.next;//将前结点next指针指向后结点
        indexNode.next.prev=indexNode.prev;//将后结点prev指针指向前结点
        indexNode.next=null;
        indexNode.prev=null;
        size--;
        return ob;
    }

    //public boolean add(int index,MyList list){

    //}

    public void show(){
        Node tempTail=tail;
        Node tempHead=head;
        System.out.print("head:");
        while(tempHead!=null){
            System.out.print(tempHead.o + "->");
            tempHead = tempHead.next;
        }
        System.out.print("tail:");

        while(tempTail!=null){
            System.out.print(tempTail.o + "->");
            tempTail = tempTail.prev;
        }

        System.out.println();
    }

    /*public Node quickSort(){
        //Node pivot=head;
        Node start=head;
        Node end=tail;
        quick(start,end);
        return head;
    }

    private void quick(Node start, Node end) {
        if(start==null || end==null || start==end)
            return;
        Node pivot=sortOfQuick(start,end);
        quick(start,pivot.prev);
        quick(pivot.next,end);
    }

    private void swap(Node node1,Node node2){
        Node temp=node1;
        node1=node2;
        node2=temp;
    }
    private Node sortOfQuick(Node start, Node end) {
        Node pivot=start;
        while (start!=end){
            while (start!=end && end!=null && (Integer)end.o>=(Integer)pivot.o){
                end=end.prev;
            }
            //start.o=end.o;
            while (start!=end && start!=null && (Integer)start.o<=(Integer)pivot.o){
                start=start.next;
            }
            //end.o=start.o;
            swap(start,end);
            show();
            //start=start.next;
        }
        swap(start,pivot);
        return start;
    }*/
}
