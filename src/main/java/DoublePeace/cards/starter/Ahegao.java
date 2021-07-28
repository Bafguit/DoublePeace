package DoublePeace.cards.starter;

import DoublePeace.abstracts.DPCard;
import DoublePeace.characters.DoublePeace;
import DoublePeace.powers.CumPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static DoublePeace.DPMod.makeID;

public class Ahegao extends DPCard {

    public static final String ID = makeID("Ahegao"); //카드 아이디. 띄어쓰기는 안하는게 좋음
    private static final CardType TYPE = CardType.SKILL; // 카드 타입. ATTACK, SKILL, POWER, CURSE(저주), STATUS(상태이상)
    private static final CardColor COLOR = DoublePeace.Enums.COLOR_DP; //카드 색깔. COLORLESS(무색 카드), CURSE(저주)
    private static final CardRarity RARITY = CardRarity.BASIC; //희귀도. BASIC, SPECIAL, COMMON, UNCOMMON, RARE, CURSE
    private static final CardTarget TARGET = CardTarget.SELF; //타겟. ENEMY, ALL_ENEMY, SELF, NONE, SELF_AND_ENEMY, ALL

    private static final int COST = 2; //비용. -1 은 코스트 없음(사용불가), -2 는 X 코스트
    private static final int BLOCK = 7; //방어도
    private static final int UP_BLOCK = 2; //강화하면 추가되는 방어도
    private static final int MAGIC = 2; //Cum 파워 (턴종시 방어도 얻고 중첩 1 감소)
    private static final int UP_MAGIC = 1; //강화하면 추가되는 파워 중첩

    public Ahegao() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);

        defineBlock(7, 2); // defineBlock(방어도, 강화시 추가 방어도);
        defineMagic(2, 1); // defineMagic(파워 중첩, 강화시 추가 중첩);
    }

    //사용시 효과
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        //방어도 획득
        addToBot(new GainBlockAction(p, this.block));

        //파워 획득
        addToBot(new ApplyPowerAction(p, p, new CumPower(p, p, this.magicNumber)));

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