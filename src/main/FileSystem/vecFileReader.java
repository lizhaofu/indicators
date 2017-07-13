package FileSystem;

import IndexSystem.*;
import jxl.*;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import mllib.*;

import java.io.*;
import java.util.*;

/**
 * Created by linheng on 2016/4/13.
 */
public class vecFileReader {

    private static String ThesaurusFilePath = "E:\\Result\\Robot\\清单.xls";
    private static allTecs all = new allTecs();
    private static Map<String, Double> keywordsFre = new HashMap<>();

    public static void main(String[] args) throws IOException {
        tp();
//        AboutOne("E:\\BigPAIndicator.xls","E:\\指标体系1.xls");
    }
    private static void tp () throws IOException{
        String DataPath = "E:\\Platform Program\\Python\\AllData\\Result\\whole\\a";
        List<String> thesaurus=Thesaurus1(0,2);
        List<indicator> li=KeywordsRead(DataPath,thesaurus);
//        DataPath = "E:\\Data\\Robot\\Vectors\\tpL";
//        List<indicator> li=ReadFromTxt(DataPath);
        List<indicator> teclist=getOne(li,Thesaurus2(1));
//        List<indicator> teclist=li;
        for (indicator i:teclist){
            String filename="E:\\Data\\Robot\\Vectors\\tpL\\"+i.tec+".txt";
            WriteToTxt(i,filename);
        }
        Map<String,Double> msd=new HashMap<>();
        thesaurus=Thesaurus1(0,1);
        for (int i=0;i<thesaurus.size();++i){
            String s=thesaurus.get(i);
            for (indicator id:teclist){
                if (id.tec.equals(s)){
                    msd.put(s,id.Amount());
                }
            }
        }
        SDMapWrite(msd,"E:\\Lamount.txt");
        Map<String,List<String>> msls=new HashMap<>();
        List<indicator> Biglist=getOne(teclist,Thesaurus2(2));
        for (int i=0;i<thesaurus.size();++i){
            String s=thesaurus.get(i);
            List<String> lss=new ArrayList<>();
            for (indicator id:Biglist){
                if (id.tec.equals(s)){
                    for (literatureItem it:id.itemList){
                        lss.add(String.valueOf(it.number));
                    }
                }
            }
            msls.put(s,lss);
        }
        StringMapWrite(msls,"E:\\LTrain.txt");
        li.clear();
        teclist.clear();
        Biglist.clear();
        msd.clear();
        msls.clear();
        DataPath = "E:\\Platform Program\\Python\\AllData\\Result\\whole\\a";
        thesaurus=Thesaurus1(0,2);
        li=KeywordsRead(DataPath,thesaurus);
//        DataPath = "E:\\Data\\Robot\\Vectors\\tpPA";
//        li=ReadFromTxt(DataPath);
        teclist=getOne(li,Thesaurus2(1));
//        teclist=li;
//        for (indicator i:teclist){
//            String filename="E:\\Data\\Robot\\Vectors\\tpPA\\"+i.tec+".txt";
//            WriteToTxt(i,filename);
//        }
        msd=new HashMap<>();
        msls=new HashMap<>();
        thesaurus=Thesaurus1(0,1);
        for (int i=0;i<thesaurus.size();++i){
            String s=thesaurus.get(i);
            for (indicator id:teclist){
                if (id.tec.equals(s)){
                    msd.put(s,id.Amount());
                }
            }
        }
        SDMapWrite(msd,"E:\\PAamount.txt");
        Biglist=getOne(teclist,Thesaurus2(2));
        thesaurus=Thesaurus1(0,0);
        for (int i=0;i<thesaurus.size();++i){
            String s=thesaurus.get(i);
            List<String> lss=new ArrayList<>();
            for (indicator id:Biglist){
                if (id.tec.equals(s)){
                    for (literatureItem it:id.itemList){
                        lss.add(String.valueOf(it.number));
                    }
                }
            }
            msls.put(s,lss);
        }
        StringMapWrite(msls,"E:\\PATrain.txt");
    }
    private static void indicators() throws IOException{

        /**/
        //        AboutOne("E:\\指标体系.xls","E:\\指标体系1.xls");
//        List<String> thesaurus = Thesaurus1(0, 1);
        String DataPath = "E:\\Data\\Robot\\Classifyresult\\Patent\\patent_classify_result";

        DataPath="E:\\Data\\Robot\\Vectors\\newPA";
        List<indicator> tecList =ReadFromTxt(DataPath);
//        List<indicator> tecList = Read(DataPath);
//        for (indicator i:tecList){
//            String filename="E:\\Data\\Robot\\Vectors\\newPA\\"+i.tec+".txt";
//            WriteToTxt(i,filename);
////        }
//        for (int i=0;i<thesaurus.size();++i){
//            String s=thesaurus.get(i);
//            for (indicator id:tecList){
//                if (id.tec.equals(s)){
//                    String filename="E:\\Data\\FrePerYear\\Fre\\"+id.tec+".txt";
//                    MapWrite(id.FrequencyPerYear(),filename);
////                    filename="E:\\Data\\FrePerYear\\Grow\\"+id.tec+".txt";
////                    MapWrite(id.Grow(),filename);
////                    filename="E:\\Data\\FrePerYear\\Mature\\"+id.tec+".txt";
////                    MapWrite(id.Mature(),filename);
////                    filename="E:\\Data\\FrePerYear\\Old\\"+id.tec+".txt";
////                    MapWrite(id.Old(),filename);
////                    filename="E:\\Data\\FrePerYear\\Com\\"+id.tec+".txt";
////                    SDMapWrite(id.ComFre(),filename);
//                }
//            }
//        }
//        String filename="E:\\Data\\FrePerYear\\Fre\\TotalAmount.txt";
////        MapWrite(all.FrequencyPerYear(),filename);
//        filename="E:\\Data\\FrePerYear\\Fre\\TotalCom.txt";
////        SDMapWrite(all.ComFre(),filename);
        String OutFilePath = "E:\\PAIndicator.xls";
//        WritePAToExcel(tecList, OutFilePath);



        tecList=getOne(tecList,Thesaurus2(0));
        for (indicator i:tecList){
            String filename="E:\\Data\\FrePerYear\\Big\\"+i.tec+".txt";
            MapWrite(i.FrequencyPerYear(),filename);
        }
//        OutFilePath = "E:\\BigPAIndicator.xls";
//        WritePAToExcel(tecList, OutFilePath);
//        DataPath = "E:\\Data\\Robot\\Classifyresult\\SCI\\artical_classify_result\\whole";
//        tecList = Read(DataPath);
        DataPath = "E:\\Data\\Robot\\Classifyresult\\SCI\\artical_classify_result";
        ReadFourSeq(DataPath,Thesaurus2(0));
        DataPath="E:\\Data\\Robot\\Vectors\\L";
        tecList =ReadFromTxt(DataPath);
//        for (indicator i:tecList){
//            String filename="E:\\Data\\Robot\\Vectors\\L\\"+i.tec+".txt";
//            WriteToTxt(i,filename);
//        }
//        OutFilePath = "E:\\LiIndicator.xls";
//        WriteLIToExcel(tecList, OutFilePath);
//        tecList=getOne(tecList,Thesaurus2(0));
        OutFilePath = "E:\\BiglIndicator.xls";
//        WriteLIToExcel(tecList, OutFilePath);
    }


