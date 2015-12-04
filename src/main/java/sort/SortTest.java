package sort;

/**
 * Created by pc9507 on 2015/11/28.
 * 冒泡排序
 * 选择排序
 * 快速排序
 * 三种排序算法
 */
public class SortTest {
    public static int arr[];

    public static void main(String[] args) {
        arr = new int[]{61, 17, 29, 22, 34, 60, 72, 21, 50, 1, 62};
        bubbleSort(arr);
        selectionSort(arr);
        quickSort(0,arr.length-1);
    }

    public static void swap(int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public static void print(int arr[]){
        for (int i= 0 ;i < arr.length ; i++){
            System.out.print(arr[i]+",");
        }
            System.out.println("");
    }
    public static void bubbleSort(int [] arr){
        int i,j,len = arr.length;
        for (i = 0; i < len - 1; i++ ){
            for(j = 0; j < len -i - 1; j++ ){
                if (arr[j] > arr[j+1]){
                    swap(j,j+1);
                }
            }
        }
        System.out.print("Bubble_Sort------");
        print(arr);
    }
    public static void selectionSort(int []arr){
        int i,j,max,len = arr.length;
        for ( i = 0 ; i < len -1 ; i++){
            max = i;
            for( j = 0 ; j < len - i - 1; j++){
                if (arr[j] > arr[max]){
                    swap(max , j);
                }
            }
        }
        System.out.print("Selection_Sort------");
        print(arr);
    }

    public static void quickSort(int start, int end) {
        if (start >= end)
            return;
        int mid = arr[end];
        int left = start, right = end - 1;
        while (left < right) {
            while (arr[left] < mid && left < right)
                left++;
            while (arr[right] >= mid && left < right)
                right--;
            swap(left, right);
        }
        if (arr[left] >= arr[end]){
            swap(left, end);
        }
        else{
            left++;
        quickSort(start, left - 1);
        quickSort(left + 1, end);
        }

        System.out.print("Quick_Sort------");
        print(arr);
    }

}
