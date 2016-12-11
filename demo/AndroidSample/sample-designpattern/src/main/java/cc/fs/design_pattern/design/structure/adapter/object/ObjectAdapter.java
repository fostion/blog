package cc.fs.design_pattern.design.structure.adapter.object;

import cc.fs.design_pattern.design.structure.adapter.clazz.BullCharge;
import cc.fs.design_pattern.design.structure.adapter.clazz.ChargeType;

public class ObjectAdapter implements ChargeType{

    private BullCharge bullCharge;

    public ObjectAdapter(BullCharge bullCharge){
        this.bullCharge = bullCharge;
    }

    @Override
    public void charge110V() {
        System.out.println(" -----充110V电源----- ");
    }

    @Override
    public void charge220V() {
        bullCharge.charge220V();
    }
}
