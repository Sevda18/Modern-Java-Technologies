package bg.sofia.uni.fmi.mjt.fittrack.exception;

public class InvalidWorkoutException extends RuntimeException{
    public InvalidWorkoutException(String name)
    {
        super(name);
    }

    public InvalidWorkoutException(String name,Throwable cause )
    {
        super(name, cause);
    }
}
