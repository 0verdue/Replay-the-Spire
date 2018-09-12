package com.megacrit.cardcrawl.relics;

import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.core.*;

import java.util.ArrayList;

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.ui.panels.*;

import replayTheSpire.ReplayAbstractRelic;
import replayTheSpire.panelUI.ReplayIntSliderSetting;
import replayTheSpire.panelUI.ReplayRelicSetting;

public class EnergyBall extends ReplayAbstractRelic
{
    public static final String ID = "ReplayTheSpireMod:EnergyBall";
    private AbstractCard srcCard;
    private AbstractCard card;
	
    public static final ReplayIntSliderSetting SETTING_ENERGY = new ReplayIntSliderSetting("EnergyBall_Energy", "Energy Required", 5, 3, 6);
    public ArrayList<ReplayRelicSetting> BuildRelicSettings() {
  		ArrayList<ReplayRelicSetting> r = new ArrayList<ReplayRelicSetting>();
  		r.add(SETTING_ENERGY);
  		return r;
  	}
    public EnergyBall() {
        super(ID, "SSBB_Smash_Ball.png", RelicTier.SHOP, LandingSound.MAGICAL);
    }
    
    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + SETTING_ENERGY.value + this.DESCRIPTIONS[1];
    }
	
    @Override
    public void onPlayerEndTurn() {
    	if (EnergyPanel.totalCount > 0 && this.counter < SETTING_ENERGY.value) {
    		this.flash();
    		this.counter += EnergyPanel.totalCount;
    		if (this.counter >= SETTING_ENERGY.value) {
    			this.counter = SETTING_ENERGY.value;
    			this.card = this.srcCard.makeCopy();
    			this.card.freeToPlayOnce = true;
    			this.card.retain = true;
    			AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        		AbstractDungeon.actionManager.addToTop(new MakeTempCardInHandAction(this.card));
    		}
    	}
    	if (this.card != null) {
    		this.card.retain = true;
    	}
    }
    
    @Override
    public AbstractRelic makeCopy() {
        return new EnergyBall();
    }
}