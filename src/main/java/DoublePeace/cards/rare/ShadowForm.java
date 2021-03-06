package DoublePeace.cards.rare;

import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import DoublePeace.DPMod;
import DoublePeace.abstracts.ChaserCard;
import DoublePeace.characters.TheChaser;
import DoublePeace.powers.ShadowFormPower;

import static DoublePeace.DPMod.makeCardPath;

public class ShadowForm extends ChaserCard {

    public static final String ID = DPMod.makeID("Shadow Form");
    public static final String IMG = makeCardPath("ShadowForm.png");

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheChaser.Enums.COLOR_CHASER;

    private static final int COST = 3;
    private static final int MAGIC = 2;
    private static final int UP_MAGIC = 1;

    public ShadowForm() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET, 0, 0, MAGIC);
        this.tags.add(BaseModCardTags.FORM);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new ShadowFormPower(p, this.magicNumber), this.magicNumber));
    }

    @Override
    public void upgradeCard() {
        upgradeMagicNumber(UP_MAGIC);
    }
}