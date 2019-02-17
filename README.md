# ownDataStrutures
Impement our own collections in Java for understanding

Time Complexity Analysis per my implementation:
HashMap
add: O(1) - in case of collision, its O(N)
get: O(1) - in case of collision, its O(N). But in Java 8, Collision is mainted in Balanced BST so it will be O(logn).


ArrayList
add: O(1) - amortized constant time. ie. adding n elements will be O(n), if its resizing 
get: O(1)
remove: O(N) 
