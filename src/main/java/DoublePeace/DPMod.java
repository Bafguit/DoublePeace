package DoublePeace;

import DoublePeace.abstracts.ChaserCard;
import DoublePeace.abstracts.DPRelic;
import DoublePeace.characters.DoublePeace;
import basemod.*;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import DoublePeace.characters.TheChaser;
import DoublePeace.potions.AcidPotion;
import DoublePeace.powers.*;

import java.nio.charset.StandardCharsets;

import static basemod.BaseMod.addRelic;
import static basemod.BaseMod.addRelicToCustomPool;
import static com.megacrit.cardcrawl.cards.AbstractCard.*;

//TODO: DON'T MASS RENAME/REFACTOR
//TODO: DON'T MASS RENAME/REFACTOR
//TODO: DON'T MASS RENAME/REFACTOR
//TODO: DON'T MASS RENAME/REFACTOR
// Please don't just mass replace "theDefault" with "yourMod" everywhere.
// It'll be a bigger pain for you. You only need to replace it in 4 places.
// I comment those places below, under the place where you set your ID.

//TODO: FIRST THINGS FIRST: RENAME YOUR PACKAGE AND ID NAMES FIRST-THING!!!
// Right click the package (Open the project pane on the left. Folder with black dot on it. The name's at the very top) -> Refactor -> Rename, and name it whatever you wanna call your mod.
// Scroll down in this file. Change the ID from "theDefault:" to "yourModName:" or whatever your heart desires (don't use spaces). Dw, you'll see it.
// In the JSON strings (resources>localization>eng>[all them files] make sure they all go "yourModName:" rather than "theDefault", and change to "yourmodname" rather than "thedefault".
// You can ctrl+R to replace in 1 file, or ctrl+shift+r to mass replace in specific files/directories, and press alt+c to make the replace case sensitive (Be careful.).
// Start with the DefaultCommon cards - they are the most commented cards since I don't feel it's necessary to put identical comments on every card.
// After you sorta get the hang of how to make cards, check out the card template which will make your life easier

/*
 * With that out of the way:
 * Welcome to this super over-commented Slay the Spire modding base.
 * Use it to make your own mod of any type. - If you want to add any standard in-game content (character,
 * cards, relics), this is a good starting point.
 * It features 1 character with a minimal set of things: 1 card of each type, 1 debuff, couple of relics, etc.
 * If you're new to modding, you basically *need* the BaseMod wiki for whatever you wish to add
 * https://github.com/daviscook477/BaseMod/wiki - work your way through with this base.
 * Feel free to use this in any way you like, of course. MIT licence applies. Happy modding!
 *
 * And pls. Read the comments.
 */

