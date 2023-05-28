package com.example.demo.web;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dao.CompteRepository;
import com.example.demo.entities.Compte;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;
import java.util.Map;

@Component
@ManagedBean
@ViewScoped
public class BarChartBean {
    @Autowired
    private DataService dataService;
    private BarChartModel barModel;

    @Autowired
  private CompteRepository compteRepo;

    @PostConstruct
    public void init() {
        barModel = new BarChartModel();

        ChartSeries series = new ChartSeries();
        series.setLabel("Population");

        List<ChartItem> chartData = dataService.getBarChartData();
        for (ChartItem item : chartData) {
            series.set(item.getLabel(), item.getValue());
        }

        barModel.addSeries(series);
        barModel.setLegendPosition("e");

        Axis y = barModel.getAxis(AxisType.Y);
        y.setMin(0);
        List<Compte> li=this.compteRepo.findAll();
        
        Double maxn=0.0;
        for (Compte compte : li) {
            if (compte.getSolde()>maxn) {
                maxn=compte.getSolde();
            }
        }

        y.setMax(maxn);
        y.setLabel("Solde");

        Axis x = barModel.getAxis(AxisType.X);
        x.setLabel("code compte");
    }

    public BarChartModel getBarModel() {
        return barModel;
    }
}
