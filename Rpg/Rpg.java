import java.io.*;
import java.util.Scanner;
import java.util.Random;

class Rpg
{
  public static Scanner UI;

  public static Character player;

  public static Random rand;

  public static void printStatus(Monster[] mobs)
  {
    System.out.println("=============================================");
    System.out.print(" " + player.name);
    System.out.print(" Hp: " + player.mnHp + "/" + player.mxHp);
    System.out.println(" Mp: " + player.mnMp + "/" + player.mxMp);
    for (int i = 0; i < mobs.length; i += 1)
    {
      System.out.print("\n " + mobs[i].name);
      System.out.println(" Hp: " + mobs[i].mnHp + "/" + mobs[i].mxHp);
    }
    System.out.println("===================");
    return;
  }

  public static String battleOption()
  {
    boolean valid = false;
    System.out.println("\n What will you do?");
    System.out.println("  Fight Item Run");
    while (valid == false)
    {
      UI = new Scanner(System.in);
      String choice = UI.next();
      choice = choice.toLowerCase();
      switch(choice)
      {
        case "fight":
          boolean valid2 = false;
          while (valid2 == false)
          {
            System.out.println("\n What will you do?");
            System.out.println("  Attack Skill Back");
            UI = new Scanner(System.in);
            choice = UI.next();
            choice = choice.toLowerCase();
            if (choice.equals("attack") || choice.equals("skill"))
            {
              return choice;
            }
            else if (choice.equals("back"))
            {
              valid2 = true;
            }
            else
            {
              System.out.println(" Not a valid choice!");
            }
          }
          break;
        case "Item":
          return choice;
        case "Run":
          return choice; 
        default:
          System.out.println(" Not valid!");
          break;
      }
    }
    return "Error";
  }

  public static Monster randMon(int nb)
  {
    Monster mon = new Monster();
    String[] monList = {"slime"};
    String monName = monList[0];
    if (monName.equals("slime"))
    {
      String name = "slime" + Integer.toString(nb);
      mon = new Slime(name);
    }
    return mon;
  }

  public static boolean battle()
  {
    int expEarn = 0;
    int nMobs = 1; //rand.nextInt(2) + 1;
    Queue turns = new Queue();
    Monster[] mobs = new Monster[nMobs];
    for (int i = 0; i < nMobs; i += 1)
    {
      mobs[i] = randMon(i + 1);
      expEarn += mobs[i].exp;
    }
    turns.enqueue(player.name, player.spd);
    for (int i = 0; i < nMobs; i += 1)
    {
      turns.priorityEnqueue(mobs[i].name, mobs[i].spd);
    }
    while (turns.len > 1)
    {
      Node atkr = turns.dequeue();
      if (atkr.enty.equals(player.name))
      {
        printStatus(mobs);
        String action = battleOption();
        switch(action)
        {
          case "attack":
            boolean valid = false;
            while (valid == false)
            {
              System.out.print("\n Choose your target: ");
              for (int i = 0; i < nMobs; i += 1)
              {
                if (mobs[i].mnHp > 0)
                {
                  System.out.print(mobs[i].name + " ");
                }
              }
              System.out.print("\n");
              UI = new Scanner(System.in);
              String target = UI.next();
              for (int i = 0; i < nMobs; i += 1)
              {
                if (target.equalsIgnoreCase(mobs[i].name))
                {
                  valid = true;
                  System.out.print("\n You did " + player.atk + " damage to ");
                  System.out.println(mobs[i].name);
                  mobs[i].mnHp -= player.atk;
                  if (mobs[i].mnHp <= 0)
                  {
                    System.out.println(" You killed " + mobs[i].name + "!");
                    turns.deleteItm(target);
                  }
                }
              }
              if (valid == false)
              {
                System.out.println(" Not a valid target!");
              }
            }
            System.out.print("\n");
            break;
        }
      }
      else
      {
        for (int i = 0; i < nMobs; i += 1)
        {
          if (atkr.enty.equals(mobs[i].name))
          {
            System.out.print(" " + mobs[i].name + " attacks and does ");
            System.out.println(mobs[i].atk + " damage!");
            player.mnHp -= mobs[i].atk;
            if (player.mnHp <= 0)
            {
              System.out.println(" You died...");
              return false;
            }
          }
        }
      }
      turns.enqueue(atkr.enty, atkr.spd);
    }
    System.out.println("\n You defeated your enemies!");
    player.getExp(expEarn); 
    return true;
  }

  public static void main(String args[])
  {
    boolean valid = false;
    System.out.println(" Enter your name");
    UI = new Scanner(System.in);
    rand = new Random();
    String name = UI.next();
    while (valid == false)
    {
      System.out.println("\n Choose your job: Warrior Mage Archer");
      UI = new Scanner(System.in);
      String job = UI.next().toLowerCase();
      if (job.equals("archer"))
      {
        player = new Archer(name);
        valid = true;
      }
      else if (job.equals("mage"))
      {
        player = new Mage(name);
        valid = true;
      }
      else if (job.equals("warrior"))
      {
        player = new Warrior(name);
        valid = true;
      }
      else
      {
        System.out.println("\n Not a valid job. Please try again.");
      }
    }
    if (battle() == false)
    {
      System.out.println(" Game Over!");
      return;
    }
    if (battle() == false)
    {
      System.out.println(" Game Over!");
      return;
    }
  }
}
