    [0] TABLE OF CONTENTS
    [1] INTRODUCTION
    [2] DATA
    [3] VARIABLES
    [4] TYPES
    [5]
    [6]
    [7]
    [8]
    [9]







                   _ESOTERIC-NESS_OF_CONTENT_OVER_TIME_

        |          ?                              ?  ??     ?
      ^ |                                   ??   ?   ???
      | |                             ?? ??   ?
      | |                        ???   ?
        |                     ??  ?           ?
      d |               ?   ?  ? ?
      e |                ??  ??
      p |              ? ?       ?
      t |           ??   ?
      h |  ?      ? ?
        |       ?
      ^ |     ?    ?
      | |    ?
      | |  ?         ?
        | ?
        |_____________________________________________________
       0         -----> application runtime ----->


## INTRODUCTION

Programming is about solving problems.

    .--------.    . . . . ./. .     ..__...__ ..__..
    |X |  |O |    . . . . / . .     :|  | |  | |  |:     |
    |--+--+--|    . . . ./. . .    ::| '| | '| | '|::    |        |
    |X |O |O |    - - - + - - -    ::|  | |  | |  |::    |   |    |
    |--+--+--|    . . ./. . . .   ___________________    |  ||  | |
    |O |X |X |    . . / . . . .    .. .... ... .. ..     |__||_||_|
    '--------'    . ./. . . . .    .: :: :.:: :: .::     0123456789

A program is just a list of instructions for the computer to execute, usually written with a particular goal in mind.

                                 -----------
                                |           |
                                |           |
                input  ------>  |  program  | ------> output
                                |           |
                                |           |
                                 -----------

Every thing we care about is in the box in the center.


## DATA

Data (or datum, singular) is a fundamental object in programming.
Typically, data exists as a specific sequence of 0s and 1s, or bits:

            D   A   T   A   01000100 01000001 01010100 01000001
            d   a   t   a   01100100 01100001 01110100 01100001

Also in use is the term 'byte' for 8 consecutive bits, and, less
commonly, 'nibble' for 4 consecutive bits.


## VARIABLES

Variables mean more or less the same thing as they meant in math
class, except they are more powerful.


## TYPES

Java is a staticly-typed language. This means that whenever you are
dealing with data, you must specify which type of data you are
dealing with. There are 8 types built into Java that are referred to
as 'primitives', plus one other, 'String' which is special. The good
news is that the process of declaring, and initializing variables is
more or less the same for all of them.


## INTS

Ints, Bytes, Shorts, and Longs are all basically integers of varying
size. Here they are ordered from smallest to largest. Note that size
here has a special meaning: roughly: 'range of allowed values'. If
you try and fit a long-sized number into a byte, you will experience
some problems.


## FLOATS

Floats are a number that can include decimals - 3.14 for example.
Doubles are simply a version of floats that is precise to more
digits. Double, in fact.


## BOOLEANS

Booleans represent truth values: whether something is true or false.


## CHARS

Chars are, as you might guess, single characters.


## STRINGS

And String, the black sheep, is technically not a primitive data
type. It just happens to behave very similar to one. You will be
using Strings quite a bit - a String is simply an array of
characters. Sentences, paragraphs, and entire books can be stored
as Strings.


## IDENTIFIERS

You may have noticed, all of the variables above have been given a
name, followed by a semicolon. It is necessary to provide a name
for your variables, and most lines of Java code (and many other
languages) must be terminated with a semicolon. Good names are
short without sacrificing descriptiveness, unique, and considered
by Java to be 'legal identifiers':

    varName     |       variableNameExample2    |       name01234

        Any combination of uppercase and lowercase letters, numbers
        and underscores, provided the first character of the
        identifier is not a number. You may also use a dollar sign
        ('$') as long as it is the first character, but this isn't
        usually advised.

Java also uses the 'lower camel case' convention for variable
names. This just means that the first word in your variable name
should start with a lowercase letter, and all subsequent words
should start with an uppercase one.

In math, you are probably used to using the symbol 'x' and 'y',
frequently to represent numbers, especially on cartesian planes.
In programming, there is also a set of naming conventions that you
will get used to.


## VISIBILITY

Visibility in java is a rule about what can 'see' a class, method,
or field in java.

In large projects, you may be tempted to reuse variable names, but
this can cause 'namespace conflicts'. If java looks up a variable
name, and finds two different values, how does it figure out which
one to use? In short: it doesn't and it crashes. Specifying
visibility for classes, methods, and fields lets you hide away or
'encapsulate' your data. making it only accessible to exactly what
needs to be able to access it.

|     Type of Visibility     |        Brief Description       |
| -------------------------- | ------------------------------ |
| private                    | only within the class          |
| public                     | everywhere                     |
| protected                  | to itself and its sub-classes  |
| default (none specified)   | only within its package        |

