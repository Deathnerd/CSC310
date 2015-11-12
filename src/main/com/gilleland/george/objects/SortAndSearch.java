package com.gilleland.george.objects;

import com.gilleland.george.exceptions.NotSortedException;

import java.util.ArrayList;

/**
 * Created by Wes Gilleland on 10/1/2015.
 */
public class SortAndSearch {
    /**
     * The data set
     */
    public ArrayList<String> dataset = null;

    /**
     * Is the data sorted?
     */
    public boolean is_sorted = false;
    /**
     * The k value to use for merge sort
     */
    public int k;

    /**
     * Sort a string array based on the first character of the elements
     * by using the MergeSort algorithm. Has a time complexity of the following:
     * <table summary="The big o complexities for MergeSort">
     *  <tr>
     *   <td>Best</td>
     *   <td>Average</td>
     *   <td>Worst</td>
     *  </tr>
     *  <tr>
     *   <td><pre>O(log n)</pre></td>
     *   <td><pre>O(log n)</pre></td>
     *   <td><pre>O(log n)</pre></td>
     *  </tr>
     * </table>
     *
     * @param arrayToSort The string array to sort
     * @return The sorted array
     */
    public ArrayList<String> mergeSort(ArrayList<String> arrayToSort) {
        this.dataset = arrayToSort;
        this.mergeSort(0, arrayToSort.size() - 1);
        this.is_sorted = true;
        return this.dataset;
    }

    /**
     * The recursive part of merge sort
     *
     * @param left  The left index bound
     * @param right The right index bound
     */
    private void mergeSort(int left, int right) {
        if (left < right) {
            int middle = left + (right - left) / 2; // find the pivot
            this.mergeSort(left, middle); // sort the left side recursively
            this.mergeSort(middle + 1, right); // sort the right side recursively
            this.merge(left, middle, right); // merge the parts
        }
    }

    /**
     * The merge part of merge sort.
     *
     * @param left   The left index bound
     * @param middle The middle between the left and the right
     * @param right  The right index bound
     */
    private void merge(int left, int middle, int right) {
        // create a helper array to help with the sorting
        // Tried using an ArrayList here as well but kept getting
        // IndexOutOfBounds exceptions
        String[] arr = new String[right + 1];
        // copy both sub-arrays in to the helper array
        for (int i = left; i <= right; i++) {
            arr[i] = this.dataset.get(i);
        }

        int left_index = left; // the index for the left array
        int right_index = middle + 1; // the index for the right array
        int overall_index = left; // the overall index
        String val;

        /*
         * Loop through all of the values until we hit the end of either
         * left or the right array
         */
        while (left_index <= middle && right_index <= right) {
            // Determine which value to swap
            if (arr[left_index].charAt(0) <= arr[right_index].charAt(0)) {
                val = arr[left_index++];
            } else {
                val = arr[right_index++];
            }
            // do the swap
            this.dataset.set(overall_index++, val);
        }

        /*
         * There'input_string some leftover elements in the left array,
         * copy them to the right array
         */
        while (left_index <= middle) {
            this.dataset.set(overall_index++, arr[left_index++]);
        }
        /*
         * There'input_string some leftover elements in the right array,
         * copy them to the dataset
         */
        while (right_index <= right) {
            this.dataset.set(overall_index++, arr[right_index++]);
        }
    }

    /**
     * Sort a string array based on the first character of the elements
     * by using the QuickSort algorithm. Has a time complexity of the following:
     * <table summary="The big o complexities for MergeSort">
     *  <tr>
     *   <td>Best</td>
     *   <td>Average</td>
     *   <td>Worst</td>
     *  </tr>
     *  <tr>
     *   <td><pre>O(n log n)</pre></td>
     *   <td><pre>O(n log n)</pre></td>
     *   <td><pre>O(n^2)</pre></td>
     *  </tr>
     * </table>
     *
     * @param arrayToSort The string array to sort
     * @return The sorted string array
     */
    public ArrayList<String> quickSort(ArrayList<String> arrayToSort) {
        this.dataset = arrayToSort;
        this.quickSort(0, this.dataset.size() - 1);
        this.is_sorted = true;
        return this.dataset;
    }

