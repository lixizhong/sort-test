import org.junit.*;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by lixizhong on 2016/11/6.
 */
public class TestSort {
    private static int[] array;
    private static int len = 1000;
    private int[] arrayToSort;
    private static int[] arrayRightSort;
    private long startTime;

    @BeforeClass
    public static void generateArray() {
        array = new int[len];
        for(int i=0; i<len; i++){
            int n = new Random().nextInt(999999999);
            array[i] = n;
        }
        arrayRightSort = new int[len];
        System.arraycopy(array, 0, arrayRightSort, 0, len);

        Arrays.sort(arrayRightSort);
        System.out.println("init finished");
        System.out.println("array to sort:");
        displayArray(array);
        System.out.println("array want:");
        displayArray(arrayRightSort);
    }
    @Before
    public void countDown(){
        arrayToSort = new int[len];
        System.arraycopy(array, 0, arrayToSort, 0, len);
        startTime = System.currentTimeMillis();
    }
    @After
    public void countUp(){
        long endTime = System.currentTimeMillis();
        displayArray(arrayToSort);
        Assert.assertTrue(checkArray(arrayToSort));
        System.out.println("userd time: " + (endTime - startTime));
    }

    public boolean checkArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if(arrayRightSort[i] != arrayToSort[i]) {
                return false;
            }
        }
        return true;
    }

    public static void displayArray(int [] array) {
        if(true){
            return;
        }
        for (int i = 0; i < array.length; i++) {
            int n = array[i];
            System.out.print(n + "\t");
        }
        System.out.println();
    }

    //@Test
    public void testBubbleSort(){
        System.out.println("bubble sort:");
        Sort.bubbleSort(arrayToSort);
    }

    //@Test
    public void testBubbleSortImprove(){
        System.out.println("bubble improve:");
        Sort.bubbleSortImprove(arrayToSort);
    }

    //@Test
    public void testSelectionSort() {
        System.out.println("selection sort:");
        Sort.selectionSort(arrayToSort);
    }

    @Test
    public void testInsertionSort(){
        System.out.println("insertion sort:");
        Sort.insertionSort(arrayToSort);
    }

    @Test
    public void testInsertionBinarySort(){
        System.out.println("insertion binary sort:");
        Sort.insertionBinarySort(arrayToSort);
    }

    @Test
    public void testQuickSort() {
        System.out.println("quick sort:");
        Sort.quickSort(arrayToSort);
    }

    @Test
    public void testMergeSort(){
        System.out.println("merge sort:");
        Sort.mergeSort(arrayToSort);
    }
}
