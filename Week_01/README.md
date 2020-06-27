学习笔记

本周是算法训练营的第一周，主要讲解了数组、链表、跳表、栈和队列等数据结构。
1.数组是顺序存储，查找的时间复杂度为O(1),插入和删除的时间复杂度为O(n)；

2.链表是非顺序存储，查找的时间复杂度为O(n),插入和删除的时间复杂度为O(1)；

3.跳表里的元素必须是有序的，查询/插入/删除的时间复杂度为O(logn),redis采用了跳表存储；

4.栈是一种后进先出(LIFO)的数据结构，查找的时间复杂度为O(n)，增加和删除的时间复杂度为O(1)。栈里面包含的API：
empty()-判断栈是否为空，push(E item)-将元素压入栈，pop()-移除栈顶元素并返回对应的值，peek()-返回栈顶元素，
但是不对栈做任何操作，search(Object o)-返回栈中元素下标；

5.队列是一种先进先出(FIFO)的数据结构，查找的时间复杂度为O(n)，增加和删除的时间复杂度为O(1)。
队列包含的API：add(e)/offer(e)-将元素放入队列，remove()/poll()-出队，element()/peek()-返回队头元素，
前者与后者的区别在于遇到某些异常情况（如队列为空）时，前者会抛异常，后者会返回特殊值。

实际工程中使用最多的是Deque（双端队列），查找的时间复杂度是O(n)，增加和删除的时间复杂度是O(1)。Deque包含的API：
插入：addFirst(e)/addLast(e)，offerFirst(e)/offerLast(e)
移除：removeFirst()/removeLast()，pollFirst()/pollLast()
查找：getFirst()/getLast()，peekFirst()/peekLast()

遇到异常情况（如队列为空）时，add，remove，get接口会抛异常，offer，poll，peek接口会返回特殊值。
用addFirst或addLast改写Deque代码：
```
          Deque<String> deque = new LinkedList<String>();
          deque.addFirst("a");
          deque.addFirst("b");
          deque.addLast("c");
          deque.addLast("d");
          System.out.println(deque);
          String str = deque.peekFirst();
          System.out.println(str);
          System.out.println(deque);
          while(!deque.isEmpty()) {
              System.out.println(deque.removeFirst());
          }
          System.out.println(deque);
```
Queue源码分析：

Queue是一个接口，继承Collection，实现类包含：AbstractQueue, ArrayBlockingQueue, ArrayDeque, ConcurrentLinkedDeque, ConcurrentLinkedQueue, DelayQueue, LinkedBlockingDeque, LinkedBlockingQueue, LinkedList, LinkedTransferQueue, PriorityBlockingQueue, PriorityQueue, SynchronousQueue。
包含的方法有：

boolean add(E e)：将元素插入队列，成功的话返回true，失败的话，返回false；

boolean offer(E e)：将元素插入队列，成功的话返回true，失败的话，返回false；

E remove()：移除队头元素，如果队列为空，则抛异常；

E poll()：移除队头元素，如果队列为空，则返回null；

E element()：返回队头元素，如果队列为空，则抛异常；

E peek()：返回队头元素，如果队列为空，则返回null。

PriorityQueue源码分析：

PriorityQueue是一个类，继承AbstractQueue，默认容量大小为11，包含的构造方法有：PriorityQueue()、PriorityQueue(int initialCapacity)、
PriorityQueue(Comparator<? super E> comparator)、PriorityQueue(int initialCapacity,Comparator<? super E> comparator)、
PriorityQueue(Collection<? extends E> c)、PriorityQueue(PriorityQueue<? extends E> c)、PriorityQueue(SortedSet<? extends E> c)

包含的主要API有：

add(E e)/offer(E e)：将元素插入优先队列；

E peek()：返回优先队列队头元素；

E poll():移除某个元素；

boolean remove(Object o)：移除某个元素；

removeEq(Object o)：使用引用移除某个元素；

offer及poll及removeAt部分的源码：