    /**
     * Sort a string array based on the first character of the elements
     * by using the QuickSort algorithm. Has a time complexity of the following:
     * <table summary="The big o complexities for MergeSort">
     *  <tr>
     *   <td>Best</td>
     *   <td>Average</td>
     *   <td>Worst</td>
     *  </tr>
     *  <tr>
     *   <td><pre>O(n log n)</pre></td>
     *   <td><pre>O(n log n)</pre></td>
     *   <td><pre>O(n^2)</pre></td>
     *  </tr>
     * </table>
     *
     * @param left  The left index bound
     * @param right The right index bound
     */
    private void quickSort(int left, int right) {
        if (left < right) {
            int i = this.partition(left, right);
            this.quickSort(left, i - 1);
            this.quickSort(i + 1, right);
        }
    }

    /**
     * Sort a string array based on the first character of the elements
     * by using the ShellSort algorithm. Has a time complexity of the following:
     * <table summary="The big o complexities for MergeSort">
     *  <tr>
     *   <td>Best</td>
     *   <td>Average</td>
     *   <td>Worst</td>
     *  </tr>
     *  <tr>
     *   <td><pre>O(n)</pre></td>
     *   <td><pre>O((n log n)^2)</pre></td>
     *   <td><pre>O((n log n)^2)</pre></td>
     *  </tr>
     * </table>
     *
     * @param arrayToSort
     */
    public void shellSort(ArrayList<String> arrayToSort) {
        this.dataset = arrayToSort;
        this.findk(); // find the K value for shell sort and set it to the class k variable
        while (this.k > 0) {  // While we still have a K value to use for shell sort, sort!
            for (int i = 0; i < this.k; i++) {
                // insertion sort on the sub arrays
                this.shellInsertionSort(this.dataset.size(), i);
            }
            // Decrement K value for the next pass
            k /= 2;
        }
    }

    /**
     * The partition method for quick sort. This does a "pre-sort" of sorts (hah)
     * and finds the pivot point for {@link #quickSort(int, int)} to start at with its recursive insertion sort
     *
     * @param left  the left index bound
     * @param right the right index bound
     * @return returns the pivot point for {@link #quickSort(int, int)} to use
     */
    private int partition(int left, int right) {
        boolean move_right_pointer = true;

        while (left < right) {
            if (this.dataset.get(left).charAt(0) > this.dataset.get(right).charAt(0)) {
                // swap the things
                //noinspection SuspiciousNameCombination
                this.swap(left, right);
                // flip the swapped bit
                move_right_pointer = !move_right_pointer;
            }
            // Move the left and right pointers respectively
            if (move_right_pointer) {
                right--;
            } else {
                left++;
            }
        }
        // left and right pointers are at the same position so it doesn't matter
        // Which one we return
        return left;
    }

    /**
     * Performs an in-place swap on the dataset array
     *
     * @param x the position of the first thing to swap
     * @param y the posotion of the second thing to swap
     */
    private void swap(int x, int y) {
        String t = this.dataset.get(y);
        this.dataset.set(y, this.dataset.get(x));
        this.dataset.set(x, t);
    }

    /**
     * Finds the value for k for shell sortAndSearch using the size of the dataset.
     * Adheres to the pattern of 1,3,7,15,...
     */
    private void findk() {
        int h = 1;
        while (h < this.dataset.size() / 2) {
            h = 2 * h + 1;
        }
        this.k = h;
    }

    /**
     * Helper function for Shell Sort to do its actual sorting
     * <p>
     * Has an O complexity of the following
     * <table summary="The big o complexities for MergeSort">
     *  <tr>
     *   <td>Best</td>
     *   <td>Average</td>
     *   <td>Worst</td>
     *  </tr>
     *  <tr>
     *   <td><pre>O(n)</pre></td>
     *   <td><pre>O(n^2)</pre></td>
     *   <td><pre>O(n^2)</pre></td>
     *  </tr>
     * </table>
     *
     * @param length the length of the array to run
     * @param i
     */
    private void shellInsertionSort(int length, int i) {
        if (length > this.k) {
            this.shellInsertionSort(length - 1, i);

            String t1 = this.dataset.get(length - 1); // Holds the array item
            int t2 = length - this.k - 1; // The index of the next element to compare with

            while (t2 >= 0) {
                // if the element in the right part is lexographically greater than
                // the one in the left, then perform a swap and decrement the left pointer (t2)
                if (this.dataset.get(t2).charAt(0) > t1.charAt(0)) {
                    this.dataset.set(t2 + this.k, this.dataset.get(t2));
                    t2 -= this.k; // jump by K, thus improving the time for sorting compared to regular insertion sort
                } else {
                    break;
                }
            }

            this.dataset.set(t2 + this.k, t1);
        }
    }

