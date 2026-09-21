/**
 * Performance optimization configuration for AIO Calculator.
 * Guidelines:
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

    /** ProGuard/R8 rules file */
    @get:Suppress("UNCHECKED_CAST")
    @get:Serializable
    const val proguardRules = "proguard-rules.pro"

    /** Baseline Profile configuration */
    data class BaselineProfile(
        val name: String,
        val description: String,
        /** Critical startup sequences to profile */
        val startupSequences: List<String>,
        /** Components that should be lazily loaded */
        val lazyLoadedComponents: List<String>,
        /** Components to avoid initializing on startup */
        val avoidOnStartup: List<String>
    )

    /** Default baseline profile for AIO Calculator */
    val defaultBaselineProfile = BaselineProfile(
        name = "AIO Calculator Default",
        description = "Critical user journeys for AIO Calculator",
        startupSequences = listOf(
            "app_launch → home_calculator_visible",
            "home_calculator → basic_operations",
            "basic_operations → first_calculation"
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

/**
 * Startup timing measurements
 */
object StartupTiming {

    @Volatile
    var appLaunchStart: Long = 0
    @Volatile
    var homeCalculatorVisible: Long = 0
    @Volatile
   firstCalculationStart: Long = 0

    fun recordAppLaunchStart() {
        appLaunchStart = System.currentTimeMillis()
    }

    fun recordHomeCalculatorVisible() {
        homeCalculatorVisible = System.currentTimeMillis()
        val total = homeCalculatorVisible - appLaunchStart
        // Log or emit analytics
        println("AIO Calculator: Home screen visible in ${total}ms")
    }

    fun recordFirstCalculation() {
        firstCalculationStart = System.currentTimeMillis()
        val calcTime = firstCalculationStart - homeCalculatorVisible
        println("AIO Calculator: First calculation in ${calcTime}ms")
    }
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

#endif // PERFORMANCE_OPTIMIZATION_GUIDELINES