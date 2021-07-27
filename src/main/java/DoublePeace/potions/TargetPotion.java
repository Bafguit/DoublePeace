package DoublePeace.potions;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import DoublePeace.DPMod;
import DoublePeace.actions.TargetAttackAction;

public class TargetPotion extends AbstractPotion {

    public static final String ID = DPMod.makeID("Target Potion");
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(ID);
    private static final String NAME = potionStrings.NAME;
    private static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public TargetPotion() {
        super(NAME, ID, PotionRarity.UNCOMMON,
                PotionSize.SPIKY, PotionColor.NONE);
        this.isThrown = true;
        this.labOutlineColor = CardHelper.getColor(100, 100, 255).cpy();
    }

    @Override
    public void use(AbstractCreature target) {
        for(int i = 0; i < this.potency; i++) {
            this.addToBot(new TargetAttackAction());
        }
    }

    @Override
    public void initializeData() {
        this.potency = this.getPotency();
        this.description = DESCRIPTIONS[0] + this.potency + DESCRIPTIONS[1];

        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
    }

    @Override
    public int getPotency(int i) {
        return 5;
    }

    @Override
    public AbstractPotion makeCopy() {
        return new TargetPotion();
    }
}
