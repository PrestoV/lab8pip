package com.presto.lab8Pip;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.Date;


@ManagedBean(name = "interactiveDate")
@ApplicationScoped
public class InteractiveDate implements Serializable {
    public Date getDate() {
        return new Date();
    }
}