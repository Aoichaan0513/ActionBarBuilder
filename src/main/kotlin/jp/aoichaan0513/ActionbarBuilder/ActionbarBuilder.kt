package jp.aoichaan0513.ActionbarBuilder

import jp.aoichaan0513.Kotlin_Utils.toString

class ActionbarBuilder {

    var defaultSeparator: String = " / "

    private var list = mutableListOf<String>()
    val strings
        get() = list.toList()

    constructor(defaultSeparator: String = " / ") : this(defaultSeparator, mutableListOf())

    constructor(defaultSeparator: String = " / ", collection: Collection<String>) {
        this.defaultSeparator = defaultSeparator
        list = collection.filter { it.isNotBlank() }.toMutableList()
    }

    constructor(defaultSeparator: String = " / ", iterable: Iterable<String>) : this(
        defaultSeparator,
        iterable.filter { it.isNotBlank() })

    constructor(defaultSeparator: String = " / ", array: Array<out String>) : this(
        defaultSeparator,
        array.filter { it.isNotBlank() })

    fun separator(separator: String): ActionbarBuilder {
        defaultSeparator = separator
        return this
    }

    fun clear(): ActionbarBuilder {
        list.clear()
        return this
    }

    fun add(str: String): ActionbarBuilder {
        if (str.isBlank())
            list.add(str)
        return this
    }

    fun add(i: Int, str: String): ActionbarBuilder {
        if (str.isBlank())
            list.add(i, str)
        return this
    }

    fun addAll(collection: Collection<String>): ActionbarBuilder {
        list.addAll(collection.filter { it.isNotBlank() })
        return this
    }

    fun addAll(i: Int, collection: Collection<String>): ActionbarBuilder {
        list.addAll(i, collection.filter { it.isNotBlank() })
        return this
    }

    fun addAll(iterable: Iterable<String>): ActionbarBuilder {
        return addAll(iterable.filter { it.isNotBlank() })
    }

    fun addAll(i: Int, iterable: Iterable<String>): ActionbarBuilder {
        return addAll(i, iterable.filter { it.isNotBlank() })
    }

    fun addAll(array: Array<out String>): ActionbarBuilder {
        return addAll(array.filter { it.isNotBlank() })
    }

    fun addAll(i: Int, array: Array<out String>): ActionbarBuilder {
        return addAll(i, array.filter { it.isNotBlank() })
    }

    fun remove(str: String): ActionbarBuilder {
        list.remove(str)
        return this
    }

    fun remove(i: Int): ActionbarBuilder {
        list.removeAt(i)
        return this
    }

    fun build(separator: String = defaultSeparator): String {
        return list.toString({ it }, "", separator).trim()
    }
}