What is it:

Character based drawing application, currently supported commands are:

C w h (Creates a new canvas of width w and height h)
L x1 y1 x2 y2 (Creates a new line from (x1,y1) to (x2,y2). Currently only horizontal or vertical lines are supported. Line will be drawn using the 'x' character)
R x1 y1 x2 y2 (Creates a new rectangle whose upper left corner is (x1,y1) and lower right corner is (x2,y2). Lines will be drawn using the 'x' character)
B x y c (Fills the entire area connected to (x,y) with "colour" c. If more than 1 character is specified for 'c' only the first one will be used)
Q (Quits the program)

Code Example:
C 10 10
R 3 2 8 5
L 5 6 5 9
L 6 6 6 9
B 5 3 o

Will produce
------------
|          |
|  xxxxxx  |
|  xoooox  |
|  xoooox  |
|  xxxxxx  |
|    xx    |
|    xx    |
|    xx    |
|    xx    |
|          |
------------

Running the program:
To run the program there is a IntelliJ run configuration named "Drawing App"

Running the tests:
To run the tests there is a IntelliJ run configuration named "Run All Tests"