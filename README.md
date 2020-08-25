# SortVisualizationTool
This tool visually demonstrates how various sorting algorithms process an array, assisting in the users' understanding of these sorting algorithms.
The implemented sorting algorithms include: selection sort, bubble sort, insertion sort, merge sort, quick sort, and heap sort. 

![Tool Image](https://i.imgur.com/09b9aIW.png)
## How to use
The tool initially generates an int[] containing every integer from 1 to n (n depends on the # of elements, set to the 6th tick by default), and randomize the order of its elements. The green rectangles above the input panel is the visual representation of this array. The user can edit the # of elements, as well as the speed of the sort, then click on one of the six sorting algorithms to begin the sort. 

Once the sort begins, only the speed can be changed until the sort ends. To stop the sort for any reason, the user can click on "Force Stop". 

At any given time during the sort, two rectangles within the visual representation will be red. These rectangles represent the two elements being compared at that given time.
## Tick Settings
(# of elements): 1st tick - 10 elements,

2nd tick - 20 elements,

3rd tick - 35 elements,

4th tick - 50 elements,

5th tick - 70 elements,

6th tick - 100 elements,

7th tick - 175 elements,

8th tick - 350 elements,

9th tick - 700 elements

(speed): 1st tick - 1000 ms pause each comparison,

2nd tick - 100 ms pause,

3rd tick - 10ms pause,

4th tick - 1ms pause