    /**
     * <p>Search a string array based on the first character of the elements
     * by using the BinarySearch algorithm. Has a time complexity of the following:</p>
     * <table summary="The big o complexities for MergeSort">
     *  <tr>
     *   <td>Best</td>
     *   <td>Average</td>
     *   <td>Worst</td>
     *  </tr>
     *  <tr>
     *   <td><pre>O(1)</pre></td>
     *   <td><pre>O(log n)</pre></td>
     *   <td><pre>O(log n)</pre></td>
     *  </tr>
     * </table>
     *
     * @param key The first character of the string to search for
     * @return -1 if the key wasn't found, otherwise the index of where the key is
     * @throws NotSortedException If the array isn't sorted, then throw an exception
     */
    public int binarySearch(char key) throws NotSortedException {
        if (!this.is_sorted) {
            throw new NotSortedException();
        }
        // Attempt the search
        return this.binarySearch(key, 0, this.dataset.size() - 1);
    }

    /**
     * <p>Search a string array based on the first character of the elements
     * by using the BinarySearch algorithm. Has a time complexity of the following:</p>
     * <table summary="The big o complexities for MergeSort">
     *  <tr>
     *   <td>Best</td>
     *   <td>Average</td>
     *   <td>Worst</td>
     *  </tr>
     *  <tr>
     *   <td><pre>O(1)</pre></td>
     *   <td><pre>O(log n)</pre></td>
     *   <td><pre>O(log n)</pre></td>
     *  </tr>
     * </table>
     *
     * @param key   The first character of the string to search for
     * @param left  The left bound index of the array to search in
     * @param right The right bound index of the array to search in
     * @return -1 if the key wasn't found, otherwise the index of where the key is
     */
    private int binarySearch(char key, int left, int right) {
        // Base case. If the left index has passed the right index, we've exhausted our search
        if (left > right) {
            return -1;
        }
        // find the new middle point based on the left and right pointers
        int middle = (left + right) / 2;
        if (this.dataset.get(middle).charAt(0) == key) {
            // we've found it!
            return middle;
        } else if (this.dataset.get(middle).charAt(0) < key) {
            // We think the value is to the right of where we're currently at
            // so we'll split the current array range in two and search it
            return this.binarySearch(key, middle + 1, right);
        }
        // Okay, it might be in the left half of this array, so split it in two and
        // search it
        return this.binarySearch(key, left, middle - 1);
    }

    /**
     * <p>Search for a key value in a string ArrayList using interpolation search.
     * This is a more efficient method of searching than Binary Search. You can think of it as an
     * improved version of Binary Search</p>
     * <table summary="The O functions for Interpolation Search">
     *  <tr>
     *   <td>Best</td>
     *   <td>Average</td>
     *   <td>Worst</td>
     *  </tr>
     *  <tr>
     *   <td><pre>O(log log n)</pre></td>
     *   <td><pre>O(log n)</pre></td>
     *   <td><pre>O(n)</pre></td>
     *  </tr>
     * </table>
     *
     * @param key The key to search for
     * @return -1 if key not found, the index of the position otherwise
     * @throws NotSortedException
     */
    public int interpolationSearch(char key) throws NotSortedException {
        if (!this.is_sorted) {
            throw new NotSortedException();
        }
        return this.interpolationSearch(key, 0, this.dataset.size() - 1);
    }

    /**
     * Actually do the search
     *
     * @param key The key to search for
     * @param left The left index bound
     * @param right The right index bound
     * @return The index of where the element is located, -1 if not found
     */
    private int interpolationSearch(char key, int left, int right) {
        if (left > right) {
            // Key not found
            return -1;
        }
        // The meat and bones of Interpolation Search
        // Find the middle using the formula:
        // m = left + (key-A[left)/(A[right]-A[left])*(right-left)
        int middle = left + ((key - this.dataset.get(left).charAt(0)) * (right - left) / (this.dataset.get(right).charAt(0) - this.dataset.get(left).charAt(0)));

        // another base case. If out of range, then key not found
        if (middle < 0 || middle >= this.dataset.size()) {
            return -1;
        } else if (this.dataset.get(middle).charAt(0) == key) {  // We found it! Return the index
            return middle;
        } else if (middle < key) {
            // We're assuming the value is to the right of the current pointer
            // Recursively search it
            return this.interpolationSearch(key, middle + 1, right);
        }
        // Assume the value is to the left of the current pointer
        // Recursively search it
        return this.interpolationSearch(key, middle - 1, right);
    }
}
