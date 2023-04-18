package modthedd.cards.blue;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;
import modthedd.cards.BaseCard;
import modthedd.character.MyCharacter;
import modthedd.util.CardInfo;



import static modthedd.BasicMod.makeID;

public class peijiu extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "peijiu", //Card ID. Will be prefixed with mod id, so the final ID will be "modID:MyCard" with whatever your mod's ID is.
            2, //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
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
    private static final int MG = 2;
    //    private static final int UPG_DRAW_CARD = 0;
    private static final int MG_UPG = -1;

    public peijiu() {
        super(cardInfo);
        setMagic(MG, MG_UPG);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int count = 0;

        for (AbstractMonster mon : AbstractDungeon.getMonsters().monsters) {
            if (!mon.isDeadOrEscaped()) {
                ++count;
                addToBot(new ApplyPowerAction(mon, p, new SlowPower(mon, 1), 1));
            }
        }
        addToBot(new ApplyPowerAction(p, p, new PoisonPower(p, p, this.magicNumber * count), this.magicNumber));
    }


    @Override
    public AbstractCard makeCopy() {
        return new peijiu();
    }
}
