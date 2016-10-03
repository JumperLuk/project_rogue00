package eu.devy.network;

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
     //TODO: Do stuff!!
        return new DataPackage(1);
    }

}
