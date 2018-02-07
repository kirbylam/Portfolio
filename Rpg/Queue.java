// altered queue
public class Queue
{
  Node head;
  Node tail;
  int len; // keeps track of length of queue

  public Queue()
  {
    head = null;
    tail = null;
    len = 0;
  }

  public void enqueue(String name, long s)
  {
    Node n = new Node(name, s);
    this.len += 1;
    if (this.tail == null)
    {
      this.head = n;
      this.tail = n;
      return;
    }
    this.tail.next = n;
    this.tail = n;
    return;
  }

  // adds item to queue based on priority
  public void priorityEnqueue(String name, long s)
  {
    Node prev = this.head;
    Node curr = this.head;
    Node n = new Node(name, s);
    while (curr != null)
    {
      if (n.spd > curr.spd)
      {
        this.len += 1;
        if (curr == prev)
        {
          n.next = curr;
          this.head = n;
          return;
        }
        else
        {
          prev.next = n;
          n.next = curr;
          return;
        }
      }
      prev = curr;
      curr = curr.next;
    }
    enqueue(name, s);
    return;
  }
  
  public Node dequeue()
  {
    if (this.head != null)
    {
      Node curr = this.head;
      this.head = this.head.next;
      this.len -= 1;
      return curr;
    }
    return null;
  }

  public void deleteItm(String s)
  {
    Node prev = this.head;
    Node curr = this.head;
    this.len -= 1;
    while (curr != null)
    {
      if (curr.enty.equalsIgnoreCase(s))
      {
        if (prev == curr)
        {
          this.head = this.head.next;
          return;
        }
        else
        {
          prev.next = curr.next;
          if (curr == this.tail)
          {
            this.tail = prev;
          }
          return;
        }
      }
      prev = curr;
      curr = curr.next;
    }
    return;
  }
}
