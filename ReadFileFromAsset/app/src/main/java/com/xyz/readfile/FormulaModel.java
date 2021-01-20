package com.xyz.readfile;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class FormulaModel implements Serializable {

    @SerializedName("formulae")
    private String formulae;

    @SerializedName("url")
    private String url;

    public String getFormulae() {
        return formulae;
    }

    public void setFormulae(String formulae) {
        this.formulae = formulae;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}