import java.io.*;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.Random;

class Rpg
{
  public static Scanner UI;

  public static Character player;

  public static Random rand;

  public static Potion pot = new Potion();

  // prints status during a battle
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

  // returns a random monster
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

  public static int calcDmg(Entity e)
  {
    int dmg = rand.nextInt(e.atk[0]) + e.atk[1];
    return dmg;
  }

  // Options when in battle
  public static String battleOption()
  {
    boolean valid = false;
    while (valid == false)
    {
      System.out.println("\n What will you do?");
      System.out.println("  Fight Item Run");
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
        default: // if the user inputs an incorrect command
          System.out.println(" Not valid!");
          break;
      }
    }
    return "Error";
  }

  // method for a random encounter
  public static boolean battle()
  {
    int expEarn = 0;
    int nMobs = rand.nextInt(2) + 1;
    Queue turns = new Queue(); // creates a new queue
    // creates an array of Monsters, size is random
    Monster[] mobs = new Monster[nMobs]; 
    for (int i = 0; i < nMobs; i += 1)
    {
      mobs[i] = randMon(i + 1);
      expEarn += mobs[i].exp;
    }
    turns.enqueue(player.name, player.spd); // enqueues player into queue
    for (int i = 0; i < nMobs; i += 1)
    {
      // enqueues mobs into array based on spd (priority)
      turns.priorityEnqueue(mobs[i].name, mobs[i].spd);
    }
    // while there are more than 1 entities alive in battle
    while (turns.len > 1)
    {
      qNode atkr = turns.dequeue(); // dequeues queue (current turn)
      if (atkr.enty.equals(player.name)) // if current turn is player's
      {
        printStatus(mobs); // prints status
        String action = battleOption(); // asks for battle option
        switch(action)
        {
          case "attack":
            boolean valid = false;
            while (valid == false)
            {
              // asks player to choose target b/n 1+ monsters
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
                  int dmg = calcDmg(player);
                  System.out.print("\n You did " + dmg + " damage to ");
                  System.out.println(mobs[i].name);
                  mobs[i].mnHp -= dmg;
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
            int dmg = calcDmg(mobs[i]);
            System.out.print(" " + mobs[i].name + " attacks and does ");
            System.out.println(dmg + " damage!");
            player.mnHp -= dmg;
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

  // creates the starting map
  public static rmGraph createMap()
  {
    rmGraph g = new rmGraph(7);
    g.addRm(0, 1);
    g.addRm(1, 2);
    g.addRm(2, 3);
    g.addRm(1, 4);
    g.addRm(4, 5);
    g.addRm(5, 6);
    for (int i = 0; i < 7; i += 1)
    {
      g.array[i].head.rmName = "Block" + i;
    }
    g.array[3].head.chest = 1;
    g.array[5].head.chest = 1;
    return g;
  }

  public static rmNode rmOption(rmGraph g, rmNode currRm)
  {
    boolean nextRoom = false; // did the player move to the next room yet?
    System.out.println(" \nWhat will you do?");
    System.out.println("  Move Search Inventory");
    while (nextRoom == false)
    {
      UI = new Scanner(System.in);
      String choice = UI.next().toLowerCase();
      switch (choice)
      {
        case "move":
          boolean valid = false;
          System.out.println(" Choose room to travel to:");
          rmNode adjRm = currRm.next;
          while (adjRm != null)
          {
            System.out.print(g.array[adjRm.index].head.rmName + " ");
            adjRm = adjRm.next;
          }
          System.out.print("back\n");
          while (valid == false)
          {
            UI = new Scanner(System.in);
            choice = UI.next().toLowerCase();
            if (choice.equals("back"))
            {
              valid = true;
            }
            else
            {
              adjRm = currRm.next;
              while (adjRm != null)
              {
                if(g.array[adjRm.index].head.rmName.toLowerCase().equals(choice))
                {
                  currRm = g.array[adjRm.index].head;
                  return currRm;
                }
                adjRm = adjRm.next;
              } 
              System.out.println("Not a valid destination!");
            }
          }
          break;
        case "search":
          System.out.println("You look around the room...");
          if (currRm.chest >= 1)
          {
            System.out.println("You see a chest! Would you like to open it?");
            UI = new Scanner(System.in);
            choice = UI.next().toLowerCase();
            if (choice.equals("yes") || choice.equals("y"))
            {
              System.out.println("You opened the chest and got a potion!");
              player.inventory.put(pot, 1);
            }
            else if (choice.equals("no") || choice.equals("n"))
            {
              System.out.println("You chose not to open the chest."); 
            }
            else
            {
              System.out.println("Not a valid choice!");
            }
          }
          break;
        case "inventory":
          Enumeration<Item> itms = player.inventory.keys();
          while (itms.hasMoreElements())
          {
            Item itm = itms.nextElement();
            System.out.println(itm.name + " " + player.inventory.get(itm));
          }
          break;
        case "back":
          nextRoom = true;
          break;
        default:
          System.out.println("Not a valid choice!");
          break;
      }
    }
    return currRm;
  }

  public static void main(String args[])
  {
    boolean valid = false;
    boolean boss = false; // has the player defeated the boss yet?
    System.out.println(" Enter your name");
    UI = new Scanner(System.in);
    rand = new Random();
    String name = UI.next();
    Weapon starter = null;
    while (valid == false)
    {
      System.out.println("\n Choose your job: Warrior Mage Archer");
      UI = new Scanner(System.in);
      String job = UI.next().toLowerCase();
      if (job.equals("archer"))
      {
        player = new Archer(name);
        starter = new Weapon("Bow", 2, 4, 1, 2, 0, 0, 0);
        valid = true;
      }
      else if (job.equals("mage"))
      {
        player = new Mage(name);
        starter = new Weapon("Staff", 1, 2, 1, 7, 0, 0, 0);
        valid = true;
      }
      else if (job.equals("warrior"))
      {
        player = new Warrior(name);
        starter = new Weapon("Sword", 1, 5, 1, 1, 0, 0, 0);
        valid = true;
      }
      else
      {
        System.out.println("\n Not a valid job. Please try again.");
      }
    }
    player.inventory.put(pot, 1);
    player.equip = starter;
    rmGraph map = createMap();
    rmNode currRm = map.array[0].head;
    if (battle() == false)
    {
      System.out.println(" Game Over!");
      return;
    }
    currRm.visited = true;
    while (boss == false)
    {
      System.out.println(" Current Location: ");
      System.out.println("  " + currRm.rmName);
      currRm = rmOption(map, currRm);
      if (currRm.visited == false)
      {
        if (battle() == false)
        {
          System.out.println(" Game Over!");
          return;
        }
        currRm.visited = true;
      }
    }
  }
}
