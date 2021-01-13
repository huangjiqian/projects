1.强引用:普通对象被new出来的就是强引用，GC时不会被回收，除非对象赋值null
2.软引用:在Heap内存中内存不够时，GC会回收软引用。适用于缓存以及从数据库读取数据(数据被回收大不了重新读)
3.弱引用:GC时就会被回收，但是有强引用指向同一个对象，那么软引用就不会回收。适用于容器
4.虚引用:必定被回收
5.ThreadLocal:内部使用了ThreadLocalMap存储变量，Map中Entry方法实现了弱引用指向ThreadLocal。
为什么Entry要使用弱引用？Entry extends WeakReference
-> 若是强引用，ThreadLocal对象为null时，但key的引用依然指向ThreadLocal对象，所有会有内存泄漏，而使用弱引用则不会。
-> ThreadLocal对象被回收，Entry的key值为null，导致整个value无法被访问到，就会有内存泄漏，所以在利用完key-value后调用ThreadLocal中remove方法，删除value