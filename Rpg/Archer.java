// Archer subclass
public class Archer extends Character
{
  public Archer(String n)
  {
    super(n);
    job = "Archer";
  }

  public void levelUp()
  {
    System.out.println("You leveled up!");
    this.level += 1;
    this.mxHp += 6;
    this.mxMp += 6;
    this.mnHp = this.mxHp;
    this.mnMp = this.mxMp;
    this.str += 1;
    this.dex += 2;
    this.exp = 0;
    System.out.println(" Your hp went up by 6!\n You mp went up by 6!");
    System.out.println(" Your dex went up by 2!\n Your str went up by 1!");
    if ((this.level % 2) == 0)
    {
      this.wis += 1;
      System.out.println(" You wis went up by 1!");
    }
  }
}
