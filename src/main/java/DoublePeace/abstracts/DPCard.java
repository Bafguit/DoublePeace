package DoublePeace.abstracts;

import DoublePeace.util.TextureLoader;
import basemod.ReflectionHacks;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import java.util.ArrayList;

import static DoublePeace.DPMod.*;

public abstract class DPCard extends CustomCard {

    public boolean isTempCard;
    public String upgradeDescription;
    public String[] extendedDescription;
    public String betaArtPath;

    private CardStrings cardStrings;
    protected String NORMAL_DESCRIPTION;
    protected String UPGRADE_DESCRIPTION;
    protected String[] EXTENDED_DESCRIPTION;

    protected int upDamage;
    protected int upBlock;
    protected int upMagic;
    protected int upCost;

    private float rotationTimer = 0;
    private int previewIndex;
    protected ArrayList<AbstractCard> cardToPreview = new ArrayList<>();

    public DPCard(String id, int cost,
                  CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, CardCrawlGame.languagePack.getCardStrings(id).NAME,
                makeImgPath("cards/" + repID(id) + ".png"), cost, CardCrawlGame.languagePack.getCardStrings(id).DESCRIPTION, type, color, rarity, target);
        cardStrings = CardCrawlGame.languagePack.getCardStrings(id);
        NORMAL_DESCRIPTION = cardStrings.DESCRIPTION;
        this.upgradeDescription = UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
        this.extendedDescription = EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
        this.isTempCard = false;
        this.upDamage = -100;
        this.upBlock = -100;
        this.upMagic = -100;
        this.upCost = -100;
    }

    public void resetAttributes() {
        super.resetAttributes();
    }

    public void displayUpgrades() {
        super.displayUpgrades();
    }

    protected void defineDamage(int dmg) {
        baseDamage = damage = dmg;
    }

    protected void defineBlock(int blc) {
        baseBlock = block = blc;
    }

    protected void defineMagic(int mgc) {
        baseMagicNumber = magicNumber = mgc;
    }

    protected void defineDamage(int dmg, int up) {
        defineDamage(dmg);
        upDamage = up;
    }

    protected void defineBlock(int blc, int up) {
        defineBlock(blc);
        upBlock = up;
    }

    protected void defineMagic(int mgc, int up) {
        defineMagic(mgc);
        upMagic = up;
    }

    protected void defineUpCost(int cost) {
        upCost = cost;
    }

    @Override
    public final void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeAttributes();
            upgradeCard();
            if(UPGRADE_DESCRIPTION != null) {
                this.rawDescription = UPGRADE_DESCRIPTION;
            }
            initializeDescription();
        }
    }

    private void upgradeAttributes() {
        if(upDamage != -100) upgradeDamage(upDamage);
        if(upBlock != -100) upgradeBlock(upBlock);
        if(upMagic != -100) upgradeMagicNumber(upMagic);
        if(upCost != -100) upgradeBaseCost(upCost);
    }

    protected void addPreCard(AbstractCard card) {
        this.cardToPreview.add(card);
    }

    protected void upPreCard() {
        for (AbstractCard q : cardToPreview) {
            q.upgrade();
        }
    }

    public void update() {
        super.update();
        if (!cardToPreview.isEmpty()) {
            if (hb.hovered) {
                if (rotationTimer <= 0F) {
                    rotationTimer = 1F;
                    cardsToPreview = cardToPreview.get(previewIndex);
                    if (previewIndex == cardToPreview.size() - 1) {
                        previewIndex = 0;
                    } else {
                        previewIndex++;
                    }
                } else {
                    rotationTimer -= Gdx.graphics.getDeltaTime();
                }
            }
        }
    }

    public static void loadJokeCardImage(AbstractCard card) {
        String img = makeImgPath("cards/beta/" + repID(card.cardID) + ".png");
        if (card instanceof DPCard) {
            ((DPCard) card).betaArtPath = img;
        }
        Texture cardTexture;
        cardTexture = TextureLoader.getTexture(img);
        cardTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        int tw = cardTexture.getWidth();
        int th = cardTexture.getHeight();
        TextureAtlas.AtlasRegion cardImg = new TextureAtlas.AtlasRegion(cardTexture, 0, 0, tw, th);
        ReflectionHacks.setPrivate(card, AbstractCard.class, "jokePortrait", cardImg);
    }

    @Override
    protected Texture getPortraitImage() {
        if (Settings.PLAYTESTER_ART_MODE || UnlockTracker.betaCardPref.getBoolean(this.cardID, false)) {
            if (this.textureImg == null) {
                return null;
            } else {
                if (betaArtPath != null) {
                    int endingIndex = betaArtPath.lastIndexOf(".");
                    String newPath = betaArtPath.substring(0, endingIndex) + "_p" + betaArtPath.substring(endingIndex);
                    System.out.println("Finding texture: " + newPath);

                    Texture portraitTexture;
                    try {
                        portraitTexture = ImageMaster.loadImage(newPath);
                    } catch (Exception var5) {
                        portraitTexture = null;
                    }

                    return portraitTexture;
                }
            }
        }
        return super.getPortraitImage();
    }

    public abstract void upgradeCard();

}