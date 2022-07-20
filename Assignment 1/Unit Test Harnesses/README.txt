The test harnesses in these individual folders only show that the classes works as expected when the commands are executed properly as well. 
It is not accomadating of things like Inputoutput mismatch errors and such since these classes are called through the interface gameofcatz, and we sanitize the data through gameofcatz
so that the individual classes such as DSAGraph, DSAHashTable, DSALinkedList will not crash, therefore these three classes do not have exception handling inside them.

When testing these classes please be aware of this. There is no point in me using exception handling in these classes to faciliate for wrong errors and types because the data has already
been through one stage of sanitizing as the user never really calls these classes directly. 

I have however shown that each function in the class works as expected. 

Testing for the gameofcatz has been detailed in the Report.

Thank you.