@SpireInitializer
public class DPMod implements
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        EditCharactersSubscriber,
        PostInitializeSubscriber, SetUnlocksSubscriber{

    public static final Logger logger = LogManager.getLogger(DPMod.class.getName());

    private static String modID;

    public static String makeCardPath(String resourcePath) {
        return getModID() + "Resources/images/cards/" + resourcePath;
    }
    
    public static String makeRelicPath(String resourcePath) {
        return getModID() + "Resources/images/relics/" + resourcePath;
    }
    
    public static String makeImgPath(String resourcePath) {
        return getModID() + "Resources/images/" + resourcePath;
    }

    private static void setModID(String id) {
        modID = id;
    }

    public static String getModID() {
        return modID;
    }

    public static String repID(String id) {
        return id.replace(getModID() + ":", "");
    }

    //---------------------------???????????? ????????? ??????-----------------------//


    public DPMod() {
        BaseMod.subscribe(this);
        setModID("DP"); //TODO ?????? ????????? ?????????. pom.xml ?????? <ModID> ????????? ???????????? ?????? ?????? ???.
    }

    public static void initialize() {
        DPMod DoublePeace = new DPMod();
    }

    @Override
    public void receivePostInitialize() {
        receiveEditColor();
        receiveEditPowers();
    }

    //?????? ??????
    public void receiveEditPowers() {
        //????????? ???????????? ?????? ??????
        //???????????? ????????? ????????? ??????????????? ????????? ??? ??????. ???) power DP:Cum 999
        BaseMod.addPower(CumPower.class, CumPower.POWER_ID); //?????? ???????????? ????????? ????????? ???
    }

    //?????? ??? ??????
    public void receiveEditColor() {
        //????????? DoublePeace ??????
        BaseMod.addColor(DoublePeace.Enums.COLOR_DP, DoublePeace.COLOR, DoublePeace.COLOR, DoublePeace.COLOR,
                DoublePeace.COLOR, DoublePeace.COLOR, DoublePeace.COLOR, DoublePeace.COLOR,
                makeImgPath("512/" + DoublePeace.ID + "/bg_attack.png"),
                makeImgPath("512/" + DoublePeace.ID + "/bg_skill.png"),
                makeImgPath("512/" + DoublePeace.ID + "/bg_power.png"),
                makeImgPath("512/" + DoublePeace.ID + "/orb.png"),
                makeImgPath("1024/" + DoublePeace.ID + "/bg_attack.png"),
                makeImgPath("1024/" + DoublePeace.ID + "/bg_skill.png"),
                makeImgPath("1024/" + DoublePeace.ID + "/bg_power.png"),
                makeImgPath("1024/" + DoublePeace.ID + "/orb.png"),
                makeImgPath("512/" + DoublePeace.ID + "/orb_s.png"));
    }

    //????????? ??????
    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new DoublePeace(),
                DoublePeace.CHAR_BUTTON,
                DoublePeace.CHAR_BG,
                DoublePeace.Enums.DOUBLE_PEACE);
        receiveEditPotions();
    }

    //?????? ??????
    public void receiveEditPotions() {
        Color maroon = new Color(0.4F, 0, 0, 1).cpy();
        Color dark = new Color(50, 50, 255, 255).cpy();
        BaseMod.addPotion(AcidPotion.class, maroon, maroon, Color.YELLOW, AcidPotion.ID, TheChaser.Enums.THE_CHASER);
    }

    //?????? ??????
    @Override
    public void receiveEditRelics() {
        logger.info("Adding relics");
        new AutoAdd(modID)
                .packageFilter(DPRelic.class)
                .any(DPRelic.class, (info, relic) -> {
                    if (relic.color == null) {
                        addRelic(relic, RelicType.SHARED);
                    } else {
                        addRelicToCustomPool(relic, relic.color);
                    }
                    if (!info.seen) {
                        UnlockTracker.markRelicAsSeen(relic.relicId);
                    }
                });
        logger.info("Done adding relics!");
    }

    //?????? ??????
    @Override
    public void receiveEditCards() {
        new AutoAdd("TheChaser")
                .packageFilter(ChaserCard.class)
                .setDefaultSeen(true)
                .cards();
    }

    //?????? ??????
    public void receiveSetUnlocks() {
    }

    //????????? ??????
    @Override
    public void receiveEditStrings() {
        BaseMod.loadCustomStringsFile(CardStrings.class,
                getModID() + "Resources/localization/" + getLanguage() + "/cards.json");
        BaseMod.loadCustomStringsFile(PowerStrings.class,
                getModID() + "Resources/localization/" + getLanguage() + "/powers.json");
        BaseMod.loadCustomStringsFile(RelicStrings.class,
                getModID() + "Resources/localization/" + getLanguage() + "/relics.json");
        BaseMod.loadCustomStringsFile(PotionStrings.class,
                getModID() + "Resources/localization/" + getLanguage() + "/potions.json");
        BaseMod.loadCustomStringsFile(CharacterStrings.class,
                getModID() + "Resources/localization/" + getLanguage() + "/character.json");
    }

    //????????? ??????
    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String json = Gdx.files.internal(getModID() + "Resources/localization/" + getLanguage() + "/keywords.json").readString(String.valueOf(StandardCharsets.UTF_8));
        Keyword[] keywords = (Keyword[])gson.fromJson(json, Keyword[].class);
        if (keywords != null) {
            int var7 = keywords.length;
            for(int var8 = 0; var8 < var7; ++var8) {
                Keyword keyword = keywords[var8];
                BaseMod.addKeyword(getModID().toLowerCase(), keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);

            }
        }
    }

    //?????? ???????????? ??????. jpn, zhs, rus ??????...
    public String getLanguage() {
        switch (Settings.language.name()) {
            case "KOR":  //????????? ???????????? ????????????
                return "kor";
            default:  //?????? ????????? ????????? ?????????
                return "eng";
        }
    }

    public static String makeID(String idText) {
        return getModID() + ":" + idText;
    }
}
