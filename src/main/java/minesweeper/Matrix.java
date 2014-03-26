package minesweeper;

import java.util.Random;
import java.util.Set;

import javax.swing.JOptionPane;

import com.despegar.highflight.utils.Matrix2DCellPosition;
import com.despegar.highflight.utils.MatrixUtils;

public class Matrix {
	private int nrows;
    private int ncols;
    private Cell[][] matrix;
    private int[][] BinaryMatrix;

    //Matrix constructor
    public Matrix(int rows,int cols) {
        this.nrows = rows;
        this.ncols = cols;
        this.BinaryMatrix=new int[nrows][ncols];
        this.matrix=new Cell[nrows][ncols];
        
        //Calculates how many mines the game will have
        int MinesNum= (int)(rows*cols*15)/100;
        
       //Selects randomly matrix positions and sets type of those cells to "M"=mine
        for(int i=0;i<=MinesNum;i++){
        	Random rand = new Random();
        	int rowValue = rand.nextInt(rows);
        	Random random = new Random();
        	int colValue = rand.nextInt(cols);
        	Cell c = this.GetCell(rowValue, colValue);
        	if(c==null){
        		Integer num=this.getIntM(rowValue, colValue);
    			num=new Integer(1);
    			this.setIntM(rowValue, colValue, num);
        		c=new Cell();
        		c.setType("M");
        		c.setMinesAround(0);
        		c.setIsCovered(true);
        		c.setIsFlaged(false);
        		this.SetCell(rowValue, colValue, c);
        	}
        }
        
        
        //Goes through the matrix and sets type to "C"=Common (Only cells which weren't already taged)
        for (int row = 0; row < matrix.length; row++) {
        	for (int column = 0; column < matrix[row].length; column++){
        		Cell c=this.GetCell(row, column);
        		if(c==null){
        			Integer num=this.getIntM(row, column);
        			num=new Integer(0);
        			this.setIntM(row, column, num);
          			c=new Cell();
        			c.setType("C");
        			c.setMinesAround(0);
        			c.setIsCovered(true);
        			c.setIsFlaged(false);
        			this.SetCell(row, column, c);
        		}else{
        			
        		}
        	} 
        }
        
        //If finds a mine it calls another method
        for(int row=0; row< matrix.length; row++){
        	for (int column=0;column<matrix[row].length;column++){
        		Cell c=this.GetCell(row, column);
        		if(c==null){
        		System.out.println("Es null cra");
        		}
        		if(c.getType()=="M"){
        			this.AddMineToAdjacent(row, column);
        		}
        	}
        }
        
        
        
        

    }
    
    
    //This two methods are used to put or clear a flag from a cell
    public void Flag(int row, int col){
    	Cell c =this.GetCell(row, col);
    	c.setIsFlaged(true);
    	this.SetCell(row, col, c);
    }
    
    public void ClearFlag(int row, int col){
    	Cell c =this.GetCell(row, col);
    	c.setIsFlaged(false);
    	this.SetCell(row, col, c);
    }
    
    
    //Sets IsCovered cell property to false
    public void uncover(int row, int col) {
    	Cell d=this.GetCell(row, col);
    	if(d.getType()!="M"){
    		Set<Matrix2DCellPosition> SP=MatrixUtils.cascade(BinaryMatrix, row, col);
    		for(Matrix2DCellPosition Pos:SP){
    			Cell c=this.GetCell(Pos.getRow(),Pos.getColumn());
    			c.setIsCovered(false);
    			this.SetCell(Pos.getRow(),Pos.getColumn(),c);
    		}
    		}else{
    			d.setIsCovered(false);
    	}
	}
    
    
    //Prints only uncovered cells and a '-' for covered cells
    public void Print(){
    	for (int row = 0; row < matrix.length; row++) {       
    		for (int column = 0; column < matrix[row].length; column++){
    			Cell c = this.GetCell(row, column);
        		if(c.getIsCovered()==true){
        			if(c.getIsFlaged()==true){
        				System.out.print("F  ");
        			}else{
        				System.out.print("-"+"  ");
        			}
        		}else{
        				if(c.getType().equals("M")){
        					System.out.print("M" + "  ");
        				}else{
        					System.out.print(c.getMinesAround()+"  ");
        				}
        			}
        	}		
    		System.out.println();
        }
    	System.out.println();
    }
    
    
    //Prints all mines and values
    public void PrintInternal(){
    	for (int row = 0; row < matrix.length; row++) {       
    		for (int column = 0; column < matrix[row].length; column++){
        		if(this.GetCell(row, column).getType().equals("M")){
        			System.out.print("M" + "  ");
        		}else{
        			Cell c=this.GetCell(row, column);
        			System.out.print(c.getMinesAround()+"  ");
        		}
        	}		
    		System.out.println();
        }
    	System.out.println();
    }
    
    
  //Prints the binaryMatrix
    public void PrintRaw(){
    	for (int row = 0; row < matrix.length; row++) {       
    		for (int column = 0; column < matrix[row].length; column++){
        		System.out.print(this.getIntM(row, column)+"  ");
        	}		
    		System.out.println();
        }
    	System.out.println();
    }
    
