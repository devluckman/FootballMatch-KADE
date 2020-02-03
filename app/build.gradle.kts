import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins{
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
        buildConfigField(Dependencies.buildConfig.typeConfig,
            Dependencies.buildConfig.nameConfig,
            Dependencies.buildConfig.valueConfig)


    }
    buildTypes {
        getByName("release"){
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    androidExtensions{
        isExperimental = true
    }


    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    dynamicFeatures = ModuleDependency.getDynamicFeatureModules().toMutableSet()

}


dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(Dependencies.kotlin.stdlib_jdk7)
    implementation(Dependencies.kotlin.coroutines)

    implementation(Dependencies.supportLibs.appCompat)
    implementation(Dependencies.supportLibs.constraintLayout)
    implementation(Dependencies.supportLibs.legacy)
    implementation(Dependencies.supportLibs.vectorDrawable)
    implementation(Dependencies.supportLibs.lifecycle)
    implementation(Dependencies.supportLibs.recyclerview)
    implementation(Dependencies.supportLibs.viewpager2)
    implementation(Dependencies.supportLibs.navigationFragment)
    implementation(Dependencies.supportLibs.navigationUi)

    //Denpedency Testing
    testImplementation(Dependencies.testLibs.mockitoCore)
    testImplementation(Dependencies.testLibs.mockitoInline)
    testImplementation(Dependencies.testLibs.junit)
    androidTestImplementation(Dependencies.testLibs.runner)
    androidTestImplementation(Dependencies.testLibs.rules)
    androidTestImplementation(Dependencies.testLibs.espresso)

    //anko
    implementation(Dependencies.anko.core)
    implementation(Dependencies.anko.coroutines)
    implementation(Dependencies.anko.sqlite)

    //View
    implementation(Dependencies.view.material)
    implementation(Dependencies.view.picasso)
    implementation(Dependencies.view.autoimageslider)


    //Gson
    implementation(Dependencies.data.gson)
    //Eventbus
    implementation(Dependencies.data.eventbus)

}
