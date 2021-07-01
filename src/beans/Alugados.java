/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.Date;


/**
 *
 * @author Guilh
 */
public class Alugados {
    private int id;
    private Clientes clientescpf;
    private Carros carrosid;
    private Date data;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Clientes getClientescpf() {
        return clientescpf;
    }

    public void setClientescpf(Clientes clientescpf) {
        this.clientescpf = clientescpf;
    }

    public Carros getCarrosid() {
        return carrosid;
    }

    public void setCarrosid(Carros carrosid) {
        this.carrosid = carrosid;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
