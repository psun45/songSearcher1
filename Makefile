runFDTests: FrontendDeveloperTests.class
	java -jar ../junit5.jar -cp . -c FrontendDeveloperTests

runBDTests: BackendDeveloperTests.class
	java -jar ../junit5.jar -cp . -c BackendDeveloperTests

FrontendDeveloperTests.class:
	javac -cp ../junit5.jar: FrontendDeveloperTests.java

BackendDeveloperTests.class:
	javac -cp ../junit5.jar: BackendDeveloperTests.java

compileFiles: *.java
	javac *.java

clean:
	rm -f *.class
