JAVASRC = Rpg.java Entity.java Character.java Archer.java\
	  Mage.java Warrior.java Monster.java Slime.java Queue.java\
	  Node.java Weapon.java 
SOURCES = Makefile $(JAVASRC)
MAINCLASS = Rpg
CLASSES = Rpg.class Character.class Entity.class Archer.class\
	  Warrior.class Mage.class Monster.class Slime.class Queue.class\
	  Node.class Weapon.class
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

