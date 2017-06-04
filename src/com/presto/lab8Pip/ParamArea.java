package com.presto.lab8Pip;


import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;


@ManagedBean(name = "paramArea")
@ApplicationScoped
public class ParamArea implements Serializable {
    public void addPoint(Point point) {
        point.setInArea(
                pointCheck(point.getxParam(), point.getyParam(), point.getrParam())
        );
        ( new PointDAO() ).save(point);
    }

    public List<Point> getPoints() {
        return ( new PointDAO() ).getAll();
    }

    public ParamArea() {
    //  ( new PointDAO() ).incrementPoints();
    }

    public String getScaledPointList() {
        JSONArray pointsArray = new JSONArray();
        List<Point> points = ( new PointDAO() ).getAll();
        Double r = null;
        String rValue;

        rValue = FacesContext.getCurrentInstance().
                    getExternalContext().getRequestParameterMap().get("rform:r");
        if(rValue == null) {
            rValue = FacesContext.getCurrentInstance().
                    getExternalContext().getRequestParameterMap().get("form:r_param");
        }

        if(rValue != null) {
            r = Double.parseDouble(rValue);
        }

        for (Point point : points) {
            JSONObject pointJson = new JSONObject();
            point.setInArea(
                    pointCheck(point.getxParam(), point.getyParam(), r != null ? r : point.getrParam())
            );
            pointJson.put("x", r != null ?
                    point.getxParam() * point.getrParam() / r
                    : point.getxParam());
            pointJson.put("y", r != null ?
                    point.getyParam() * point.getrParam() / r
                    : point.getyParam());
            pointJson.put("r",  point.getrParam());
            pointJson.put("inArea", point.getInArea());
            pointsArray.put(pointJson);
        }

        return pointsArray.toString();
    }

    private boolean pointCheck(double x, double y, double r) {
        if(x > 0) {
            if(y > 0)
                return checkFirstRegion(x, y, r);
            else if(y < 0)
                return checkFourthRegion(x, y, r);
            else
                return checkFirstRegion(x, y, r) || checkFourthRegion(x, y, r);
        }
        else if (x < 0) {
            if(y > 0)
                return checkSecondRegion(x, y, r);
            else if(y < 0)
                return checkThirdRegion(x, y, r);
            else
                return checkSecondRegion(x, y, r) || checkThirdRegion(x, y, r);
        }
        else {
            if(y > 0)
                return checkFirstRegion(x, y, r) || checkSecondRegion(x, y, r);
            else if(y < 0)
                return checkThirdRegion(x, y, r) || checkFourthRegion(x, y, r);
            else
                return checkFirstRegion(x, y, r) || checkSecondRegion(x, y, r)
                        || checkThirdRegion(x, y, r) || checkFourthRegion(x, y, r);
        }
    }

    private boolean checkFirstRegion(double x, double y, double r) {
        return x >= 0 && y >= 0
                && x <= r && y <= r/2;
    }

    private boolean checkSecondRegion(double x, double y, double r) {
        return x <= 0 && y >= 0
                && (x*x + y*y <= r*r/4);
    }

    private boolean checkThirdRegion(double x, double y, double r) {
        return x <= 0 && y <= 0
                && x >= -r && y >= (-2*x - r);
    }

    private boolean checkFourthRegion(double x, double y, double r) {
        return false;
    }
}