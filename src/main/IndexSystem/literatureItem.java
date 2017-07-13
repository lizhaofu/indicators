package IndexSystem;

/**
 * Created by linheng on 2016/4/13.
 */
//单个文献的信息
public class literatureItem {
    public int number;
    public int year;
    public int kindcode;
    public int paCitedAmount;
    public int lCitedAmount;
    public int Partition;
    public String country;
    public String patentee;
    public String pn;
    public String citaion;
    public String ipc;
    public String AU;

    public literatureItem() {
        this.number=0;
        this.year = 0;
        this.country = "";
        this.patentee = "";
        this.pn = "";
        this.citaion = "";
        this.kindcode = 0;
        this.ipc = "";
        this.AU="";
        this.paCitedAmount=0;
        this.lCitedAmount=0;
        this.Partition=0;

    }
}
