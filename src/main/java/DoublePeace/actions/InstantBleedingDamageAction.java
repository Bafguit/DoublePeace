//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package DoublePeace.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class InstantBleedingDamageAction extends AbstractGameAction {

    public InstantBleedingDamageAction(final AbstractCreature target, int amount) {
        this.target = target;
        this.amount = amount;
        this.actionType = ActionType.DAMAGE;
    }

    public void update() {
        if(!this.target.isDeadOrEscaped()) {
            BleedingPower bleedingPower = (BleedingPower) this.target.getPower(BleedingPower.POWER_ID);
            if (bleedingPower != null) {
                bleedingPower.getBleedingDamage(this.amount);
            }
        }

        this.isDone = true;
    }
}
