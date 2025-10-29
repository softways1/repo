import java.util.*;
class Heap {
    private List<Integer> heap;
    private boolean isMaxHeap;
    Heap() {
        heap = new ArrayList<>();
        this.isMaxHeap = false;
    }
    Heap(boolean isMaxHeap) {
        heap = new ArrayList<>();
        this.isMaxHeap = isMaxHeap;
    }
    private boolean compare(int i, int j) {
        if(isMaxHeap) {
            return heap.get(i) > heap.get(j);
        } else {
            return heap.get(i) < heap.get(j);
        }
    } 
    private void swap(int i, int j) {
        int t = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, t);
    }
    private void bubbleUp(int i) {
        while(i > 0 && compare(i, (i-1)/2)) {
            swap(i, (i-1)/2);
            i = (i-1)/2;
        }
    }
    private void bubbleDown() {
        int i = 0;
        int limit = heap.size() - 1;
        while(true) {
            int target = i;
            int leftChild = (2*i+1), rightChild = (2*i+2);
            if(leftChild <= limit && compare(leftChild, target)) target = leftChild;
            if(rightChild <= limit && compare(rightChild, target)) target = rightChild;
            if(i == target) break;
            swap(i, target);
            i = target;
        }
    }
    public void insert(int newNode) {
        heap.add(newNode);
        bubbleUp(heap.size()-1);
    }
    public int remove() {
        int ele = heap.get(0);
        swap(0, heap.size()-1);
        heap.remove(heap.size()-1);
        bubbleDown();
        return ele;
    }
    public int top() {
        return heap.get(0);
    }
    public int size() {
        return heap.size();
    }
}
public class Median {
    private Heap minHeap, maxHeap;
    Median() {
        minHeap = new Heap();
        maxHeap = new Heap(true);
    }
    private void balanceHeap() {
        int minHeapSize = minHeap.size();
        int maxHeapSize = maxHeap.size();
        if(Math.abs(maxHeapSize-minHeapSize) > 1) {
            if(minHeapSize > maxHeapSize) maxHeap.insert(minHeap.remove());
            else minHeap.insert(maxHeap.remove());
        }
    }
    private void addNode(int newNode) {
        if(maxHeap.size() == 0) maxHeap.insert(newNode);
        else {
            if(newNode >= maxHeap.top()) {
                minHeap.insert(newNode);
            } else {
                maxHeap.insert(newNode);
            }
        }
        balanceHeap();
        getMedian();
    }
    private void getMedian() {
        int minHeapSize = minHeap.size();
        int maxHeapSize = maxHeap.size();
        if(((minHeapSize+maxHeapSize) % 2) != 0) {
            if(minHeapSize > maxHeapSize) System.out.println("Current Median: " + minHeap.top());
            else System.out.println("Current Median: " + maxHeap.top());
        } else {
            double med = (minHeap.top() + maxHeap.top()) / 2.0;
            System.out.printf("Current Median: %.2f \n", med);
        }
    }
    public static void main(String[] args) {
        Median m = new Median();
        Scanner sc = new Scanner(System.in);
        while (true) { 
            int ele = sc.nextInt();
            if(ele == -999) break;
            m.addNode(ele);
        }

        System.out.println("******THANKS FOR USING******");
        sc.close();
    }
}