import java.util.*;
import java.lang.Math;

public class Radix{
  public static void radixsort(int[] data){
    if (data.length == 0) return;
    double max = getMax(data);
    @SuppressWarnings({"rawtypes", "unchecked"})
    MyLinkedList<Integer>[] buckets = new MyLinkedList[20];
    for (int i =0; i < 20; i++){
      buckets[i] = new MyLinkedList<Integer>();
    }
    for (int i = 0; i <= max; i++){
      //first pass through the array
      if (i == 0){
        for (int idx = 0; idx < data.length; idx++){
          int digit = data[idx] % 10;
          buckets[digit + 10].add(data[idx]);
        }
      }
      else{
        for (int idx = 0; idx < data.length; idx++){
          //get the digit
          int num = buckets[0].removeFront();
          int digit = (num / (int)Math.pow(10,i)) % 10;
          buckets[digit + 10].add(data[idx]);
        }
      }
      for (int k = 1; k < 20; k++){
         buckets[0].extend(buckets[k]);
      }
    }
    for (int i = 0; i < data.length; i++){
      data[i] = buckets[0].removeFront();
    }
  }
  //find max of the data and return how many passes radix sort will need
  private static int getMax(int[] data){
    double max = data[0];
    for (int i : data){
      if (i > max) max = i;
    }
    int count = 1;
    while (max >= 10){
      max /= 10;
      count++;
    }
    return count;
  }
  //debuggingg
  public static String linkedString(MyLinkedList<Integer>[] data){
    String output = "";
    for (int i = 10; i < data.length; i++){
      output += data[i].toString() + " ";
    }
    return output;
  }
  public static void main(String[] args){
    int[] arr = new int[]{100,1124,12412,91,32,134};
    radixsort(arr);
    System.out.println(Arrays.toString(arr));
  }
}
