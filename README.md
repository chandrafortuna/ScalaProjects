# 1
You are given a textfile that contains the age (Integer) of each people in Jakarta, let's assume Jakarta 10 Million people. The format of the file is very simple, each line is an integer representing age of one people.
Example age.txt:
23
51
35

to run the program:
```
$ scala TechnicalAssessment1.scala 
```
*sorted_age.txt* will be created in folder /part1




# 2
Now, the age.txt contains the age of not only people in Jakarta, but the whole world (7 Billion People). Also, you only have a shitty laptop with 1GB of RAM. Can your script in part 1 handle this file? If not, how would you modify it so it can handle this kind of big file?

 i used external sort and merge sort to handle sorting big file, please run this command to exec task 2:
```
$ scala TechnicalAssessment2.scala 
```
the result *sorted_age[unique_alphanum].txt* will be created in folder /part2

# 3
You want to build an API server that receive the name and phone number as an input, then output boolean whether this name and phone number is in the blacklist

to execute task 3:
```
$ scala TechnicalAssessment3.scala 
```
