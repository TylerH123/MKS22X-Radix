public class Radix{
  public static void radixsort(int[] data){
    MyLinkedList<Integer>[] buckets;
    buckets = new MyLinkedList[20];
    int count = getMax(data);
    int base = 10;
    int idx = 0;
    for(int i = 0; i < count * data.length; i++){
      if (idx == data.length){
        idx = 0;
        buckets[0].extend(buckets[1]);
        buckets[0].extend(buckets[2]);
        buckets[0].extend(buckets[3]);
        buckets[0].extend(buckets[4]);
        buckets[0].extend(buckets[5]);
        buckets[0].extend(buckets[6]);
        buckets[0].extend(buckets[7]);
        buckets[0].extend(buckets[8]);
        buckets[0].extend(buckets[9]);
        buckets[0].extend(buckets[10]);
        buckets[0].extend(buckets[11]);
        buckets[0].extend(buckets[12]);
        buckets[0].extend(buckets[13]);
        buckets[0].extend(buckets[14]);
        buckets[0].extend(buckets[15]);
        buckets[0].extend(buckets[16]);
        buckets[0].extend(buckets[17]);
        buckets[0].extend(buckets[18]);
        buckets[0].extend(buckets[19]);
        buckets[0].extend(buckets[20]);
        for (int j = 0; j < data.length; j++){
          data[j] = buckets[0].getData();
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
}
