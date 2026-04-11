****************
* Double-Linked List
* CS221
* Apr 10, 2026
* Bryson Leatham
**************** 

IUDoubleLinkedList implements IndexedUnsortedList to create a node-based, doubly linked list.
Each node references a generic T element and is linked to its next and its previous node.


INCLUDED FILES:

* <IUDoubleLinkedList.java> - source file - is the Double-Linked List class with all of its functionality
* <ListTester.java> - source file - is used to test the IUDoubleLinkedList functionality and acts as the main when running
* <Node.java> - source file - is the Node class with each node's functionality, references, and links
* <IndexedUnsortedList.java> - source file - is the interface implemented in IUDoubleLinkedList
* <README.md> - this file

COMPILING AND RUNNING:

To compile this project, use:
$ javac ListTester.java

To run the compiled project, use:
$ java ListTester

Console output will be all passing, and failing test scenarios,
with failing scenarios displaying the specific failed tests.

For passed tests, its name, toString and the total tests ran, passed,
and failed up to that point. It ends with the total count for each value.



PROGRAM DESIGN AND IMPORTANT CONCEPTS:

This Double-Linked List is very similar to a single-linked list
at its core, but with the added functionality of traversing backwards.
Being able to traverse the list from both ends allows certain methods,
like removeLast() or addToFront(), to be constant-time. It also allows
methods like get() or any index-based method to cut the Order coefficient
in half by starting at the closer end to the node.

With those index-based methods, special cases, usually at the head or tail,
are taken care of first. Then, if the index is less than half of the list’s size,
the current node is set to head and counts up from there; otherwise, the current
node is set to tail and counts down to the index. Once the current node reaches
that index, the specific method’s functions will run.

IUDoubleLinkedList also has a  class that implements the ListIterator
interface. It is also a fail-fast iterator, so if the list changes, it will throw a
ConcurrentModificationException, so it doesn’t give false information.
Its constructors allow it to start at the beginning of the list,
or in front of any specific index as long as it is within 0 to the list size.
This means that a call to next() will point to head, or the Node at the input index.
ListIterator allows the user to traverse the list from both directions, add, remove,
set, and get positional information about the list as well.

Node.java functions are used in IUDoubleLinkedList to move nodes, select surrounding
nodes, and even set a specific node to reference a different Generic T Object.



TESTING:

Using ListTester.java, it is very easy to test the functionality of the
Double-Linked List. Running whenever the code is changed gives feedback on
how those changes fixed or ruined functionality. Scenarios were added to test
all types of generic cases, as well as many edge cases. These edge cases are
typically for IndexOutOfBounds and NoSuchElement Exceptions, accounting for
erroneous input.

No known bugs persist, but as-is, there is no way to check the total amount
of tests implemented compared to the amount run. When creating or altering
this program, the total tests run can vary wildly.



DISCUSSION:

When creating this program, I frequently had problems getting to the
correct node, like when using index-based functions, moving from
the tail. After I was able to confirm that some of those loops worked
correctly using the ListTester, I got more comfortable looping through the list.

When adding new List Iterator scenarios in ListTester, I also struggled
because my Literator add function basically wasn’t written at that point. I
must have started it, got distracted, and never went back until the end.
I also had a problem because I made the error of adding Element_A twice to
the scenario’s list, and I tried altering multiple methods across all of
IUDoubleLinkedList, rather than noticing I made that mistake.

Overall, this project was very challenging in its specifics. Understanding
the general idea of what needed to happen wasn’t extraordinarily difficult,
but finding the exact steps to get to that point could be.


 
EXTRA CREDIT:

    Not Applicable.
