import java.util.*;
@SuppressWarnings({"unchecked", "rawtypes"})

public class Radix2 {

  public static void radixsort(int[] data){
    int passes = maxL(data);
    MyLinkedList[] buckets = new MyLinkedList[20];
    MyLinkedList<Integer> temp = new MyLinkedList<Integer>();
    for (int i = 0; i < buckets.length; i++) {
      buckets[i] = new MyLinkedList<Integer>();
    }
    for (int i = 1; i <= passes; i++) {
      if (i == 1) {
        for (int x = 0; x < data.length; x++) {
          int num = data[x];
          int index = (int)(num / (Math.pow(10,i-1))) % 10;
          buckets[index+9].add(num);
        }
      //  System.out.println(Arrays.toString(buckets));
        temp.clear();
        for (int y = 0; y < 20; y++) {
        //  System.out.println("1: "+temp.toString());
          temp.extend(buckets[y]);
        //  System.out.println("T: "+temp.toString());
        }
      /*  for (int z = 0; z < data.length; z++) {
          data[z] = temp.remove(0);
        } */
        clearB(buckets);
    //    System.out.println(temp.toString());
      //  System.out.println("-----------\n"+Arrays.toString(buckets));
      } else {
        for (int x = 0; x < data.length; x++) {
          int num = temp.removeFront();
          int index = (int)(num / (Math.pow(10,i-1))) % 10;
          buckets[index+9].add(num);
        }
      //  System.out.println(Arrays.toString(buckets));
        temp.clear();
        for (int y = 0; y < 20; y++) {
      //    System.out.println("1: "+temp.toString());
          temp.extend(buckets[y]);
      //    System.out.println("T: "+temp.toString());
        }
      /*  for (int z = 0; z < data.length; z++) {
          data[z] = temp.remove(0);
        } */
        clearB(buckets);
      //  System.out.println(temp.toString());
      //  System.out.println("-----------\n"+Arrays.toString(buckets));
      }
    }
    for (int i = 0; i < data.length; i++) {
      data[i] = temp.removeFront();
    }
  //  System.out.println(Arrays.toString(data));
  }

  // returns number of passes necessary
  private static int maxL(int[] data) {
    if (data.length == 0) {
      return 0;
    }
    int result = data[0];
    for (int i : data) {
      if (i > result) {
        result = i;
      }
    }
    return (""+result).length();
  }

  private static void clearB(MyLinkedList<Integer>[] b) {
    for (MyLinkedList<Integer> c : b) {
      c.clear();
    }
  }

  public static void main(String[] args) {
    System.out.println("Size\t\tMax Value\tquick/builtin ratio ");
    int[]MAX_LIST = {10000,500,10};
    for(int MAX : MAX_LIST){
      for(int size = 31250; size < 500001; size*=2){
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

}
