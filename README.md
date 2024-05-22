# 0-1Knapsack-BranchAndBound
Uses a best first branch and bound strategy to solve a 0-1 knapsack problem.


The program will be to solve the 0-1 knapsack problem using a branch-and-bound strategy. The
program will take as input a file containing a number of items, the capacity of the knapsack and
the individual items. Each individual item will be on a separate row and consist of a label, then a
value then a weight. The label will contain no blank spaces. The output will be a simple message 
stating the items that should be included in the knapsack and the total weight and value of the 
knapsack.

Example Data (knapSack1.txt)

7
15
A 25 5
B 45 11
C 12 3
AA 7 2
BB 6 2
CC 10 7
AAA 5 4

Output:
Item included in knapsack: B, AA, BB
Total weight = 15
Total value = 58

