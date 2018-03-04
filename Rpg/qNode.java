// node for queue
public class qNode 
{
  String enty;
  long spd;
  qNode next;

  public qNode(String e, long s)
  {
    enty = e;
    spd = s;
    next = null;
  }
}
