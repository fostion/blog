package cc.fs.design_pattern.design.structure.adapter;

import cc.fs.design_pattern.design.structure.adapter.clazz.ClzzAdapter;
import cc.fs.design_pattern.design.structure.adapter.clazz.BullCharge;
import cc.fs.design_pattern.design.structure.adapter.object.ObjectAdapter;

/**
 * 适配器模式
 * 介绍：将某个类的接口转换成另外一种表示，目的是消除接口不匹配所造成的兼容性问题
 * 分类：
 * 1. 类适配
 *    使用新的Adapter类，继承原有方法，实现新方法（接口），
 *    例：110v 和 220v 充电标准，原来技术是220v，新的技术是沿用原来220v技术，实现新的110v充电技术
 *
 * 2. 对象适配
 *    使用新的Adapter类，实现接口，构造是传入源对象，使用源对象方法和实现新方法
 *
 */
public class AdapterRun {

    //使用适配器后不仅可以使用220v 还添加了新功能110v

    //类适配器
    public void runClassAdapter(){
        ClzzAdapter adapter = new ClzzAdapter();
        adapter.charge110V();
        adapter.charge220V();

        BullCharge bullCharge = new BullCharge();
        bullCharge.charge220V();
    }

    //对象适配
    public void runObjectAdapter(){
        BullCharge bullCharge = new BullCharge();
        ObjectAdapter adapter = new ObjectAdapter(bullCharge);
        adapter.charge110V();
        adapter.charge220V();
    }

}
