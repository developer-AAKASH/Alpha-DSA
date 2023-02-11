public class DivideAndConquer {
    public static void mergeSort (int[] data, final int start, final int end) {
        if (start >= end) {
            return;
        }

        int mid = start + (end - start) / 2;
        mergeSort (data, start, mid);
        mergeSort (data, mid + 1, end);

        merge (data, start, mid, end);
    }

    public static void merge (int data[],final int start, final int mid, final int end) {
        int merged[] = new int[ end - start + 1];
        int left = start;
        int right = mid + 1;
        int itr = 0;

        while (left <= mid && right <= end) {
            if (data[left] < data[right]) {
                merged[itr] = data[left];
                left++;
            } else {
                merged[itr] = data[right];
                right++;
            }

            itr++;
        }

        while (left <= mid) {
            merged[itr++] = data[left++];
        }

        while (right <= end) {
            merged[itr++] = data[right++];
        }

        for (itr = 0, left = start; itr < merged.length; itr++, left++) {
            data[left] = merged[itr];
        }
    }

    public static void quickSort (int data[], final int startIndex, final int endIndex) {
        if (startIndex >= endIndex) {
            return;
        }

        final int pivotIndex = partition (data, startIndex, endIndex);
        quickSort (data, startIndex, pivotIndex - 1);
        quickSort (data, pivotIndex + 1, endIndex);

    }

    public static int partition (final int data[], final int startIndex, final int endIndex) {
        final int pivot = data[endIndex];
        int pivotI = startIndex - 1;

        for (int itr = startIndex; itr < endIndex; itr++) {
            if (data[itr] <= pivot) {
                pivotI++;
                final int swap = data[itr];
                data[itr] = data[pivotI];
                data[pivotI] = swap;
            }
        }

        pivotI++;
        final int swap = pivot;
        data[endIndex] = data[pivotI];
        data[pivotI] = swap;

        return pivotI;
    }
    public static void printArray (final int[] data) {
        System.out.println();
        for (int itr = 0; itr < data.length; itr++) {
            System.out.print(data[itr]);
        }
        System.out.println();
    }
    public static void main(String[] args) {
        int data[] = { 2, 5, 3, 6, 1};

//        mergeSort (data, 0, data.length - 1);
        quickSort (data, 0, data.length - 1);
        printArray (data);
    }
}