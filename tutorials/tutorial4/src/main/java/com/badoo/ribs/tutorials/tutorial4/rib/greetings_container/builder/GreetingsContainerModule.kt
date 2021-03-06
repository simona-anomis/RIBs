package com.badoo.ribs.tutorials.tutorial4.rib.greetings_container.builder

import com.badoo.ribs.android.text.Text
import com.badoo.ribs.core.Node
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.tutorials.tutorial4.R
import com.badoo.ribs.tutorials.tutorial4.rib.greetings_container.GreetingsContainerInteractor
import com.badoo.ribs.tutorials.tutorial4.rib.greetings_container.GreetingsContainerRouter
import com.badoo.ribs.tutorials.tutorial4.rib.hello_world.HelloWorld
import com.badoo.ribs.tutorials.tutorial4.rib.hello_world.builder.HelloWorldBuilder
import dagger.Provides
import io.reactivex.functions.Consumer

@dagger.Module
internal object GreetingsContainerModule {

    @GreetingsContainerScope
    @Provides
    @JvmStatic
    internal fun router(
        // pass component to child rib builders, or remove if there are none
        component: GreetingsContainerComponent,
        interactor: GreetingsContainerInteractor,
        buildParams: BuildParams<Nothing?>
    ): GreetingsContainerRouter =
        GreetingsContainerRouter(
            buildParams = buildParams,
            routingSource = interactor,
            helloWorldBuilder = HelloWorldBuilder(component)
        )

    @GreetingsContainerScope
    @Provides
    @JvmStatic
    internal fun interactor(
        buildParams: BuildParams<Nothing?>
    ): GreetingsContainerInteractor =
        GreetingsContainerInteractor(
            buildParams = buildParams
        )

    @GreetingsContainerScope
    @Provides
    @JvmStatic
    internal fun node(
        buildParams: BuildParams<Nothing?>,
        router: GreetingsContainerRouter,
        interactor: GreetingsContainerInteractor
    ) : Node<Nothing> = Node<Nothing>(
        buildParams = buildParams,
        viewFactory = null,
        plugins = listOf(interactor, router)
    )

    @GreetingsContainerScope
    @Provides
    @JvmStatic
    internal fun helloWorldConfig(): HelloWorld.Config =
        HelloWorld.Config(
            welcomeMessage = Text.Resource(
                R.string.hello_world_welcome_text
            )
        )

    @GreetingsContainerScope
    @Provides
    @JvmStatic
    internal fun helloWorldOutputConsumer(
        interactor: GreetingsContainerInteractor
    ) : Consumer<HelloWorld.Output> =
        TODO("Pass instance from interactor")
}
