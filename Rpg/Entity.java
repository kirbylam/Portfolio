// super class that groups the player and monsters
public class Entity
{
  String name;
  int mxHp;
  int mnHp;
  int atk;
  long spd;

  public Entity()
  {
    name = "";
    mxHp = 0;
    mnHp = 0;
    atk = 0;
    spd = 0;
  }
}
