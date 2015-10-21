1) Given a group of data, how to choose a proper sorting algorithm to sort the data.
   Hint: the impact factors include: data size, memory space, randomness of the data, the
   efficiencies of different sorting algorithms.

    * First you need to know what kind of data you're going to be sorting. Is it numerical? Strings? What's the
    distribution of the data? Is my data nearly sorted? What are my memory requirements?
    * Assume that you have an array of [10,2,3,4,5,6,7,8,9,1] Using Shell Sort would be ideal because it allows the
    swapping of indexes that are far apart (using its K value), whereas using something like Bubble Sort or Merge Sort
    would be very not ideal. Quick Sort would in that case have an O complexity of O(n^2) compared to O(n) for Shell Sort
    * Choose Quick Sort if you have a large data size with very random distribution. Best and Average cases are going to be O(n log n) with the worst being O(n^2). However, it will have a space complexity of O(log n) which is less than the space complexity for Merge Sort.
    * Choose Merge Sort when you need a guaranteed O(n log n ) time complexity, though there is an added memory cost because of the temporary arrays it makes while sorting.
    * Choose Shell Sort if you have nearly sorted data with large gap distributions. It has O(n) for best case, but degrades quickly to O((n log n)^2) for both average and worst complexities. However, it has the best space complexity of O(1) because it doesn't make new arrays like Merge Sort

2)  Given a group of data, how to choose a proper searching algorithm to search for a key data

    * First, you must know the distribution of your data (is it linear? near linear? quadratic?) and if the data is sorted or not.
    * If your data is not sorted, then the only way to find anything is a sequential search, which has an O complexity of O(n)
    * If your data is sorted, then you're left with a couple of options: Interpolation Search and Binary Search.
    * Binary has an O complexity of O(log n) because it searches half of the array at a time, recursively. It starts at the middle, so if you know that your data is close to the middle, then Binary Search will provide a faster search.
    * With Interpolation search, you must know more about your data than just the distribution. Ideally you would know the distribution of the key you're searching by. Interpolation Search does not work well with very lewdly distributed data. Your data needs to have a near linear distribution for Interpolation Search to work well, otherwise it performs worse than binary search.