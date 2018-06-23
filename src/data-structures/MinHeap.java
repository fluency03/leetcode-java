import java.util.NoSuchElementException;

public class MinHeap {

    private int size;
    private int capacity;
    private int[] arr;

    public MinHeap(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        this.arr = new int[capacity];
    }

    public int getMin() {
        if (this.size == 0) throw new NoSuchElementException();
        return this.arr[0];
    }

    public int extractMin() {
        if (this.size == 0) throw new NoSuchElementException();
        if (this.size == 1) {
            this.size--;
            return this.arr[0];
        }
        int minVal = this.arr[0];
        this.arr[0] = this.arr[this.size - 1];
        this.size--;
        heapify(0);
        return minVal;
    }

    private void heapify(int idx) {
        heapify(this.arr, this.size, idx);
    }

    private void heapify(int[] A, int size, int idx) {
        int l = left(idx);
        int r = right(idx);
        int minIdx = idx;
        if (l < size && A[l] < A[minIdx]) minIdx = l;
        if (r < size && A[r] < A[minIdx]) minIdx = r;
        if (minIdx != idx) {
            swap(A, minIdx, idx);
            heapify(minIdx);
        }
    }

    private void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    public void decreaseKey(int idx, int x) {
        if (this.size <= idx) throw new NoSuchElementException();
        this.arr[idx] = x;
        popUp(idx);
    }


    public void insert(int x) {
        if (this.size >= this.capacity) throw new StackOverflowError(); 
        this.arr[this.size++] = x;
        popUp(this.size - 1);
    }

    private void popUp(int idx) {
        int i = idx;
        while (i > 0 && this.arr[i] < this.arr[parent(i)]) {
            swap(this.arr, i, parent(i));
            i = parent(i);
        }
    }

    public int delete(int idx) {
        decreaseKey(idx, Integer.MIN_VALUE);
        return extractMin();
    }


    public void buildHeap(int[] A) {
        for (int i = parent(A.length-1); i >= 0; i--) {
            heapify(A, A.length, i);
        }
    }

    // Heap Sort in Descending Order
    public void sort(int[] A) {
        buildHeap(A);
        for (int i = 0; i < A.length; i++) {
            swap(A, i, A.length - 1);
            heapify(A, A.length - i - 1, 0);
        }
    }


    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int left(int i) {
        return i * 2 + 1;
    }

    private int right(int i) {
        return i * 2 + 2;
    }

}
