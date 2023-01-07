jc : 
	javac -classpath src/ --module-path ${JAVAFX_HOME}/lib --add-modules=javafx.base,javafx.controls,javafx.fxml src/main/java/*.java
j :
	java -classpath src/ --module-path ${JAVAFX_HOME}/lib --add-modules=javafx.base,javafx.controls,javafx.fxml main.java.Main

clean :
	rm src/main/java/*.class