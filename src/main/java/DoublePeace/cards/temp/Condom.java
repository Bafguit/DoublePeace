package DoublePeace.cards.temp;

import DoublePeace.abstracts.DPCard;
import DoublePeace.characters.DoublePeace;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static DoublePeace.DPMod.makeID;
import static com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import static com.megacrit.cardcrawl.cards.DamageInfo.*;

public class Condom extends DPCard {

    public static final String ID = makeID("Condom"); //카드 아이디. 띄어쓰기는 안하는게 좋음
    private static final CardType TYPE = CardType.ATTACK; // 카드 타입. ATTACK, SKILL, POWER, CURSE(저주), STATUS(상태이상)
    private static final CardColor COLOR = CardColor.COLORLESS; //카드 색깔. COLORLESS(무색 카드), CURSE(저주)
    private static final CardRarity RARITY = CardRarity.SPECIAL; //희귀도. BASIC, SPECIAL, COMMON, UNCOMMON, RARE, CURSE
    private static final CardTarget TARGET = CardTarget.SELF; //타겟. ENEMY, ALL_ENEMY, SELF, NONE, SELF_AND_ENEMY, ALL

    private static final int COST = 0; //비용. -1 은 코스트 없음(사용불가), -2 는 X 코스트
    private static final int HEAL = 2; //피해량

    public Condom() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);

        defineMagic(HEAL); //강화시 증가하지 않음

        this.exhaust = true; //소멸
    }

    //사용시 효과
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        addToBot(new HealAction(p, p, this.magicNumber));

    }

    //강화 효과. 특별한거 없으면 비워놓으셈
    @Override
    public void upgradeCard() {
        this.exhaust = false; //강화시 소멸이 사라짐
    }
}
