package com.aio.calculator.feature.calculator

import androidx.compose.foundation.clickable.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemIcon
import androidx.compose.material3.ListItemSecondaryAction
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
import com.aio.calculator.core.common.HistoryItem
import com.aio.calculator.core.common.ToolRegistry

/**
 * Recent Screen - Shows recently used tools
 */
@Composable
fun RecentScreen(
    onBack: () -> Unit,
    recentToolIds: List<String>,
    onSelectRecent: (String) -> Unit
) {
    val calcColors = MaterialTheme.colorScheme
    val recentTools = ToolRegistry.getRecent(recentToolIds)

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(8.dp)
    ) {
        // Header
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            color = calcColors.surface
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Recent",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = calcColors.onSurface
                )

                // Count of recent
                Text(
                    text = "${recentTools.size} recent",
                    fontSize = 12.sp,
                    color = calcColors.onSurface.copy(alpha = 0.5f)
                )
            }
        }

        // Recent list
        if (recentTools.isEmpty()) {
            EmptyRecentState(onBack = onBack)
        } else {
            RecentList(recentTools = recentTools, onSelect = onSelectRecent, calcColors = calcColors)
        }
    }
}

/** Empty recent state */
@Composable
fun EmptyRecentState(onBack: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(32.dp),
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
        ) {
            Icon(
                imageVector = androidx.compose.material.Icons.Default.History,
                contentDescription = "No recent tools",
                tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                modifier = Modifier.size(64.dp)
            )
            Text(
                text = "No recent tools",
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                modifier = Modifier.padding(vertical = 16.dp)
            )
            Text(
                text = "Tools you use will appear here",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
            )
            Button(
                onClick = onBack,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "Browse Tools",
                    fontSize = 16.sp
                )
            }
        }
    }
}

/** Recent list composable */
@Composable
fun RecentList(
    recentTools: List<ToolDefinition>,
    onSelectRecent: (String) -> Unit,
    calcColors: androidx.compose.material3.MaterialTheme.colorScheme
) {
    androidx.compose.foundation.layout
        .padding(Modifier.padding(16.dp))) {
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
                    }}
                }}
              }}
            }}
          }}
        }}
      }}
    }}
}

/** Recent list item */
@Composable
fun RecentListItem(
    tool: ToolDefinition,
    onSelect: () -> Unit
) {
    val color = MaterialTheme.colorScheme

    ListItem(
        modifier = Modifier.fillMaxWidth(),
        display = ListItemDisplay.PrimaryAndSecondary,
        leading = {
            Icon(
                imageVector = when (tool.category) {
                    com.aio.calculator.core.common.ToolCategory.ALGEBRA -> androidx.compose.material.Icons.Default.Article
                    com.aio.calculator.core.common.ToolCategory.STATISTICS -> androidx.compose.material.Icons.Default.Book
                    com.aio.calculator.core.common.ToolCategory.GEOMETRY -> androidx.compose.material.Icons.Default.ContentPerspective
                    com.aio.calculator.core.common.ToolCategory.TRIGONOMETRY -> androidx.compose.material.Icons.Default.Functions
                    com.aio.calculator.core.common.ToolCategory.CALCULUS -> androidx.compose.material.Icons.Default.Code
                    com.aio.calculator.core.common.ToolCategory.PHYSICS -> androidx.compose.material.Icons.Default.DeviceHub
                    com.aio.calculator.core.common.ToolCategory.CHEMISTRY -> androidx.compose.material.Icons.Default.TestTube
                    com.aio.calculator.core.common.ToolCategory.ELECTRONICS -> androidx.compose.material.Icons.Default.PowerSettings
                    com.aio.calculator.core.common.ToolCategory.COMPUTER_SCIENCE -> androidx.compose.material.Icons.Default.Computer
                    com.aio.calculator.core.common.ToolCategory.CONVERTERS -> androidx.compose.material.Icons.Default.Queue
                    com.aio.calculator.core.common.ToolCategory.FINANCE -> androidx.compose.material.Icons.Default.Business
                    com.aio.calculator.core.common.ToolCategory.HEALTH -> androidx.compose.material.Icons.Default.HealthAndFitness
                    com.aio.calculator.core.common.ToolCategory.DATETIME -> androidx.compose.material.Icons.Default.CalendarMonth
                    com.aio.calculator.core.common.ToolCategory.EVERYDAY -> androidx.compose.material.Icons.Default.Home
                    com.aio.calculator.core.common.ToolCategory.SHOPPING -> androidx.compose.material.Icons.Default.ShoppingCart
                    else -> androidx.compose.material.Icons.Default.Help
                },
                tint = calcColors.onSurface,
                modifier = Modifier.size(24.dp)
            )
        },
        content = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {
                ListItemCaption(
                    primary = tool.title,
                    secondary = tool.category.displayName
                )
            }
        ),
        secondary = {
            Button(
                onClick = onSelect,
                mini = true,
                colors = ButtonDefaults.buttonColors(
                    containerColor = calcColors.surface,
                    contentColor = calcColors.onSurface
                ),
                shape = RoundedCornerShape(4.dp)
            ) {
                Icon(
                    imageVector = androidx.compose.material.Icons.Default.ArrowForward,
                    tint = calcColors.primary,
                    contentDescription = "Select tool"
                )
            }
        }
    )
}