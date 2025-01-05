package com.bookverse.packapps.automation.utils

import com.bookverse.packapps.automation.utils.constants.GeneralConstants
import net.serenitybdd.core.Serenity
import net.serenitybdd.screenplay.abilities.BrowseTheWeb
import net.serenitybdd.screenplay.actors.Cast
import net.serenitybdd.screenplay.actors.OnStage

object SerenitySession {

    @JvmStatic
    fun createActorForWeb(url: String, headLess: Boolean = false, actorName: String? = null) {
        startActor(Cast.whereEveryoneCan(
            BrowseTheWeb.with(WebApp.start(url, headLess))), actorName
        )
    }

    @JvmStatic
    fun <T> get(key: String): T {
        return Serenity.sessionVariableCalled(key)
    }

    @JvmStatic
    fun <T> set(key: String, value: T) {
        Serenity.setSessionVariable(key).to(value)
    }

    @JvmStatic
    fun delete(key: String) {
        Serenity.clearSessionVariable(key)
    }

    private fun startActor(cast: Cast, actorName: String?) {
        OnStage.setTheStage(cast)
        OnStage.theActor(actorName ?: GeneralConstants.ACTOR)
    }
}