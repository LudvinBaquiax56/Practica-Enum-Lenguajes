/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Token;

/**
 *
 * @author baquiax
 */
public class Token {
    
    private String token;
    private TipoToken tipo;

    public Token(String token, TipoToken tipo) {
        this.token = token;
        this.tipo = tipo;
    }

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return the tipo
     */
    public TipoToken getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(TipoToken tipo) {
        this.tipo = tipo;
    }
    
}
