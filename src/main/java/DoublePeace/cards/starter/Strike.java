package DoublePeace.cards.starter;

import DoublePeace.abstracts.DPCard;
import DoublePeace.characters.DoublePeace;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static com.megacrit.cardcrawl.actions.AbstractGameAction.*;
import static DoublePeace.DPMod.*;

public class Strike extends DPCard {

    //TODO 수정할 부분 =======================
    public static final String ID = makeID("Strike"); //카드 아이디
    private static final CardType TYPE = CardType.ATTACK; // 카드 타입. ATTACK, SKILL, POWER, CURSE(저주), STATUS(상태이상)
    private static final CardColor COLOR = DoublePeace.Enums.COLOR_DP; //카드 색깔. COLORLESS(무색 카드), CURSE(저주)
    private static final CardRarity RARITY = CardRarity.BASIC; //희귀도. BASIC, SPECIAL, COMMON, UNCOMMON, RARE, CURSE
    private static final CardTarget TARGET = CardTarget.ENEMY; //타겟. ENEMY, ALL_ENEMY, SELF, NONE, SELF_AND_ENEMY, ALL
    //TODO =================================

    private static final int COST = 1; //비용. -1 은 코스트 없음(사용불가), -2 는 X 코스트
    private static final int DAMAGE = 6; //피해량
    private static final int UP_DAMAGE = 3; //강화하면 추가되는 피해량

    public Strike() {
        super(repID(ID), COST, TYPE, COLOR, RARITY, TARGET);

        //피해량 정의
        defineDamage(6, 3); // defineDamage(피해량, 강화시 추가 피해량);
        //이렇게 하면 따로 강화하는 코드 작성할 필요 없음. 강화시 증가하는 값이 아니라면 defineDamage(6) 이렇게만 쓰면 됨.
        //방어도, 변수는 defineBlock, defineMagic 이 두개를 쓰면 됨.
        //강화시 코스트 변화는 defineUpCost(X) 이렇게 씀. "X만큼 증가/감소"가 아니고 X로 바뀌는거임. 조심!
    }

    //사용시 효과
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        //피해량만큼 공격
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage), AttackEffect.SLASH_HORIZONTAL));

        /* ===========================================

        슬더스의 모든 행동은 액션 리스트를 통해 실행됨.
        액션 리스트는 순서대로 액션을 실행하는데,
        이 리스트에 액션을 추가해주는 함수가 바로 addToBot, addToTop 이 둘임.
        addToBot 은 액션 리스트의 맨 뒤에 액션을 추가함. -> 앞선 액션 끝나면 순차적으로 실행
        반대로 addToTop 은 리스트 맨 앞에 액션을 추가함. -> 순서 무시하고 제일 먼저 실행
        그래서 일반적으로 addToBot 이 쓰이고,
        addToTop 은 특별한 상황 아니면 거의 쓰이지 않음.
        잘 구분해서 필요에 따라 사용하길 바람.

        =========================================== */

    }

    //강화 효과. 특별한거 없으면 비워놓으셈
    @Override
    public void upgradeCard() {

    }
}
