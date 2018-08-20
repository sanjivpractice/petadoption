# petadoption
pet adoption exercise

# Build
To build run the following command

mvn clean package

## Run the app with command line args
java -jar target/petadoption-0.0.1-SNAPSHOT.jar --inputData=<Input csv filename> <searchType1> <Logical OP AND|OR 1> <<searchType2>. . . <Logical OP AND|OR N> <<searchTypeN>

e.g:

java -jar target/petadoption-0.0.1-SNAPSHOT.jar --inputData=/Users/Sanjiv/dev/gitrepos/petadoption/src/main/resources/petadoption.csv dog AND spayed
java -jar target/petadoption-0.0.1-SNAPSHOT.jar --inputData=/Users/Sanjiv/dev/gitrepos/petadoption/src/main/resources/petadoption.csv dog AND 97205
java -jar target/petadoption-0.0.1-SNAPSHOT.jar --inputData=/Users/Sanjiv/dev/gitrepos/petadoption/src/main/resources/petadoption.csv dog OR cat AND spayed


## Assumptions
1) input csv file would be specified with full path
2) input csv file is assumed to be free or errors
3) only AND and OR operators are supported. NOT operator currently is not supported.
4) First line in the csv file will be a header file
5) Only simple combination is supported.  Grouped combination is not supported
   e.g:
   (dog AND spayed) OR (cat AND spayed)  is Not supported

## Testing

### Unit tests: 
Quite a few unit tests (within the allowed time) have been written that tests various happy path and negative/boundary conditions

### IT tests:
   - For integration tests I would use Junit again in this case to instantiate the PetAdoptionRunner (with appropriate args) and let it run all the way through.
   - Another option is to add a quick rest controller and have an IT which specifies input through the rest controller and assert the result

## ALTERNATIVE APPROACHES:

    1) Inmemory db like H2 would have worked quite well for this but db was not an option in this exercise
    2) Using json representation of the Pet data (in a pet array represented in json) would have allowed
       me to use the JsonPath to query the data(a more sophisticated query mechanism than what I have) but that is not very performant which is why I went with the HashMap based storage

