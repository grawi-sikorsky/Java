package pl.printo3d.waluty.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import pl.printo3d.waluty.model.ResultModel;
import pl.printo3d.waluty.model.StockPiece;
import pl.printo3d.waluty.model.cutListModel;

public class OneDCutterService {
  
  // lista roboczych kawalkow - kazdy zawiera info o cieciach oraz o ilosci wolnego miejsca na nim
  public List<StockPiece> workPieces = new ArrayList<StockPiece>();

  // resulty
  public List<ResultModel> results = new ArrayList<ResultModel>();

  // Multimapa zawierajaca klucze (dlugosci) i wartosci (ilosc)
  public List<cutListModel> cutList = new ArrayList<cutListModel>();
  
  // zmjenne
  public List<String> stockLen = new ArrayList<String>(Arrays.asList("1000"));
  public List<String> stockPcs = new ArrayList<String>(Arrays.asList("10"));
  public List<String> pcsLength = new ArrayList<String>(Arrays.asList("100","200"));
  public List<String> pcs = new ArrayList<String>(Arrays.asList("5","2"));
  public List<Double> partsList = new ArrayList<Double>(Arrays.asList(
    320.0,350.0,370.0,320.0,350.0,370.0,320.0,350.0,370.0,320.0,350.0,370.0,
    320.0,350.0,370.0,320.0,350.0,370.0,260.0, 310.0));

  public OneDCutterService()
  {
    cutList.add(new cutListModel("200", "5"));
  }
  //public List<String> wyniki = new ArrayList<String>();

  // Sortowanie odwrotne
  public List<Double> SortReverse()
  {
    Collections.sort(partsList);
    Collections.reverse(partsList);

    return partsList;
  }

  // 1. Pierwsza metoda rozwiazania problemu 
  public List<StockPiece> firstFit(/* List<String> parts, List<String> stockPcs, List<String> stockLen */)
  {
    // flush workpieces:
    workPieces.clear();
    
    for (Double part : partsList)
    {
      System.out.println("Next part is: " + part);
      if(!workPieces.stream().anyMatch(work->work.freeSpace() >= part))
      {
        workPieces.add(new StockPiece(1000.0));
        System.out.println("No free left, adding new stock piece 1000.0");
      }

      for(var work : workPieces)
      {
        if(work.freeSpace() >= part)
        {
          work.cut(part);
          System.out.println("Cutting nju pis: " + part);
          break; // koniecznie wyskoczyc z loopa!
        }
      }
    }
    return workPieces;
  }

  // 2. Druga metoda rozwiazania problemu
  public List<StockPiece> bestFit()
  {
    workPieces.clear();

    return workPieces;
  }
  // Wypisuje rezultat obliczen
  public List<String> getResults()
  {
    results.add(new ResultModel());
    return results.get(0).getResults(workPieces);
  }

  // Tworzy liste elementow do ciecia na podstawie wpisanych danych
  public List<Double> makePartListFromInputs( List<String> pcsCnt, List<String> pcLen )
  {
    partsList.clear();

    for(int i=0; i < pcsCnt.stream().count(); ++i)
    {
      for (int j=0; j < Integer.parseInt(pcsCnt.get(i)); ++j)
      {
        partsList.add(Double.parseDouble(pcLen.get(i)));
      }
    }
    return partsList;
  }

  // Oblicza ilosc potrzebnych elementow do wykonania zadania
  public Integer calculateNeededStock(List<Double> partsList, List<String> stockLength)
  {
    Integer stockNeed=0;

    // int ucina co po przecinku wiec dodajemy 1 i jest ideoloooo
    for (var part : partsList) 
    {
      stockNeed += (Integer)part.intValue();
    }
    stockNeed = (stockNeed / Integer.valueOf(stockLength.get(0)) +1 );
    //stockNeed = ((Integer.valueOf(pcsCount) * Integer.valueOf(pcLength)) / Integer.valueOf(stockLength))+1;

    System.out.println(stockNeed);

    return stockNeed;
  }

  public Double calculateWaste()
  {
    Double waste=0.0, used=0.0;
    Double procWaste=0.0;

    for (var workpc : workPieces) 
    {
      used += workpc.getStockLenght();
      waste += workpc.freeSpace();
    }
    procWaste = (waste / used) * 100.0;

    return procWaste;
  }

  
  public List<String> getStockLen() {
    return stockLen;
  }

  public void setStockLen(List<String> stockLen) {
    this.stockLen = stockLen;
  }

  public List<String> getStockPcs() {
    return stockPcs;
  }

  public void setStockPcs(List<String> stockPcs) {
    this.stockPcs = stockPcs;
  }

  public List<Double> getCuts() {
    return partsList;
  }

  public void setCuts(List<Double> partsList) {
    this.partsList = partsList;
  }
  
}
