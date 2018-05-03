package DataClasses;

import java.util.ArrayList;

public class DataArrayList {
    private static ArrayList<Data> dataArrayList2 ;


    public DataArrayList()
    {
        dataArrayList2 = new ArrayList();
    }

    public synchronized ArrayList<Data> getDataArrayList2() {
        return dataArrayList2;
    }

    public synchronized void  clear() {
        dataArrayList2.removeAll(dataArrayList2);
    }
}
