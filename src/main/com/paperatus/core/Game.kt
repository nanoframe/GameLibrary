package com.paperatus.core

import com.badlogic.gdx.Application
import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.Gdx
import com.paperatus.core.scene.SceneController

abstract class Game : ApplicationListener {

    val sceneController = SceneController()
    val assets = Assets()

    override fun create() {
        Gdx.app.logLevel = Application.LOG_DEBUG

        // Initialize the Scene
        sceneController.init()
    }

    override fun render() {
        sceneController.step()
    }

    override fun resume() = Unit

    override fun pause() = Unit

    override fun resize(width: Int, height: Int) = sceneController.resize(width, height)

    override fun dispose() {
        sceneController.dispose()
        assets.dispose()
    }
}
