# Nisum Coding Test

Problem Statement:

In a tag-based language like XML or HTML, contents are enclosed between a start tag and an end tag like ```HTML<tag>contents</tag>```.Note that the corresponding end tag starts with a /.
Given a string of text in a tag-based language, parse this text and retrieve the contents enclosed within sequences of well-organized tags meeting the following criterion:
1.	The name of the start and end tags must be same. The HTML code ```HTML<h1>Hello World</h2>``` is not valid, because the text starts with an h1 tag and ends with a non-matching h2 tag.
2.	Tags can be nested, but content between nested tags is considered not valid. For example, in ```HTML<h1><a>contents</a>invalid</h1>```, contents is valid but invalid is not valid.
3.	Tags can consist of any printable characters.
Input Format
The first line of input contains a single integer,  (the number of lines).
The  subsequent lines each contain a line of text.
Constraints
•	Each line contains a maximum of  printable characters.
•	The total number of characters in all test cases will not exceed .
Output Format
For each line, print the content enclosed within valid tags.
If a line contains multiple instances of valid content, print out each instance of valid content on a new line; if no valid content is found, print None.
Sample Input String
```HTML<h1>India is my country.</h1>
<h1><h1>COVID-19 is not over yet.</h1></h1><par>Take care of you and others. </par>
<Nisum>Nisum is hiring Java developer.</Nisum>
<java developer>The Java developer should always follow best coding practices.</java developer>
```
Sample Output
India is my country.
COVID-19 is not over yet.
Take care of you and others.
Nisum is hiring Java developer.
The Java developer should always follow best coding practices.

Acceptance Criteria:
•	The output must asserted using JUnits.
•	The developer should follow best coding practices (Javadoc, naming conventions, indentation)

