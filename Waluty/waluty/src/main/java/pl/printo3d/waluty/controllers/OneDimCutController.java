package pl.printo3d.waluty.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OneDimCutController {

  @RequestMapping(value="/1dcut", method={RequestMethod.GET})
  public String OneDimCutControllerShow()
  {

    return "1dcut";
  }

  @RequestMapping(value="/1dcut", method={RequestMethod.POST})
  public String OneDimCutControllerRun(
    @RequestParam(value = "stockcount", required = true) String stockCount,
    @RequestParam(value = "stocklen", required = true) String stockLength,
    @RequestParam(value = "pcscount", required = true) String pcsCount,
    @RequestParam(value = "pclength", required = true) String pcLength  )
  {


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