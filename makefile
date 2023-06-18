# makefile begins
# define a variable for compiler flags (JFLAGS)
# define a variable for the compiler (JC)
# define a variable for the Java Virtual Machine (JVM)
# define a variable for de JVM options, in the case of JavaFX
# 	these are --module-path and --add-modules. For the first one, make sure
# 	to use the location where you have JavaFX lib directory.
# define a variable for a parameter. When you run make, you could use:
# make run FILE="Algo.csv" to overwrite its value in this file.
#

JFLAGS = -g
JC = javac
JVM= java
JavaFX = /mnt/c/Users/sofia/downloads/javafx-sdk-20.0.1/lib
JavaSource = "/mnt/c/users/sofia/OneDrive/Escritorio/1° semestre 2023/POO/Proyecto - copia/Proyecto2/src"
JavaEmail = /mnt/c/Users/sofia/Downloads/mail
JFX_OPTIONS = --module-path /mnt/c/Users/sofia/downloads/javafx-sdk-20.0.1/lib --add-modules javafx.controls
CP_OPTIONS_ComoIntelliJ = -cp $(JavaSource):$(JavaFX)/javafx-swt.jar:$(JavaFX)/javafx.web.jar:$(JavaFX)/javafx.base.jar:$(JavaFX)/javafx.fxml.jar:$(JavaFX)/javafx.media.jar:$(JavaFX)/javafx.swing.jar:$(JavaFX)/javafx.controls.jar:$(JavaFX)/javafx.graphics.jar:$(JavaEmail)/activation-1.1.jar:$(JavaEmail)/javaee-api-8.0.1.jar:$(JavaEmail)/javax.mail-1.6.2.jar
CP_OPTIONS = -cp $(JavaSource):$(JavaFX)/*.jar:$(JavaEmail)/activation-1.1.jar:$(JavaEmail)/javaee-api-8.0.1.jar:$(JavaEmail)/javax.mail-1.6.2.jar

FILE="config.txt"

#
# Clear any default targets for building .class files from .java files; next, we
# provide our own target entry to do this in this makefile.
# make has a set of default targets for different suffixes (like .c.o)
# Currently, clearing the default for .java.class is not necessary since
# make does not have a definition for this target, but later versions of
# make may, so it doesn't hurt to make sure that we clear any default
# definitions for these
#

.SUFFIXES: .java .class

#
# Here is our target entry for creating .class files from .java files
# This is a target entry that uses the suffix rule syntax:
#	DSTS:
#		rule
#	DSTS (Dependency Suffix     Target Suffix)  Ej.:.java.class
# 'TS' is the suffix of the target file (ej. .class), 'DS' is the suffix of the dependency (ej. .java)
# file, and 'rule'  is the rule for building a target
# '$*' is a built-in macro that gets the basename of the current target
# Remember that there must be a <tab> before the command line ('rule')
# you may need to check that your editor is not replacing <tab> by spaces
#

.java.class:
	$(JC) $(JFLAGS) $(JFX_OPTIONS) $(CP_OPTIONS) $*.java

#
# CLASSES is a macro consisting of N words (one for each java source file)
# When a single line is too long, use \<return> to split lines that then will be
# considered as a single line. For example:
# NAME = Camilo \
         Juan
# is understood as
# NAME = Camilo        Juan

CLASSES = \
	Alumno.java \
	Apoderado.java \
	Asignatura.java \
	AsignaturaView.java \
	Colegio.java \
	Curso.java \
	EnvioCorreo.java \
	EnvioCorreos.java \
	EvalAddView.java \
	Evaluacion.java \
	LoginView.java \
	NotaAddView.java \
	Persona.java \
	Profesor.java \
	ProfesorView.java \
	Proyecto.java


#
# MAIN is a variable with the name of the file containing the main method
#

MAIN = Proyecto

#
# the default make target entry
# for this example it is the target classes

default: classes

#
# Next line is a target dependency line
# This target entry uses Suffix Replacement within a macro:
# $(macroname:string1=string2)
# In the words in the macro named 'macroname' replace 'string1' with 'string2'
# Below we are replacing the suffix .java of all words in the macro CLASSES
# with the .class suffix
#

classes: $(CLASSES:.java=.class)

#
# Next two lines contain a target for running the program
# Remember the tab in the second line.
# $(JMV) y $(MAIN) are replaced by their values
#

run: $(MAIN).class
	$(JVM) $(JFX_OPTIONS) $(CP_OPTIONS) $(MAIN) $(FILE)

#
# this line is to remove all unneeded files from
# the directory when we are finished executing(saves space)
# and "cleans up" the directory of unneeded .class files
# RM is a predefined macro in make (RM = rm -f)
#

clean:
	$(RM) *.class

