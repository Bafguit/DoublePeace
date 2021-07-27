package DoublePeace.cards.uncommon;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import DoublePeace.DPMod;
import DoublePeace.abstracts.ChaserCard;
import DoublePeace.characters.TheChaser;

import static DoublePeace.DPMod.makeCardPath;

public class FeebleDefend extends ChaserCard {

    public static final String ID = DPMod.makeID("Feeble Defend");
    public static final String IMG = makeCardPath("FeebleDefend.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheChaser.Enums.COLOR_CHASER;

    private static final int COST = 1;
    private static final int BLOCK = 8;
    private static final int MULTI = 2;
    private static final int UP_MULTI = 1;

    public FeebleDefend() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET, 0, BLOCK, MULTI);
    }

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (AbstractDungeon.player.hasPower(FrailPower.POWER_ID)) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, this.block * (p.hasPower(FrailPower.POWER_ID) ? this.magicNumber : 1)));
    }

    @Override
    public void upgradeCard() {
        upgradeMagicNumber(UP_MULTI);
    }

}