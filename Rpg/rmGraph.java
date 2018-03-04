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
    if (this.array[s].head == null)
    {
      this.array[s].Insert(s);
    }
    this.array[s].Insert(d);
    if (this.array[d].head == null)
    {
      this.array[d].Insert(d);
    }
    this.array[d].Insert(s);
    return;
  }
}
