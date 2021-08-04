package pl.printo3d.waluty.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pl.printo3d.waluty.services.OneDCutterService;

@Controller
public class OneDimCutController {

  private OneDCutterService oneDCutterService = new OneDCutterService();

  @RequestMapping(value="/1dcut", method={RequestMethod.GET})
  public String OneDimCutControllerShow()
  {
    return "1dcut";
  }

  @RequestMapping(value="/1dcut", method={RequestMethod.POST})
  public String ReqStockRun(
    @RequestParam(value = "stockcount", required = false) String stockCount,
    @RequestParam(value = "stocklen", required = false) List<String> stockLength,
    @RequestParam(value = "pcscount", required = false) List<String> pcsCount,
    @RequestParam(value = "pclength", required = false) String pcLength, Model mdl  )
  {
    System.out.println(pcsCount);
    System.out.println(stockLength);

    oneDCutterService.makePartListFromInputs(pcsCount, stockLength);



    // liczymy
    mdl.addAttribute("stockLen", oneDCutterService.getStockLen());
    mdl.addAttribute("stockPcs", oneDCutterService.getStockPcs());
    mdl.addAttribute("cuts", oneDCutterService.getCuts());

    oneDCutterService.SortReverse();
    //oneDCutterService.firstFit(parts, stockPcs, stockLen);
    oneDCutterService.firstFit();

    mdl.addAttribute("wyniki", oneDCutterService.getResults());

    return "1dcut";
  }

  public Integer nextFit(String stockCount, String stockLength, String pcsCount, String pcLength)
  {
    Integer wynik=0;
    //Integer stockCount=0;

    // int ucina co po przecinku wiec dodajemy 1 i jest ideoloooo
    wynik = ((Integer.valueOf(pcsCount) * Integer.valueOf(pcLength)) / Integer.valueOf(stockLength))+1;

    System.out.println(wynik);

    return wynik;
  }
}


/*
public static void binPacking(int[] a, int size, int n)
{
  int binCount = 0;
  int[] binValues = new int[n];
  for (int i = 0; i < binValues.length; i++)
    binValues[i] = size;

  for (int i = 0; i < n; i++)
    for (int j = 0; j < binValues.length; j++)
    {
      if (binValues[j] - a[i] >= 0)
      {
        binValues[j] -= a[i];
        break;
      }
    }

  for (int i = 0; i < binValues.length; i++)
    if (binValues[i] != size)
      binCount++;

  System.out
      .println("Number of bins required using first fit decreasing algorithm is:"
          + binCount);
}

static int[] sort(int[] sequence)
{
  // Bubble Sort descending order
  for (int i = 0; i < sequence.length; i++)
    for (int j = 0; j < sequence.length - 1; j++)
      if (sequence[j] < sequence[j + 1])
      {
        sequence[j] = sequence[j] + sequence[j + 1];
        sequence[j + 1] = sequence[j] - sequence[j + 1];
        sequence[j] = sequence[j] - sequence[j + 1];
      }
  return sequence;
}
*/