package minesweeper;

import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class main {

	public static void main(String[] args) {
		MineSweeper game=new MineSweeperImp();
		//game.displayInternal();
		game.display();
		int RowsNum=((MineSweeperImp) game).GetRows();
		int ColNum=((MineSweeperImp)game).GetCol();
		//game.flagAsMine(1, 0);
		//game.uncover(0, 0);
		//game.uncover(1, 1);
		//game.uncover(0, 0);
		//game.flagAsMine(0, 0);
		//game.displayRaw();
		boolean rowColIsValid = true;
		while(game.isGameOver()==false & game.isWinningGame()==false){
			int row=-1;
			int column=-1;
			char Action='B';
			String rowS="";
			String colS="";
			do{
				do{
				//try{
					rowColIsValid = true;
					rowS=JOptionPane.showInputDialog(null,"Ingrese fila");
					if(rowS==null){
						System.exit(0);
					}
					
				//}catch(Exception e){
					//rowColIsValid = false;
				//}
					
				}while(!IsNumeric(rowS));
			
			row=Integer.parseInt(rowS);
			}while(row>(RowsNum-1));
		do{
			do{
			//	try{
					rowColIsValid=true;
					colS=JOptionPane.showInputDialog(null,"Ingrese columna");
					if(colS==null){
						System.exit(0);
					}
					
			//	}catch(Exception e){
					rowColIsValid=false;
			//	}
			}while(!IsNumeric(colS));
			
			column=Integer.parseInt(colS);
		}while(column>(ColNum-1));
			do{
				//try{
					String ac=JOptionPane.showInputDialog(null,"Ingrese accion");
					if(ac==null){
						System.exit(0);
					}
					Action=ac.charAt(0);
			//	}catch(Exception e){
				
				//}
			}while(Action!='U' & Action!='F' & Action!='C');
			
			switch (Action) {
			case 'U':
				game.uncover(row, column);
				break;
			case 'F':
				game.flagAsMine(row, column);
				break;
			
				case 'C':
				game.clearFlag(row, column);
				break;
			}
		}
		if(game.isWinningGame()==true){
			System.out.println("Ganaste :D");
		}else{
			System.out.println("Perdiste D:");
		}
		game.displayInternal();
	}
	
	public static boolean IsNumeric(String s){
		boolean w=true;
		try{
		int i=Integer.parseInt(s);
		}catch(Exception e){
			w=false;
		}
		return w;
	}
}

