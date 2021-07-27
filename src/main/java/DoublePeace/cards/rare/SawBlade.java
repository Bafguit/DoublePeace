package DoublePeace.cards.rare;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import DoublePeace.DPMod;
import DoublePeace.abstracts.ChaserCard;
import DoublePeace.characters.TheChaser;
import DoublePeace.powers.SawBladePower;

import static DoublePeace.DPMod.makeCardPath;

public class SawBlade extends ChaserCard {

    public static final String ID = DPMod.makeID("Saw Blade");
    public static final String IMG = makeCardPath("SawBlade.png");

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheChaser.Enums.COLOR_CHASER;

    private static final int COST = 3;
    private static final int MAGIC = 1;

    public SawBlade() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET, 0, 0, MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new SawBladePower(p, this.magicNumber), this.magicNumber));
    }

    @Override
    public void upgradeCard() {
        this.isInnate = true;
    }
}