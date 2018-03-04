// Parent class for character

import java.util.Enumeration;
import java.util.Hashtable;
import java.lang.Math.*;

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
  Hashtable<Item, Integer> inventory = new Hashtable<Item, Integer>();
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
    calcDRng();
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
    calcDRng();
    return;
  }

  // level up. different for each class
  public void levelUp()
  {
    return;
  }

  // calculates damage range
  public void calcDRng()
  {
    int additive = (int) Math.ceil(str / 2);
    if (equip == null) // if the player has no weapon equiped
    {
      atk[0] = 1 + additive;
      atk[1] = 5 + additive;
      return;
    }
    atk[0] = additive + equip.atkRng[0];
    atk[1] = additive + equip.atkRng[1];
    return;
  }

  // calculates speed based on dex
  private void calcSpd()
  {
    spd = (long) (dex * 1.5);
    return;
  }
}
