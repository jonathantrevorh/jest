jest
====
Not stable or easy to drop in an run at the moment. 
Inspired by Avesta's API implementation (as such, "Avesta"substr(1, 4).replace("v", "j"))

Let me explain:
Over Summer 2013, I took CS2340 (Objects and Design, as designated on paper). It's a group project-based learning class. Our task was to make a simplified version Hasbro's Risk in Java servlets. 
Coming from PHP, Java web development felt like it came with quite an amount of overhead, so jest was born out of the need for a simple way to quickly build out an API.

It features:
- Logical mapping of uris to methods
- Ordering POST payload parameters to match the methods' arguments
- Handling exceptions not by failing over, but outputing useful JSON objects
