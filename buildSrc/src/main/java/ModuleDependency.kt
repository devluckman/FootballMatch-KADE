import kotlin.reflect.full.memberProperties

object ModuleDependency {
    const val APP = ":app"
    const val FEATURE_ALBUM = ":pattern_mvvm"

    fun getAllModules() = ModuleDependency::class.memberProperties
        .filter { it.isConst }
        .map { it.getter.call().toString() }
        .toSet()

    fun getDynamicFeatureModules() = getAllModules().toSet()
}

