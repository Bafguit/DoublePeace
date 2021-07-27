//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package DoublePeace.patches.interfaces;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

public interface OnInflictPowerSubscriber {
    AbstractPower onInflictPower(AbstractPower power, AbstractCreature target, AbstractCreature source);
}
