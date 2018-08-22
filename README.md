# petadoption
pet adoption exercise

# Build
To build the artifact, execute the following command from the petadoption folder

mvn clean package

## Run the app with command line args(from the petadoption folder).
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
Quite a few unit tests (within the allowed time) have been written that tests various happy path and negative/boundary conditions for the core piece of functionality.  I would ideally target 100% code coverage - yes its possible using "Clean Code"  best practices.  Code coverage can be mainly used to see what areas of functionality or programming logoic have not been tested.  A hihger code coverage metric can also add to the level of confidence a team has in its codebase.  Also with future enhancements, bugfixes one can make sure that existing functionality is not broken. 
     I prefer the use of the mocking lib called Mockito and not powermock or JMockit since it forces you to write CLEAN CODE.

### IT tests:
   - For integration tests I would use Junit again in this case to instantiate the PetAdoptionRunner (with appropriate args) and let it run all the way through.
   - Another option is to add a quick rest controller and have an IT which specifies input through the rest controller and assert the result.  With Springboot we can use Slice tests for addressing various levels of functional tests including API layer to db layer or service layer only or service layer to db layer or only the db layer with a live db.
   -  If UI is involved then Selenium + Protractor based End to End tests would be written.

## ALTERNATIVE APPROACHES:

    1) Inmemory db like H2 would have worked quite well for this but db was not an option in this exercise
    2) Using json representation of the Pet data (in a pet array represented in json) would have allowed
       me to use the JsonPath to query the data(a more sophisticated query mechanism than what I have) but that is not very performant which is why I went with the HashMap based storage

# Version control tools:
Question: In your opinion, what’s helpful about version control systems? What’s annoying about them?
Answer: Its funny this question was asked :-)  because at one time I had worked in a codebase(back inmid 1990s) which had no version  control and we used to talk over the cube walls to our team mates which file and where we were modifying.  With perforce I remember having to lock the file so that no one else could be modifying it back in the day.  Subversion was better but no good branching strategy.  Git so far is the best with multiple branches and commit strategy which is a Best Practice in my opinion.  One of the cons I see with Git is the learning curve involved with rebasing and clost chanes sometimes with rebasing when two people are working in the same branch.
     Both git and subversion have security built in + support of large binaries if those need to be checked in.  While subversion is more centralized in nature git is distributed.  Having a centralized repository has an advantage where it can control access/permissions across teams but it also affects the performance of the server with multiple teams using it.  
