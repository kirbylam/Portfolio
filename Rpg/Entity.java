// super class that groups the player and monsters
public class Entity
{
  String name;
  int mxHp;
  int mnHp;
  int[] atk; // atk rng (normal attacks only; 0th elem is min, 1st elem is max)
  long spd;

  public Entity()
  {
    name = "";
    mxHp = 0;
    mnHp = 0;
    atk = new int[2];
    spd = 0;
  }
}
