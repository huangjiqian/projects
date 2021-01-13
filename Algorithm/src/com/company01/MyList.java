package com.company01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyList {
    private static int size;
    private static int capacity;
    private static int[] params;

    public MyList(){
        size=0;
        capacity=16;
        params=new int[capacity];
    }

    private boolean addFirst(int param){
        if(size==0) {
            params[0] = param;
            size++;
            return true;
        }
        return false;
    }
    //添加元素
    public void add(int param){
        if(!addFirst(param)){
            if(size<capacity) {
                params[size++] = param;
            }else{
                params=reCapacity(capacity*2);
            }
        }
    }
    //根据索引添加元素
    public boolean add(int index,int param){
        if(index<0||index>index-1)
            return false;
        for (int i = index; i < index-1; i++) {
            params[i++]=params[i];
        }
        params[index]=param;
        return true;
    }
    //扩容，将原数组转到新数组
    private int[] reCapacity(int num) {
        //capacity=capacity*2;
        int[] tempParams=new int[num];
        for (int i = 0; i < size; i++) {
            tempParams[i]=params[i];
        }
        return tempParams;
    }
    //根据索引寻找元素
    public int get(int index){
        if(index<0||index>index-1)
            return -1;
        return params[index];
    }
    public int getindex(){
        return size;
    }
    public void show(){
        for (int i=0;i<size;i++) {
            System.out.print(params[i]+" ");
        }
        System.out.println();
    }
    private void swap(int index1,int index2){
//        params[index1]=params[index1]+params[index2];
//        params[index2]=params[index1]-params[index2];
//        params[index1]=params[index1]-params[index2];
        int temp=params[index1];
        params[index1]=params[index2];
        params[index2]=temp;
    }
    public boolean add(List<Integer> list){
        if(list == null)
            return false;
        int num=list.size();
        params=reCapacity(num);
        for (int i = 0; i < num; i++) {
            params[i+size]=list.get(i);
        }
        size+=num;
        return true;
    }
    public int[] getParams(){
        return params;
    }
    //各种排序算法实现
    /**
     * 1.冒泡排序:此排序最容易想到，但是耗费时间也是最多的，不建议使用
     * 时间复杂度：套双层循环，O(N^2),常数O(1)<对数O(logN)<幂O(N^2)<指数O(2^N)<阶乘O(N!)
     * 空间复杂度：int不占内存，没有new出来的对象，O(1)
     * 比较完之后直接交换数据，此排序稳定
     * 第一轮：
     * 2-5-6-4-1
     * 2-5-4-6-1
     * 2-5-4-1-6
     * 第二轮：6是排好序，不需要再判断
     * 2-4-5-1-6
     * 2-4-1-5-6
     * 第三轮：5,6是排好序，不需要再判断
     * 2-1-4-5-6
     * 第四轮：4,5,6是排好序，不需要再判断
     * 1-2-4-5-6
     */
    public void bubbleSort(){
        for (int i = 0; i < size-1; i++) {
            //j需要跟j+1比较，j的范围必须是index-1-i
            for (int j = 0; j < size-1-i; j++) {
                if(params[j]>params[j+1]){
                    //只能通过索引在方法内部交换
                    swap(j,j+1);
                }
            }
        }
    }

    /**
     * 2.选择排序:每次将右边比较完的最小数放在左边
     * 时间复杂度：套双层循环，O(N^2),常数O(1)<对数O(logN)<幂O(N^2)<指数O(2^N)<阶乘O(N!)
     * 空间复杂度：int不占内存，没有new出来的对象，O(1)
     * 需要比较完大小值然后交换数据，可能排序混乱，此排序不稳定
     * 第一轮：
     * 2-5-6-4-1
     * 1-5-6-4-2
     * 第二轮：
     * 1-2-6-4-5
     * 第三轮：
     * 1-2-4-6-5
     * 第四轮：
     * 1-2-4-5-6
     */
    public void selectionSort(){
        for (int i = 0; i < size-1; i++) {
            int minIndex=i;
            //j需要跟minIndex比较，j的范围必须是index
            for (int j = i+1; j < size; j++) {
                if(params[minIndex]>params[j]){
                    minIndex=j;
                }
            }
            swap(minIndex,i);
        }
    }

    /**
     * 3.希尔排序:不断细分进行排序，例如1和N/2比较，2和N/2+1比较。对于中等规模数据排序表现良好，比前两个排序快多了
     * 它与插入排序的不同之处在于，它会优先比较距离较远的元素。希尔排序又叫缩小增量排序
     * 时间复杂度：O(N^1.3),常数O(1)<对数O(logN)<幂O(N^2)<指数O(2^N)<阶乘O(N!)
     * 空间复杂度：int不占内存，没有new出来的对象，O(1)
     * 需要比较完大小值然后交换数据，可能排序混乱，此排序不稳定
     * 第一轮：N/2
     * 2-5-6-4-1-3 2,4比较
     * 2-1-6-4-5-3 1,5比较
     * 2-1-3-4-5-6 6,3比较
     * 第二轮：N/4
     * 1-2-3-4-5-6 1,2比较
     */
    public void shellSort(){
        for (int i = size/2; i > 0; i/=2) {
            for (int j = i; j < size; j++) {
                int current=params[j];
                int index=j;
                //将较大值往后移
                while (index-i>=0 && params[index-i]>current){
                    params[index]=params[index-i];//后移数据
                    index=index-i;//查找前一数据
                    //index= index-i;
                }
                //swap(j,index);
                params[index]=current;
            }
        }
    }

    /**
     * 4.归并排序：建立在归并操作上的有效稳定的排序算法，采用分治法，将已有序的子序列合并，得到完全有序的序列
     * 时间复杂度：O(NlogN),常数O(1)<对数O(logN)<幂O(N^2)<指数O(2^N)<阶乘O(N!)
     * 空间复杂度：new出来一维数组，O(N)
     * 比较完之后直接交换数据，此排序稳定
     */
    public void mergeSort(){
        int[] tempList=new int[size];
        sortOfMerge(tempList,0,size-1);
    }

    private void sortOfMerge(int[] tempList, int left, int right) {
        if(left>=right)
            return;
        int middle=(right+left)/2;
        sortOfMerge(tempList,left,middle);
        sortOfMerge(tempList,middle+1,right);
        merge(tempList,left,middle,right);
    }

    private void merge(int[] tempList, int left, int middle, int right) {
        int index=left;
        int p1=left;
        int p2=middle+1;
        while (p1 <= middle && p2 <= right){
            tempList[index++]=params[p1] > params[p2]?params[p2++]:params[p1++];
        }
        //左边剩余数据全部放进tempList
        while (p1 <= middle){
            tempList[index++]=params[p1++];
        }
        //右边剩余数据全部放进tempList
        while (p2 <= right){
            tempList[index++]=params[p2++];
        }
        for (int i = left; i < index; i++) {
            params[i]=tempList[i];
        }
    }

    /**
     * 5.快速排序:取一个数pivot（默认为第一个）作为界定值，索引end从末尾往前遍历，找到比pivot小的数，索引start从第一个往后遍历，找到比pivot大的数，
     * 将start和end交换，以此类推，然后start和end交汇，再将pivot和start交换。这样pivot将数组分成左右两个子数组，再对这两个子数组用同样方法排序。
     * 时间复杂度：O(NlogN),常数O(1)<对数O(logN)<幂O(N^2)<指数O(2^N)<阶乘O(N!)
     * 空间复杂度：不需要new数组，复杂度为O(1)
     * 需要先找到索引，再进行交换，排序不稳定
     */
    public void quickSort(){
        int start=0;
        //int pivot=start;//取第一个数作为界定值
        int end=size-1;
        quick(start,end);
    }

    private void quick(int start, int end) {
        if(start>end)
            return;
        int pivot;
        pivot=sortOfQuick(start,end);
        //左边排序
        quick(start,pivot-1);
        //右边排序
        quick(pivot+1,end);
    }

    //将数组快速排序
    private int sortOfQuick(int start, int end) {
        int pivot=start;
        while (start<end){
            //从右至左找到比pivot小的索引
            while (start<end && params[end]>=params[pivot]){
                end--;
            }
            //从左至右找到比pivot大的索引
            while (start<end && params[start]<=params[pivot]){
                start++;
            }
            swap(start,end);
        }
        //将pivot移到start与end交汇点
        swap(pivot,start);
        return start;
    }

    /**
     * 6.插入排序:也被称为直接插入排序。对于少量元素的排序，它是一个有效的算法.插入排序是一种最简单的排序方法，
     * 它的基本思想是将一个记录插入到已经排好序的有序表中，从而一个新的、记录数增1的有序表
     * 时间复杂度：套双层循环，O(N^2),常数O(1)<对数O(logN)<幂O(N^2)<指数O(2^N)<阶乘O(N!)
     * 空间复杂度：int不占内存，没有new出来的对象，O(1)
     * 比较完之后直接交换数据，此排序稳定
     */
    public void insertionSort(){
        for (int i = 1; i < size; i++) {
            int j=i-1;
            int current=params[i];
            while (j>=0 && params[j]>current){
                params[j+1]=params[j];
                j--;
            }
            params[j+1]=current;
        }
    }

    /**
     * 7.堆排序:升序用大顶堆，降序用小顶堆。大顶堆中父节点必须大于他的孩子结点。小顶堆中父节点必须小于他的孩子结点。
     * 此排序每次将调整好的大顶堆中根结点与末尾的数据交换，如此往复，直到根结点
     * 时间复杂度：O(NlogN),常数O(1)<对数O(logN)<幂O(N^2)<指数O(2^N)<阶乘O(N!)
     * 空间复杂度：int不占内存，没有new出来的对象，O(1)
     * 此排序不是相邻元素交换，所有不稳定
     */
    public void heapSort(){
        int bound=size-1;
        int parent=bound>>1;//parent=bound/2

        //heapOfRecursion(parent,bound);//递归方法
        heap(parent,bound);
    }

    /**
     * 此方法使用迭代
     */
    private void heap(int parent,int bound){
        while (bound>=0){
            sortOfHeap(parent,bound);
            bound--;
            parent=bound>>1;
        }
    }

    private void sortOfHeap(int parent, int bound) {
        while (parent-->0) {
            int lChild = (parent << 1) + 1;//lChild=parent*2+1
            int rChild = (parent << 1) + 2;//lChild=parent*2+2
            int max=parent;
            if (lChild <= bound) {
                if (params[max] < params[lChild]) {
                    //swap(parent, lChild);
                    max=lChild;
                }
            }
            if (rChild <= bound) {
                if (params[max] < params[rChild]) {
                    //swap(parent, rChild);
                    max=rChild;
                }
            }
            if (max != parent){
                swap(max,parent);
            }
            //在迭代中这个判断语句必须放在while循环里面
            if(parent==0){
                swap(parent,bound);
            }
        }
    }

    /**
     * 此方法使用递归，在数据量较大时，会出现StackOverflowError异常
     */
    private void heapOfRecursion(int parent, int bound){
        if (bound<0)
            return;
        //将较大值放到末尾
        sortOfRecursionHeap(parent,bound);
        //数组的边界变小
        bound--;
        //继续递归
        heapOfRecursion(bound>>1,bound);
    }
    //把堆分成最小的结构，只包含parent,lChild,rChild，三个结点,将三者最大值交换给parent
    private void sortOfRecursionHeap(int parent,int bound){
        if(parent<0)
            return;
        //使用移位操作后，至少能排序bound=10000的数组，<<和>>确实能减少内存的占用
        int lChild=(parent<<1)+1;//lChild=parent*2+1
        int rChild=(parent<<1)+2;//lChild=parent*2+2
        int max=parent;
        if (lChild <= bound) {
            if (params[max] < params[lChild]) {
                //swap(parent, lChild);
                max=lChild;
            }
        }
        if (rChild <= bound) {
            if (params[max] < params[rChild]) {
                //swap(parent, rChild);
                max=rChild;
            }
        }
        if (max != parent){
            swap(max,parent);
        }
        //递归到最后根结点为数组中最大值，则需要将根结点与当前数组末尾进行交换
        if(parent==0){
            swap(parent,bound);
            return;
        }
        sortOfRecursionHeap(--parent,bound);//递归过深会报出StackOverflowError异常，尝试bound=1000时就不行了
    }

    /**
     * 8.计数排序:必须知道数组的最大值和最小值，创建数组，长度为k，存储的是元素重复的数量.
     * 时间复杂度：存在N个0-K的数，O(N+K),常数O(1)<对数O(logN)<幂O(N^2)<指数O(2^N)<阶乘O(N!)
     * 空间复杂度：需要创建数组，O(N+K)
     * 稳定的排序算法
     * 要求：排序数组差值不是很大，序列比较集中。例如，对高考分数进行排序，需要知道每个分数有多少人。
     * 如果数据不连续，会浪费内存空间的
     */
    public void countingSort(){
        int min=params[0],max=params[0];
        for (int i = 0; i < size; i++) {
            min=Math.min(min,params[i]);
            max=Math.max(max,params[i]);
        }
        int len=max-min+1;
        int[] countArr=new int[len];
        for (int i = 0; i < size; i++) {
            countArr[params[i]-min]++;
        }
        int index=0;
        for (int i = 0; i < len; i++) {
            int temp=countArr[i];
            while (temp != 0){
                params[index++]=i+min;
                temp--;
            }
        }
    }

    /**
     * 9.桶排序:计数排序升级版，将排序数组分成若干个桶，每个桶为一个范围，类似[100-200)
     * 只需要每个桶内部自行排序，然后依次把数据从桶里拿出来
     * 本排序方法使用数组+链表结构
     * 时间复杂度：N个数，M个桶，O(N)+O(M*(N/M)*log(N/M))=O(N+N*(logN-logM))=O(N+N*logN-N*logM),
     * 最好的情况下时间复杂度为O(N)
     * 常数O(1)<对数O(logN)<幂O(N^2)<指数O(2^N)<阶乘O(N!)
     * 空间复杂度：需要创建数组，O(N+M)，如果输入数据非常庞大，而桶的数量也非常多，则空间代价无疑是昂贵的
     * 稳定的排序算法
     */
    public void bucketSort(){
        int min=params[0],max=params[0];
        //获取数组中最大值和最小值
        for (int i = 0; i < size; i++) {
            min=Math.min(min,params[i]);
            max=Math.max(max,params[i]);
        }
        //计算桶的数量
        int bucketNum=(max-min)/10+1;
        List<List<Integer>> buckets=new ArrayList<>(bucketNum);
        for (int i = 0; i < bucketNum; i++) {
            buckets.add(new ArrayList<>());
        }
        for (int i = 0; i < size; i++) {
            int index=(params[i]-min)/10;
            buckets.get(index).add(params[i]);
        }
        for (int i = 0; i < bucketNum; i++) {
            Collections.sort(buckets.get(i));
        }
        int k=0;
        for (int i=0;i < bucketNum;i++){
            for (int j = 0; j < buckets.get(i).size(); j++) {
                params[k++]=buckets.get(i).get(j);
            }
        }


        //此方法使用边添加边排序的方法
        /*LinkNode[] buckets=new LinkNode[bucketNum];
        for (int i = 0; i < size; i++) {
            //计算第i个数据应放在哪个桶里
            int index=(params[i]-min)/(max+1);
            if (buckets[index]==null){
                buckets[index]=new LinkNode(params[i]);
            }else {
                insertLinkNode(buckets, buckets[index], index, params[i]);
            }
        }
        int k=0;
        for (LinkNode bucket:buckets){
            while (bucket != null){
                params[k++]=bucket.value;
                bucket=bucket.next;
            }
        }*/
    }

    /**
     * 将结点插入到链表中并排序
     * @param buckets:链表集合
     * @param head:头结点
     * @param index:当前链表在数组中索引
     * @param value:结点的value值
     */
    private void insertLinkNode(LinkNode[] buckets, LinkNode head, int index,int value) {
        LinkNode newNode=new LinkNode(value);
        if (value<=head.value){
            newNode.next=head;
            buckets[index]=newNode;
            return;
        }
        LinkNode curNode=head;
        LinkNode prevNode=curNode;
        //找到比value大的位置
        while (curNode != null && curNode.value < value){
            prevNode=curNode;
            curNode=curNode.next;
        }
        prevNode.next=newNode;
        if (curNode != null) {
            newNode.next = curNode;
        }
    }

    private class LinkNode{
        LinkNode next;
        int value;

        public  LinkNode(){
            this(0);
        }
        public LinkNode(int value){
            next=null;
            this.value=value;
        }
    }

    /**
     * 基数排序：先对高位排序，再对低位排序
     */
    public void basketSort(){//data为待排序数组
        int data[]=params;
        int n=data.length;
        int bask[][]=new int[10][n];
        int index[]=new int[10];
        int max=Integer.MIN_VALUE;
        for(int i=0;i<n;i++)
        {
            max= Math.max(max, (Integer.toString(data[i]).length()));
        }
        StringBuilder str;
        for(int i=max-1;i>=0;i--)
        {
            for(int j=0;j<n;j++)
            {
                str = new StringBuilder();
                if(Integer.toString(data[j]).length()<max)
                {
                    for(int k=0;k<max-Integer.toString(data[j]).length();k++)
                        str.append("0");
                }
                str.append(Integer.toString(data[j]));
                bask[str.charAt(i)-'0'][index[str.charAt(i)-'0']++]=data[j];
            }
            int pos=0;
            for(int j=0;j<10;j++)
            {
                for(int k=0;k<index[j];k++)
                {
                    data[pos++]=bask[j][k];
                }
            }
            for(int x=0;x<10;x++)
                index[x]=0;
        }
    }
}
