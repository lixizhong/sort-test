import org.junit.*;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by lixizhong on 2016/11/6.
 */
public class TestSort {
    private static int[] array;
    private static int len = 10000000;
    private int[] arrayCopy;
    private long startTime;
    @BeforeClass
    public static void generateArray() {
        array = new int[len];
        for(int i=0; i<len; i++){
            int n = new Random().nextInt(999999999);
            array[i] = n;
        }
        System.out.println("init finished");
    }
    @Before
    public void countDown(){
        arrayCopy = new int[len];
        System.arraycopy(array, 0, arrayCopy, 0, len);
        startTime = System.currentTimeMillis();
        displayArray(arrayCopy);
    }
    @After
    public void countUp(){
        long endTime = System.currentTimeMillis();
        displayArray(arrayCopy);
        Assert.assertTrue(checkArray(arrayCopy));
        System.out.println("userd time: " + (endTime - startTime));
    }

    public boolean checkArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if(i > 0 && array[i] < array[i - 1]) {
                return false;
            }
        }
        return true;
    }

    public void displayArray(int [] array) {
        if(0 == 0)
            return;
        for (int i = 0; i < array.length; i++) {
            int n = array[i];
            System.out.print(n + "\t");
        }
        System.out.println();
    }

    @Test
    public void testBubbleSort(){
        System.out.println("bubble:");
        Sort.bubbleSort(arrayCopy);
    }

    @Test
    public void testBubbleSortImprove(){
        System.out.println("bubble improve:");
        Sort.bubbleSortImprove(arrayCopy);
    }

    @Test
    public void testInsertionSort(){
        System.out.println("insertion:");
        Sort.insertionSort(arrayCopy);
    }

    @Test
    public void testQuickSort() {
        System.out.println("quick:");
        Sort.quickSort(arrayCopy);
    }

    @Test
    public void testArraySort() {
        System.out.println("array.sort");
        Arrays.sort(arrayCopy);
    }
}
