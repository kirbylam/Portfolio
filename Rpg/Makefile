JAVASRC = Rpg.java Entity.java Character.java Archer.java\
	  Mage.java Warrior.java Monster.java Slime.java Bat.java\
	  Queue.java qNode.java rmNode.java rmList.java rmGraph.java\
	  Item.java Weapon.java Consumable.java Potion.java 
SOURCES = Makefile $(JAVASRC)
MAINCLASS = Rpg
CLASSES = Rpg.class Character.class Entity.class Archer.class\
	  Warrior.class Mage.class Monster.class Slime.class Bat.class\
	  Queue.class qNode.class rmNode.class rmList.class rmGraph.class\
	  Item.class Weapon.class Consumable.class Potion.class
JARFILE = Rpg.jar 

all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm Manifest
 
$(CLASSES): $(JAVASRC)
	javac -Xlint $(JAVASRC)
 
clean:
	rm $(CLASSES) $(JARFILE)

