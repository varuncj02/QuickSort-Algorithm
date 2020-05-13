import java.time.Duration;
import java.util.ArrayList;
import java.util.Random;
import java.lang.System;


public class QuickSorter {
	
	public static enum PivotStrategy {
		FIRST_ELEMENT,
		RANDOM_ELEMENT,
		MEDIAN_OF_THREE_ELEMENTS,
		MEDIAN_OF_THREE_RANDOM_ELEMENTS
	}
	
	
	public static <E extends Comparable<E>> Duration timedQuickSort
	(ArrayList<E> list, PivotStrategy pivotStrategy) throws NullPointerException{
		if(list.isEmpty() || pivotStrategy == null) {
			throw new NullPointerException("Empty list or invalid Pivot Strategy");
		}
		long startTime = System.nanoTime();
		QuickSorter.quickSort(list, 0, list.size()-1,pivotStrategy);
		long endTime = System.nanoTime();
		Duration totalTime = Duration.ofNanos(endTime - startTime);
		return totalTime;		
	}
	
    public static ArrayList<Integer> generateRandomList(int size) throws IllegalArgumentException {
    	if(size <= 0) {
    		throw new IllegalArgumentException("Size cannot be non-negative");
    	}
    	Random random = new Random();
    	ArrayList<Integer> list = new ArrayList<Integer>(size);
    	for(int i = 0; i < size; i++) {
    		list.add(random.nextInt());
    	}
    	
    	return list;
    }
	
	private static int pivotSelect(int leftIndex, int rightIndex, PivotStrategy pivotStrategy) {
		int pivot = 0;
		Random random = new Random();
		switch(pivotStrategy){
			case FIRST_ELEMENT:
				pivot = leftIndex;
				break;
			case RANDOM_ELEMENT:
				pivot = random.nextInt(rightIndex);
				break;
			case MEDIAN_OF_THREE_ELEMENTS:
				int middle = (leftIndex + rightIndex)/2;
				pivot = (leftIndex + rightIndex + middle)/3;
				break;
			case MEDIAN_OF_THREE_RANDOM_ELEMENTS:
				pivot = (random.nextInt(rightIndex) + random.nextInt(rightIndex) + random.nextInt(rightIndex));
				pivot = pivot/3;
				break;
			default:
				System.out.println("Invalid pivot method");
		}
		return pivot;
	}
	
	
	public static <E extends Comparable<E>> void quickSort(ArrayList<E> list, int leftIndex, int rightIndex,
			PivotStrategy pivotStrategy) {
		E pivot;
		int left;
		int right;
		
		if(leftIndex >= rightIndex) {
			return;
		}
		
		if((rightIndex - leftIndex) < 20 ) {
			insertionSort(list, leftIndex, rightIndex);
		}
		else {
		int pivotIndex = pivotSelect(leftIndex, rightIndex, pivotStrategy);
		
		
		pivot = list.get(pivotIndex);
		left = leftIndex;
		right = rightIndex -  1;
		
		while(left < right) {
			while(pivot.compareTo(list.get(left)) > 0 && (left + 1 <= rightIndex)) {
				left++;
			}
			while(pivot.compareTo(list.get(right)) < 0 && (right - 1 >= leftIndex)) {
				right--;
			}
			if(left < right) {
				elementSwap(list, left, right);
			}
		}
		
		elementSwap(list, left, rightIndex);
		
		QuickSorter.quickSort(list,leftIndex, left - 1, pivotStrategy);
		QuickSorter.quickSort(list, left + 1, rightIndex, pivotStrategy);
	}
	}
	
	 private static <E extends Comparable<E>> void elementSwap(ArrayList<E> list, int i, int j) {
	        E temp = list.get(i);
	        list.set(i, list.get(j));
	        list.set(j, temp);
	  }
	
	
	// Assume this method works
	private static <E extends Comparable<E>> void insertionSort(ArrayList<E> list, int left, int right) {
		E x;
		for (int i = left + 1; i < right + 1; i++) {
            x = list.get(i);
            int j = i - 1;
            while (j >= 0 && (list.get(j).compareTo(x)) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, x);

        }
	}

}
