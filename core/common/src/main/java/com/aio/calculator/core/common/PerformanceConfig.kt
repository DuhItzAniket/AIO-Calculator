/**
 * Performance optimization configuration for AIO Calculator.
 * Guidelines from AIOSPEC.md §26:
 * - Avoid unnecessary recompositions
 * - Blocking operations off main thread
 * - Expensive initialization on startup
 * - Loading every feature's heavy data at startup
 * - Unnecessary database queries
 * - Unnecessary network requests
 *
 * The home calculator should load before nonessential data.
 * Lazy-load feature-specific content where appropriate.
 */

object PerformanceConfig {

    /** Enable R8 minification and optimization for release builds */
    @get:Suppress("UNCHECKED_CAST")
    @get:Serializable
    const val enableR8 = true

    /** Enable resource shrinking */
    @get:Suppress("UNCHECKED_CAST")
    @get:Serializable
    const val shrinkResources = true

    /** Release flavor dimensions */
    @get:Suppress("UNCHECKED_CAST")
    @get:Serializable
    const val flavorDimensions = listOf("version")

    /** Dependencies to review before release */
    @get:Suppress("UNCHECKED_CAST")
    @get:Serializable
    val dependenciesToReview = listOf(
        "robolectric", // Only for tests
        // Remove if not needed
    )

    /** Minimal required dependencies */
    @get:Suppress("UNCHECKED_CAST")
    @get:Serializable
    val minimalRequiredDependencies = listOf(
        "androidx.core:core-ktx",
        "androidx.lifecycle:lifecycle-runtime-ktx",
        "androidx.compose.ui:ui",
        "androidx.compose.material3:material3",
        "androidx.navigation:navigation-compose",
        "androidx.room:room-runtime",
        "androidx.datastore:datastore-preferences",
        "org.jetbrains.kotlinx:kotlinx-coroutines-core",
        "net.objecthunter.exp4j:exp4j",
    )

    /** Target release APK size (in MB) */
    @get:Suppress("UNCHECKED_CAST")
    @get:Serializable
    val targetReleaseSizeMb = 50

    /** Maximum allowed release APK size (in MB) */
    @get:Suppress("UNCHECKED_CAST")
    @get:Serializable
    val maxAllowedReleaseSizeMb = 75

    /** Android App Bundle enabled */
    @get:Suppress("UNCHECKED_CAST")
    @get:Serializable
    const val useAndroidAppBundle = true

    /** Baseline Profile configuration */
    data class BaselineProfile(
        val name: String,
        val description: String,
        val startupSequences: List<String>,
        val lazyLoadedComponents: List<String>,
        val avoidOnStartup: List<String>
    )

    /** Default baseline profile for AIO Calculator */
    val defaultBaselineProfile = BaselineProfile(
        name = "AIO Calculator Default",
        description = "Critical user journeys for AIO Calculator",
        startupSequences = listOf(
            "app_launch → home_calculator_visible",
            "home_calculator → first_calculation",
            "first_calculation → result_display"
        ),
        lazyLoadedComponents = listOf(
            "algebra_tools",
            "statistics_tools",
            "geometry_tools",
            "physics_tools",
            "chemistry_tools",
            "electronics_tools",
            "computer_science_tools",
            "converters_tools",
            "finance_tools",
            "health_tools",
            "datetime_tools",
            "everyday_tools",
            "shopping_tools",
            "formula_library",
            "constants_library"
        ),
        avoidOnStartup = listOf(
            "full_database_sync",
            "all_currency_rates",
            "all_formula_data",
            "all_constants_data"
        )
    )

    /** Components that should be lazy-loaded */
    val lazyComponents = listOf(
        "algebra",
        "statistics",
        "geometry",
        "trigonometry",
        "calculus",
        "physics",
        "chemistry",
        "electronics",
        "computer_science",
        "converters",
        "finance",
        "health",
        "datetime",
        "everyday",
        "shopping",
        "formula_library",
        "constants_library"
    )

    /** Components that can initialize on startup */
    val startupComponents = listOf(
        "basic_calculator",
        "scientific_toggle",
        "tool_registry",
        "search",
        "history_favorites_recent",
        "settings"
    )

    /** Avoid DB queries on startup */
    val avoidStartupQueries = listOf(
        "load_all_history",
        "load_all_favorites",
        "load_all_saved",
        "sync_all_recent"
    )

    /** Network requests to avoid on startup */
    val avoidStartupNetwork = listOf(
        "fetch_all_exchange_rates",
        "download_formula_library",
        "download_constants_library"
    )
}

/** Inspect recomposition performance - do not micro-optimize before profiling */
fun inspectRecompositionPerformance() {
    // This function should be called after profiling with Perfetto or Profiler
    // Common patterns to check:
    // - Composable functions with remember that could be hoisted
    // - Large remember blocks that could be split
    // - Unnecessary @Composable calls in tree
    // - Frequency of composition vs. user interaction
    
    // TODO: After profiling, address:
    // 1. Hoist stable objects out of composition
    // 2. Use snapshot state for immutable data
    // 3. Remove unnecessary @Composable from pure functions
    // 4. Lazy load lists with items LazyColumn
}