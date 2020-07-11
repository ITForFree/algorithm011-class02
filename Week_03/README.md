学习笔记

### 递归

Java递归的代码模板：
```
public void recur(int level, int param1, int param2, ...) {
    // recursion terminator
    if(level > MAX_LEVEL) {
        return;
    }
    
    // process current logic
    process(level, param);
    
    // drill down
    recur(level + 1, newParam1, newParam2, ...);
    
    // restore current status
}
```
思维要点：
* 不要进行人肉递归（最大误区）；
* 找到最近最简方法，将其拆解成可重复解决的问题（重复子问题）；
* 数学归纳法思维。

### 分治

Java分治的代码模板：
```
public void divideConquer(problem, param1, param2, ...) {
    // recursion terminator
    if(problem == null) {
        // deal with result
        return;
    }
   
    // prepare data
    data = prepareData(problem);
    subProblems = spiltProblem(problem, data);
   
    // conquer subProblems
    subResult1 = divideConquer(subProblems[0], p1, p2, ...);
    subResult2 = divideConquer(subProblems[1], p1, p2, ...);
    subResult3 = divideConquer(subProblems[2], p1, p2, ...);
    ...
    // process and generate the final result 
    result = processResult(subResult1, subResult2, subResult3, ...)
    
    // revert the current level states
}
```
### 回溯

回溯法采用试错的思想，它尝试分步的去解决一个问题。在分步解决问题的过程中，
当它通过尝试发现现有的分步答案不能得到有效的正确的解答的时候，它将取消
上一步甚至是上几步的计算，再通过其它的可能的分步解答再次尝试寻找问题的答案。

回溯法通常用最简单的递归方法来实现，在反复重复上述的步骤后可能出现两种情况：
* 找到一个可能存在的正确的答案；
* 在尝试了所有可能的分步方法后宣告该问题没有答案。

在最坏的情况下，回溯法会导致一次复杂度为指数时间的计算。