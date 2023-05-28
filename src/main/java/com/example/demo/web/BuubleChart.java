package com.example.demo.web;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BubbleChartModel;
import org.primefaces.model.chart.BubbleChartSeries;
import org.springframework.stereotype.Component;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.BubbleChartModel;
import org.primefaces.model.chart.BubbleChartSeries;


@Component
@ManagedBean
@ViewScoped
public class BuubleChart implements Serializable {

    private BubbleChartModel model;

    public BuubleChart() {
        model = new BubbleChartModel();
        model.add(new BubbleChartSeries("Acura", 0, 0,55));
        model.add(new BubbleChartSeries("Alfa Romeo", 45, 92, 36));
        model.add(new BubbleChartSeries("AM General", 24, 104, 40));
        model.add(new BubbleChartSeries("Bugatti", 50, 123, 60));
        model.add(new BubbleChartSeries("BMW", 15, 89, 25));
        model.add(new BubbleChartSeries("Audi", 40, 180, 80));
        model.add(new BubbleChartSeries("Aston Martin", 70, 70, 48));
        model.setTitle("Bubble Chart");
        model.getAxis(AxisType.X).setLabel("Price");
        Axis yAxis = model.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(250);
        yAxis.setLabel("Labels");
    }
    public BubbleChartModel getModel() {
        return model;
    }

}
