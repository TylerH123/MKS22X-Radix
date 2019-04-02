public class Radix{
  public static void radixsort(int[] data){
    @SuppressWarnings({"rawtypes", "unchecked"})
    MyLinkedList<Integer>[] buckets = new MyLinkedList[20];
    for (int i =0; i < 20; i++){
      buckets[i] = new MyLinkedList<Integer>();
    }
    int count = getMax(data);
    int base = 10;
    int idx = 0;
    for(int i = 0; i < count * data.length; i++){
      if (idx == data.length){
        idx = 0;
        for (int j = 0; i < 19; i++){
          buckets[0].extend(buckets[j+1]);
        }
      }
      int digit = data[idx] % base;

      if (data[i] >= 0){
        buckets[digit + 10].add(data[i]);
      }
      else{
        buckets[9 - digit].add(data[i]);
      }
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
  public static void main(String[] args){
    //int[] arr = new int[]{12,199,13334};
    //System.out.println(getMax(arr));
  }
}
