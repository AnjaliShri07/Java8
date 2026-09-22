package com.java8.programs.interview.GS;

/*
 ** Instructions to candidate.
 **  1) Given an array of non-negative integers representing the elevations
 **     from the vertical cross-section of a range of hills, determine how
 **     many units of snow could be captured between the hills.
 **
 **     See the example array and elevation map below.
 **                                 ___
 **             ___                |   |        ___
 **            |   |        ___    |   |___    |   |
 **         ___|   |    ___|   |   |   |   |   |   |
 **     ___|___|___|___|___|___|___|___|___|___|___|___
 **     {0,  1,  3,  0,  1,  2,  0,  4,  2,  0,  3,  0}
 **                                 ___
 **             ___                |   |        ___
 **            |   | *   *  _*_  * |   |_*_  * |   |
 **         ___|   | *  _*_|   | * |   |   | * |   |
 **     ___|___|___|_*_|___|___|_*_|___|___|_*_|___|___
 **     {0,  1,  3,  0,  1,  2,  0,  4,  2,  0,  3,  0}
 **
 **     Solution: In this example 13 units of snow (*) could be captured.
 **
 **  2) Consider adding some additional tests in doTestsPass().
 **  3) Implement computeSnowpack() correctly.
 */
/*✅ How It Works
To find how much snow is trapped at each index:
Water trapped at index i = min(maxLeft, maxRight) - height[i], if it's > 0
maxLeft: max height to the left of i
maxRight: max height to the right of i

✅ Algorithm Steps
Traverse the height array to compute:
leftMax[i]: Max height to the left of each index.
rightMax[i]: Max height to the right of each index.
Compute trapped water at each index using:

trapped = Math.min(leftMax[i], rightMax[i]) - height[i]*/
public class Snowpack {
    //Approach -2: Two-Pointer Approach
    public static int trapTwoPointers(int[] height) {
        /*left and right are pointers initialized to the start and end of the array, respectively.
            leftMax and rightMax store the maximum heights encountered so far from the left and right sides.
            snow accumulates the total amount of trapped water.*/
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        int snow = 0;

        //The loop runs until the left pointer is less than the right pointer.
        while (left < right) {
            //If the height at the left pointer is less than the height at the right pointer, we process the left side.
            if (height[left] < height[right]) {
                /*If the current height at left is greater than or equal to leftMax, update leftMax.
                Otherwise, calculate the trapped water at this position (leftMax - height[left]) and add it to snow.
                Move the left pointer one step to the right.*/
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    snow += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    snow += rightMax - height[right];
                }
                right--;
            }
        }
        return snow;
    }
    /*✅ Best all-around: O(n) time, O(1) space
    ✅ No extra memory required
    ❗ Slightly more logic to understand*/

    public static int computeSnowpack(int[] heights) {
        if (heights == null || heights.length < 3) return 0;

        int n = heights.length;
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];

        // Fill leftMax array
        leftMax[0] = heights[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], heights[i]);
        }

        // Fill rightMax array
        rightMax[n - 1] = heights[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], heights[i]);
        }

        // Calculate trapped snow
        int snow = 0;
        for (int i = 0; i < n; i++) {
            snow += Math.max(0, Math.min(leftMax[i], rightMax[i]) - heights[i]);
        }

        return snow;
    }
/*🧠 Time & Space Complexity
Type	Complexity
Time	O(n)
Space (extra)	O(n)*/

    public static void main(String[] args) {
        int[] input1 = {0, 1, 3, 0, 1, 2, 0, 4, 2, 0, 3, 0};
        System.out.println(trapTwoPointers(input1));
        int[] input2 = {3, 0, 2, 0, 4};
        System.out.println(trapTwoPointers(input2));
        int[] input3 = {1, 2, 3};
        System.out.println(trapTwoPointers(input3));
        int[] input4 = {};
        System.out.println(trapTwoPointers(input4));
    }
}
