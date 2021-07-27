package DoublePeace.cards.uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import DoublePeace.DPMod;
import DoublePeace.abstracts.ChaserCard;
import DoublePeace.characters.TheChaser;
import DoublePeace.powers.BleedingPower;

import java.util.Iterator;

import static DoublePeace.DPMod.makeCardPath;

public class InternalHemorrhage extends ChaserCard {

    public static final String ID = DPMod.makeID("Internal Hemorrhage");
    public static final String IMG = makeCardPath("InternalHemorrhage.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheChaser.Enums.COLOR_CHASER;

    private static final int COST = 2;
    private static final int WEAK = 6;
    private static final int UP_WEAK = 3;

    public InternalHemorrhage() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET, 0, 0, WEAK);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.flash();
            Iterator var3 = AbstractDungeon.getMonsters().monsters.iterator();

            while(var3.hasNext()) {
                AbstractMonster monster = (AbstractMonster)var3.next();
                if (!monster.isDeadOrEscaped()) {
                    this.addToBot(new ApplyPowerAction(monster, p, new BleedingPower(monster, p, this.magicNumber), this.magicNumber));
                    this.addToBot(new ApplyPowerAction(monster, p, new WeakPower(monster, 2, false), 2));
                }
            }
        }
    }

    @Override
    public void upgradeCard() {
        upgradeMagicNumber(UP_WEAK);
    }

}
