package ks.msx.SpringSecurity.exceptions;

public class PostTitleException extends RuntimeException{

    public PostTitleException(String message){
        System.out.println(message);
    }
}
