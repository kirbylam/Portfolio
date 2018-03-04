// subclass for monsters
public class Monster extends Entity
{
  String name;
  int def;
  long spd;
  int exp;

  public Monster()
  {
    name = "";
    mxHp = 0;
    mnHp = 0;
    def = 0;
    spd = 0;
  }
}
