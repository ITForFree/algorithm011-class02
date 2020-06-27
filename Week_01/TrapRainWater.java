import java.util.Stack;

/**
 * 42.接雨水：
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * 示例:
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 */
public class TrapRainWater {
    public int trap(int[] height) {
        // 借助栈的方式，时间复杂度：O(n)
        // 如果当前条形块小于或等于栈顶条形块，将条形块的索引入栈，即当前条形块被栈中前一个条形块界定
        // 如果当前条形块长于栈顶，可以确定栈顶条形块被当前条形块和栈的前一个条形块界定，可以弹出栈顶元素并且累加结果
        int ans = 0, current = 0;
        Stack<Integer> stack = new Stack<>();
        while(current < height.length) {
            while(!stack.isEmpty() && height[current] > height[stack.peek()]) {
                int top = stack.peek();
                stack.pop();
                if(stack.isEmpty()) {
                    break;
                }
                // 计算当前元素和栈顶元素的距离，准备进行填充操作
                int distance = current - stack.peek() - 1;
                // 找出界定高度
                int bounded_height = Math.min(height[current], height[stack.peek()]) - height[top];
                // 累加积水量
                ans += distance * bounded_height;
            }
            stack.push(current++);
        }
        return ans;
    }
}
