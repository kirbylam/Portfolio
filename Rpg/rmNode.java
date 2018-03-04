// Node for room (adjacent list node) 
public class rmNode
{
  int index; // index in array
  String rmName; // name of room
  int chest; // number of chests in the room
  boolean visited;
  rmNode next;

  public rmNode(int i)
  {
    this.index = i;
    this.rmName = "";
    this.chest = 0;
    this.visited = false;
    this.next = null;
  }
}

