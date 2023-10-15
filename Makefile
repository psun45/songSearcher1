runBDTests : Song.java Backend.java BackendDeveloperTests.java BackendIterableMultiKeySortedCollection.java
	javac SortedCollectionInterface.java
	javac KeyListInterface.java
	javac IterableMultiKeySortedCollectionInterface.java
	javac SongInterface.java
	javac BackendInterface.java
	javac Song.java
	javac Backend.java
	javac BackendIterableMultiKeySortedCollection.java
	javac -cp .:../junit5.jar BackendDeveloperTests.java
	java -jar ../junit5.jar -cp . -c BackendDeveloperTests

clean:
	rm -f *.class
