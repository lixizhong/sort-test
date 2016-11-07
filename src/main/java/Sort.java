import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by lixizhong on 2016/11/6.
 */
public class Sort {
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

    public static void quickSort(int[] array) {
        subQuickSort3(array, 0, array.length-1);
    }

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

}
