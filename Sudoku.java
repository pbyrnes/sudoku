class Sudoku{
	static long numBoards = 0;
	static void outputBoard(int[] b){
		for(int i=0; i<9; i++){
			if(i==3 || i==6)
				System.out.println("----- ----- -----");
			for(int j=0; j<9; j++){
				if(b[9*i+j] > 0)
				  System.out.print(b[9*i+j]);
			  else
				  System.out.print(" ");
				if(j == 2 || j == 5)
					System.out.print("|");
				else
					System.out.print(" ");
			}
			System.out.println();
		}
	}
		
	static void fillBoard(int[] board, int index){
		if(index > 80){
			System.out.println("Solution found: ");
			outputBoard(board);
			return;
		}
		if(fixed[index]){
			fillBoard(board, index+1);
			return;
		}
		int [] newBoard = new int[81];
		for(int i=0; i<81; i++)
			if(i<index || fixed[i])
				newBoard[i] = board[i];
		for(int i=1; i<=9; i++){
			numBoards++;
			newBoard[index] = i;
			if(checkBoard(newBoard))
				fillBoard(newBoard, index+1);
		}
	}
	
	static boolean checkBoard(int[] board){
		//check rows
		for(int r=0; r<9; r++)
			for(int i=0; i<9; i++)
				if(board[9*r+i] > 0)
					for(int j=i+1; j<9; j++)
						if(board[9*r+i] == board[9*r+j])
							return false;
		
		//check columns
		for(int c=0; c<9; c++)
			for(int i=0; i<9; i++)
				if(board[9*i+c] > 0)
					for(int j=i+1; j<9; j++)
						if(board[9*i+c] == board[9*j+c])
							return false;
		
		//check boxes
		for(int a=0; a<3; a++)
			for(int b=0; b<3; b++)
				for(int i=0; i<3; i++)
					for(int j=0; j<3; j++)
						if(board[27*a+9*i+3*b+j] > 0)
							for(int p=i; p<3; p++)
								for(int q=0; q<3; q++)
									if(board[27*a+9*i+3*b+j] == board[27*a+9*p+3*b+q] && 27*a+9*i+3*b+j != 27*a+9*p+3*b+q)
										return false;
		
		return true;
	}
	
	static boolean[] fixed = new boolean[81];
		
	public static void main(String[] args){
		//read in puzzle

		String start = "-74---2-6";
		start += 	   "---4-----";
		start += 	   "---1--59-";
		start += 	   "----6-34-";
		start += 	   "6--7-9--5";
		start += 	   "-21-8----";
		start += 	   "-32--7---";
		start += 	   "-----8---";
		start += 	   "7-6---93-";

		for(int i=0; i<81; i++)
			if(start.charAt(i) != '-')
				fixed[i] = true;
			
		int[] board = new int[81];
		for(int i=0; i<81; i++)
			if(fixed[i])
				board[i] = Character.getNumericValue(start.charAt(i));
			else
				board[i] = -1;
			
		System.out.println("initial board: ");
		outputBoard(board);
		System.out.println();
		
		fillBoard(board,0);
		System.out.println("checked " + numBoards + " boards");
	}
}