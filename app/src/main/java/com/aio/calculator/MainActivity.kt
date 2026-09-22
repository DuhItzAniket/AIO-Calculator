package com.aio.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aio.calculator.core.database.entity.CalculationHistoryEntity
import com.aio.calculator.core.database.entity.SavedCalculationEntity
import com.aio.calculator.core.common.ToolRegistry
import com.aio.calculator.core.common.ToolDefinition
import com.aio.calculator.core.common.AngleMode
import com.aio.calculator.core.design.AioTheme
import com.aio.calculator.core.math.SpecialistCalculations
import com.aio.calculator.core.navigation.NavRoutes
import com.aio.calculator.feature.calculator.BasicCalculatorScreen
import com.aio.calculator.feature.calculator.ScientificCalculatorScreen
import com.aio.calculator.history.AppHistoryViewModel
import com.aio.calculator.data.AppFavoritesViewModel
import com.aio.calculator.data.AppRecentViewModel
import com.aio.calculator.data.AppSavedViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val historyViewModel: AppHistoryViewModel by viewModels()
    private val favoritesViewModel: AppFavoritesViewModel by viewModels()
    private val savedViewModel: AppSavedViewModel by viewModels()
    private val recentViewModel: AppRecentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { AioCalculatorApp(historyViewModel, favoritesViewModel, savedViewModel, recentViewModel) }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AioCalculatorApp(
    historyViewModel: AppHistoryViewModel,
    favoritesViewModel: AppFavoritesViewModel,
    savedViewModel: AppSavedViewModel,
    recentViewModel: AppRecentViewModel,
) {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val history by historyViewModel.history.collectAsState()
    val favorites by favoritesViewModel.favorites.collectAsState()
    val saved by savedViewModel.saved.collectAsState()
    val recent by recentViewModel.recent.collectAsState()

    fun openDrawer() = scope.launch { drawerState.open() }
    fun navigate(route: String) {
        scope.launch {
            drawerState.close()
            navController.navigate(route)
        }
    }

    AioTheme {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet {
                    Text("AIO Calculator", modifier = Modifier.padding(20.dp))
                    NavigationDrawerItem(
                        label = { Text("Calculator") },
                        selected = false,
                        onClick = { navigate(NavRoutes.CALCULATOR) },
                    )
                    NavigationDrawerItem(
                        label = { Text("Scientific") },
                        selected = false,
                        onClick = { navigate(NavRoutes.toolRoute("scientific")) },
                    )
                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                    NavigationDrawerItem(
                        label = { Text("History") },
                        selected = false,
                        onClick = { navigate(NavRoutes.HISTORY) },
                    )
                    NavigationDrawerItem(
                        label = { Text("Favorites (${favorites.size})") },
                        selected = false,
                        onClick = { navigate(NavRoutes.FAVORITES) },
                    )
                    NavigationDrawerItem(
                        label = { Text("Recent (${recent.size})") },
                        selected = false,
                        onClick = { navigate(NavRoutes.RECENT) },
                    )
                    NavigationDrawerItem(
                        label = { Text("Saved (${saved.size})") },
                        selected = false,
                        onClick = { navigate(NavRoutes.SAVED) },
                    )
                    NavigationDrawerItem(
                        label = { Text("Tools") },
                        selected = false,
                        onClick = { navigate(NavRoutes.TOOLS) },
                    )
                }
            },
        ) {
            NavHost(navController = navController, startDestination = NavRoutes.CALCULATOR) {
                composable(NavRoutes.CALCULATOR) {
                    BasicCalculatorScreen(
                        onNavigateToScientific = { navigate(NavRoutes.toolRoute("scientific")) },
                        onOpenDrawer = { openDrawer() },
                        onOpenHistory = { navigate(NavRoutes.HISTORY) },
                        onCalculation = { expression, result ->
                            historyViewModel.record(expression, result)
                            recentViewModel.add("basic_calculator")
                        },
                    )
                }
                composable(NavRoutes.TOOL) {
                    val toolId = it.arguments?.getString("toolId")
                    if (toolId == "scientific") {
                        ScientificCalculatorScreen(
                            onNavigateToBasic = { navController.popBackStack() },
                            onOpenDrawer = { openDrawer() },
                            onOpenHistory = { navigate(NavRoutes.HISTORY) },
                            onCalculation = { expression, result ->
                                historyViewModel.record(expression, result)
                                recentViewModel.add("scientific_calculator")
                            },
                        )
                    } else {
                        ToolDetailScreen(ToolRegistry.getTool(toolId.orEmpty()), { openDrawer() }, favoritesViewModel::toggle)
                    }
                }
                composable(NavRoutes.HISTORY) {
                    HistoryScreen(
                        items = history,
                        onOpenDrawer = { openDrawer() },
                        onClear = historyViewModel::clear,
                        onSave = savedViewModel::save,
                    )
                }
                composable(NavRoutes.FAVORITES) {
                    ToolListScreen("Favorites", favorites, { openDrawer() }, favoritesViewModel::toggle)
                }
                composable(NavRoutes.RECENT) {
                    ToolListScreen("Recent", recent, { openDrawer() }, recentViewModel::add)
                }
                composable(NavRoutes.SAVED) {
                    SavedScreen(saved, { openDrawer() }, savedViewModel::delete)
                }
                composable(NavRoutes.TOOLS) {
                    ToolsScreen({ openDrawer() }) { toolId -> navigate(NavRoutes.toolRoute(toolId)) }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HistoryScreen(
    items: List<CalculationHistoryEntity>,
    onOpenDrawer: () -> Unit,
    onClear: () -> Unit,
    onSave: (CalculationHistoryEntity) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("History") },
                navigationIcon = { Button(onClick = onOpenDrawer) { Text("Menu") } },
                actions = { Button(onClick = onClear) { Text("Clear") } },
            )
        },
    ) { paddingValues ->
        if (items.isEmpty()) {
            Text("No calculations yet", modifier = Modifier.padding(paddingValues).padding(24.dp))
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize().padding(paddingValues).padding(horizontal = 16.dp)) {
                items(items, key = { it.id }) { item ->
                    Column(modifier = Modifier.padding(vertical = 12.dp)) {
                        Text(item.displayExpression)
                        Text("= ${item.result}")
                        Button(onClick = { onSave(item) }) { Text("Save") }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ToolListScreen(
    title: String,
    tools: List<ToolDefinition>,
    onOpenDrawer: () -> Unit,
    onToolAction: (String) -> Unit,
) {
    Scaffold(topBar = {
        TopAppBar(title = { Text(title) }, navigationIcon = { Button(onClick = onOpenDrawer) { Text("Menu") } })
    }) { paddingValues ->
        LazyColumn(modifier = Modifier.fillMaxSize().padding(paddingValues).padding(horizontal = 16.dp)) {
            items(tools, key = { it.id }) { tool ->
                Button(onClick = { onToolAction(tool.id) }, modifier = Modifier.padding(vertical = 6.dp)) {
                    Text("${tool.title}: ${tool.description}")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SavedScreen(
    items: List<SavedCalculationEntity>,
    onOpenDrawer: () -> Unit,
    onDelete: (Long) -> Unit,
) {
    Scaffold(topBar = {
        TopAppBar(title = { Text("Saved calculations") }, navigationIcon = { Button(onClick = onOpenDrawer) { Text("Menu") } })
    }) { paddingValues ->
        LazyColumn(modifier = Modifier.fillMaxSize().padding(paddingValues).padding(horizontal = 16.dp)) {
            items(items, key = { it.id }) { item ->
                Column(modifier = Modifier.padding(vertical = 12.dp)) {
                    Text(item.name)
                    Text("= ${item.result}")
                    Button(onClick = { onDelete(item.id) }) { Text("Delete") }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ToolsScreen(
    onOpenDrawer: () -> Unit,
    onOpenTool: (String) -> Unit,
) {
    var query by remember { mutableStateOf("") }
    val tools = ToolRegistry.search(query)
    Scaffold(topBar = {
        TopAppBar(title = { Text("All tools") }, navigationIcon = { Button(onClick = onOpenDrawer) { Text("Menu") } })
    }) { paddingValues ->
        Column(modifier = Modifier.fillMaxSize().padding(paddingValues).padding(horizontal = 16.dp)) {
            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                modifier = Modifier.padding(vertical = 12.dp),
                label = { Text("Search tools") },
                singleLine = true,
            )
            LazyColumn {
                items(tools, key = { it.id }) { tool ->
                    Button(onClick = { onOpenTool(tool.id) }, modifier = Modifier.padding(vertical = 4.dp)) {
                        Text("${tool.title} · ${tool.category.displayName}")
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ToolDetailScreen(
    tool: ToolDefinition?,
    onOpenDrawer: () -> Unit,
    onToggleFavorite: (String) -> Unit,
) {
    Scaffold(topBar = {
        TopAppBar(title = { Text(tool?.title ?: "Tool") }, navigationIcon = { Button(onClick = onOpenDrawer) { Text("Menu") } })
    }) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues).padding(24.dp)) {
            if (tool == null) {
                Text("This tool is not available yet.")
            } else {
                when (tool.id) {
                    "percentage" -> PercentageToolContent()
                    "statistics_mean", "statistics_median" -> StatisticsToolContent(tool.id)
                    "geometry_triangle" -> TriangleToolContent()
                    "trigonometry" -> TrigonometryToolContent()
                    "physics_speed" -> SpeedToolContent()
                    "chemistry_molarity" -> MolarityToolContent()
                    "electronics_ohms_law" -> OhmsLawToolContent()
                    "finance_emi" -> EmiToolContent()
                    "health_bmi" -> BmiToolContent()
                    else -> {
                        Text(tool.description)
                        Text("Category: ${tool.category.displayName}", modifier = Modifier.padding(top = 8.dp))
                    }
                }
                Button(onClick = { onToggleFavorite(tool.id) }, modifier = Modifier.padding(top = 16.dp)) {
                    Text("Toggle favorite")
                }
            }
        }
    }
}

@Composable
private fun PercentageToolContent() {
    var amount by remember { mutableStateOf("") }
    var percent by remember { mutableStateOf("") }
    var result by remember { mutableStateOf<Double?>(null) }
    Text("Percentage", style = androidx.compose.material3.MaterialTheme.typography.headlineSmall)
    NumberField("Amount", amount) { amount = it }
    NumberField("Percent", percent) { percent = it }
    Button(onClick = { result = amount.toDoubleOrNull()?.let { value -> percent.toDoubleOrNull()?.let { rate -> value * rate / 100.0 } } }) {
        Text("Calculate")
    }
    result?.let { Text("Result: ${formatToolNumber(it)}", modifier = Modifier.padding(top = 8.dp)) }
}

@Composable
private fun StatisticsToolContent(toolId: String) {
    var values by remember { mutableStateOf("") }
    var result by remember { mutableStateOf<Double?>(null) }
    Text(if (toolId == "statistics_mean") "Mean" else "Median", style = androidx.compose.material3.MaterialTheme.typography.headlineSmall)
    OutlinedTextField(
        value = values,
        onValueChange = { values = it },
        label = { Text("Values separated by commas") },
        modifier = Modifier.padding(top = 8.dp),
    )
    Button(onClick = {
        val numbers = values.split(",").mapNotNull { it.trim().toDoubleOrNull() }.sorted()
        result = if (numbers.isEmpty()) null else if (toolId == "statistics_mean") numbers.average() else {
            val middle = numbers.size / 2
            if (numbers.size % 2 == 1) numbers[middle] else (numbers[middle - 1] + numbers[middle]) / 2.0
        }
    }, modifier = Modifier.padding(top = 8.dp)) { Text("Calculate") }
    result?.let { Text("Result: ${formatToolNumber(it)}", modifier = Modifier.padding(top = 8.dp)) }
}

@Composable
private fun TriangleToolContent() {
    var base by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var result by remember { mutableStateOf<Double?>(null) }
    Text("Triangle area", style = androidx.compose.material3.MaterialTheme.typography.headlineSmall)
    NumberField("Base", base) { base = it }
    NumberField("Height", height) { height = it }
    Button(onClick = { result = base.toDoubleOrNull()?.let { b -> height.toDoubleOrNull()?.let { h -> b * h / 2.0 } } }) {
        Text("Calculate")
    }
    result?.let { Text("Area: ${formatToolNumber(it)}", modifier = Modifier.padding(top = 8.dp)) }
}

@Composable
private fun TrigonometryToolContent() {
    var angle by remember { mutableStateOf("") }
    var mode by remember { mutableStateOf(AngleMode.DEGREES) }
    var result by remember { mutableStateOf<Double?>(null) }
    Text("Sine", style = androidx.compose.material3.MaterialTheme.typography.headlineSmall)
    NumberField("Angle", angle) { angle = it }
    Button(onClick = { mode = if (mode == AngleMode.DEGREES) AngleMode.RADIANS else AngleMode.DEGREES }) {
        Text(mode.displayName)
    }
    Button(onClick = { result = angle.toDoubleOrNull()?.let { SpecialistCalculations.sine(it, mode) } }) {
        Text("Calculate sin")
    }
    result?.let { Text("Result: ${formatToolNumber(it)}", modifier = Modifier.padding(top = 8.dp)) }
}

@Composable
private fun SpeedToolContent() {
    var distance by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }
    var result by remember { mutableStateOf<Double?>(null) }
    Text("Speed", style = androidx.compose.material3.MaterialTheme.typography.headlineSmall)
    NumberField("Distance", distance) { distance = it }
    NumberField("Time", time) { time = it }
    Button(onClick = { result = runCatching { SpecialistCalculations.speed(distance.toDouble(), time.toDouble()) }.getOrNull() }) { Text("Calculate") }
    result?.let { Text("Speed: ${formatToolNumber(it)}", modifier = Modifier.padding(top = 8.dp)) }
}

@Composable
private fun OhmsLawToolContent() {
    var current by remember { mutableStateOf("") }
    var resistance by remember { mutableStateOf("") }
    var result by remember { mutableStateOf<Double?>(null) }
    Text("Ohm's law: V = I × R", style = androidx.compose.material3.MaterialTheme.typography.headlineSmall)
    NumberField("Current", current) { current = it }
    NumberField("Resistance", resistance) { resistance = it }
    Button(onClick = { result = current.toDoubleOrNull()?.let { i -> resistance.toDoubleOrNull()?.let { r -> SpecialistCalculations.ohmsVoltage(i, r) } } }) { Text("Calculate voltage") }
    result?.let { Text("Voltage: ${formatToolNumber(it)}", modifier = Modifier.padding(top = 8.dp)) }
}

@Composable
private fun MolarityToolContent() {
    var moles by remember { mutableStateOf("") }
    var liters by remember { mutableStateOf("") }
    var result by remember { mutableStateOf<Double?>(null) }
    Text("Molarity", style = androidx.compose.material3.MaterialTheme.typography.headlineSmall)
    NumberField("Moles of solute", moles) { moles = it }
    NumberField("Liters of solution", liters) { liters = it }
    Button(onClick = { result = runCatching { SpecialistCalculations.molarity(moles.toDouble(), liters.toDouble()) }.getOrNull() }) { Text("Calculate") }
    result?.let { Text("Molarity: ${formatToolNumber(it)} mol/L", modifier = Modifier.padding(top = 8.dp)) }
}

@Composable
private fun EmiToolContent() {
    var principal by remember { mutableStateOf("") }
    var rate by remember { mutableStateOf("") }
    var months by remember { mutableStateOf("") }
    var result by remember { mutableStateOf<Double?>(null) }
    Text("Monthly loan payment", style = androidx.compose.material3.MaterialTheme.typography.headlineSmall)
    NumberField("Principal", principal) { principal = it }
    NumberField("Annual interest %", rate) { rate = it }
    NumberField("Term in months", months) { months = it }
    Button(onClick = { result = runCatching { SpecialistCalculations.emi(principal.toDouble(), rate.toDouble(), months.toInt()) }.getOrNull() }) { Text("Calculate EMI") }
    result?.let { Text("Payment: ${formatToolNumber(it)}", modifier = Modifier.padding(top = 8.dp)) }
}

@Composable
private fun BmiToolContent() {
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var result by remember { mutableStateOf<Double?>(null) }
    Text("Body mass index", style = androidx.compose.material3.MaterialTheme.typography.headlineSmall)
    NumberField("Weight in kg", weight) { weight = it }
    NumberField("Height in meters", height) { height = it }
    Button(onClick = { result = runCatching { SpecialistCalculations.bmi(weight.toDouble(), height.toDouble()) }.getOrNull() }) { Text("Calculate BMI") }
    result?.let { Text("BMI: ${formatToolNumber(it)}", modifier = Modifier.padding(top = 8.dp)) }
}

@Composable
private fun NumberField(label: String, value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier.padding(top = 8.dp),
        singleLine = true,
    )
}

private fun formatToolNumber(value: Double): String =
    if (value == value.toLong().toDouble()) value.toLong().toString()
    else "%.6f".format(value).trimEnd('0').trimEnd('.')
