// Parent class for character
public class Character extends Entity
{
  String name;
  String job;
  int level;
  int str;
  int dex;
  int wis;
  int mxMp;
  int mnMp;
  int exp;
  int[] xpNextLvl = {0, 5, 10, 15, 20, 25}; // array for xp needed to lvl up
  Weapon equip;

  public Character(String n)
  {
    name = n;
    job = "";
    level = 1;
    str = 5;
    dex = 5;
    wis = 5;
    mxHp = 10;
    mnHp = 10;
    mxMp = 10;
    mnMp = 10;
    calcSpd();
    calcDmg();
    exp = 0;
    equip = null;
  }

  // exp gained after each battle
  public void getExp(int eGain)
  {
    this.exp += eGain;
    System.out.println(" You got " + eGain + " exp!");
    if (this.exp == xpNextLvl[this.level])
    {
      levelUp();
    }
    calcSpd();
    calcDmg();
    return;
  }

  // level up. different for each class
  public void levelUp()
  {
    return;
  }

  public void calcDmg()
  {
    atk = str;
    return;
  }

  // calculates speed based on dex
  private void calcSpd()
  {
    spd = (long) (dex * 1.5);
    return;
  }
}
