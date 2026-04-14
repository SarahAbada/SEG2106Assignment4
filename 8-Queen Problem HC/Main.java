import java.util.Scanner;

 
public class Main {
	
	


	public static void main(String[] args) {
			int numThreads = 10;
			HillClimbingSearch[] hillclimbingsearcharray = new HillClimbingSearch[numThreads];	       
		    int n = 0; 
	        try (Scanner s=new Scanner(System.in)) {
	        	while (true){
	        		System.out.println("Enter the number of Queens :");
	        		n = s.nextInt();
	        		if ( n == 2 || n ==3) {
	        			System.out.println("No Solution possible for "+ n +" Queens. Please enter another number");
	        		}
	        		else
	        			break;
	        	}
	        }
	        long timestamp1 = System.currentTimeMillis();
	        
	        System.out.println("Solution to "+ n +" queens using hill climbing search:");

			ThreadGroup threadgroup = new ThreadGroup("HillClimbingSearchThreadGroup");
			for (int i=0; i<numThreads; i++){
				HillClimbingSearch hcs = new HillClimbingSearch(n);
				hillclimbingsearcharray[i] = hcs;
				Thread t = new Thread(threadgroup, hcs);
				t.start();
			}
		// 1. Keep looping as long as the "bucket" has active workers
		while (threadgroup.activeCount() > 0) {
    
    		// 2. Peek into each search instance to see if anyone found the answer
    		for (HillClimbingSearch searchTask : hillclimbingsearcharray) {
        		if (searchTask.getFinalSolution() != null) {
            		// 3. Success! Tell the whole group to stop searching
            		threadgroup.interrupt();
            		break; 
        		}
		    }
    
    		// 4. Optional: tiny sleep so the CPU doesn't catch fire checking 1 million times a second
    		try { Thread.sleep(10); } catch (InterruptedException e) { break; }
		}
		
	    for (HillClimbingSearch searchTask : hillclimbingsearcharray) {
    		if (searchTask.getFinalSolution() != null) {
        		searchTask.printState(searchTask.getFinalSolution());
        		break;
    		}
		}    
	        //HillClimbingSearch hcs = new HillClimbingSearch(n);
	        
	        //hcs.runSearch();
	        
	     
	        
	        
	        //Printing the solution
	        long timestamp2 = System.currentTimeMillis();
			
			long timeDiff = timestamp2 - timestamp1;
			System.out.println("Execution Time: "+timeDiff+" ms");
	        
	       
	    }
}
