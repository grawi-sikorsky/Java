package pl.printo3d.test3;

public class UserModel {
  String id;
  String uname;
  
  public UserModel(String id, String uname) {
    this.id = id;
    this.uname = uname;
  }


  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getUname() {
    return uname;
  }
  public void setUname(String uname) {
    this.uname = uname;
  }
}
