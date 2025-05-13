package com.exemplo.encurtadorurl.dto;

public class UrlRequestDTO {
    private String url;
    private String username;
    private  String senha;

    public String getUrl() {return url;}
    public void setUrl(String url) {this.url = url; }

    public String getUsername() {return username; }
    public void setUsername(String username) { this.username = username; }

    public String getSenha() {return senha;}
    public void setSenha(String senha) { this.senha = senha; }
}
