package pl.printo3d.waluty.model;

import javax.persistence.GeneratedValue;

public class cutListModel {

  @GeneratedValue
  Long id;
  String cutLenght;
  String cutPcs;
  String name;


  public cutListModel(String cutLenght, String cutPcs) {
    this.cutLenght = cutLenght;
    this.cutPcs = cutPcs;
  }

  public cutListModel(Long id, String cutLenght, String cutPcs, String name) {
    this.id = id;
    this.cutLenght = cutLenght;
    this.cutPcs = cutPcs;
    this.name = name;
  }


  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getCutLenght() {
    return cutLenght;
  }
  public void setCutLenght(String cutLenght) {
    this.cutLenght = cutLenght;
  }
  public String getCutPcs() {
    return cutPcs;
  }
  public void setCutPcs(String cutPcs) {
    this.cutPcs = cutPcs;
  }
  
}
