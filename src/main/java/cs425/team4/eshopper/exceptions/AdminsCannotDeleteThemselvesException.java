package cs425.team4.eshopper.exceptions;

public class AdminsCannotDeleteThemselvesException extends RuntimeException{
    public AdminsCannotDeleteThemselvesException(String username) { super(username + " you can't delete yourself."); }
}
