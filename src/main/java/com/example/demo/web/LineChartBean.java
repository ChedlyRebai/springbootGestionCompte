package com.example.demo.web;


import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dao.ClientRepository;
import com.example.demo.dao.CompteRepository;
import com.example.demo.entities.Client;
import com.example.demo.entities.Compte;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@Component
@ManagedBean
@ViewScoped
public class LineChartBean {
  @Autowired
  private DataService dataService;
  private LineChartModel lineModel;

  @Autowired
  private ClientRepository clientRepo;
  @Autowired
  private CompteRepository compteRepo;
  

  int mincompte=0;
  int maxcompte=0;

  Long maxClient;
  Long minClient;

  
  @PostConstruct
  public void init() {
      lineModel = new LineChartModel();
      LineChartSeries s = new LineChartSeries();
      s.setLabel("Population");

      maxClient = this.clientRepo.findAll().get(0).getCode();
      minClient = this.clientRepo.findAll().get(0).getCode();
      List<Client> listClient = this.clientRepo.findAll();
      List<Compte> listCompte = this.compteRepo.findAll();
      
      for (Compte compte : listCompte) {
        
      }

      for (Client client : listClient) {
        if(this.compteRepo.findByClientCode(client.getCode()).size()>maxcompte)
            maxcompte=this.compteRepo.findByClientCode(client.getCode()).size();

        if(this.compteRepo.findByClientCode(client.getCode()).size()<mincompte)
            mincompte=this.compteRepo.findByClientCode(client.getCode()).size();
        
        if(client.getCode()>maxClient)
            maxClient=client.getCode();

        if(client.getCode()<minClient)
            minClient=client.getCode();
        
      }


      dataService.getLineChartData().forEach(s::set);

      lineModel.addSeries(s);
     // lineModel.setLegendPosition("e");
      Axis y = lineModel.getAxis(AxisType.Y);
      y.setMin(mincompte);
      y.setMax(maxcompte);
      y.setLabel("number of accounts ");

      Axis x = lineModel.getAxis(AxisType.X);
      x.setMin(minClient);
      x.setMax(maxClient);

     // x.setTickInterval("100");
      x.setLabel("client code");


      
  }

  public LineChartModel getLineModel() {
      return lineModel;
  }
}