JAVAC = /usr/bin/javac
JAVA=/usr/bin/java
.SUFFIXES: .java .class
SRCDIR= src
BINDIR=bin

$(BINDIR)/%.class:$(SRCDIR)/%.java
	$(JAVAC) -d $(BINDIR)/ -cp $(BINDIR) -sourcepath $(SRCDIR) $<


CLASSES= GenericsKbAVLApp.class ActionsAVL.class AVLTree.class AVLNode.class
CLASS_FILES=$(CLASSES:%.class=$(BINDIR)/%.class)

default: $(CLASS_FILES)

clean:
	rm $(BINDIR)/*.class

run: $(CLASS_FILES)
	$(JAVA) -cp bin Assignment2
	$(JAVA) -cp bin GenericsKbAVLApp | tee -a outputs.txt

jdoc: $(CLASS_FILES)
	javadoc -d docs src/*java
