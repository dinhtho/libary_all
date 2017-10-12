
# Set up Annotations Android

## Add build.gradle(project)
```
classpath 'com.neenbedankt.gradle.plugins:android-apt:1.8'
```

## Add build.gradle(module)
```
apply plugin: 'com.android.application'
apply plugin: 'android-apt'
def AAVersion = '4.2.0'


dependencies {
    apt "org.androidannotations:androidannotations:$AAVersion"
    compile "org.androidannotations:androidannotations-api:$AAVersion"
}
```

