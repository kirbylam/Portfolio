// subclass  for warrior
public class Warrior extends Character
{
  public Warrior(String n)
  {
    super(n);
    job = "Warrior";
  }

  public void levelUp()
  {
    System.out.println(" You leveled up!");
    this.level += 1;
    this.mxHp += 10;
    this.mxMp += 5;
    this.mnHp = this.mxHp;
    this.mnMp = this.mxMp;
    this.str += 2;
    this.dex += 1;
    this.exp = 0;
    System.out.println(" Your hp went up by 10!\n You mp went up by 5! ");
    System.out.println(" Your str went up by 2!\n Your dex went up by 1!");
    if ((this.level % 2) == 0)
    {
      this.wis += 1;
      System.out.println(" You wis went up by 1!");
    }
  }
}
