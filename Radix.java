import java.util.*;

public class Radix{
  public static void radixsort(int[] data){
    @SuppressWarnings({"rawtypes", "unchecked"})
    MyLinkedList<Integer>[] buckets = new MyLinkedList[20];
    for (int i =0; i < 20; i++){
      buckets[i] = new MyLinkedList<Integer>();
    }
    MyLinkedList<Integer> temp = new MyLinkedList<Integer>();
    int count = getMax(data);
    //System.out.println(count);
    int base = 1;
    for(int i = 0; i < count; i++){
      //first pass through the array
      if (i == 0){
        for (int idx = 0; idx < data.length; idx++){
          int digit = (data[idx] / base) % 10;
          //System.out.println(digit);
          //sort the positives
          if (data[idx] >= 0){
            buckets[digit + 10].add(data[idx]);
          }
          //sort the negatives
          else{
            buckets[9 - digit].add(data[idx]);
          }
        }
        //clear temp
        temp.clear();
        /**for (int c = 0; c < 20; c++){
          System.out.println(buckets[c].toString());
        }**/
        //use temp and extend the buckets to this
        for (int j = 0; j < 20; j++){
          temp.extend(buckets[j]);
          //System.out.println(temp.toString());
        }
        //clear the buckets for another pass
        for (MyLinkedList<Integer> m : buckets){
          m.clear();
        }
      }
      //other passes
      else{
        for (int idx = 0; idx < data.length; idx++){
          //get the digit
          int num = temp.removeFront();
          int digit = (num / base) % 10;
          System.out.println("pass #: " + i + " base: " + base + " num: " + num + " digit: " + digit);
          //sort the positives
          if (data[idx] >= 0){
            buckets[digit + 10].add(data[idx]);
          }
          //sort the negatives
          else{
            buckets[9 - digit].add(data[idx]);
          }
        }
        temp.clear();
        //copy from buckets to temp and extend
        for (int j = 0; j < 20; j++){
          temp.extend(buckets[j]);
        }
        //clear the buckets for another pass
        for (MyLinkedList<Integer> m : buckets){
          m.clear();
        }
      }
      base *= 10;
    }
    //copy from linked list to original array
    for (int i = 0; i < data.length; i++){
      data[i] = temp.removeFront();
    }
  }
  //find max of the data and return how many passes radix sort will need
  private static int getMax(int[] data){
    int max = data[0];
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
  public static void test(){
    //int[] arr = new int[]{12,199,13334};
    //System.out.println(getMax(arr));
    System.out.println("Size\t\tMax Value\tquick/builtin ratio ");
    int[]MAX_LIST = {1000000000,500,10};
    for(int MAX : MAX_LIST){
      for(int size = 31250; size < 2000001; size*=2){
        long qtime=0;
        long btime=0;
        //average of 5 sorts.
        for(int trial = 0 ; trial <=5; trial++){
          int []data1 = new int[size];
          int []data2 = new int[size];
          for(int i = 0; i < data1.length; i++){
            data1[i] = (int)(Math.random()*MAX);
            data2[i] = data1[i];
          }
          long t1,t2;
          t1 = System.currentTimeMillis();
          radixsort(data2);
          t2 = System.currentTimeMillis();
          qtime += t2 - t1;
          t1 = System.currentTimeMillis();
          Arrays.sort(data1);
          t2 = System.currentTimeMillis();
          btime+= t2 - t1;
          if(!Arrays.equals(data1,data2)){
            System.out.println("FAIL TO SORT!");
            System.exit(0);
          }
        }
        System.out.println(size +"\t\t"+MAX+"\t"+1.0*qtime/btime);
      }
      System.out.println();
    }
  }
  public static void main(String[] args){
    int[] arr = new int[]{100,1124,12412,91,32,134};
    radixsort(arr);
    System.out.println(Arrays.toString(arr));
  }
}
