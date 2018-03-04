// Linked List of rooms (ll of adjacent list nodes)

public class rmList
{
  rmNode head, tail;

  public rmList()
  {
    this.head = null;
    this.tail = null;
  }

  public void Insert(int i)
  {
    rmNode newNode = new rmNode(i);
    if (head == null)
    {
      head = newNode;
      tail = newNode;
      return;
    } 
    tail.next = newNode;
    tail = tail.next;
    return;
  }
}
