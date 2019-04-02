public class Radix{
  public static void radixsort(int[] data){
    @SuppressWarnings({"rawtypes", "unchecked"})
    MyLinkedList<Integer>[] buckets = new MyLinkedList[20];
    for (int i =0; i < 20; i++){
      buckets[i] = new MyLinkedList<Integer>();
    }
    MyLinkedList<Integer> temp = new MyLinkedList<Integer>();
    int count = getMax(data);
    int base = 10;
    for(int i = 0; i < count; i++){
      //first pass through the array
      if (i == 0){
        for (int idx = 0; idx < data.length; idx++){
          int digit = data[idx] % base;
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
        //use temp and extend the buckets to this
        for (int j = 0; j < 20; j++){
          temp.extend(buckets[j]);
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
          int digit = temp.removeFront() % base;
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
  public static void main(String[] args){
    //int[] arr = new int[]{12,199,13334};
    //System.out.println(getMax(arr));
  }
}
