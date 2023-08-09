# MBTI Coding Challenge

This repository contains all the code and documentation for solving the MBTI Coding Challenge.

The first analysis (i.e., reading the task, thinking about possible solutions) took about an 
hour. The implementation of tests and the actual functionality took about 1,5 hours. 
Documentation, including comments and this readme, took about another hour, and finalizing 
everything and pushing it to git took roughly half an hour. So in total, around 4 hours were 
spent on this coding challenge.

## Running the code

This project was made with IntelliJ. The easiest way to run it is to open it in IntelliJ and execute
the code from there. (Note that depending on your operating system and the installed software, Java
20 must be installed. IntelliJ will show a menu option on the right, which allows to automatically
download and include the correct Java version.) The code can then be executed by running the 
`Main. java` class, and the tests can either be executed class-wise by running the respective 
test classes, or all tests can be executed at once by running the `src/test/java/` folder.

## Repository structure

### Implementation

The implementation code can be found under `src/main/java/org/challenge/`. There are three Java
classes in there:

- `Interval.java`: This class implements the `Inrterval` data structure. An interval consists of a
  start time and an end time. For this challenge, the following two assumptions were made:
    1. Both the start time and the end time are integers. Since there were no requirements given in
       the task on which data types these two properties of an interval have, the smallest sensible
       data type was used. Depending on the actual functional requirements, the implementation could
       be made more generic by allowing any number type.
    2. An interval's start time must not be greater than its end time. If this constraint is
       violated, an `IllegalArgumentException` is thrown when creating a new interval or setting an
       existing interval's start or end time.
- `Main.java`: This is the main class, which calls the `merge` method with an exemplary list of
  intervals, and then prints the result of the `merge`
  method to the console.
- `SolveCodingChallenge.java`: This class implements the `merge` method. The class is used as a
  static class, meaning that `merge` is the only sensible functionality, which is implemented as a
  static method. Furthermore, to prevent the instantiation of objects of this class, there is a
  private constructor defined. The exact idea behind the algorithm is explained in the `merge`'s
  method javadoc.

### Tests

The tests can be found under `src/test/java/`. Both the `Interval` class and the `merge` method are
tested.

- `IntervalTest.java`: In this set of tests it is verified that the constraints on intervals
  described under the Implementation section are satisfied. In particular, this means that it is
  tested whether creating intervals with start times greater than their end time, trying to set a
  start time which is greater than the end time or an end time which is less than the start time
  throws an
  `IllegalArgumentException`. Also, the same behaviour is tested for creating a new interval with a
  negative start time or trying to set a negative start time. Note that setting a negative end time
  will be caught by the previously mentioned constraint, i.e, the start time must not be greater
  than the end time.
- `SolveCodingChallengeTest.java`: For testing the actual `merge` method, an equivalence class
  testing strategy is applied. For each equivalence class, one test case was written. These are the
  equivalence classes tested:
    - An empty list of intervals. In this case, the desired output is set to be an empty list.
      Depending on the functional requirements, it might also be required to throw an error in case
      of an empty input list.
    - A list with only one interval.
    - A list of intervals in which some intervals overlap, and others don't.
    - A list of intervals in which all intervals are overlapping.
    - A list of intervals in which there are no overlapping intervals.

## On robustness

By implementing an own interval class, we can guarantee that all intervals in the input list 
satisfy the requirements (i.e., startDate <= endDate). This is more robust than using, say, an 
array of ints as an interval because then we can no longer ensure this requirement (or even that 
each interval consists of two ints). Also, the implementation can handle an empty input list, 
which is also explicitly tested in one test case. 

When it comes to very large inputs, there may be both time and space problems. Let us first 
consider the space problem.

### Space complexity

Since we are assuming that the input is very large, we have to avoid duplicating it 
unnecessarily. Note that this is not done in our current implementation as we use the 
`Collections.sort()` method, which is not in-place. Instead, it creates a new array, sorts this 
array, and then reorders the list elements based on this sorted array. So, for ensuring 
robustness, one possible approach is to use an in-place sorting algorithm. Furthermore, we 
create a new list for the output, which means that in the worst case, we double the space 
consumption because this output list may be as big as the input list. We can again reduce the 
space consumption by manipulating the input list directly.<br> Of course, both these approaches 
manipulate the original input list, which may or may not be feasible, depending on the 
functional requirements. Note that while the sorting algorithm used in our implementation is not 
in-place, it still modifies the input list.

Another approach can be splitting up the input and processing it in batches instead of the 
complete input. This way, only a subset of the whole input needs to be stored, although this 
approach needs careful considerations of how to implement the merging of subresults. 

Also, depending 
on the actual use case, it may be possible to process the intervals in a streaming fashion, 
meaning that instead of having a list of all intervals, new intervals are processed as they 
arrive. Hence, there is no need to store all intervals; rather, only the most recent interval needs 
to be stored.

### Time complexity

If the method runs too long for large inputs, the first thing to verify is whether we use an 
efficient sort algorithm (which we use in our implementation). Apart from that, we can use 
parallelization to speed up the processing. After sorting, we can split up the list of intervals,
and process sublists in parallel, merging the results of these sublists again. The exact number 
of partitions and their sizes would need to be determined.

