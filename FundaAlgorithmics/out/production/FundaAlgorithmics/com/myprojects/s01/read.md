## 对数器

为了测试算法的正确性，使用一个容易实现的算法与之比较，使用大量的测试案例来测试算法的稳定性。

```java
//随机生成数组
public static int[] generateRandomArray(int maxSize,int maxValue){
    int[] arr=new int[(int) ((maxSize+1) * Math.random())];
    for (int i = 0; i < arr.length; i++) {
        arr[i]=(int) ((maxValue+1)*Math.random()) - (int) (maxValue*Math.random());
    }
    return arr;
}

//copy数组用于容易实现的算法
public static int[] copyArray(int[] arr){
    if(arr==null) return null;
    int[] result=new int[arr.length];
    for (int i = 0; i < arr.length; i++) {
        result[i]=arr[i];
    }
    return result;
}
//判断两个算法排序后的数组是否一样
public static boolean equals(int[] arr1,int[] arr2){
    if((arr1==null && arr2!=null) || (arr1!=null && arr2==null)) return false;
    if(arr1==null && arr2==null) return true;
    if(arr1.length!=arr2.length) return false;
    for (int i = 0; i < arr1.length; i++) {
        //if(arr1[i] != arr2[i]) return false;
        if((arr1[i] ^ arr2[i]) != 0) return false;
    }
    return true;
}

//容易实现的算法
public static void comparator(int[] arr){
    Arrays.sort(arr);
}

//打印数组
public static void printArr(int[] arr){
    System.out.print("arr[");
    for (int i = 0; i < arr.length; i++) {
        System.out.print(arr[i]+",");
    }
    System.out.println("]");
}
```



## 位运算操作

异或：两个数无进位相加

6 ^ 7

```bit
6	0110
7	0111
+----------
	0001
```

面试题：

1. 数组中只有一个数出现奇数次，找出这个数 

   ```java
   //arr中，只有一种数，出现了奇数次
   public static void printOneNumEvenTimes(int[] arr){
       int eor=0;
       for (int i = 0; i < arr.length; i++) {
           eor ^= arr[i];
       }
       System.out.println(eor);
   }
   ```

2.数组中有两个数出现奇数次，找出这两个数

```java
//arr中，只有两种数，出现了奇数次
public static void printTwoNumEvenTimes(int[] arr){
    int eor=0;
    for (int i = 0; i < arr.length; i++) {
        eor ^= arr[i];
    }
    /**
         * 提取eor最右边的一个1
         * eor     =  0001 1100
         * ~eor    =  1110 0011
         * ~eor+1  =  1110 0100
         * rightOne=  0000 0100
         */
    int rightOne=eor & (~eor +1);
    int onlyOne=0;
    for (int i = 0; i < arr.length; i++) {
        if((rightOne & arr[i]) != 0){
            onlyOne ^= arr[i];
        }
    }
    System.out.println(onlyOne + "," + (onlyOne^eor));
}
```



## 二分法

1. 时间复杂度: logN

   ```
   第一次		N/2
   第二次		N/4
   第三次		N/8
   	...
   第K次		N/(2^K)
   
   大致得出N=2^K，那么时间复杂度为logN
   ```

2. 面试题
   1. 给定一个有序数组arr和value，判断这个value在arr中是否存在
   2. 给定一个有序数组arr和value，找出>=value最左位置的index
   3. 给定一个有序数组arr和value，找出<=value最右位置的index
   4. 给定一个无序数组arr，找出局部最小值