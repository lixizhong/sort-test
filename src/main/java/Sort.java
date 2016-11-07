import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by lixizhong on 2016/11/6.
 */
public class Sort {

    public static void main(String[] args){
        int[] array = new int[]{1, 2, 4};
        int low = 0;
        int hight = array.length - 1;
        int key = 3;
        while(low <= hight) {
            int mid = (low + hight) / 2;
            if(array[mid] > key) {
                hight = mid - 1;
            }else if(array[mid] <= key){
                low = mid + 1;
            }
        }
        System.out.println(low);
    }

    //冒泡排序
    public static void bubbleSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                int m = array[j];
                int n = array[j + 1];
                if (m > n) {
                    array[j] = n;
                    array[j + 1] = m;
                }
            }
        }
    }
    //改进的冒泡排序
    public static void bubbleSortImprove(int[] array) {
        int lastSwapPosTmp = array.length - 1;
        int lastSwapPos = array.length - 1;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < lastSwapPos; j++) {
                int m = array[j];
                int n = array[j + 1];
                if (m > n) {
                    array[j] = n;
                    array[j + 1] = m;
                    lastSwapPosTmp = j;
                }
            }
            if(lastSwapPos == lastSwapPosTmp) {
                break;
            }
            lastSwapPos = lastSwapPosTmp;
        }
    }
    //插入排序
    public static void insertionSort(int[] array){
        for (int i = 1; i < array.length; i++) {
            for(int j=i; j>0; j--) {
                if(array[j] < array[j-1]) {
                    swap(array, j, j-1);
                }else{
                    break;
                }
            }
        }
    }
    //插入排序(使用二分法确定插入位置)
    public static void insertionBinarySort(int[] array){
        for (int i = 1; i < array.length; i++) {
            int low = 0;
            int hight = i - 1;
            int key = array[i];
            while(low <= hight) {
                int mid = (low + hight) / 2;
                if(array[mid] > key) {
                    hight = mid - 1;
                }else if(array[mid] <= key){
                    low = mid + 1;
                }
            }
            int t = array[i];
            for(int j = i; j > low; j--){
                array[j] = array[j-1];
            }
            array[low] = t;
        }
    }
    //快速排序
    public static void quickSort(int[] array) {
        subQuickSort3(array, 0, array.length-1);
    }
    //快速排序（递归实现方式）
    public static void subQuickSort(int[] array, int low, int high) {
        int p = partion(array, low, high);
        if(p < high) {
            subQuickSort(array, p+1, high);
        }
        if(p > low) {
            subQuickSort(array, low, p);
        }
    }
    //非递归快排(使用栈)
    private static void subQuickSort2(int[] array, int low, int high) {
        class Range {
            public int low;
            public int high;
            public Range(int low, int high){
                this.low = low; this.high = high;
            }
        }
        Stack<Range> stack = new Stack<Range>();
        stack.push(new Range(low, high));
        while( ! stack.empty()){
            Range range = stack.pop();
            int p = partion(array, range.low, range.high);
            if(p < range.high) {
                stack.push(new Range(p+1, range.high));
            }
            if(p > range.low) {
                stack.push(new Range(range.low, p));
            }
        }
    }

    //非递归快排(使用队列)
    private static void subQuickSort3(int[] array, int low, int high) {
        class Range {
            public int low;
            public int high;
            public Range(int low, int high){
                this.low = low; this.high = high;
            }
        }
        Queue<Range> queue = new LinkedList<Range>();
        queue.add(new Range(low, high));
        while( ! queue.isEmpty()){
            Range range = queue.remove();
            int p = partion(array, range.low, range.high);
            if(p < range.high) {
                queue.add(new Range(p+1, range.high));
            }
            if(p > range.low) {
                queue.add(new Range(range.low, p));
            }
        }
    }

    private static int partion(int[] array, int low, int high){
        int key = array[low];
        while(low < high) {
            while(low < high && array[high] >= key){
                high--;
            }
            while(low < high && array[low] < key){
                low++;
            }
            if(low != high) {
                swap(array, high, low);
            }
        }
        return high;
    }

    private static void swap(int[] array, int i, int j){
        if(i == j) return;
        int t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    public static void selectionSort(int[] array){
        for(int i=0; i<array.length-1; i++){
            int index = i;
            for(int j=i+1; j<array.length; j++){
                if(array[j] < array[index]) {
                    index = j;
                }
            }
            swap(array, i, index);
        }
    }

    public static void mergeSort(int[] array){
        mergeSortSub(array, 0, array.length - 1);
    }

    private static void mergeSortSub(int[] array, int low, int high){
        if(low == high) return;
        int mid = (high + low) / 2;
        mergeSortSub(array, low, mid);
        mergeSortSub(array, mid+1, high);
        int[] temp = new int[high-low+1];

        int p1 = low;
        int p2 = mid + 1;
        int p3 = 0;
        while(p3 < temp.length) {
            while(p1 <= mid && (p2 > high || array[p1] <= array[p2])) {
                temp[p3++] = array[p1++];
            }
            while(p2 <= high && (p1 > mid || array[p2] <= array[p1])) {
                temp[p3++] = array[p2++];
            }
        }
        System.arraycopy(temp, 0, array, low, high - low + 1);
    }

}
