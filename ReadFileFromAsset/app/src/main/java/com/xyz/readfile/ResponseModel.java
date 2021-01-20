package com.xyz.readfile;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ResponseModel implements Serializable {

    @SerializedName("formulas")
    private List<FormulaModel> formulaModelList;

    public void setFormulas(List<FormulaModel> formulaModelList) {
        this.formulaModelList = formulaModelList;
    }

    public List<FormulaModel> getFormulaModelList() {
        return formulaModelList;
    }

}