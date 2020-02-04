private const val androidGradleVersion = "3.5.3"
private const val kotlinVersion = "1.3.61"
private const val coroutinesVersion = "1.3.2"

//support libs
private const val appcompatVersion = "1.1.0"
private const val constraintLayoutVersion = "1.1.3"
private const val legacySupportVersion = "1.0.0"
private const val vectorDrawableVersion = "1.1.0"

//UI
private const val recyclerviewVersion = "1.1.0"
private const val viewpager2Version = "1.0.0"
private const val navigationVersion = "2.2.0"
private const val lifecycleVersion = "2.2.0"

//test libs
private const val junitVersion = "4.12"
private const val extJunitVersion = "1.1.1"
private const val runnerVersion = "1.2.0"
private const val rulesVersion = "1.2.0"
private const val espressoVersion = "3.2.0"
private const val mockitoVersion = "3.0.0"

//Anko
private const val ankoVersion = "0.10.8"

//View
private const val materialVersion = "1.2.0-alpha04"
private const val picassoVersion = "2.71828"
private const val sliderVersion = "1.3.2-appcompat"

//Data
private const val gsonVersion = "2.8.6"
private const val eventbusVersion = "3.1.1"

object Dependencies {
    object Android {
        val minSdkVersion = 15
        val targetSdkVersion = 29
        val compileSdkVersion = 29
        val applicationId = "com.man.hellosport"
        val versionCode = 1
        val versionName = "0.1"
        val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        val vectorDrawablesUseSupportLibrary = true
    }

    object GradleProject{
        val androidGradle = "com.android.tools.build:gradle:$androidGradleVersion"
        val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    }

    object BuildConfig {
        val typeConfig = "String"
        val nameConfig = "BASE_URL"
        val valueConfig = "\"https://www.thesportsdb.com/api/v1/json/1/\""
    }

    object Kotlin {
        val stdlib_jdk7 = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion"
        val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion"
    }

    object SupportLibs {
        val appCompat = "androidx.appcompat:appcompat:$appcompatVersion"
        val constraintLayout = "androidx.constraintlayout:constraintlayout:$constraintLayoutVersion"
        val legacy = "androidx.legacy:legacy-support-v4:$legacySupportVersion"
        val vectorDrawable = "androidx.vectordrawable:vectordrawable:$vectorDrawableVersion"
        //UI
        val recyclerview = "androidx.recyclerview:recyclerview:$recyclerviewVersion"
        val viewpager2 = "androidx.viewpager2:viewpager2:$viewpager2Version"
        val navigationFragment = "androidx.navigation:navigation-fragment-ktx:$navigationVersion"
        val navigationUi = "androidx.navigation:navigation-ui-ktx:$navigationVersion"

        val lifecycle = "androidx.lifecycle:lifecycle-extensions:$lifecycleVersion"
    }

    object TestLibs {
        val junit = "junit:junit:$junitVersion"
        val extJunit = "androidx.test.ext:junit:$extJunitVersion"
        val runner = "androidx.test:runner:$runnerVersion"
        val rules = "androidx.test:rules:$rulesVersion"
        val espresso = "androidx.test.espresso:espresso-core:$espressoVersion"
        val espressoContrib = "androidx.test.espresso:espresso-contrib:$espressoVersion"
        val mockitoCore = "org.mockito:mockito-core:$mockitoVersion"
        val mockitoInline = "org.mockito:mockito-inline:$mockitoVersion"
    }

    object Anko {
        val core = "org.jetbrains.anko:anko:$ankoVersion"
        val coroutines = "org.jetbrains.anko:anko-coroutines:$ankoVersion"
        val sqlite = "org.jetbrains.anko:anko-sqlite:$ankoVersion"
    }

    object view {
        val material = "com.google.android.material:material:$materialVersion"
        val picasso = "com.squareup.picasso:picasso:$picassoVersion"
        val autoimageslider = "com.github.smarteist:autoimageslider:$sliderVersion"
    }

    object data {
        val gson = "com.google.code.gson:gson:$gsonVersion"
        val eventbus = "org.greenrobot:eventbus:$eventbusVersion"
    }


}

