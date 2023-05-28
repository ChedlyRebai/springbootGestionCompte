package com.example.demo.web;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.PieChartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dao.RetraitRepository;
import com.example.demo.dao.VersementRepository;
import com.example.demo.entities.Retrait;
import com.example.demo.entities.Versement;

@Component
@ManagedBean
@ViewScoped
public class PieChartBean {

    private PieChartModel pieModel;

    @PostConstruct
    public void init() {
        createPieModel();
    }

    
    @Autowired
private RetraitRepository retraitRepository;

@Autowired
private VersementRepository versementRepository;

List<Retrait> listRetrait = new ArrayList<>();
List<Versement> listVersements = new ArrayList<>();

    private void createPieModel() {
        listRetrait = retraitRepository.findAll();
        listVersements = versementRepository.findAll();
        pieModel = new PieChartModel();
        pieModel.set("Versement", listVersements.size());
        pieModel.set("Retrait", listRetrait.size());
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }
}

