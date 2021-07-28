package DoublePeace.characters;

import DoublePeace.abstracts.DPPlayer;
import DoublePeace.cards.starter.Defend;
import DoublePeace.cards.starter.Strike;
import DoublePeace.relics.ShadowInNecklace;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import java.util.ArrayList;

import static DoublePeace.DPMod.*;

public class DoublePeace extends DPPlayer {


    //TODO ============아주 중요함!!============
    public static class Enums {
        @SpireEnum
        public static AbstractPlayer.PlayerClass DOUBLE_PEACE; //캐릭터의 클래스임. 원하는 이름으로 바꾸셈. 보통 THE_뭐시기 이런식으로 씀
        @SpireEnum
        public static AbstractCard.CardColor COLOR_DP; //카드 종류임. 알기 쉽게 COLOR_뭐시기 이렇게 짓는걸 추천
    }

    public static class LibraryEnum {
        @SpireEnum
        public static CardLibrary.LibraryType COLOR_DP; //이건 카드 종류 이름이랑 똑같이 해놓으셈
    }

    public static final String ID = "DoublePeace"; //캐릭터 아이디임 원하는대로 바꾸셈
    private static final PlayerClass CLASS = Enums.DOUBLE_PEACE; //위에 캐릭터 클래스 그대로 쓰셈
    private static final AbstractCard.CardColor CARD_COLOR = Enums.COLOR_DP; // 위에 카드 종류 그대로 쓰셈

    public static final Color COLOR = new Color(255, 0, 0, 1); // 캐릭터의 색깔임. 여기에서 골라-> https://rgbacolorpicker.com/

    //TODO ====================================

    public static final String CHAR_BUTTON = makeImgPath("char/" + ID + "/select/button.png");
    public static final String CHAR_BG = makeImgPath("char/" + ID + "/select/bg.png");

    public DoublePeace() {
        super(ID, CLASS, CARD_COLOR, COLOR, HP);

        //에너지 숫자의 색을 바꿀 수 있음. 기본은 RED인데 안바꿀거면 지워도 됨.
        //사용 가능한 색: RED, GREEN, BLUE, PURPLE
        setEnergyNumFont(AbstractCard.CardColor.BLUE);

        //컷씬 추가. 안만들었으면 그냥 지우셈
        //각 장면에서 나오는 소리 설정하는거임.
        setCutsceneSound("ATTACK_DAGGER_5", "TURN_EFFECT", "CEILING_DUST_3");
    }

    //기본 덱
    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> deck = new ArrayList<>();

        //여기에 원하는 카드 추가하면 됨
        deck.add(Strike.ID);
        deck.add(Defend.ID);

        return deck;
    }

    //시작 유물
    @Override
    public ArrayList<String> getStartingRelics() {
        ArrayList<String> relics = new ArrayList<>();

        relics.add(ShadowInNecklace.ID);
        UnlockTracker.markRelicAsSeen(ShadowInNecklace.ID);

        return relics;
    }

    //캐릭터 선택할때 나오는 소리랑 효과. 안바꿀거면 지워도 됨 =================
    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("BLOCK_BREAK", MathUtils.random(-0.2F, 0.2F));
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, true);
    }
    //================================================================

    //심장 만났을때 공격하는 이펙트
    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{
                AbstractGameAction.AttackEffect.SLASH_HORIZONTAL,
                AbstractGameAction.AttackEffect.SLASH_VERTICAL,
                AbstractGameAction.AttackEffect.SLASH_DIAGONAL,
                AbstractGameAction.AttackEffect.SLASH_HEAVY }; //배열 안에 원하는 이펙트를 추가하면 됨
    }

    @Override
    public AbstractPlayer newInstance() {
        return new DoublePeace();
    }
}
