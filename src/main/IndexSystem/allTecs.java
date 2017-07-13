package IndexSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by linheng on 2016/4/14.
 */
public class allTecs {
    public List<literatureItem> itemList;
    public double startYear;
    public double stopYear;

    public allTecs() {
        this.itemList = new ArrayList<literatureItem>();
        this.startYear = 1985;
        this.stopYear = 2016;
    }

    public void SetStartYear(double startYear) {
        this.startYear = startYear;
    }

    public void SetStopYear(double stopYear) {
        this.stopYear = stopYear + 1;
    }

    public Map<String, Double> ComFre() {
        Map<String, Double> comMap = new HashMap<String, Double>();
        for (literatureItem li : this.itemList) {
            String[] pas = li.patentee.split(" ");
            for (String s : pas) {
                if (!comMap.containsKey(s)) comMap.put(s,1.0);
                else comMap.put(s,comMap.get(s)+1);
            }
        }
        comMap.put("Total",this.Amount());
        return comMap;
    }

    public double Amount() {
        return this.itemList.size();
    }

    public double Amount(int year) {
        double fre = 0;
        for (literatureItem li : this.itemList) {
            if (li.year == year) fre += 1;
        }
        return fre;
    }

    public Map<Double, Double> FrequencyPerYear() {
        Map<Double, Double> timeMap = new HashMap<Double, Double>();
        for (double i = this.startYear; i < stopYear; ++i) {
            double fre = 0;
            for (literatureItem li : this.itemList) {
                if (i == li.year) fre += 1;
            }
            timeMap.put(i, fre);
        }
        return timeMap;
    }

    public double CitedAmount() {
        if (this.itemList.size() == 0) return 0;
        double fre = 0;
        for (literatureItem li : this.itemList) {
            if (li.paCitedAmount > 0) fre += 1;
        }
        return fre;
    }

    public double CitedAmount(int year) {
        if (this.itemList.size() == 0) return 0;
        double fre = 0;
        for (literatureItem li : this.itemList) {
            if (li.paCitedAmount > 0 && li.year == year) fre += 1;
        }
        return fre;
    }

    public double CII(List<String> citedList, int year) {
        if (this.itemList.size() == 0 || this.itemList.get(0).pn.equals("")) return 0;
        double fiveFre = 0;
        for (literatureItem li : this.itemList) {
            if (li.year < year && li.year > (year - 5)) {
                String[] citedL = li.citaion.split(" | ");
                for (String s : citedL) {
                    if (citedList.contains(s)) fiveFre += 1;
                }
            }
        }
        return fiveFre * this.CitedAmount(year) / this.Amount(year);
    }
}
