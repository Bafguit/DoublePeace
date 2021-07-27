package DoublePeace.cards.uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import DoublePeace.DPMod;
import DoublePeace.actions.ChaserUtil;
import DoublePeace.abstracts.ChaserCard;
import DoublePeace.characters.TheChaser;

import static DoublePeace.DPMod.makeCardPath;

public class Conquest extends ChaserCard {

    public static final String ID = DPMod.makeID("Conquest");
    public static final String IMG = makeCardPath("Conquest.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheChaser.Enums.COLOR_CHASER;

    private static final int COST = 1;

    public Conquest() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(ChaserUtil.getPlayerDebuffAmount() > 0) {
            addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, ChaserUtil.getPlayerDebuffAmount())));
        }
    }

    @Override
    public void upgradeCard() {
        this.exhaust = false;
    }

}
