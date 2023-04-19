# Keep

a kotlin multiplatform library for caching serializable objects with simple keys

[badges]

## Setup: Gradle

```kotlin
dependencies {
    // if you need it in common code
    implementation("tz.co.asoft:keep-api:[version]")
    
    // if you want to cache into a file
    implementation("tz.co.asoft:keep-file:[version]")
    
    // if you want to cache things in the browser's local/session storage
    implementation("tz.co.asoft:keep-browser:[version]")
    
    // if you want to test or mock
    implementation("tz.co.asoft:keep-mock:[version]")
    
    // if you want to cache things in react native
    implementation("tz.co.asoft:keep-react-native:[version]")
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