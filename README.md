jest
====
Not stable or easy to drop in an run at the moment. Literally ripped out of a 1-use-case implentation.

The goal of jest is make writing APIs in Java without worrying about servlet logical overhead.

Inspired by Avesta's API implementation (as such, "Avesta".substr(1, 4).replace("v", "j"))

Cool stuff
-------------
- Logical mapping of uris to methods (`/api/player/add` calls `PlayerController.add(/* arguments if provided */)`)
- Ordering POST payload parameters to match the methods' arguments
- Handling exceptions not by failing over (I'm looking at you default Java servlet error page), but outputting useful JSON objects

Let me explain
---------------
Over Summer 2013, I took CS2340 (Objects and Design, on paper). It's a group project-based class. Our task was to make a simplified version Hasbro's Risk in Java servlets. 

Coming from PHP, Java web development felt like it came with quite an amount of overhead, so jest was born out of the need for a simple way to quickly build out an API.
