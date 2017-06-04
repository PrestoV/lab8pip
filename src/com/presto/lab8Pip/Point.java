package com.presto.lab8Pip;

import javax.annotation.PostConstruct;
import javax.faces.bean.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;


@ManagedBean(name = "point")
@RequestScoped
@Entity
@Table(name="point")
public class Point implements Serializable {
    @ManagedProperty("#{paramArea}")
    @Transient
    private ParamArea paramArea;

    @Id @GeneratedValue
    @Column(name="id")
    private long id;

    @ManagedProperty(value="#{param.get(\"form:x_param\")}")
    @Column(name="x")
    private Double xParam;

    @ManagedProperty(value="#{param.get(\"form:y_param\")}")
    @Column(name="y")
    private Double yParam;

    @ManagedProperty(value="#{param.get(\"form:r_param\")}")
    @Column(name="r")
    private Double rParam;

    @Column(name="inarea")
    private Boolean inArea;

    public Point() {

    }

    public Point(Double xParam, Double yParam, Double rParam, Boolean inArea) {
        this.xParam = xParam;
        this.yParam = yParam;
        this.rParam = rParam;
        this.inArea = inArea;
    }



    @PostConstruct
    public void init() {
        if(xParam != null && yParam != null && rParam != null) {
            paramArea.addPoint(this);
        }
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

    public ParamArea getParamArea() {
        return paramArea;
    }

    public void setParamArea(ParamArea paramArea) {
        this.paramArea = paramArea;
    }
}