package cc.fs.design_pattern.design.structure.adapter.clazz;

public class ClzzAdapter extends BullCharge implements ChargeType {
    @Override
    public void charge110V() {
        System.out.println(" -----充110V电源----- ");
    }
}
