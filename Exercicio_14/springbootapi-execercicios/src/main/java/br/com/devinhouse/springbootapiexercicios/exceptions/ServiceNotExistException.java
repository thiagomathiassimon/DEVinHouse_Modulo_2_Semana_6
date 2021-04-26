package br.com.devinhouse.springbootapiexercicios.exceptions;
public class ServiceNotExistException extends RuntimeException{
    public ServiceNotExistException(String message){
        super(message);
    }

}
