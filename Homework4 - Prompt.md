# Homework 4
### Polynomial (Linked Lists)

----------

#### Problem

----------

A polynomial can be represented as a linked list, in which each node contains the coefficient
and the exponent of a term of the polynomial. For example, the polynomial: `4^3+7^2?5` would be represented as a linked list shown in the following figure.

`[4|3]->[7|2]->[-5|null]`

Define and implement classes `Term` and `Polynomial` and use them in your program.(Do not use the Java predefined class `java.util.LinkedLilst`). Class `Polynomial` should include methods for creating a polynomial, reading, displaying, adding, and subtracting polynomials. Partial declaration of the classes is as follows:

```java
class Term {
    private int exponent;
    private int coefficient;
    private Term next;

    // define necessary constructor(s) and methods
}

class Polynomial {
    private Term head;

    // define necessary constructor(s) and methods
}
```

#### Other Requirements

----------

* _No_ node exists for a term in linked list if the coefficient of the term is zero
* The nodes in the linked list should be sorted in the decreasing order of the exponents terms
* To simplify the program, read a polynomial from the keyboard based on the following assumptions:
    1) All terms in a polynomial are entered in the decreasing order of exponents
    2) If the coefficient of a term is positive, the user always enters the `+` character before the coefficient.
    3) All coefficients are integers
* The following are some examples of valid input
    * `+4x^2-5x^0`
    * `-6x^4+3x^2+15x^1`
* In contrast, the following are some examples of invalid input
    * `4x^2-5x^1`   (Missing `+` before coefficient `4`)
    * `+3x^2-6x^4+15x^1`    (Exponents are not in decreasing order)
* When displaying a polynomial, the terms should be printed out in the decreasing order of exponents
* Your code should include comments as specified in the course syllabus

Your program should provide a menu to allow a user to read and display a polynomial, as well as add and subtract polynomials. The following are some examples when running the program

```
C:\CSC310>java Hw5
-------------
1: Read
2. Print
3: Add
4. Subtract
0: exit
--------------
1
Enter a polynomial (e.g., +4x^3+3x^2-5x^0): +25x^10-9x^6-16x^1
-------------
1: Read
2. Print
3: Add
4. Subtract
0: exit
--------------
2
The polynomial is 25x^10-9x^6-16x^1
-------------
1: Read
2. Print
3: Add
4. Subtract
0: exit
--------------
3
Enter a polynomial (e.g., +4x^3+3x^2-5x^0): -60x^13-11x^10+9x^6-5x^1+7x^0
-------------
1: Read
2. Print
3: Add
4. Subtract
0: exit
--------------
2
The polynomial is -60x^13+14x^10-21x^1+7x^0
-------------
1: Read
2. Print
3: Add
4. Subtract
0: exit
--------------
4
Enter a polynomial (e.g., +4x^3+3x^2-5x^0): +15x^20-6x^5+7x^0
-------------
1: Read
2. Print
3: Add
4. Subtract
0: exit
--------------
2
The polynomial is -15x^20-60x^13+14x^10+6x^5-21x^1
-------------
1: Read
2. Print
3: Add
4. Subtract
0: exit
--------------
0
Quit.
```