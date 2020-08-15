### 学习笔记

#### 位运算

位运算符包含：左移(<<)、右移(>>)、按位或(|)、按位与(&)、按位取反(~)、按位异或(相同为零不同为一：^)。
异或：相同为0，不同为1。也可用“不进位加法”来理解。异或操作的一些特点：

* x ^ 0 = x
* x ^ 1s = ~x      // 注意1s = ~0
* x ^ (~x) = 1s
* x ^ x = 0
* c = a ^ b   =>   a ^ c = b, b ^ c = a      // 交换两个数
* a ^ b ^ c = a ^ (b ^ c) = (a ^ b) ^ c     // associative 
 
指定位置的位运算：

 * 将x 最右边的n 位清零：x& (~0 << n)
 * 获取x 的第n 位值（0 或者1）：(x >> n) & 1
 * 获取x 的第n 位的幂值：x& (1 <<n)
 * 仅将第n 位置为1：x | (1 << n)
 * 仅将第n 位置为0：x & (~ (1 << n))
 * 将x 最高位至第n 位（含）清零：x& ((1 << n) -1)
 * 将第n 位至第0 位（含）清零：x& (~ ((1 << (n + 1)) -1))
 
实战位运算要点：
 
 * 判断奇偶： x % 2 == 1  —> (x & 1) == 1 x % 2 == 0  —> (x & 1) == 0 
 * x >> 1 —> x / 2    即： x = x / 2;   —>    x = x >> 1; mid = (left + right) / 2;   —>    mid = (left + right) >> 1; 
 * X = X & (X-1) 清零最低位的1
 * X & -X => 得到最低位的1
 * X & ~X => 0

#### 布隆过滤器

一个很长的二进制向量和一系列随机映射函数。布隆过滤器可以用于检索一个元素是否在一个集合中。优点是空间效率和查询时间
都远远超过一般的算法， 缺点是有一定的误识别率和删除困难。

#### LRU Cache

替换策略：

* LFU - least frequently used
* LRU - least recently used

两个要素：大小 、替换策略

* Hash Table + Double LinkedList

* O(1) 查询 O(1) 修改、更新

代码模板：
```
public class LRUCache {
    private Map<Integer, Integer> map;

    public LRUCache(int capacity) {
        map = new LinkedCappedHashMap<>(capacity);
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        return map.get(key);
    }

    public void put(int key, int value) {
        map.put(key, value);
    }

    private static class LinkedCappedHashMap<K, V> extends LinkedHashMap<K, V> {
        int maximumCapacity;

        LinkedCappedHashMap(int maximumCapacity) {
            super(16, 0.75f, true);
            this.maximumCapacity = maximumCapacity;
        }

        protected boolean removeEldestEntry(Map.Entry eldest) {
            return size() > maximumCapacity;
        }
    }
} 
```

#### 排序算法

排序算法可分为以下两种：

* 比较类排序： 通过比较来决定元素间的相对次序，由于其时间复杂度不能突破 O(nlogn)，因此也称为非线性时间比较类排序。 
* 非比较类排序： 通过比较来决定元素间的相对次序，它可以突破基于比较排序的时间下界，以线性时间运行，因此也称为线性时间非比较类排序。

比较类排序分为以下四种：

* 交换排序：冒泡排序、快速排序

* 插入排序：简单插入排序、希尔排序

* 选择排序：简单选择排序、堆排序

* 归并排序：二路归并排序、多路归并排序

非比较排序包含：计数排序、桶排序、基数排序。

初级排序 - O(n^2)
1. 选择排序（Selection Sort）： 每次找最小值，然后放到待排序数组的起始位置。

2. 插入排序（Insertion Sort）： 从前到后逐步构建有序序列；对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。

3. 冒泡排序（Bubble Sort）： 嵌套循环，每次查看相邻的元素如果逆序，则交换。

高级排序 - O(N*LogN)
* 快速排序（Quick Sort） ：数组取标杆 pivot，将小元素放 pivot左边，大元素放右侧，然后依次对右边和右边的子数组继续快排；
以达到整个序列有序。

* 归并排序（Merge Sort）— 分治 
	1. 把长度为n的输入序列分成两个长度为n/2的子序列； 	
	2. 对这两个子序列分别采用归并排序； 	
	3. 将两个排序好的子序列合并成一个最终的排序序列。 
	
归并和快排具有相似性，但步骤顺序相反。

归并：先排序左右子数组，然后合并两个有序子数组。 
 
快排：先调配出左右子数组，然后对于左右子数组进行排序。

* 堆排序（Heap Sort） — 堆插入 O(logN)，取最大/小值 O(1) 
	1. 数组元素依次建立小顶堆 ； 
	2. 依次取堆顶元素，并删除。
	
