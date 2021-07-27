package DoublePeace.cards.uncommon;

import com.megacrit.cardcrawl.actions.defect.IncreaseMiscAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import DoublePeace.DPMod;
import DoublePeace.actions.TargetAttackAction;
import DoublePeace.abstracts.ChaserCard;
import DoublePeace.characters.TheChaser;

import static DoublePeace.DPMod.makeCardPath;

public class Ragewind extends ChaserCard {

    public static final String ID = DPMod.makeID("Ragewind");
    public static final String IMG = makeCardPath("Ragewind.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheChaser.Enums.COLOR_CHASER;

    private static final int COST = 1;
    private static final int UP_COST = 0;
    private static final int MAGIC = 1;

    public Ragewind() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.misc = 1;
        this.magicNumber = this.baseMagicNumber = this.misc;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new IncreaseMiscAction(this.uuid, this.misc, 1));
        for(int i = 0; i < this.magicNumber; i++) {
            addToBot(new TargetAttackAction());
        }
    }

    public void applyPowers() {
        this.magicNumber = this.baseMagicNumber = this.misc;
        super.applyPowers();
        this.initializeDescription();
    }

    @Override
    public void upgradeCard() {
        upgradeBaseCost(UP_COST);
    }

}
