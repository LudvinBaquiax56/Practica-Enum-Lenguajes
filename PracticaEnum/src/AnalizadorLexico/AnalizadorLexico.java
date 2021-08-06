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
        texto += " ";
        String token = "";
        TipoToken tipo = null;
        for (int i = 0; i < texto.length() - 1; i++) {
            char actual = texto.charAt(i);
            tipo = validarTokenLargo(actual, tipo);
            if (tipo == null) {
                if (validarSLE(actual)) {
                    token = "";
                } else {
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
            } else {
                switch (tipo) {
                    case ID:
                        token += actual;
                        if (!validarNumero(texto.charAt(i + 1)) && !validarletra(texto.charAt(i + 1))) {
                            tokens.add(new Token(token, TipoToken.ID));
                            token = "";
                        }
                        break;
                    case NUMERO_ENTERO:
                        if (validarNumero(actual)) {
                            token += actual;
                            if (validarPunto(texto.charAt(i + 1))) {
                                tipo = TipoToken.NUMERO_DECIMAL;
                            }
                            if (!validarNumero(texto.charAt(i + 1)) && tipo == TipoToken.NUMERO_ENTERO) {
                                tokens.add(new Token(token, TipoToken.NUMERO_ENTERO));
                                token = "";
                            }
                        }
                        break;
                    case NUMERO_DECIMAL:
                        if (validarNumero(actual) || validarPunto(actual)) {
                            token += actual;
                            if (!validarNumero(texto.charAt(i + 1))) {
                                tokens.add(new Token(token, TipoToken.NUMERO_DECIMAL));
                                token = "";
                            }
                        }
                        break;
                    default:
                        throw new AssertionError();
                }
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

    public boolean validarSLE(char caracter) {
        return caracter == 32 || caracter == 10;
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

    private TipoToken validarTokenLargo(char actual, TipoToken tipo) {
        if ((tipo == null || tipo == TipoToken.NUMERO_ENTERO)
                && validarNumero(actual)) {
            return TipoToken.NUMERO_ENTERO;
        }
        if ((tipo == null || tipo == TipoToken.ID)
                && validarletra(actual)) {
            return TipoToken.ID;
        }
        if (tipo == TipoToken.ID && (validarletra(actual) || validarNumero(actual))) {
            return TipoToken.ID;
        }
        if (tipo == TipoToken.NUMERO_ENTERO && validarPunto(actual)) {
            return TipoToken.NUMERO_DECIMAL;
        }
        if (tipo == TipoToken.NUMERO_DECIMAL && validarNumero(actual) || validarPunto(actual)) {
            return TipoToken.NUMERO_DECIMAL;
        }
        return null;
    }

}
