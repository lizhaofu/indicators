package mllib;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class kmeans {
    private static int k=4;
    private static Map<String,List<String>> Msls;
    public static void main(String[] args) throws IOException {
        double data[][] = {
                {0.1, 0.1, 0.1, 0.1, 0.2},
                {0.1, 0.2, 0.5, 0.1, 0.2},
                {0.1, 0.1, 0.2, 0.1, 0.2},
                {0.1, 0.1, 0.1, 0.1, 0.2},
                {7.1, 7.1, 7.1, 7.1, 7.2},
                {7.2, 7.5, 7.9, 7.6, 7.7},
                {7.3, 7.1, 7.5, 7.1, 7.8},
                {7.4, 7.6, 7.2, 7.1, 7.2},
                {12.1, 12.9, 12.3, 12.6, 12.2},
                {12.2, 12.3, 12.9, 12.7, 12.2},
                {12.1, 12.2, 12.9, 12.7, 12.3},
                {12.1, 12.5, 12.5, 12.2, 12.2},
                {19.5, 19.1, 19.3, 19.1, 19.2},
                {19.1, 19.3, 19.1, 19.8, 19.2},
                {19.3, 19.2, 19.1, 19.9, 19.2},
                {19.2, 19.1, 19.8, 19.4, 19.2},
                {3.1, 2.1, 5.1, 1.1, 4.2},
                {17.1, 16.1, 17.7, 17.1, 18.2},
                {10.1, 11.1, 9.1, 10.1, 8.2},
                {2.1, 3.1, 5.1, 4.1, 4.2},
                {0.0,0.0,0.0,0.0,0.0},
                {19.2,19.1,19.8,19.4,19.2},
                {5.1,6.1,7.1,8.1,9.2},
                {4.1,9.1,8.7,6.1,7.2},
                {1.1,9.1,19.1,2.1,9.2},
                {12.1,13.1,5.1,1.1,14.2},
                {9.2,8.1,1.8,8.4,7.2},
                {13.1,12.1,15.1,11.1,14.2},
                {3.1,4.1,7.7,7.1,8.2},
                {5.1,6.1,14.1,13.1,11.2},
        };
        List<Item> li=new ArrayList<>();
        for (int i=0;i<30;++i){
            Item it=new Item();
            for (int j=0;j<5;++j){
                it.originalClass= String.valueOf(i);
                it.vec.add(data[i][j]);
            }
            li.add(it);
        }
        Msls=new HashMap<>();
        List<String> ls= Arrays.asList("林烈迎","林振轩","林晟川","林新刚","林旭亮","林昕雨","林子炫","林若屹","林秀芬","林一仪","林立平","林一呜","林芷源","林新奇","林艾朋","林聃彤","林宇","林泽腾","林洪磊","林朋","林钰丹","林海宏","林臻芩","林中哲","林芮桐","林锦","林诗瑜","林竣辰","林勋","林腾兮");
        Msls.put("姓林的",ls);
        ls= Arrays.asList("李烈迎","李振轩","李晟川","李新刚","李旭亮","李昕雨","李子炫","李若屹","李秀芬","李一仪","李立平","李一呜","李芷源","李新奇","李艾朋","李聃彤","李宇","李泽腾","李洪磊","李朋","李钰丹","李海宏","李臻芩","李中哲","李芮桐","李锦","李诗瑜","李竣辰","李勋","李腾兮");
        Msls.put("姓李的",ls);
        ls= Arrays.asList("王烈迎","王振轩","王晟川","王新刚","王旭亮","王昕雨","王子炫","王若屹","王秀芬","王一仪","王立平","王一呜","王芷源","王新奇","王艾朋","王聃彤","王宇","王泽腾","王洪磊","王朋","王钰丹","王海宏","王臻芩","王中哲","王芮桐","王锦","王诗瑜","王竣辰","王勋","王腾兮");
        Msls.put("姓王的",ls);
        ls= Arrays.asList("张烈迎","张振轩","张晟川","张新刚","张旭亮","张昕雨","张子炫","张若屹","张秀芬","张一仪","张立平","张一呜","张芷源","张新奇","张艾朋","张聃彤","张宇","张泽腾","张洪磊","张朋","张钰丹","张海宏","张臻芩","张中哲","张芮桐","张锦","张诗瑜","张竣辰","张勋","张腾兮");
        Msls.put("姓张的",ls);
        model(li);
    }

    public kmeans(int i) {
        k = i;
    }

    /*计算两个向量间的距离*/
    private static double getDistXY(Item tec1, Item tec2) {
        double sum = 0;
        for (int i = 0; i < tec1.vec.size(); ++i) {
            double vec1 = tec1.vec.get(i);
            double vec2 = tec2.vec.get(i);
            sum += (vec1 - vec2) * (vec1 - vec2);
        }
        return Math.sqrt(sum);
    }

    /*将某个元素聚到某一类*/
    private static int clusterOfTuple(List<Item> means, Item tuple) {
        double dist = getDistXY(means.get(0), tuple);
        double tmp;
        int label = 0;//标示属于哪一个簇
        for (int i = 1; i < k; i++) {
            tmp = getDistXY(means.get(i), tuple);
            if (tmp < dist) {
                dist = tmp;
                label = i;
            }
        }
        return label;
    }

    /*计算适应度函数*/
    private static double getVar(List<Item> clusters, List<Item> means) {
        double var = 0;
        for (int i = 0; i < clusters.size(); i++) {
            for (int j = 0; j < means.size(); j++) {
                var += getDistXY(clusters.get(i), means.get(j));
            }
        }
        //cout<<"sum:"<<sum<<endl;
        return var;
    }

    static double getEK(List<Item> clusters, List<Item> means) {
        double EK = 0;
        for (Item itm : means){
            for (Item itc : clusters) {
                if (itm.clusteredClass==itc.clusteredClass)
                EK += getDistXY(itm, itc);
            }
        }
        return EK;
    }

    static double getDK(List<Item> means) {
        double DK = 0;
        for (int i = 0; i < k; ++i) {
            for (int j = i + 1; j < k; ++j) {
                double tp = getDistXY(means.get(i), means.get(j));
                if (tp > DK) {
                    DK = tp;
                }
            }
        }
        return DK;
    }

    static double fevaluate(List<Item> clusters, List<Item> means) {
        return getDK(means) / getEK(clusters,means) / k;
    }

    /*得到所有的聚类中心*/
    static private Item getMeans(List<Item> cluster) {
        int num = cluster.size();
        Item it = new Item();
        Map<Integer, Double> mid = new HashMap<>();
        int dimNum = cluster.get(0).vec.size();
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < dimNum; ++j) {
                if (mid.containsKey(j)) mid.put(j, mid.get(j) + cluster.get(i).vec.get(j));
                else mid.put(j, cluster.get(i).vec.get(j));
            }
        }
        for (int j = 0; j < dimNum; ++j)
            it.vec.add(mid.get(j) / num);
        return it;
        //cout<<"sum:"<<sum<<endl;
    }

    /*模型*/
    public static List<Item> model(List<Item> tuples) throws IOException {
        List<Item> clusters = new ArrayList<Item>();//k个簇
        List<Item> means = new ArrayList<Item>();//k个中心点
        //一开始随机选取k条记录的值作为k个簇的质心（均值）
        List<Integer> ks = new ArrayList<>();
        for (int i = 0; i < k; ) {
            int iToSelect = (int) (Math.random() * (tuples.size() - 1));
            if (!ks.contains(iToSelect)) {
                Item item = tuples.get(iToSelect);
                item.clusteredClass = i;
                means.add(item);
                ++i;
            }
            ks.add(iToSelect);
        }
        int lable = 0;
        //根据默认的质心给簇赋值
        for (int i = 0; i < tuples.size(); ++i) {
            lable = clusterOfTuple(means, tuples.get(i));
            Item it = tuples.get(i);
            it.clusteredClass = lable;
            clusters.add(it);
        }
        double oldVar = -1;
        double newVar = getVar(clusters, means);
        System.out.print("初始的整体误差平方和为：" + newVar + "\n");
        int t = 0;
        while (Math.abs(newVar - oldVar) >= 1e-10) //当新旧函数值相差不到1即准则函数值不发生明显变化时，算法终止
        {
            System.out.print("第 " + t + " 次迭代开始：\n");
            for (int i = 0; i < k; i++) //更新每个簇的中心点
            {
                List<Item> currentCluster = new ArrayList<>();
                for (int j = 0; j < clusters.size(); j++) {
                    if (clusters.get(j).clusteredClass == i)
                        currentCluster.add(clusters.get(j));
                }
                Item item = getMeans(currentCluster);
                item.clusteredClass = i;
                means.set(i, item);
            }
            oldVar = newVar;
//            newVar = getVar(clusters, means); //计算新的准则函数值
            newVar = fevaluate(clusters, means);
            clusters.clear();


            //根据新的质心获得新的簇
            for (int i = 0; i != tuples.size(); ++i) {
                lable = clusterOfTuple(means, tuples.get(i));
                Item it = tuples.get(i);
                it.clusteredClass = lable;
                clusters.add(it);
            }
            System.out.print("此次迭代之后的EK为：" + getEK(clusters,means) + "\n");
            System.out.print("此次迭代之后的DK为：" + getDK(means) + "\n");
            ++t;
        }
        System.out.print("最终的聚类中心：\n");
        for (int i=0;i<k;++i){
            System.out.print(String.format("means%d[" ,i));
            for (Item it:means){
                if (it.clusteredClass==i){
                    for (int kk=0;kk<it.vec.size();++kk){
                        System.out.print(String.format("%5.1f," ,it.vec.get(kk)));
                    }
                    System.out.print("\n");
                }
            }
        }
        System.out.print("聚类结果：\n");
        for (int i=0;i<k;++i){
            System.out.print(String.format("class%d\n[" ,i));
            for (Item it:clusters){
                if (it.clusteredClass==i){
                    System.out.print(it.originalClass+"\t");
                    for (int kk=0;kk<it.vec.size();++kk){
                        System.out.print(String.format("%5.1f," ,it.vec.get(kk)));
                    }
                    System.out.print("\n");
                }
            }
        }
        LiWrite(clusters,means,"E:\\result.txt");
        return clusters;
    }
    public static void LiWrite(List<Item> clusters, List<Item> means, String filename) throws IOException {
        try {
//            String outpath = "E:\\Result.txt";
            File outfile = new File(filename);
            if (!outfile.exists())
                outfile.createNewFile();
            FileOutputStream out = new FileOutputStream(outfile, false); //如果追加方式用true
//            for (Item i:means){
//                StringBuffer sb = new StringBuffer();
//                for (Item j:clusters){
//                    if (i.clusteredClass==j.clusteredClass){
//                        int dist=(int)getDistXY(i,j)*10;
//                        sb.append(j.originalClass+"\t"+dist + "\n" );
//                    }
//                }
//                sb.append("\n" );
//                out.write(sb.toString().getBytes("utf-8"));
//            }
//            for (int i=0;i<means.size();++i){
//                for (int j=i+1;j<means.size();++j){
//                    StringBuffer sb = new StringBuffer();
//                    int dist=(int)getDistXY(means.get(i),means.get(j))*10;
//                    sb.append(means.get(i).clusteredClass+"\t"+means.get(j).clusteredClass+"\t"+dist + "\n" );
//                    out.write(sb.toString().getBytes("utf-8"));
//                }
//            }

            StringBuffer sb = new StringBuffer();
            for (Item i : clusters) {
                sb = new StringBuffer();
                int classIndex=0;
                for (Map.Entry<String, List<String>> msl : Msls.entrySet()) {
                    if (i.clusteredClass==classIndex){
                        for (Item j:means){
                            if (i.clusteredClass==j.clusteredClass){
                                sb.append(msl.getValue().get(Integer.valueOf(i.originalClass)) + "\t"+j.clusteredClass+"\t"+getDistXY(i,j)+"\n");
                            }
                        }
                    }
                    classIndex++;
                }
                out.write(sb.toString().getBytes("utf-8"));
            }
            for (int i=0;i<means.size();++i){
                for (int j=i+1;j<means.size();++j){
                    sb = new StringBuffer();
                    int dist=(int)getDistXY(means.get(i),means.get(j));
                    sb.append(means.get(i).clusteredClass+"\t"+means.get(j).clusteredClass+"\t"+dist + "\n" );
                    out.write(sb.toString().getBytes("utf-8"));
                }
            }
//            sb.append("\n");
//            out.write(sb.toString().getBytes("utf-8"));
//            for (Item i : clusters) {
//                StringBuffer sb1 = new StringBuffer();
//                int classIndex=0;
//                for (Map.Entry<String, List<String>> msl : Msls.entrySet()) {
//                    if (i.clusteredClass==classIndex){
//                        sb1.append("\""+msl.getKey() + "\",");
//                    }
//                    classIndex++;
//                }
//                out.write(sb1.toString().getBytes("utf-8"));
//            }
            out.close();
        } catch (Exception e) {

        }
    }
}