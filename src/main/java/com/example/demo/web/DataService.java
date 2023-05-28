package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;
import com.example.demo.dao.ClientRepository;
import com.example.demo.dao.CompteRepository;
import com.example.demo.entities.Client;
import com.example.demo.entities.Compte;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
@Service
public class DataService {

  @Autowired
  private ClientRepository clientrepo;

  
  @Autowired
  private CompteRepository compteRepository;
  
  List<Compte> listCompte;
  List<Client> listClient;
  public Map<Long, Integer> getLineChartData() {
      Map<Long, Integer> map = new HashMap<>();
     // List<Client> listclient=this.clientrepo.findAll();
      /*for (Client client : listclient) {
      
        double doubleValue = client.getComptes().size();
        map.put(client.getCode().intValue(), client.getComptes().size()   );
      }*/
      
      listClient= this.clientRepo.findAll();
      listCompte= this.compteRepo.findAll();
      
       for (Client client : listClient) {   
         map.put( client.getCode(), compteRepo.findByClientCode(client.getCode()).size());
         System.out.println(compteRepo.findByClientCode(client.getCode()).size());
         System.out.println(client.getCode());
      }
      
     /*  map.put(1, 5.20);
      map.put(2, 19.63);
      map.put(3, 59.01);
      map.put(4, 139.76);
      map.put(5, 300.4);
      map.put(6, 630.0);*/

      return map;
  }

  @Autowired
  private CompteRepository compteRepo;

  public List<ChartItem> getBarChartData() {
    List<ChartItem> chartData = new ArrayList<>();
    List<Compte> listComptes = compteRepo.findAll();
    
    for (Compte compte : listComptes) {
      chartData.add(new ChartItem(""+compte.getCodeCompte()+"", compte.getSolde()));
      System.out.println(compte.getSolde());
      //chartData.add(new ChartItem("Solde", compte.getSolde()));
    }
    /*
    chartData.add(new ChartItem("8s", listComptes.get(1).getSolde()));
    chartData.add(new ChartItem("8a", listComptes.get(2).getSolde()));
    chartData.add(new ChartItem("8s", listComptes.get(3).getSolde()));
    chartData.add(new ChartItem("8s", listComptes.get(4).getSolde()));
    */

    return chartData;
  }


  public List<Compte> getAllComptes() {
    return compteRepo.findAll();
}

@Autowired
    private ClientRepository clientRepo;

   
public Map<String, Number> getPieChartData() {
  Map<String, Number> data = new HashMap<>();

  List<Client> clients = clientRepo.findAll();
  List<Compte> comptes = clients.stream()
          .flatMap(client -> client.getComptes().stream())
          .collect(Collectors.toList());

  double totalBalance = comptes.stream()
          .mapToDouble(Compte::getSolde)
          .sum();

  for (Compte compte : comptes) {
      String category = "Category " + compte.getCodeCompte();
      double percentage = (compte.getSolde() / totalBalance) * 100;
      data.put(category, percentage);
  }

  return data;
}




}