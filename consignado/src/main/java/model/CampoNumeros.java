/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author lucka
 */
public class CampoNumeros extends PlainDocument {

    private Integer limite;

    public CampoNumeros(Integer limite) {
        this.limite = limite;
    }

    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        if (getLength() + str.length() <= limite) {
            super.insertString(offs, str.replaceAll("[^0-9]", ""), a);
        }
    }

}
