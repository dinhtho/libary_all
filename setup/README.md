

```gradle
dependencies {
    // ... other dependencies
    compile 'com.afollestad:sectioned-recyclerview:0.5.0'
}
```

<h1>Add build.gradle(project)</h1>
```groovy
classpath 'com.neenbedankt.gradle.plugins:android-apt:1.8'
```

<h1>Add build.gradle(module)</h1>
```groovy
apply plugin: 'com.android.application'
apply plugin: 'android-apt'
def AAVersion = '4.2.0'


dependencies {
    apt "org.androidannotations:androidannotations:$AAVersion"
    compile "org.androidannotations:androidannotations-api:$AAVersion"
}
```

```gradle
dependencies {
    // ... other dependencies
    compile 'com.afollestad:sectioned-recyclerview:0.5.0'
}
```

