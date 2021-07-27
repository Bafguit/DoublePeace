package DoublePeace.cards.temp;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import DoublePeace.DPMod;
import DoublePeace.abstracts.ChaserCard;
import DoublePeace.powers.BleedingPower;

import static DoublePeace.DPMod.makeCardPath;

public class BloodyWind extends ChaserCard {

    public static final String ID = DPMod.makeID("Bloody Wind");
    public static final String IMG = makeCardPath("BloodyWind.png");

    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = CardColor.COLORLESS;

    private static final int COST = 1;
    private static final int AMOUNT = 3;
    private static final int UP_AMOUNT = 1;

    public BloodyWind() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET, 0, 0, AMOUNT);
    }

    public void onChoseThisOption() {
        for(AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            this.addToBot(new ApplyPowerAction(mo, AbstractDungeon.player, new BleedingPower(mo, AbstractDungeon.player, this.magicNumber), this.magicNumber));
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
