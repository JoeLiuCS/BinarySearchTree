package binarySearchTree;

import java.util.Scanner;

public class play {

	
	
	public static void main(String[] args) {

		binarySearchTree<Integer> game = new binarySearchTree<>();
		playGame(game);
		
	}
	
	
	
	
	static void playGame(binarySearchTree<Integer> game){
		boolean keepToPlay = true;
		initialize(game);
		while(keepToPlay){
		  String command = menu();
		  if(command.equals("e")||command.equals("E")){
			  keepToPlay = false;
		  }
		  read(command,game);
		}
	}
	
	static void initialize(binarySearchTree<Integer> game){
		System.out.println("Please tell me how many numbers for initialize : ");
		Scanner scan1 = new Scanner(System.in);
		int number = scan1.nextInt();
		for(int i =0;i<number;i++){
            System.out.println("Please enther initialize numbers #" +(i+1)+ " : ");
            Scanner scan = new Scanner(System.in);
            int n = scan.nextInt();
            game.add(n);
		}
		System.out.print("Inorder : ");
		game.inorderPrint();
		System.out.print("\nPreorder : ");
		game.preoderPrint();
		System.out.print("\nPostorder : ");
		game.postPrint();
	}
	
	static String menu(){
		System.out.println("\nI : Inset a value");
		System.out.println("D : Delete a value");
		System.out.println("P : Find predecessor");
		System.out.println("S : Find successor");
		System.out.println("E : Exit the program");
		System.out.println("H : Display this message");
		System.out.println("Get me a command please : ");
		
		Scanner scan = new Scanner(System.in);
		String s = scan.next();
		return s;
	}
	
	static void  read (String command,binarySearchTree<Integer> game){
		switch(command.toUpperCase()){
			case "I":
				System.out.println("Give me a number please : ");
				Scanner scan = new Scanner(System.in);
				int n = scan.nextInt();
				game.add(n);
				System.out.println("In-order print : ");
				game.inorderPrint();
				break;
			case "D":
				System.out.println("Give me a number please : ");
				Scanner scan2 = new Scanner(System.in);
				int n2 = scan2.nextInt();
				if(game.contains(n2)){
					game.remove(n2);
					System.out.println("In-order print : ");
					game.inorderPrint();
				}
				else{
					System.out.println("The number does not exsist!");
				}
				break;
			case "P":
				System.out.println("Give me a number please : ");
				Scanner scan3 = new Scanner(System.in);
				int n3 = scan3.nextInt();
				if(game.contains(n3)){
					System.out.println("Presuccessor: "+game.findPresuccessor(n3));
				}
				else{
					System.out.println("The number does not exsist!");
				}
				break;
			case "S":
				System.out.println("Give me a number please : ");
				Scanner scan4 = new Scanner(System.in);
				int n4 = scan4.nextInt();
				if(game.contains(n4)){
                    System.out.println("Successor: "+game.findSuccessor(n4));
				}
				else{
					System.out.println("The number does not exsist!");
				}
				break;
			case "E":
				System.out.println("Bye!");
				break;
			case "H":
				break;
			default:
					System.out.println("Does not have this command!");	
					break;
		}
	}

}
