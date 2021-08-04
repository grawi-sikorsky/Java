package pl.printo3d.waluty.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import pl.printo3d.waluty.model.StockPiece;

public class OneDCutterService {
  
  // lista roboczych kawalkow - kazdy zawiera info o cieciach oraz o ilosci wolnego miejsca na nim
  public List<StockPiece> workPieces = new ArrayList<StockPiece>();

  public List<String> stockLen = new ArrayList<String>(Arrays.asList("1000"));
  public List<String> stockPcs = new ArrayList<String>(Arrays.asList("10"));
  public List<String> pcsLength = new ArrayList<String>(Arrays.asList("100","200"));
  public List<String> pcs = new ArrayList<String>(Arrays.asList("5","2"));
  public List<Double> partsList = new ArrayList<Double>(Arrays.asList(
    320.0,350.0,370.0,320.0,350.0,370.0,320.0,350.0,370.0,320.0,350.0,370.0,
    320.0,350.0,370.0,320.0,350.0,370.0,260.0, 310.0));

  public List<String> wyniki = new ArrayList<String>();

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
    List<String> result = new ArrayList<String>();

    for (var work : workPieces) 
    {
      StringBuilder temp = new StringBuilder();
      temp.append("Dlugość 1000: |  ");

      for(int i=0; i<work.cuts.size(); ++i)
      {
        temp.append(work.cuts.get(i).toString() + "  |  ");
      }

      temp.append(" - odpad: " + work.freeSpace());
      
      result.add(temp.toString());
    }

    return result;
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
  public Integer calculateNeededStock(String stockCount, String stockLength, String pcsCount, String pcLength)
  {
    Integer wynik=0;

    // int ucina co po przecinku wiec dodajemy 1 i jest ideoloooo
    wynik = ((Integer.valueOf(pcsCount) * Integer.valueOf(pcLength)) / Integer.valueOf(stockLength))+1;

    System.out.println(wynik);

    return wynik;
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
