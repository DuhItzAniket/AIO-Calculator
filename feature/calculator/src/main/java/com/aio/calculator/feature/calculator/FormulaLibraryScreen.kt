package com.aio.calculator.feature.calculator

import androidx.compose.foundation.clickable.clickable
import androidx.foundation.layout.Arrangement
import androidx.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aio.calculator.core.formula.FormulaCategory
import com.aio.calculator.core.formula.FormulaDefinition
import com.aio.calculator.core.formula.FormulaLibrary

/**
 * Formula Library Screen - Browse and use mathematical formulas
 */
@Composable
fun FormulaLibraryScreen(
    onBack: () -> Unit,
    selectedCategory: FormulaCategory?,
    onCategorySelected: (FormulaCategory) -> Unit,
    onToolSelected: (String) -> Unit
) {
    val calcColors = MaterialTheme.colorScheme
    val allCategories = FormulaCategory.allCategories()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(16.dp)
    ) {
        // Category toolbar
        ChipGroup(
            values = allCategories.map { it.displayName },
            selected = selectedCategory?.id ?: "",
            onSelectionChanged = { selectedCategory?.let { onCategorySelected(it) } }?,
            modifier = Modifier.fillMaxWidth(),
            divider = true,
            colors = ChipGroupColors(
                defaultColor = calcColors.surface,
                selected = calcColors.primary,
                selectedContent = calcColors.onPrimary
            )
        )

        // Formulas grid
        if (selectedCategory == null) {
            // Show all formulas
            FormulaGrid(formulas = FormulaLibrary.allFormulas, onToolSelected = onToolSelected)
        } else {
            // Show formulas in selected category
            val categoryFormulas = FormulaLibrary.getFormulas(selectedCategory!!)
            FormulaGrid(formulas = categoryFormulas, onToolSelected = onToolSelected)
        }
    }
}

/** Formula grid composable */
@Composable
fun FormulaGrid(
    formulas: List<FormulaDefinition>,
    onToolSelected: (String) -> Unit
) {
    if (tools.isEmpty()) {
        EmptyState(onBack = { }) {
            Text(text = "No formulas in this category")
        }
    } else {
        // Calculate grid columns based on screen width
        val columnCount = if (androidx.compose.ui.platform.LocalContext.current.resources.configuration.screenWidthdpx > 600) {
            3 // Tablet: 3 columns
        } else {
            2 // Phone: 2 columns
        }

        androidx.compose.foundation.layout
            .padding(Modifier.padding(16.dp)))} {
            androidx.compose.foundation.layout
                .padding(Modifier.padding(16.dp)))}
                        }}
                            }}
                                }}
                                    }}
                                        }}
                                            }}
                                                }}
                                                    }}
                                                        }}
                                                                }}
                                                                    }}
                                                                        }}
                                                                            }}
                                                                                }}
                                                                                    }}
                                                                                        }}
                                                                                            }}
                                                                                                }}
                                                                                                        }}
                                                                                                            }}
                                                                                                                }}
                                                                                                                    }}
                                                                                                                        }