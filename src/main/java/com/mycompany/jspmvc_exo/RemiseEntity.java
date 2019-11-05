/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jspmvc_exo;

/**
 *
 * @author Diego
 */
public class RemiseEntity {

    
    private String code;
    private float taux;
    
    public RemiseEntity(String code, float taux) {
        this.code = code;
        this.taux = taux;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @return the taux
     */
    public float getTaux() {
        return taux;
    }

    
    
    
}
