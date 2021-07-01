/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author Guilh
 */
public class Carros {
    private int id;
    private String marca;
    private String modelo;
    private String cor;
    private int quilometragem;
    private int valordia;
    private int valorkm;
    private boolean status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public int getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(int quilometragem) {
        this.quilometragem = quilometragem;
    }

    public int getValordia() {
        return valordia;
    }

    public void setValordia(int valordia) {
        this.valordia = valordia;
    }

    public int getValorkm() {
        return valorkm;
    }

    public void setValorkm(int valorkm) {
        this.valorkm = valorkm;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    public String toString(){
        return this.id+" "+this.modelo+" "+this.cor;
    }
}
