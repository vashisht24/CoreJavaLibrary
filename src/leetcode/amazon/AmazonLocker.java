package leetcode.amazon;

public class AmazonLocker {

	private int[] lockerX;
	private int[] lockerY;
	
	private int[][] cityBlocks;
	
	public int computeDistance(int x, int y){
		
		int minDistance = 0;		
		
		for(int i=0;i<lockerX.length;++i){
			
			int distance = Math.abs(lockerX[i]-x) + Math.abs(lockerY[i]-y);
			if(i == 0)minDistance = distance;
			else if(distance<minDistance) minDistance = distance;
		}		
		
		return minDistance;
	}
	
	public static void main(String args[]){
		
		AmazonLocker obj = new AmazonLocker();
		
		int x = 5;
		int y = 7;
		obj.lockerX = new int[]{2, 4};
		obj.lockerY = new int[]{3, 7};	
		
		obj.cityBlocks = new int[x][y];
		
		for(int j=0;j<y;++j){
			for(int i=0;i<x;++i){
				obj.cityBlocks[i][j] = obj.computeDistance(i+1, j+1);
				System.out.print(obj.cityBlocks[i][j]);
			}
			System.out.println("");
		}
	}
}
