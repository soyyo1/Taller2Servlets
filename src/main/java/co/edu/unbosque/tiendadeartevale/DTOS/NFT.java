package co.edu.unbosque.tiendadeartevale.DTOS;

import com.opencsv.bean.CsvBindByName;

public class NFT {

    @CsvBindByName
    private String titulo;

    @CsvBindByName
    private String coins;

    @CsvBindByName
    private String path;

    public NFT() {}

    public NFT(String titulo, String coins, String path) {
        this.titulo = titulo;
        this.coins = coins;
        this.path = path;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCoins() {
        return coins;
    }

    public void setCoins(String coins) {
        this.coins = coins;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
