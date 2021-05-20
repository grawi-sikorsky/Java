package pl.printo3d.waluty.model;

import java.util.List;

// RATES MODEL Z OPENEXCHANGERATES.ORG
public class RatesModel
{
  String disclaimer;
  String license;
  String timestamp;
  String base;
  List<String> rates;


  public String getDisclaimer() {
    return disclaimer;
  }
  public void setDisclaimer(String disclaimer) {
    this.disclaimer = disclaimer;
  }
  public String getLicense() {
    return license;
  }
  public void setLicense(String license) {
    this.license = license;
  }
  public String getTimestamp() {
    return timestamp;
  }
  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }
  public String getBase() {
    return base;
  }
  public void setBase(String base) {
    this.base = base;
  }
  public List<String> getRates() {
    return rates;
  }
  public void setRates(List<String> rates) {
    this.rates = rates;
  }

  
}