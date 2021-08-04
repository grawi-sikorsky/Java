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
  public List<Double> partsList = new ArrayList<Double>(Arrays.asList(
    320.0,350.0,370.0,320.0,350.0,370.0,320.0,350.0,370.0,320.0,350.0,370.0,
    320.0,350.0,370.0,320.0,350.0,370.0,260.0, 310.0));

  public List<String> wyniki = new ArrayList<String>();

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

  public List<Double> SortReverse()
  {
    Collections.sort(partsList);
    Collections.reverse(partsList);

    return partsList;
  }

  public List<StockPiece> firstFit(/* List<String> parts, List<String> stockPcs, List<String> stockLen */)
  {
    // flush workpieces:
    workPieces.clear();

    Integer stockCount;
    Double stockLen=1000.0;
    Double stockPcs=100.0;
    
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

  public List<StockPiece> bestFit()
  {
    workPieces.clear();

    

    return workPieces;
  }

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
  
}
