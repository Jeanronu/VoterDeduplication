<h1 style="text-align: center;">Lab 8: Deduplication and Hash Tables</h1>
<h3 style="text-align: center;">Haverford CS 106 - Introduction to Data Structures</h3>
<h3 style="text-align: center;">Due: Friday May, 12th at 12:00pm (Noon) </h3>
<h3 style="text-align: center;">Extra credit, not required </h3>


# Overview

In this assignment, your job will be to run deduplication methods (all pairs, linear hashing, and sort-remove) on a set of Voter objects in order to remove duplicate voters. These objects, each representing a voter in an election, will be created from voter roll data.

Though you will not need to write or code anything on complexity in this lab, consider the efficiency of each deduplication method and the potential impacts on time and memory usage each would have.


# The Input: Voting Rolls
In the United States, in order to vote in elections you must be registered to vote. States
must regularly update these voting rolls in order to account for deaths and newly
registered voters. Some states additionally regularly audit their voting rolls and remove
voters based on various rules – many of these rules have been criticized for being
unnecessarily harsh and purposefully suppressing the vote. In the 2018 election in Georgia, an “exact match” rule was instituted to require a voter’s name as listed on their
government-issued ID (e.g., their driver's license) to exactly match their name as listed on the voting rolls. Voters whose names did not match exactly were removed from the voting
rolls.

Ideally, to examine the impact of such a rule, we would count the number of people we
could duplicate between voting rolls and voter ID lists. While voting rolls are public, we
don’t have access to voter ID lists, so instead we’ll focus solely on deduplication of voting
rolls. We’ll be looking at the Ohio statewide voter rolls, available here.

Smaller subsets of the data are provided in the following starter code files:
> vote_files/SWVF_1_22_short.txt
>
> vote_files/SWVF_23_44_short.txt
>
> vote_files/SWVF_45_66_short.txt
>
> vote_files/SWVF_67_88_short.txt

Each voter roll CSV has the following format:
```
voterID, countyNumber, countyID, lastName, firstName, middleName, suffix, birthdate, ... (more voting information not used in this lab)
```
Note that you only need the lastName, firstName, middleName, and birthdate columns. All else can be ignored/excluded from your Voter class design.

Each row in the data represents a single registered voter, and the attributes (columns) include their name, address, and other voting information.
The statewide information is divided by county across 4 files - throughout this assignment it’s fine to use any of those 4 files.
Note that you’ll need to create a way to programmatically read in a given file (just one) for testing.
Recall from previous labs that you can read in data from a file using the given `CSVReader` class.
You should feel free to do that for this lab as well.

Your job is to build a program that will read in voter roll data to an ArrayList of Voter objects so that deduplication can be performed on the data.

# Classes to be Implemented


## 1. `Voter` class
This class should represent a person who has voted in an election and should hold only the given information from the csv: First name, Middle name, Last name, Birthday.
It should also implement the `Comparable` interface so that Voter objects are put in order based on last name. In cases where the last name is the same, order those Voters by first name. Make sure that your code also indicates if two names are the same.
Finally, it should override the `toString` method to return a `String` in the format Last name, First name (for example):

```
Farias, Jeova
```


## 2. `VoterDeduplication` class
Create a `VoterDeduplication` class which will perform three deduplication methods for a list of voters.


### 2.1. Attributes
This class should have two attributes: an ArrayList of voters to hold the original list of voters and another
ArrayList to hold the deduplicated list of voters.

### 2.2. Constructor
In the `VoterDeduplication` constructor, read a CSV file into an ArrayList of strings, then read that list into an
ArrayList of Voter objects, initializing the respective class attribute. Be sure to have a try-catch setup for reading
the file in order to handle errors and exceptions.

### 2.3. Methods:
In this class you should implement three deduplication methods that, by the end of them, should initialize the attribute
ArrayList that contains the deduplicated voter list. The methods you should implement are:

1. `void allPairsDeduplication()`: Implement a method which deduplicates the list with the all pairs strategy.
   As a reminder, all pairs has the program iterate through the list and compare each element to every other element to
   check for duplicates. Remember to correctly call your `compareTo` while checking for duplicates!
   At the end, produce a list with the duplicate Voters removed.

2. `void sortAndRemoveDeduplication()`: Implement a method which sorts the ArrayList of Voters, then iterates through
   and removes each duplicate Voter. Be careful not to modify the original ArrayList of voters. To do this, you may use the sort() method built into the `Collections`
   class in Java (which `ArrayLists` are a member of). These are imported with `java.util.*`. Example:

```
import java.util.*;

ArrayList<String> list = new ArrayList<String>();
Collections.sort(list);
```

3. `void hashMapDeduplication()`: Implement a method which deduplicates the list using a HashTable/HashMap. In this approach,
   your program will need to create an empty `HashMap`, and then read through the Voter ArrayList to populate the hash map.
   Use the methods `put` and `get` accordingly and set each `Voter` key as the `toString` of that voter.
   Hint: Only add a Voter to the HashMap if their first and last names do not bring up an entry, otherwise just
   increment the value of that entry by 1. The values in this way will count how many duplicates a certain key (a voter) has.
   For this program, you will be using the built-in `HashMap` object in `java.util`.
   Below is an example of how to import and call the HashMap:

```
import java.util.HashMap;

HashMap<String, Integer> map = new HashMap<String, Integer>();
```

Helpful information about java.util's HashMap:
* [HashMap documentation](https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html)
* [HashMap examples](https://www.w3schools.com/java/java_hashmap.asp)
* [Useful HashMap commands](https://www.javatpoint.com/java-hashmap)

You should also include some additional methods: original list size, deduplicated list size, list of duplicates.



## 3. `Main` class
Here you'll read the filename from one of the available voting files in `vote_files` from the command line, use it to
initialize an object from the `VoterDeduplication` class and try out all the deduplication methods, computing some data about
them along the way. One data in particular you should print is the elapsed time to run each method. You can do it using
`System.currentTimeMillis()`, as in:
```
long start = System.currentTimeMillis();
// ...
long finish = System.currentTimeMillis();
long timeElapsed = finish - start;
```
Here are some examples of input/output for this lab (OBS.: the exact timings may be different in different machines):

### First example
Command line argument:
```
vote_files/SWVF_1_22_short.txt
```
Output:
```
- All Pairs Deduplication:
Records given: 10000
Deduplicated size: 9910
Duplicates found: 90
Elapsed time: 8159 milliseconds

- HashMap Deduplication:
Records given:10000
Deduplicated size:9910
Duplicates found:90
Elapsed time: 12 milliseconds

- Sort and Remove Deduplication:
Records given: 10000
Deduplicated size: 9910
Duplicates found: 90
Elapsed time: 44 milliseconds
```

### Second example
Command line argument:
```
vote_files/SWVF_23_44_short.txt
```
Output:
```
- All Pairs Deduplication:
Records given: 10000
Deduplicated size: 9907
Duplicates found: 93
Elapsed time: 8899 milliseconds

- HashMap Deduplication:
Records given:10000
Deduplicated size:9907
Duplicates found:93
Elapsed time: 20 milliseconds

- Sort and Remove Deduplication:
Records given: 10000
Deduplicated size: 9907
Duplicates found: 93
Elapsed time: 83 milliseconds
```

# Notes for Lab 8

- Use `.equals()` when comparing strings.  For this lab, you do not need to overwrite the `.equals()` method, you can use `String` to compare two strings directly.
- Consult the [documentation](https://docs.oracle.com/javase/7/docs/api/java/lang/String.html) to learn about the`.compareTo()` method that you will need to override for the Voter data.

# General Style & Coding Tips

Below are some helpful general tips and style guidelines for all labs (they will be taken into account during grading):

-   Remember to use Javadoc style conventions while commenting.
    > For reference on Javadoc style, check out [this resource](https://www.tutorialspoint.com/java/java_documentation.htm).
    > Don't worry about tags, just look at the examples at the top. Plus, use [this code example](https://pythontutor.com/java.html#code=/**%0A%20*%20Java%20program%20that%20computes%20the%20sum%20of%20the%20first%20n%20numbers%0A%20*%20%40author%20Jeova%20Farias%0A%20*%20%40version%20January%2013,%202022%0A%20*/%0Apublic%20class%20Main%20%7B%0A%20%20%20%20public%20static%20void%20main%28String%5B%5D%20args%29%20%7B%0A%20%20%20%20%20%20%20%20int%20n%20%3D%2010%3B%0A%20%20%20%20%20%20%20%20int%20result%20%3D%20sumIntegers%28n%29%3B%0A%20%20%20%20%20%20%20%20System.out.println%28result%29%3B%0A%20%20%20%20%7D%0A%0A%20%20%20%20/**%0A%20%20%20%20%20*%20Returns%20the%20sum%20of%20the%20first%20n%20integers%0A%20%20%20%20%20*%20%40param%20n%20positive%20integer%20n%0A%20%20%20%20%20*%20%40return%20sum%20of%20the%20first%20n%20integers%0A%20%20%20%20%20*/%0A%20%20%20%20public%20static%20int%20sumIntegers%28int%20n%29%7B%0A%20%20%20%20%20%20%20%20int%20total%20%3D%200%3B%20//%20Set%20up%20a%20variable%20for%20the%20total%0A%20%20%20%20%20%20%20%20for%20%28int%20i%20%3D%200%3B%20i%20%3C%3D%20n%3B%20i%2B%2B%29%7B%0A%20%20%20%20%20%20%20%20%20%20%20%20total%20%2B%3D%20i%3B%0A%20%20%20%20%20%20%20%20%7D%0A%20%20%20%20%20%20%20%20return%20total%3B%0A%20%20%20%20%7D%0A%7D&cumulative=false&heapPrimitives=nevernest&mode=edit&origin=opt-frontend.js&py=java&rawInputLstJSON=%5B%5D&textReferences=false)
    from class as a reference for what your style should look like.

-   Write your variables in CamelCase, and be sure to indent properly.

-   Don't forget to add comments! Include...
    > - A document header (name, date, program description)
    > - Comments at the top of each function describing what the
        >   function does
    > - Comments within the functions as needed, to describe
        >   non-obvious steps

-   When working on larger programs, test as you go! That is, write
    a little of code, then make sure it works before building on it.

-   Focus on correctness rather than efficiency.