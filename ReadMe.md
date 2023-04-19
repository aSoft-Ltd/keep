# Keep

a kotlin multiplatform library for caching serializable objects with simple keys

![Maven](https://img.shields.io/maven-central/v/tz.co.asoft/keep/2.0.13?style=for-the-badge)
![Kotlin](https://img.shields.io/badge/kotlin-multiplatform-blue?style=for-the-badge&logo=kotlin&logoColor=white)
![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=&logoColor=white)
![Swift](https://img.shields.io/badge/swift-F54A2A?style=for-the-badge&logo=swift&logoColor=white)
![iOS](https://img.shields.io/badge/iOS-000000?style=for-the-badge&logo=ios&logoColor=white)
![JavaScript](https://img.shields.io/badge/javascript-%23323330.svg?style=for-the-badge&logo=javascript&logoColor=%23F7DF1E)
![TypeScript](https://img.shields.io/badge/typescript-%23007ACC.svg?style=for-the-badge&logo=typescript&logoColor=white)

## Setup: Gradle

```kotlin
dependencies {
    // if you need it in common code
    implementation("tz.co.asoft:keep-api:2.0.13")
    
    // if you want to cache into a file
    implementation("tz.co.asoft:keep-file:2.0.13")
    
    // if you want to cache things in the browser's local/session storage
    implementation("tz.co.asoft:keep-browser:2.0.13")
    
    // if you want to test or mock
    implementation("tz.co.asoft:keep-mock:2.0.13")
    
    // if you want to cache things in react native
    implementation("tz.co.asoft:keep-react-native:2.0.13")
}
```

## Api Reference
The full api reference of kevlar can be found at [https://asoft-ltd.github.io/kevlar](https://asoft-ltd.github.io/kevlar)

## Support

There are multiple ways you can support this project

### Star It

If you found it useful, just give it a star

### Contribute

You can help by submitting pull request to available open tickets on the issues section

### Report Issues

This makes it easier to catch bugs and offer enhancements required

## Credits

- [andylamax](https://github.com/andylamax) - The author