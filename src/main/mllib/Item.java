package mllib;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by linheng on 2016/4/18.
 */
public class Item {
    public String originalClass;
    public int clusteredClass;
    public List<Double> vec=new ArrayList<Double>();

    public Item(){
        originalClass="";
        clusteredClass=0;
    };
}
