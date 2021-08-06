/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AnalizadorLexico;

import Token.*;
import Token.Token;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author baquiax
 */
public class AnalizadorLexico {

    private List<Token> tokens;

    public AnalizadorLexico(List<Token> tokens) {
        this.tokens = tokens;
    }

    public AnalizadorLexico() {
        this.tokens = new ArrayList<Token>();
    }

    public void analizarTexto(String texto) {
        String token = "";
        for (int i = 0; i < texto.length(); i++) {
            char actual = texto.charAt(i);
            if (!validarError(actual)) {
                token += actual;
                tokens.add(new Token(token, TipoToken.ERROR));
                token = "";
            }
            if (validarSimbolos(actual)) {
                token += actual;
                tokens.add(new Token(token, TipoToken.SIMBOLO));
                token = "";
            }
        }
    }

    public boolean validarError(char actual) {
        return validarSimbolos(actual) || validarNumero(actual) || validarPunto(actual)
                || validarletra(actual);
    }

    public boolean validarNumero(char caracter) {
        return (caracter >= 48 && caracter <= 57);
    }

    public boolean validarletra(char caracter) {
        return ((caracter >= 65 && caracter <= 90)
                || (caracter >= 97 && caracter <= 122));
    }

    public boolean validarSimbolos(char caracter) {
        return (caracter == 123 || caracter == 125 || caracter == 91
                || caracter == 93 || caracter == 44 || caracter == 59);
    }

    public boolean validarPunto(char caracter) {
        return caracter == 46;
    }

    /**
     * @return the tokens
     */
    public List<Token> getTokens() {
        return tokens;
    }

    /**
     * @param tokens the tokens to set
     */
    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }

}
