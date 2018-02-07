public class Weapon
{
  String name;
  int[] atkRng = new int[2];
  int[] mgcRng = new int[2];
  int[] reqStat = new int[3]; // [<str>, <dex>, <wis>]

  public Weapon(String n, int[] aRng, int[] mRng, int[] req)
  {
    name = n;
    atkRng = aRng;
    mgcRng = mRng;
    reqStat = req;
  }
}

