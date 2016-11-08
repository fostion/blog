// Sample.aidl
package cc.fs.sample;
import cc.fs.sample.Data;

// Declare any non-default types here with import statements

interface Sample {
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    void setData(in Data data);
}
