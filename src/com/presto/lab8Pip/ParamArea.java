package com.presto.lab8Pip;


import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.LinkedList;


@ManagedBean(name = "paramArea", eager = true)
@ApplicationScoped
public class ParamArea implements Serializable {
    private LinkedList<Point> points = new LinkedList<>();
    private double currentR = 1.0;

    public void addPoint(Point point) {
        points.add(0, point);
    }

    public LinkedList<Point> getPoints() {
        return points;
    }

    public void scalePoints(double newR) {
        double scale = newR / currentR;
        for(Point point : points) {
            point.setxParam(point.getxParam() * scale);
            point.setyParam(point.getyParam() * scale);
            //TODO: DO CHECK!!!
        }
        currentR = newR;
    }

    @Override
    public String toString() {
        JSONArray pointsArray = new JSONArray();
        for(Point point : points) {
            JSONObject pointJson = new JSONObject();
            pointJson.put("x", point.getxParam());
            pointJson.put("y", point.getyParam());
            pointJson.put("r", point.getrParam());
            pointJson.put("inArea", point.getInArea());
            pointsArray.put(pointJson);
        }

        return pointsArray.toString();
    }
}