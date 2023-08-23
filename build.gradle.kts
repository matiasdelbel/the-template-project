plugins {
    @Suppress("DSL_SCOPE_VIOLATION")
    alias(libs.plugins.com.android.application) apply false

    @Suppress("DSL_SCOPE_VIOLATION")
    alias(libs.plugins.com.android.library) apply false

    @Suppress("DSL_SCOPE_VIOLATION")
    alias(libs.plugins.org.jetbrains.kotlin.android) apply false
    
    @Suppress("DSL_SCOPE_VIOLATION")
    alias(libs.plugins.android.hilt) apply false
}
