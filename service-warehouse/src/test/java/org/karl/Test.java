package org.karl;

import java.util.*;

/**
 * @author KARL ROSE
 * @date 2020/9/23 15:38
 **/
public class Test {

    public int minOperationsMaxProfit(int[] customers, int boardingCost, int runningCost) {
        /*HashMap<Integer, Integer> map = new HashMap<>();
        int maxProfit = 0;
        int profit;
        int uploadedCustomer = 0;
        int runNum = 0;

        for (int customer : customers) {
            int upCustomer = Math.min(customer, 4);
            uploadedCustomer += upCustomer;
            while (upCustomer <= customer) {
                runNum++;
                profit = uploadedCustomer * boardingCost - runNum * runningCost;
                maxProfit = Math.max(maxProfit, profit);
                map.put(maxProfit, runNum);
                if (upCustomer == customer) {
                    break;
                }
                if (customer - upCustomer < 4) {
                    uploadedCustomer += (customer - upCustomer);
                    upCustomer = customer;
                } else {
                    upCustomer += 4;
                    uploadedCustomer += 4;
                }
            }
        }
        return maxProfit > 0 ? map.get(maxProfit) : -1;*/
        int profit = 0, res = -1, rem = 0;
        int round = 0, sum = 0, n = customers.length;
        int i = 0;
        while (i < n || rem > 0) {
            rem += i < n ? customers[i++] : 0;
            int cur = Math.min(rem, 4);
            rem -= cur;
            round += 1;
            sum += cur;
            int cost = sum * boardingCost - runningCost * round;
            if (cost > profit) {
                profit = cost;
                res = round;
            }
        }
        return res;
    }

    private static final int _1MB = 1024 * 1024;

    public static int[] sortByBits(int[] arr) {
        List<Integer> list = new ArrayList<>();
        for (int x : arr) {
            list.add(x);
        }
        int[] bit = new int[10001];
        //bit[i]=bit[i>>1]+(i&1)
        for (int i = 1; i <= 10000; ++i) {
            bit[i] = bit[i >> 1] + (i & 1);
            System.out.println("i=" + i + ",bit[i>>i]=" + bit[i >> i] + ",bit[i]=" + bit[i]);
        }
        list.sort((x, y) -> {
            if (bit[x] != bit[y]) {
                return bit[x] - bit[y];
            } else {
                return x - y;
            }
        });
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    public int[][] kClosest(int[][] points, int K) {
        int[][] res = new int[K][2];
        HashMap<int[], Integer> map = new HashMap<>();
        int[] values = new int[points.length];
        int count = 0;
        for (int i = 0; i < points.length; i++) {
            int[] arr = points[i];
            int sum = arr[0] * arr[0] + arr[1] * arr[1];
            map.put(arr, sum);
            values[i] = sum;
        }
        Arrays.sort(values);
        int min = values[K - 1];
        for (Map.Entry<int[], Integer> entry : map.entrySet()) {
            if (entry.getValue() <= min) {
                res[count++] = entry.getKey();
            }
        }
        return res;


    }

    public int[][] reconstructQueue(int[][] people) {
        //排序 从高到低
        Arrays.sort(people, (o1, o2) -> {
            if (o1[0] != o2[0]) {
                return o2[0] - o1[0];
            } else {
                return o1[1] - o2[1];
            }
        });
        List<int[]> ans = new ArrayList<>();
        for (int[] person : people) {
            ans.add(person[1], person);
        }
        return ans.toArray(new int[ans.size()][]);


    }

    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        int[][] ans = new int[R * C][2];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                ans[i * C + j] = new int[]{i, j};
            }
        }
        Arrays.sort(ans, Comparator.comparingInt(o -> (Math.abs(o[0] - r0) + Math.abs(o[1] - c0))));
        return ans;

    }

    public static int findMinArrowShots(int[][] points) {
        //排序 结束坐标小的在前
        Arrays.sort(points, Comparator.comparingInt(o -> o[1]));

        int pos = points[0][1];
        int ans = 1;
        for (int[] balloon : points) {
            if (balloon[0] > pos) {
                pos = balloon[1];
                ++ans;
            }
        }
        return ans;
    }


    public String sortString(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int[] num = new int[26];
        for (int i = 0; i < s.length(); i++) {
            num[s.charAt(i) - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        while (sb.length() < s.length()) {
            for (int i = 0; i < 26; i++) {
                if (num[i] > 0) {
                    sb.append((char) (i + 'a'));
                    num[i]--;
                }
            }
            for (int i = 25; i >= 0; i--) {
                if (num[i] > 0) {
                    sb.append((char) (i + 'a'));
                    num[i]--;
                }
            }
        }
        return sb.toString();
    }


    public static void main(String[] args) {
//        System.out.println(new Test().minOperationsMaxProfit(new int[]{10, 10, 6, 4, 7}, 3, 8));
//        System.out.println(Arrays.toString(sortByBits(new int[]{1, 3, 5, 7, 9})));
        System.out.println(findMinArrowShots(new int[][]{{1, 2}, {3, 4}, {5, 6}, {7, 8}}));
    }
}
