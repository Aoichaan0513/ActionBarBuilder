package jp.aoichaan0513.ActionBarBuilder

import jp.aoichaan0513.Kotlin_Utils.joinToString
import java.util.concurrent.CopyOnWriteArrayList

class ActionBarBuilder {

    var defaultSeparator: String = " / "

    private var list = CopyOnWriteArrayList<String>()
    val strings
        get() = list.toList()

    constructor(defaultSeparator: String = " / ") : this(defaultSeparator, CopyOnWriteArrayList())

    constructor(defaultSeparator: String = " / ", collection: Collection<String>) {
        this.defaultSeparator = defaultSeparator
        list = CopyOnWriteArrayList(collection.filter { it.isNotBlank() }.toMutableList())
    }

    constructor(defaultSeparator: String = " / ", iterable: Iterable<String>) :
            this(defaultSeparator, iterable.filter { it.isNotBlank() })

    constructor(defaultSeparator: String = " / ", array: Array<out String>) :
            this(defaultSeparator, array.filter { it.isNotBlank() })

    fun separator(separator: String): ActionBarBuilder {
        defaultSeparator = separator
        return this
    }

    fun clear(): ActionBarBuilder {
        list.clear()
        return this
    }

    fun add(str: String): ActionBarBuilder {
        if (str.isNotBlank())
            list.add(str)
        return this
    }

    fun add(i: Int, str: String): ActionBarBuilder {
        if (str.isNotBlank())
            list.add(i, str)
        return this
    }

    fun addAll(collection: Collection<String>): ActionBarBuilder {
        list.addAll(collection.filter { it.isNotBlank() })
        return this
    }

    fun addAll(i: Int, collection: Collection<String>): ActionBarBuilder {
        list.addAll(i, collection.filter { it.isNotBlank() })
        return this
    }

    fun addAll(iterable: Iterable<String>) = addAll(iterable.filter { it.isNotBlank() })

    fun addAll(i: Int, iterable: Iterable<String>) = addAll(i, iterable.filter { it.isNotBlank() })

    fun addAll(array: Array<out String>) = addAll(array.filter { it.isNotBlank() })

    fun addAll(i: Int, array: Array<out String>) = addAll(i, array.filter { it.isNotBlank() })

    fun remove(str: String): ActionBarBuilder {
        list.remove(str)
        return this
    }

    fun remove(i: Int): ActionBarBuilder {
        list.removeAt(i)
        return this
    }

    fun build(separator: String = defaultSeparator) =
        list.filter { it.isNotBlank() }.joinToString("", separator) { it }.trim()
}