特殊排序 - O(n)
* 计数排序（Counting Sort）： 计数排序要求输入的数据必须是有确定范围的整数。将输入的数据值转化为键存储在
额外开辟的数组空间中；然后依次把计数大于1的填充回原数组；
* 桶排序（Bucket Sort）： 桶排序 (Bucket sort)的工作的原理：假设输入数据服从均匀分布，将数据分到有限数量的桶里，
每个桶再分别排序（有可能再使用别的排序算法或是以递归方式继续使用桶排序进行排）。
* 基数排序（Radix Sort） 基数排序是按照低位先排序，然后收集；再按照高位排序，然后再收集；依次类推，直到最高位。
有时候有些属性是有优先级顺序的，先按低优先级排序，再按高优先级排序。 

选择排序代码：
```
public static int[] selectionSort(int[] arr) {
    int minIndex, temp;
    for (int i = 0; i < arr.length - 1; i++) {
        minIndex = i;
        for (int j = i + 1; j < arr.length; j++) {
            if (arr[j] < arr[minIndex]) { // 寻找最小的数
                minIndex = j; // 将最小数的索引保存
            }
        }
        temp = arr[i];
        arr[i] = arr[minIndex];
        arr[minIndex] = temp;
    }
    return arr;
}
```

插入排序代码：
```
public static int[] insertionSort(int[] arr) {
    int preIndex, current;
    for(int i = 1; i < arr.length; i++) {
        preIndex = i - 1;
        current = arr[i];
        while(preIndex >= 0 && arr[preIndex] > current) {
            arr[preIndex+1] = arr[preIndex];
            preIndex --;
        }
        arr[preIndex+1] = current;
    }
    return arr;
}
```

冒泡排序代码：
```
public static int[] bubbleSort(int[] arr) {
    for(int i = 0; i < arr.length - 1; i++) {
        for(int j = 0; j < arr.length - 1 - i; j++) {
            if(arr[j] > arr[j+1]) { // 相邻元素两两对比
                int temp = arr[j+1]; // 元素交换
                arr[j+1] = arr[j];
                arr[j] = temp;
            }
        }
    }
    return arr;
}
```
快速排序代码：
```
// 调用方式：quickSort(arr, 0, arr.length - 1);
public static int[] quickSort(int[] arr, int begin, int end) {
    if(end <= begin) {
        return arr;
    }
    int pivot = partition(arr, begin, end);
    quickSort(arr, begin, pivot - 1);
    quickSort(arr, pivot + 1, end);
    return arr;
}
public static int partition(int[] arr, int begin, int end) {
    // pivot: 标杆位置，counter: ⼩于pivot的元素的个数
    int pivot = end, counter = begin;
    for(int i = begin; i < end; i++) {
        if(arr[i] < arr[pivot]) {
            int temp = arr[counter];
            arr[counter] = arr[i];
            arr[i] = temp;
            counter ++;
        }
    }
    int temp = arr[pivot];
    arr[pivot] = arr[counter];
    arr[counter] = temp;
    return counter;
}
```
归并排序代码：
```
public static void mergeSort(int[] arr, int left, int right) {
    if (right <= left) return;
    int mid = (left + right) >> 1; // (left + right) / 2
    mergeSort(arr, left, mid);
    mergeSort(arr, mid + 1, right);
    merge(arr, left, mid, right);
}

public static void merge(int[] arr, int left, int mid, int right) {
    int[] temp = new int[right - left + 1];
    int i = left, j = mid + 1, k = 0;
    while(i <= mid && j <= right) {
        temp[k++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
    }
    while(i <= mid) temp[k++] = arr[i++];
    while(j <= right) temp[k++] = arr[j++];
    for(int p = 0; p < temp.length; p++) {
        arr[left+p] = temp[p];
    }
}
```
堆排序代码：
```
public static void heapify(int[] array, int length, int i) {
    int left = 2 * i + 1, right = 2 * i + 2;
    int largest = i;
    if (left < length && array[left] > array[largest]) {
        largest = left;
    }
    if (right < length && array[right] > array[largest]) {
        largest = right;
    }
    if (largest != i) {
        int temp = array[i]; array[i] = array[largest]; array[largest] = temp;
        heapify(array, length, largest);
    }
}
public static void heapSort(int[] array) {
    if (array.length == 0) return;
    int length = array.length;
    for (int i = length / 2-1; i >= 0; i--)
        heapify(array, length, i);
    for (int i = length - 1; i >= 0; i--) {
        int temp = array[0]; array[0] = array[i]; array[i] = temp;
        heapify(array, i, 0);
    }
}
```


