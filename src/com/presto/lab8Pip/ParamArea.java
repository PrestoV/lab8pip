package com.presto.lab8Pip;

import javax.faces.bean.*;
import java.io.Serializable;


@ManagedBean(name = "paramArea")
@RequestScoped
public class ParamArea implements Serializable {
    @ManagedProperty(value="#{param.get(\"form:x_param\")}")
    private Double xParam;

    @ManagedProperty(value="#{param.get(\"form:y_param\")}")
    private Double yParam;

    @ManagedProperty(value="#{param.get(\"form:r_param\")}")
    private Double rParam;

    public Double getxParam() {
        return xParam;
    }

    public Double getyParam() {
        return yParam;
    }

    public Double getrParam() {
        return rParam;
    }

    public void setxParam(Double xParam) {
        this.xParam = xParam;
    }

    public void setyParam(Double yParam) {
        this.yParam = yParam;
    }

    public void setrParam(Double rParam) {
        this.rParam = rParam;
    }
}
