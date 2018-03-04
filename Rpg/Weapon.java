// class for weapons

public class Weapon
{
  String name;
  int[] atkRng = new int[2];
  int[] mgcRng = new int[2];
  int[] reqStat = new int[3]; // [<str>, <dex>, <wis>]

  public Weapon()
  {
    name = "";
  }
  // n: name, aMin/aMax: minimum & maximum attack range, 
  // mMin & mMax: min & max magic range, sReq: required strength for weapon,
  // dReq: required dex for weapon, mReq: required magic for weapon
  public Weapon(String n, int aMin, int aMax, int mMin, int mMax, int sReq, int dReq, int mReq)
  {
    name = n;
    atkRng[0] = aMin;
    atkRng[1] = aMax;
    mgcRng[0] = mMin;
    mgcRng[1] = mMax;
    reqStat[0] = sReq;
    reqStat[1] = dReq;
    reqStat[2] = mReq;
  }
}

