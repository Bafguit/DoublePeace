package DoublePeace.cards.common;

import DoublePeace.abstracts.DPCard;
import DoublePeace.cards.temp.Condom;
import DoublePeace.characters.DoublePeace;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.red.Combust;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static DoublePeace.DPMod.makeID;

public class Casanova extends DPCard {

    public static final String ID = makeID("Casanova"); //카드 아이디. 띄어쓰기는 안하는게 좋음
    private static final CardType TYPE = CardType.SKILL; // 카드 타입. ATTACK, SKILL, POWER, CURSE(저주), STATUS(상태이상)
    private static final CardColor COLOR = DoublePeace.Enums.COLOR_DP; //카드 색깔. COLORLESS(무색 카드), CURSE(저주)
    private static final CardRarity RARITY = CardRarity.COMMON; //희귀도. BASIC, SPECIAL, COMMON, UNCOMMON, RARE, CURSE
    private static final CardTarget TARGET = CardTarget.NONE; //타겟. ENEMY, ALL_ENEMY, SELF, NONE, SELF_AND_ENEMY, ALL

    private static final int COST = 1; //비용. -1 은 코스트 없음(사용불가), -2 는 X 코스트
    private static final int DRAW = 2; //드로우
    private static final int UP_DRAW = 1; //강화하면 추가되는 드로우

    public Casanova() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);

        defineMagic(DRAW, UP_DRAW);
        addPreCard(new Condom()); //미리보기에 Condom 추가 (사일런트 검무 참고)
    }

    //사용시 효과
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        //손에 Condom 추가
        addToBot(new MakeTempCardInHandAction(new Condom(), this.magicNumber));

    }

    //강화 효과. 특별한거 없으면 비워놓으셈
    @Override
    public void upgradeCard() {

    }
}
