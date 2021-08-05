package pl.printo3d.waluty.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pl.printo3d.waluty.model.cutListModel;
import pl.printo3d.waluty.services.OneDCutterService;

@Controller
public class OneDimCutController {

  private OneDCutterService oneDCutterService = new OneDCutterService();

  @RequestMapping(value="/1dcut", method={RequestMethod.GET})
  public String OneDimCutControllerShow(Model mdl)
  {
    mdl.addAttribute("pclength", oneDCutterService.pcsLength);
    mdl.addAttribute("pcscount", oneDCutterService.pcs);
    mdl.addAttribute("cuttlistmap", oneDCutterService.cutList);
    return "1dcut";
  }

  @RequestMapping(value="/1dcut", method={RequestMethod.POST})
  public String ReqStockRun(
    @RequestParam(value = "stockcount", required = false)   String stockCount,
    @RequestParam(value = "stocklength", required = false)  List<String> stockLength,
    @RequestParam(value = "pcscount", required = false)     List<String> pcsCount,
    @RequestParam(value = "pclength", required = false)     List<String> pcLength, Model mdl  )
  {
    System.out.println(pcsCount);
    System.out.println(pcLength);
    System.out.println(stockCount);
    System.out.println(stockLength);
    System.out.println(oneDCutterService.calculateWaste());

    oneDCutterService.cutList.clear();
    for (int i=0; i < pcLength.size(); ++i) 
    {
      oneDCutterService.cutList.add(new cutListModel(pcLength.get(i), pcsCount.get(i)));
    }
    mdl.addAttribute("cuttlistmap", oneDCutterService.cutList);


    oneDCutterService.makePartListFromInputs(pcsCount, pcLength);
    oneDCutterService.SortReverse();
    oneDCutterService.firstFit();

    mdl.addAttribute("wyniki", oneDCutterService.getResults());
    mdl.addAttribute("pclength", pcLength);
    mdl.addAttribute("pcscount", pcsCount);

    // liczymy
    mdl.addAttribute("stockLen", oneDCutterService.getStockLen());
    mdl.addAttribute("stockPcs", oneDCutterService.getStockPcs());
    mdl.addAttribute("cuts", oneDCutterService.getCuts());
    mdl.addAttribute("stockneed", oneDCutterService.calculateNeededStock(oneDCutterService.partsList, oneDCutterService.stockLen));
    mdl.addAttribute("wasteproc", oneDCutterService.calculateWaste());
    mdl.addAttribute("usedproc", 100 - oneDCutterService.calculateWaste());


    return "1dcut";
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