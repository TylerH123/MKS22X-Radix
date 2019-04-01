public class Radix{
  public static void radixsort(int[] data){
    MyLinkedList<Integer>[] buckets;
    buckets = new MyLinkedList[20];
    int count = getMax(data);
    int base = 10;
    for (int i = 0; i < count; i++){
      int digit = data[i] % base;
      if (data[i] >= 0){
        buckets[digit + 10].add(data[i]);
      }
      else{
        buckets[9 - digit].add(data[i]);
      }
      base *= 10; 
    }
  }
  //find max of the data and return how many passes radix sort will need
  private static int getMax(int[] data){
    int max = 0;
    for (int i = 0; i < data.length; i++){
      if (data[i] > max) max = data[i];
    }
    int count = 1;
    while (max >= 10){
      max /= 10;
      count++;
    }
    return count;
  }
  private static int getDigit(int num){
    return num % 10;
  }
}
