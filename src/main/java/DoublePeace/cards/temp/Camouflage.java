package DoublePeace.cards.temp;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import DoublePeace.DPMod;
import DoublePeace.abstracts.ChaserCard;
import DoublePeace.powers.FakeSorePower;

import static DoublePeace.DPMod.makeCardPath;

public class Camouflage extends ChaserCard {

    public static final String ID = DPMod.makeID("Camouflage");
    public static final String IMG = makeCardPath("Camouflage.png");

    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = CardColor.COLORLESS;

    private static final int COST = 1;
    private static final int AMOUNT = 3;
    private static final int UP_AMOUNT = 1;

    public Camouflage() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET, 0, 0, AMOUNT);
    }

    public void onChoseThisOption() {
        for(int i = 0; i < this.magicNumber; i++) {
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new FakeSorePower(AbstractDungeon.player, 1), 1, true));
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public void upgradeCard() {
        upgradeMagicNumber(UP_AMOUNT);
    }

}
