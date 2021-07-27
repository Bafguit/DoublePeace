package DoublePeace.cards.uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import DoublePeace.DPMod;
import DoublePeace.abstracts.ChaserCard;
import DoublePeace.characters.TheChaser;
import DoublePeace.powers.UnfortifiedPower;

import static DoublePeace.DPMod.makeCardPath;

public class UnstableBlock extends ChaserCard {

    public static final String ID = DPMod.makeID("Unstable Block");
    public static final String IMG = makeCardPath("UnstableBlock.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheChaser.Enums.COLOR_CHASER;

    private static final int COST = 2;
    private static final int BLOCK = 25;
    private static final int WEAK = 1;

    public UnstableBlock() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET, 0, BLOCK, WEAK);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, this.block));
        if(this.upgraded) {
            addToBot(new ApplyPowerAction(p, p, new UnfortifiedPower(p, p, this.magicNumber), this.magicNumber));
        } else {
            addToBot(new ApplyPowerAction(p, p, new VulnerablePower(p, this.magicNumber, false), this.magicNumber));
        }
    }

    @Override
    public void upgradeCard() {
    }

}
