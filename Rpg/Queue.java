// altered queue
// "hybrid" queue. Does priority enqueueing and normal queueing
public class Queue
{
  qNode head;
  qNode tail;
  int len; // keeps track of length of queue

  public Queue()
  {
    head = null;
    tail = null;
    len = 0;
  }

  public void enqueue(String name, long s)
  {
    qNode n = new qNode(name, s);
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

  // adds item to queue based on priority (speed)
  public void priorityEnqueue(String name, long s)
  {
    qNode prev = this.head;
    qNode curr = this.head;
    qNode n = new qNode(name, s);
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
  
  public qNode dequeue()
  {
    if (this.head != null)
    {
      qNode curr = this.head;
      this.head = this.head.next;
      this.len -= 1;
      return curr;
    }
    return null;
  }

  public void deleteItm(String s)
  {
    qNode prev = this.head;
    qNode curr = this.head;
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
