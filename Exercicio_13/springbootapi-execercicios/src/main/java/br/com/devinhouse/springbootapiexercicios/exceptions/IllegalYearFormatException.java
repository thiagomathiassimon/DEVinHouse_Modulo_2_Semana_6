package br.com.devinhouse.springbootapiexercicios.exceptions;

public class IllegalYearFormatException extends RuntimeException{

    public IllegalYearFormatException(String message){
        super(message);
    }

}
