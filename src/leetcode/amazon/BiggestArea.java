package leetcode.amazon;

import java.util.ArrayList;
import java.util.List;

public class BiggestArea {

	private int[][] array;
	private int length;
	private int breath;
	
	public void findArea(){
		
		List<String> areaList = new ArrayList<String>();
		length = array.length;
		breath = array[0].length;		
		
		for(int i=0;i<length;++i){			
			for(int j=0;j<breath;++j){
				
				if(array[i][j] == 1 && !areaList.contains(i+""+j)){
					List<String> newAreaList = findArea(i, j, new ArrayList<String>());
					if(newAreaList.size() > areaList.size())areaList = newAreaList;
				}				
			}
		}	
		
		System.out.println(areaList.toString());
				
	}
	
	public List<String> findArea(int x, int y, List<String> areaList){
		
		if(!areaList.contains(x+""+y))areaList.add(x+""+y);
		else return areaList;
		
		if(x-1>=0 && y-1>=0 && array[x-1][y-1] == 1)findArea(x-1, y-1, areaList);
		if(x-1>=0 && y>=0 && array[x-1][y] == 1)findArea(x-1, y, areaList);
		if(x-1>=0 && y+1<breath && array[x-1][y+1] == 1)findArea(x-1, y+1, areaList);
		if(x>=0 && y-1>=0 && array[x][y-1] == 1)findArea(x, y-1, areaList);
		if(x>=0 && y+1<breath && array[x][y+1] == 1)findArea(x, y+1, areaList);
		if(x+1<length && y-1>=0 && array[x+1][y-1] == 1)findArea(x+1, y-1, areaList);		
		if(x+1<length && y>=0 && array[x+1][y] == 1)findArea(x+1, y, areaList);
		if(x+1<length && y+1<breath && array[x+1][y+1] == 1)findArea(x+1, y+1, areaList);
		
		return areaList;
		
	}
	
	public static void main(String args[]){
		
		BiggestArea obj = new BiggestArea();
		obj.array = new int[][]{{0,1,0,0},
								{1,1,1,0},
								{0,1,1,0},
								{0,0,0,0},
								{0,0,1,1}};
								
		obj.findArea();
	}
}
