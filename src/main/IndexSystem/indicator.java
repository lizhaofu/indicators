package IndexSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by linheng on 2016/4/13.
 */


//一个技术点对应的所有文献统计
public class indicator {
    public String tec;
    public List<literatureItem> itemList;
    public double startYear;
    public double stopYear;

    public indicator() {
        this.tec = "";
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

    //通用
    //总的文献数量
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

    //文献每年的数量
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

    //文献每年的累积数量
    public Map<Double, Double> CumulativeQuantity() {
        Map<Double, Double> timeMap = new HashMap<Double, Double>();
        double fre = 0;
        for (double i = this.startYear; i < stopYear; ++i) {
            for (literatureItem li : this.itemList) {
                if (i == li.year) fre += 1;
            }
            timeMap.put(i, fre);
        }
        return timeMap;
    }

    //每年增长率
    public Map<Double, Double> DevPerYear() {
        Map<Double, Double> timeMap = new HashMap<Double, Double>();
        double lastYearFre = 0;
        for (double i = this.startYear; i < stopYear; ++i) {
            double fre = 0;
            for (literatureItem li : this.itemList) {
                if (i == li.year) fre += 1;
            }
            if (lastYearFre == 0) {
                timeMap.put(i, 0.0);
                lastYearFre = fre;
            } else {
                timeMap.put(i, fre);
                lastYearFre = fre;
            }
        }
        return timeMap;
    }

    public double DevPerYear(int year) {
        double lastYearFre = 0;
        double fre = 0;
        for (literatureItem li : this.itemList) {
            if (year == li.year) fre += 1;
            if ((year - 1) == li.year) lastYearFre += 1;

        }
        if (lastYearFre == 0) {
            return 0;
        } else {
            return fre / lastYearFre;
        }
    }

    public double Burst() {
        double burst = 0;
        double lastThreeYear = 0;
        double lastBurst = 0;
        for (double i = this.startYear + 2; i < stopYear; ++i) {
            double fre = 0;
            for (literatureItem li : this.itemList) {
                if ((i == li.year) || (i == (li.year - 1)) || (i == (li.year - 2))) fre += 1;
            }
            if (lastThreeYear == 0) {
                lastBurst = 0;
                lastThreeYear=fre;
            }
            else {
                lastBurst = fre / lastThreeYear;
                lastThreeYear=fre;
            }
            if (lastBurst > burst) burst = lastBurst;
        }
        return burst;
    }

    //专利指标
    //前向引文量
    public double CitedAmount() {
        if (this.itemList.size() == 0) return 0;
        double fre = 0;
        for (literatureItem li : this.itemList) {
            fre += li.paCitedAmount;
        }
        return fre;
    }

    //科学关联度
    public double ScienceAmount() {
        double fre = 0;
        for (literatureItem li : this.itemList) {
            fre += li.lCitedAmount;
        }
        return fre / this.Amount();
    }

    //美国专利每年的数量
    public Map<Double, Double> USFrequencyPerYear() {
        Map<Double, Double> timeMap = new HashMap<Double, Double>();
        for (double i = this.startYear; i < stopYear; ++i) {
            double fre = 0;
            for (literatureItem li : this.itemList) {
                if (i == li.year && li.country.equals("US")) fre += 1;
            }
            timeMap.put(i, fre);
        }
        return timeMap;
    }

    public double USFrequencyPerYear(int Year) {
        double fre = 0;
        for (literatureItem li : this.itemList) {
            if (li.country.contains("US")) fre += 1;
        }
        return fre;
    }

    //优先权国家为世界的专利每年的数量
    public Map<Double, Double> WOFrequencyPerYear() {
        Map<Double, Double> timeMap = new HashMap<Double, Double>();
        for (double i = this.startYear; i < stopYear; ++i) {
            double fre = 0;
            for (literatureItem li : this.itemList) {
                if (i == li.year && li.country.equals("WO")) fre += 1;
            }
            timeMap.put(i, fre);
        }
        return timeMap;
    }

    public double WOFrequencyPerYear(int Year) {
        double fre = 0;
        for (literatureItem li : this.itemList) {
            if (li.country.equals("WO")) fre += 1;
        }
        return fre;
    }

    //发明专利每年的数量
    public Map<Double, Double> InventFrequencyPerYear() {
        Map<Double, Double> timeMap = new HashMap<Double, Double>();
        for (double i = this.startYear; i < stopYear; ++i) {
            double fre = 0;
            for (literatureItem li : this.itemList) {
                if (i == li.year && li.kindcode == 1) fre += 1;
            }
            timeMap.put(i, fre);
        }
        return timeMap;
    }

    public double InventFrequencyPerYear(int Year) {
        double fre = 0;
        for (literatureItem li : this.itemList) {
            if (Year == li.year && li.kindcode == 1) fre += 1;
        }
        return fre;
    }

    //实用新型专利每年的数量
    public Map<Double, Double> PracFrequencyPerYear() {
        Map<Double, Double> timeMap = new HashMap<Double, Double>();
        for (double i = this.startYear; i < stopYear; ++i) {
            double fre = 0;
            for (literatureItem li : this.itemList) {
                if (i == li.year && li.kindcode == 2) fre += 1;
            }
            timeMap.put(i, fre);
        }
        return timeMap;
    }

    public double PracFrequencyPerYear(int Year) {
        double fre = 0;
        for (literatureItem li : this.itemList) {
            if (Year == li.year && li.kindcode == 2) fre += 1;
        }
        return fre;
    }

    //发明型或实用新型专利每年增长率，参数是kindcode
    public Map<Double, Double> PatentDevPerYear(double kindcode) {
        Map<Double, Double> timeMap = new HashMap<Double, Double>();
        double lastYearFre = 0;
        for (double i = this.startYear; i < stopYear; ++i) {
            double fre = 0;
            for (literatureItem li : this.itemList) {
                if (i == li.year && li.kindcode == kindcode) fre += 1;
            }
            if (lastYearFre == 0) {
                timeMap.put(i, 0.0);
                lastYearFre = fre;
            } else {
                timeMap.put(i, fre);
                lastYearFre = fre;
            }
        }
        return timeMap;
    }

    //每年的公司数
    public Map<Double, Double> ComPerYear() {
        Map<Double, Double> timeMap = new HashMap<Double, Double>();
        for (double i = this.startYear; i < stopYear; ++i) {
            List<String> comList = new ArrayList<String>();
            for (literatureItem li : this.itemList) {
                if (i == li.year) {
                    String pat = li.patentee;
                    String[] pas = pat.split(" | ");
                    for (String s : pas) {
                        if (!comList.contains(s)) {
                            comList.add(s);
                        }
                    }
                }
            }
            timeMap.put(i, (double) comList.size());
        }
        return timeMap;
    }

    //公司频次
    public Map<String, Double> ComFre() {
        Map<String, Double> comMap = new HashMap<String, Double>();
        for (literatureItem li : this.itemList) {
            String[] pas = li.patentee.split(" ");
            if (!comMap.containsKey(pas[0])) comMap.put(pas[0], 1.0);
            else comMap.put(pas[0], comMap.get(pas[0]) + 1);
        }
        comMap.put("Total",this.Amount());
        return comMap;
    }

    //技术成熟度，包含IPC分类号的数量
    public double CentreDegree() {
        List<String> ls = new ArrayList<String>();
        for (literatureItem li : this.itemList) {
            String[] ipcs = li.ipc.split(", ");
            for (String s : ipcs) {
                if (!ls.contains(s)) ls.add(s);
            }
        }
        return ls.size();
    }

    public double CentreDegree(int Year) {
        List<String> ls = new ArrayList<String>();
        for (literatureItem li : this.itemList) {
            if (li.year == Year) {
                String[] ipcs = li.ipc.split(", ");
                for (String s : ipcs) {
                    if (!ls.contains(s)) ls.add(s);
                }
            }
        }
        return ls.size();
    }

    //生长率V=a/A，其中a表示当年发明专利申请量，而A为追溯5年的发明专利申请累积数
    public Map<Double, Double> Grow() {
        Map<Double, Double> timeMap = new HashMap<Double, Double>();
        for (double i = this.startYear; i < this.stopYear; ++i) {
            double fre = 0;
            double fiveFre = 0;
            for (literatureItem li : this.itemList) {
                if (i == li.year && li.kindcode == 1) fre += 1;
            }
            for (double k = this.startYear - 1; k < this.stopYear; ++k) {
                for (literatureItem li : this.itemList) {
                    if (k == li.year && li.kindcode == 1) fiveFre += 1;
                }
            }
            if (fiveFre == 0) timeMap.put(i, 0.0);
            else timeMap.put(i, fre / fiveFre);
        }
        return timeMap;
    }

    public double Grow(int Year) {
        Map<Double, Double> timeMap = this.Grow();
        double lastYearValue = timeMap.get((double) (Year - 5));
        double total = 0.0;
        for (double i = Year - 4; i < Year + 1; i++) {
            total += (timeMap.get(i) - lastYearValue);
            lastYearValue = timeMap.get(i);
        }
        return total / 5;
    }

    //发明专利率发明专利率=企业发明专利量/企业所有专利数量
    public double InventRate() {
        double rate = 0.0;
        for (literatureItem li : this.itemList) {
            if (li.kindcode == 1) rate += 1;
        }
        return rate / this.Amount();
    }

    //技术成熟系数α=a/(a+b),a为当年发明专利申请量；b为当年实用新型专利申请量
    public Map<Double, Double> Mature() {
        Map<Double, Double> timeMap = new HashMap<Double, Double>();
        for (double i = this.startYear; i < this.stopYear; ++i) {
            double fre = 0;
            double pracFre = 0;
            for (literatureItem li : this.itemList) {
                if (i == li.year && li.kindcode == 1) fre += 1;
                else if (i == li.year && li.kindcode == 2) pracFre += 1;
            }
            if (pracFre == 0) timeMap.put(i, 0.0);
            else timeMap.put(i, fre / pracFre);
        }
        return timeMap;
    }

    public double Mature(int Year) {
        Map<Double, Double> timeMap = this.Mature();
        double lastYearValue = timeMap.get((double) (Year - 5));
        double total = 0.0;
        for (double i = Year - 4; i < Year + 1; i++) {
            total += (lastYearValue - timeMap.get(i));
            lastYearValue = timeMap.get(i);
        }
        return total / 5;
    }

    //技术衰老系数β=(a+b)/(a+b+c),a为当年发明专利申请量；b为当年好似用新型专利申请量，c为当年外观设计或商标申请数
    public Map<Double, Double> Old() {
        Map<Double, Double> timeMap = new HashMap<Double, Double>();
        for (double i = this.startYear; i < this.stopYear; ++i) {
            double fre = 0;
            double pracFre = 0;
            double outLookFre = 0;
            for (literatureItem li : this.itemList) {
                if (i == li.year && li.kindcode == 1) fre += 1;
                else if (i == li.year && li.kindcode == 2) pracFre += 1;
                else if (i == li.year && li.kindcode == 3) outLookFre += 1;
            }
            if (pracFre + fre + outLookFre == 0) timeMap.put(i, 0.0);
            else timeMap.put(i, (double) (fre + pracFre) / (fre + pracFre + outLookFre));
        }
        return timeMap;
    }

    public double Old(int Year) {
        Map<Double, Double> timeMap = this.Old();
        if (!timeMap.containsKey(Year-5)) return 0;
        double lastYearValue = timeMap.get(Year - 5);
        double total = 0.0;
        for (int i = Year - 4; i < Year + 1; i++) {
            total += (timeMap.get(i) - lastYearValue);
            lastYearValue = timeMap.get(i);
        }
        return total / 5;
    }

    //被引用的专利比率
    public double CitedRate() {
        double fre = 0.0;
        for (literatureItem li : itemList) {
            if (li.paCitedAmount == 0) continue;
            fre += 1;
        }
        return fre / this.Amount();
    }

    //当前影响指数（CII），该企业现行年前5年期间的专利平均每件被现行年专利引用的比率/所有专利被引用的比率
    public List<String> CII(int year) {
        List<String> citedList = new ArrayList<String>();
        for (literatureItem li : this.itemList) {
            if (li.year < year && li.year > (year - 5)) citedList.add(li.pn);
        }
        return citedList;
    }

    //技术强度TS，TS=当年专利数*当前影响指数


    //技术影响力指标（TII）,(某年专利位居被引用次数前10%的最具影响力专利件数/当年专利量)/(所有专利位居最具影响力的专利件数/企业所有专利量)
    public double TII(double currentYearTop10, double currentYearAmount, double totalTop10) {
        if ((totalTop10 * currentYearAmount) == 0) {
            return 0;

        }
        double tii = (currentYearTop10 / currentYearAmount) / (totalTop10 / this.Amount());
        return tii;
    }

    //技术独立性，技术点专利自引次数/技术点专利总被引次数
    public double Independence() {
        double fre = 0;
        for (int i = 0; i < this.itemList.size(); ++i) {
            for (int j = i + 1; j < this.itemList.size(); ++j) {
                if (itemList.get(i).citaion.contains(itemList.get(j).pn))
                    fre += 1;
            }
        }
        return fre * this.CitedRate() / this.Amount();
    }


    //论文指标
    //每年的作者数
    public Map<Double, Double> AUPerYear() {
        Map<Double, Double> timeMap = new HashMap<Double, Double>();
        for (double i = this.startYear; i < stopYear; ++i) {
            List<String> comList = new ArrayList<String>();
            for (literatureItem li : this.itemList) {
                if (i == li.year) {
                    String pat = li.patentee;
                    String[] pas = pat.split(",");
                    for (String s : pas) {
                        if (!comList.contains(s)) {
                            comList.add(s);
                        }
                    }
                }
            }
            timeMap.put(i, (double) comList.size());
        }
        return timeMap;
    }

    //篇均引用量
    public double PartialCitation() {
        double count = 0;
        double pcount = 0;
        for (literatureItem li : this.itemList) {
            if (li.lCitedAmount > 1) {
                count += 1;
            }
            pcount += li.lCitedAmount;
        }
        if (count == 0) return 0;
        return pcount / count;
    }

}
