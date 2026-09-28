package com.aio.calculator.feature.calculator

import androidx.compose.foundation.clickable.clickable
import androidx.foundation.layout.Arrangement
import androidx.foundation.layout.Column
import androidx.compose.material3.Chip
import androidx.compose.material3.ChipGroup
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.GridCells
import androidx.compose.material3.GridToolbar
import androidx.compose.material3.GridToolbarAdapter
import androidx.compose.material3.GridToolbarColors
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemIcon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.clickable.onClick
import androidx.compose.ui.unit.dp
import com.aio.calculator.core.common.ToolCategory
import com.aio.calculator.core.common.ToolDefinition
import com.aio.calculator.core.common.ToolRegistry

/**
 * Tool Browser Screen - Browse all calculator tools by category
 */
@Composable
fun ToolBrowserScreen(
    onBack: () -> Unit,
    selectedCategory: ToolCategory?,
    onCategorySelected: (ToolCategory) -> Unit,
    onToolSelected: (ToolDefinition) -> Unit
) {
    val calcColors = MaterialTheme.colorScheme
    val allCategories = ToolCategory.allCategories()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
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

        // Tools grid
        if (selectedCategory == null) {
            // Show all tools
            ToolGrid(tools = ToolRegistry.allTools, onToolSelected = onToolSelected)
        } else {
            // Show tools in selected category
            val categoryTools = ToolRegistry.getTools(selectedCategory!!)
            ToolGrid(tools = categoryTools, onToolSelected = onToolSelected)
        }
    }
}

/** Tool grid composable */
@Composable
fun ToolGrid(
    tools: List<ToolDefinition>,
    onToolSelected: (ToolDefinition) -> Unit
) {
    if (tools.isEmpty()) {
        EmptyState(onBack = { }) {
            Text(text = "No tools in this category")
        }
    } else {
        // Calculate grid columns based on screen width
        val columnCount = if (androidx.compose.ui.platform.LocalContext.current.resources.configuration.screenWidthdpx > 600) {
            4 // Tablet: 4 columns
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
                                                                                                                        }}
                                                                                            }}
                                                                                                }}
                                                                                                        }}
                                                                                                            }}
                                                                                                                    }}
                                                                                                                        }}
                                                                                            }