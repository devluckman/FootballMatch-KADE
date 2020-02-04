import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
}

android {
    compileSdkVersion(Dependencies.Android.compileSdkVersion)
    defaultConfig {
        applicationId = Dependencies.Android.applicationId
        minSdkVersion(Dependencies.Android.minSdkVersion)
        targetSdkVersion(Dependencies.Android.targetSdkVersion)
        versionCode = Dependencies.Android.versionCode
        versionName = Dependencies.Android.versionName
        testInstrumentationRunner = Dependencies.Android.testInstrumentationRunner
        vectorDrawables.useSupportLibrary = Dependencies.Android.vectorDrawablesUseSupportLibrary
        buildConfigField(Dependencies.BuildConfig.typeConfig,
            Dependencies.BuildConfig.nameConfig,
            Dependencies.BuildConfig.valueConfig)


    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    androidExtensions {
        isExperimental = true
    }


    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    tasks.withType < KotlinCompile > ().configureEach {
        kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    dynamicFeatures = ModuleDependency.getDynamicFeatureModules().toMutableSet()

}


dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    api(Dependencies.Kotlin.stdlib_jdk7)
    implementation(Dependencies.Kotlin.coroutines)

    api(Dependencies.SupportLibs.appCompat)
    api(Dependencies.SupportLibs.constraintLayout)
    implementation(Dependencies.SupportLibs.legacy)
    implementation(Dependencies.SupportLibs.vectorDrawable)
    implementation(Dependencies.SupportLibs.lifecycle)
    implementation(Dependencies.SupportLibs.recyclerview)
    implementation(Dependencies.SupportLibs.viewpager2)
    implementation(Dependencies.SupportLibs.navigationFragment)
    implementation(Dependencies.SupportLibs.navigationUi)

    //Denpedency Testing
    testImplementation(Dependencies.TestLibs.mockitoCore)
    testImplementation(Dependencies.TestLibs.mockitoInline)
    testImplementation(Dependencies.TestLibs.junit)
    androidTestImplementation(Dependencies.TestLibs.runner)
    androidTestImplementation(Dependencies.TestLibs.rules)
    androidTestImplementation(Dependencies.TestLibs.extJunit)
    androidTestImplementation(Dependencies.TestLibs.espresso)
    androidTestImplementation(Dependencies.TestLibs.espressoContrib)
    androidTestImplementation(Dependencies.TestLibs.espressoContrib) {
        exclude(group = "androidx", module = "appcompat")
        exclude(group = "androidx", module = "recyclerview")
    }

    //Anko
    implementation(Dependencies.Anko.core)
    implementation(Dependencies.Anko.coroutines)
    implementation(Dependencies.Anko.sqlite)

    //View
    implementation(Dependencies.view.material)
    implementation(Dependencies.view.picasso)
    implementation(Dependencies.view.autoimageslider)

    //Gson
    implementation(Dependencies.data.gson)
    //Eventbus
    implementation(Dependencies.data.eventbus)
}
