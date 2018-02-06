// Kirby Lam
// klam18
// Bard.java 
// This program takes in an input file and a file called "shakespeare.txt"
//  and prints out the word that is equivilent the length and rank given in the
//  input file.

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Set;

class WordRank
{
  public static Hashtable<String, Integer> words; // global var for hashtable

  public static ArrayList<Word> list; // global var for arraylist

  public static PrintWriter out; 

  // stores all the words and its frequencies in the hashtable
  public static void addHash(String[] line)
  {
    // iterates thru the array of the text
    for (int i = 0; i < line.length; i += 1) 
    {
      // if the word is already in the hashtable, increase frequency
      if (words.containsKey(line[i])) 
      {
        words.put(line[i], words.get(line[i]) + 1);
      }
      // else add word to hashtable
      else
      {
        words.put(line[i], 1);
      }
    }
    return;
  }

  // iterates thru the hashtable, finds words of the target length and adds
  //  the word and frequency to the arraylist
  public static void findWords(int l)
  {
    Enumeration<String> key; 
    key = words.keys(); // sets key to enumeration of all the keys in hashtable
    while (key.hasMoreElements()) // while there are still keys
    {
      Word newWord = new Word(key.nextElement()); // creates new word
      newWord.freq = words.get(newWord.word); // sets frequncy to correct amnt
      if (newWord.word.length() == l) // if the word is of the given length
      {
        list.add(newWord); // add word to arraylist
      }
    }
    return;
  }

  // bubble sorts the arraylist
  public static void sortList()
  {
    int len = list.size();
    while (len > 1)
    {
      for (int i = 1; i < len; i += 1)
      {
        Word prev = list.get(i - 1);
        Word curr = list.get(i);
        if (prev.freq < curr.freq)
        {
          list.set(i - 1, curr);
          list.set(i, prev);
        }
        if ((prev.freq == curr.freq) && (prev.word.compareTo(curr.word) > 0))
        {
          list.set(i - 1, curr);
          list.set(i, prev);
        }
      }
      // 'decrease' length of arraylist because Word at end has lowest frequency
      len = len - 1;
    }
    return;
  }

  public static void printList(int r)
  {
    if ((list.size() == 0) || (r > (list.size() - 1))) 
    {
      out.printf("-\n");
    }
    else
    {
      Word target = list.get(r);
      out.printf("%s %d\n", target.word, target.freq);
    }
    return;
  }

  public static void main(String[] files) throws IOException
  {
    if (files.length < 3)
    {
      System.out.print("Usage: java -jar Bard.jar ");
      System.out.print("<input file> <output file> <text file>\n");
      System.exit(1);
    }
    Scanner text = new Scanner(new File(files[2]));
    Scanner in = new Scanner(new File(files[0]));
    out = new PrintWriter(new FileWriter(files[1]));
    words = new Hashtable<String, Integer>();
    while (text.hasNextLine())
    {
      String line = text.nextLine();
      line = line.replace('?',' ');
      line = line.replace(',',' ');
      line = line.replace('.',' ');
      line = line.replace('!',' ');
      line = line.replace(':',' ');
      line = line.replace(';',' ');
      line = line.replace('[',' ');
      line = line.replace(']', ' ');
      line = line.toLowerCase();
      String[] tokens = line.trim().split("\\s+");
      addHash(tokens);
    }
    while (in.hasNextLine())
    {
      list = new ArrayList<Word>();
      String find = in.nextLine().trim() + " ";
      String[] val = find.split("\\s+");
      int len = Integer.parseInt(val[0]);
      int rank = Integer.parseInt(val[1]);
      findWords(len);
      sortList();
      if (len != 0)
      {
        printList(rank);
      }
      else
      {
        out.printf("-\n");
      }
    }
    text.close();
    in.close();
    out.close();
  }
}