    private static void cluster() throws IOException{
        List<String> thesaurus = Thesaurus1(3, 1);
        Map<String, List<String>> thesaurus2 = Thesaurus2(3);
//        Map<String, Double> msd = TogetherRead(thesaurus, DataPath);
        String filename = "E:\\Data\\Robot\\Vectors\\together.txt";
//        SDMapWrite(msd, filename);
        Map<String, Double> msd2 = TogetherReadFromTxt(filename);
        msd2 = bigTogether(msd2, thesaurus2);
        filename = "E:\\Data\\Robot\\Vectors\\Thesaurus.txt";
        keywordsFre = TogetherReadFromTxt(filename);
        BigKeywords(thesaurus2);
        thesaurus2 = Thesaurus2(2);
        msd2 = bigTogether(msd2, thesaurus2);
        BigKeywords(thesaurus2);
//        SDMapWrite(keywordsFre, filename);
        thesaurus = Thesaurus1(2, 0);
        List<Item> li = TogetherCluster(msd2, thesaurus);
        LIMapWrite2(li,"E:\\1.txt");
        li = SaltonLength(li);
        LIMapWrite2(li,"E:\\2.txt");
        kmeans km = new kmeans(12);
        li = km.model(li);
        filename = "E:\\Result.txt";
        LIMapWrite(li, filename,12);
    }

