// Map of rooms (graph of rooms)

public class rmGraph
{
  int rm; // vertices (rooms)
  rmList[] array; 

  public rmGraph(int r) // number of rooms
  {
    this.rm = r;
    this.array = new rmList[r];
    for (int i = 0; i < r; i += 1)
    {
      this.array[i] = new rmList();
    }
  }

  public void addRm(int s, int d) // s-source, d-destination (add edge)
  {
    rmNode newNode = new rmNode(d);
    newNode.next = this.array[s].head;
    this.array[d].head = newNode;
    newNode = new rmNode(s);
    newNode.next = this.array[d].head;
    this.array[s].head = newNode;
    return;
  }
}