```
    public boolean add(E e) {
        return offer(e);
    }
    public boolean offer(E e) {
        if (e == null)
            throw new NullPointerException();
        // 队列被改变的次数累加
        modCount++;
        int i = size;
        // 如果存储元素大于队列长度，则需要扩容队列
        if (i >= queue.length)
            grow(i + 1);
        // 向上调整
        siftUp(i, e);
        size = i + 1;
        return true;
    }
    
    private void siftUp(int k, E x) {
        if (comparator != null)
            siftUpUsingComparator(k, x); // 通过比较器排序
        else
            siftUpComparable(k, x); // 通过实现了Comparable接口的类排序
    }
    private void siftUpUsingComparator(int k, E x) {
         // 当前元素与父节点不断比较,如果比父节点小就交换然后继续向上比较，否则停止比较
         while (k > 0) {
             int parent = (k - 1) >>> 1;
             Object e = queue[parent];
             if (comparator.compare(x, (E) e) >= 0)
                 break;
             queue[k] = e;
             k = parent;
         }
         queue[k] = x;
    }
    private void siftUpComparable(int k, E x) {
        Comparable<? super E> key = (Comparable<? super E>) x;
         // 当前元素与父节点不断比较,如果比父节点小就交换然后继续向上比较，否则停止比较
        while (k > 0) {
            int parent = (k - 1) >>> 1;
            Object e = queue[parent];
            if (key.compareTo((E) e) >= 0)
                break;
            queue[k] = e;
            k = parent;
        }
        queue[k] = key;
    }
    public E poll() {
        if (size == 0)
            return null;
        int s = --size;
        // 队列被改变的次数累加
        modCount++;
        E result = (E) queue[0];
        E x = (E) queue[s];
        queue[s] = null;
        if (s != 0)
            siftDown(0, x); // 向下调整
        return result;
    }
    public boolean remove(Object o) {
        int i = indexOf(o);
        if (i == -1)
            return false;
        else {
            removeAt(i);
            return true;
        }
    }
    E removeAt(int i) {
        // assert i >= 0 && i < size;
        modCount++;
        int s = --size;
        if (s == i) // removed last element
            queue[i] = null;
        else {
            E moved = (E) queue[s];
            queue[s] = null;
            siftDown(i, moved); // 从当前删除位置向下比较
            if (queue[i] == moved) {
                siftUp(i, moved); // 从当前位置向上比较
                if (queue[i] != moved)
                    return moved;
            }
        }
        return null;
    }
    private void siftDown(int k, E x) {
        if (comparator != null)
            siftDownUsingComparator(k, x); // 通过比较器排序
        else
            siftDownComparable(k, x); // 通过实现了Comparable类的接口排序
    }
    private void siftDownUsingComparator(int k, E x) {
        int half = size >>> 1;
        // 如果当前节点比左右节点中的最小节点小就 break，否则一直与左右节点的最小节点交换，直到当前节点无子节点
        while (k < half) {
            int child = (k << 1) + 1;
            Object c = queue[child];
            int right = child + 1;
            if (right < size &&
                comparator.compare((E) c, (E) queue[right]) > 0)
                c = queue[child = right]; //如果右孩子更小，则将right赋给child，把queue[right]赋给c
            if (comparator.compare(x, (E) c) <= 0)
                break; // 如果目标节点值小于等于c，则break，否则，继续循环
            queue[k] = c;
            k = child;
        }
        queue[k] = x;
    }
    private void siftDownComparable(int k, E x) {
        Comparable<? super E> key = (Comparable<? super E>)x;
        int half = size >>> 1;        // loop while a non-leaf
        while (k < half) {
            int child = (k << 1) + 1; // assume left child is least
            Object c = queue[child];
            int right = child + 1;
            if (right < size &&
                ((Comparable<? super E>) c).compareTo((E) queue[right]) > 0)
                c = queue[child = right];
            if (key.compareTo((E) c) <= 0)
                break;
            queue[k] = c;
            k = child;
        }
        queue[k] = key;
    }
```


