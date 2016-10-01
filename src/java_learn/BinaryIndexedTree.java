package java_learn;

public class BinaryIndexedTree {

	public int [] treeArr;
	public int size;
	
	
	public BinaryIndexedTree(){
				
	}

	public BinaryIndexedTree(int [] arr){
		size = arr.length;
		treeArr = new int[size+1];
		for(int i = 1 ; i < size+1 ; i++){	//BIT ARRAY INDEXES START AT 1
			treeArr[i] = 0;
		}		
		for(int i = 1 ; i < size+1 ; i++){ //BIT ARRAY INDEXES START AT 1
			add(arr[i-1],i);			
		}
		
	}

	//arrayStart at 1
	//1,2,4,8 (cum sum of 1,1->2,1->4,1->8)
	//3  (cum sum of 3)
	//5,6  (cum sum of 5,5->6)
	//7    (cum sum of 7)
	//9,10,12   (cum sum of 9,9->10,9->12)
	//11		(cum sum of 11)
	//13,14		(cum sum of 13,13->14)
	//15		(cum sum of 15)	
	//if you change index 4-> you need to change 4,8
	public void add(int value,int index){
		//the magic of the last set bit is that i can be seen as a pointer
		//draw a square to represent each tree to make it more clear
		// 3(0011) + lastsetbit -> 4
		// 4(0101) + lastsetbit -> 8
		// 5(0110) + lastsetbit -> 6
		// 6(0110) + lastsetbit -> 8
		//the last set bit is key to indicate which other values of the tree to add		
		int lastSetBit;
		while(index <= size){
			treeArr[index] += value;
			lastSetBit = (index & -index); //get the last bit component 
			index += lastSetBit;	//add the last set bit(make sure to update the value of the cumulative indeces)
		}
				
	}
	
	//what is the value of 15?
	//8,4,2,1
	//15(1111) ->treeArr15(1111)+treeArr14(1110) + treeArr12(1100) + treeArr8(1000)
	//15 -> 15 + 13->14 + 9->12 + 1->8
	public int sum(int index){
		int lastSetBit;
		int sum = 0;
		while(index > 0){
			sum = sum+treeArr[index];
			lastSetBit = (index & -index); //get the last bit component 
			index -= lastSetBit;	//remove the last set bit			
		}
		return sum;
	}
	
	
	/*
	 * BIT ARRAY STARTS AT 1 FOR EASIER IMPLEMENTATION
	 * 
	 */
	public static void main(String[] args){
		int []arr = new int[]{1,7,3,0,5,8,3,2,6,2,1,1,4,5};
		int []sol = new int[]{1,8,3,11,5,13,3,29,6,8,1,10,4,9};
		BinaryIndexedTree tree = new BinaryIndexedTree(arr);
		System.out.print("Your tree:\n");
		for(int i = 1 ; i <= arr.length ; i++){
			System.out.print(tree.treeArr[i] + ",");
		}
		System.out.print("\nSOLUTION:\n");
		for(int i = 0 ; i < arr.length ; i++){
			System.out.print(sol[i] + ",");
		}
		System.out.print("\nSum(13):\n");
		System.out.print("Yours:"+tree.sum(13)+"\n");
		System.out.print("Ans:"+43);
		System.out.print("\nSum(7):\n");
		System.out.print("Yours:"+tree.sum(7)+"\n");
		System.out.print("Ans:"+27);

	}
}

