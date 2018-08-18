# petadoption
pet adoption exercise

#Build - to build run the following command
mvn clean package

# Run the app with command line args
java -jar target/petadoption-0.0.1-SNAPSHOT.jar --inputData=<Input csv filename> <searchType1> <Logical OP AND|OR 1> <<searchType2>. . . <Logical OP AND|OR N> <<searchTypeN>

e.g:
java -jar target/petadoption-0.0.1-SNAPSHOT.jar --inputData=petData.csv dog AND spayed
