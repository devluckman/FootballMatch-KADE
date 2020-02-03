import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins{
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
}

//android {
//    kotlinOptions {
//        jvmTarget = "1.8"
//    }
//}

android {
    compileSdkVersion(29)
    defaultConfig {
        applicationId = "com.man.hellosport"
        minSdkVersion(15)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
        buildConfigField("String", "BASE_URL", "\"https://www.thesportsdb.com/api/v1/json/1/\"")


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

}


dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.61")
    implementation("androidx.appcompat:appcompat:1.1.0")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.vectordrawable:vectordrawable:1.1.0")

    //Denpedency Testing
    testImplementation("junit:junit:4.12")
    androidTestImplementation("androidx.test:runner:1.2.0")
    androidTestImplementation("androidx.test:rules:1.2.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")

    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")

    //UI
    implementation("androidx.recyclerview:recyclerview:1.1.0")
    implementation("androidx.viewpager2:viewpager2:1.0.0")
    implementation("com.google.android.material:material:1.2.0-alpha04")

    //Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.2.0")
    implementation("androidx.navigation:navigation-ui-ktx:2.2.0")

    //Kotlin Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.2")


    //Mockito
    testImplementation("org.mockito:mockito-core:3.0.0")
    testImplementation("org.mockito:mockito-inline:3.0.0")


    //anko
    implementation("org.jetbrains.anko:anko:0.10.8")
    implementation("org.jetbrains.anko:anko-coroutines:0.10.8")
    implementation("org.jetbrains.anko:anko-sqlite:0.10.8")

    //Picasso
    implementation("com.squareup.picasso:picasso:2.71828")

    //Gson
    implementation("com.google.code.gson:gson:2.8.6")

    // Slider image
    implementation("com.github.smarteist:autoimageslider:1.3.2-appcompat")

    //Eventbus
    implementation("org.greenrobot:eventbus:3.1.1")

}
