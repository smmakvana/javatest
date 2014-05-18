javatest
========

### Email Message Processing Java Test

----
## Requirement

> Given an interface with a single method: void processEmailCommand(String from, String subject, String content)

Write an implementation that:
----
1. Uses JDK 1.6
2. Assume it WILL be called by multiple threads and senders concurrently but NOT for the same filename concurrently (i.e. no need to synchronise on a file)
3. Collects the total number of calls made by each unique sender (in-memory) and returns this information in the toString() method
4. Processes the subject values and implements the following features
4.1. where subject parameter equals 'write <filename>' value and content parameter is the content to write, write the file
4.2. where subject parameter equals 'append <filename>' value append the content parameter to the end of the file
4.3. where subject parameter equals 'delete <filename>', delete the file
5. Provide pragmatic testing coverage for above
6. Provide a github markdown document (will be viewed on http://markdownlivepreview.com/) to explain the following:
6.1. Your thoughts about the requirements and design considerations
6.2. The length of time you chose or were restricted to spend on the solution
6.3. Any limitations/compromises in your solution and/or any enhancements you might consider if this project was to grow

There is no time limit and no right or wrong answer.  The exercise is intended to demonstrate your natural coding/testing/documentation instincts and thinking.

----
## Test Response
1. I have developed this project using maven and set the source and target JDK as 1.6.
2. I develope this small project using TDD approach and provided the test coverage report, it has ben configure to generated automatically each time project is build using maven also spend some more time and wrote AdvanceTest.java to capture most complex multithreading requirements.
3. Used ConcurrentHashMap to capture each client info and it's counter, Take some extra care about synchronization.
4. Used Strategy pattern to seperate the logic of each different command in nice and elegant way, It will keep class length small but also easy to extend the functionality.
5. I wrote test cases to cover almost 100% of code coverage, I have commigted testcoverage.png file as part of my test code.
6. This document has been prepare using http://markdownlivepreview.com as requested.
6.1. I found this execercise very good and I did my best to seperate out logic into different layer and implemented using best pattern solution for given problem.
6.2. I spent almost around five hours to complete this execrise, which includes, defining and writing testcases, applying correct design pattern and wrote solution to given reqirement
and last but not list created basic UML class diagram and wrote this page.
6.3. I have used two stement co.uk.sanjay.ubstest.CommandProcessorImpl#messagesMap for better code reading purpose but it can also be implement with one line of code
messagesMap.putIfAbsent(sender, new AtomicInteger(0)).incrementAndGet()
I did not used the logger in this project which I will use if project will grow! I also did not used the best File IO code, which I will do and strategy pattern will definatly  help this program to grow without any much effort!



----
## Use full information:
>
#### 
[Service Layer UML](https://github.com/smmakvana/javatest/blob/master/UMLDiagram1.png)
>
####
[Strategy Layer UML](https://github.com/smmakvana/javatest/blob/master/UMLDiagram2.png)
>
####
[Strategy Context Factory UML](https://github.com/smmakvana/javatest/blob/master/UMLDiagram3.png)
>
####
[Complete UML diagram](https://github.com/smmakvana/javatest/blob/master/UMLdiagram.jpg)
>
#### 
[Test Coverage Quick Info] (https://github.com/smmakvana/javatest/blob/master/testcoverage.png)
>
#### 
[Test Coverage Report detail](https://github.com/smmakvana/javatest/tree/master/cobertura)
>
#### 
[GitHub](https://github.com/smmakvana/javatest)

>

----
## thanks
##Sanjay Makivana
