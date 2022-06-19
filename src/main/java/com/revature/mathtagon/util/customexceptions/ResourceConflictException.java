package com.revature.mathtagon.util.customexceptions;

public class ResourceConflictException extends RuntimeException{
    public ResourceConflictException(){ super();}

    public ResourceConflictException(String message){ super(message);}
}
