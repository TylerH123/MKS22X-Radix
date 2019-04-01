public class Radix{
  public static void radixsort(int[] data){
    MyLinkedList<Integer>[] buckets;
    buckets = new MyLinkedList[20];
    int max = getMax(data); 
  }
  private static int getMax(int[] data){
    int max = 0;
    for (int i = 0; i < data.length; i++){
      if (data[i] > max) max = data[i];
    }
    return max;
  }
}
