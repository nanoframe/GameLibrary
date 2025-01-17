package com.paperatus.core.scene

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.paperatus.core.Game
import com.paperatus.core.graph.NodeRemover
import com.paperatus.core.graph.NodeRenderer
import com.paperatus.core.graph.NodeTraversal
import com.paperatus.core.graph.NodeUpdater
import ktx.collections.GdxArray

/**
 * Contains game components for updating and rendering.
 */
abstract class ObjectScene(protected val game: Game) : Scene {
    val root = GameObject()

    protected val nodeTraversal = NodeTraversal()
    protected open val nodeUpdater = NodeUpdater()
    protected open val nodeRemover = NodeRemover()
    protected open val nodeRenderer = NodeRenderer(game)

    override fun update(delta: Float) {
        nodeTraversal.traverse(nodeUpdater, root, delta)
    }

    /**
     * Begins the traversal of the first node for rendering
     *
     * Calling [render] will retrieve the [RenderComponent.sprite] of the object
     * and render the image onto the screen. The asset should be loaded in
     * [com.paperatus.Game.assets].
     *
     * The GameObject will be skipped if it does not contain a RenderComponent component
     *
     * Overriding this method allows for custom rendering.
     *
     * @param batch the SpriteBatch to render onto.
     */
    override fun render(batch: SpriteBatch) {
        nodeTraversal.traverse(nodeRemover, root)
        nodeTraversal.traverse(nodeRenderer, root, batch)
    }

    /**
     * Adds a GameObject to the root GameObject.
     *
     * @param gameObject the GameObject to add.
     */
    open fun addObject(gameObject: GameObject) = root.addChild(gameObject)

    /**
     * Removes the GameObject to the root GameObject.
     *
     * @param gameObject the GameObject to remove.
     * @param identity true will use == to compare, false will use .equals().
     */
    open fun removeObject(gameObject: GameObject, identity: Boolean = true) =
        root.removeChild(gameObject, identity)

    inline fun <T : Any> GdxArray<T>.operate(action: GdxArray<T>.() -> Unit) = action()
}
