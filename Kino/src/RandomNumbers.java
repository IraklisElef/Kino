import java.util.Random;

public class RandomNumbers {

	public static Random rand = new Random();
	
	boolean numExists;
	
	public int[] randomNumbers(int totalNumbers[]) {
		
		generateNumbers(totalNumbers);
		totalNumbers = bubbleSort(totalNumbers);
		
		return totalNumbers;
		
	}
	
	public int randomKino(int totalNumbers[]) {
		
		int randKino;
		
		do{
			
			numExists = false;
			
			randKino = rand.nextInt(80) + 1;
			
			for(int i = 0; i < totalNumbers.length && !numExists; i++) {
				
				if(randKino == totalNumbers[i]) 
					numExists = true;

				
			}
			
		}while(numExists);
		
		return randKino;
		
	}	
	
	private void generateNumbers(int totalNumbers[]) {
		
		int randNum;
		
		for(int i = 0; i < totalNumbers.length; i++) {
			
			do{
				
				numExists = false;
				
				randNum = rand.nextInt(80) + 1;
				
				for(int j = 0; j < totalNumbers.length && !numExists; j++) {
			
					if(randNum == totalNumbers[j]) 
						numExists = true;
					
				}
				
			}while(numExists);
			
			totalNumbers[i] = randNum;
			
		}
		
	} 
	
	public int[] bubbleSort(int totalNumbers[]) {
	
		int swap;
		
		for(int i = 0; i < totalNumbers.length - 1; i++) {
			
			for(int j = 0; j < totalNumbers.length - i - 1; j++) {
				
				if(totalNumbers[j] > totalNumbers[j + 1]) {
					
					swap = totalNumbers[j];
					totalNumbers[j] = totalNumbers[j + 1];
					totalNumbers[j + 1] = swap;
					
				}
				
			}
			
		}
		
		return totalNumbers;
	
	}
	
}
