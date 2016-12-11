package cc.fs.design_pattern.design.create.prototype;

import android.util.Log;

public class PrototypeRun {

    public void run() {
        Prototype source = new Prototype();
        source.setName("我是原型");
        source.setDataObject(new DataObject());
        Prototype lightClone = source.lightClone();
        Prototype deepClone = source.deepClone();

        L("--------------------------------------------------------------------");
        L("源对象 " + source);
        L("浅复制对象 " + lightClone);
        L("深复制对象 " + deepClone);
        L("源对象内存地址：" + source.hashCode());
        L("浅复制对象内存地址：" + lightClone.hashCode());
        L("深复制对象内存地址：" + deepClone.hashCode());

        L("--------------------------------------------------------------------");
        L("源对象name：" + source.getName());
        L("浅复制对象name：" + lightClone.getName());
        L("深复制对象name：" + deepClone.getName());
        L("源对象name内存地址：" + source.getName().hashCode());
        L("浅复制对象name内存地址：" + lightClone.getName().hashCode());
        L("深复制对象name内存地址：" + deepClone.getName().hashCode());

        L("--原型修改内容------------------------------------------------------");
        source.setName("我是原型，现在我要修改");
        L("源对象name：" + source.getName());
        L("浅复制对象name：" + lightClone.getName());
        L("深复制对象name：" + deepClone.getName());
        L("源对象name内存地址：" + source.getName().hashCode());
        L("浅复制对象name内存地址：" + lightClone.getName().hashCode());
        L("深复制对象name内存地址：" + deepClone.getName().hashCode());


        L("--------------------------------------------------------------------");
        L("源对象dataObject：" + source.getDataObject());
        L("浅复制对象dataObject：" + lightClone.getDataObject());
        L("深复制对象dataObject：" + deepClone.getDataObject());
        L("源对象dataObject内存地址：" + source.getDataObject().hashCode());
        L("浅复制对象dataObject内存地址：" + lightClone.getDataObject().hashCode());
        L("深复制对象dataObject内存地址：" + deepClone.getDataObject().hashCode());

        L("--原型修改dataObject内容---------------------------------------------------------");
        source.setDataObject(new DataObject());
        L("源对象dataObject：" + source.getDataObject());
        L("浅复制对象dataObject：" + lightClone.getDataObject());
        L("深复制对象dataObject：" + deepClone.getDataObject());
        L("源对象dataObject内存地址：" + source.getDataObject().hashCode());
        L("浅复制对象dataObject内存地址：" + lightClone.getDataObject().hashCode());
        L("深复制对象dataObject内存地址：" + deepClone.getDataObject().hashCode());
    }

    void L(String msg) {
        Log.e(" --原型模式测试--", msg);
    }

}
