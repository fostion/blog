package cc.fs.design_pattern.design.create.prototype;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 原型模式
 * 介绍：根据对象作为原型，对其进行复制、克隆产生产生
 * 复制分类：
 * 1.浅复制：将一个对象复制后，基本数据类型的变量都会重新创建，而引用类型，指向的还是原对象所指向的
 * 2.深复制：将一个对象复制后，不论是基本数据类型还有引用类型，都是重新创建的。
 */
public class Prototype implements Cloneable, Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private DataObject dataObject;

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public DataObject getDataObject() {
        return dataObject;
    }

    public void setDataObject(DataObject dataObject) {
        this.dataObject = dataObject;
    }

    /**
     * 浅复制
     */
    public Prototype lightClone() {
        Prototype resPrototype = null;
        try {
            resPrototype = (Prototype) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resPrototype;
    }

    /**
     * 深复制
     */
    public Prototype deepClone() {
        Prototype resPrototype = null;
        try {
            //将当前对象写进二进制流中
            ByteArrayOutputStream bos = new ByteArrayOutputStream();//二进制输出流
            ObjectOutputStream oos = new ObjectOutputStream(bos);//对象输出流
            oos.writeObject(this);

            //将二进制流写成对象
            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());//二进制写入流
            ObjectInputStream ois = new ObjectInputStream(bis);//对象输入流
            resPrototype = (Prototype) ois.readObject();

            bos.close();
            oos.close();
            bis.close();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resPrototype;
    }
}