    /*专利写入excel*/
    public static void WritePAToExcel(List<indicator> tecList, String OutFilePath) throws IOException {
        try {
            File fileWrite = new File(OutFilePath);
            if (!fileWrite.exists()) fileWrite.createNewFile();
            OutputStream os;
            os = new FileOutputStream(fileWrite);
            WritableWorkbook wwb = Workbook.createWorkbook(os);
            WritableSheet ws = wwb.createSheet("Indicator", 0);
            int year = 2013;
            ws.addCell(new Label(0, 0, "技术点"));
            ws.addCell(new Label(1, 0, "专利数量"));
            ws.addCell(new Label(2, 0, "集中度"));
            ws.addCell(new Label(3, 0, "生长率"));
            ws.addCell(new Label(4, 0, "成熟系数"));
            ws.addCell(new Label(5, 0, "衰老系数"));
            ws.addCell(new Label(6, 0, "美国授权量"));
            ws.addCell(new Label(7, 0, "PCT授权量"));
            ws.addCell(new Label(8, 0, "专利增长率"));
            ws.addCell(new Label(9, 0, "当前影响指数(CII)"));
            ws.addCell(new Label(10, 0, "技术力量(TS)"));
            ws.addCell(new Label(11, 0, "技术独立性"));
            ws.addCell(new Label(12, 0, "技术影响力指标(TII)"));
            ws.addCell(new Label(13, 0, "发明专利率"));
            ws.addCell(new Label(14, 0, "前向引文量"));
            ws.addCell(new Label(15, 0, "科学关联度"));
            ws.addCell(new Label(16, 0, "Burst"));
            int row = 0;
            for (indicator i : tecList) {
                row++;
                ws.addCell(new Label(0, row, i.tec));
                ws.addCell(new Number(1, row, i.Amount()));
                ws.addCell(new Number(2, row, i.CentreDegree()));
                ws.addCell(new Number(3, row, i.Grow(year)));
                ws.addCell(new Number(4, row, i.Mature(year)));
                ws.addCell(new Number(5, row, i.Old(year)));
                ws.addCell(new Number(6, row, i.USFrequencyPerYear(year)));
                ws.addCell(new Number(7, row, i.WOFrequencyPerYear(year)));
                ws.addCell(new Number(8, row, i.DevPerYear(year)));
                double cii = all.CII(i.CII(year), year);
                ws.addCell(new Number(9, row, cii));
                ws.addCell(new Number(10, row, cii * i.Amount()));
                ws.addCell(new Number(11, row, i.Independence()));
                double ca1 = all.CitedAmount(year);
                double a = all.Amount(year);
                double ca2 = all.CitedAmount();
                ws.addCell(new Number(12, row, i.TII(all.CitedAmount(year) * 0.1,
                        all.Amount(year),
                        all.CitedAmount() * 0.1)));
                ws.addCell(new Number(13, row, i.InventRate()));
                ws.addCell(new Number(14, row, i.CitedAmount()));
                ws.addCell(new Number(15, row, (i.ScienceAmount())));
                ws.addCell(new Number(16, row, (i.Burst())));
            }
            wwb.write();
            wwb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*论文写入excel*/
    public static void WriteLIToExcel(List<indicator> tecList, String OutFilePath) throws IOException {
        try {
            File fileWrite = new File(OutFilePath);
            if (!fileWrite.exists()) fileWrite.createNewFile();
            OutputStream os;
            os = new FileOutputStream(fileWrite);
            WritableWorkbook wwb = Workbook.createWorkbook(os);
            WritableSheet ws = wwb.createSheet("Indicator", 0);
            int year = 2015;
            ws.addCell(new Label(0, 0, "技术点"));
            ws.addCell(new Label(1, 0, "文献增长率"));
            ws.addCell(new Label(2, 0, "篇均引用量"));
            int row = 0;
            for (indicator i : tecList) {
                row++;
                ws.addCell(new Label(0, row, i.tec));
                ws.addCell(new Number(1, row, i.DevPerYear(year)));
                ws.addCell(new Number(2, row, i.PartialCitation()));
            }
            wwb.write();
            wwb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*//向量文件写入txt*/
    public static void WriteToTxt(indicator tec, String OutFileName) throws IOException {
        try {
            File fileWrite = new File(OutFileName);
            if (!fileWrite.exists()) fileWrite.createNewFile();
            FileOutputStream out = new FileOutputStream(OutFileName, true); //如果追加方式用true
            for (literatureItem i : tec.itemList) {
                StringBuffer sb = new StringBuffer(i.AU + "@@" + i.paCitedAmount + "@@"
                        + i.year + "@@" + i.pn + "@@" + i.lCitedAmount + "@@" + i.citaion + "@@" + i.country
                        + "@@" + i.ipc + "@@" + i.patentee + "@@" + i.kindcode + "\n");
                out.write(sb.toString().getBytes("utf-8"));//注意需要转换对应的字符集
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*//txt读取向量文件*/
    public static List<indicator> ReadFromTxt(String DataPath) throws IOException {
        try {
            List<indicator> tecList = new ArrayList<indicator>();
            File rf = new File(DataPath);
            if (!rf.exists() && !rf.isDirectory()) {
                System.out.println(rf.getName() + " not exists");
                return null;
            }
            File TecItems[] = rf.listFiles();
            for (File tecName : TecItems) {
                indicator aTec = new indicator();
                String tecNamePath = DataPath + "\\" + tecName.getName();
                aTec.tec = tecName.getName().substring(0,tecName.getName().length()-4);
                File file = new File(tecNamePath);
                if (file.isFile() && file.exists()) { //判断文件是否存在
                    InputStreamReader read = new InputStreamReader(
                            new FileInputStream(file), "utf-8");//考虑到编码格式
                    BufferedReader bufferedReader = new BufferedReader(read);
                    String lineTxt = null;
                    while ((lineTxt = bufferedReader.readLine()) != null) {
                        literatureItem lItem = new literatureItem();
                        String[] text = lineTxt.split("@@");
                        if (text.length == 0) continue;
                        lItem.AU = text[0];
                        lItem.paCitedAmount = Integer.valueOf(text[1]);
                        lItem.year = Integer.valueOf(text[2]);
                        lItem.pn = text[3];
                        lItem.lCitedAmount = Integer.valueOf(text[4]);
                        lItem.citaion = text[5];
                        lItem.country = text[6];
                        lItem.ipc = text[7];
                        lItem.patentee = text[8];
                        lItem.kindcode = Integer.valueOf(text[9]);
                        aTec.itemList.add(lItem);
                        all.itemList.add(lItem);
                    }
                }
                tecList.add(aTec);
            }
            return tecList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*//归一化*/
    public static void AboutOne(String InFilePath, String OutFilePath) {
        try {
            File fileWrite = new File(OutFilePath);
            if (!fileWrite.exists()) fileWrite.createNewFile();
            OutputStream os;
            os = new FileOutputStream(fileWrite);
            WritableWorkbook wwb = Workbook.createWorkbook(os);
            WritableSheet ws = wwb.createSheet("Indicator", 0);
            InputStream is = new FileInputStream(InFilePath);
            Workbook rwb = Workbook.getWorkbook(is);
            Sheet st = rwb.getSheet(1);
            int rowSize = st.getRows();
            int colSize = st.getColumns();
            for (int i = 0; i < colSize; ++i) {
                List<Double> li = new ArrayList<Double>();
                Map<Double, Double> mii = new HashMap<Double, Double>();
                Map<Double, Double> outmii = new HashMap<Double, Double>();
                for (int j = 0; j < rowSize ; ++j) {
                    if (st.getCell(i, j).getContents() == "") continue;
                    double cellContent = Double.valueOf(st.getCell(i, j).getContents());
                    li.add(cellContent);
                    mii.put((double) j, cellContent);
                }
                double max = Collections.max(li);
                double min = Collections.min(li);
                for (Map.Entry<Double, Double> m : mii.entrySet()) {
                    if ((max - min) == 0) outmii.put(m.getKey(), 0.0);
                    else outmii.put(m.getKey(), (m.getValue() - min) / (max - min));
                }
                for (Map.Entry<Double, Double> m2 : outmii.entrySet()) {
                    ws.addCell(new Number(i, m2.getKey().intValue(), m2.getValue()));
                }
            }
            wwb.write();
            wwb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /*//得到二层级结构的叙词表*/
    public static Map<String, List<String>> Thesaurus2(int sheeti) {
        try {
            Map<String, List<String>> ThesaurusMap = new HashMap<String, List<String>>();
            InputStream is = new FileInputStream(ThesaurusFilePath);
            Workbook rwb = Workbook.getWorkbook(is);
            Sheet st = rwb.getSheet(sheeti);
            int rowSize = st.getRows();
            String key = "";
            for (int i = 0; i < rowSize; ++i) {
                String keyword = st.getCell(0, i).getContents();
                String tec = st.getCell(1, i).getContents();
                if (keyword != "") {
                    key = keyword;
                    List<String> ls = new ArrayList<String>();
                    ls.add(tec);
                    ThesaurusMap.put(key, ls);
                } else {
                    List<String> ls = ThesaurusMap.get(key);
                    ls.add(tec);
                    ThesaurusMap.put(key, ls);
                }
            }
            return ThesaurusMap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*//得到一层级结构的叙词表*/
    public static List<String> Thesaurus1(int sheeti, int col) {
        try {
            List<String> thesaurus = new ArrayList<String>();
            InputStream is = new FileInputStream(ThesaurusFilePath);
            Workbook rwb = Workbook.getWorkbook(is);
            Sheet st = rwb.getSheet(sheeti);
            int rowSize = st.getRows();
            for (int i = 0; i < rowSize; ++i) {
                String s = st.getCell(col, i).getContents();
                if (!s.equals("") && !thesaurus.contains(s)) thesaurus.add(st.getCell(col, i).getContents());
            }
            return thesaurus;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /*/读向量化之后的文件*/
    public static List<indicator> Read(String DataPath) throws IOException {
        try {
            List<indicator> tecList = new ArrayList<indicator>();
            File rf = new File(DataPath);
            if (!rf.exists() && !rf.isDirectory()) {
                System.out.println(rf.getName() + " not exists");
                return null;
            }
            File TecItems[] = rf.listFiles();
            for (File tecName : TecItems) {
                indicator aTec = new indicator();
                String tecNamePath = DataPath + "\\" + tecName.getName();
                aTec.tec = tecName.getName();
                String encoding = "utf-8";
                File tf = new File(tecNamePath);
                File items[] = tf.listFiles();
                for (File item : items) {
                    literatureItem lItem = new literatureItem();
                    String itemPath = tecNamePath + "\\" + item.getName();
                    File file = new File(itemPath);
                    if (file.isFile() && file.exists()) { //判断文件是否存在
                        InputStreamReader read = new InputStreamReader(
                                new FileInputStream(file), encoding);//考虑到编码格式
                        BufferedReader bufferedReader = new BufferedReader(read);
                        StringBuffer sb = new StringBuffer();
                        String lineTxt = null;
                        boolean AUflag = false;
                        while ((lineTxt = bufferedReader.readLine()) != null) {
                            String[] fWord = lineTxt.split(" ");
                            if (fWord.length < 1) continue;
                            if (fWord[0].equals("pry"))
                                lItem.year = Integer.valueOf(lineTxt.substring(7, 11));
                            if (fWord[0].equals("pn")) {
                                lItem.pn = lineTxt.substring(6);
                            }
                            if (fWord[0].equals("pa"))
                                lItem.patentee = lineTxt.substring(6);
                            if (fWord[0].equals("prc")) {
                                lItem.country = lineTxt.substring(7);
                                ;
                            }
                            if (fWord[0].equals("icd$9f")) lItem.ipc = lineTxt.substring(10);
                            if (fWord[0].equals("cipf.pn")) lItem.citaion = lineTxt.substring(11);
                            if (fWord[0].equals("cin$cnt")) lItem.lCitedAmount = Integer.valueOf(lineTxt.substring(11));
                            if (fWord[0].equals("cipfct")) lItem.paCitedAmount = Integer.valueOf(lineTxt.substring(10));
                            if (fWord[0].equals("ki")) {
                                String KI = lineTxt.substring(6, 7);
                                if (KI.equals("A") || KI.equals("B") || KI.equals("C")) lItem.kindcode = 1;
                                else if (KI.equals("S")) lItem.kindcode = 3;
                                else lItem.kindcode = 2;
                            }
                            if (fWord[0].equals("PY")) lItem.year = Integer.valueOf(lineTxt.substring(3, 7));
                            if (fWord[0].equals("Z9")) lItem.lCitedAmount = Integer.valueOf(lineTxt.substring(3));
                            if (fWord[0].equals("AU")) {
                                String author = lineTxt.substring(3);
                                String[] au = author.split(",");
                                lItem.AU = au[0];
                                AUflag = true;
                            } else if (AUflag && fWord[0].equals("")) AUflag = false;
                            else if (AUflag && fWord[0].equals("")) {
                                String author = lineTxt.substring(3);
                                String[] au = author.split(",");
                                lItem.AU += au[0] + ",";
                            }
                        }
                    }
                    aTec.itemList.add(lItem);
                    all.itemList.add(lItem);
                }
                tecList.add(aTec);
            }
            return tecList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void  ReadFourSeq(String DataPath,Map<String, List<String>> thesaurus2) throws IOException {
        try {
            List<indicator> tecList = new ArrayList<indicator>();
            File rf = new File(DataPath);
            if (!rf.exists() && !rf.isDirectory()) {
                System.out.println(rf.getName() + " not exists");
                return;
            }
            File TecItems[] = rf.listFiles();
            File fileWrite = new File("E:\\Four.xls");
            if (!fileWrite.exists()) fileWrite.createNewFile();
            OutputStream os;
            os = new FileOutputStream(fileWrite);
            WritableWorkbook wwb = Workbook.createWorkbook(os);
            WritableSheet ws = wwb.createSheet("Indicator", 0);
            ws.addCell(new Label(0,0,"技术点"));
            for (File tecName : TecItems) {
                if (tecName.getName().equals("whole")) continue;
                Map<String,Double> msd=new HashMap<>();
                ws.addCell(new Label(Integer.valueOf(tecName.getName()),0,tecName.getName()));
                String tecNamePath = DataPath + "\\" + tecName.getName();
                File tf = new File(tecNamePath);
                File items[] = tf.listFiles();
                for (File item : items) {
                    String itemNamePath = item.getPath();
                    File itemF = new File(itemNamePath);
                    File itemFs[] = itemF.listFiles();
                    msd.put(item.getName(),(double)itemFs.length);
                }
                Map<String,Double> bigmsd=new HashMap<>();
                for (Map.Entry<String, List<String>> m : thesaurus2.entrySet()) {
                    List<String> ls = m.getValue();
                    for (String s:ls) {
                        for (Map.Entry<String, Double> ms : msd.entrySet()) {
                            if (ms.getKey().equals(s)) {
                                if (!bigmsd.containsKey(m.getKey()))bigmsd.put(m.getKey(),ms.getValue());
                                else bigmsd.put(m.getKey(),bigmsd.get(m.getKey())+ms.getValue());
                            }
                        }
                    }
                }
                int Row=1;
                for(Map.Entry<String, Double> ms : bigmsd.entrySet()){
                    ws.addCell(new Label(Integer.valueOf(tecName.getName())*2,Row,ms.getKey()));
                    ws.addCell(new Label(Integer.valueOf(tecName.getName())*2+1,Row,String.valueOf(ms.getValue())));
                    ++Row;
                }
            }
            wwb.write();
            wwb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static List<indicator> KeywordsRead(String DataPath,List<String> thesaurus) throws IOException {
        try {
            List<indicator> tecList = new ArrayList<indicator>();
            File rf = new File(DataPath);
            if (!rf.exists() && !rf.isDirectory()) {
                System.out.println(rf.getName() + " not exists");
                return null;
            }
            File TecItems[] = rf.listFiles();
            Map<String,List<literatureItem>> msli=new HashMap<>();
            for (File tecName : TecItems) {
                String tecNamePath = DataPath + "\\" + tecName.getName();
                String encoding = "utf-8";
                File tf = new File(tecNamePath);
                File items[] = tf.listFiles();
                for (File item : items) {
                    literatureItem lItem = new literatureItem();
                    String itemPath = tecNamePath + "\\" + item.getName();
                    File file = new File(itemPath);
                    if (file.isFile() && file.exists()) { //判断文件是否存在
                        InputStreamReader read = new InputStreamReader(
                                new FileInputStream(file), encoding);//考虑到编码格式
                        BufferedReader bufferedReader = new BufferedReader(read);
                        StringBuffer sb = new StringBuffer();
                        String lineTxt = null;
                        boolean AUflag = false;
                        String AB="";
                        String DE="";
                        while ((lineTxt = bufferedReader.readLine()) != null) {
                            String[] fWord = lineTxt.split(" ");
                            lItem.number=Integer.valueOf(file.getName());
                            if (fWord.length < 1) continue;
                            if (fWord[0].equals("pry"))
                                lItem.year = Integer.valueOf(lineTxt.substring(7, 11));
                            if (fWord[0].equals("pn")) {
                                lItem.pn = lineTxt.substring(6);
                            }
                            if (fWord[0].equals("pa"))
                                lItem.patentee = lineTxt.substring(6);
                            if (fWord[0].equals("prc")) {
                                lItem.country = lineTxt.substring(7);
                            }
                            if (fWord[0].equals("abd")) {
                                AB = lineTxt.substring(7).toLowerCase();
                            }
                            if (fWord[0].equals("AB")) {
                                AB = lineTxt.substring(3).toLowerCase();
                            }
                            if (fWord[0].equals("DE")) {
                                DE = lineTxt.substring(3).toLowerCase();
                            }
                            if (fWord[0].equals("icd$9f")) lItem.ipc = lineTxt.substring(10);
                            if (fWord[0].equals("cipf.pn")) lItem.citaion = lineTxt.substring(11);
                            if (fWord[0].equals("cin$cnt")) lItem.lCitedAmount = Integer.valueOf(lineTxt.substring(11));
                            if (fWord[0].equals("cipfct")) lItem.paCitedAmount = Integer.valueOf(lineTxt.substring(10));
                            if (fWord[0].equals("ki")) {
                                String KI = lineTxt.substring(6, 7);
                                if (KI.equals("A") || KI.equals("B") || KI.equals("C")) lItem.kindcode = 1;
                                else if (KI.equals("S")) lItem.kindcode = 3;
                                else lItem.kindcode = 2;
                            }
                            if (fWord[0].equals("PY")) lItem.year = Integer.valueOf(lineTxt.substring(3, 7));
                            if (fWord[0].equals("Z9")) lItem.lCitedAmount = Integer.valueOf(lineTxt.substring(3));
                            if (fWord[0].equals("AU")) {
                                String author = lineTxt.substring(3);
                                String[] au = author.split(",");
                                lItem.AU = au[0];
                                AUflag = true;
                            } else if (AUflag && fWord[0].equals("")) AUflag = false;
                            else if (AUflag && fWord[0].equals("")) {
                                String author = lineTxt.substring(3);
                                String[] au = author.split(",");
                                lItem.AU += au[0] + ",";
                            }
                        }for (String s:thesaurus){
                            s=s.toLowerCase();
                            if(AB.contains(s) || DE.contains(s)){
                                if (msli.containsKey(s)){
                                    List<literatureItem> li=msli.get(s);
                                    li.add(lItem);
                                    msli.put(s,li);
                                }else {
                                    List<literatureItem> li=new ArrayList<>();
                                    li.add(lItem);
                                    msli.put(s,li);
                                }
                                all.itemList.add(lItem);
                            }
                        }
                    }
                }
            }
            for (String s:thesaurus){
                if (!msli.containsKey(s)) continue;
                indicator aTec=new indicator();
                aTec.tec = s;
                aTec.itemList=msli.get(s);
                tecList.add(aTec);
            }
            return tecList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /*//合并*/
    public static List<indicator> getOne(List<indicator> tecList, Map<String, List<String>> Thesaurus) {
        List<indicator> newTecList = new ArrayList<indicator>();
        for (Map.Entry<String, List<String>> m : Thesaurus.entrySet()) {
            List<String> ls = m.getValue();
            indicator idc = new indicator();
            idc.tec = m.getKey();
            for (String s : ls) {
                for (indicator i : tecList) {
                    if (i.tec.equals(s)) {
                        idc.itemList.addAll(i.itemList);
                    }
                }
            }
            newTecList.add(idc);
        }
        return newTecList;
    }

    /*//两两共现结果*/
    public static Map<String, Double> TogetherRead(List<String> thesaurus, String DataPath) throws IOException {
        try {
            Map<String, Double> togetherMap = new HashMap<String, Double>();
            File rf = new File(DataPath);
            if (!rf.exists() && !rf.isDirectory()) {
                System.out.println(rf.getName() + " not exists");
                return null;
            }
            File TecItems[] = rf.listFiles();
            for (File tecName : TecItems) {
                String tecNamePath = DataPath + "\\" + tecName.getName();
                String encoding = "utf-8";
                File tf = new File(tecNamePath);
                File items[] = tf.listFiles();
                for (File item : items) {
                    String itemPath = tecNamePath + "\\" + item.getName();
                    File file = new File(itemPath);
                    if (file.isFile() && file.exists()) { //判断文件是否存在
                        InputStreamReader read = new InputStreamReader(
                                new FileInputStream(file), encoding);//考虑到编码格式
                        BufferedReader bufferedReader = new BufferedReader(read);
                        String lineTxt = null;
                        boolean AUflag = false;
                        String AB = "";
                        String DE = "";
                        while ((lineTxt = bufferedReader.readLine()) != null) {
                            String[] fWord = lineTxt.split(" ");
                            if (fWord.length==0) continue;
                            if (fWord[0].equals("AB")) {
                                AB = lineTxt.substring(3).replace("\n", "").replace("\r", "");
                                AUflag = true;
                            } else if (AUflag && !fWord[0].equals("")) AUflag = false;
                            else if (AUflag && fWord[0].equals("")) {
                                AB += lineTxt.substring(2).replace("\n", "").replace("\r", "");
                            }
                            if (fWord[0].equals("DE")) {
                                DE = lineTxt.substring(3).replace("\n", "").replace("\r", "");
                                AUflag = true;
                            } else if (AUflag && !fWord[0].equals("")) AUflag = false;
                            else if (AUflag && fWord[0].equals("")) {
                                DE += lineTxt.substring(2).replace("\n", "").replace("\r", "");
                            }
                        }
                        DE = DE.toLowerCase();
                        AB = AB.toLowerCase();
                        for (int i = 0; i < thesaurus.size(); ++i) {
                            String atec = thesaurus.get(i).toLowerCase();
                            if (DE.contains(atec) || AB.contains(atec)) {
                                if (keywordsFre.containsKey(atec))
                                    keywordsFre.put(atec, keywordsFre.get(atec) + 1);
                                else keywordsFre.put(atec, 1.0);
                            }
                            for (int j = i + 1; j < thesaurus.size(); ++j) {
                                String teci = thesaurus.get(i).toLowerCase();
                                String tecj = thesaurus.get(j).toLowerCase();
                                if (teci.equals("") || tecj.equals("")) continue;
                                if (DE.contains(teci) && DE.contains(tecj)
                                        || AB.contains(teci) && AB.contains(tecj)) {
                                    String togetherTec = teci + "##" + tecj;
                                    if (togetherMap.containsKey(togetherTec))
                                        togetherMap.put(togetherTec, togetherMap.get(togetherTec) + 1);
                                    else togetherMap.put(togetherTec, 1.0);
                                }
                            }
                        }
                    }
                }
            }
            return togetherMap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*//两两共现矩阵*/
    public static List<Item> TogetherCluster(Map<String, Double> togetherMap, List<String> thesaurus) {
        Map<String, Map<String, Double>> clusteredResult = new HashMap<>();
        for (int is = 0; is < thesaurus.size(); is++) {
            String s = thesaurus.get(is);
            for (Map.Entry<String, Double> entry : togetherMap.entrySet()) {
                String[] tecs = entry.getKey().split("##");
                if (tecs.length < 2) continue;
                String tec1 = tecs[0];
                String tec2 = tecs[1];
                if (tec1.equals(s)) {
                    if (!clusteredResult.containsKey(tec1)) {
                        Map<String, Double> msd = new HashMap<>();
                        msd.put(tec2, entry.getValue());
                        clusteredResult.put(tec1, msd);
                    } else if (clusteredResult.containsKey(tec1)){
                        Map<String, Double> msd = clusteredResult.get(tec1);
                        msd.put(tec2, entry.getValue());
                        clusteredResult.put(tec1, msd);
                    }
                    if (!clusteredResult.containsKey(tec2)) {
                        Map<String, Double> msd = new HashMap<>();
                        msd.put(tec1, entry.getValue());
                        clusteredResult.put(tec2, msd);
                    }else if (clusteredResult.containsKey(tec2)){
                        Map<String, Double> msd = clusteredResult.get(tec2);
                        msd.put(tec1, entry.getValue());
                        clusteredResult.put(tec2, msd);
                    }
                }
            }
            if (!clusteredResult.containsKey(s)) clusteredResult.put(s, new HashMap<String, Double>());
        }
        List<Item> li = new ArrayList<>();
        int index = 0;
        for (int is1 = 0; is1 < thesaurus.size(); ++is1) {
            Item i = new Item();
            i.originalClass = thesaurus.get(is1);
            Double sum = 0.0;
            String s1 = thesaurus.get(is1);
            Map<String, Double> msm = clusteredResult.get(s1);
            for (int is = 0; is < thesaurus.size(); ++is) {
                String s = thesaurus.get(is);
                if (msm == null) i.vec.add(0.0);
                else if (msm.containsKey(s)) {
                    i.vec.add(msm.get(s));
                    sum += msm.get(s);
                } else i.vec.add(0.0);
            }
            i.vec.set(index, sum + 1);
            li.add(i);
            index++;
        }
        return li;
    }

    /*//大技术点共现*/
    private static Map<String, Double> bigTogether(Map<String, Double> togetherMap, Map<String, List<String>> Thesaurus2) {
        Map<String, Double> msdOut = new HashMap<String, Double>();
        String bigtec1 = "";
        String bigtec2 = "";
        for (Map.Entry<String, Double> m1 : togetherMap.entrySet()) {
            String[] tecs = m1.getKey().split("##");
            if (tecs.length < 2) continue;
            String tec1 = tecs[0];
            String tec2 = tecs[1];
            if (tec1.equals("微机器人系统") || tec2.equals("微机器人系统") ){
                int i=0;
            }
            if (tec1.equals("人机交互") || tec2.equals("人机交互") ){
                int i=0;
            }
            for (Map.Entry<String, List<String>> msl : Thesaurus2.entrySet()) {
                if (msl.getValue().contains(tec1) && msl.getValue().contains(tec2)) continue;
                if (msl.getValue().contains(tec1)) bigtec1 = msl.getKey();
                for (Map.Entry<String, List<String>> msl2 : Thesaurus2.entrySet()) {
                    if (msl.getKey().equals(msl2.getKey())){
                        continue;
                    }
                    if (msl2.getValue().contains(tec2)) {
                        bigtec2 = msl2.getKey();
                    }
                    if (!bigtec1.equals("") && !bigtec2.equals("")) {
                        String key = bigtec1 + "##" + bigtec2;
                        if (!msdOut.containsKey(key)) msdOut.put(key, m1.getValue());
                        else msdOut.put(key, msdOut.get(key) + m1.getValue());
                    }
                    bigtec2 = "";
                }
                bigtec1="";
            }
        }
        return msdOut;
    }

    public static void BigKeywords(Map<String, List<String>> thesaurus2) {
        Map<String, Double> keywords = new HashMap<>();
        for (Map.Entry<String, Double> me : keywordsFre.entrySet()) {
            for (Map.Entry<String, List<String>> msl : thesaurus2.entrySet()) {
                if (msl.getValue().contains(me.getKey())) {
                    String key = msl.getKey();
                    if (keywords.containsKey(key)) keywords.put(key, keywords.get(key) + me.getValue());
                    else keywords.put(key, me.getValue());
                }
            }
        }
        keywordsFre.clear();
        keywordsFre = keywords;
    }

    /*//txt读取共现信息*/
    public static Map<String, Double> TogetherReadFromTxt(String DataPath) throws IOException {
        try {
            Map<String, Double> tecMap = new HashMap<>();
            File rf = new File(DataPath);
            if (!rf.exists() && !rf.isDirectory()) {
                System.out.println(rf.getName() + " not exists");
                return null;
            }
            String encoding = "utf-8";
            if (rf.isFile() && rf.exists()) { //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(rf), encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    String[] words = lineTxt.split("\t");
                    tecMap.put(words[0], Double.valueOf(words[1]));
                }
            }
            return tecMap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*//SaltonLength*/
    public static List<Item> SaltonLength(List<Item> togetherList) {
        List<Item> sList = new ArrayList<>();
        for (int i = 0; i < togetherList.size(); ++i) {
            List<Double> outVec = new ArrayList<>();
            List<Double> veci = togetherList.get(i).vec;
            for (int j = 0; j < veci.size(); ++j) {
                outVec.add(veci.get(j) / Math.sqrt(veci.get(i) * togetherList.get(j).vec.get(j)));
            }
            Item item = new Item();
            item.originalClass = togetherList.get(i).originalClass;
            item.vec = outVec;
            sList.add(item);
        }
        for (int j=0;j< sList.size();++j) {
            List<Double> veci = sList.get(j).vec;
            for (int i = 0; i < veci.size(); ++i) {
                if (i==j) veci.set(i,1.0);
                else veci.set(i, 1 - veci.get(i));
            }
        }
        return sList;
    }


    /*//写Map文件，要改*/
    public static void MapWrite(Map<Double, Double> m, String filename) throws IOException {
        try {
//            String outpath = "E:\\Result.txt";
            File outfile = new File(filename);
            if (!outfile.exists())
                outfile.createNewFile();
            FileOutputStream out = new FileOutputStream(outfile, false); //如果追加方式用true
            for (double d = 1985; d < 2016; d++) {
                StringBuffer sb = new StringBuffer();
                sb.append(d + "\t" + m.get(d) + "\n");
                out.write(sb.toString().getBytes("utf-8"));//注意需要转换对应的字符集
            }
            out.close();
        } catch (Exception e) {

        }
    }

    public static void SDMapWrite(Map<String, Double> m, String filename) throws IOException {
        try {
//            String outpath = "E:\\Result.txt";
            File outfile = new File(filename);
            if (!outfile.exists())
                outfile.createNewFile();
            FileOutputStream out = new FileOutputStream(outfile, false); //如果追加方式用true
            for (Map.Entry<String, Double> msd : m.entrySet()) {
                StringBuffer sb = new StringBuffer();
                sb.append(msd.getKey() + "\t" + msd.getValue() + "\n");
                out.write(sb.toString().getBytes("utf-8"));//注意需要转换对应的字符集
            }
            out.close();
        } catch (Exception e) {

        }
    }

    public static void LIMapWrite(List<Item> LI, String filename,int k) throws IOException {
        try {
//            String outpath = "E:\\Result.txt";
            File outfile = new File(filename);
            if (!outfile.exists())
                outfile.createNewFile();
            FileOutputStream out = new FileOutputStream(outfile, false); //如果追加方式用true
            for (int i = 0; i < k; ++i) {
                StringBuffer sb = new StringBuffer();
                for (Item j : LI) {
                    if (j.clusteredClass == i) {
                        sb.append(j.originalClass + "\t");
                    }
                }
                sb.append("\n");
                out.write(sb.toString().getBytes("utf-8"));//注意需要转换对应的字符集
            }
            out.close();
        } catch (Exception e) {

        }
    }

    public static void LIMapWrite2(List<Item> LI, String filename) throws IOException {
        try {
//            String outpath = "E:\\Result.txt";
            File outfile = new File(filename);
            if (!outfile.exists())
                outfile.createNewFile();
            FileOutputStream out = new FileOutputStream(outfile, false); //如果追加方式用true
            for (Item j : LI) {
                StringBuffer sb = new StringBuffer();
                for (double d:j.vec){
                    sb.append(d+"\t");
                }
                sb.append("\n");
                out.write(sb.toString().getBytes("utf-8"));//注意需要转换对应的字符集
            }
            out.close();
        } catch (Exception e) {

        }
    }

    public static void StringMapWrite(Map<String, List<String>> m, String filename) throws IOException {
        try {
//            String outpath = "E:\\Result.txt";
            File outfile = new File(filename);
            if (!outfile.exists())
                outfile.createNewFile();
            FileOutputStream out = new FileOutputStream(outfile, true); //如果追加方式用true
            Iterator it = m.keySet().iterator();
            for (Map.Entry<String, List<String>> entry : m.entrySet()) {
                StringBuffer sb = new StringBuffer();
                sb.append(entry.getKey());
                List<String> ls = entry.getValue();
                for (String s : ls) {
                    sb.append("\t" + s );
                }
                sb.append("\n");
                out.write(sb.toString().getBytes("utf-8"));//注意需要转换对应的字符集
            }
            out.close();
        } catch (Exception e) {

        }
    }


}
