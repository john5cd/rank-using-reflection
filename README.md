# Rank classes using reflection api

This is a Java project that ranks the classes passed to it, via an input.txt file containing the names of the classes 
and generates an output.txt that contains a number of these classes ranked by

- number of declared fields
- number of declared & inherited fields
- number of declared methods
- number of declared & inherited methods
- number of sub-types
- number of super-types

To run the program, in the directory of the file use the command: java Main input.txt output.txt <insert number for top N results>

for example: java Main input.txt output.txt 3
