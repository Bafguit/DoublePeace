package DoublePeace.cards.common;

import DoublePeace.abstracts.DPCard;
import DoublePeace.characters.DoublePeace;
import DoublePeace.powers.OppaiPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static DoublePeace.DPMod.makeID;

public class Inflation extends DPCard {

    public static final String ID = makeID("Inflation"); //카드 아이디. 띄어쓰기는 안하는게 좋음
    private static final CardType TYPE = CardType.SKILL; // 카드 타입. ATTACK, SKILL, POWER, CURSE(저주), STATUS(상태이상)
    private static final CardColor COLOR = DoublePeace.Enums.COLOR_DP; //카드 색깔. COLORLESS(무색 카드), CURSE(저주)
    private static final CardRarity RARITY = CardRarity.COMMON; //희귀도. BASIC, SPECIAL, COMMON, UNCOMMON, RARE, CURSE
    private static final CardTarget TARGET = CardTarget.SELF_AND_ENEMY; //타겟. ENEMY, ALL_ENEMY, SELF, NONE, SELF_AND_ENEMY, ALL

    private static final int COST = 1;
    private static final int BLOCK = 5;
    private static final int UP_AMT = 3;

    public Inflation() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);

        defineBlock(BLOCK, UP_AMT);
    }

    //사용시 효과
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        addToBot(new GainBlockAction(p, this.block));

        // 적 전체에게 Oppai 부여
        for(AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if(!mo.isDeadOrEscaped()) {
                addToBot(new ApplyPowerAction(mo, p, new OppaiPower(mo, p)));
            }
        }

    }

    //강화 효과. 특별한거 없으면 비워놓으셈
    @Override
    public void upgradeCard() {

    }
}
