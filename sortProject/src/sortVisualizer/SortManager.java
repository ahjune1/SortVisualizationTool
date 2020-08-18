package sortVisualizer;

import java.util.ArrayList;

public class SortManager {

    private int[] numArr;
    private boolean sorting;
    private int count;
    private int comparisons = 0;
    private int speed = 1;
    private Panel jPanel;
    private int compareIndex1 = 0;
    private int compareIndex2 = 1;
    public SortManager() {
    }

    public void setPanel(Panel jPanel) {
        this.jPanel = jPanel;
    }

    //initialize a randomly ordered array of size count to sort
    public void initializeArr() {
        numArr = new int[count];
        ArrayList<Integer> tempList = new ArrayList<Integer>();
        for (int a = 1; a <= count; a++) {
            tempList.add(a);
        }
        int b = count;
        while (b > 0) {
            int randomIndex = (int) (Math.random() * b);
            numArr[count - b] = tempList.get(randomIndex);
            tempList.remove(randomIndex);
            b--;
        }
        if (jPanel != null) {
            jPanel.update();
        }
    }

    public void setCount(int c) {
        count = c;
        initializeArr();
    }

    public int[] getNumArr() {
        return numArr;
    }

    public int getComparisons() {
        return comparisons;
    }

    public void resetComparisons() {
        comparisons = 0;
    }

    public void setSpeed(int spd) {
        speed = spd;
    }

