runBDTests: BackendDeveloperTests.class
	java -jar ../junit5.jar -cp . -c BackendDeveloperTests
BackendClass.class: BackendClass.java
	javac BackendClass.java

FrontendClass.class: FrontendClass.java
	javac FrontendClass.java

BackendDeveloperTests.class: BackendDeveloperTests.java BackendClass.class FrontendClass.class
	javac -cp .:../junit5.jar BackendDeveloperTests.java

FrontendDeveloperTests.class: FrontendDeveloperTests.java BackendClass.class FrontendClass.class
	javac -cp .:../junit5.jar FrontendDeveloperTests.java

runFDTests: FrontendDeveloperTests.class
	java -jar ../junit5.jar -cp . -c FrontendDeveloperTests

clean:
	rm -r *.class
