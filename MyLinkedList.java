public class MyLinkedList<E>{

  private Node start,end;
  private int length;
  private class Node{

    private E data;
    private Node next, prev;

    //returns the next node
    public Node next(){
      return next;
    }
    //return the previous node
    public Node prev(){
      return prev;
    }
    //sets next node to param other
    public void setNext(Node other){
      next = other;
    }
    //sets prev node to param other
    public void setPrev(Node other){
      prev = other;
    }
    //return data
    public E getData(){
      return data;
    }
    //set data to i
    public E setData(E i){
      return data = i;
    }
    //return string representation of node
    public String toString(){
      return data + "";
    }
  }

  //constructor
  //empty list
  public MyLinkedList(){
    start = new Node();
    end = new Node();
  }
  //make a new node that contains the value
  //then set the end to the new node
  //if the first element is null, then set the start to value
  public boolean add(E value){
    Node newNode = new Node();
    newNode.setData(value);
    if (length == 0){
      start = newNode;
      end = newNode;
      length++;
      return true;
    }
    end.setNext(newNode);
    newNode.setPrev(end);
    end = newNode;
    length++;
    return true;
  }
  //use getNthNode to get the node at the desired index
  //then set the data at the node to the new value
  public E set(int index, E value){
    if (index >= length || index < 0) throw new IndexOutOfBoundsException();
    E num = getNthNode(index).getData();
    getNthNode(index).setData(value);
    return num;
  }
  //use getNthNode to get the node at the index then return data
  public E get(int index){
    if (index >= length || index < 0) throw new IndexOutOfBoundsException();
    return getNthNode(index).getData();
  }
  //get the nth node of the linked MyLinkedList
  private Node getNthNode(int idx){
    if (idx >= length || idx < 0) throw new IndexOutOfBoundsException();
    Node current = start;
    int index = 0;
    while (current != null){
      if (index == idx){
        return current;
      }
      current = current.next();
      index++;
    }
    return current;
  }
  //if the index is at the beginning or the end, then set the start or end to the next/prev Node
  //else getNthNode of the prev node and next node and set the next and prev
  public E removeFront(){
    E val = start.getData();
    start = start.next();
    start.setPrev(null);
    length--;
    return val;
  }
  //string representation of the linked list
  public String toString(){
    String out = "[";
    Node current = start;
    for (int i = 0; i < length; i++){
      if (i == length - 1){
        out += current.getData();
      }
      else{
        out += current.getData() + ", ";
        current = current.next();
      }
    }
    return out + "]";
  }
  //concatenate two lists
  //set the start, end, length of the other list to null/0 to erase it
  //set the prev of the first node of other to the last node of the first list
  //set the next of the last node of the first list to the first node of other
  //set end to the last node of other
  public void extend(MyLinkedList other){
    if (length == 0){
      start = other.start;
      end = other.end;
    }
    else if (other.length > 0){
      other.start = start;
      end = other.end;
    }
    length += other.length;
    other.length = 0;
  }
}
