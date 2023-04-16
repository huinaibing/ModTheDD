package modthedd.cards.blue;

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import modthedd.cards.BaseCard;
import modthedd.character.MyCharacter;
import modthedd.powers.YBBPower;
import modthedd.util.CardInfo;

import static modthedd.BasicMod.makeID;

public class xinzhibi extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "xinzhibi", //Card ID. Will be prefixed with mod id, so the final ID will be "modID:MyCard" with whatever your mod's ID is.
            3, //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
            CardType.POWER, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardTarget.SELF, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            CardRarity.UNCOMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            MyCharacter.Enums.CARD_COLOR //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
    );
    //This is theoretically optional, but you'll want it. The ID is how you refer to the card.
    //For example, to add a card to the starting deck, you need to use its ID.
    //With this, you can just use 'MyCard.ID'. Without it, you'd have to type out
    //'yourModID:MyCard' and make sure you don't make any mistakes, and you'd also have to update it
    //if you decided to change the card's ID.
    public static final String ID = makeID(cardInfo.baseId);

    //These will be used in the constructor. Technically you can just use the values directly,
    //but constants at the top of the file are easy to adjust.
    private static final int MG = 1;
    private static final int UPG_MG = 1;

    public xinzhibi() {
        super(cardInfo);
        setMagic(MG, UPG_MG);

    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, p, 8));
        this.addToBot(new ApplyPowerAction(p, p, new YBBPower(p, this.magicNumber), this.magicNumber));

    }
    @Override
    public AbstractCard makeCopy() {
        return new xinzhibi();
    }
}
