package binarySearchTree;

@SuppressWarnings("serial")
public class EmptyTreeException extends RuntimeException
{
    public EmptyTreeException (){
        super ();
    }; 

    public EmptyTreeException (String message){
        super (message);
    }; 
};