    private void pause() {
        try {
            Thread.sleep(speed);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getCompareIndex1() {
        return compareIndex1;
    }

    public int getCompareIndex2() {
        return compareIndex2;
    }

    public boolean sorting() {
        return sorting;
    }

    public void stop() {
        sorting = false;
        jPanel.update();
    }

    public void quickSort() {
        sorting = true;
        jPanel.update();
        boundedQuickSort(0, count);
        sorting = false;
        jPanel.update();
    }

    public void boundedQuickSort(int start, int end) {
        if (end - start < 1) {
            return;
        }
        int pIndex = partition(start, end);
        boundedQuickSort(start, pIndex);
        boundedQuickSort(pIndex + 1, end);
    }

    public int partition(int start, int end) {
        int pNum = numArr[end - 1];
        int tempIndex = start;
        for (int index = start; index < end - 1; index++) {
            compareIndex1 = index;
            compareIndex2 = end - 1;
            comparisons++;
            jPanel.update();
            pause();
            if (numArr[index] < pNum) {
                swap(index, tempIndex);
                tempIndex++;
            }
        }
        swap(tempIndex, end - 1);
        return tempIndex;
    }

    public void heapSort() {
        sorting = true;
        jPanel.update();
        createMaxHeap();
        for (int index = count - 1; index > 0; index--) {
            int tempIndex = 0;
            swap(tempIndex, index);
            while (true) {
                if (tempIndex * 2 + 1 >= index) {
                    break;
                }
                int maxIndex;
                if (tempIndex * 2 + 2 >= index) {
                    maxIndex = tempIndex * 2 + 1;
                }
                else {
                    compareIndex1 = tempIndex * 2 + 1;
                    compareIndex2 = tempIndex * 2 + 2;
                    comparisons++;
                    jPanel.update();
                    pause();
                    if (numArr[compareIndex1] > numArr[compareIndex2]) {
                        maxIndex = compareIndex1;
                    } else {
                        maxIndex = compareIndex2;
                    }
                }
                compareIndex1 = tempIndex;
                compareIndex2 = maxIndex;
                comparisons++;
                jPanel.update();
                pause();
                if (numArr[maxIndex] > numArr[tempIndex]) {
                    swap(maxIndex, tempIndex);
                    tempIndex = maxIndex;
                } else {
                    break;
                }
            }
        }
        sorting = false;
        jPanel.update();
    }

    public void createMaxHeap() {
        for (int index = 1; index < count; index++) {
            int tempIndex = index;
            compareIndex1 = tempIndex;
            compareIndex2 = (tempIndex - 1) / 2;
            comparisons++;
            jPanel.update();
            pause();
            while (tempIndex != 0 && numArr[(tempIndex - 1) / 2] < numArr[tempIndex]) {
                swap((tempIndex - 1) / 2, tempIndex);
                tempIndex = (tempIndex - 1) / 2;
                compareIndex1 = tempIndex;
                compareIndex2 = (tempIndex - 1) / 2;
                comparisons++;
                jPanel.update();
                pause();
            }
        }
    }

    public void insertionSort() {
        sorting = true;
        jPanel.update();
        int tempIndex;
        for (int index = 1; index < count; index++) {
            tempIndex = index - 1;
            compareIndex1 = index;
            compareIndex2 = tempIndex;
            comparisons++;
            jPanel.update();
            pause();
            while (tempIndex >= 0 && numArr[tempIndex + 1] < numArr[tempIndex]) {
                /** For visualization purposes, numArr[tempIndex] and numArr[index] is swapped after every comparison.
                 *  However, the conventional approach is to store the value of numArr[index] as a temporary variable
                 *  and assign numArr[tempIndex] to it only after numArr[index] is no longer less than numArr[tempIndex].
                 */
                swap(tempIndex + 1, tempIndex);
                compareIndex1--;
                compareIndex2--;
                tempIndex--;
                comparisons++;
                jPanel.update();
                pause();
            }
        }
        sorting = false;
        jPanel.update();
    }
    public void mergeSort() {
        sorting = true;
        jPanel.update();
        boundedMergeSort(0, count);
        sorting = false;
        jPanel.update();
    }

    public void boundedMergeSort(int start, int end) {
        if (end - start == 1) {
            return;
        }
        int halfLength = (end - start) / 2;
        boundedMergeSort(start, start + halfLength);
        boundedMergeSort(start + halfLength, end);
        merge(start, halfLength, end);
    }

    public void merge(int start, int halfLength, int end) {
        int temp[] = new int[end - start];
        int tempIndex = 0;
        int index1 = start;
        int index2 = start + halfLength;
        while (index1 < start + halfLength || index2 < end) {
            compareIndex1 = index1;
            compareIndex2 = index2;
            if (index1 == start + halfLength) {
                temp[tempIndex] = numArr[index2];
                tempIndex++;
                index2++;
            } else if (index2 == end) {
                temp[tempIndex] = numArr[index1];
                tempIndex++;
                index1++;
            } else {
                if (numArr[index1] < numArr[index2]) {
                    temp[tempIndex] = numArr[index1];
                    tempIndex++;
                    index1++;
                } else {
                    temp[tempIndex] = numArr[index2];
                    tempIndex++;
                    index2++;
                }
                comparisons++;
                jPanel.update();
                pause();
            }
        }
        for (int index = start; index < end; index++) {
            numArr[index] = temp[index - start];
        }
        jPanel.update();
    }

    public void selectionSort() {
        sorting = true;
        jPanel.update();
        for (int index = 0; index < count - 1; index++) {
            int min = numArr[index];
            int minIndex = index;
            for (int index2 = index + 1; index2 < count; index2++) {
                compareIndex1 = minIndex;
                compareIndex2 = index2;
                comparisons++;
                jPanel.update();
                pause();
                if (numArr[index2] < min) {
                    min = numArr[index2];
                    minIndex = index2;
                }
            }
            swap(minIndex, index);
        }
        sorting = false;
        jPanel.update();
    }
    public void bubbleSort() {
        sorting = true;
        jPanel.update();
        boolean stay = true;
        while (stay) {
            stay = false;
            for (int index = 0; index < count - 1; index++) {
                compareIndex1 = index;
                compareIndex2 = index + 1;
                comparisons++;
                jPanel.update();
                pause();
                if (numArr[index] > numArr[index + 1]) {
                    stay = true;
                    swap(index, index + 1);
                }
            }
        }
        sorting = false;
        jPanel.update();
    }

    private void swap(int a, int b) {
        int temp = numArr[a];
        numArr[a] = numArr[b];
        numArr[b] = temp;
    }

}
