package Networking;

/**
 * Created by schueler on 01.10.2016.
 */
public class DataPackage {

    int value;

    public DataPackage()
    {

    }

    public DataPackage(int value)
    {
        this.value = value;
    }


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String toString()
    {
        return "" + value + ";";
    }

    public static DataPackage toDatapackage(String str)
    {
        DataPackage temp = new DataPackage();
        temp.setValue(Integer.parseInt(str.substring(0, str.indexOf(";"))));
        return temp;
    }

}
