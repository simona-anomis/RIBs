package com.badoo.ribs.core.routing.activator

import android.os.Parcelable
import com.badoo.ribs.core.Node
import com.badoo.ribs.core.routing.history.Routing

interface ChildActivator<C : Parcelable> {

    fun activate(routing: Routing<C>, child: Node<*>)

    fun deactivate(routing: Routing<C>, child: Node<*>)
}
