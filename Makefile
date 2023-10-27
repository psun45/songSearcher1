runBDTests: BackendDeveloperTests.class
	java -jar ../junit5.jar -cp . -c BackendDeveloperTests
BackendDeveloperTests.class:
	javac -cp .:../junit5.jar BackendDeveloperTests.java

runFDTests: FrontendDeveloperTests.class
	java -jar ../junit5.jar -cp . -c FrontendDeveloperTests

FrontendDeveloperTests.class:
	javac -cp ../junit5.jar: FrontendDeveloperTests.java
compileFiles: *.java
	javac *.java
clean:
	rm -rf *.class
