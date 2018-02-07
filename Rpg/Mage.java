// subclass for mage
public class Mage extends Character
{
  public Mage(String n)
  {
    super(n);
    job = "Mage";
  }

  public void levelUp()
  {
    System.out.println(" You leveled up!");
    this.level += 1;
    this.mxHp += 5;
    this.mxMp += 5;
    this.mnHp = this.mxHp;
    this.mnMp = this.mxMp;
    this.wis += 2;
    this.dex += 1;
    this.exp = 0;
    System.out.println(" Your hp went up by 5!\n You mp went up by 5!");
    System.out.println(" Your wis went up by 2!\n Your dex went up by 1!");
    if ((this.level % 2) == 0)
    {
      this.str += 1;
      System.out.println(" You wis went up by 1!");
    }
  }
}
