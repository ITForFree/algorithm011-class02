学习笔记

####总结
本周主要讲解了哈希表、映射、集合、树、图、堆等数据结构，其中图和堆是我目前从未接触过的数据结构，听完之后，收获很大。

1.哈希表（散列表），根据关键码值直接进行访问，最好情况下，查询/插入/删除的时间复杂度为O(1)，最坏为O(n)。Java中常用的是HashMap 和 HashSet。

2.树和图的区别在于图有环。树和图遍历的时间复杂度都为O(n)，由于每个节点只访问一次。

3.堆是一种可以快速找到一堆数中最大值或最小值的数据结构。根节点最大的堆叫做大根堆或大顶堆，
根节点最小的堆叫小根堆或小顶堆。二叉堆通过完全二叉树实现，满足以下性质：
* 是一颗完全树；
* 树中任意节点的值 >= 其子节点的值。

二叉堆堆插入、删除（最大值）的时间复杂度为O(logN)，查找最大值的时间复杂度为O(1)。

####HashMap分析
HashMap是基于哈希表(散列表)，继承 AbstractMap，key唯一的，value可以重复，
允许存储null 键null 值，元素无序。

数据结构：

（1）JDK 8 以前：
* JDK 8 以前 HashMap 的实现是 数组+链表，即使哈希函数取得再好，也很难达到元素百分百均匀分布；
* 当 HashMap 中有大量的元素都存放到同一个桶中时，这个桶下有一条长长的链表，极端情况HashMap 就相当于一个单链表，假如单链表有 n 个元素，遍历的时间复杂度就是 O(n)，完全失去了它的优势。

（2）JDK 8 以后：
* JDK7与JDK8中HashMap实现的最大区别就是对于冲突的处理方法。JDK 1.8 中引入了红黑树（查找时间复杂度为 O(logn)）,用数组+链表+红黑树的结构来优化这个问题。

HashMap有以下集中重要属性：
* loadFactor: 装载因子，用来衡量 HashMap 满的程度，默认为 0.75f；
* threshold: 表示当 HashMap 的 size 大于 threshold 时会执行 resize 操作，threshold = capacity（HashMap中桶的数量，默认值为16）*loadFactor；
* DEFAULT_INITIAL_CAPACITY : 默认初始化容量 16，容量必须为2的次方。默认的 hashmap 大小为16；
* MAXIMUM_CAPACITY :最大的容量大小2^30；
DEFAULT_LOAD_FACTOR: 默认 resize 的因子。0.75，即实际数量超过总数 DEFAULT_LOAD_FACTOR 的数量即会发生resize动作。

HashMap 中 get 及 put 方法源码如下：
```
public V get(Object key) {
       Node<K,V> e;
       return (e = getNode(hash(key), key)) == null ? null : e.value;
}

public V put(K key, V value) {
       return putVal(hash(key), key, value, false, true);
}

final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                   boolean evict) {
        Node<K,V>[] tab; Node<K,V> p; int n, i;
        // 初始化时，map中还没有key-value
        if ((tab = table) == null || (n = tab.length) == 0)
            // 利用resize生成对应的tab[]数组
            n = (tab = resize()).length;
        if ((p = tab[i = (n - 1) & hash]) == null)
            // 当前桶无元素
            tab[i] = newNode(hash, key, value, null);
        else { // 桶内有元素
            Node<K,V> e; K k;
            if (p.hash == hash &&
                ((k = p.key) == key || (key != null && key.equals(k))))
                // 桶内第一个元素的key等于待放入的key
                e = p;
            else if (p instanceof TreeNode)
                // 如果此时桶内已经树化
                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
            else { // 桶内还是一个链表，则插入链尾（尾插）
                for (int binCount = 0; ; ++binCount) {
                    if ((e = p.next) == null) {
                        p.next = newNode(hash, key, value, null);
                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                            // 变成红黑树
                            treeifyBin(tab, hash);
                        break;
                    }
                    if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                        break;
                    p = e;
                }
            }
            if (e != null) { // existing mapping for key
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;
                afterNodeAccess(e);
                return oldValue;
            }
        }
        ++modCount;
        // 检查是否应该扩容
        if (++size > threshold)
            resize();
        afterNodeInsertion(evict);
        return null;
}
```

