JFLAGS = -g
JC = javac
JVM= java
FILE=
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java
CLASSES = \
	Skreat.java \
	Customer.java \
	Skin.java \
	Product.java \
	Treatment.java \
	Cleanser.java \
	Freshner.java \
	Gel.java \
	Oil.java \
	Moisturizer.java \
	Cream.java 

MAIN = Skreat

default:	classes

classes:	$(CLASSES:.java=.class)

run: classes
	$(JVM) $(MAIN)

clean:
	$(RM) *.class