    //Checks if all not mine cells are uncovered
    public Boolean HasWin(){
    	Boolean w=new Boolean(true);
    	for (int row = 0; row < matrix.length; row++) {       
    		for (int column = 0; column < matrix[row].length; column++){
    			Cell c=this.GetCell(row, column);
    			if(c.getType().equals("C") && c.getIsCovered()==true){
    				w=false;
    				break;
    			}
    		}		
    	}
		return w;
    }
    
    
    //Checks if a mine has been uncovered
    public Boolean GameOver(){
    	Boolean w=false;
    	for (int row = 0; row < matrix.length; row++) {       
    		for (int column = 0; column < matrix[row].length; column++){
    			Cell c=this.GetCell(row, column);
    			if(c.getType().equals("M") && c.getIsCovered()==false){
    				w=true;
    			}
    		}		
    	}
		return w;
    }
    
    
    //This method adds +1 to all adjacent cells which are not mines
    public void AddMineToAdjacent(int row, int col){
    	Cell c;
    	try{
    		c=this.GetCell(row-1, col-1);
    		if(c.getType()!="M"){
    			
    				c.setMinesAround(c.getMinesAround()+1);
    			
    			this.SetCell(row-1, col-1, c);
    		}
    	}catch(Exception e){}
    	try{
    		c=this.GetCell(row, col-1);
    		if(c.getType()!="M"){
    			
    				c.setMinesAround(c.getMinesAround()+1);
    			
    			this.SetCell(row, col-1, c);
    		}
    	}catch(Exception e){
    	}	
    	try{
    		c=this.GetCell(row-1, col);
    		if(c.getType()!="M"){
    			
    				c.setMinesAround(c.getMinesAround()+1);
    			
    	   		this.SetCell(row-1, col, c);
    		}
    	}catch(Exception e){}
    	try{
    		c=this.GetCell(row+1, col);
    		if(c.getType()!="M"){
    			
    				c.setMinesAround(c.getMinesAround()+1);
    			
    			this.SetCell(row+1, col, c);
    		} 
    	}catch(Exception e){}
    	try{
    		c=this.GetCell(row, col+1);
    		if(c.getType()!="M"){
    			
    				c.setMinesAround(c.getMinesAround()+1);
    			
    			this.SetCell(row, col+1, c);
    		}
    	}catch(Exception e){}
    	try{
    		c=this.GetCell(row+1, col+1);
    		if(c.getType()!="M"){
    			
    				c.setMinesAround(c.getMinesAround()+1);
    			
    			this.SetCell(row+1, col+1, c);
    		}
    	}catch(Exception e){}
    	try{
    		c=this.GetCell(row-1, col+1);
    		if(c.getType()!="M"){
    			
    				c.setMinesAround(c.getMinesAround()+1);
    			
    			this.SetCell(row-1, col+1, c);
    		}
    	}catch(Exception e){}
    	try{
    		c=this.GetCell(row+1, col-1);
    		if(c.getType()!="M"){
    			
    				c.setMinesAround(c.getMinesAround()+1);
    			
    			this.SetCell(row+1, col-1, c);
    		}
    	}catch(Exception e){}
    }
    
    //Returns the number of rows of the matrix
   public int GetNumRows(){
	   return matrix.length;
   }
   
   //Returns number of columns of the matrix
   public int GetNumCol(){
	   return matrix[0].length;
   }
    //Returns a Cell object
    public Cell GetCell(int row, int col){
    	return matrix[row][col];
    }
    //Saves a Cell object into the matrix
    public void SetCell(int row, int col, Cell c){
    	matrix[row][col]=c;
    }

    //Returns an integer from binary matrix
	public int getIntM(int row, int col) {
		return BinaryMatrix[row][col];
	}

	//Saves an integer into binary matrix
	public void setIntM(int row, int col,int Value) {
		BinaryMatrix[row][col]=Value;
	}
   
}
