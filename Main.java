import java.io.PrintWriter;
import java.util.ArrayList;

public class Main {
	
	public static void main(String[] args) {
		
		if(args.length == 4) {
			try {
                int size = Integer.parseInt(args[0]);

                String Report = args[1];
                String unsortedFile = args[2];
                String sortedFile = args[3];

                PrintWriter printReports = new PrintWriter(Report);
                PrintWriter printUnsorted = new PrintWriter(unsortedFile);
                PrintWriter printSorted = new PrintWriter(sortedFile);

                ArrayList<Integer> array = QuickSorter.generateRandomList(size);
                if (array == null) {
                    throw new NullPointerException("Entered List is null");
                }

                ArrayList<Integer> array1 = new ArrayList<Integer>(array);
                ArrayList<Integer> array2 = new ArrayList<Integer>(array);
                ArrayList<Integer> array3 = new ArrayList<Integer>(array);
                ArrayList<Integer> array4 = new ArrayList<Integer>(array);

                printUnsorted.println(array);

                printReports.println("Array Size = " + size);
                printReports.println("FIRST_ELEMENT  :  " + QuickSorter.timedQuickSort(array1, QuickSorter.PivotStrategy.FIRST_ELEMENT));

                printReports.println("RANDOM_ELEMENT  :  " + QuickSorter.timedQuickSort(array2, QuickSorter.PivotStrategy.RANDOM_ELEMENT));
                printReports.println("MEDIAN_OF_THREE_RANDOM_ELEMENT  :  " + QuickSorter.timedQuickSort(array3, QuickSorter.PivotStrategy.MEDIAN_OF_THREE_RANDOM_ELEMENTS));
                printReports.println("MEDIAN_OF_THREE_ELEMENTS  :  " + QuickSorter.timedQuickSort(array4, QuickSorter.PivotStrategy.MEDIAN_OF_THREE_ELEMENTS));

                printSorted.println(array1);
                printSorted.println(array2);
                printSorted.println(array3);
                printSorted.println(array4);

                printReports.close();
                printUnsorted.close();
                printSorted.close();

            } 
			catch (Exception e) {
                System.out.println("Error: " + e);
            }

        } 
		else {
            System.out.println("Number of arguments is not 4");
        }

			
		}
}

