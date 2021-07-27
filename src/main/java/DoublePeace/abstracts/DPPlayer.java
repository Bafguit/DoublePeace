package DoublePeace.abstracts;

import basemod.abstracts.CustomPlayer;
import basemod.animations.SpineAnimation;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.cutscenes.CutscenePanel;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;

import java.util.ArrayList;
import java.util.List;

import static DoublePeace.DPMod.*;
import static com.megacrit.cardcrawl.cards.AbstractCard.*;

public abstract class DPPlayer extends CustomPlayer {

    private static String[] makeOrbTex(String name) {
        String[] tex = {
                makeImgPath("char/" + name + "/orb/layer1.png"),
                makeImgPath("char/" + name + "/orb/layer2.png"),
                makeImgPath("char/" + name + "/orb/layer3.png"),
                makeImgPath("char/" + name + "/orb/layer4.png"),
                makeImgPath("char/" + name + "/orb/layer5.png"),
                makeImgPath("char/" + name + "/orb/layer6.png"),
                makeImgPath("char/" + name + "/orb/layer1d.png"),
                makeImgPath("char/" + name + "/orb/layer2d.png"),
                makeImgPath("char/" + name + "/orb/layer3d.png"),
                makeImgPath("char/" + name + "/orb/layer4d.png"),
                makeImgPath("char/" + name + "/orb/layer5d.png") };
        return tex;
    }

    private static String getOrbVfx(String name) {
        return makeImgPath("char/" + name + "/orb/vfx.png");
    }

    private static String getAtlas(String name) {
        return makeImgPath("char/" + name + "/animation/animation.atlas");
    }

    private static String getJson(String name) {
        return makeImgPath("char/" + name + "/animation/animation.json");
    }

    private static String ID;
    private static CharacterStrings characterStrings;
    private static String[] NAMES;
    private static String[] TEXT;
    private static BitmapFont FONT = FontHelper.energyNumFontRed;
    private static String[] CS_SOUND;
    private static boolean hasCutScene = false;

    protected static int ENERGY = 3;
    protected static int HP = 75;
    protected static int GOLD = 99;
    protected static int DRAW = 5;
    protected static int ORB = 0;

    private CardColor cardColor;
    private Color color;


    public DPPlayer(String id, PlayerClass playerClass, CardColor cardColor, Color color, int hp) {
        super(CardCrawlGame.playerName, playerClass, makeOrbTex(id), getOrbVfx(id),
                null, new SpineAnimation(getAtlas(id), getJson(id), 1.0F));

        ID = makeID(id);
        characterStrings = CardCrawlGame.languagePack.getCharacterString(ID);
        NAMES = characterStrings.NAMES;
        TEXT = characterStrings.TEXT;
        HP = hp;

        this.cardColor = cardColor;
        this.color = color;

        initializeClass(null, makeImgPath("char/" + id + "/shoulder.png"),
                makeImgPath("char/" + id + "/shoulder.png"), makeImgPath("char/" + id + "/corpse.png"),
                getLoadout(), -20.0F, -24.0F, 240.0F, 240.0F, new EnergyManager(ENERGY));
    }

    protected static void setEnergyNumFont(CardColor color) {
        switch (color) {
            case GREEN:
                FONT = FontHelper.energyNumFontGreen;
                break;
            case BLUE:
                FONT = FontHelper.energyNumFontBlue;
                break;
            case PURPLE:
                FONT = FontHelper.energyNumFontPurple;
                break;
            default:
                FONT = FontHelper.energyNumFontRed;
        }
    }

    protected static void setCutsceneSound(String s1, String s2, String s3) {
        if(s1 != null && s2 != null && s3 != null) {
            CS_SOUND = new String[]{s1, s2, s3};
            hasCutScene = true;
        }
    }

    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("BLOCK_BREAK", MathUtils.random(-0.2F, 0.2F));
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, true);
    }

    @Override
    public final CharSelectInfo getLoadout() {
        return new CharSelectInfo(NAMES[0], TEXT[0],
                HP, HP, ORB, GOLD, DRAW, this, getStartingRelics(),
                getStartingDeck(), false);
    }

    @Override
    public final String getTitle(PlayerClass playerClass) {
        return NAMES[1];
    }

    @Override
    public final CardColor getCardColor() {
        return this.cardColor;
    }

    @Override
    public final Color getCardRenderColor() {
        return this.color;
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return null;
    }

    @Override
    public final Color getCardTrailColor() {
        return this.color;
    }

    @Override
    public final int getAscensionMaxHPLoss() {
        return MathUtils.round((float) HP * 6.0F / 10.0F);
    }

    @Override
    public final BitmapFont getEnergyNumFont() {
        return FONT;
    }

    @Override
    public final String getCustomModeCharacterButtonSoundKey() {
        return "BLOCK_BREAK";
    }

    @Override
    public final String getLocalizedCharacterName() {
        return NAMES[0];
    }

    public final String getLeaderboardCharacterName() {
        return NAMES[2];
    }

    @Override
    public final String getSpireHeartText() {
        return TEXT[1];
    }

    @Override
    public final Color getSlashAttackColor() {
        return this.color;
    }

    public List<CutscenePanel> getCutscenePanels() {
        List<CutscenePanel> panels = new ArrayList();
        if(hasCutScene) {
            panels.add(new CutscenePanel(makeImgPath("scene/1.png"), CS_SOUND[0]));
            panels.add(new CutscenePanel(makeImgPath("scene/2.png"), CS_SOUND[1]));
            panels.add(new CutscenePanel(makeImgPath("scene/3.png"), CS_SOUND[2]));
        } else {
            panels.add(new CutscenePanel("images/scenes/ironclad1.png", "ATTACK_HEAVY"));
            panels.add(new CutscenePanel("images/scenes/ironclad2.png"));
            panels.add(new CutscenePanel("images/scenes/ironclad3.png"));
        }
        return panels;
    }

    @Override
    public final String getVampireText() {
        return TEXT[2];
    }
}
