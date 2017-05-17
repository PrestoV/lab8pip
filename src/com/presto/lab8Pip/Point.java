package com.presto.lab8Pip;

import javax.annotation.PostConstruct;
import javax.faces.bean.*;
import java.io.Serializable;
import java.util.ArrayList;


@ManagedBean(name = "point")
@RequestScoped
public class Point implements Serializable {
    private Boolean inArea;

    @ManagedProperty("#{paramArea}")
    private ParamArea paramArea;

    @ManagedProperty(value="#{param.get(\"form:x_param\")}")
    private Double xParam;

    @ManagedProperty(value="#{param.get(\"form:y_param\")}")
    private Double yParam;

    @ManagedProperty(value="#{param.get(\"form:r_param\")}")
    private Double rParam;

    @PostConstruct
    public void init() {
        if(xParam != null && yParam != null && rParam != null) {
            inArea = false;
            paramArea.addPoint(this);
        }
    }

    public ParamArea getParamArea() {
        return paramArea;
    }

    public void setParamArea(ParamArea paramArea) {
        this.paramArea = paramArea;
    }

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

    public Boolean getInArea() {
        return inArea;
    }

    public void setInArea(Boolean inArea) {
        this.inArea = inArea;
    }
}